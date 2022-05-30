package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import DAO.UserDAO;

public class LoginUI  extends JFrame {
	private JPanel panel;
	private JTextField inputID;
	private JPasswordField inputPW;
	private JButton signInButton;
	private JButton signUpButton;
	
	public LoginUI() {		
		super("영화 예매 로그인");
		
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		JLabel idLabel = new JLabel("아이디");
		JLabel pwLabel = new JLabel("비밀번호");
		idLabel.setBounds(35, 25, 100, 50);
		pwLabel.setBounds(35, 90, 100, 50);
		idLabel.setFont(font);
		pwLabel.setFont(font);
		panel.add(idLabel);
		panel.add(pwLabel);
		
		// 아이디 입력
		inputID = new JTextField();
		inputID.setBounds(150, 25, 300, 50);
		panel.add(inputID);
		
		// 비밀번호 입력
		inputPW = new JPasswordField();
		inputPW.setBounds(150, 90, 300, 50);
		panel.add(inputPW);
		
		signInButton = new JButton("로그인");
		signInButton.setBounds(100, 180, 300, 50);
		signInButton.setFont(font);
		panel.add(signInButton);
		signInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDAO userDAO = UserDAO.getInstance();
				
				int login = userDAO.signIn(inputID.getText(), new String(inputPW.getPassword()));
				if (login == 1)
				{
					new MainUI(userDAO.getUserDTO(inputID.getText()));
					//new ManagerUI();
					dispose();
				}
				else if (login == -1)
				{
					// ID 없음
					JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.", "로그인 오류", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (login == 0)
				{
					// PW 틀림
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "로그인 오류", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		signUpButton = new JButton("회원가입");
		signUpButton.setBounds(125, 250, 250, 35);
		signUpButton.setFont(font);
		panel.add(signUpButton);
		// 버튼을 누르면 회원가입 UI창을 띄움
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUpUI signUpUI = new SignUpUI();
				dispose();
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