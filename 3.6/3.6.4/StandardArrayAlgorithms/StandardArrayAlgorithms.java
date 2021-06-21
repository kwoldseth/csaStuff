/*
 * Activity 3.6.4
 */
public class StandardArrayAlgorithms
{
  public static void main(String[] args)
  {
    int[] goals = {1, 2, 0, 3, 2, 4, 2, 1, 0, 2, 0, 1, 3, 2};
    //int[] goals = {1, 2, 0, 3, 2, 4, 2, 1, 1}; //step 9
    
    //provided code - SUM
      int sum = 0;
      for (int i = 0; i < goals.length; i++)
        sum += goals[i];
      
      System.out.println("All goals: " + sum);

    //step 2 - AVERAGE
      double avg = 0;
      for (int i = 0; i < goals.length; i++)
        avg += goals[i];
      avg = avg/(double)goals.length;
      
      System.out.println("Average goals: " + avg);

    //steps 3-5 - MAXIMUM and MINIMUM
      int max = goals[0];
      int min = goals[0];

      for (int i = 1; i < goals.length; i++)
      {
        if(goals[i]>max)
          max = goals[i];
        if(goals[i]<min)
          min = goals[i];    
      }

      System.out.println("Max: " + max);
      System.out.println("Min: " + min);

    /**
     * This algorithm calculates the mode of a list.
     * Pre-condition: The list has one and only one mode. If multiple modes exist, the algorithm will only report the first mode that occurs.
     */
    //MODE ALGORITHM
  
        //step 6
        int[] goalCounter = new int[10];

        //step 7
        for(int i=0; i<goals.length; i++)
        {
          goalCounter[goals[i]]++; //add one to goalCounter at value of goals[i]
        }

        //step 8: test goalCounter algorithm

        //System.out.println("goalCounter Array:"); //test code
        int mode = goals[0];
        for(int i = 0; i<goalCounter.length; i++)
        {
          //System.out.println(i + " goals: " +goalCounter[i]); //test code
          if(goalCounter[i] > mode)
          mode = i;
        }
        System.out.println("Mode: " + mode + " is the mode. " + mode + " goals occurs " + goalCounter[mode] + " times.");

    //END OF MODE ALGORITHM


    //step 13
        Player[] players = {new Player("Alex", 12), new Player("Aiden", 13),
        new Player("Bobbie", 18), new Player("Blaine", 20),
        new Player("Chris", 15),   new Player("Sally", 15) };

    //steps 14-17 -- AT LEAST ONE
        boolean hasValue = false;
        int i = 0;
        while(!hasValue && i<players.length)
        {
          if(players[i].getAge() >= 18)
          {
            hasValue = true;
          }
          i++;
        }
        System.out.println("list has at least one player 18+: " + hasValue);

    //steps 18-20 -- ALL HAVE PROPERTY
        boolean allHaveValue = true;
        for(Player p : players)
          {
            if(!(p.getAge() < 21))
            {
              allHaveValue = false;
            }
          }
          System.out.println("everyone in list is <21: " + allHaveValue);

    //steps 21-23 -- COUNT HOW MANY ELEMENTS THAT...
          int count = 0;
          for(Player p : players)
          {
            if(p.getAge()==15)
              count++;
          }
          System.out.println("Number of players in list that are 15 years old: " + count);

    //step 24 -- PRINT CONSECUTIVE PAIRS
          for(int j = 0; j<players.length-1;j+=2)
          {
            System.out.println(players[j].getName() + " and " + players[j+1].getName());
          }
          if(players.length%2 == 1) //odd amount of players
             System.out.println(players[players.length-1].getName());

  }//end of main
  
}//end of class