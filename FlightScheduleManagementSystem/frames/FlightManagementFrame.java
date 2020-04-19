
package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;  
import java.util.Date; 

import entity.*;
import repository.*;

public class FlightManagementFrame extends JFrame implements ActionListener,MouseListener{
	private JLabel sysLabel, flightLabel, fromLabel, toLabel,dateLabel1,deptTimeLabel,dateLabel2, arrvlTimeLabel;
	private JButton addBtn, updateBtn,searchBtn, removeBtn, backBtn,logoutBtn,getAllBtn,refreshBtn;
	private JTextField flightNoTF, fromTF, toTF,date1TF,deptTime1TF,deptTime2TF,date2TF,arrvlTime1TF,arrvlTime2TF;
	private JPanel panel;
	private JTable flightTable;
	private JScrollPane flightTableSP;
	private Font myFont;
	private Color myColor;

	FlightRepo fr;
	Person person;
	PersonRepo pr;


	public FlightManagementFrame(Person person){
		super("Flight Management");
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.myColor = new Color(200, 200, 200);
		this.myFont = new Font("Cambria", Font.ITALIC | Font.BOLD, 20);

		this.person=person;

		fr = new FlightRepo();
		pr = new PersonRepo();
		

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(myColor);

		sysLabel = new JLabel("Flight Schedule MS");
		sysLabel.setBounds(5,20,200,50);
		sysLabel.setForeground(Color.BLUE);
		sysLabel.setFont(myFont);
		panel.add(sysLabel);

		logoutBtn = new JButton("Log Out");
		logoutBtn.setBounds(680,20,100,30);
		logoutBtn.setBackground(myColor);
		logoutBtn.setForeground(Color.RED);
		logoutBtn.addActionListener(this);
		logoutBtn.addMouseListener(this);
		panel.add(logoutBtn);

		flightLabel = new JLabel("Flight No. : ");
		flightLabel.setBounds(20,70,70,20);
		panel.add(flightLabel);

		flightNoTF = new JTextField();
		flightNoTF.setBounds(100,70,120,20);
		panel.add(flightNoTF);

		fromLabel = new JLabel("From : ");
		fromLabel.setBounds(20,100,70,20);
		panel.add(fromLabel);

		fromTF = new JTextField();
		fromTF.setBounds(100,100,120,20);
		panel.add(fromTF);

		dateLabel1 = new JLabel("Date : ");
		dateLabel1.setBounds(20,130,70,20);
		panel.add(dateLabel1);

		date1TF = new JTextField();
		date1TF.setBounds(100,130,120,20);
		panel.add(date1TF);

		deptTimeLabel = new JLabel("Dep. Time : ");
		deptTimeLabel.setBounds(20,160,70,20);
		panel.add(deptTimeLabel);

		deptTime1TF = new JTextField();
		deptTime1TF.setBounds(100,160,58,20);
		panel.add(deptTime1TF);
		
		deptTime2TF = new JTextField();
		deptTime2TF.setBounds(160,160,58,20);
		panel.add(deptTime2TF);



		toLabel = new JLabel("To : ");
		toLabel.setBounds(20,190,70,20);
		panel.add(toLabel);

		toTF = new JTextField();
		toTF.setBounds(100,190,120,20);
		panel.add(toTF);

		dateLabel2 = new JLabel("Date : ");
		dateLabel2.setBounds(20,220,70,20);
		panel.add(dateLabel2);

		date2TF = new JTextField();
		date2TF.setBounds(100,220,120,20);
		panel.add(date2TF);

		arrvlTimeLabel = new JLabel("Arr. Time : ");
		arrvlTimeLabel.setBounds(20,250,70,20);
		panel.add(arrvlTimeLabel);

		arrvlTime1TF = new JTextField();
		arrvlTime1TF.setBounds(100,250,58,20);
		panel.add(arrvlTime1TF);
		
		arrvlTime2TF = new JTextField();
		arrvlTime2TF.setBounds(160,250,58,20);
		panel.add(arrvlTime2TF);


		addBtn = new JButton("Add");
		addBtn.setBounds(20,400,100,30);
		addBtn.addActionListener(this);
		panel.add(addBtn);

		updateBtn = new JButton("Update");
		updateBtn.setBounds(130,400,100,30);
		updateBtn.setEnabled(false);
		updateBtn.addActionListener(this);
		panel.add(updateBtn);

		searchBtn = new JButton("Search");
		searchBtn.setBounds(250,400,100,30);
		searchBtn.addActionListener(this);
		panel.add(searchBtn);

		removeBtn = new JButton("Remove");
		removeBtn.setBounds(370,400,100,30);
		removeBtn.setEnabled(false);
		removeBtn.addActionListener(this);
		panel.add(removeBtn);

		backBtn = new JButton("Back");
		backBtn.setBounds(490,400,100,30);
		backBtn.addActionListener(this);
		panel.add(backBtn);

		getAllBtn = new JButton("Show All");
		getAllBtn.setBounds(710,400,85,30);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);

