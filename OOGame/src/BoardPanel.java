import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;

import javax.imageio.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

/* This panel represents the game board (grid) 
 * It also responds to game related events
 * The overridden paint component() is called whenever the board
 * or the pieces needs to be updated */

public class BoardPanel extends JPanel implements ActionListener, KeyListener 
{
	private Player player;
	private Monster monster;
	private Grid grid;
	private Image bg;
	private Image cnrImage;
	private ImageIcon icon, cnrIcon;
	private Graphics gr;
	private Game game;
	private final int CELLWIDTH = 40;
	private final int CELLHEIGHT = 40;
	private final int LMARGIN = 275;
	private final int TMARGIN = 50;

	public BoardPanel(Grid g, Player p, Monster m) throws MalformedURLException, IOException
	{
		icon = new ImageIcon("C:\\Users\\.Lacey\\Documents\\GitHub\\pt-assignment-2\\OOGame\\src\\resources\\bgColor.jpgg");
	    cnrIcon = new ImageIcon("C:\\Users\\.Lacey\\Documents\\GitHub\\pt-assignment-2\\OOGame\\bin\\corner.jpg");
       
	    bg = icon.getImage();
        cnrImage = cnrIcon.getImage();
		
		player = p;
		grid = g;
		monster = m;
		gr = this.getGraphics();
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
		Cell cellp;
		Cell cell;
		Cell cellm;
		for (int i = 0; i < cells.length; i++) 
		{
			cell = cells[i];
			if (cell.col % 5 == 0 && cell.row % 5 == 0)
			{
				gr.setColor(Color.blue);
				gr.fillRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
				gr.setColor(Color.black);
				gr.drawRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
			}
			else
			{
				gr.setColor(Color.gray);
				gr.fillRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
				gr.setColor(Color.black);
				gr.drawRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
				
				
			}
		}
		
		
		cellp = Player.getCell();
		ImageIcon imageCake = new ImageIcon("C:\\Users\\.Lacey\\Documents\\GitHub\\pt-assignment-2\\OOGame\\src\\resources\\cupcake.png");
		Image Cake = imageCake.getImage();
		gr.drawImage(Cake, xCor(cellp.col), yCor(cellp.row), CELLWIDTH, CELLHEIGHT, game);
		
		if (monster.viewable()) 
		{
			ImageIcon imageMonster = new ImageIcon("C:\\Users\\.Lacey\\Documents\\GitHub\\pt-assignment-2\\OOGame\\src\\resources\\fatty.png");
			Image Monster = imageMonster.getImage();
			cellm = monster.getCell();
			gr.drawImage(Monster, xCor(cellm.col), yCor(cellm.row), CELLWIDTH, CELLHEIGHT, game);
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
		if (Player.currentEnergy > 0) 
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
			else if(c == KeyEvent.VK_ENTER)
			{
				player.setReady(true);
			}
			Player.currentEnergy -= 1;
			Game.jWarning.setText("Energy Levels: " + Player.currentEnergy);
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
