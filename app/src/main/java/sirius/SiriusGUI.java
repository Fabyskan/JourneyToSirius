/*
 *
 * ░▒█▀▀▄░▒█▀▀▀█░▒█▄░▒█░█░▀▀█▀▀░░░▒█▀▀▄░▒█░▒█░█▀▀▄░▒█▄░▒█░▒█▀▀█░▒█▀▀▀
 * ░▒█░▒█░▒█░░▒█░▒█▒█▒█░░░░▒█░░░░░▒█░░░░▒█▀▀█▒█▄▄█░▒█▒█▒█░▒█░▄▄░▒█▀▀▀
 * ░▒█▄▄█░▒█▄▄▄█░▒█░░▀█░░░░▒█░░░░░▒█▄▄▀░▒█░▒█▒█░▒█░▒█░░▀█░▒█▄▄▀░▒█▄▄▄
 *
 */
package sirius;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SiriusGUI extends JPanel implements KeyListener {

  public static final int WIDTH = 512;
  public static final int HEIGHT = 512;

  private SiriusGameManager gameManager;

  private double time;

  private double startTime;
  private double lastUpdateTime;

  private List<DrawInfo>  drawQueue;

  private Map<String, Image> imageCache;

  private static PlayerInput playerInput;

  private record DrawInfo(String image, double x, double y){};

  public SiriusGUI(SiriusGameManager gameManager){
    super();

    if (gameManager == null)
      throw new IllegalArgumentException("game manager must not be null");

    this.gameManager = gameManager;
    this.drawQueue = new LinkedList<>();
    this.imageCache = new HashMap<>();
    SiriusGUI.playerInput = PlayerInput.NONE;

    JFrame frame = new JFrame("Journey to Sirius");
    frame.setSize(WIDTH, HEIGHT);
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    frame.add(this);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setVisible(true);

    frame.addKeyListener(this);

    startTime = System.currentTimeMillis()/1000d;
    time = 0d;
    lastUpdateTime = time;

    Timer updateTimer = new Timer(16, this::updateState);
    updateTimer.setRepeats(true);
    updateTimer.start();
  }

  public double getTime() {
    return time;
  }

  private void updateState(ActionEvent event){
    drawQueue.clear();

    time = System.currentTimeMillis()/1000d - startTime;
    double deltaTime = time - lastUpdateTime;
    this.gameManager.update(time, deltaTime);
    lastUpdateTime = time;

    this.paintImmediately(this.getBounds());
    this.repaint();
    Toolkit.getDefaultToolkit().sync();

  }

  public void draw(String imageFile, double x, double y){
    this.drawQueue.add(new DrawInfo(imageFile, x, y));
  }

  public void gameOver(){
    int minutes = (int) (time/60);
    int seconds = (int) (time%60);
    JOptionPane.showMessageDialog(this, String.format("Game Over! You lasted %d minutes and %d seconds", minutes, seconds));

    System.exit(0);

  }

  private Image loadImage(String imageFile) throws IOException{

    if (!imageCache.containsKey(imageFile)) {
      imageCache.put(
          imageFile, 
          ImageIO.read(ClassLoader.getSystemClassLoader().getResource(String.format("%s.png", imageFile))));
    }

    return imageCache.get(imageFile);

  }

  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);

    try {
      Image background = loadImage("background");

      int backgroundScroll = ((int)(100d*time))%HEIGHT;
      g.drawImage(background, 0, backgroundScroll, null);
      g.drawImage(background, 256, backgroundScroll, null);
      g.drawImage(background, 0, backgroundScroll-512, null);
      g.drawImage(background, 256, backgroundScroll-512, null);

      for (DrawInfo drawInfo : drawQueue){
        Image actorImage = loadImage(drawInfo.image());
        int x = (int) (drawInfo.x() - actorImage.getWidth(null)/2.0);
        int y = (int) (drawInfo.y() + actorImage.getHeight(null)/2.0);

        g.drawImage(actorImage, x, y, null);

      }

    } catch (IOException ioException){
      System.err.println(ioException);
      System.exit(1);
    }



  }

  public static PlayerInput getPlayerInput(){
    return SiriusGUI.playerInput;
  }

  @Override
  public void keyTyped(KeyEvent e) { }

  @Override
  public void keyPressed(KeyEvent e) {
    SiriusGUI.playerInput = switch (e.getKeyCode()){
      case KeyEvent.VK_LEFT, KeyEvent.VK_H, KeyEvent.VK_A -> PlayerInput.LEFT;
      case KeyEvent.VK_RIGHT, KeyEvent.VK_L, KeyEvent.VK_D -> PlayerInput.RIGHT;
      default -> SiriusGUI.playerInput;
    };
  }

  @Override
  public void keyReleased(KeyEvent e) {
    SiriusGUI.playerInput = switch (e.getKeyCode()){
      case KeyEvent.VK_LEFT, KeyEvent.VK_H, KeyEvent.VK_A, 
           KeyEvent.VK_RIGHT, KeyEvent.VK_L, KeyEvent.VK_D -> PlayerInput.NONE;
      default -> SiriusGUI.playerInput;
    };
  }

}
