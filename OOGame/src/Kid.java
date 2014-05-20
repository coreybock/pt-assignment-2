
public class Kid extends Monster {
	
	private boolean canView = true; // allows
	private Player player;
	protected int time = 0;

	public Kid(Grid g, Player p, int row, int col) throws Exception 
	{
		super(g, p, row, col);
		player = p;
		setCell(grid.getCell(row, col));
	}

	public Cell move() 
	{
		currentDirection = grid.getBestDirection(currentCell, player.getCell());
		currentCell = (grid.getCell(getCell(), getDirection()));
		return currentCell;
	}

	public boolean viewable() // can be used for hiding
	{
		time ++;
		if (time >= 2)
		{			
			return true;
		}
			else 
				return false;
	
	}
	
}
