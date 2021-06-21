import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * Class that contains helper methods for the Review Lab
 **/
public class Review {
  
  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
  private static ArrayList<String> posAdjectives = new ArrayList<String>();
  private static ArrayList<String> negAdjectives = new ArrayList<String>();
 
  public static void main(String[] args)
  {
    //Activity 1
    /*
    System.out.println("****** Activity 1 ******");
    System.out.println(sentimentVal("cheerful"));
    System.out.println(sentimentVal("sad"));
    System.out.println(sentimentVal("anxious"));
    */

    //Activity 2
    /*
    System.out.println("****** Activity 2 ******");
    System.out.println(totalSentiment("SimpleReview.txt"));
    System.out.println(starRating("SimpleReview.txt"));
    System.out.println(totalSentiment("turf.txt"));
    System.out.println(starRating("turf.txt"));
    System.out.println(totalSentiment("happyHeartHaven.txt"));
    System.out.println(starRating("happyHeartHaven.txt"));
    */

    //Activity 3
    System.out.println("****** Activity 3 ******");
    System.out.println(fakeReview("SimpleReview.txt"));
    System.out.println(fakeReview("happyHeartHaven.txt"));

  }
  
  //ACTIVITY 2 METHOD
   /*Method to determine a total Sentiment value based on the sentiment value of all the words in a review found in a file.
   *
   * @param fileName - the name of the txt file with the review
   * @return the total sentiment value as a double
  */
  public static double totalSentiment(String fileName)
  {
    String reviewString = textToString(fileName);
    double total = 0.0;
    int len = reviewString.length();
    String currLetter = "";
    String word = "";

    for(int i = 0; i<len; i++)
    {
      currLetter = reviewString.substring(i,i+1);
      if(Character.isAlphabetic(reviewString.charAt(i)))
      {
        word += currLetter;
      }
      else if(currLetter.equals(" ") || i==len-1)
      {
        total += sentimentVal(word);
        word = "";
      }
    }
    return total;
  }

  //ACTIVITY 2 METHOD
  /*Method to determine a starRating based on the totalSentiment of a review found in a file.
   *
   * @param fileName - the name of the txt file with the review
   * @return the star rating of the review 1-5 as an int
  */
  public static int starRating(String fileName)
  {
    int rating = 0;
    double totalSent = totalSentiment(fileName);
    if(totalSent < 0)
      rating = 0;
    else if(totalSent < 10)
      rating = 1;
    else if(totalSent < 20)
      rating = 2;
    else if(totalSent < 30)
      rating = 3;
    else
      rating = 4;
    return rating;
  }

  //ACTIVITY 3 METHOD
  /*Method to create a fake review by replacing adjectives in an existing review.
  *
  * @param file containing original review
  * @return String with fake review
  */
  public static String fakeReview(String fileName) 
  {
    String originalReview = textToString(fileName);
    String fakeReview = "";
    String adj = "";
    int asteriskIndex = 0;
    int spaceIndex = 0;
    int i = 0;
    String newAdjective;

    //iterate through original and take substrings, replacing each adjective
    while(i< originalReview.length())
    {
      asteriskIndex = originalReview.indexOf("*");
      if(asteriskIndex != -1)
      {
        fakeReview += originalReview.substring(0,asteriskIndex); //copy first part of string to fake Review
        originalReview = originalReview.substring(asteriskIndex+1); //copy rest of review to temp
        spaceIndex = originalReview.indexOf(" ");
        adj = originalReview.substring(0,spaceIndex); //get adjective
        newAdjective = newAdj(adj);
        originalReview = originalReview.substring(spaceIndex+1); //copy rest of review to temp
        fakeReview +=  newAdjective; 
        i++;
      }
      else //no more adjectives left
      {
        fakeReview += originalReview;
        break;
      }
    }
    return fakeReview;
  }

