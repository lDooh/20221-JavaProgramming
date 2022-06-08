package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import DAO.RezDAO;
import DTO.UserDTO;
import DTO.RezDTO;

public class RezInfoUI extends JFrame {
	private JPanel panel;
	private RezDAO rezDAO;
	private UserDTO userDTO;
	private RezDTO[] rezDTO;
	private String[] tableHeader = {"title", "예약 날짜", "예약 시간", "좌석 번호"};
	Vector<String> rezVector;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton deleteButton;
	
	public RezInfoUI(UserDTO userDTO) {
		super("예약 정보");
		//this.userDTO = userDTO;
		
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		panel = new JPanel();
		panel.setLayout(null);
		
		rezDAO = RezDAO.getInstance();
		rezDTO = rezDAO.getRezs(userDTO.getId());
		
		model = new DefaultTableModel(tableHeader, 0);
		table = new JTable(model);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 800, 300);
		
		for (int i = 0; i < rezDTO.length; i++)
		{
			rezVector = new Vector<>();
			rezVector.add(rezDTO[i].getTitle());
			rezVector.add(rezDTO[i].getMDate());
			rezVector.add(rezDTO[i].getMTime());
			rezVector.add(rezDTO[i].getSeatNum());
			model.addRow(rezVector);
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
				rezDAO.deleteRez(new RezDTO(userDTO.getId(),
											(String)table.getValueAt(index, 0),
											(String)table.getValueAt(index, 1),
											(String)table.getValueAt(index, 2),
											(String)table.getValueAt(index, 3)));
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
		new RezInfoUI(new UserDTO("test", null, "NICK", "00000000", null, null));
	}
}