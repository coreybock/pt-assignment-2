import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GameParameters extends JFrame {
	protected static Kid kid;
	protected static Monster monster;
	protected static Player player;
	protected static Nuggets sprinkles;
	protected JFrame gpFrame;
	
	public GameParameters(Player p, Monster m, Kid k, Nuggets n)
	{
		player = p;
		monster = m;
		kid = k;
		sprinkles = n;
		
		
		gpFrame = new JFrame();
		gpFrame.setSize(500, 200);
		gpFrame.setTitle("Game Controls");
		gpFrame.setLocationRelativeTo(null);
		gpFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		gpFrame.setAlwaysOnTop (true);
		gpFrame.setLayout(new GridBagLayout());
		gpFrame.setVisible(true);
		
		JPanel backgroundPanel = new backgroundPanel();
		backgroundPanel.setLayout(new BorderLayout());
		backgroundPanel.add(new selectComponents(), BorderLayout.CENTER);
		
		gpFrame.setContentPane(backgroundPanel);
		gpFrame.setVisible(true);
	}
}

class backgroundPanel extends JPanel
{
	Image bg = new ImageIcon("src/resources/bgGP.png").getImage();
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}

class selectComponents extends JPanel{
	selectComponents()
		{
			super();
			setOpaque(false);
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
	        c.fill = GridBagConstraints.HORIZONTAL;
			
			String[] monOptions 	= 	{"Normal Fatty","Hidden Fatty","Productive Fatty"}; // Normal, Hidden Monster or Productive Monster
			String[] playOptions	=	{"Normal Cupcake","Cupcake with Sprinkles","Cupcake with Traps"}; // Normal, Player Extension #1 and Player Extension #3
			
			final JComboBox monList 		= 	new JComboBox(monOptions);
			final JComboBox playerList 		= 	new JComboBox(playOptions);
			
			JLabel header			=	new JLabel("<html><b><font color='white'>Please select Game Parameters: </font></b></hmtl><br>");
			JLabel monsterType 		= 	new JLabel("Fatty Type:  ");
			JLabel playerType		=	new JLabel("Cupcake Type:  ");
			
			JButton	selectButton	=	new JButton("Select!");
			
			header.setFont(new Font("Serif", Font.PLAIN, 20));
			
			c.gridx = 1;
			c.gridy = 0;
			c.gridwidth = 2;
			add(header, c);

			c.gridy = 1;
			add(new JLabel("\n"), c);
			
			c.gridwidth = 1;
			c.gridy = 2;
			add(monsterType, c); 
			c.gridx = 2;
			add(monList, c);
			
			c.gridx = 1;
			c.gridy = 3;
			add(playerType, c); 
			c.gridx = 2;
			add(playerList, c);
			
			c.gridx = 1;
			c.gridy = 4;
			add(new JLabel("\n"), c);
			
			c.gridx = 1;
			c.gridy = 5;
			c.gridwidth = 2;
			add(selectButton, c);
			
			selectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					String monSelected = (String) monList.getSelectedItem();
					String plySelected = (String) playerList.getSelectedItem();
					
					if (monSelected.equals("Hidden Fatty"))
					{
						GameParameters.monster.setViewable(true);
						System.out.println("Hidden Fatty");
					}
					else if (monSelected.equals("Productive Fatty"))
					{
						GameParameters.kid.setViewable(true);
						System.out.println("Productive Fatty");
					}
					else
						System.out.println("Normal Fatty");
					
					if (plySelected.equals("Cupcake with Sprinkles"))
					{
						GameParameters.monster.setViewable(true);
						System.out.println("Cupcake with Sprinkles");
					}
					else if (plySelected.equals("Cupcake with Traps"))
					{
						GameParameters.kid.setViewable(true);
						System.out.println("Productive Fatty");
					}
					
				}
		});
		}
}

