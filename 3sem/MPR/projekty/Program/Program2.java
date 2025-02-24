/*
 * Program2.java
 *
 * Created on 11 styczeñ 2005, 11:20
 */

package grafika3d;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 *
 * @author MariaN
 */
public class Program2 
    extends Frame{
    
    public static void main(String[] args){
        new Program2();
    }
    
    /** Creates a new instance of Program2 */
    public Program2() {
        setSize(640, 480);
        setVisible(true);
        
        addWindowListener(
            new WindowAdapter(){
                public void windowClosing(WindowEvent evt){
                    System.exit(0);
                }
            }
        );
    }
    
    private String str = "Bardzo smutny text :)";
    
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        
        Font font = new Font("Serif", Font.PLAIN, 24);
        FontRenderContext frc = g2d.getFontRenderContext();
        
        g2d.translate(40, 80);
        
        GlyphVector gv = font.createGlyphVector(frc, str);
        int length = gv.getNumGlyphs();
        for(int i = 0; i < length; i++){
            Point2D p = gv.getGlyphPosition(i);
            double kat = (double)i / (double)(length - 1)*Math.PI/4;
            AffineTransform at = AffineTransform.getTranslateInstance(
                p.getX(), p.getY()
            );
            at.rotate(kat);
            Shape glyph = gv.getGlyphOutline(i);
            Shape transfprmedGlyph = at.createTransformedShape(glyph);
            g2d.fill(transfprmedGlyph);
        }
        
    }
    
}
