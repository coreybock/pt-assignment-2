import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

/* This panel represents the game board (grid) 
 * It also responds to game related events
 * The overridden paint component() is called whenever the board
 * or the pieces needs to be updated 
 */
public class BoardPanel extends JPanel implements ActionListener, KeyListener {

	private Player player;
	private Monster monster;
	private Grid grid;
	private Graphics gr;
	private Game game;
	private final int CELLWIDTH = 40;
	private final int CELLHEIGHT = 40;
	private final int LMARGIN = 100;
	private final int TMARGIN = 100;

	public BoardPanel(Grid g, Player p, Monster m) {
		player = p;
		grid = g;
		monster = m;
		gr = this.getGraphics();
	}

	/* responds to various button clicked messages */

	/* returns the x coordinate based on left margin and cell width */
	private int xCor(int col) {
		return LMARGIN + col * CELLWIDTH;
	}

	/* returns the y coordinate based on top margin and cell height */
	private int yCor(int row) {
		return TMARGIN + row * CELLHEIGHT;
	}

	/*
	 * Redraws the board and the pieces Called initially and in response to
	 * repaint()
	 */
	protected void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		Cell cells[] = grid.getAllCells();
		Cell cell;
		for (int i = 0; i < cells.length; i++) {
			cell = cells[i];
			if (cell.col % 5 == 0 && cell.row % 5 == 0)
				gr.setColor(Color.blue);
			else
				gr.setColor(Color.gray);
			gr.fillRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
			gr.setColor(Color.black);
			gr.drawRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
		}
		cell = player.getCell();
		gr.setColor(Color.red);
		gr.fillOval(xCor(cell.col) + CELLWIDTH / 8, yCor(cell.row) + CELLWIDTH
				/ 8, CELLWIDTH * 3 / 4, CELLHEIGHT * 3 / 4);
		gr.setColor(Color.white);
		gr.drawString("P", xCor(cell.col) + CELLWIDTH / 3, yCor(cell.row) + 2
				* CELLWIDTH / 3);

		if (monster.viewable()) {
			cell = monster.getCell();
			gr.setColor(Color.black);
			gr.fillRect(xCor(cell.col), yCor(cell.row), CELLWIDTH, CELLHEIGHT);
			gr.setColor(Color.white);
			gr.drawString("M", xCor(cell.col) + CELLWIDTH / 3, yCor(cell.row)
					+ 2 * CELLWIDTH / 3);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (((JButton) arg0.getSource()).getText().compareTo("start") == 0) {
			player.setReady(true);
		}
	}

	public boolean held;

	@Override
	public void keyTyped(KeyEvent c) {
		held = true;
		buttonHeld(c);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		held = false;
	}

	public void keyPressed(KeyEvent e) {
		if (Player.currentEnergy > 0) {
			int c = e.getKeyCode();
			if (c == KeyEvent.VK_UP) {
				player.setDirection('U');
			} else if (c == KeyEvent.VK_DOWN) {
				player.setDirection('D');
			} else if (c == KeyEvent.VK_LEFT) {
				player.setDirection('L');
			} else if (c == KeyEvent.VK_RIGHT) {
				player.setDirection('R');
			}
			Player.currentEnergy -= 1;
		} else
			System.out.println("No more energy"); // change later to display
													// on screen
	}

	public void buttonHeld(KeyEvent c) {
		while (held = true) {
			int d = c.getKeyCode();
			if (d == KeyEvent.VK_UP) {
				player.setDirection('U');
			} else if (d == KeyEvent.VK_DOWN) {
				player.setDirection('D');
			} else if (d == KeyEvent.VK_LEFT) {
				player.setDirection('L');
			} else if (d == KeyEvent.VK_RIGHT) {
				player.setDirection('R');
			}
		}
	}
}
