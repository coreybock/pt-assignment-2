
public class Nuggets
{
	private Player player;
	private Grid grid;
	private Cell currentCell;
	protected boolean visible = false;

	
	public Nuggets(Grid g, Player p) throws Exception
	{
		grid = g;
		player = p;
		setCell(g.getCell(randomRow(), randomCol()));
	}

	public static int Randomize()
	{
		int n = 1 + (int)(Math.random() * ((4 - 1) + 1));
		return n;
	}
	
	public void spawnNugget() throws Exception{
		Cell nuggetCell = grid.getCell(randomRow(), randomCol());
		Cell playerCell = player.getCell();
		
		do
		{
			nuggetCell = grid.getCell(randomRow(), randomCol());
		}
		while (playerCell == nuggetCell);
		{
			currentCell = nuggetCell;
		}
		
	}
	public boolean visible(){
		return visible;
	}
	
	public void setVisible(){
		visible = true;
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
}
