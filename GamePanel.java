import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class GamePanel extends JPanel {
	private GameState gameState;
	private BufferedImage bg;
	private MapPanel mapPanel;
	//private EndPanel endPanel;
	private PlayerPanel player1, player2, player3, player4;
	Color gold = new Color(241, 194, 50);
	Color darkGold = new Color(191, 144, 0);
	
	private JPanel top;
	private JPanel bottom;
	private JPanel left;
	private JPanel right;
	private JPanel inv;
	
	public GamePanel() {
		
		gameState = new GameState();
		
		setLayout(new BorderLayout());
		Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(gold, 4), "TICKET TO RIDE EUROPE", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 18), darkGold);
	        mapPanel = new MapPanel(this.gameState, this);
	        mapPanel.setBorder(border);
	        add(mapPanel, BorderLayout.CENTER);
	        
	    inv = new JPanel(new BorderLayout());  
        top = new JPanel(new BorderLayout());
        bottom = new JPanel(new BorderLayout());
        
        left = new JPanel(new BorderLayout());
        right = new JPanel(new BorderLayout());
        left.setOpaque(false);
        right.setOpaque(false);
       
        
        
        player1 = new PlayerPanel(Color.WHITE, gameState.getPlayer(0));
        player2 = new PlayerPanel(Color.RED, gameState.getPlayer(1));
        player3 = new PlayerPanel(Color.GREEN, gameState.getPlayer(2));
        player4 = new PlayerPanel(Color.BLUE, gameState.getPlayer(3));
        player1.setPreferredSize(new Dimension(300, 300));
        player2.setPreferredSize(new Dimension(300, 300));
        player3.setPreferredSize(new Dimension(300, 300));
        player4.setPreferredSize(new Dimension(300, 300));
        
        left.add(player1, BorderLayout.NORTH);
        right.add(player2, BorderLayout.NORTH);
        left.add(player3, BorderLayout.SOUTH);
        right.add(player4, BorderLayout.SOUTH);
        
        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);

        //set bg
        try {
            bg = ImageIO.read(new File("C:\\Users\\k1702352\\Downloads\\bg.png"));
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        
	}
	
	public void showInv(int player) {
		left.setVisible(false);
		right.setVisible(false);
		inv.add(player1.inventory());
		
		inv.setPreferredSize(new Dimension(600, 300));
		Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(gold, 4), "PLAYER INVENTORY", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman", Font.BOLD, 18), darkGold);
		inv.setBorder(border);
		add(inv, BorderLayout.EAST);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (bg != null) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
        
    }

}
