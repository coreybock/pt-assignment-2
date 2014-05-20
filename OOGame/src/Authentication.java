import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;


public class Authentication extends JFrame {
	
	private JTextField 	usernameField 	= new JTextField("Username",25);
	private JTextField 	passwordField 	= new JTextField("Password",25);
	private JButton		submit			= new JButton("Login");
	private JLabel		loggedIn		= new JLabel("You have been logged in.");
	private	JLabel		loggedFa		= new JLabel("You have not been logged in.");
	private JFrame 		loginPage;
	
	
	public Authentication(){
	
		Database db1 = new Database("laceylace","password");
		boolean logged = db1.checkConnection();
		JPanel panel = new JPanel();
		JPanel top	= new JPanel();
		
		panel.add(usernameField);
		panel.add(passwordField);
		panel.add(submit);
		
		panel.setLayout(new GridLayout(5, 4, 5, 5));
		
		add(top, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		
		if (logged) {
			panel.add(loggedIn);
		}
		else
			panel.add(loggedFa);
		}
	
}
