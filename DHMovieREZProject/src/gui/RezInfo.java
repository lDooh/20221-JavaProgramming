package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import DTO.UserDTO;
import DAO.RezDAO;
import DTO.RezDTO;

public class RezInfo extends JFrame {
	private JPanel panel;
	private String[] tableHeader = {"title", "date", "time", "seat"};
	Vector<String> userVector;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private UserDTO userDTO;
	private RezDAO rezDAO;
	private RezDTO[] rezDTO;
	private JButton backButton, cancelButton;
	
	public RezInfo(UserDTO userDTO) {
		super("DH 영화예매");
		this.userDTO = userDTO;
		rezDAO = RezDAO.getInstance();
		rezDTO = rezDAO.getRezs(userDTO.getId());
		
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		panel = new JPanel();
		panel.setLayout(null);
		
		model = new DefaultTableModel(tableHeader, 0);
		table = new JTable(model);
		//table.setFont(font);
		
		scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 800, 300);
		
		for (int i = 0; i < rezDTO.length; i++)
		{
			userVector = new Vector<>();
			userVector.add(rezDTO[i].getTitle());
			userVector.add(rezDTO[i].getMDate());
			userVector.add(rezDTO[i].getMTime());
			userVector.add(rezDTO[i].getSeatNum());
			model.addRow(userVector);
		}
		add(scrollPane);
		
		cancelButton = new JButton("예매 취소");
		cancelButton.setFont(font);
		cancelButton.setBounds(650, 400, 150, 50);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				
				if (index == -1)
				{
					JOptionPane.showMessageDialog(null, "삭제할 티켓을 선택해 주세요.", "티켓 선택", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				if (JOptionPane.showConfirmDialog(null, "예매를 취소하시겠습니까?", "예매 취소"
						, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)== 0)
				{
					rezDAO.deleteRez(new RezDTO(userDTO.getId()
							, (String)table.getValueAt(index, 0)
							, (String)table.getValueAt(index, 1)
							, (String)table.getValueAt(index, 2)
							, (String)table.getValueAt(index, 3)));
					
					JOptionPane.showMessageDialog(null, "예매 취소가 완료되었습니다.", "예매 취소", JOptionPane.INFORMATION_MESSAGE);
					new RezInfo(userDTO);
					dispose();
				}
				else
				{
					return;
				}
			}
		});
		add(cancelButton);
		
		backButton = new JButton("뒤로가기");
		backButton.setFont(font);
		backButton.setBounds(650, 500, 150, 50);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainUI(userDTO);
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
}
