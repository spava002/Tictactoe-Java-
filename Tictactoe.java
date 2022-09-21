import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String[][] game = {{"-", "-", "-"},
                      {"-", "-", "-"},
                      {"-", "-", "-"}};
                      
    //Print out the starting table
    for (int row = 0; row < game.length; row++) {
      for (int col = 0; col < game[row].length; col++) {
        System.out.print(game[row][col] + "\t");
      }
      System.out.println("");
    }
    
    //Variables that keep track of who's turn it is and if the game has ended 
    
    int turnCount = 0;
    String turn = "";
    boolean gameEnd = false;

    //Game code
    while (gameEnd != true) {
      
      //Determine if player X or player O
      if (turnCount % 2 == 0) {
        turn = "X";
      }
      else if (turnCount % 2 != 0) {
        turn = "O";
      }

      //Player input
      System.out.print("Enter a row (0, 1, or 2) for player " + turn + ": ");
      int rowInput = input.nextInt();
      System.out.print("Enter a column (0, 1, or 2) for player " + turn + ": ");
      int colInput = input.nextInt();

      System.out.println("");

      //Check to see if row and column is in proper bounds
      while (rowInput > 2 || rowInput < 0) {
        System.out.print("Invalid row, try again: ");
        rowInput = input.nextInt();
      }
      while (colInput > 2 || colInput < 0) {
        System.out.print("Invalid column, try again: ");
        colInput = input.nextInt();
      }

      //Check to see that no existing value is there already
      while (game[rowInput][colInput] != "-") {
        System.out.println("This spot is already taken, try again.");
        
        System.out.print("Enter a new row (0, 1, or 2): ");
        rowInput = input.nextInt();
        System.out.print("Enter a new column (0, 1, or 2): ");
        colInput = input.nextInt();

        System.out.println("");
      }

      //Assign letter played on board based on turns
      if (turnCount % 2 == 0) {
        game[rowInput][colInput] = "X";
      }
      else if (turnCount % 2 != 0) {
        game[rowInput][colInput] = "O";
      }

      //Print new gameboard with recent input
      for (int row = 0; row < game.length; row++) {
        for (int col = 0; col < game[row].length; col++) {
          System.out.print(game[row][col] + "\t");
        }
        System.out.println("");
      }

    //Check if a player has won
    gameEnd = winHorizontal(game);
    if (gameEnd == true) {
      System.out.print("Player " + turn + " wins!");
      break;
    }
    gameEnd = winVertical(game);
    if (gameEnd == true) {
      System.out.print("Player " + turn + " wins!");
      break;
    }
    gameEnd = winDiagonal(game);
    if (gameEnd == true) {
      System.out.print("Player " + turn + " wins!");
      break;
    }

    //Change the turn
    turnCount++;

    //Check if no more moves can be made
    if (gameEnd == false && turnCount == 9) {
      System.out.print("The game ended in a draw!");
      gameEnd = true;
    }

    }
  }

  //Methods to check if player wins
  public static boolean winHorizontal(String[][] game) {
    boolean win = false;
    for (int row = 0; row < game.length; row++) {
      String checker = "";
      for (int col = 0; col < game[0].length; col++) {
        checker = checker + game[row][col];
      }
      if (checker.equals("XXX") || checker.equals("OOO"))
        win = true;
      }
      return win;
  }

  public static boolean winVertical(String[][] game) {
    boolean win = false;
    for (int col = 0; col < game[0].length; col++) {
      String checker = "";
      for (int row = 0; row < game.length; row++) {
        checker = checker + game[row][col];
      }
      if (checker.equals("XXX") || checker.equals("OOO"))
        win = true;
    }
    return win;
  }

  public static boolean winDiagonal(String[][] game) { 
   boolean win = false;
   String checker = "";

   checker = game[0][0] + game[1][1] + game[2][2];
   if (checker.equals("XXX") || checker.equals("OOO"))
      win = true;
   
   checker = game[0][2] + game[1][1] + game[2][0];
   if (checker.equals("XXX") || checker.equals("OOO"))
      win = true;

   return win; 
  }
}