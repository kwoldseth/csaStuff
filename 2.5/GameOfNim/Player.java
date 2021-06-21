/** 2.5.11 Game of Nim
 * 
 * Player class
 * 
 * */ 

import java.util.Scanner;

 public class Player
{
    private String name;
    private int score = 0;

    //Constructor
    public Player()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Type player name: ");
        name =  s.nextLine();
    }

    //GETTERS
    public String getName()
    {
        return name;
    }
    public int getScore()
    {
        return score;
    }

    //SETTERS
    public void incrScore()
    {
        score++;
    }
}