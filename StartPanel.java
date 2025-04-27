import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

public class StartPanel extends JPanel implements MouseListener {
    private static final double r = 1; 
    private char stat; 
    private HashMap<Character, BufferedImage> images; 
    private BufferedImage bg;
    private TTREFrame f;
    
   

    public StartPanel(TTREFrame frame) {
        stat = 's';
        images = new HashMap<>();
        f = frame;
        try {
			bg = ImageIO.read(new File("C:\\Users\\k1702352\\Desktop\\treimages\\ermmmm.PNG"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        addMouseListener(this);
        loadImages();
    }


    private void loadImages() {
        try {
            images.put('s', ImageIO.read(new File("C:\\Users\\k1702352\\Desktop\\treimages\\ermmmm.PNG")));
            images.put('p', ImageIO.read(new File("C:\\Users\\k1702352\\Downloads\\startButton.png"))); //play button
            images.put('q', ImageIO.read(new File("C:\\Users\\k1702352\\Desktop\\treimages\\rulesButton.png"))); //question mark?
            images.put('1', ImageIO.read(new File("C:\\Users\\k1702352\\Desktop\\treimages\\Rules1.png")));
            images.put('2', ImageIO.read(new File("C:\\Users\\k1702352\\Desktop\\treimages\\Rules2.png")));
            images.put('3', ImageIO.read(new File("C:\\Users\\k1702352\\Desktop\\treimages\\Rules3.png")));
            images.put('c', ImageIO.read(new File("C:\\Users\\k1702352\\Desktop\\treimages\\exitButton.png")));
            images.put('n', ImageIO.read(new File("C:\\Users\\k1702352\\Desktop\\treimages\\nextButton.png")));
            images.put('b', ImageIO.read(new File("C:\\Users\\k1702352\\Desktop\\treimages\\backButton.png")));

        } catch (IOException e) {
            System.out.println("Error loading images: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        BufferedImage img = images.get(stat);

        
        if (stat == 's') {
        	if (img != null) {
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
            g.drawImage(images.get('p'), getWidth()/2-105, getHeight()-150, 235, 90, this);
            g.drawImage(images.get('q'), getWidth() - 80, 20, 60, 60, this); 
        }
        if (stat == '1' || stat == '2' || stat == '3') {
        	if (img != null) {
                g.drawImage(img, getWidth()/4, 0, getWidth()/2, getHeight(), this);
            }
            g.drawImage(images.get('c'), getWidth() - 40, 0, 40, 40, this); // Close button
        }
        if (stat == '1' || stat == '2') {
        	if (img != null) {
                g.drawImage(img, getWidth()/4, 0, getWidth()/2, getHeight(), this);
            }
            g.drawImage(images.get('n'), getWidth() - 100, getHeight() - 100, 70, 70, this); // Next button
        }
        if (stat == '2' || stat == '3') {
        	if (img != null) {
                g.drawImage(img, getWidth()/4, 0, getWidth()/2, getHeight(), this);
            }
            g.drawImage(images.get('b'), 30, getHeight() - 100, 70, 70, this); // Back button
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Clicked at: (" + x + ", " + y + ")");

        char previousStat = stat; 

        if (stat == 's') {
            if (isClicked(x, y, 695, 815, 915, 893)) {
            	f.showGame();
                setVisible(false);
            } else if (isClicked(x, y, getWidth() - 80, 20, getWidth() - 20, 80)) {
                stat = '1';
            }
            
        } else if (stat == '1') {
        	System.out.println(getWidth());
            if (isClicked(x, y, getWidth() - 40, 0, getWidth(), 40)) {
                stat = 's'; 
            } else if (isClicked(x, y, getWidth() - 100, getHeight() - 100, getWidth(), getHeight())) {
                stat = '2'; 
            }
        } else if (stat == '2') {
            if (isClicked(x, y, getWidth() - 40, 0, getWidth(), 40)) {
                stat = 's'; 
            } else if (isClicked(x, y, 30, getHeight() - 100, 100, getHeight())) {
                stat = '1'; 
            } else if (isClicked(x, y, getWidth() - 100, getHeight() - 100, getWidth(), getHeight())) {
                stat = '3'; 
            }
        } else if (stat == '3') {
            if (isClicked(x, y, getWidth() - 40, 0, getWidth(), 40)) {
                stat = 's'; 
            } else if (isClicked(x, y, 30, getHeight() - 100, 100, getHeight())) {
                stat = '2'; 
            }
        }

        if (stat != previousStat) {
            System.out.println("Switching to state: " + stat);
            repaint();
        }
    }

    private boolean isClicked(int x, int y, int x1, int y1, int x2, int y2) {
        return x > x1 && x < x2 && y > y1 && y < y2;
    }

    public char getStat() {
        return stat;
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}