import java.awt.GridBagLayout;

import javax.swing.*;


public class GameParameters extends JFrame {
	private Kid kid;
	private Monster monster;
	private Player player;
	
	public GameParameters(Player p, Monster m, Kid k)
	{
		player = p;
		monster = m;
		kid = k;
		
		this.setSize(300, 100);
		this.setTitle("Game Controls");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.setVisible(true);
	}
}
