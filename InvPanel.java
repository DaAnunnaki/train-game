import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class InvPanel extends JPanel implements MouseListener{
	private String name;
	private Player p;
	private int traincount;
	private int stationcount;
	private Color c;
	
	public InvPanel(Player p, Color c) {
		this.p = p;
		name = p.getName();
		traincount = p.getTrainsCount();
		stationcount = p.getStationsCount();
		this.c = c;
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 
		 g.setFont(new Font("Times New Roman", Font.BOLD, 38));
			g.drawString("Player "+name, 15, 40);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
