import java.sql.*;

import javax.swing.JOptionPane;

public class Database {
	
	protected static String username;
	protected static String password;
	
	public Database(){
	}

	static boolean checkConnection(String username, String password) throws DatabaseException{

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
						Authentication.loggedAccID = accID;
						Authentication.loggedAccName = accName;
						Authentication.loggedUsername = accUsername;
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
			throw new DatabaseException();
			
		}
		}
	public boolean addDetails(String Name, String Username, String Address, String Password) throws DatabaseException
	{
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "javagame";
		String driver = "com.mysql.jdbc.Driver";
		
		String dbUsername = "coreyboc_game";
		String dbPassword = "$ptass2";
		
//		String accName = Name;
//		String accUsername = Username;
//		String accAddress = Address;
//		String accPassword = Password;
		
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url+dbName,dbUsername,dbPassword);
			
			String insert = " insert into accounts (accID, accName, accUsername, accPassword, accAddress)"
			        + " values (?, ?, ?, ?, ?)";
			
			PreparedStatement query = conn.prepareStatement(insert);
				query.setString (1, null);
				query.setString (2, Name);
				query.setString (3, Username);
				query.setString (4, Password);
				query.setString (5, Address);

		    
		      query.execute();
			  JOptionPane.showMessageDialog (null, "Registration is complete! \n You may login now.", "Registration Successfull", JOptionPane.INFORMATION_MESSAGE);
			  return true;
			
		} catch (Exception e) {
			throw new DatabaseException();
			
		}

	}
}
