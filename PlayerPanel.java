import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel {
	private Player player;
	private int traincount;
	private int stationcount;
	private Color c;
	private InvPanel inventory;
	Color darkGold = new Color(191, 144, 0);
	
	
	public PlayerPanel(Color color, Player p) {
		c = color;
		player = p;
		traincount = p.getTrainsCount();
		stationcount = p.getStationsCount();
		inventory = new InvPanel(this.player, c);
		setOpaque(false);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(c);
		g.setFont(new Font("Times New Roman", Font.BOLD, 38));
		g.drawString("Player "+player.getName(), 15, 40);
		
		g.setColor(darkGold);
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		g.drawString("Train Count: "+traincount, 15, 70);
		g.drawString("Station Count: "+stationcount, 15, 90);
		
		repaint();
		
	}
	
	public InvPanel inventory() {
		return inventory;
	}
	
	public Player returnPlayer() {
		return player;
	}

}
