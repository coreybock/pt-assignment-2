/* This class encapsulates Monster position and direction
 * It also keeps a reference to the player it is tracking
 * The canView attribute can be used to limit monster visibility
 */

public class Monster extends Moveable {
	private boolean isHiddenMonster = false; // allows
	private int time = 0;
	private int freezeTime = 0;
	private Player player;
	private boolean frozen = false;
	//protected Game game; 

	public Monster(Grid g, Player p, int row, int col) throws Exception {
		super(g);
		player = p;
		setCell(grid.getCell(row, col));
		//game = l;
	}

	public Cell move() {
		
		isFrozen(time, freezeTime);
		if (!frozen)
		{
			currentDirection = grid.getBestDirection(currentCell, player.getCell());
			currentCell = (grid.getCell(getCell(), getDirection()));
		}
		else
		{
			return currentCell;
		}
		return currentCell;
	}

	public boolean viewable() // can be used for hiding
	{
		if(isHiddenMonster)
		{
			if (player.isReady())
			{
				Cell pcell = player.getCell();
				Cell mcell = currentCell;
				int distance = grid.distance(mcell, pcell); // monster.viewable()
				
				if (distance<=5)
				{
					return true;
				}
				else 
					return false;
			}
			else 
				return false;
		}
		else
			return true;
	}
	
	public void setViewable(boolean b)
	{
		if (b == true)
		{
			isHiddenMonster = true;
		}
		else
			isHiddenMonster = false;
	}
	
	public void setFreeze(int currentTime)
	{
		freezeTime = currentTime + 10;
		System.out.println("-------------FREEZE-------------");
		frozen = true;
	}

	public void isFrozen(int time, int frozenTime)
	{
		if (time <= frozenTime)
		{
			frozen = true;
		}
		else
		{
			frozen = false;
			Game.alreadyFrozen = false;
		}
	}
	
	public void setTime(int time)
	{
		this.time = time;
	}

}