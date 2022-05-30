package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

import DAO.UserDAO;
import DTO.UserDTO;

public class SignUpUI extends JFrame {
	private JPanel panel;
	private JLabel idLabel, pwLabel, pwLabel2, nickLabel, birthdayLabel, genderLabel, phoneLabel;
	private ButtonGroup genderGroup;
	private JRadioButton male, female;
	private JTextField inputID, inputNick, inputBirth, phone1, phone2, phone3;
	private JPasswordField inputPW, inputPW2;
	private JComboBox<String> carrierBox;
	private JButton okButton, cancelButton;
	private UserDTO userDTO;
	
	public SignUpUI() {
		super("회원가입");
		
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		// ID 입력칸
		idLabel = new JLabel("ID");
		idLabel.setBounds(25, 25, 100, 50);
		idLabel.setFont(font);
		panel.add(idLabel);
		
		inputID = new JTextField();
		inputID.setBounds(150, 25, 300, 50);
		panel.add(inputID);
		
		// PW 입력칸
		pwLabel = new JLabel("PW");
		pwLabel.setBounds(25, 100, 100, 50);
		pwLabel.setFont(font);
		panel.add(pwLabel);
		
		inputPW = new JPasswordField();
		inputPW.setBounds(150, 100, 300, 50);
		panel.add(inputPW);
		
		// PW 재입력칸
		pwLabel2 = new JLabel("PW 재확인");
		pwLabel2.setBounds(25, 175, 100, 50);
		pwLabel2.setFont(font);
		panel.add(pwLabel2);
		
		inputPW2 = new JPasswordField();
		inputPW2.setBounds(150, 175, 300, 50);
		panel.add(inputPW2);
		
		// 닉네임 입력칸
		nickLabel = new JLabel("닉네임");
		nickLabel.setBounds(25, 250, 100, 50);
		nickLabel.setFont(font);
		panel.add(nickLabel);
		
		inputNick = new JTextField();
		inputNick.setBounds(150, 250, 300, 50);
		panel.add(inputNick);
		
		// 생년월일 입력칸, 6자리 정수 입력
		birthdayLabel = new JLabel("생년월일");
		birthdayLabel.setBounds(25, 325, 100, 50);
		birthdayLabel.setFont(font);
		panel.add(birthdayLabel);
		
		inputBirth = new JTextField();
		inputBirth.setBounds(150, 325, 300, 50);
		panel.add(inputBirth);
		
		// 성별 입력칸
		genderLabel = new JLabel("성별");
		genderLabel.setFont(font);
		genderLabel.setBounds(25, 400, 100, 50);
		panel.add(genderLabel);
		
		genderGroup = new ButtonGroup();
		male = new JRadioButton("남성");
		female = new JRadioButton("여성");
		genderGroup.add(male);
		genderGroup.add(female);
		male.setFont(font);
		female.setFont(font);
		male.setBounds(150, 400, 100, 50);
		female.setBounds(300, 400, 100, 50);
		panel.add(male);
		panel.add(female);
		
		// 번호 입력칸
		phoneLabel = new JLabel("번호");
		phoneLabel.setBounds(25, 475, 100, 50);
		phoneLabel.setFont(font);
		panel.add(phoneLabel);
		
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
		
		okButton = new JButton("가입");
		okButton.setFont(font);
		okButton.setBounds(50, 575, 150, 50);
		panel.add(okButton);
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				// 유효성 검사 통과하지 못하면 return
				if (!isSatisfiedSignUp()) return;
				
				signUpFunc();
			}
		});
		
		cancelButton = new JButton("취소");
		cancelButton.setFont(font);
		cancelButton.setBounds(275, 575, 150, 50);
		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginUI();
				dispose();
			}
		});
		
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 700);
		setResizable(false);
		setVisible(true);
	}
		
	// 회원가입 메소드
	private void signUpFunc() {
		String password = new String(inputPW.getPassword());
		String gender;
		if (male.isSelected())
			gender = "m";
		else
			gender = "f";
		
		userDTO = new UserDTO(inputID.getText(),
				new String(inputPW.getPassword()),
				inputNick.getText(),
				inputBirth.getText(),
				gender,
				phone1.getText() + phone2.getText() + phone3.getText());
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.signUp(userDTO);
		
		// 테스트용 출력
		System.out.println("ID: " + userDTO.getId() + 
				"\nPW: " + userDTO.getPassword() + 
				"\n닉네임: " + userDTO.getNickname() + 
				"\n생년월일: " + userDTO.getbd() + 
				"\n성별: " + userDTO.getGender() + 
				"\n번호: " + carrierBox.getSelectedItem().toString() + " " +  userDTO.getCallNum());
		
		new LoginUI();
		dispose();
	}
	
	// 회원가입 유효성 검사 메서드
	private boolean isSatisfiedSignUp() {
		String regExpId = "^[a-zA-Z0-9]{4,20}$";
		String regExpPw = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$";
		String regExpNick = "^[a-zA-Z0-9가-힇]{3,15}$";
		
		if (!Pattern.matches(regExpId, inputID.getText()))
		{
			JOptionPane.showMessageDialog(null, "아이디는 영문 대/소문자와 숫자 4~20자리를 입력해주세요.", "ID", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!Pattern.matches(regExpPw, new String(inputPW.getPassword())))
		{
			JOptionPane.showMessageDialog(null, "비밀번호는 8자리~16자리 숫자, 영문, 특수문자를 1개 이상 포함해주세요.", "PW", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!(new String(inputPW.getPassword())).equals(new String(inputPW2.getPassword())))
		{
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "PW 재확인", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!Pattern.matches(regExpNick, inputNick.getText()))
		{
			JOptionPane.showMessageDialog(null, "닉네임은 특수문자 금지 3~15자 이내로 입력해주세요.", "닉네임", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!Pattern.matches("^[1-2][0-9][0-9][0-9][0-1][0-9][0-3][0-9]$", inputBirth.getText()))
		{
			JOptionPane.showMessageDialog(null, "8자리 숫자로 올바른 생년월일을 입력해 주세요.", "생년월일", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!male.isSelected() && !female.isSelected())
		{
			JOptionPane.showMessageDialog(null, "성별을 선택해 주세요.", "성별", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!Pattern.matches("^[0-9]{3}$", phone1.getText()) || 
				!Pattern.matches("^[0-9]{4}$", phone2.getText()) ||
				!Pattern.matches("^[0-9]{4}$", phone3.getText()))
		{
			JOptionPane.showMessageDialog(null, "올바른 번호를 입력해 주세요.", "번호", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}
		
	public static void main(String[] args) {
		new SignUpUI();
	}
}