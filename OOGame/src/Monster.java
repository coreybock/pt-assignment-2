/* This class encapsulates Monster position and direction
 * It also keeps a reference to the player it is tracking
 * The canView attribute can be used to limit monster visibility
 */

public class Monster extends Moveable {
	private boolean canView = true; // allows
	private Player player;

	public Monster(Grid g, Player p, int row, int col) throws Exception {
		super(g);
		player = p;
		setCell(grid.getCell(row, col));
	}

	public Cell move() {
		currentDirection = grid.getBestDirection(currentCell, player.getCell());
		currentCell = (grid.getCell(getCell(), getDirection()));
		return currentCell;
	}

	public boolean viewable() // can be used for hiding
	{
		Cell monster = getCell();
		Cell player = Player.getCell();
		int distanceCol = 0;
		int distanceRow = 0;
		if(monster.col>player.col){
		distanceCol = monster.col - player.col ;
		}
		else distanceCol = player.col - monster.col;
		if(monster.row>player.row)
		{
			distanceRow =monster.row - player.row;
		}
		else distanceRow = player.row - monster.row;
			if(distanceCol <= 5 || distanceRow<=5)
			{
				canView = true;
			}
		else
			canView = false;
			return canView;
	}
}