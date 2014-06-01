import javax.swing.*;

import java.awt.*;

/* This class is the main System level class which creates all the objects 
 * representing the game logic (model) and the panel for user interaction. 
 * It also implements the main game loop */

public class Game extends JFrame 
{
	static int TIMEALLOWED = 100;
	public static boolean loggedIn = false;
	public static boolean loginOpen = false;
	public static boolean loginKilled = false;
	public static boolean alreadyFrozen = false;
	private JButton start = new JButton("start");
	private JButton restart = new JButton("restart");
	private JButton pause = new JButton("pause");
	private JButton save = new JButton("save");
	static JLabel mLabel = new JLabel("Time Remaining : " + TIMEALLOWED);
	private Grid grid;
	protected Player player;
	protected Monster monster;
	protected Nuggets sprinkles;
	protected Trap trap;
	protected Kid kid;
	protected GameParameters gp;
	private BoardPanel bp;
	
	protected boolean kidIsActive;
	protected boolean trapIsActive;
	protected boolean nuggetsIsActive;
	
	public static JLabel jWarning = new JLabel("Energy Levels: " + Player.getCurrentEnergy());

	/* This constructor creates the main model objects and the panel used for
	 * UI. It throws an exception if an attempt is made to place the player or
	 * the monster in an invalid location. */

	public Game() throws Exception 
	{
		grid = new Grid();
		player = new Player(grid, 0, 0);
		monster = new Monster(grid, player, 5, 5);
		
		kid = new Kid(grid, player, 5, 6);
		sprinkles = new Nuggets(grid, player);
		trap = new Trap(grid, player, monster);
		bp = new BoardPanel(grid, player, monster, kid, sprinkles, trap);
		
		
		//bp = new BoardPanel(grid, player, monster, kid, sprinkles);
		// Create a separate panel and add all the buttons
		JPanel panel = new JPanel();
		panel.add(jWarning);
		panel.add(start);
		panel.add(restart);
		panel.add(pause);
		panel.add(save);
		panel.add(mLabel);
		
		System.out.println("Game construtor loaded");
		
		// add Action listeners to all button event
		start.addActionListener(bp);
		start.addKeyListener(bp);
		restart.addActionListener(bp);
		pause.addActionListener(bp);
		save.addActionListener(bp);

		// add panels to frame
		add(bp, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        System.exit(0);
		    }
		});
		
		gp = new GameParameters(player, monster, kid, sprinkles, trap);
	}

	// method to delay by specified time in ms
	public void delay(int time)
	{
		try 
		{
			Thread.sleep(time);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/* This method waits until play is ready (until start button is pressed)
	 * after which it updates the moves in turn until time runs out (player won)
	 * or player is eaten up (player lost). */

	public String play() throws Exception 
	{
		
		int time = 0;
		String message;
		System.out.println("Play was called.");
		System.out.println("Traps is active: " + trapIsActive);
		System.out.println("Kid is active: " + kidIsActive);
		System.out.println("Sprinkles is active: " + nuggetsIsActive);
		player.setDirection(' '); // set to no direction
		while(!player.isReady())
		{
			if (!loggedIn&&!loginOpen)
			{
				new Authentication();
				loginOpen = true;
			}
			else if (loginKilled)
			{
				this.dispose();
				System.exit(0); 
			}
			delay(100);
		}
		do
		{
			if(player.isReady())
			{
				Cell newPlayerCell = player.move();

				kidIsActive = kid.active();
				trapIsActive = trap.active();
				nuggetsIsActive = sprinkles.active();
				System.out.println("Traps: " + trapIsActive);
				
				
				if (newPlayerCell == monster.getCell())
				{
					player.rest();
					jWarning.setText("Cupcake was eaten!");
				}
				
				if (newPlayerCell == kid.getCell() && kidIsActive)
				{
					System.out.println("Kid was killed");
					kid.setViewable(false);
				}
				
				if (newPlayerCell == sprinkles.getCell() && nuggetsIsActive)
				{
					System.out.println("====Sprinkles====");
					sprinkles.move();
					player.Addenergy();
					jWarning.setText("Energy Levels: " + player.getCurrentEnergy());
					//sprinkles.setVisible(false);
				}
				
				player.setDirection(' '); // reset to no direction
				Cell newMonsterCell = monster.move();
				
				if (newMonsterCell == player.getCell())
				{
					System.out.println("Monster hit player");
					player.rest();
					jWarning.setText("Cupcake was eaten!");
				}
				
				if (newMonsterCell == trap.getCell() && !alreadyFrozen && trapIsActive)
				{
					monster.setFreeze(time);
					alreadyFrozen = true;
				}

				int refreshNugget = 5;
				
				if (time >= refreshNugget && nuggetsIsActive)
				{
					System.out.println("---------Sprinkles--------");
					sprinkles.setVisible(true);
				} 
				
				Cell newKidCell = kid.move();
				
				if (newKidCell == player.getCell() && kid.canView == true)
				{
					System.out.println("Kid hit player");
					kid.setViewable(false);

				}
				// update time and repaint
				time++;
				monster.setTime(time);
				mLabel.setText("Time Remaining : " + (TIMEALLOWED - time));
				delay(200);
				bp.repaint();
				
			}
			else delay(100);
		}
		while (time < TIMEALLOWED);
		{
			if (time < TIMEALLOWED) // players has been eaten up
			{
				message = "Cupcake was eaten";
			}
			else
			{
				message = "Cupcake survived!";
				
			}
			mLabel.setText(message);
		return message;
		}
	}

	public static void main(String args[]) throws Exception
	{
		Game game = new Game();
		game.setTitle("Cupcake Survival");
		game.setSize(1000, 600);
		game.setLocationRelativeTo(null); // center the frame
		game.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		game.setVisible(true);
		game.play();
		
		
	}
	
//	public void callGame() throws Exception{
//		Game game = new Game();
//		game.setTitle("Cupcake Survival");
//		game.setSize(1000, 600);
//		game.setLocationRelativeTo(null); // center the frame
//		game.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		game.setVisible(true);
//		game.play();
//		player.setReady(false);
//	}
	
}