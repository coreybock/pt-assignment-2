import java.sql.*;

public class Database {
	
	protected static String username;
	protected static String password;
	
	public Database(String username, String password){
		this.username = username;
		this.password = password;
	}

	static boolean checkConnection(){
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "javagame";
		String driver = "com.mysql.jdbc.Driver";
		
		String dbUsername = "coreyboc_game";
		String dbPassword = "$ptass2";
		
		String gameUsername = username;
		String gamePassword = password;
		
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url+dbName,dbUsername,dbPassword);
			
		
			Statement st = conn.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM  accounts");
			
			while (res.next()){
				
				int accID 			= res.getInt("accID");
				String accName 		= res.getString("accName");
				String accUsername	= res.getString("accUsername");
				String accPassword	= res.getString("accPassword");
				
				System.out.println(accID + " " + accName + " " + accUsername + " " + accPassword);
				
				if (accUsername.equals(gameUsername))
				{
					if (accPassword.equals(gamePassword))
					{
						System.out.println("Password correct");
						return true;
					}
					else {
						System.out.println("Password incorrect");
						return false;
					}
				}
				
			} return false;
			} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		}
}
