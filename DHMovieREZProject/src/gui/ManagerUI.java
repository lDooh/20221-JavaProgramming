package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerUI extends JFrame{
	private JPanel panel;
	private JButton userButton, movieButton;
	
	public ManagerUI() {
		super("DH 영화예매 관리자 메뉴");
		panel = new JPanel();
		panel.setLayout(null);
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		userButton = new JButton("회원관리");
		userButton.setBounds(50, 50, 150, 150);
		userButton.setFont(font);
		userButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerUserUI();
				dispose();
			}
		});
		add(userButton);
		
		movieButton = new JButton("영화 관리");
		movieButton.setBounds(250, 50, 150, 150);
		movieButton.setFont(font);
		movieButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerMovieUI();
				dispose();
			}
		});
		add(movieButton);
		
		add(panel);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(475, 300);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ManagerUI();
	}
}
