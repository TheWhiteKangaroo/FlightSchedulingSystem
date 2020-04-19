package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

import repository.*;
import entity.*;

public class ForgetPasswordFrame extends JFrame implements ActionListener{
	JLabel title,idLabel,passLabel,codeLabel,msgLabel,newPassLabel;
	JButton backBtn, submitBtn,verifyBtn;
	JTextField idTF,codeTF,passTF,newPassTF;
	JPanel panel;
	Font myFont;
	Color myColor;
	
	
	
	
	public ForgetPasswordFrame()
	{
		super("Froget Password");
		this.setSize(800,500);
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

		idLabel = new JLabel("ID : ");
		idLabel.setBounds(50 ,100, 120, 30);
		panel.add(idLabel);

		idTF = new JTextField();
		idTF.setBounds(180, 100, 120, 30);
		panel.add(idTF);

		codeLabel = new JLabel("Verification Code : ");
		codeLabel.setBounds(50,140,120,30);
		panel.add(codeLabel);

		codeTF = new JTextField();
		codeTF.setBounds(180,140,120,30);
		panel.add(codeTF);

		msgLabel = new JLabel("Your Password Is : ");
		msgLabel.setBounds(50,180,120,30);
		panel.add(msgLabel);

		passTF = new JTextField();
		passTF.setBounds(180,180,120,30);
		passTF.setEnabled(false);
		panel.add(passTF);

		newPassLabel = new JLabel("New Password : ");
		newPassLabel.setBounds(50,220,120,30);
		panel.add(newPassLabel);

		newPassTF = new JTextField();
		newPassTF.setBounds(180,220,120,30);
		newPassTF.setEnabled(false);
		panel.add(newPassTF);


		backBtn = new JButton("Back");
		backBtn.setBounds(120,260,80,30);
		backBtn.addActionListener(this);
		panel.add(backBtn);

		verifyBtn = new JButton("Verify");
		verifyBtn.setBounds(205,260,80,30);
		verifyBtn.addActionListener(this);
		panel.add(verifyBtn);

		submitBtn = new JButton("Change");
		submitBtn.setBounds(290,260,80,30);
		submitBtn.addActionListener(this);
		panel.add(submitBtn);

		this.add(panel);
	}		

	public void actionPerformed(ActionEvent ae){
		String command = ae.getActionCommand();

		if(command.equals(backBtn.getText())){
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(verifyBtn.getText()))
		{
			PersonRepo pr = new PersonRepo();
			Person person = pr.verifyPerson(idTF.getText(), Integer.parseInt(codeTF.getText()));
			
			if(person != null)
			{
				passTF.setText(person.getPassword());
				passTF.setEnabled(true);

				newPassTF.setEnabled(true);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Invaild Id or Verification Code!");
			}
		}
		else if(command.equals(submitBtn.getText())){
			PersonRepo pr = new PersonRepo();
			Person person = pr.verifyPerson(idTF.getText(), Integer.parseInt(codeTF.getText()));
			if(person != null && !newPassTF.getText().equals("") && !newPassTF.getText().equals(null)){
				person.setPassword(newPassTF.getText());
				pr.updatePerson(person);
				JOptionPane.showMessageDialog(this,"Password Changed! New Password : "+newPassTF.getText());

				idTF.setText("");
				codeTF.setText("");
				passTF.setText("");
				newPassTF.setText("");
				passTF.setEnabled(false);
				newPassTF.setEnabled(false);
			}
			else{
				JOptionPane.showMessageDialog(this, "Invaild Id or Password!");	
			}
		}
	}
}