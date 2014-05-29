
public class Kid extends Monster {
	
	protected boolean canView = true; // allows
	private Player player;
	protected int time = 0;
	protected int currentAge = 0;

	public Kid(Grid g, Player p, int row, int col) throws Exception 
	{
		super(g, p, row, col);
		player = p;
		setCell(grid.getCell(row, col));
		grid = g;
	}

	public Cell move() 
	{
		currentDirection = grid.getWorstDirection(currentCell, player.getCell());
		currentCell = (grid.getCell(getCell(), getDirection()));
		return currentCell;
	}

	public boolean viewable() // can be used for hiding
	{
		time ++;
		if (time >= 5 && canView == true && currentAge<=10)
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
	
	public int Age()
	{
		return currentAge;
	}
	
	public void UpdateAge()
	{
		currentAge ++;
	}
	
}
