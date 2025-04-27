import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MapPanel extends JPanel implements MouseListener {
		
	private BufferedImage backgroundImage;
	private JButton discardBtn, getDestCardBtn,playerViewBtn;
	private BufferedImage trainCardBack;
	private BufferedImage endTurn;
	private BufferedImage pv;
	private BufferedImage back;
	private GameState gm;
	private GamePanel uh;
	
	   public MapPanel(GameState g, GamePanel p) {
		   try {
			trainCardBack = ImageIO.read(new File("C:\\Users\\k1702352\\Downloads\\TCARDBACK.png"));
			endTurn = ImageIO.read(new File("C:\\Users\\k1702352\\Downloads\\oh-removebg-preview.png"));
			backgroundImage = ImageIO.read(new File("C:\\Users\\k1702352\\Downloads\\map.png"));
			pv = ImageIO.read(new File("C:\\Users\\k1702352\\Downloads\\playerview-removebg-preview.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	        
	        gm = g;
	        uh = p;
	        addMouseListener(this);
			setOpaque(false);
	    }
	   
	   
	   
	   @Override
	   protected void paintComponent(Graphics g) {
		   super.paintComponent(g);
	       
	       //original tcardback is 220 by 140
	       g.drawImage(backgroundImage, 5, getHeight()/6-10, 975, 660, null);
	       g.drawImage(trainCardBack, 5, 10, 170, 100, null);
	       g.drawImage(endTurn, 35, 850, endTurn.getWidth()-50, endTurn.getHeight()-15, null);
	       g.drawImage(pv, 685, 850, endTurn.getWidth()-40, endTurn.getHeight()-15, null);
	       //g.drawRect(55, 850, 100, 50);
	       
	       g.setColor(Color.WHITE);
	       	g.setFont(new Font("Times New Roman", Font.BOLD, 40));
	        g.drawString("PLAYER "+gm.getCurrentPlayer()+"'S TURN", 276, 890);
	       
	   }
	   
	   @Override
	   public void mouseClicked(MouseEvent e) {
		   int x = e.getX();
	       int y = e.getY();
	       System.out.println("Clicked at: (" + x + ", " + y + ")");
	       
	       if(isClicked(x, y, 40, 850, 233, 900)) {
	    	   gm.incCurrentPlayer();
	    	   System.out.println(gm.getCurrentPlayer());
	    	   repaint();
	       }
	       
	       if(isClicked(x, y, 686, 850, 895, 900)) {
	    	   uh.showInv(gm.getCurrentPlayer());
	       }
	       
	       
	   }
	   
	   private boolean isClicked(int x, int y, int x1, int y1, int x2, int y2) {
	        return x > x1 && x < x2 && y > y1 && y < y2;
	    }



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
