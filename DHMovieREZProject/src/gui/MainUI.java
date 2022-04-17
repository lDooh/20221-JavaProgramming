package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainUI extends JFrame {
	private JPanel panel;
	private ImageIcon[] images = { new ImageIcon("src/image/movie1.jpg"),
			new ImageIcon("src/image/movie2.jpg"),
			new ImageIcon("src/image/movie3.png"),
			new ImageIcon("src/image/movie4.jpg"),
			new ImageIcon("src/image/movie5.jpg")
	};
	private JList<ImageIcon> movieList;
	private JScrollPane listScrollPane;
	private JButton rezInfo, rezBt;
	private ButtonGroup weekGroup;
	private ArrayList<JRadioButton> rezDays = new ArrayList<>();
	private JComboBox<String> rezTime;
	
	public MainUI() {
		super("DH 영화예매");
		
		Font font = new Font("Slab Serif", Font.BOLD, 20);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		for (int i = 0; i < images.length; i++)
		{
			Image image = images[i].getImage();
			Image sizedImage = image.getScaledInstance(250,  250,  Image.SCALE_SMOOTH);
			images[i] = new ImageIcon(sizedImage);
		}
		
		movieList = new JList<>(images);
		movieList.setVisibleRowCount(1);
		movieList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		movieList.setBounds(75, 75, 700, 200);
		listScrollPane = new JScrollPane(movieList, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listScrollPane.setBounds(75, 50, 700, 270);
		panel.add(listScrollPane);
		
		rezInfo = new JButton("예약 정보");
		rezInfo.setBounds(75, 400, 150, 150);
		rezInfo.setFont(font);
		panel.add(rezInfo);
		
		weekGroup = new ButtonGroup();
		LocalDate now = LocalDate.now();
		String[] days = {"월", "화", "수", "목", "금", "토", "일"};
		for (int i = 0; i < days.length; i++)
		{
			JRadioButton rb = new JRadioButton(Integer.toString(now.getDayOfMonth()) + 
					"(" + days[(now.getDayOfWeek().getValue() - 1)] + ")");
			rb.setFont(font);
			rb.setBounds(250 + i * 90, 380, 85, 50);
			rezDays.add(rb);
			
			weekGroup.add(rb);
			panel.add(rb);
			
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
		panel.add(rezBt);
		
		add(panel);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainUI();
	}
}
