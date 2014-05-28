
public class Kid extends Monster {
	
	private boolean canView = true; // allows
	private Player player;
	protected int time = 0;

	public Kid(Grid g, Player p, int row, int col) throws Exception 
	{
		super(g, p, row, col);
		player = p;
		setCell(grid.getCell(row, col));
		grid = g;
	}

	public Cell move() 
	{
		if (grid.getBestDirection(currentCell, player.getCell()) == 'R')
		{
		currentDirection = 'L';
		currentCell = (grid.getCell(getCell(), getDirection()));
		}
		else if (grid.getBestDirection(currentCell, player.getCell()) == 'L')
		{
		currentDirection = 'R';
		currentCell = (grid.getCell(getCell(), getDirection()));
		}
		else if (grid.getBestDirection(currentCell, player.getCell()) == 'U')
		{
		currentDirection = 'D';
		currentCell = (grid.getCell(getCell(), getDirection()));
		}
		else if (grid.getBestDirection(currentCell, player.getCell()) == 'D')
		{
		currentDirection = 'U';
		currentCell = (grid.getCell(getCell(), getDirection()));
		}
		return currentCell;
	}

	public boolean viewable() // can be used for hiding
	{
		time ++;
		if (time >= 5)
		{			
			return true;
		}
			else 
				return false;
	
	}
	
}
