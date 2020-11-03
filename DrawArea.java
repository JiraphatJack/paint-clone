package game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

public class DrawArea extends JComponent {

    private Image image;
    private Graphics2D g2;

    private int currentX, currentY, oldX, oldY;

    public DrawArea() {
        draw();
    }

    private void draw() {  
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                if (g2 != null) {
                    g2.drawLine(oldX, oldY, currentX, currentY);
                    repaint();
                    oldX = currentX;
                    oldY = currentY;
                }
            }
        });   
    }

    private void drawLine() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                if (g2 != null) {
                    g2.drawLine(oldX, oldY, currentX, currentY);
                    repaint();
                }
            }
        });
    }
    

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g. drawImage(image, 0, 0, null); 
    }

    public void clear() {
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }

    
    public void red() {
        // apply red color on g2 context
        g2.setPaint(Color.red);
    }
     
    public void black() {
        g2.setPaint(Color.black);
    }
     
    public void magenta() {
        g2.setPaint(Color.magenta);
    }
     
    public void green() {
        g2.setPaint(Color.green);
    }
     
    public void blue() {
        g2.setPaint(Color.blue);
    }

    public void yellow() {
        g2.setPaint(Color.yellow);
    }
}