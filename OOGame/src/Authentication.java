import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;



public class Authentication extends JFrame{
	public static JFrame authFrame;
	public static String loggedUsername;
	public static String loggedAccName;
	public static int loggedAccID;
	protected boolean Visible = true;
	
	public Authentication(){
	    
		authFrame  = new JFrame("Login");
		authFrame.setResizable(false);
        authFrame.setLayout(new GridBagLayout());
        authFrame.setSize(1000, 600);
        authFrame.setLocationRelativeTo(null); 
        authFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        authFrame.setAlwaysOnTop (true);
		
		JPanel bgAuthPanel = new BgAuthPanel();
		bgAuthPanel.setLayout(new BorderLayout());
		bgAuthPanel.add(new registerAuthComponents(), BorderLayout.CENTER);
		
		authFrame.setContentPane(bgAuthPanel);
		authFrame.setVisible(true);
	}
}
class BgAuthPanel extends JPanel {
	Image bg = new ImageIcon("src/resources/bgColor.jpg").getImage();
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}

class registerAuthComponents extends JPanel {
	registerAuthComponents()
	{
		setOpaque(false);
		setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel heading = new JLabel("<html><center>Fatty and Cupcake<br>Please login<br></center></html>");
		JLabel line = new JLabel("<html><br></html>");
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		add(heading, c);
		add(line, c);
		c.insets = new Insets(1,1,1,1);

		final JTextField 	usernameField 	= new JTextField("Username",25);
		final JTextField 	passwordField 	= new JPasswordField("",25);
		final JButton		submit			= new JButton("Login");
		final JButton		register		= new JButton("Register");
		final JLabel		blank			= new JLabel("\n");
		
        
		usernameField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                usernameField.setText("");
            }
        });
		
		passwordField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                passwordField.setText("");
            }
        });
		
		c.gridx = 1;
		c.gridy = 1;
		add(usernameField, c);
		c.gridy = 2;
		add(passwordField, c);
		c.gridy = 3;
		add(submit, c);
		c.gridy = 4;
		add(blank, c);
		c.gridy = 5;
		add(register, c);

		submit.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				   String username = usernameField.getText();
				   String password = passwordField.getText();
				   
				   try {
					CheckDetails(username, password);
				} catch (Exception e) {
					e.printStackTrace();
				}
			  	}
			});
		register.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					RegisterPanel regPanel = new RegisterPanel();
					
				}
		});
		Authentication.authFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        Authentication.authFrame.dispose();
		        try {
					CheckDetails("guest", "password");
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		});

		
	}
	
	
	public void CheckDetails(String username, String password) throws Exception
	{
		Database db1 = new Database();
		boolean logged = db1.checkConnection(username,password);

		if (logged) {
			
			close();
		}
		else{
			JOptionPane.showMessageDialog (Authentication.authFrame, "Your login credentials were incorrect!", "Login failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void close() throws Exception{
		
		Authentication.authFrame.dispose();
		Game.loggedIn = true;
	}

}
