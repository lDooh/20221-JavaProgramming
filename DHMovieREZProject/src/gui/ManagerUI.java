package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.table.*;
import DAO.UserDAO;
import DTO.UserDTO;

public class ManagerUI extends JFrame {
	private JPanel panel;
	private String[] tableHeader = {"ID", "Password", "nickname", "birthday", "gender", "callNum"};
	Vector<String> userVector;
	private UserDAO userDAO;
	private UserDTO[] usersDTO;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton backButton, modifyButton, deleteButton;
	
	public ManagerUI() {
		super("회원관리");
		panel = new JPanel();
		panel.setLayout(null);
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		userDAO = UserDAO.getInstance();
		usersDTO = userDAO.getUsers();

		model = new DefaultTableModel(tableHeader, 0);
		table = new JTable(model);
		//table.setFont(font);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 800, 300);
		
		for (int i = 0; i < usersDTO.length; i++)
		{
			userVector = new Vector<>();
			userVector.add(usersDTO[i].getId());
			userVector.add(usersDTO[i].getPassword());
			userVector.add(usersDTO[i].getNickname());
			userVector.add(usersDTO[i].getbd());
			userVector.add(usersDTO[i].getGender());
			userVector.add(usersDTO[i].getCallNum());
			model.addRow(userVector);
		}
		add(scrollPane);
		
		deleteButton = new JButton("삭제");
		deleteButton.setFont(font);
		deleteButton.setBounds(650, 400, 150, 50);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				//System.out.println((String)table.getValueAt(index, 0));
				userDAO.deleteUser((String)table.getValueAt(index, 0));
				model.removeRow(index);
			}
		});
		add(deleteButton);
		
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ManagerUI();
	}
}