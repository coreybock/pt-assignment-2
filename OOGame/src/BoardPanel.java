import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;

import javax.imageio.*;

import java.io.File;
import java.io.IOException;

/* This panel represents the game board (grid) 
 * It also responds to game related events
 * The overridden paint component() is called whenever the board
 * or the pieces needs to be updated */

public class BoardPanel extends JPanel implements ActionListener, KeyListener 
{
	private Player player;
	private Monster monster;
	private Grid grid;
	private Image bg, cnrImage;
	private ImageIcon icon, cnrIcon;
	private Graphics gr;
	private Game game;
	 private final int CELLWIDTH = 40;
	   private final int CELLHEIGHT = 40;
	   private final int LMARGIN = 275;
	   private final int TMARGIN = 50;

	public BoardPanel(Grid g, Player p, Monster m) 
	{
		icon = new ImageIcon("C:\\Users\\Corey\\Documents\\GitHub\\pt-assignment-2\\OOGame\\src\\resources\\corner.jpg");
	    //cnrIcon = new ImageIcon("resources\\corner.jpg");
       
	    bg = icon.getImage();
        //cnrImage = cnrIcon.getImage();
		
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
		Cell cells[] = grid.getAllCells();
		Cell cell;
		for (int i = 0; i < cells.length; i++) 
		{
			cell = cells[i];
			if (cell.col % 5 == 0 && cell.row % 5 == 0)
			{
				gr.setColor(Color.blue);
			}
			else
			{
				gr.setColor(Color.gray);
				gr.fillRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
				gr.setColor(Color.black);
				gr.drawRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
			}
		}
		
		File input = new File("C:/Users/Corey/Downloads/cake.jpg");
		cell = player.getCell();
		BufferedImage img;
		try 
		{
			img = ImageIO.read(input);
			gr.drawImage(img, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		if (monster.viewable()) 
		{
			cell = monster.getCell();
			File input2 = new File("C:/Users/Corey/Downloads/fatty.jpg");
			BufferedImage fatty;
				try 
				{
					fatty = ImageIO.read(input2);
					gr.drawImage(fatty, xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT, game);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
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
			Player.currentEnergy -= 1;
		} 
		else 
			System.out.println("No more energy"); // change later to display on screen
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
		}
	}
}
