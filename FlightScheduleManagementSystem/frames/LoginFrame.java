package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import entity.*;
import repository.*;


public class LoginFrame extends JFrame implements ActionListener, MouseListener
{
	JLabel title, userLabel, passLabel;
	JTextField userTF;
	JPasswordField passPF;
	JButton loginBtn, exitBtn, frgtBtn, showPassBtn;
	JPanel panel;
	Font myFont;
	Color myColor;
	
	
	public LoginFrame()
	{
		super("Flight Scheduling System - Login Window");
		
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.myColor = new Color(200, 200, 200);
		this.myFont = new Font("Cambria", Font.ITALIC | Font.BOLD, 20);
		
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(myColor);
		
		title = new JLabel("Flight Schedule MS");
		title.setBounds(5,5, 300, 60);
		title.setFont(myFont);
		title.setForeground(Color.BLUE);
		panel.add(title);


		userLabel = new JLabel("User ID:");
		userLabel.setBounds(290 , 170, 70, 30);
		panel.add(userLabel);
		
		userTF = new JTextField();
		userTF.setBounds(360, 170, 150, 30);
		panel.add(userTF);
		
		passLabel = new JLabel("Password:");
		passLabel.setBounds(290, 220, 70, 30);
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(360, 220, 150, 30);
		passPF.setEchoChar('*');
		panel.add(passPF);

		showPassBtn = new JButton("Show");
		showPassBtn.setBounds(510, 220, 70, 30);
		showPassBtn.setBackground(myColor);
		showPassBtn.setForeground(Color.BLACK);
		showPassBtn.addMouseListener(this);
		showPassBtn.addMouseListener(this);
		panel.add(showPassBtn);
		
		
		loginBtn = new JButton("Login");
		loginBtn.setBounds(360, 300, 80, 30);
		loginBtn.setBackground(myColor);
		loginBtn.setForeground(Color.BLACK);
		loginBtn.addActionListener(this);
		loginBtn.addMouseListener(this);
		panel.add(loginBtn);
		
		
		exitBtn = new JButton("Exit");
		exitBtn.setBounds(460, 300, 80, 30);
		exitBtn.setBackground(myColor);
		exitBtn.setForeground(Color.BLACK);
		exitBtn.addActionListener(this);
		exitBtn.addMouseListener(this);
		panel.add(exitBtn);
		
		frgtBtn = new JButton("Forgot");
		frgtBtn.setBounds(260, 300, 80, 30);
		frgtBtn.setBackground(myColor);
		frgtBtn.setForeground(Color.BLACK);
		frgtBtn.addActionListener(this);
		frgtBtn.addMouseListener(this);
		panel.add(frgtBtn);

		
		this.add(panel);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(loginBtn.getText()))
		{
			PersonRepo pr = new PersonRepo();
			Person person = pr.getPerson(userTF.getText(), passPF.getText());
			
			if(person != null)
			{
				if(person.getStatus() == 1)
				{
					ManagerHome mh = new ManagerHome(person);
					mh.setVisible(true);
					this.setVisible(false);
				}
				else if(person.getStatus() == 0)
				{
					EmployeeHome eh = new EmployeeHome(person);
					eh.setVisible(true);
					this.setVisible(false);
				}
				else{}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Invaild Id or Password!");
			}
		}
		 else if(command.equals(exitBtn.getText()))
		{
			System.exit(0);
		}
		else if(command.equals(frgtBtn.getText()))
		{
			ForgetPasswordFrame fpf = new ForgetPasswordFrame();
			fpf.setVisible(true);
			this.setVisible(false);
		}
		else{

		}
	}
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me)
	{
		passPF.setEchoChar((char)0);
	}
	public void mouseReleased(MouseEvent me)
	{
		passPF.setEchoChar('*');
	}
	public void mouseEntered(MouseEvent me)
	{
		if(me.getSource() == exitBtn)
		{
			exitBtn.setBackground(Color.RED);
			exitBtn.setForeground(Color.WHITE);
		}
		else if(me.getSource() == loginBtn){
			loginBtn.setBackground(Color.BLUE);
			loginBtn.setForeground(Color.WHITE);
		}
		else if(me.getSource() == frgtBtn){
			frgtBtn.setBackground(Color.YELLOW);
			frgtBtn.setForeground(Color.BLACK);
		}
		else if(me.getSource()==showPassBtn)
		{
			showPassBtn.setBackground(Color.GREEN);
			showPassBtn.setForeground(Color.BLACK);
		}

	}
	public void mouseExited(MouseEvent me){
		if(me.getSource() == exitBtn ){
			exitBtn.setBackground(myColor);
			exitBtn.setForeground(Color.BLACK);
		}
		else if(me.getSource() == loginBtn){
			loginBtn.setBackground(myColor);
			loginBtn.setForeground(Color.BLACK);
		}			
		else if(me.getSource()==frgtBtn){
			frgtBtn.setBackground(myColor);
			frgtBtn.setForeground(Color.BLACK);
		}
		else if(me.getSource()==showPassBtn)
		{
			showPassBtn.setBackground(myColor);
			showPassBtn.setForeground(Color.BLACK);
		}
	}
	
}