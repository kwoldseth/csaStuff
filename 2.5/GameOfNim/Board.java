/** 2.5.11 Game of Nim
 * 
 * Board class
 * 
 * */ 

public class Board {
    
    private static int pieces;

    public static void populate()
    {
        pieces = (int)(Math.random()*41+10);
    }



    //GETTERS
    public static int getNumPieces()
    {
        return pieces;
    }

    //SETTERS
    public static void removePieces(int numPieces)
    {
            pieces -= numPieces;
    }

}