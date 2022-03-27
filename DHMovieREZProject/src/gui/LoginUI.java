package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUI  extends JFrame {
	private JPanel panel;
	private JTextField inputID;
	private JPasswordField inputPW;
	private JButton signInButton;
	private JButton signUpButton;
	
	public LoginUI() {		
		super("��ȭ ���� �α���");
		
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		JLabel idLabel = new JLabel("���̵�");
		JLabel pwLabel = new JLabel("��й�ȣ");
		idLabel.setBounds(35, 25, 100, 50);
		pwLabel.setBounds(35, 90, 100, 50);
		idLabel.setFont(font);
		pwLabel.setFont(font);
		panel.add(idLabel);
		panel.add(pwLabel);
		
		// ���̵� �Է�
		inputID = new JTextField();
		inputID.setBounds(150, 25, 300, 50);
		panel.add(inputID);
		
		// ��й�ȣ �Է�
		inputPW = new JPasswordField();
		inputPW.setBounds(150, 90, 300, 50);
		panel.add(inputPW);
		
		signInButton = new JButton("�α���");
		signInButton.setBounds(100, 180, 300, 50);
		signInButton.setFont(font);
		panel.add(signInButton);
		
		signUpButton = new JButton("ȸ������");
		signUpButton.setBounds(125, 250, 250, 35);
		signUpButton.setFont(font);
		panel.add(signUpButton);
		// ��ư�� ������ ȸ������ UIâ�� ���
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUpUI signUpUI = new SignUpUI();
			}
		});
		
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 350);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		LoginUI loginUI = new LoginUI();
	}
}