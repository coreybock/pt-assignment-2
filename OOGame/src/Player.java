public class Player extends Moveable {
	private static int currentEnergy = 40;
	public static boolean readyToStart = false;

	public Player(Grid g, int row, int col) throws Exception {
		super(g);
		currentCell = grid.getCell(row, col);
		currentDirection = ' ';
	}

	public Cell move() {
		currentCell = grid.getCell(currentCell, currentDirection);
		return currentCell;
	}

	public int maxCellsPerMove() {
		return 1;
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
	public static void rest() {
		readyToStart = false;
	}
	public static void updateEnergy(int amount){
		currentEnergy -= amount;
	}
	public static int getCurrentEnergy(){
		return currentEnergy;
	}
	}