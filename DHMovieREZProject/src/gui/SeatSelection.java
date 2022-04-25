package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class SeatSelection extends JFrame {
	private JPanel panel;
	private JCheckBox seat[][];
	private JLabel personLabel, adultLabel, teenLabel, priceTextLabel, priceLabel;
	private JButton rezBt;
	private ButtonGroup adultGroup, teenGroup;
	private JRadioButton[] adultRadio, teenRadio;
	
	public SeatSelection() {
		super("좌석 선택");
		
		Font seatFont = new Font("Slab Serif", Font.BOLD, 15);
		Font font = new Font("Slab Serif", Font.BOLD, 20);		
		panel = new JPanel();
		panel.setLayout(null);

		seat = new JCheckBox[8][10];
		String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H"};
		
		int count = 0;		// 몇 개의 좌석이 선택되었는지
		// TODO: 성인&청소년 인원수보다 많이 체크되면 alert
		for (int i = 0; i < seat.length; i++)
			for (int j = 0; j < seat[i].length; j++)
			{
				seat[i][j] = new JCheckBox(alpha[i] + (j + 1));
				seat[i][j].setBounds(35 + 65 * j, 35 + 60 * i, 65, 60);
				seat[i][j].setFont(seatFont);
				panel.add(seat[i][j]);
			}
		
		personLabel = new JLabel("인원");
		//personLabel.setBorder(new LineBorder(Color.black, 2));
		personLabel.setFont(font);
		personLabel.setHorizontalAlignment(JLabel.CENTER);
		personLabel.setBounds(30, 600, 60, 45);
		panel.add(personLabel);
		
		adultLabel = new JLabel("성인");
		adultLabel.setFont(font);
		adultLabel.setHorizontalAlignment(JLabel.CENTER);
		adultLabel.setBounds(125, 560, 60, 45);
		//adultLabel.setBorder(new LineBorder(Color.black, 1));
		panel.add(adultLabel);
		
		teenLabel= new JLabel("청소년");
		teenLabel.setFont(font);
		teenLabel.setHorizontalAlignment(JLabel.CENTER);
		teenLabel.setBounds(110, 640, 100, 45);
		//teenLabel.setBorder(new LineBorder(Color.black, 1));
		panel.add(teenLabel);
		
		adultGroup = new ButtonGroup();
		adultRadio = new JRadioButton[8];
		for (int i = 0; i < adultRadio.length; i++)
		{
			adultRadio[i] = new JRadioButton(Integer.toString(i));
			adultRadio[i].setFont(font);
			adultRadio[i].setBounds(230 + 45 * i, 560, 40, 40);
			adultGroup.add(adultRadio[i]);
			panel.add(adultRadio[i]);
			
		}
		
		teenGroup = new ButtonGroup();
		teenRadio = new JRadioButton[8];
		for (int i = 0; i < teenRadio.length; i++)
		{
			teenRadio[i] = new JRadioButton(Integer.toString(i));
			teenRadio[i].setFont(font);
			teenRadio[i].setBounds(230 + 45 * i, 640, 40, 40);
			teenGroup.add(teenRadio[i]);
			panel.add(teenRadio[i]);
		}
		
		priceTextLabel = new JLabel("가격");
		priceTextLabel.setFont(font);
		priceTextLabel.setBounds(630, 560, 60, 45);
		priceTextLabel.setHorizontalAlignment(JLabel.CENTER);
		//priceTextLabel.setBorder(new LineBorder(Color.blue, 1));
		panel.add(priceTextLabel);
		
		priceLabel = new JLabel("23463원");
		priceLabel.setFont(font);
		priceLabel.setBounds(610, 640, 100, 45);
		priceLabel.setHorizontalAlignment(JLabel.CENTER);
		//priceLabel.setBorder(new LineBorder(Color.blue, 1));
		panel.add(priceLabel);
		
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 800);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SeatSelection();
	}
}
