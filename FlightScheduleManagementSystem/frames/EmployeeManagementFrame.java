package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

import entity.*;
import repository.*;

public class EmployeeManagementFrame extends JFrame implements ActionListener,MouseListener{
	private JLabel sysLabel, empIdLabel, empNameLabel, empDesigLabel,empSalLabel;
	private JButton addBtn, updateBtn,searchBtn, removeBtn, backBtn,logoutBtn,getAllBtn,refreshBtn;
	private JTextField empIdTF, empNameTF, empDesigTF,empSalTF;
	private JPanel panel;
	private JTable empTable;
	private JScrollPane empTableSP;
	private Font myFont;
	private Color myColor;

	private Person person;
	private EmployeeRepo er;
	private PersonRepo pr;

	public EmployeeManagementFrame(Person person){
		super("Employee Management");
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.myColor = new Color(200, 200, 200);
		this.myFont = new Font("Cambria", Font.ITALIC | Font.BOLD, 20);

		this.person=person;

		er= new EmployeeRepo();
		pr = new PersonRepo();

		

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(myColor);

		sysLabel = new JLabel("Flight Schedule MS");
		sysLabel.setBounds(5,5,300,60);
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

		empIdLabel = new JLabel("ID : ");
		empIdLabel.setBounds(20,70,70,20);
		panel.add(empIdLabel);

		empIdTF = new JTextField();
		empIdTF.setBounds(100,70,120,20);
		panel.add(empIdTF);

		empNameLabel = new JLabel("Name : ");
		empNameLabel.setBounds(20,100,70,20);
		panel.add(empNameLabel);

		empNameTF = new JTextField();
		empNameTF.setBounds(100,100,120,20);
		panel.add(empNameTF);

		empDesigLabel = new JLabel("Designation : ");
		empDesigLabel.setBounds(20,130,70,20);
		panel.add(empDesigLabel);

		empDesigTF = new JTextField();
		empDesigTF.setBounds(100,130,120,20);
		panel.add(empDesigTF);

		empSalLabel = new JLabel("Salary : ");
		empSalLabel.setBounds(20,160,70,20);
		panel.add(empSalLabel);

		empSalTF = new JTextField();
		empSalTF.setBounds(100,160,120,20);
		panel.add(empSalTF);
		
		

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


		String data[][]={{"","","",""}};
		String head[]={"ID","Name", "Designation","Salary"};
		empTable = new JTable(data,head);
		empTableSP = new JScrollPane(empTable);
		empTableSP.setBounds(230,60,565,320);
		empTable.setEnabled(false);
		panel.add(empTableSP);

		this.add(panel);

	}



	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(searchBtn.getText()))
		{
			if(!empIdTF.getText().equals("") || !empIdTF.getText().equals(null))
			{
				Employee e = er.searchEmployee(empIdTF.getText());
				if(e!= null)
				{
					empNameTF.setText(e.getEmployeeName());
					empDesigTF.setText(e.getEmployeeDesignation());
					empSalTF.setText(e.getEmployeeSalary()+"");
					
					empIdTF.setEnabled(false);
					empNameTF.setEnabled(true);
					empDesigTF.setEnabled(true);
					empSalTF.setEnabled(true);
					
					updateBtn.setEnabled(true);
					removeBtn.setEnabled(true);
					refreshBtn.setEnabled(true);
					addBtn.setEnabled(false);
					searchBtn.setEnabled(false);
					backBtn.setEnabled(true);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild Employee ID!");
				}
			}
		}
		else if(command.equals(addBtn.getText()))
		{
			Person pd = new Person();
			if(empIdTF.getText().equals("") || empIdTF.getText().equals(null)){
				JOptionPane.showMessageDialog(this,"Invaild ID!");
			}
			else{
			Employee e = new Employee();
			Person p = new Person();
			Random rd = new Random();
			int x = rd.nextInt(9999)+10000;
			int y = rd.nextInt(9999)+10000;

			Employee e1 = new Employee();
			e1=er.searchEmployee(empIdTF.getText());
			if(e1!=null){
				JOptionPane.showMessageDialog(this,"Alreay Employee Id Exist!");
			}
			else if(empIdTF.getText().equals("") || empIdTF.getText().equals(null) || empNameTF.getText().equals("") || empNameTF.getText().equals(null) || empDesigTF.getText().equals("") || empDesigTF.getText().equals(null) || empSalTF.getText().equals("")  || empSalTF.getText().equals(null)){
				JOptionPane.showMessageDialog(this,"One of The Fields Missing!");
			}
			else{
				e.setEmployeeId(empIdTF.getText());
				e.setEmployeeName(empNameTF.getText());
				e.setEmployeeDesignation(empDesigTF.getText());
				e.setEmployeeSalary(Double.parseDouble(empSalTF.getText()));
				
				p.setPersonId(empIdTF.getText());
				p.setPassword(x+"");
				p.setCode(y);
				
				if(((empDesigTF.getText()).equals("Manager")) || ((empDesigTF.getText()).equals("manager")|| ((empDesigTF.getText()).equals("MANAGER") )))
				{
					p.setStatus(1);
				}
				else
				{
					p.setStatus(0);
				}
				
				er.insertEmployee(e);
				pr.insertPerson(p);
				
				JOptionPane.showMessageDialog(this, "Inserted Employee, Id: "+empIdTF.getText()+" and Password: "+x+"\n Verification Code : "+y+" \nSave the code in case you forget Password.");
				
				empIdTF.setText("");
				empNameTF.setText("");
				empDesigTF.setText("");
				empSalTF.setText("");
				
				searchBtn.setEnabled(true);
				addBtn.setEnabled(true);
				updateBtn.setEnabled(false);
				removeBtn.setEnabled(false);
				refreshBtn.setEnabled(false);
				backBtn.setEnabled(true);
				}
			}
			
			
			
			
		}
		else if(command.equals(refreshBtn.getText()))
		{
			empIdTF.setText("");
			empNameTF.setText("");
			empDesigTF.setText("");
			empSalTF.setText("");

			String data[][]={{"","","",""}};
			String head[]={"ID","Name", "Designation","Salary"};
			empTable = new JTable(data,head);
			empTableSP = new JScrollPane(empTable);
			empTableSP.setBounds(230,60,565,320);
			empTable.setEnabled(false);
			panel.add(empTableSP);
			
			empIdTF.setEnabled(true);
			searchBtn.setEnabled(true);
			addBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			removeBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			backBtn.setEnabled(true);
		}
		else if(command.equals(updateBtn.getText()))
		{
			if(empIdTF.getText().equals("") || empIdTF.getText().equals(null) || empNameTF.getText().equals("") || empNameTF.getText().equals(null) || empDesigTF.getText().equals("") || empDesigTF.getText().equals(null) || empSalTF.getText().equals("") || empSalTF.getText().equals(null) ){
				JOptionPane.showMessageDialog(this,"One of The Fields Missing!");
			}
			Employee e = new Employee();
			
			e.setEmployeeId(empIdTF.getText());
			e.setEmployeeName(empNameTF.getText());
			e.setEmployeeDesignation(empDesigTF.getText());
			e.setEmployeeSalary(Double.parseDouble(empSalTF.getText()));
			
			er.updateEmployee(e);

			Person p = new Person();
			p.setPersonId(empIdTF.getText());
			if(empDesigTF.getText().equals("Manager") || empDesigTF.getText().equals("manager")){
				p.setStatus(1);
			}			
			else{
				p.setStatus(0);
			}
			
			pr.updatePerson(p);

			JOptionPane.showMessageDialog(this, "Updated Employee!");
			
			empIdTF.setText("");
			empNameTF.setText("");
			empDesigTF.setText("");
			empSalTF.setText("");
			
			searchBtn.setEnabled(true);
			addBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			removeBtn.setEnabled(false);
			refreshBtn.setEnabled(true);
			backBtn.setEnabled(true);
			
			empIdTF.setEnabled(true);
			empNameTF.setEnabled(true);
			empDesigTF.setEnabled(true);
			empSalTF.setEnabled(true);
		}
		else if(command.equals(removeBtn.getText()))
		{
			if(person.getPersonId().equals(empIdTF.getText())){
				JOptionPane.showMessageDialog(this,"Can not Remove Yourself!");	
			}
			else  if(person.getPersonId() != empIdTF.getText()){

				er.removeEmployee(empIdTF.getText());
				pr.deletePerson(empIdTF.getText());
			
			JOptionPane.showMessageDialog(this,"Deleted Person!");
			
			empIdTF.setText("");
			empNameTF.setText("");
			empDesigTF.setText("");
			empSalTF.setText("");
			
			empIdTF.setEnabled(true);
			empNameTF.setEnabled(true);
			empDesigTF.setEnabled(true);
			empSalTF.setEnabled(true);
	
			searchBtn.setEnabled(true);
			addBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			removeBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			backBtn.setEnabled(true);
			}	 
		}
		else if(command.equals(getAllBtn.getText()))
		{
			String data[][] = er.getAllEmployee();
			String head[] = {"Id", "Name", "Designation", "Salary"};
			
			panel.remove(empTableSP);
			
			empTable = new JTable(data,head);
			empTable.setEnabled(false);
			empTableSP = new JScrollPane(empTable);
			empTableSP.setBounds(230,60,565,320);
			panel.add(empTableSP);
			
			panel.revalidate();
			panel.repaint();
			
		}
		else if(command.equals(backBtn.getText()))
		{
			ManagerHome mh = new ManagerHome(person);
			mh.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
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
