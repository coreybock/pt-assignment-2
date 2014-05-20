//import java.io.*;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.*;
//
//
//public class saveGame {
//	private Player player;
//	private Monster monster;
//	private Cell oldMonsterPosition;
//	private Cell oldPlayerPosition;
//	final static Charset ENCODING = StandardCharsets.UTF_8;
//	
//	public saveGame(Player pl, Monster mon) {
//		this.monster = mon;
//		this.player = pl;
//		this.oldMonsterPosition = null;
//		this.oldPlayerPosition = null;
//	}
//	public void save() throws FileNotFoundException, UnsupportedEncodingException{
//		
//		Cell monsterPosition	= monster.getCell();
//		Cell playerPosition 	= player.getCell();
//	
//		System.out.println("Monster: " + monsterPosition.row + " " + monsterPosition.col + " " + playerPosition.row + " " + playerPosition.col);
//
//		
//		System.out.print("Eh");
//		
//		PrintWriter writer = new PrintWriter("save.txt", "UTF-8");
//		writer.println(monsterPosition.row + " " + monsterPosition.col + " " + playerPosition.row + " " + playerPosition.col);
//		writer.close();
//
//	}
//
//	public void read() throws IOException
//	{
//		Path path = Paths.get("save.txt");
//	    try (BufferedReader reader = Files.newBufferedReader(path, ENCODING))
//	    		{
//	    	
//	    			String line = null;
//	    			String[] spots;
//	      
//	      while ((line = reader.readLine()) != null) {
//	      spots = line.split(" ");
//	        
//	        for (int i=0; i<=spots.length;i++)
//	        {
//	        	if (i <=1)
//	        	{
//	        		int monRow = Integer.parseInt(spots[0]);
//	        		int monCol = Integer.parseInt(spots[1]);
//	        		oldMonsterPosition = new Cell(monRow, monCol);
//	        	}
//	        	else if (i > 1)
//	        	{
//	        		int playRow = Integer.parseInt(spots[2]);
//	        		int playCol = Integer.parseInt(spots[3]);
//	        		oldPlayerPosition = new Cell(playRow, playCol);
//	        	}
//	        }
//	      }      
//	    }
//	}
//	public Cell getOldMonsterPosition(){
//		return oldMonsterPosition;
//	}
//	public Cell getOldPlayerPosition(){
//		return oldPlayerPosition;
//	}
//}
