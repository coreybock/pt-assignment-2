import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class RegisterPanel extends JFrame{

	static JFrame regFrame;
	
	public RegisterPanel(){
	    
		regFrame = new JFrame();
		regFrame.setTitle("Register");
		regFrame.setSize(1000, 600);
		regFrame.setLocationRelativeTo(null);
		regFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		regFrame.setLayout(new GridBagLayout());
		
		JPanel bgPanel = new BgPanel();
		bgPanel.setLayout(new BorderLayout());
		bgPanel.add(new registerComponents(), BorderLayout.CENTER);
		
		regFrame.setContentPane(bgPanel);
		regFrame.setVisible(true);
	}

}

	class BgPanel extends JPanel {
		Image bg = new ImageIcon("src/resources/bgColor.jpg").getImage();
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		}
	}
	
	class registerComponents extends JPanel {
		registerComponents()
		{
			final JLabel 		header			= 	new JLabel("<html><strong>Register Account</strong></html>");
			final JLabel 		accountLabel	=	new JLabel("Account Name: ");
			final JLabel 		usernameLabel	=	new JLabel("Username: ");
			final JLabel		addressLabel	=	new JLabel("Address: ");
			final JLabel		passwordLabel	=	new JLabel("Password: ");
			
			final JTextField 	accountField	=	new JTextField("",30);
			final JTextField 	usernameField	=	new JTextField("",30);
			final JTextField	addressField	=	new JTextField("",30);
			final JTextField	passwordField	=	new JPasswordField("",30);
			
			final JButton		submitButton	=	new JButton("<html><i>Register</i></html<");
		
			
			setOpaque(false);
			setLayout(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
	        c.fill = GridBagConstraints.HORIZONTAL;
	        header.setHorizontalAlignment(SwingConstants.CENTER);
	        submitButton.setHorizontalAlignment(SwingConstants.CENTER);
	        
	        c.gridx = 1;
			c.gridy = 0;
			c.gridwidth = 2;
			c.insets = new Insets(2, 2, 2, 2);
			header.setFont(new Font("Serif", Font.PLAIN, 20));
			add(header, c);
			
			c.gridwidth = 1;
			c.gridy = 1;
			add(new JLabel("\n"), c);
			
			c.gridx = 1;
			c.gridy = 2;
			add(accountLabel, c);
			c.gridx = 2;
			add(accountField, c);
			
			c.gridx = 1;
			c.gridy = 3;
			add(usernameLabel, c); 
			c.gridx = 2;
			add(usernameField, c);
			
			c.gridx = 1;
			c.gridy = 4;
			add(addressLabel, c);
			c.gridx = 2;
			add(addressField, c);
			
			c.gridx = 1;
			c.gridy = 5;
			add(passwordLabel, c);
			c.gridx = 2;
			add(passwordField, c);
			
			c.gridx = 2;
			c.gridy = 6;
			add(new JLabel("\n"), c);
			
			c.gridx = 1;
			c.gridy = 7;
			c.gridwidth = 2;

			add(submitButton, c);
			
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					String name		= accountField.getText();
					String username = usernameField.getText();
					String address 	= addressField.getText();
					String password = passwordField.getText();
					try {
						new addToDatabase(name, username, address, password);
					} catch (DatabaseException e) {
						e.printStackTrace();
					}
					
				}
			});
		}
		
		class addToDatabase {
			addToDatabase(String accName, String accUName, String accAddress,String accPw) throws DatabaseException
			{
				if(accName != null && !accName.isEmpty())
				{
					if (accUName != null && !accUName.isEmpty())
					{
						if (accAddress != null && !accAddress.isEmpty())
						{
							if (accPw != null && !accPw.isEmpty())
							{
								 Database db1 = new Database();
								 boolean work = db1.addDetails(accName, accUName, accAddress, accPw);
								 
								 if(work)
								 {
									 RegisterPanel.regFrame.dispose();
								 }
								 
							}
							else
								componentAlert("a Password.");

						}
						else
							componentAlert("an Address.");
					}
					else
						componentAlert("a Username.");
				}
				else
					componentAlert("an Account Name.");
					
			}

			private void componentAlert(String component) {
				
				JOptionPane.showMessageDialog (null, "You must provide " + component, "Missing Field", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		
	}

