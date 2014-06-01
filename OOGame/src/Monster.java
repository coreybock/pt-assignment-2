/* This class encapsulates Monster position and direction
 * It also keeps a reference to the player it is tracking
 * The canView attribute can be used to limit monster visibility
 */

public class Monster extends Moveable {
	private boolean isHiddenMonster = false; // allows
	private Player player;
	private boolean frozen = false;
	protected Game game; 

	public Monster(Grid g, Player p, int row, int col, Game l) throws Exception {
		super(g);
		player = p;
		setCell(grid.getCell(row, col));
		game = l;
	}

	public Cell move() {
		if (frozen = false)
		{
		currentDirection = grid.getBestDirection(currentCell, player.getCell());
		currentCell = (grid.getCell(getCell(), getDirection()));
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
	public void setFreeze()
	{
		System.out.println("FREEZE");
		frozen = true;
		String label = game.mLabel.getText();
		System.out.println(label);
	}
	
}