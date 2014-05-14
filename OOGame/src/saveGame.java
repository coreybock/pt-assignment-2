import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class saveGame extends Game {

	public saveGame() throws Exception {
		super();
		
	}
	public void save(){
		
		Cell monsterPosition 	= monster.getCell();
		Cell playerPosition 	= player.getCell();
		
		System.out.println("Monster: " + monsterPosition);
		System.out.println("Player: " + playerPosition);
		
//		PrintWriter writer = new PrintWriter("save.txt", "UTF-8");
//		writer.println("Monster " + monsterPosition);
//		writer.println("Player " + playerPosition);
//		writer.close();

	}

	

}
