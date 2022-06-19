package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.table.*;
import DAO.UserDAO;
import DTO.UserDTO;

public class ManagerUserUI extends JFrame {
	private JPanel panel;
	private String[] tableHeader = {"ID", "nickname", "birthday", "gender", "callNum"};
	Vector<String> userVector;
	private UserDAO userDAO;
	private UserDTO[] usersDTO;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField searchField;
	private JButton searchButton, modifyButton, deleteButton, backButton;
	
	public ManagerUserUI() {
		super("회원관리");
		panel = new JPanel();
		panel.setLayout(null);
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		userDAO = UserDAO.getInstance();
		usersDTO = userDAO.getUsers();

		model = new DefaultTableModel(tableHeader, 0);
		table = new JTable(model);
		//table.setFont(font);
		
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 800, 300);
		
		for (int i = 0; i < usersDTO.length; i++)
		{
			userVector = new Vector<>();
			userVector.add(usersDTO[i].getId());
			userVector.add(usersDTO[i].getNickname());
			userVector.add(usersDTO[i].getbd());
			userVector.add(usersDTO[i].getGender());
			userVector.add(usersDTO[i].getCallNum());
			model.addRow(userVector);
		}
		add(scrollPane);
		
		searchField = new JTextField();
		searchField.setFont(font);
		searchField.setBounds(100, 400, 250, 50);
		add(searchField);
		
		searchButton = new JButton("검색");
		searchButton.setFont(font);
		searchButton.setBounds(100, 500, 150, 50);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				usersDTO = userDAO.getUsersByNick(searchField.getText());
				for (int i = 0; i < usersDTO.length; i++)
				{
					userVector = new Vector<>();
					userVector.add(usersDTO[i].getId());
					userVector.add(usersDTO[i].getNickname());
					userVector.add(usersDTO[i].getbd());
					userVector.add(usersDTO[i].getGender());
					userVector.add(usersDTO[i].getCallNum());
					model.addRow(userVector);
				}
			}
		});
		add(searchButton);
		
		modifyButton = new JButton("수정");
		modifyButton.setFont(font);
		modifyButton.setBounds(400, 500, 150, 50);
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				UserDTO mUserDTO = userDAO.getUserDTO((String)table.getValueAt(index, 0));
				mUserDTO.setNickname((String)table.getValueAt(index, 1));
				mUserDTO.setbd((String)table.getValueAt(index, 2));
				userDAO.modifyUser(mUserDTO);
				
				new ManagerUserUI();
				dispose();
			}
		});
		add(modifyButton);
		
		deleteButton = new JButton("삭제");
		deleteButton.setFont(font);
		deleteButton.setBounds(650, 400, 150, 50);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				//System.out.println((String)table.getValueAt(index, 0));
				userDAO.deleteUser((String)table.getValueAt(index, 0));

				new ManagerUserUI();
				dispose();
			}
		});
		add(deleteButton);
		
		backButton = new JButton("뒤로가기");
		backButton.setFont(font);
		backButton.setBounds(650, 500, 150, 50);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerUI();
				dispose();
			}
		});
		add(backButton);
		
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ManagerUserUI();
	}
}