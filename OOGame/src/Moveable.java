/* The abstract base class for Monster and Player
 * The abstract method move() must be overridden by Player and Monster classes
 */

public abstract class Moveable {
	protected char currentDirection;
	protected static Cell currentCell;
	protected Grid grid;

	public Moveable(Grid g) {
		grid = g;
	}

	public void setDirection(char d) {
		currentDirection = d;
	}

	public char getDirection() {
		return currentDirection;
	}

	public void setCell(Cell c) {
		currentCell = c;
	}

	public static Cell getCell() {
		return currentCell;
	}

	public abstract Cell move();
}