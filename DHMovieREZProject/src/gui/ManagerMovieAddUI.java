package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

import DAO.MovieDAO;
import DTO.MovieDTO;

public class ManagerMovieAddUI extends JFrame {
	private JPanel panel;
	private JLabel titleLabel, runningTimeLabel;
	private JTextField titleField, runningTimeField;
	private JButton addButton, cancelButton;
	private MovieDAO movieDAO;
	
	public ManagerMovieAddUI() {
		super("영화 추가");
		panel = new JPanel();
		panel.setLayout(null);
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		movieDAO = MovieDAO.getInstance();
		
		titleLabel = new JLabel("영화 제목");
		titleLabel.setBounds(25, 25, 100, 50);
		titleLabel.setFont(font);
		panel.add(titleLabel);
		
		titleField = new JTextField();
		titleField.setBounds(150, 25, 300, 50);
		panel.add(titleField);
		
		runningTimeLabel = new JLabel("상영 시간");
		runningTimeLabel.setBounds(25, 100, 100, 50);
		runningTimeLabel.setFont(font);
		panel.add(runningTimeLabel);
		
		runningTimeField = new JTextField();
		runningTimeField.setBounds(150, 100, 300, 50);
		panel.add(runningTimeField);
		
		addButton = new JButton("추가");
		addButton.setFont(font);
		addButton.setBounds(50, 200, 150, 50);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!checkMovie()) return;
				
				movieDAO.addMovie(new MovieDTO(titleField.getText(), runningTimeField.getText()));
				
				JOptionPane.showMessageDialog(null, "영화 추가가 완료되었습니다.", "영화 추가", JOptionPane.INFORMATION_MESSAGE);
				
				new ManagerMovieUI();
				dispose();
			}
		});
		add(addButton);
		
		cancelButton = new JButton("취소");
		cancelButton.setFont(font);
		cancelButton.setBounds(275, 200, 150, 50);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerMovieUI();
				dispose();
			}
		});
		add(cancelButton);
		
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 325);
		setResizable(false);
		setVisible(true);
	}
	
	// 유효성 검사
	private boolean checkMovie() {
		String regExpTitle = "^[A-Za-z0-9가-힇]{2,30}$";
		String regExpTime = "^[0-9]{2}[0-5][0-9][0-5][0-9]";
		
		if (!Pattern.matches(regExpTitle, titleField.getText()))
		{
			JOptionPane.showMessageDialog(null, "영화 제목은 2~30글자로 입력해주세요.", "Title", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		if (!Pattern.matches(regExpTime, runningTimeField.getText()))
		{
			JOptionPane.showMessageDialog(null, "상영 시간은 6자리 숫자로 올바르게 입력해주세요.", "RunningTime", JOptionPane.INFORMATION_MESSAGE);
			return false;			
		}
		
		return true;
		
	}
	
	public static void main(String[] args) {
		new ManagerMovieAddUI();
	}
}
