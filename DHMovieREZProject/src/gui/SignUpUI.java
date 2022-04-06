package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpUI extends JFrame {
	private JPanel panel;
	private JLabel idLabel, pwLabel, pwLabel2, nickLabel, birthdayLabel, genderLabel, phoneLabel;
	private ButtonGroup genderGroup;
	private JRadioButton male, female;
	private JTextField inputID, inputNick, inputBirth, phone1, phone2, phone3;
	private JPasswordField inputPW, inputPW2;
	private JComboBox<String> carrierBox;
	private JButton okButton, cancelButton;
	
	public SignUpUI() {
		super("È¸¿ø°¡ÀÔ");
		
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		idLabel = new JLabel("ID");
		idLabel.setBounds(25, 25, 100, 50);
		idLabel.setFont(font);
		panel.add(idLabel);
		
		// ID ÀÔ·ÂÄ­
		inputID = new JTextField();
		inputID.setBounds(150, 25, 300, 50);
		panel.add(inputID);
		
		pwLabel = new JLabel("PW");
		pwLabel.setBounds(25, 100, 100, 50);
		pwLabel.setFont(font);
		panel.add(pwLabel);
		
		// PW ÀÔ·ÂÄ­
		inputPW = new JPasswordField();
		inputPW.setBounds(150, 100, 300, 50);
		panel.add(inputPW);
		
		pwLabel2 = new JLabel("PW ÀçÈ®ÀÎ");
		pwLabel2.setBounds(25, 175, 100, 50);
		pwLabel2.setFont(font);
		panel.add(pwLabel2);
		
		// PW ÀçÀÔ·ÂÄ­
		inputPW2 = new JPasswordField();
		inputPW2.setBounds(150, 175, 300, 50);
		panel.add(inputPW2);
		
		nickLabel = new JLabel("´Ð³×ÀÓ");
		nickLabel.setBounds(25, 250, 100, 50);
		nickLabel.setFont(font);
		panel.add(nickLabel);
		
		// ´Ð³×ÀÓ ÀÔ·ÂÄ­
		inputNick = new JTextField();
		inputNick.setBounds(150, 250, 300, 50);
		panel.add(inputNick);
		
		birthdayLabel = new JLabel("»ý³â¿ùÀÏ");
		birthdayLabel.setBounds(25, 325, 100, 50);
		birthdayLabel.setFont(font);
		panel.add(birthdayLabel);
		
		// »ý³â¿ùÀÏ ÀÔ·ÂÄ­, 6ÀÚ¸® Á¤¼ö ÀÔ·Â
		inputBirth = new JTextField();
		inputBirth.setBounds(150, 325, 300, 50);
		panel.add(inputBirth);
		
		// ¼ºº° ¼±ÅÃÄ­
		genderLabel = new JLabel("¼ºº°");
		genderLabel.setFont(font);
		genderLabel.setBounds(25, 400, 100, 50);
		panel.add(genderLabel);
		
		genderGroup = new ButtonGroup();
		male = new JRadioButton("³²ÀÚ");
		female = new JRadioButton("¿©ÀÚ");
		genderGroup.add(male);
		genderGroup.add(female);
		male.setFont(font);
		female.setFont(font);
		male.setBounds(150, 400, 100, 50);
		female.setBounds(300, 400, 100, 50);
		panel.add(male);
		panel.add(female);
		
		phoneLabel = new JLabel("¹øÈ£");
		phoneLabel.setBounds(25, 475, 100, 50);
		phoneLabel.setFont(font);
		panel.add(phoneLabel);
		
		// ¹øÈ£ ÀÔ·ÂÄ­
		String[] mobileCarrier = { "SKT", "KT", "LGU+" };
		carrierBox = new JComboBox<String>(mobileCarrier);
		carrierBox.setFont(font);
		carrierBox.setBounds(150, 475, 75, 50);
		panel.add(carrierBox);
		
		phone1 = new JTextField();
		phone1.setFont(font);
		phone1.setBounds(245, 475, 50, 50);
		panel.add(phone1);
		
		phone2 = new JTextField();
		phone2.setFont(font);
		phone2.setBounds(310, 475, 65, 50);
		panel.add(phone2);
		
		phone3 = new JTextField();
		phone3.setFont(font);
		phone3.setBounds(390, 475, 65, 50);
		panel.add(phone3);
		
		okButton = new JButton("°¡ÀÔ");
		okButton.setFont(font);
		okButton.setBounds(50, 575, 150, 50);
		panel.add(okButton);
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				if (!isSatisfiedSignUp()) return;
				
				testFunc();
			}
		});
		
		cancelButton = new JButton("Ãë¼Ò");
		cancelButton.setFont(font);
		cancelButton.setBounds(275, 575, 150, 50);
		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 700);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SignUpUI();
	}
	
	// È¸¿ø°¡ÀÔ Å×½ºÆ®¿ë
	private void testFunc() {
		String password = new String(inputPW.getPassword());
		
		String gender;
		if (male.isSelected()) gender = male.getText();
		else gender = female.getText();
		
		System.out.println("ID: " + inputID.getText() + 
				"\nPW: " + password + 
				"\n´Ð³×ÀÓ: " + inputNick.getText() + 
				"\n»ý³â¿ùÀÏ: " + inputBirth.getText() + 
				"\n¼ºº°: " + gender + 
				"\n¹øÈ£: " + carrierBox.getSelectedItem().toString() + " " + 
				phone1.getText() + "-" + phone2.getText() + "-" + phone3.getText());
		
		dispose();
	}
	
	// È¸¿ø°¡ÀÔ À¯È¿¼º °Ë»ç ¸Þ¼­µå
	private boolean isSatisfiedSignUp() {
		String regExpId = "^[a-zA-Z0-9]{4,20}$";
		String regExpPw = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$";
		String regExpNick = "^[a-zA-Z0-9¤¡-¤¾¤¿-¤Ó°¡-ÆR]{3,15}$";
		
		if (!Pattern.matches(regExpId, inputID.getText()))
		{
			JOptionPane.showMessageDialog(null,  "¾ÆÀÌµð´Â ¿µ¹® ´ë/¼Ò¹®ÀÚ¿Í ¼ýÀÚ 4~20ÀÚ¸®¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.", "ID", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (!Pattern.matches(regExpPw, new String(inputPW.getPassword())))
		{
			JOptionPane.showMessageDialog(null,  "ºñ¹Ð¹øÈ£´Â 8ÀÚ¸®~16ÀÚ¸® ¼ýÀÚ, ¿µ¹®, Æ¯¼ö¹®ÀÚ¸¦ 1°³ ÀÌ»ó Æ÷ÇÔÇØÁÖ¼¼¿ä.", "PW", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (!(new String(inputPW.getPassword())).equals(new String(inputPW2.getPassword())))
		{
			JOptionPane.showMessageDialog(null,  "ºñ¹Ð¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.", "PW ÀçÈ®ÀÎ", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (!Pattern.matches(regExpNick,  inputNick.getText()))
		{
			JOptionPane.showMessageDialog(null,  "´Ð³×ÀÓÀº Æ¯¼ö¹®ÀÚ ±ÝÁö 3~15ÀÚ ÀÌ³»·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.", "´Ð³×ÀÓ", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (!Pattern.matches("^[0-9]{6}$", inputBirth.getText()))
		{
			JOptionPane.showMessageDialog(null,  "6ÀÚ¸® ¼ýÀÚ·Î »ý³â¿ùÀÏÀ» ÀÔ·ÂÇØ ÁÖ¼¼¿ä.", "»ý³â¿ùÀÏ", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (!male.isSelected() && !female.isSelected())
		{
			JOptionPane.showMessageDialog(null,  "¼ºº°À» ¼±ÅÃÇØ ÁÖ¼¼¿ä.", "¼ºº°", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (!Pattern.matches("^[0-9]{3}$", phone1.getText()) || 
				!Pattern.matches("^[0-9]{4}$", phone2.getText()) ||
				!Pattern.matches("^[0-9]{4}$", phone3.getText()))
		{
			JOptionPane.showMessageDialog(null,  "¿Ã¹Ù¸¥ ¹øÈ£¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä.", "¹øÈ£", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		return true;
	}
}