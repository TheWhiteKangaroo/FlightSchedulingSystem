package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

import repository.*;
import entity.*;


public class ManagerHome extends JFrame implements ActionListener,MouseListener{
	JLabel title;
	JButton logoutBtn, schBtn, empBtn,checkBtn,changePassBtn;
	JPanel panel;
	Font myFont;
	Color myColor;
	
	Person person;
	Flight flight;
	
	public ManagerHome(Person person)
	{
		super("Manager Home");
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.myColor = new Color(200, 200, 200);
		this.myFont = new Font("Cambria", Font.ITALIC | Font.BOLD, 20);
		
		this.person = person;
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(myColor);
		
		title = new JLabel("Flight Scheduling System");
		title.setBounds(5,5, 300, 60);
		title.setFont(myFont);
		title.setForeground(Color.BLUE);
		panel.add(title);
		
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(680,20,100,30);
		logoutBtn.setBackground(myColor);
		logoutBtn.setForeground(Color.RED);

		logoutBtn.addActionListener(this);
		logoutBtn.addMouseListener(this);
		panel.add(logoutBtn);

		checkBtn = new JButton("Check Flights");
		checkBtn.setBounds(240,120,250,70);
		checkBtn.addActionListener(this);
		panel.add(checkBtn);
		
		schBtn = new JButton("Manage Schedule");
		schBtn.setBounds(240, 200, 250, 70);
		schBtn.addActionListener(this);
		panel.add(schBtn);
				
		empBtn = new JButton("Manage Employee");
		empBtn.setBounds(240, 280, 250, 70);
		empBtn.addActionListener(this);
		panel.add(empBtn);

		changePassBtn = new JButton("Change Password");
		changePassBtn.setBounds(240,360,250,70);
		changePassBtn.addActionListener(this);
		panel.add(changePassBtn);
	
		this.add(panel);
	
		
	}

	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}

		else if(command.equals(empBtn.getText()))
		{
			EmployeeManagementFrame emf = new EmployeeManagementFrame(person);
			emf.setVisible(true);
			this.setVisible(false);	
		}
		else if(command.equals(schBtn.getText()))
		{
			FlightManagementFrame fmf = new FlightManagementFrame(person);
			fmf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(checkBtn.getText())){
			EmployeeHome eh = new EmployeeHome(person);
			eh.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(changePassBtn.getText())){
				ForgetPasswordFrame fpf = new ForgetPasswordFrame();
				fpf.setVisible(true);
				this.setVisible(false);
			}
			else{
				
			}	
	}
	
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me){}
	public void mouseEntered(MouseEvent me){
		if(me.getSource() == logoutBtn){
			logoutBtn.setBackground(Color.RED);
			logoutBtn.setForeground(Color.WHITE);
		}
		else{

		}
	}
	public void mouseExited(MouseEvent me){
		if(me.getSource() == logoutBtn){
			logoutBtn.setBackground(myColor);
			logoutBtn.setForeground(Color.RED);
		}
		else{

		}
	}
	public void mouseReleased(MouseEvent me){}
	
}