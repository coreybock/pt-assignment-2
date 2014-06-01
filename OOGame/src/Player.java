public class Player extends Moveable {
	protected static int currentEnergy = 100;
	protected boolean readyToStart = false;

	public Player(Grid g, int row, int col) throws Exception {
		super(g);
		currentCell = grid.getCell(row, col);
		currentDirection = ' ';
	}

	public Cell move() {
		currentCell = grid.getCell(currentCell, currentDirection);
		return currentCell;
	}

	public int pointsRemaining() {
		return -1; // not implemented
	}

	public void setReady(boolean val) {
		readyToStart = val;
	}

	public boolean isReady() {
		return readyToStart;
	}

	public int Maxenergy() {
		return 40;
	}

	public int Addenergy() {
		currentEnergy += 6;
		return currentEnergy;
	}
	public void rest() {
		readyToStart = false;
	}
	public void resetEnergy() {
		currentEnergy = 100;
	}
	public int updateEnergy(int amount)
	{
		if (readyToStart = true)
		{
			currentEnergy -= amount;
			System.out.println("Energy: " + currentEnergy);
		}
		return currentEnergy;
	}
	public static int getCurrentEnergy(){
		return currentEnergy;
	}
	@Override
	public void setDirection(char d) {
		currentDirection = d;
	}
	
	public void jump()
	{
		
	}
	}
	