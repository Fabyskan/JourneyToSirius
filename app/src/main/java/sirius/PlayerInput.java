/*
 *
 * ░▒█▀▀▄░▒█▀▀▀█░▒█▄░▒█░█░▀▀█▀▀░░░▒█▀▀▄░▒█░▒█░█▀▀▄░▒█▄░▒█░▒█▀▀█░▒█▀▀▀
 * ░▒█░▒█░▒█░░▒█░▒█▒█▒█░░░░▒█░░░░░▒█░░░░▒█▀▀█▒█▄▄█░▒█▒█▒█░▒█░▄▄░▒█▀▀▀
 * ░▒█▄▄█░▒█▄▄▄█░▒█░░▀█░░░░▒█░░░░░▒█▄▄▀░▒█░▒█▒█░▒█░▒█░░▀█░▒█▄▄▀░▒█▄▄▄
 *
 */
package sirius;

public enum PlayerInput{
  LEFT, NONE, RIGHT;


  public static PlayerInput toInput(String input){
    return switch(input) {
      case "player-left" -> LEFT;
      case "player-right" -> RIGHT;
      default -> NONE;
    };
  }
}

