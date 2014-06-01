
public class Trap{

	private Player player;
	private Grid grid;
	private Cell currentCell;
	private Monster monster;
	
	private boolean visible = false;
	
	public Trap(Grid g, Player p, Monster m) throws Exception {
		grid = g;
		player = p;
		monster = m;
		setCell(player.getCell());
	}

	public void setCell(Cell d) throws Exception
	{
		currentCell = d;
	}
	
	public boolean visible()
	{
		return visible;
	}
	public void setVisible(boolean k)
	{
		if (k)
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
