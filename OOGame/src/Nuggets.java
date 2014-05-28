
public class Nuggets
{
	private Player player;
	private Grid grid;
	private Cell nugCell;
	
	
	public static boolean visible(Player player, Cell playerCell, Cell[] nugCells)
	{
		boolean visible = false;
		for (int i = 0; i < nugCells.length; i++) 
		{		
			Cell nugCell = nugCells[i];
			if(player.getCell() == nugCell)
			{
				visible = false;
			}
			else
			{
				visible = true;
			}
		}
		return visible;
	}
	
}
