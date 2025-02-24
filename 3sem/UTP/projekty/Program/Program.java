/*
 * Program.java
 *
 * Created on 11 styczeñ 2005, 10:24
 */

package grafika3d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;

/**
 *
 * @author MariaN
 */
public class Program
    extends Frame
    implements Runnable{
    
    private MyPoint points[];
    private Point2D.Double points2d[];
    private CubicCurve2D curve;
    private Color colors[];
    
    public static void main(String[] args){
        new Program();
    }
    
    
    /** Creates a new instance of Program */
    public Program() {
        points = new MyPoint[4];
        colors = new Color[4];
        points2d = new Point2D.Double[4];
        
        colors[0] = new Color(255, 0, 0);
        colors[1] = new Color(0, 255, 0);
        colors[2] = new Color(0, 0, 255);
        colors[3] = new Color(255, 0, 255);
        
        for(int i = 0; i < points.length; i++){
            points[i] = new MyPoint();
            points2d[i] = new Point2D.Double();
            addComponentListener(points[i]);
        }
        
        curve = new CubicCurve2D.Float();
        
        setSize(200, 200);
        setVisible(true);
        
        addWindowListener(
                new WindowAdapter(){
                    public void windowClosing(WindowEvent evt){
                        System.exit(0);
                    }
                }
        );
        
        new Thread(this).start();
    }
    
    public void paint(Graphics g){
        for(int i = 0; i < points.length; i++){
            g.setColor(colors[i]);
            synchronized(points2d[i]){
                g.fillOval(
                    points[i].getX()-1,
                    points[i].getY()-1,
                    2, 2
                );
                points2d[i].x = points[i].getX();
                points2d[i].y = points[i].getY();
                
            }
        }
        Graphics2D g2d = (Graphics2D)g;
        curve.setCurve(points2d, 0);
        g2d.draw(curve);
    }
    
    public void run(){
        while(true){
            try{
                Thread.sleep(10);
                repaint();
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
    }
}


class MyPoint
        extends ComponentAdapter
        implements Runnable{
    
    private int x, y;
    
    private int sX, sY;
    private int speed;
    
    private int sizeX, sizeY;
    private int licznik;
    
    public MyPoint(){
        sX = getRandomVector();
        sY = getRandomVector();
        
        x = 50;
        y = 50;
        
        speed = 10;
        licznik = 0;
        new Thread(this).start();
    }
    
    public void componentResized(ComponentEvent e){
        Dimension dim = e.getComponent().getSize();
        sizeX = (int)dim.getWidth();
        sizeY = (int)dim.getHeight();
    }
    
    public void run(){
        while(true){
            move();
            if(x < 5 || x > sizeX - 5){
                sX *= -1;
                sY = getRandomVector();
                speed = 10 + (int)(Math.random()*20);
            }
            if(y < 25 || y > sizeY - 5){
                sY *= -1;
                sX = getRandomVector();
                speed = 10 + (int)(Math.random()*20);
            }
//            if(licznik % 75 == 0){
//                sX = getRandomVector();
//                sY = getRandomVector();
//            }
            try{
                Thread.sleep(speed);
                licznik++;
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
    public int getRandomVector(){
        if(Math.random() > 0.5)
            return -1;
        return 1;
    }
    
    public synchronized void move(){
        x += sX;
        y += sY;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
