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
	protected static Trap trap;
	protected static JFrame gpFrame;
	
	public GameParameters(Player p, Monster m, Kid k, Nuggets n, Trap t)
	{
		player = p;
		monster = m;
		kid = k;
		sprinkles = n;
		trap = t;
		
		
		
		gpFrame = new JFrame();
		gpFrame.setSize(500, 305);
		gpFrame.setTitle("Game Controls");
		gpFrame.setLocationRelativeTo(null);
		gpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			
			String[] monOptions 	= 	{"Normal Fatty","Hidden Fatty","Productive Fatty","Both"}; // Normal, Hidden Monster or Productive Monster
			String[] playOptions	=	{"Normal Cupcake","Cupcake with Sprinkles","Cupcake with Traps","Both"}; // Normal, Player Extension #1 and Player Extension #3
			String[] speedLevel		=	{"1 - Fastest", "2 - Faster", "3 - Average", "4 - Slower", "5 - Slowest"};
			String[] timeLevel		=	{"150s", "120s", "90s", "60s", "30s"};
			
			final JComboBox monList 		= 	new JComboBox(monOptions);
			final JComboBox playerList 		= 	new JComboBox(playOptions);
			final JComboBox speedList		=	new JComboBox(speedLevel);
			final JComboBox timeList		=	new JComboBox(timeLevel);
			
			JLabel header			=	new JLabel("<html><b><font color='white'>Please select Game Parameters: </font></b></hmtl><br>");
			JLabel monsterType 		= 	new JLabel("Fatty Type:  ");
			JLabel playerType		=	new JLabel("Cupcake Type:  ");
			JLabel speed			=	new JLabel("Speed:  ");
			JLabel time				=	new JLabel("Time:  ");
			
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
			add(speed, c); 
			c.gridx = 2;
			add(speedList, c);
			
			c.gridx = 1;
			c.gridy = 5;
			add(time, c); 
			c.gridx = 2;
			add(timeList, c);
			
			c.gridx = 1;
			c.gridy = 6;
			add(new JLabel("\n"), c);
			
			c.gridx = 1;
			c.gridy = 7;
			c.gridwidth = 2;
			add(selectButton, c);
			
			selectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					String monSelected 	= (String) monList.getSelectedItem();
					String plySelected 	= (String) playerList.getSelectedItem();
					String speed		= (String) speedList.getSelectedItem();
					String time			= (String) timeList.getSelectedItem();
					
					if (monSelected.equals("Hidden Fatty"))
					{
						GameParameters.monster.setViewable(true);
						GameParameters.gpFrame.dispose();
						System.out.println("Hidden Fatty");
					}
					else if (monSelected.equals("Productive Fatty"))
					{
						GameParameters.kid.setActive(true);
						GameParameters.gpFrame.dispose();
						System.out.println("Productive Fatty");
					}
					else if (monSelected.equals("Both"))
					{
						GameParameters.kid.setActive(true);
						GameParameters.monster.setViewable(true);
						GameParameters.gpFrame.dispose();
						System.out.println("Both");
					}
					else
						System.out.println("Normal Fatty");
					
					if (plySelected.equals("Cupcake with Sprinkles"))
					{
						GameParameters.sprinkles.setActive(true);
						GameParameters.gpFrame.dispose();
						System.out.println("Cupcake with Sprinkles");
					}
					else if (plySelected.equals("Cupcake with Traps"))
					{
						GameParameters.trap.setActive(true);
						GameParameters.gpFrame.dispose();
						System.out.println("Cupcake with Traps");
					}
					else if (monSelected.equals("Both"))
					{
						GameParameters.trap.setActive(true);
						GameParameters.sprinkles.setActive(true);
						GameParameters.gpFrame.dispose();
						System.out.println("Both");
					}
					else
						System.out.println("Normal Cupcake");
					
					if (speed.equals("1 - Fastest"))
						Game.setSpeed(200);
					else if (speed.equals("2 - Faster"))
						Game.setSpeed(400);
					else if (speed.equals("3 - Average"))
						Game.setSpeed(600);
					else if (speed.equals("4 - Slower"))
						Game.setSpeed(800);
					else if (speed.equals("5 - Slowest"))
						Game.setSpeed(1000);
					
					if (time.equals("150s"))
						Game.setTime(150);
					else if (time.equals("120s"))
						Game.setTime(120);
					else if (time.equals("90s"))
						Game.setTime(90);
					else if (time.equals("60s"))
						Game.setTime(60);
					else if (time.equals("30s"))
						Game.setTime(30);
					
					GameParameters.gpFrame.dispose();
					//sprinkles.setActive(false);
				}
		});
			
			GameParameters.gpFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	
			    	GameParameters.monster.setViewable(false);
			    	GameParameters.sprinkles.setActive(false);
			    	GameParameters.kid.setActive(false);
			    	GameParameters.trap.setActive(false);
			    	
			        
			    }
			});
		}
}