  /*ACTIVITY 3 HELPER METHOD
  * method to replace the adjective based on sentiment value of positive or negative
  * @param String with old adjective
  * @return String with new adjective
  */
  private static String newAdj(String oldAdj)
  {
    String pos =  textToString("positiveAdjectives.txt");
    String neg =  textToString("negativeAdjectives.txt");
    String tmp = "";
    String newWord = "";

    if (sentimentVal(oldAdj) > 0)
    {
      int rand = (int)(Math.random()*pos.length()-1);
      tmp = pos.substring(rand);//random character
      if(tmp.indexOf(" ") != -1)//find next space
      {
        tmp = tmp.substring(tmp.indexOf(" ")+1);
        if(tmp.indexOf(" ") != -1)
        {
         newWord = tmp.substring(0,tmp.indexOf(" ")) + " ";//take next word
         }
        else //last word
          newWord = tmp + " ";   
      }
      else
      {
        newAdj(oldAdj); //do the method over
      }
    }
    else
    {
      int rand = (int)(Math.random()*(neg.length()-1));
      tmp = neg.substring(rand);//random character

      if(tmp.indexOf(" ") != -1)//find next space
      {
        tmp = tmp.substring(tmp.indexOf(" ")+1);
        if(tmp.indexOf(" ") != -1)
          newWord = tmp.substring(0,tmp.indexOf(" ")) + " ";//take next word
        else //last word
         newWord = tmp + " ";
      }
      else
      {
        newAdj(oldAdj); //do the method over
      }
      System.out.println(newWord);
    }
    return newWord;
  }
  private static final String SPACE = " ";
  
  static{
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while(input.hasNextLine()){
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0],Double.parseDouble(temp[1]));
        //System.out.println("added "+ temp[0]+", "+temp[1]);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }
  
  
  //read in the positive adjectives in postiveAdjectives.txt
     try {
      Scanner input = new Scanner(new File("positiveAdjectives.txt"));
      while(input.hasNextLine()){
        String temp = input.nextLine().trim();
        System.out.println(temp);
        posAdjectives.add(temp);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }   
 
  //read in the negative adjectives in negativeAdjectives.txt
     try {
      Scanner input = new Scanner(new File("negativeAdjectives.txt"));
      while(input.hasNextLine()){
        negAdjectives.add(input.nextLine().trim());
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing negativeAdjectives.txt");
    }   
  }
  
  /** 
   * returns a string containing all of the text in fileName (including punctuation), 
   * with words separated by a single space 
   */
  public static String textToString( String fileName )
  {  
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));
      
      //add 'words' in the file to the string, separated by a single space
      while(input.hasNext()){
        temp = temp + input.next() + " ";
      }
      input.close();
      
    }
    catch(Exception e){
      System.out.println("Unable to locate " + fileName);
    }
    //make sure to remove any additional space that may have been added at the end of the string.
    return temp.trim();
  }
  
  /**
   * @returns the sentiment value of word as a number between -1 (very negative) to 1 (very positive sentiment) 
   */
  public static double sentimentVal( String word )
  {
    try
    {
      return sentiment.get(word.toLowerCase());
    }
    catch(Exception e)
    {
      return 0;
    }
  }
  
  /**
   * Returns the ending punctuation of a string, or the empty string if there is none 
   */
  public static String getPunctuation( String word )
  { 
    String punc = "";
    for(int i=word.length()-1; i >= 0; i--){
      if(!Character.isLetterOrDigit(word.charAt(i))){
        punc = punc + word.charAt(i);
      } else {
        return punc;
      }
    }
    return punc;
  }
  
  /** 
   * Randomly picks a positive adjective from the positiveAdjectives.txt file and returns it.
   */
  public static String randomPositiveAdj()
  {
    int index = (int)(Math.random() * posAdjectives.size());
    return posAdjectives.get(index);
  }
  
  /** 
   * Randomly picks a negative adjective from the negativeAdjectives.txt file and returns it.
   */
  public static String randomNegativeAdj()
  {
    int index = (int)(Math.random() * negAdjectives.size());
    return negAdjectives.get(index);
    
  }
  
  /** 
   * Randomly picks a positive or negative adjective and returns it.
   */
  public static String randomAdjective()
  {
    boolean positive = Math.random() < .5;
    if(positive){
      return randomPositiveAdj();
    } else {
      return randomNegativeAdj();
    }
  }
}
