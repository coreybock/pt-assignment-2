
public class Nuggets
{
	private Player player;
	private Grid grid;
	private Cell currentCell;
	protected boolean visible = true;
	public static boolean isActive = false;
	

	public Nuggets(Grid g, Player p) throws Exception
	{
		grid = g;
		player = p;
		setCell(checker());
	}

	public void move()throws Exception
	{
		setCell(checker());
		System.out.println("------Sprinkles------");
	}
	public static int Randomize()
	{
		int n = 1 + (int)(Math.random() * ((4 - 1) + 1));
		return n;
	}
	public Cell reset() throws Exception
	{
		Cell nuggetCell = grid.getCell(randomRow(), randomCol());
		return nuggetCell;
	}
	public Cell checker()throws Exception
	{
		
		Cell nuggetCell = reset();
		Cell playerCell = player.getCell();
		
		int distance = grid.distance(nuggetCell,playerCell);
		while (distance > player.currentEnergy || playerCell==nuggetCell)
		{
		 reset();
		}
		return nuggetCell;
	}
	
	public boolean visible(){
		return visible;
	}
	
	public void setVisible(boolean r)
	{
		if (r)
		{
			visible = true;
		}
		else
			visible = false; 
	}
	public int randomRow()
	{
		int randRow = (int) (Math.random() * 10);
		return randRow;
	}
	public int randomCol()
	{
		int[] availableCol = {0, 5, 10};
		int randCol = (int) (Math.random() * 3);
		return availableCol[randCol];
	}
	public void setCell(Cell c) {
		currentCell = c;
	}
	public Cell getCell() {
		return currentCell;
	}
	public boolean active()
	{
		return isActive;
	}
	public void setActive(boolean result){
		isActive = result;
		System.out.println(isActive);
	}
}
