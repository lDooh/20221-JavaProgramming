package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;

public class SignUpUI extends JFrame {
	private JPanel panel;
	private JLabel idLabel, pwLabel, pwLabel2, nickLabel, birthdayLabel, genderLabel, phoneLabel;
	private JComboBox genderBox;
	private JTextField inputID, inputNick, inputBirth, inputPhoneNum;
	private JPasswordField inputPW, inputPW2;
	private JButton okButton, cancelButton;
	
	public SignUpUI() {
		super("회원가입");
		
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		idLabel = new JLabel("ID");
		idLabel.setBounds(25, 25, 100, 50);
		idLabel.setFont(font);
		panel.add(idLabel);
		
		// ID 입력칸
		inputID = new JTextField();
		inputID.setBounds(150, 25, 300, 50);
		panel.add(inputID);
		
		pwLabel = new JLabel("PW");
		pwLabel.setBounds(25, 100, 100, 50);
		pwLabel.setFont(font);
		panel.add(pwLabel);
		
		// PW 입력칸
		inputPW = new JPasswordField();
		inputPW.setBounds(150, 100, 300, 50);
		panel.add(inputPW);
		
		pwLabel2 = new JLabel("PW 재확인");
		pwLabel2.setBounds(25, 175, 100, 50);
		pwLabel2.setFont(font);
		panel.add(pwLabel2);
		
		// PW 재입력칸
		inputPW2 = new JPasswordField();
		inputPW2.setBounds(150, 175, 300, 50);
		panel.add(inputPW2);
		
		nickLabel = new JLabel("닉네임");
		nickLabel.setBounds(25, 250, 100, 50);
		nickLabel.setFont(font);
		panel.add(nickLabel);
		
		// 닉네임 입력칸
		inputNick = new JTextField();
		inputNick.setBounds(150, 250, 300, 50);
		panel.add(inputNick);
		
		birthdayLabel = new JLabel("생년월일");
		birthdayLabel.setBounds(25, 325, 100, 50);
		birthdayLabel.setFont(font);
		panel.add(birthdayLabel);
		
		// 생년월일 입력칸, 6자리 정수 입력
		// TODO: 정수만 입력되도록 처리
		inputBirth = new JTextField();
		inputBirth.setBounds(150, 325, 300, 50);
		panel.add(inputBirth);
		
		genderLabel = new JLabel("성별");
		genderLabel.setFont(font);
		genderLabel.setBounds(25, 400, 100, 50);
		panel.add(genderLabel);
		
		String[] gender = { "남자", "여자" };
		genderBox = new JComboBox<String>(gender);
		genderBox.setFont(font);
		genderBox.setBounds(150, 400, 100, 50);
		panel.add(genderBox);
		
		phoneLabel = new JLabel("번호");
		phoneLabel.setBounds(25, 475, 100, 50);
		phoneLabel.setFont(font);
		panel.add(phoneLabel);
		
		// 번호 입력칸
		// TODO: 정수만 입력되도록 처리, 숫자 사이사이 "-" 자동삽입?
		inputPhoneNum = new JTextField();
		inputPhoneNum.setBounds(150, 475, 300, 50);
		panel.add(inputPhoneNum);
		
		okButton = new JButton("가입");
		okButton.setFont(font);
		okButton.setBounds(50, 575, 150, 50);
		panel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String password = new String(inputPW.getPassword());
				
				
				System.out.println("ID: " + inputID.getText() + 
						"\nPW: " + password + 
						"\n닉네임: " + inputNick.getText() + 
						"\n생년월일: " + inputBirth.getText() + 
						"\n성별: " + genderBox.getSelectedItem().toString() + 
						"\n번호: " + inputPhoneNum.getText());
				
				dispose();
			}
		});
		
		cancelButton = new JButton("취소");
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
}