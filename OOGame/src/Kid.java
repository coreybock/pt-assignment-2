
public class Kid extends Monster {
	
	protected boolean canView = true; // allows
	private Player player;
	protected int time = 0;
	protected boolean isActive = false;
	//protected Game game; 

	public Kid(Grid g, Player p, int row, int col) throws Exception 
	{
		super(g, p, row, col);
		player = p;
		setCell(grid.getCell(row, col));
		grid = g;
		//game = l;
	}

	public Cell move() 
	{
		if (time <= 15 && canView == true)
		{
		currentDirection = grid.getWorstDirection(currentCell, player.getCell());
		currentCell = (grid.getCell(getCell(), getDirection()));
		return currentCell;
		}
		else 
			currentDirection = grid.getBestDirection(currentCell, player.getCell());
			currentCell = (grid.getCell(getCell(), getDirection()));
			return currentCell;
	}

	public boolean viewable() // can be used for hiding
	{
		time ++;
		if (time >= 5 && canView == true)
		{			
			return true;
		}
			else 
				return false;
	
	}
	public void setViewable(boolean b)
	{
		if (b == true)
		{
			canView = true;
		}
		else
			canView = false;
	}
	public boolean active()
	{
		return isActive;
	}
	public void setActive(boolean result){
		isActive = result;
		System.out.println(isActive);
	}
	public int getTime()
	{
		return time;
	}
}
