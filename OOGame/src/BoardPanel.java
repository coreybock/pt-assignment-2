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
//	private saveGame save;
	private final int CELLWIDTH = 40;
	private final int CELLHEIGHT = 40;
	private final int LMARGIN = 275;
	private final int TMARGIN = 50;

	public BoardPanel(Grid g, Player p, Monster m) throws Exception
	{
		icon = new ImageIcon("C:\\Users\\Corey\\Downloads\\bgColor.jpg");
	    cnrIcon = new ImageIcon("C:\\Users\\Corey\\Documents\\GitHub\\pt-assignment-2\\OOGame\\bin\\corner.jpg");
       
	    bg = icon.getImage();
        cnrImage = cnrIcon.getImage();

		player = p;
		grid = g;
		monster = m;
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
			int n = Nuggets.Randomize();
//			boolean visible = Nuggets.visible();
		
//			if (visible = true)
//			{
//				if(n == 1)
//				{
//					ImageIcon flourimg = new ImageIcon("C:\\Users\\.Lacey\\Downloads\\flour.jpg");
//					Image Flour = flourimg.getImage();
//					gr.drawImage(Flour, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);
//				}
//				else if (n==2)
//				{
//					ImageIcon sprinklesimg = new ImageIcon("C:\\Users\\.Lacey\\Downloads\\sprinkles.jpg");
//					Image Sprinkles = sprinklesimg.getImage();
//					gr.drawImage(Sprinkles, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);
//				}
//				else if (n==3)
//				{
//					ImageIcon sugarimg = new ImageIcon("C:\\Users\\.Lacey\\Downloads\\sugar.jpg");
//					Image Sugar = sugarimg.getImage();
//					gr.drawImage(Sugar, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);
//				}
//				else 
//				{
//					ImageIcon icingimg = new ImageIcon("C:\\Users\\.Lacey\\Downloads\\icing.jpg");
//					Image Icing = icingimg.getImage();
//					gr.drawImage(Icing, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);
//				}
//			}
	}


		cell = player.getCell();

		ImageIcon imageCake = new ImageIcon("C:\\Users\\.Lacey\\Downloads\\cake.jpg");
		Image Cake = imageCake.getImage();
		gr.drawImage(Cake, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);


		if (monster.viewable()) 
		{
			cell = monster.getCell();
			ImageIcon imageMonster = new ImageIcon("C:\\Users\\.Lacey\\Downloads\\fatty.jpg");
			Image Monster = imageMonster.getImage();
			gr.drawImage(Monster, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);
		}
		
		if (kid.viewable())
		{
			cell = kid.getCell();
			ImageIcon imageFatKid = new ImageIcon("C:\\Users\\.Lacey\\Downloads\\fatkid.jpg");
			Image FatKid = imageFatKid.getImage();
			gr.drawImage(FatKid, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);
		}
	}

		
		public void actionPerformed(ActionEvent arg0)
		{
			if (((JButton) arg0.getSource()).getText().compareTo("start") == 0)
			{
				player.setReady(true);
			}
			else if (((JButton) arg0.getSource()).getText().compareTo("pause") == 0)
			{
				Player.rest();
			}
			else if (((JButton) arg0.getSource()).getText().compareTo("restart") == 0)
			{
				game.play();
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
	public void keyTyped(KeyEvent c)
	{
		held = true;
		buttonHeld(c);
	}

	public void keyReleased(KeyEvent arg0)
	{
		held = false;
	}

	public void keyPressed(KeyEvent e) 
	{
		if (Player.getCurrentEnergy() > 0) 
		{
			int c = e.getKeyCode();
			if (c == KeyEvent.VK_UP) 
			{
				player.setDirection('U');
//				Player.updateEnergy(1);
			} 
			else if (c == KeyEvent.VK_DOWN) 
			{
				player.setDirection('D');
//				Player.updateEnergy(1);
			} 
			else if (c == KeyEvent.VK_LEFT)
			{
				player.setDirection('L');
//				Player.updateEnergy(1);
			} 
			else if (c == KeyEvent.VK_RIGHT)
			{
				player.setDirection('R');
//				Player.updateEnergy(1);
			}
			else if(c == KeyEvent.VK_ENTER)
			{
				player.setReady(true);
			}
			//Update issue
			
			Game.jWarning.setText("Energy Levels: " + Player.getCurrentEnergy());
		} 
		else 
			Game.jWarning.setText("You have run out of Energy!"); 
	}

	public void buttonHeld(KeyEvent c)
	{
		while (held = true) 
		{
			int d = c.getKeyCode();
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
			else if(d == KeyEvent.VK_ENTER)
			{
				player.setReady(true);
			}
		}
	}
}