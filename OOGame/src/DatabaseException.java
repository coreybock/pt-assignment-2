import javax.swing.JOptionPane;


public class DatabaseException extends Exception {

		public DatabaseException(){
			JOptionPane.showMessageDialog (null, "Connection to database has failed!", "Connection failed", JOptionPane.ERROR_MESSAGE);	
		};
}
