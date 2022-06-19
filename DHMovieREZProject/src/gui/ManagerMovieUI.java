package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import DAO.MovieDAO;
import DAO.RezDAO;
import DTO.MovieDTO;
import DTO.RezDTO;

public class ManagerMovieUI extends JFrame {
	private JPanel panel;
	private RezDAO rezDAO;
	private MovieDAO movieDAO;
	private MovieDTO[] movies;
	private JScrollPane listScrollPane;
	private String[] tableHeader = {"Title", "runningTime", "예약 현황"};
	Vector<String> movieVector;
	private DefaultTableModel model;
	private JTable table;
	private JButton backButton, deleteButton, addButton;
	
	public ManagerMovieUI() {
		super("DH 영화예매 영화 관리");
		panel = new JPanel();
		panel.setLayout(null);
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		movieDAO = MovieDAO.getInstance();
		rezDAO = RezDAO.getInstance();
		
		movies = movieDAO.getMovies();
		model = new DefaultTableModel(tableHeader, 0);
		table = new JTable(model);
		listScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listScrollPane.setBounds(75, 75, 700, 200);
		
		for (int i = 0; i < movies.length; i++)
		{
			movieVector = new Vector<>();
			movieVector.add(movies[i].getTitle());
			movieVector.add(movies[i].getRunningTime().substring(0, 2) + ":"
					+ movies[i].getRunningTime().substring(2, 4) + ":"
					+ movies[i].getRunningTime().substring(4, 6));
			movieVector.add(Integer.toString(rezDAO.countRez(movies[i].getTitle())));
			model.addRow(movieVector);
		}
		add(listScrollPane);
		
		addButton = new JButton("영화 추가");
		addButton.setFont(font);
		addButton.setBounds(100, 400, 150, 50);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerMovieAddUI();
				dispose();
			}
		});
		add(addButton);
		
		deleteButton = new JButton("삭제");
		deleteButton.setFont(font);
		deleteButton.setBounds(650, 400, 150, 50);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1)
				{
					JOptionPane.showMessageDialog(null, "삭제할 티켓을 선택해 주세요.", "티켓 선택", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				if (JOptionPane.showConfirmDialog(null, "영화를 삭제하시겠습니까?", "영화 삭제"
						, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)== 0)
				{
					movieDAO.deleteMovie((String)table.getValueAt(index, 0));
					model.removeRow(index);
					
					JOptionPane.showMessageDialog(null, "영화가 삭제되었습니다.", "영화 삭제", JOptionPane.INFORMATION_MESSAGE);
					new ManagerMovieUI();
					dispose();
				}
				else
				{
					return;
				}
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
		new ManagerMovieUI();
	}
}
