
public class Nuggets
{
	private Player player;
	Cell Nugcells[] = BoardPanel.grid.getAllCells();
	Cell nugCell;
	Cell pcell = player.getCell();
	private boolean visible;
	
	public static int Randomize()
	{
		int n = 1 + (int)(Math.random() * ((4 - 1) + 1));
		return n;
	}
	
	public boolean visible()
	{
		for (int i = 0; i < Nugcells.length; i++) 
		{		
			nugCell = Nugcells[i];
			if(player.getCell() == nugCell)
			{
				visible = false;
			}
			else visible = true;
		}
		return visible;
	}
	
}
