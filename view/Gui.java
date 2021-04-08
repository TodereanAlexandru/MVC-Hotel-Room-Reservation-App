package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ManageUsers;
import model.User;

public class Gui {
	public Gui() {
		JFrame frame1 = new JFrame("Hotel Manager");
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame1.setLocation(dim.width/2-frame1.getSize().width/2, dim.height/2-frame1.getSize().height/2);
		
		frame1.setSize(300,200);
	    //frame1.setVisible(true);
	    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new GridLayout(3, 2));
	    
	    JTextField user = new JTextField();
		JPasswordField pass = new JPasswordField();
	    
	    mainPanel.add(new JLabel("Username"));
	    mainPanel.add(user);
	    mainPanel.add(new JLabel("Password"));
	    mainPanel.add(pass);
	    
	    JButton btnLogin1 = new JButton("Login");
	    mainPanel.add(btnLogin1);
	    
	    btnLogin1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User u = ManageUsers.find(user.getText(), pass.getText());
				if(u == null) {
					JOptionPane.showMessageDialog(null, "Invalid username / password", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}else {
					if(u.getRole().equals("admin")) {
						//AdminGui adminGui = new AdminGui(u);
					}else {
						UserGui userGui = new UserGui();
					}
					
					frame1.setVisible(false);
				}
			}
	    });
	    
	    frame1.setContentPane(mainPanel);
	    
	    frame1.setVisible(true);
	    
		}
	


}
