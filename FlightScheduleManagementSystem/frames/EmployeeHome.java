package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

import repository.*;
import entity.*;

public class EmployeeHome extends JFrame implements ActionListener,MouseListener{
	private JLabel sysLabel, dateLabel;
	private JButton goButton,logoutBtn,backBtn,getAllBtn, changePassBtn;
	private JTextField dateTF;
	private JPanel panel;
	private JTable flightTable;
	private JScrollPane flightTableSP;
	private Font myFont;
	private Color myColor;

	private Person person;
	private FlightRepo fr;


	public EmployeeHome(Person person){
		super("Employee Home");
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.myColor = new Color(200, 200, 200);
		this.myFont = new Font("Cambria", Font.ITALIC | Font.BOLD, 20);
		this.person=person;

		fr = new FlightRepo();

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(myColor);

		sysLabel = new JLabel("Flight Schedule MS");
		sysLabel.setBounds(5,5,300,60);
		sysLabel.setFont(myFont);
		sysLabel.setForeground(Color.BLUE);
		panel.add(sysLabel);


		logoutBtn = new JButton("Log Out");
		logoutBtn.setBounds(680,20,100,30);
		logoutBtn.setBackground(myColor);
		logoutBtn.setForeground(Color.RED);
		logoutBtn.addActionListener(this);
		logoutBtn.addMouseListener(this);
		panel.add(logoutBtn);

		changePassBtn = new JButton("Change Password");
		changePassBtn.setBounds(550,20,120,30);
		changePassBtn.addActionListener(this);
		panel.add(changePassBtn);

		if(person.getStatus()==1){
			changePassBtn.setEnabled(false);
		}


		

		dateLabel = new JLabel("Date : ");
		dateLabel.setBounds(20,70,50,20);
		panel.add(dateLabel);

		dateTF = new JTextField("DD/MM/YYYY");
		dateTF.setBounds(65,70,120,20);
		panel.add(dateTF);

		goButton = new JButton("GO");
		goButton.setBounds(190,70,70,20);
		goButton.addActionListener(this);
		panel.add(goButton);

		getAllBtn = new JButton("Show ALL");
		getAllBtn.setBounds(270,70,120,20);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);
		

		backBtn = new JButton("Back");
		backBtn.setBounds(700,70,70,20);
		backBtn.addActionListener(this);
		panel.add(backBtn);

		String data[][]={{"","","","","","","","",""}};
		String head[]={"Flight No.","From", "Dep.Date","Dep.(Hrs)","Dep.(Mins)","To","Arr.Date","Arr.(Hrs)","Arr.(Mins)"};
		flightTable = new JTable(data,head);
		flightTableSP = new JScrollPane(flightTable);
		flightTableSP.setBounds(10,100,780,350);
		flightTable.setEnabled(false);
		panel.add(flightTableSP);

		this.add(panel);

	}
	
	public void actionPerformed(ActionEvent ae){
		String command = ae.getActionCommand();

		if(command.equals(goButton.getText())){
			String date=dateTF.getText();
				if(date !=null){
					String data[][] = fr.searchSchedule(date);
					if(data != null)
					{
					String head[]={"Flight No.","From", "Dep.Date","Dep.(Hrs)","Dep.(Mins)","To","Arr.Date","Arr.(Hrs)","Arr.(Mins)"};
			
					panel.remove(flightTableSP);
			
					flightTable = new JTable(data,head);
					flightTable.setEnabled(false);
					flightTableSP = new JScrollPane(flightTable);
					flightTableSP.setBounds(10,100,780,350);
					panel.add(flightTableSP);
			
					panel.revalidate();
					panel.repaint();

					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Invalid Date!");	
				}
			}
		else if(command.equals(logoutBtn.getText())){
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(backBtn.getText())){
			if(person != null){
				if(person.getStatus()==0){
					LoginFrame lf = new LoginFrame();
					lf.setVisible(true);
					this.setVisible(false);
				}
				else if(person.getStatus()==1){
					ManagerHome mh = new ManagerHome(person);
					mh.setVisible(true);
					this.setVisible(false);
				}
			}
		}
		else if(command.equals(getAllBtn.getText()))
		{
					String data[][] = fr.getAllFlight();
					if(data != null)
					{
					String head[]={"Flight No.","From", "Dep.Date","Dep.(Hrs)","Dep.(Mins)","To","Arr.Date","Arr.(Hrs)","Arr.(Mins)"};
			
					panel.remove(flightTableSP);
			
					flightTable = new JTable(data,head);
					flightTable.setEnabled(false);
					flightTableSP = new JScrollPane(flightTable);
					flightTableSP.setBounds(10,100,780,350);
					panel.add(flightTableSP);
			
					panel.revalidate();
					panel.repaint();

					

					}
					else
				{
					JOptionPane.showMessageDialog(this, "No Flights!");	
				}
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