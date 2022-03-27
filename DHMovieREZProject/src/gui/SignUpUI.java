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
		super("ȸ������");
		
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		idLabel = new JLabel("ID");
		idLabel.setBounds(25, 25, 100, 50);
		idLabel.setFont(font);
		panel.add(idLabel);
		
		// ID �Է�ĭ
		inputID = new JTextField();
		inputID.setBounds(150, 25, 300, 50);
		panel.add(inputID);
		
		pwLabel = new JLabel("PW");
		pwLabel.setBounds(25, 100, 100, 50);
		pwLabel.setFont(font);
		panel.add(pwLabel);
		
		// PW �Է�ĭ
		inputPW = new JPasswordField();
		inputPW.setBounds(150, 100, 300, 50);
		panel.add(inputPW);
		
		pwLabel2 = new JLabel("PW ��Ȯ��");
		pwLabel2.setBounds(25, 175, 100, 50);
		pwLabel2.setFont(font);
		panel.add(pwLabel2);
		
		// PW ���Է�ĭ
		inputPW2 = new JPasswordField();
		inputPW2.setBounds(150, 175, 300, 50);
		panel.add(inputPW2);
		
		nickLabel = new JLabel("�г���");
		nickLabel.setBounds(25, 250, 100, 50);
		nickLabel.setFont(font);
		panel.add(nickLabel);
		
		// �г��� �Է�ĭ
		inputNick = new JTextField();
		inputNick.setBounds(150, 250, 300, 50);
		panel.add(inputNick);
		
		birthdayLabel = new JLabel("�������");
		birthdayLabel.setBounds(25, 325, 100, 50);
		birthdayLabel.setFont(font);
		panel.add(birthdayLabel);
		
		// ������� �Է�ĭ, 6�ڸ� ���� �Է�
		// TODO: ������ �Էµǵ��� ó��
		inputBirth = new JTextField();
		inputBirth.setBounds(150, 325, 300, 50);
		panel.add(inputBirth);
		
		genderLabel = new JLabel("����");
		genderLabel.setFont(font);
		genderLabel.setBounds(25, 400, 100, 50);
		panel.add(genderLabel);
		
		String[] gender = { "����", "����" };
		genderBox = new JComboBox<String>(gender);
		genderBox.setFont(font);
		genderBox.setBounds(150, 400, 100, 50);
		panel.add(genderBox);
		
		phoneLabel = new JLabel("��ȣ");
		phoneLabel.setBounds(25, 475, 100, 50);
		phoneLabel.setFont(font);
		panel.add(phoneLabel);
		
		// ��ȣ �Է�ĭ
		// TODO: ������ �Էµǵ��� ó��, ���� ���̻��� "-" �ڵ�����?
		inputPhoneNum = new JTextField();
		inputPhoneNum.setBounds(150, 475, 300, 50);
		panel.add(inputPhoneNum);
		
		okButton = new JButton("����");
		okButton.setFont(font);
		okButton.setBounds(50, 575, 150, 50);
		panel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String password = new String(inputPW.getPassword());
				
				
				System.out.println("ID: " + inputID.getText() + 
						"\nPW: " + password + 
						"\n�г���: " + inputNick.getText() + 
						"\n�������: " + inputBirth.getText() + 
						"\n����: " + genderBox.getSelectedItem().toString() + 
						"\n��ȣ: " + inputPhoneNum.getText());
				
				dispose();
			}
		});
		
		cancelButton = new JButton("���");
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