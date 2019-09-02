import java.util.Scanner;
import java.util.Random;
import java.awt.*;
import javax.swing.*;
public class StalinSortV2 extends JFrame
{
    static Random random = new Random();
    private static final long serialVersionUID = 1L;
    static int width = 900;
    static int height = 900;
    static int[] array = new int[50];
    static String[] result = new String[array.length];
    static VisualizeStalinSort[] blocks = new VisualizeStalinSort[array.length];
    static VisualizeStalinSort[] blocksBeforeSort = new VisualizeStalinSort[array.length];
    static Color huehue;
    Image doubleBufferImg;
	Graphics doubleBufferGraphics;
    public static void cleanUp()
    {
        System.gc();
        for(int i = 0; i < blocks.length; i++)
        {
            blocks[i].setX(0);
            blocksBeforeSort[i].setX(0);
            blocks[i].setY(0);
            blocksBeforeSort[i].setY(0);
            blocks[i].setB(0);
            blocks[i].setG(0);
            blocks[i].setR(0);
            blocks[i].setIsVisible(false);
        }
    }
    public static void generate()
    {
        int x = 20;
        int y = 40;
        int counter = 0;
        for(int i = 0; i < array.length; i++)
        {
            array[i] = random.nextInt(888);
            result[i] = ""+array[i];
        }
        for(int i = 0; i < array.length; i++)
        {
            blocksBeforeSort[i].setShowNumber(array[i]);
            blocksBeforeSort[i].setWidth(50);
            blocksBeforeSort[i].setHeight(50);
            blocksBeforeSort[i].setG(255);
            blocksBeforeSort[i].setR(255);
            if(counter < 15)
            {
                blocksBeforeSort[i].setX(blocksBeforeSort[i].getX()+x);
                x = x+blocksBeforeSort[i].getWidth();
                blocksBeforeSort[i].setY(blocksBeforeSort[i].getHeight()+y);
                counter++;
            }
            else
            {
                x = 20;
                y = y+blocksBeforeSort[i].getHeight();
                counter = 0;
                blocksBeforeSort[i].setX(blocksBeforeSort[i].getX()+x);
                blocksBeforeSort[i].setY(blocksBeforeSort[i].getHeight()+y);
            }
        }
        x = 20;
        y = 40;
        counter = 0;
        for(int i = 0; i < array.length; i++)
        {
            blocks[i].setShowNumber(array[i]);
            blocks[i].setWidth(50);
            blocks[i].setHeight(50);
            if(counter < 15)
            {
                blocks[i].setX(blocks[i].getX()+x);
                x = x+blocks[i].getWidth();
                blocks[i].setY(blocks[i].getHeight()+y);
                counter++;
            }
            else
            {
                x = 20;
                y = y+blocks[i].getHeight();
                counter = 0;
                blocks[i].setX(blocks[i].getX()+x);
                blocks[i].setY(blocks[i].getHeight()+y);
            }
        }
        blocks[0].setG(255);
        blocks[0].setR(0);
        blocks[0].setB(0);
    }
    public static void stalinMethod()
    {
        int compare = array[0];
        for(int i = 0; i < array.length; i++)
        {
            if(i+1 < array.length)
            {
                if(compare > array[i+1])
                {
                    array[i+1] = -1;
                    result[i+1] = "ELIMINATED";
                }
                else
                {
                    compare = array[i+1];
                }
            }
        }
        System.out.println("-----------SORTING DONE----------------");
        for(int i = 0; i < array.length; i++)
        {
            System.out.println(result[i]);
            if(result[i].equals("ELIMINATED"))
            {
                blocks[i].setR(255);
            }
            else
            {
                blocks[i].setG(255);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException
    {
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < array.length; i++)
        {
            blocks[i] = new VisualizeStalinSort();
            blocksBeforeSort[i] = new VisualizeStalinSort();
            System.out.println(array[i]);
        }
        generate();
        stalinMethod();
        StalinSortV2 gui = new StalinSortV2();
        gui.setSize(width,height);
        gui.setVisible(true);
        gui.setResizable(false);
        gui.setTitle("Stalin Sort :D"); 
        gui.repaint();
        while(!(scan.next().equalsIgnoreCase("exit")))
        {
            cleanUp();
            System.out.println("To leave type 'exit', otherwise type anything to generate again.");
            generate();
            stalinMethod();
            for(int i = 0; i < blocks.length; i++)
            {
                blocks[i].setIsVisible(true);
                Thread.sleep(100);
                gui.repaint();
            }
        }
        System.exit(1);
    }
    public void paint(Graphics g)
    {
        doubleBufferImg = createImage(width, height); //creates an image of the current screen
		doubleBufferGraphics = doubleBufferImg.getGraphics(); //gets the graphics of the current screen
		paintComponent(doubleBufferGraphics); //takes the graphics
		g.drawImage(doubleBufferImg,0,0,this); //draws them to the screen (i don't completely understand this either)
    }
    public void paintComponent(Graphics g)
    {
        huehue = new Color(222,222,222);
        g.setColor(huehue);
        g.fillRect(0,0,width,height);
        for(int i = 0; i < blocksBeforeSort.length; i++)
        {
            huehue = null;
            huehue = new Color(blocksBeforeSort[i].getR(),blocksBeforeSort[i].getG(),blocksBeforeSort[i].getB());
            g.setColor(huehue);
            g.fillRect(blocksBeforeSort[i].getX(),blocksBeforeSort[i].getY(),blocksBeforeSort[i].getWidth(),blocksBeforeSort[i].getHeight());
            huehue = null;
            huehue = new Color(0,0,0);
            g.setColor(huehue);
            g.drawRect(blocksBeforeSort[i].getX(),blocksBeforeSort[i].getY(),blocksBeforeSort[i].getWidth(),blocksBeforeSort[i].getHeight());
            g.drawString(""+blocksBeforeSort[i].getShowNumber(),blocksBeforeSort[i].getX()+(int)(blocksBeforeSort[i].getWidth()/2),blocksBeforeSort[i].getY()+(int)(blocksBeforeSort[i].getHeight()/2));
        }
        for(int i = 0; i < blocks.length; i++)
        {
            if(blocks[i].getIsVisible() == true)
            {
                huehue = null;
                huehue = new Color(blocks[i].getR(),blocks[i].getG(),blocks[i].getB());
                g.setColor(huehue);
                g.fillRect(blocks[i].getX(),blocks[i].getY(),blocks[i].getWidth(),blocks[i].getHeight());
                huehue = null;
                huehue = new Color(0,0,0);
                g.setColor(huehue);
                g.drawRect(blocks[i].getX(),blocks[i].getY(),blocks[i].getWidth(),blocks[i].getHeight());
                if(blocks[i].getG() == 255)
                {
                    g.drawString(""+blocks[i].getShowNumber(),blocks[i].getX()+(int)(blocks[i].getWidth()/2),blocks[i].getY()+(int)(blocks[i].getHeight()/2));
                }
                else
                {
                    g.drawLine(blocks[i].getX(),blocks[i].getY(),blocks[i].getX()+blocks[i].getWidth(),blocks[i].getY()+blocks[i].getHeight());
                    g.drawLine(blocks[i].getX()+blocks[i].getWidth(),blocks[i].getY(),blocks[i].getX(),blocks[i].getY()+blocks[i].getHeight());
                }
            }
        }
    }
}
class VisualizeStalinSort
{
    int width = 0;
    int height = 0;
    int x = 0; 
    int y = 0;
    int r = 0;
    int g = 0;
    int b = 0;
    int showNumber = 0;
    boolean isVisible = false;
    boolean getIsVisible()
    {
        return isVisible;
    }
    void setIsVisible(boolean isVisible)
    {
        this.isVisible = isVisible;
    }
    int getShowNumber()
    {
        return showNumber;
    }
    void setShowNumber(int showNumber)
    {
        this.showNumber = showNumber;
    }
    int getWidth()
    {
        return width;
    }
    void setWidth(int width)
    {
        this.width = width;
    }
    int getHeight()
    {
        return height;
    }
    void setHeight(int height)
    {
        this.height = height;
    }
    int getX()
    {
        return x;
    }
    void setX(int x)
    {
        this.x = x;
    }
    int getY()
    {
        return y;
    }
    void setY(int y)
    {
        this.y = y;
    }
    int getR()
    {
        return r;
    }
    void setR(int r)
    {
        this.r = r;
    }
    int getG()
    {
        return g;
    }
    void setG(int g)
    {
        this.g = g;
    }
    int getB()
    {
        return b;
    }
    void setB(int b)
    {
        this.b = b;
    }
}