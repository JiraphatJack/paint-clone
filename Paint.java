package game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class Paint {
    JButton clearBtn, saveBtn;
    DrawArea drawArea;

    // Button actionListener to save or clear the canvas
    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
                drawArea.clear();
            } else if (e.getSource() == saveBtn) {
                saveCanvas(drawArea);
            } 
        }
    };
    
    // Combobox to select the colot of the line
    ActionListener cbActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();

            String s = (String)cb.getSelectedItem();

            if (s.equals("Black")) {
                drawArea.black();
            } else if (s.equals("Blue")) {
                drawArea.blue();
            } else if (s.equals("Green")) {
                drawArea.green();
            } else if (s.equals("Red")) {
                drawArea.red();
            } else if (s.equals("Magenta")) {
                drawArea.magenta();
            } else if (s.equals("Yellow")) {
                drawArea.yellow();
            }
        }
    };

    public static void main(String[] args) {
        new Paint().show();
    }

    // Show the Application
    public void show() {
        // Create frame to display the application
        JFrame frame = new JFrame("Paint");
        Container content = frame.getContentPane();

        content.setLayout(new BorderLayout());

        // Create Draw area from DrawArea class
        drawArea = new DrawArea();

        // add draw area to the frame
        content.add(drawArea, BorderLayout.CENTER);

        // create conrtol panel object
        JPanel controls = new JPanel();

        // create color dropdown box
        String[] choices = {"Black", "Blue", "Green", "Red", "Magenta", "Yellow"};
        final JComboBox<String> cb = new JComboBox<String>(choices);

        // set action listener to button and dropdown box
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);
        cb.addActionListener(cbActionListener);
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(actionListener);

        // add button and dropdown box to control panel
        controls.add(clearBtn);
        controls.add(cb);
        controls.add(saveBtn);

        // add control panel to top of the frame
        content.add(controls, BorderLayout.NORTH);

        // set frame size to 600x600 pixels, set how to close app and make the app visible
        frame.setSize(1500, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Save your drawing as a png 
    public static void saveCanvas(DrawArea canvas) {

        // create bufferedImage the same dimension as the canvas
		BufferedImage image = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
		
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        
        // store file destination inside path
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);
        String path = f.getSelectedFile().toString();

        
        // save the image set the destination to the path
		canvas.paint(g2);
		try {
			ImageIO.write(image, "png", new File(path + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
