import java.util.Scanner;

public class Game {
    
    private Player player1;
    private Player player2;
    private boolean playAgain = true;

    public Game()
    {
        player1 = new Player();
        player2 = new Player();
    }
    

    public void play()
    {
        Player currPlayer;
        Scanner s = new Scanner(System.in);

      while(playAgain == true)
      {
            //randomize who goes first
            int rand = (int)(Math.random()+1);
            if(rand == 1)
                currPlayer = player1;
            else   
                currPlayer = player2;
           
        while(Board.getNumPieces() > 1)
        {

            System.out.println("There are " + Board.getNumPieces() + " pieces on the board.");

            //remove pieces from board
            System.out.println(currPlayer.getName() + ", how many pieces would you like to remove?");
            int num = s.nextInt();
            while(num<1 || num > Board.getNumPieces()/2)
            {
                System.out.println(currPlayer.getName() + ", how many pieces would you like to remove?");
                num = s.nextInt();
            }
            Board.removePieces(num);

            //switch players
            if(currPlayer.equals(player1))
            {
                currPlayer = player2;
            }
            else
                currPlayer = player1;
        }//end of inner while loop
        //determine winner or loser
        if(currPlayer.equals(player1))
        {
            player1.incrScore();
            currPlayer = player2;
        }
        else
        {
            player2.incrScore();
            currPlayer = player1;
        }
        System.out.print("The game is over.  ");
        System.out.println(currPlayer.getName() + " loses this round.");

        System.out.println("Current Score for " + player1.getName() + ": " + player1.getScore());
        System.out.println("Current Score for " + player2.getName() + ": " + player2.getScore());

        //replay option
        System.out.println("Do you want to play again? (y/n)");
        if(s.next().toLowerCase().equals("n"))
        {
            playAgain = false;
            System.out.println("Thanks for playing!");
        }
        else
        {
            Board.populate();
            System.out.println("Great! Let's play again!");
            

        }
    }//end of outer while loop
    }//end of play
}//end of class
