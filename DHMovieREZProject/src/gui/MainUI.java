package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import DTO.MovieDTO;
import DTO.UserDTO;
import DAO.MovieDAO;

public class MainUI extends JFrame {
	private JPanel panel;
	private JScrollPane listScrollPane;
	private JButton rezInfo, rezBt;
	private JRadioButton[] dayRadio = new JRadioButton[7];
	private ButtonGroup weekGroup;
	private ArrayList<JRadioButton> rezDays = new ArrayList<>();
	private JComboBox<String> rezTime;
	private JLabel nicknameLabel;
	private UserDTO userDTO;
	private MovieDAO movieDAO;
	private MovieDTO[] movies;
	private String[] tableHeader = {"Title", "runningTime"};
	Vector<String> movieVector;
	private DefaultTableModel model;
	private JTable table;
	
	public MainUI(UserDTO userDTO) {
		super("DH 영화예매");
		this.userDTO = userDTO;
		movieDAO = MovieDAO.getInstance();
		
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		panel = new JPanel();
		panel.setLayout(null);
		
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
			model.addRow(movieVector);
		}
		add(listScrollPane);
		
		/*for (int i = 0; i < images.length; i++)
		{
			Image image = images[i].getImage();
			Image sizedImage = image.getScaledInstance(250,  250,  Image.SCALE_SMOOTH);
			images[i] = new ImageIcon(sizedImage);
		}*/
		
		/*movieList = new JList<>(images);
		movieList.setVisibleRowCount(1);
		movieList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		movieList.setBounds(75, 75, 700, 200);
		listScrollPane = new JScrollPane(movieList, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listScrollPane.setBounds(75, 50, 700, 270);
		panel.add(listScrollPane);*/
		
		nicknameLabel = new JLabel(userDTO.getNickname() + " 님");
		nicknameLabel.setFont(font);
		nicknameLabel.setHorizontalAlignment(JLabel.CENTER);
		nicknameLabel.setBounds(75, 350, 150, 100);
		//nicknameLabel.setBorder(new javax.swing.border.LineBorder(Color.red, 3, true));
		panel.add(nicknameLabel);
		
		rezInfo = new JButton("예약 정보");
		rezInfo.setBounds(75, 450, 150, 150);
		rezInfo.setFont(font);
		rezInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RezInfo(userDTO);
				dispose();
			}
		});
		panel.add(rezInfo);
		
		weekGroup = new ButtonGroup();
		LocalDate now = LocalDate.now();
		String[] days = {"월", "화", "수", "목", "금", "토", "일"};
		
		for (int i = 0; i < days.length; i++)
		{
			dayRadio[i] = new JRadioButton(Integer.toString(now.getDayOfMonth()) + 
					"(" + days[(now.getDayOfWeek().getValue() - 1)] + ")");
			dayRadio[i].setFont(font);
			dayRadio[i].setBounds(250 + i * 90, 380, 85, 50);
			rezDays.add(dayRadio[i]);
			
			weekGroup.add(dayRadio[i]);
			panel.add(dayRadio[i]);
			
			now = now.plusDays(1);
		}
		panel.add(rezInfo);
		
		rezTime = new JComboBox<>();
		for (int i = 0; i < 8; i++)
		{
			rezTime.addItem(8 + i * 2 + ":00");
		}
		rezTime.setFont(font);
		rezTime.setBounds(315, 475, 100, 50);
		panel.add(rezTime);
		
		rezBt = new JButton("예매하기");
		rezBt.setFont(font);
		rezBt.setBounds(560, 460, 200, 120);
		rezBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1)
				{
					JOptionPane.showMessageDialog(null, "영화를 선택해 주세요.", "영화", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				for (int i = 0; i < days.length; i++)
				{
					// 선택한 날짜와 시간 정보를 가지고 좌석 선택 화면으로 이동
					if (dayRadio[i].isSelected())
					{
						// 선택한 날짜를 yyyymmdd 형식의 문자열로
						LocalDate day = LocalDate.now();
						day = day.plusDays(i);
						String dayStr = Integer.toString(day.getYear());
						if (day.getMonthValue() < 10)
							dayStr += "0" + Integer.toString(day.getMonthValue());
						else
							dayStr += Integer.toString(day.getMonthValue());
						if (day.getDayOfMonth() < 10)
							dayStr += "0" + Integer.toString(day.getDayOfMonth());
						else
							dayStr += Integer.toString(day.getDayOfMonth());
						
						// 선택한 시간을 hhmmss 형식의 문자열로
						String[] time = rezTime.getSelectedItem().toString().split(":");
						String timeStr = "";
						if (time[0].length() < 2)
							timeStr += "0";
						timeStr += time[0] + ":00:00";
						
						// 선택한 영화의 제목
						String title = (String)table.getValueAt(index, 0);
						
						new SeatSelection(userDTO.getId(), title, dayStr, timeStr, userDTO);
						dispose();
						return;
					}
				}
				
				JOptionPane.showMessageDialog(null, "날짜를 선택해 주세요.", "날짜", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(rezBt);
		
		add(panel);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainUI(new UserDTO(null, null, "NICK", "00000000", null, null));
	}
}
