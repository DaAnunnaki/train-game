import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TTREFrame extends JFrame{
	//oh my sigma 
		private InvPanel invPanel;

		private boolean invView = false;
		private CardLayout cardLayout;
		private JPanel t;

		//private GameController gc;
		
		public TTREFrame(){
			setTitle("rage rage against the dying of light");
	        setSize(1600, 1000);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        cardLayout = new CardLayout();
	         t = new JPanel(cardLayout);
	        
	        StartPanel p = new StartPanel(this);
	        GamePanel g = new GamePanel();
	        t.add(p, "start");
	        t.add(g, "game");
	        add(t);
	       
	        setResizable(false); 
	        setVisible(true);
	    }
		
		public void showGame() {
			cardLayout.show(t, "game");
		}
		
		
}