		refreshBtn  = new JButton("Refresh");
		refreshBtn.setBounds(600,400,100,30);
		refreshBtn.addActionListener(this);
		panel.add(refreshBtn);


		String data[][]={{"","","","","","","","",""}};
		String head[]={"Flight No.","From", "Dep.Date","Dep.(Hrs)","Dep.(Mins)","To","Arr.Date","Arr.(Hrs)","Arr.(Mins)"};
		flightTable = new JTable(data,head);
		flightTableSP = new JScrollPane(flightTable);
		flightTableSP.setBounds(230,60,565,320);
		flightTableSP.setBackground(Color.RED);
		flightTable.setEnabled(false);
		flightTable.setBackground(Color.RED);
		panel.add(flightTableSP);

		this.add(panel);

	}



	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(searchBtn.getText()))
		{
			if(!flightNoTF.getText().equals("") || !flightNoTF.getText().equals(null))
			{
				Flight f = fr.searchFlight(flightNoTF.getText());
				if(f!= null)
				{
					fromTF.setText(f.getJourneyFrom());
					date1TF.setText(f.getJourneyDate());
					deptTime1TF.setText(Integer.toString(f.getDepartureTimeHrs()));
					deptTime2TF.setText(Integer.toString(f.getDepartureTimeMins()));
					toTF.setText(f.getJourneyTo());
					date2TF.setText(f.getArrivalDate());
					arrvlTime1TF.setText(Integer.toString(f.getArrivalTimeHrs()));
					arrvlTime2TF.setText(Integer.toString(f.getArrivalTimeMins()));

					flightNoTF.setEnabled(false);
				
					searchBtn.setEnabled(false);
					removeBtn.setEnabled(true);
					addBtn.setEnabled(false);
					updateBtn.setEnabled(true);
					refreshBtn.setEnabled(true);
					backBtn.setEnabled(true);
				}
				else
				{	
					JOptionPane.showMessageDialog(this,"Invalid Flight Number!");
				}

			}
			
			else{
				JOptionPane.showMessageDialog(this,"Invalid Flight Number!");
			}
		}


		else if(command.equals(addBtn.getText()))

		{
			String sDate1=date1TF.getText();
			String sDate2=date2TF.getText();

			Flight f2 = null;
			f2 = fr.searchFlight(flightNoTF.getText());

			int d1 = Integer.parseInt(date1TF.getText().substring(0,2));
			int m1 = Integer.parseInt(date1TF.getText().substring(3,5));
			int y1 = Integer.parseInt(date1TF.getText().substring(6,10));

			int d2 = Integer.parseInt(date2TF.getText().substring(0,2));
			int m2 = Integer.parseInt(date2TF.getText().substring(3,5));
			int y2 = Integer.parseInt(date2TF.getText().substring(6,10));
			
			
			if(flightNoTF.getText().equals("") || flightNoTF.getText().equals(null) ||fromTF.getText().equals("") || fromTF.getText().equals(null) || date1TF.getText().equals("") || date1TF.getText().equals(null) || deptTime1TF.getText().equals("") || deptTime1TF.getText().equals(null) || deptTime2TF.getText().equals("") || deptTime2TF.getText().equals(null) || toTF.getText().equals("") || toTF.getText().equals(null) || date2TF.getText().equals("") || date2TF.getText().equals(null) || arrvlTime1TF.getText().equals("") || arrvlTime1TF.getText().equals(null) || arrvlTime2TF.getText().equals("") || arrvlTime2TF.getText().equals(null)  ){
				JOptionPane.showMessageDialog(this,"One Of The Fields Missing!");
			}
			else if(fromTF.getText().equals(toTF.getText()) || fromTF.getText().toLowerCase().equals(toTF.getText().toLowerCase()) || fromTF.getText().toUpperCase().equals(toTF.getText().toUpperCase())){
				JOptionPane.showMessageDialog(this,"Invalid Destination!");
			}
			else if(f2!=null){
				JOptionPane.showMessageDialog(this,"Flight No. Already Exists!");
			}
			else if(Integer.parseInt(deptTime1TF.getText()) >= 24 || Integer.parseInt(deptTime2TF.getText()) >=60 || Integer.parseInt(arrvlTime1TF.getText()) >=24 || Integer.parseInt(arrvlTime2TF.getText()) >= 60 ){
				JOptionPane.showMessageDialog(this,"Invalid  Time!");
			}
			else if(Integer.parseInt(arrvlTime1TF.getText()) >=24 || Integer.parseInt(arrvlTime2TF.getText()) >= 60){
				JOptionPane.showMessageDialog(this,"Invalid  Time!");
			}
			else if( Integer.parseInt(date1TF.getText().substring(0,2))>Integer.parseInt(date2TF.getText().substring(0,2)) ||  Integer.parseInt(date1TF.getText().substring(3,5))> Integer.parseInt(date2TF.getText().substring(3,5)) ||  Integer.parseInt(date1TF.getText().substring(6,10))>Integer.parseInt(date2TF.getText().substring(6,10))  ){
				JOptionPane.showMessageDialog(this,"Invalid Arrival Date!");
			}
			else if((m2-m1)>=1 || (y2-y1)>=1 || (d2-d1)>=4){
				JOptionPane.showMessageDialog(this,"Invalid Arrival Date!");	
			}

			else{
				Flight f = new Flight();
		
			f.setFlightNumber(flightNoTF.getText());
			f.setJourneyFrom(fromTF.getText());
			f.setJourneyDate(date1TF.getText());
			f.setDepartureTimeHrs(Integer.parseInt(deptTime1TF.getText()));
			f.setDepartureTimeMins(Integer.parseInt(deptTime2TF.getText()));
			f.setJourneyTo(toTF.getText());
			f.setArrivalDate(date2TF.getText());
			f.setArrivalTimeHrs(Integer.parseInt(arrvlTime1TF.getText()));
			f.setArrivalTimeMins(Integer.parseInt(arrvlTime2TF.getText()));
			
			fr.insertFlight(f);
			
			JOptionPane.showMessageDialog(this, "Inserted Flight!");
			
			flightNoTF.setText("");
			fromTF.setText("");
			date1TF.setText("");
			deptTime1TF.setText("");
			deptTime2TF.setText("");
			toTF.setText("");
			date2TF.setText("");
			arrvlTime1TF.setText("");
			arrvlTime2TF.setText("");
			
			searchBtn.setEnabled(true);
			addBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			removeBtn.setEnabled(false);
			refreshBtn.setEnabled(true);
			backBtn.setEnabled(true);
			}
			
			
		}
		else if(command.equals(refreshBtn.getText()))
		{
			flightNoTF.setText("");
			fromTF.setText("");
			date1TF.setText("");
			deptTime1TF.setText("");
			deptTime2TF.setText("");
			toTF.setText("");
			date2TF.setText("");
			arrvlTime1TF.setText("");
			arrvlTime2TF.setText("");


			String data[][]={{"","","","","","","","",""}};
			String head[]={"Flight No.","From", "Dep.Date","Dep.(Hrs)","Dep.(Mins)","To","Arr.Date","Arr.(Hrs)","Arr.(Mins)"};
			flightTable = new JTable(data,head);
			flightTableSP = new JScrollPane(flightTable);
			flightTableSP.setBounds(230,60,565,320);
			flightTable.setEnabled(false);
			panel.add(flightTableSP);

			
			flightNoTF.setEnabled(true);
			searchBtn.setEnabled(true);
			addBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			removeBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			backBtn.setEnabled(true);
		}
		else if(command.equals(updateBtn.getText()))
		{
			int d1 = Integer.parseInt(date1TF.getText().substring(0,2));
			int m1 = Integer.parseInt(date1TF.getText().substring(3,5));
			int y1 = Integer.parseInt(date1TF.getText().substring(6,10));

			int d2 = Integer.parseInt(date2TF.getText().substring(0,2));
			int m2 = Integer.parseInt(date2TF.getText().substring(3,5));
			int y2 = Integer.parseInt(date2TF.getText().substring(6,10));


			
			if(flightNoTF.getText().equals("") || flightNoTF.getText().equals(null) ||fromTF.getText().equals("") || fromTF.getText().equals(null) || date1TF.getText().equals("") || date1TF.getText().equals(null) || deptTime1TF.getText().equals("") || deptTime1TF.getText().equals(null) || deptTime2TF.getText().equals("") || deptTime2TF.getText().equals(null) || toTF.getText().equals("") || toTF.getText().equals(null) || date2TF.getText().equals("") || date2TF.getText().equals(null) || arrvlTime1TF.getText().equals("") || arrvlTime1TF.getText().equals(null) || arrvlTime2TF.getText().equals("") || arrvlTime2TF.getText().equals(null)  ){
				JOptionPane.showMessageDialog(this,"One Of The Fields Missing!");
			}
			else if(Integer.parseInt(deptTime1TF.getText()) >= 24 || Integer.parseInt(deptTime2TF.getText()) >=60 ){
				JOptionPane.showMessageDialog(this,"Invalid  Time!");
			}
			else if(Integer.parseInt(arrvlTime1TF.getText()) >=24 || Integer.parseInt(arrvlTime2TF.getText()) >= 60){
				JOptionPane.showMessageDialog(this,"Invalid  Time!");
			}
			else if(fromTF.getText().equals(toTF.getText()) || fromTF.getText().toLowerCase().equals(toTF.getText().toLowerCase()) || fromTF.getText().toUpperCase().equals(toTF.getText().toUpperCase())){
				JOptionPane.showMessageDialog(this,"Invalid Destination!");
			}
			else if( Integer.parseInt(date1TF.getText().substring(0,2))>Integer.parseInt(date2TF.getText().substring(0,2)) ||  Integer.parseInt(date1TF.getText().substring(3,5))> Integer.parseInt(date2TF.getText().substring(3,5)) ||  Integer.parseInt(date1TF.getText().substring(6,10))>Integer.parseInt(date2TF.getText().substring(6,10))  ){
				JOptionPane.showMessageDialog(this,"Invalid Arrival Date!");
			}
			else if((m2-m1)>=1 || (y2-y1)>=1 || (d2-d1)>=4){
				JOptionPane.showMessageDialog(this,"Invalid Arrival Date!");	
			}

			else{
				Flight f = new Flight();
		
			f.setFlightNumber(flightNoTF.getText());
			f.setJourneyFrom(fromTF.getText());
			f.setJourneyDate(date1TF.getText());
			f.setDepartureTimeHrs(Integer.parseInt(deptTime1TF.getText()));
			f.setDepartureTimeMins(Integer.parseInt(deptTime2TF.getText()));
			f.setJourneyTo(toTF.getText());
			f.setArrivalDate(date2TF.getText());
			f.setArrivalTimeHrs(Integer.parseInt(arrvlTime1TF.getText()));
			f.setArrivalTimeMins(Integer.parseInt(arrvlTime2TF.getText()));
			
			fr.updateFlight(f);
			
			JOptionPane.showMessageDialog(this, "Updated Flight!");
			
			flightNoTF.setText("");
			fromTF.setText("");
			date1TF.setText("");
			deptTime1TF.setText("");
			deptTime2TF.setText("");
			toTF.setText("");
			date2TF.setText("");
			arrvlTime1TF.setText("");
			arrvlTime2TF.setText("");
			
			searchBtn.setEnabled(true);
			addBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			removeBtn.setEnabled(false);
			refreshBtn.setEnabled(true);
			backBtn.setEnabled(true);
			}
			
			
		}
		else if(command.equals(removeBtn.getText()))
		{
			fr.removeFlight(flightNoTF.getText());
			
			JOptionPane.showMessageDialog(this,"Flight Removed!");
			
			flightNoTF.setText("");
			fromTF.setText("");
			date1TF.setText("");
			deptTime1TF.setText("");
			deptTime2TF.setText("");
			toTF.setText("");
			date2TF.setText("");
			arrvlTime1TF.setText("");
			arrvlTime2TF.setText("");
			
			
			flightNoTF.setEnabled(true);
			fromTF.setEnabled(true);
			date1TF.setEnabled(true);
			deptTime1TF.setEnabled(true);
			deptTime2TF.setEnabled(true);
			toTF.setEnabled(true);
			date2TF.setEnabled(true);
			arrvlTime1TF.setEnabled(true);
			arrvlTime2TF.setEnabled(true);
	
			searchBtn.setEnabled(true);
			addBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			removeBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			backBtn.setEnabled(true);
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
					flightTableSP.setBounds(230,60,565,320);
					panel.add(flightTableSP);
			
					panel.revalidate();
					panel.repaint();

					refreshBtn.setEnabled(true);
					flightNoTF.setEnabled(true);

					}
					else
				{
					JOptionPane.showMessageDialog(this, "No Flight!");	
				}
			}
		else if(command.equals(backBtn.getText()))
		{
			ManagerHome mh = new ManagerHome(person);
			mh.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(logoutBtn.getText())){
			LoginFrame lf =  new LoginFrame();
			lf.setVisible(true);
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
