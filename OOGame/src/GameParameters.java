import java.awt.GridBagLayout;

import javax.swing.*;


public class GameParameters extends JFrame {
	private Kid kid;
	private Monster monster;
	private Player player;
	private JFrame gpFrame;
	
	public GameParameters(Player p, Monster m, Kid k)
	{
		player = p;
		monster = m;
		kid = k;
		
		gpFrame = new JFrame();
		gpFrame.setSize(300, 100);
		gpFrame.setTitle("Game Controls");
		gpFrame.setLocationRelativeTo(null);
		gpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gpFrame.setLayout(new GridBagLayout());
		gpFrame.setVisible(true);
	}
}
