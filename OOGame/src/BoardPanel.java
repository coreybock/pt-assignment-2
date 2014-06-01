import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;

import javax.imageio.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/* This panel represents the game board (grid) 
 * It also responds to game related events
 * The overridden paint component() is called whenever the board
 * or the pieces needs to be updated */

public class BoardPanel extends JPanel implements ActionListener, KeyListener 
{
	private Player player;
	private Monster monster;
	private Kid kid;
	protected static Grid grid;
	private Image bg;
	private Image cnrImage;
	private ImageIcon icon, cnrIcon;
	private Graphics gr;
	private Game game;
	private Nuggets nugget;
	private Trap trap;
//	private saveGame save;
	private final int CELLWIDTH = 40;
	private final int CELLHEIGHT = 40;
	private final int LMARGIN = 275;
	private final int TMARGIN = 50;
	protected boolean multiple; 

	public BoardPanel(Grid g, Player p, Monster m, Kid k, Nuggets n, Trap t) throws Exception
	{
		icon = new ImageIcon("src/resources/bgColor.jpg");
	    bg = icon.getImage();
        
	    nugget = n;
	    kid = k;
		player = p;
		grid = g;
		monster = m;
		trap = t;
		gr = this.getGraphics();
		
//		save = new saveGame(player, monster);
//		save.read();
	}

	// returns the x coordinate based on left margin and cell width
	private int xCor(int col) 
	{
		return LMARGIN + col * CELLWIDTH;
	}

	// returns the y coordinate based on top margin and cell height
	private int yCor(int row) 
	{
		return TMARGIN + row * CELLHEIGHT;
	}

	// Redraws the board and the pieces Called initially and in response to repaint()
	protected void paintComponent(Graphics gr) 
	{
		super.paintComponent(gr);
		Graphics2D g2d = (Graphics2D) gr;
        g2d.drawImage(bg, 0, 0, null); 
		Cell cells[] = grid.getAllCells();
		Cell cell;
		for (int i = 0; i < cells.length; i++) 
		{
			cell = cells[i];
			if (cell.col % 5 == 0 && cell.row % 5 == 0)
			{
				gr.setColor(Color.pink);
				gr.fillRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
				gr.setColor(Color.black);
				gr.drawRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
			}
			else
			{
				gr.setColor(Color.blue);
				gr.fillRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
				gr.setColor(Color.black);
				gr.drawRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
			}

		}

		cell = player.getCell();

		ImageIcon imageCake = new ImageIcon("src/resources/cupcake.png");
		Image Cake = imageCake.getImage();
		gr.drawImage(Cake, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);


		if (monster.viewable()) 
		{
			cell = monster.getCell();
			ImageIcon imageMonster = new ImageIcon("src/resources/fatty.png");
			Image Monster = imageMonster.getImage();
			gr.drawImage(Monster, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);
		}
		
		if (kid.viewable())
		{
			cell = kid.getCell();
			ImageIcon imageFatKid = new ImageIcon("src/resources/fatkid.png");
			Image FatKid = imageFatKid.getImage();
			gr.drawImage(FatKid, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);
		}
		
		if (nugget.visible())
		{
				Cell nuggetCell = nugget.getCell();
				ImageIcon sprinklesimg = new ImageIcon("src/resources/sprinkles.png");
				Image Sprinkles = sprinklesimg.getImage();
				gr.drawImage(Sprinkles, xCor(nuggetCell.col), yCor(nuggetCell.row), CELLWIDTH, CELLHEIGHT, game);	
		}
		
		if (trap.visible())
		{
			Cell trapCell = trap.getCell();
			System.out.println(trapCell);
			ImageIcon trapimg = new ImageIcon("src/resources/trap.png");
			Image Trap = trapimg.getImage();
			gr.drawImage(Trap, xCor(trapCell.col), yCor(trapCell.row), CELLWIDTH, CELLHEIGHT, game);
		}
	}
		public void resetGame() throws Exception{
			player.rest();
			
			Cell newPlayerCell;
			Cell newMonsterCell;
			
			newPlayerCell = grid.getCell(0, 0);
			player.setCell(newPlayerCell);
			
			newMonsterCell = grid.getCell(5, 5);
			monster.setCell(newMonsterCell);
			
			this.repaint();
		}

		
		public void actionPerformed(ActionEvent arg0)
		{
			if (((JButton) arg0.getSource()).getText().compareTo("start") == 0)
			{
				player.setReady(true);
			}
			else if (((JButton) arg0.getSource()).getText().compareTo("pause") == 0)
			{
				player.rest();
			}
			else if (((JButton) arg0.getSource()).getText().compareTo("restart") == 0)
			{
				try {
					resetGame();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
//			else if (((JButton) arg0.getSource()).getText().compareTo("save") == 0)
//			{
//				System.out.println("Save button pressed");
//				
//					try {
//						save.save();
//					} catch (FileNotFoundException
//							| UnsupportedEncodingException e) {
//						
//						e.printStackTrace();
//					}
//					System.out.println("Save tried");
				
			}
		

	public boolean held;
	public void keyTyped(KeyEvent e)
	{
		if (Player.getCurrentEnergy() > 0) 
		{
			int c = e.getKeyCode();
			if (c == KeyEvent.VK_UP)
			{
				player.setDirection('U');
			} 
			else if (c == KeyEvent.VK_DOWN) 
			{
				player.setDirection('D');
			} 
			else if (c == KeyEvent.VK_LEFT)
			{
				player.setDirection('L');
			} 
			else if (c == KeyEvent.VK_RIGHT)
			{
				player.setDirection('R');
			}
			else if (c==KeyEvent.VK_SPACE)
			{
				try {
					trap.setCell(player.getCell());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				trap.setVisible(true);
			}
			player.updateEnergy(1);
			
			Game.jWarning.setText("Energy Levels: " + Player.getCurrentEnergy());
		} 
		else 
			Game.jWarning.setText("You have run out of Energy!"); 
		
	}

	public void keyReleased(KeyEvent arg0)
	{
		held = false;
		buttonHeld(arg0, held);
	}

	public void keyPressed(KeyEvent e) 
	{
		held = true;
		buttonHeld(e, held);
		
	}

	public void buttonHeld(KeyEvent c, boolean hold)
	{
		if (Player.getCurrentEnergy() > 0) 
		{
			boolean keep = hold;
			int d = c.getKeyCode();
			if (keep = true) 
			{
				if (d == KeyEvent.VK_UP)
				{
					player.setDirection('U');
				} 
				else if (d == KeyEvent.VK_DOWN) 
				{
					player.setDirection('D');
				} 
				else if (d == KeyEvent.VK_LEFT)
				{
					player.setDirection('L');
				} 
				else if (d == KeyEvent.VK_RIGHT) 
				{
					player.setDirection('R');
				}
				else if (d == KeyEvent.VK_SHIFT)
				{
					multiple = true;
				}
				player.updateEnergy(1);
				Game.jWarning.setText("Energy Levels: " + Player.getCurrentEnergy());
			} 
			}
		else 
			Game.jWarning.setText("You have run out of Energy!"); 
		}
	}
