
public class RandomPermutation {
 
 
/**creates a randomized list of integers from 1 to 10 without duplicates
 * @return int[]
*/
public static int[] next(int length)
{
    int[] p = new int[length];
    int[] r = new int[length];

    //fill up p
    for(int i=0; i<length; i++)
    {
        p[i]=i+1;
    }

    int rand;
    int count = p.length;
    int j = 0;

    for(int i = 0; i<p.length; i++)
    {
        //Select an element in array p from a random location
        rand = (int)(Math.random()*(count-1));
        
        //Copy its value to the last unused element of array r
        r[j] = p[rand];
        j++;
    
        //Copy the last value in p to the location in p you just used
        p[rand]=p[count-1];

        //Decrement a counter so that the last element of p will not ben used more than once
        count--;

    }//end of for loop

    return r;
}//end of next

}//end of class
