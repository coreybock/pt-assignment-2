import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;


public class GameStateManager {
	private Player player;
	private Monster monster;
	private Cell oldMonsterPosition;
	private Cell oldPlayerPosition;
	
	private int newPlayerPositionX = (Integer) null;
	private int newPlayerPositionY = (Integer) null;
	
	private int newMonsterPositionX = (Integer) null;;
	private int newMonsterPositionY = (Integer) null;;
	
	private int energy;
	private int time;
	
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public GameStateManager() {
		this.oldMonsterPosition = null;
		this.oldPlayerPosition = null;
	}
	public boolean save(Player pl, Monster mon, int energy, int time) throws FileNotFoundException, UnsupportedEncodingException{
		
		this.monster = mon;
		this.player = pl;
		
		
		Cell monsterPosition	= monster.getCell();
		Cell playerPosition 	= player.getCell();
	
		System.out.println("Monster: " + monsterPosition.row + " " + monsterPosition.col + " " + playerPosition.row + " " + playerPosition.col);

		
		System.out.print("Eh");
		
		PrintWriter writer = new PrintWriter("save.txt", "UTF-8");
		writer.println(monsterPosition.row + " " + monsterPosition.col + " " + playerPosition.row + " " + playerPosition.col + " " + energy + " " + time);
		writer.close();
		return true;

	}

	public void read() throws IOException
	{
		Path path = Paths.get("save.txt");
	    try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
	    		{
	    	
	    			String line = null;
	    			String[] spots;
	      
	      while ((line = reader.readLine()) != null) {
	      spots = line.split(" ");
	        
	        for (int i=0; i<=spots.length;i++)
	        {
	        	if (i <=1)
	        	{
	        		int monRow = Integer.parseInt(spots[0]);
	        		int monCol = Integer.parseInt(spots[1]);
	        		
	        		newMonsterPositionX = monRow;
	        		newMonsterPositionY = monCol;
	        		
	        		oldMonsterPosition = new Cell(monRow, monCol);
	        	}
	        	else if (i >= 2 && i <= 3)
	        	{
	        		int playRow = Integer.parseInt(spots[2]);
	        		int playCol = Integer.parseInt(spots[3]);
	        		
	        		newPlayerPositionX = playRow;
	        		newPlayerPositionY = playCol;
	        		
	        		oldPlayerPosition = new Cell(playRow, playCol);
	        	}
	        	else if (i > 3)
	        	{
	        		energy = Integer.parseInt(spots[4]);
	        		time = Integer.parseInt(spots[5]);
	        	}
	        }
	      }      
	    }
	}
	public Cell getOldMonsterPosition(){
		return oldMonsterPosition;
	}
	public Cell getOldPlayerPosition(){
		return oldPlayerPosition;
	}
	public int getPlayerPositionX(){
		if (newPlayerPositionX != (Integer) null)
		return newPlayerPositionX;
		else
		return 0;
	}
	public int getPlayerPositionY(){
		if (newPlayerPositionY != (Integer) null)
		return newPlayerPositionY;
		else
		return 0;
	}
	public int getMonsterPositionX(){
		if (newMonsterPositionX != (Integer) null)
		return newMonsterPositionX;
		else
		return 5;
	}
	public int getMonsterPositionY(){
		if (newMonsterPositionY != (Integer) null)
		return newMonsterPositionY;
		else
		return 5;
	}
}
