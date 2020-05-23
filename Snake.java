import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class Snake extends JPanel implements KeyListener,ActionListener
{
private ImageIcon titleImage;
private int[] snakexlength=new int [750];
private int[] snakeylength=new int [750];

private int lengthofsnake=3;

private int moves=0;

private boolean left=false;
private boolean right=false;
private boolean up=false;
private boolean down=false;

private ImageIcon rightmouth;
private ImageIcon leftmouth;
private ImageIcon upmouth;
private ImageIcon downmouth;

private Timer timer;
private int delay=100;

private int scores=0;
private ImageIcon snakeImage;

private int[] enemyxposition={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650};

private int[] enemyyposition={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650};

private ImageIcon enemyimage;

private Random random=new Random();

private int xpos=random.nextInt(34);
private int ypos=random.nextInt(23);
    public Snake()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g)
    {   
        if(moves==0)
        {
            snakexlength[2]=50;
            snakexlength[1]=75;
            snakexlength[0]=100;

            snakeylength[2]=100;
            snakeylength[1]=100;
            snakeylength[0]=100;


        }
        
        //draw image border

        g.setColor(Color.white);
        g.drawRect(24,10,851,55);

        //draw title image
        titleImage=new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);

        //draw border for playing
        g.setColor(Color.white);
        g.drawRect(24,74,851,577);

        //draw background for the gameplay
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);


        //draw scores
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Scores"+scores,780,30);

        //drawlength
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Length of snake"+lengthofsnake,750,50);



        rightmouth=new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlength[0],snakeylength[0]);


        for(int a=0;a<lengthofsnake;a++)
        {
            if(a==0 && right)
            {
                rightmouth=new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g, snakexlength[a],snakeylength[a]);
        
            }
            if(a==0 && left)
            {
                leftmouth=new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlength[a],snakeylength[a]);
        
            }
            if(a==0 && up)
            {
                upmouth=new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g, snakexlength[a],snakeylength[a]);
        
            }
            if(a==0 && down)
            {
                downmouth=new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g, snakexlength[a],snakeylength[a]);
        
            }
            if(a!=0)
            {
                snakeImage=new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this, g, snakexlength[a],snakeylength[a]);
            }
        }


        enemyimage=new ImageIcon("enemy.png");
        enemyimage.paintIcon(this, g, enemyxposition[xpos], enemyyposition[ypos]);
        if((enemyxposition[xpos]==snakexlength[0])&&(enemyyposition[ypos]==snakeylength[0]))
        {
            scores++;
            lengthofsnake++;
            xpos=random.nextInt(34);
            ypos=random.nextInt(23);
        }

        
        g.dispose();
    }
    public void keyPressed(KeyEvent ke)
    {
        if(ke.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            moves++;
            right=true;
            if(!left)
            {
                right=true;
            }
       
            else
            {
            left=true;
            right=false;
            }
            up=false;
            down=false;
        }

        if(ke.getKeyCode()==KeyEvent.VK_LEFT)
        {
            moves++;
            left=true;
            if(!right)
            {
                left=true;
            }
            else
            {
            right=true;
            left=false;
            }
            up=false;
            down=false;
        }

        if(ke.getKeyCode()==KeyEvent.VK_UP)
        {
            moves++;
            up=true;
            if(!down)
            {
                up=true;
            }
            else
            {
            up=false;
            down=true;
            }
            left=false;
            right=false;
        }

        if(ke.getKeyCode()==KeyEvent.VK_DOWN)
        {
            moves++;
            down=true;
            if(!up)
            {
                down=true;
            }
            else
            {
            down=false;
            up=true;
            }
            left=false;
            right=false;
        }
    }

    public void keyReleased(KeyEvent ke)
    {

    }

    public void keyTyped(KeyEvent ke)
    {

    }

    public void actionPerformed(ActionEvent ae)
    {
        timer.start();
        if(right)
        {
            for(int r=lengthofsnake-1;r>=0;r--)
            {
                snakeylength[r+1]=snakeylength[r];
            }

            for(int r=lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakexlength[r]=snakexlength[r]+25;
                }else
                {
                    snakexlength[r]=snakexlength[r-1];
                }
                if(snakexlength[r]>850)
                {
                    snakexlength[r]=25;

                }
            }
            repaint();
        }
        if(left)
        {
            for(int r=lengthofsnake-1;r>=0;r--)
            {
                snakeylength[r+1]=snakeylength[r];
            }

            for(int r=lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakexlength[r]=snakexlength[r]-25;
                }else
                {
                    snakexlength[r]=snakexlength[r-1];
                }
                if(snakexlength[r]<25)
                {
                    snakexlength[r]=850;

                }
            }
            repaint();
        }
        if(down)
        {
            for(int r=lengthofsnake-1;r>=0;r--)
            {
                snakexlength[r+1]=snakexlength[r];
            }

            for(int r=lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakeylength[r]=snakeylength[r]+25;
                }else
                {
                    snakeylength[r]=snakeylength[r-1];
                }
                if(snakeylength[r]>625)
                {
                    snakeylength[r]=75;

                }
            }
            repaint();
            
        }
        if(up)
        {
            for(int r=lengthofsnake-1;r>=0;r--)
            {
                snakexlength[r+1]=snakexlength[r];
            }

            for(int r=lengthofsnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakeylength[r]=snakeylength[r]-25;
                }else
                {
                    snakeylength[r]=snakeylength[r-1];
                }
                if(snakeylength[r]<75)
                {
                    snakeylength[r]=625;

                }
            }
            repaint();
        }
    }



}