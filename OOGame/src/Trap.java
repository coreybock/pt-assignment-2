
public class Trap extends Game {

	private Player player;
	private Grid grid;
	private Cell currentCell;
	protected boolean visible = false;
	private Monster monster;
	
	public Trap(Grid g, Player p, Monster m) throws Exception {
		grid = g;
		player = p;
		monster = m;
		setCell(player.currentCell);
	}

	public void setCell(Cell d) throws Exception
	{
		setCell(d);
	}
	
	public boolean visible()
	{
		return visible;
	}
	public void setVisible(boolean k)
	{
		if (k = true)
		{
			visible = true;
		}
		else 
			visible = false;
	}
	public Cell getCell() {
		return currentCell;
	}
}
