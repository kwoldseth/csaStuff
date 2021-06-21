import com.badlogic.gdx.ApplicationListener; 
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.*; 
import com.badlogic.gdx.graphics.*;
import java.util.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
public class PuzzleGame implements ApplicationListener
{
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer; // this is our pen to draw shapes
    private int[][] grid;
    private Vector2 pos;
    private SpriteBatch batch;
    private BitmapFont font;
    public static final float WORLD_WIDTH = 3;
    public static final float WORLD_HEIGHT = 3;
    private int[][] world;
    public static final float WORLD1_WIDTH = 300;
    public static final float WORLD1_HEIGHT = 300;
    private ArrayList<Integer> random = new ArrayList<Integer>();
    private ArrayList<Texture> nums;
    private boolean started = false;
    private boolean won1 = false;
    private boolean won2 = false;
    private Music win;
    private Music jeopardy;
    private boolean reset = false;
    private Music waiting;
    private Texture directions;
    private Texture spaceBar;
    private Texture title;
    private ArrayList<Texture> keys;
    private int[][] grid4By4;
    private int[][] grid5By5;
    private boolean hasWonOnce = false;
    private Texture pressA;
    private boolean secondWin = false;
    private ArrayList<Texture> redNums;
    private Sound click;
    private Texture winner; 
    @Override
    public void create()
    {        
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        grid = new int[(int)WORLD_WIDTH][(int)WORLD_HEIGHT];
        grid4By4 = new int[4][4];
        grid5By5 = new int[5][5];
        redNums = new ArrayList<Texture>();
        int index;
        font = new BitmapFont();
        world = new int[(int)WORLD1_WIDTH][(int)WORLD1_HEIGHT];
        nums = new ArrayList<Texture>();
        keys = new ArrayList<Texture>();
        pressA = new Texture(Gdx.files.internal("pressA.png"));
        directions = new Texture(Gdx.files.internal("icecube.png"));
        keys.add(new Texture(Gdx.files.internal("KeyboardButtonsDir_down.png")));
        keys.add(new Texture(Gdx.files.internal("KeyboardButtonsDir_left.png")));
        keys.add(new Texture(Gdx.files.internal("KeyboardButtonsDir_right.png")));
        keys.add(new Texture(Gdx.files.internal("KeyboardButtonsDir_up.png")));
        nums.add(new Texture(Gdx.files.internal("Number_Blocks_01_Set_4_512x512_1.png")));
        nums.add(new Texture(Gdx.files.internal("Number_Blocks_01_Set_4_512x512_2.png")));
        nums.add(new Texture(Gdx.files.internal("Number_Blocks_01_Set_4_512x512_3.png")));
        nums.add(new Texture(Gdx.files.internal("Number_Blocks_01_Set_4_512x512_4.png")));
        nums.add(new Texture(Gdx.files.internal("Number_Blocks_01_Set_4_512x512_5.png")));
        nums.add(new Texture(Gdx.files.internal("Number_Blocks_01_Set_4_512x512_6.png")));
        nums.add(new Texture(Gdx.files.internal("Number_Blocks_01_Set_4_512x512_7.png")));
        nums.add(new Texture(Gdx.files.internal("Number_Blocks_01_Set_4_512x512_8.png")));
        nums.add(new Texture(Gdx.files.internal("Number_Blocks_01_Set_4_512x512_9.png")));
        
        redNums.add(new Texture(Gdx.files.internal("numberblock10.png")));
        redNums.add(new Texture(Gdx.files.internal("numerblock11.png")));
        redNums.add(new Texture(Gdx.files.internal("numberblock12.png")));
        redNums.add(new Texture(Gdx.files.internal("numberblock13.png")));
        redNums.add(new Texture(Gdx.files.internal("numberblock14.png")));
        redNums.add(new Texture(Gdx.files.internal("numberblock15.png")));
        
        win = Gdx.audio.newMusic(Gdx.files.internal("win.mp3"));
        jeopardy = Gdx.audio.newMusic(Gdx.files.internal("Jeppy.mp3"));
        spaceBar = new Texture(Gdx.files.internal("spacebar.png"));
        title = new Texture(Gdx.files.internal("puzzlegame.png"));
        waiting = Gdx.audio.newMusic(Gdx.files.internal("waiting.mp3"));
        winner = new Texture(Gdx.files.internal("winner.png"));
        //order = new Texture(Gdx.files.internal("order.png"));
        click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
        ArrayList<Integer> all = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6,7, 8));
        for(int i = 8; i >= 0; i--)
        {
            index = (int)(Math.random() * i );
            random.add(all.remove(index));
        }

        for(int r = 0; r < grid.length; r ++)
        {
            for(int c = 0; c < grid[0].length; c++)
            {
                grid[r][c] = random.remove(0);
                //System.out.print(grid[r][c]);

            }
            //System.out.println();
        }

        // int ctr = 0;
        // for(int r = 0; r < grid.length; r ++)
        // {
        // for(int c = 0; c < grid[0].length; c++)
        // {

        // grid[r][c] = ctr;
        // ctr++;
        // }
        // }
        // printGrid(); //testing
    }

    public void printGrid()
    {

        for(int r = 0; r < grid.length; r ++)
        {
            for(int c = 0; c < grid[0].length; c++)
            {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true); // true puts 0,0 in bottom left corner 
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0,0,0,1); //sets background color R - 100/256f
        // G- 200/256f
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // WIPES SCREEN EVERY FRAME
        GlyphLayout layout = new GlyphLayout(font, " ");//this is so 
        // //we can get the width of whatever text we are typing to center it
        // //GlyphLayout has a public field (instance variable) called width
        if(Gdx.input.isKeyPressed(Keys.SPACE))
        {
            started = true;
        }
        batch.begin();
        if(!started)
        {
            font.draw(batch, layout, 
                WORLD1_WIDTH/2 - layout.width /2, 150);
            batch.draw(directions, WORLD1_WIDTH/2 - directions.getWidth()/2, 100);
            drawKeys();
            batch.draw(spaceBar, WORLD1_WIDTH/2 - spaceBar.getWidth()/2, 150);
            batch.draw(title, WORLD1_WIDTH/2 - title.getWidth()/2, 200);
            waiting.play();
            waiting.setLooping(true);
        }
        font.draw(batch, "", WORLD_WIDTH / 2 - 20, 440); 
        Texture trophy = new Texture(Gdx.files.internal("golden_trophy.png"));
        if(started && !(won1 || won2))
        {
            waiting.pause();
            updateGrid();
            win.pause();
            jeopardy.setLooping(true);
            jeopardy.play();
            won1 = inOrderBottRight();
            won2 = inOrderTopLeft();
            drawNums();
        }
        else if(won1 || won2)
        {
            jeopardy.pause();
            win.setLooping(true);
            win.play();
            batch.draw(winner, WORLD1_WIDTH / 2 - winner.getWidth() / 2, 35);
            batch.draw(trophy, trophy.getWidth() / 2 - WORLD1_WIDTH / 2 - 5,trophy.getHeight() /2 - WORLD1_HEIGHT / 2 , 95, 120);

            batch.draw(pressA, WORLD1_WIDTH / 2 - pressA.getWidth() / 2, 220);
            if(Gdx.input.isKeyJustPressed(Keys.A))
            {
                reset = true;
                //System.out.println("radha");
            }
        }
        if(reset)
        {
            reset = false;

            ArrayList<Integer> all = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6,7, 8, 9, 10, 11, 12, 13, 14, 15, 16));

            for(int i = 15; i >= 0; i--)
            {
                int index = (int)(Math.random() * i );
                random.add(all.remove(index));
            }

            for(int r = 0; r < grid4By4.length; r ++)
            {
                for(int c = 0; c < grid4By4[0].length; c++)
                {
                    grid4By4[r][c] = random.remove(0);
                    //System.out.print(grid[r][c]);
                }
                //System.out.println();
            }
            won1 = false; 
            won2 = false;
            hasWonOnce= true;
            //for(int r = 0; r < grid4By4.length; r ++)
            //{
            //for(int c = 0; c < grid4By4[0].length; c++)
            //  {

            // grid4By4[r][c] = ctr;
            //ctr++;
            //  }
            //}
            //}

        }

        batch.end(); 
    }

    private void drawKeys()
    {
        int c = 70;
        for(int i = 0; i < keys.size(); i++)
        {
            batch.draw(keys.get(i), c, 50, 30, 30);
            c+= 50;

        }
    }

    private void updateGrid()
    {
        int rowTemp = getRow();
        int colTemp = getCol();
        int temp;
        if(!hasWonOnce)
        {
            if(Gdx.input.isKeyJustPressed(Keys.DOWN) && getRow()!= 0)
            {
                temp = grid[rowTemp][colTemp];
                grid[rowTemp][colTemp] = grid[rowTemp-1][colTemp];
                grid[rowTemp-1][colTemp] = temp;
                // printGrid();
                click.play();
            }
            if(Gdx.input.isKeyJustPressed(Keys.UP) && rowTemp + 1 < grid.length)
            {
                temp = grid[rowTemp][colTemp];
                grid[rowTemp][colTemp] = grid[rowTemp+1][colTemp];
                grid[rowTemp+1][colTemp] = temp;
                // printGrid();
                click.play();
            }
            if(Gdx.input.isKeyJustPressed(Keys.LEFT) && colTemp + 1 < grid[0].length)
            {
                temp = grid[rowTemp][colTemp];
                grid[rowTemp][colTemp] = grid[rowTemp][colTemp+1];
                grid[rowTemp][colTemp+1] = temp;
                click.play();
                // printGrid();
            }
            if(Gdx.input.isKeyJustPressed(Keys.RIGHT) && getCol() > 0)
            {
                temp = grid[rowTemp][colTemp];
                grid[rowTemp][colTemp] = grid[rowTemp][colTemp-1];
                grid[rowTemp][colTemp-1] = temp;
                click.play();
                // printGrid();
            }
        }
        else if(hasWonOnce)
        {
            if(Gdx.input.isKeyJustPressed(Keys.DOWN) && getRow()!= 0)
            {
                temp = grid4By4[rowTemp][colTemp];
                grid4By4[rowTemp][colTemp] = grid4By4[rowTemp-1][colTemp];
                grid4By4[rowTemp-1][colTemp] = temp;
                click.play();
                // printGrid();

            }
            if(Gdx.input.isKeyJustPressed(Keys.UP) && rowTemp + 1 < grid4By4.length)
            {
                temp = grid4By4[rowTemp][colTemp];
                grid4By4[rowTemp][colTemp] = grid4By4[rowTemp+1][colTemp];
                grid4By4[rowTemp+1][colTemp] = temp;
                click.play();
                // printGrid();
            }
            if(Gdx.input.isKeyJustPressed(Keys.LEFT) && colTemp + 1 < grid4By4[0].length)
            {
                temp = grid4By4[rowTemp][colTemp];
                grid4By4[rowTemp][colTemp] = grid4By4[rowTemp][colTemp+1];
                grid4By4[rowTemp][colTemp+1] = temp;
                click.play();
                // printGrid();
            }
            if(Gdx.input.isKeyJustPressed(Keys.RIGHT) && getCol() > 0)
            {
                temp = grid4By4[rowTemp][colTemp];
                grid4By4[rowTemp][colTemp] = grid4By4[rowTemp][colTemp-1];
                grid4By4[rowTemp][colTemp-1] = temp;
                click.play();
                // printGrid();
            }
        }

    }

    private boolean inOrderTopLeft()
    {
        ArrayList<Integer> test = new ArrayList<Integer>();
        int ctr = 1;
        if(!hasWonOnce)
        {
            for(int r = 0; r < grid.length; r++)
            {
                for(int c=0; c< grid[0].length; c++)
                {
                    test.add(grid[r][c]);
                }
            }
            if(test.get(0) == 0)
            {
                test.remove(0);
                for(int i = 0; i < test.size(); i++)
                {
                    if(test.get(i) != ctr)
                    {
                        return false;
                    }
                    ctr++;
                }
            }
            else 
                return false;
            // System.out.println("1");
        }
        else if(hasWonOnce)
        {
            for(int r = 0; r < grid4By4.length; r++)
            {
                for(int c=0; c< grid4By4[0].length; c++)
                {
                    test.add(grid4By4[r][c]);
                }
            }
            if(test.get(0) == 0)
            {
                test.remove(0);
                for(int i = 0; i < test.size(); i++)
                {
                    if(test.get(i) != ctr)
                    {
                        return false;
                    }
                    ctr++;
                }
            }
            else
                return false;

        }
        return true;
    }

    private boolean inOrderBottRight()
    {
        ArrayList<Integer> test = new ArrayList<Integer>();
        int ctr = 1;
        if(!hasWonOnce)
        {
            for(int r = 0; r < grid.length; r++)
            {
                for(int c=0; c< grid[0].length; c++)
                {
                    test.add(grid[r][c]);
                }
            }
            // System.out.println(test);
            if(test.get(test.size() - 1) == 0)
            {
                test.remove(test.size()-1);
                for(int i = 0; i < test.size(); i++)
                {
                    if(test.get(i) != ctr)
                    {
                        return false;
                    }
                    ctr++;
                }
            }
            else
                return false;
            // System.out.println("2");
        }
        else
        {
            for(int r = 0; r < grid4By4.length; r++)
            {
                for(int c=0; c< grid4By4[0].length; c++)
                {
                    test.add(grid4By4[r][c]);
                }
            }
            // System.out.println(test);
            if(test.get(test.size() - 1) == 0)
            {
                test.remove(test.size()-1);
                for(int i = 0; i < test.size(); i++)
                {
                    if(test.get(i) != ctr)
                    {
                        return false;
                    }
                    ctr++;
                }
            }
            else
                return false;
        }
        return true;
    }

    private int getRow()
    {
        int row = 0;
        if(!hasWonOnce)
        {
            for(int r = 0; r < grid.length; r++)
            {
                for(int c = 0; c < grid[0].length; c++)
                {
                    if(grid[r][c] == 0)
                    {
                        row = r;
                    }
                }
            }
        }
        else
        {
            for(int r = 0; r < grid4By4.length; r++)
            {
                for(int c = 0; c < grid4By4[0].length; c++)
                {
                    if(grid4By4[r][c] == 0)
                    {
                        row = r;
                    }
                }
            }
        }
        return row;
    }// returns row and col of 0, checks out of bounds
    private int getCol()
    {
        int col = 0;
        if(!hasWonOnce)
        {
            for(int r = 0; r < grid.length; r++)
            {
                for(int c = 0; c < grid[0].length; c++)
                {
                    if(grid[r][c] == 0)
                    {
                        col = c;
                    }
                }
            }
        }
        else 
        {
            for(int r = 0; r < grid4By4.length; r++)
            {
                for(int c = 0; c < grid4By4[0].length; c++)
                {
                    if(grid4By4[r][c] == 0)
                    {
                        col = c;
                    }
                }
            }
        }
        return col;
    }

    private void drawNums()//
    //next time, set variables and adjust cooordinates so that they are random and not scattered in order
    {
        if(!hasWonOnce)
        {
            for(int r = 0; r < grid.length; r++)
            {
                for(int c = 0; c < grid[0].length; c++)
                {
                    if(grid[r][c] == 1)
                    {
                        batch.draw(nums.get(0), c * 100, 200 - r * 100, 100, 100);
                    }
                    if(grid[r][c] == 2)
                    {
                        batch.draw(nums.get(1), c * 100, 200 - r * 100, 100, 100);

                    }
                    if(grid[r][c] == 3)
                    {
                        batch.draw(nums.get(2), c * 100, 200 - r * 100, 100, 100);
                    }
                    if(grid[r][c] == 4)
                    {
                        batch.draw(nums.get(3),c * 100, 200 - r * 100, 100, 100);
                    }
                    if(grid[r][c] == 5)
                    {
                        batch.draw(nums.get(4), c * 100, 200 - r * 100, 100, 100);
                    }
                    if(grid[r][c] == 6)
                    {
                        batch.draw(nums.get(5),c * 100, 200 - r * 100, 100, 100);
                    }
                    if(grid[r][c] == 7)
                    {
                        batch.draw(nums.get(6), c * 100, 200 - r * 100, 100, 100);
                    }
                    if(grid[r][c] == 8)
                    {
                        batch.draw(nums.get(7), c * 100, 200 - r * 100, 100, 100);
                    }
                }
            }
        }
        else
        {
            for(int r = 0; r < grid4By4.length; r++)
            {
                for(int c = 0; c < grid4By4[0].length; c++)
                {
                    if(grid4By4[r][c] == 1)
                    {
                        batch.draw(nums.get(0), c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 2)
                    {
                        batch.draw(nums.get(1), c * 75, 225 - r * 75, 75, 75);

                    }
                    if(grid4By4[r][c] == 3)
                    {
                        batch.draw(nums.get(2), c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 4)
                    {
                        batch.draw(nums.get(3),c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 5)
                    {
                        batch.draw(nums.get(4), c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 6)
                    {
                        batch.draw(nums.get(5),c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 7)
                    {
                        batch.draw(nums.get(6), c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 8)
                    {
                        batch.draw(nums.get(7), c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 9)
                    {
                        batch.draw(nums.get(8), c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 10)
                    {
                        batch.draw(redNums.get(0), c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 11)
                    {
                        batch.draw(redNums.get(1), c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 12)
                    {
                        batch.draw(redNums.get(2), c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 13)
                    {
                        batch.draw(redNums.get(3), c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 14)
                    {
                        batch.draw(redNums.get(4), c * 75, 225 - r * 75, 75, 75);
                    }
                    if(grid4By4[r][c] == 15)
                    {
                        batch.draw(redNums.get(5), c * 75, 225 - r * 75, 75, 75);
                    }
                }
            }
        }

    }   

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}
