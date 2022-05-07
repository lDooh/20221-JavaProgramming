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
	private int personCount, price, checkCount = 0;	// 몇 명의 인원을 선택했는지, 최종 가격, 몇 명이 체크되었는지
	
	public SeatSelection() {
		super("좌석 선택");
		
		Font seatFont = new Font("Slab Serif", Font.BOLD, 15);
		Font font = new Font("Slab Serif", Font.BOLD, 20);		
		panel = new JPanel();
		panel.setLayout(null);

		seat = new JCheckBox[8][10];
		String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H"};
		
		for (int i = 0; i < seat.length; i++)
			for (int j = 0; j < seat[i].length; j++)
			{
				seat[i][j] = new JCheckBox(alpha[i] + (j + 1));
				seat[i][j].setBounds(35 + 65 * j, 35 + 60 * i, 65, 60);
				seat[i][j].setFont(seatFont);
				seat[i][j].addItemListener(new seatCheckListener());
				panel.add(seat[i][j]);
			}
		
		personLabel = new JLabel("인원");
		personLabel.setFont(font);
		personLabel.setHorizontalAlignment(JLabel.CENTER);
		personLabel.setBounds(30, 600, 60, 45);
		panel.add(personLabel);
		
		adultLabel = new JLabel("<html>&nbsp;&nbsp;&nbsp;성인<br>10,000원</html>");
		adultLabel.setFont(font);
		adultLabel.setHorizontalAlignment(JLabel.CENTER);
		adultLabel.setBounds(105, 545, 100, 60);
		panel.add(adultLabel);
		
		teenLabel= new JLabel("<html>청소년<br>8,000원</html>");
		teenLabel.setFont(font);
		teenLabel.setHorizontalAlignment(JLabel.CENTER);
		teenLabel.setBounds(105, 640, 100, 60);
		panel.add(teenLabel);
		
		adultGroup = new ButtonGroup();
		adultRadio = new JRadioButton[8];
		for (int i = 0; i < adultRadio.length; i++)
		{
			adultRadio[i] = new JRadioButton(Integer.toString(i));
			adultRadio[i].setFont(font);
			adultRadio[i].setBounds(230 + 45 * i, 560, 40, 40);
			adultRadio[i].addActionListener(new personRadioListener());
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
			teenRadio[i].addActionListener(new personRadioListener());
			teenGroup.add(teenRadio[i]);
			panel.add(teenRadio[i]);
		}
		
		priceTextLabel = new JLabel("가격");
		priceTextLabel.setFont(font);
		priceTextLabel.setBounds(640, 535, 60, 45);
		priceTextLabel.setHorizontalAlignment(JLabel.CENTER);
		panel.add(priceTextLabel);
		
		priceLabel = new JLabel("0원");
		priceLabel.setFont(font);
		priceLabel.setBounds(620, 595, 100, 45);
		priceLabel.setHorizontalAlignment(JLabel.CENTER);
		panel.add(priceLabel);
		
		rezBt = new JButton("예매하기");
		rezBt.setFont(font);
		rezBt.setBounds(610, 665, 120, 45);
		rezBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 선택한 좌석의 수가 잘못된 경우
				if (checkCount == 0 || (checkCount != personCount))
				{
					JOptionPane.showMessageDialog(null, "좌석 선택을 올바르게 해주세요.", "좌석 선택", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else
				{
					int adultNum = 0, teenNum = 0;
					
					for (int i = 0; i < adultRadio.length; i++)
					{
						if (adultRadio[i].isSelected())
						{
							adultNum = i;
							break;
						}
					}
					for (int i = 0; i < teenRadio.length; i++)
					{
						if (teenRadio[i].isSelected())
						{
							teenNum = i;
							break;
						}
					}
					
					System.out.printf("성인 %d명, 청소년 %d명 => 총 %d명\n가격: %d원\n", 
							adultNum, teenNum, checkCount, adultNum * 10000 + teenNum * 8000);
					System.out.print("선택 좌석: ");
					for (int i = 0; i < seat.length; i++)
						for (int j = 0; j < seat[i].length; j++)
							if (seat[i][j].isSelected())
								System.out.print(seat[i][j].getText() + "\t");
				}
			}
		});
		panel.add(rezBt);
		
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		setResizable(false);
		setVisible(true);
	}
	
	class personRadioListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {			
			price = 0;
			personCount = 0;
			
			// 몇 명을 선택했는지, 가격은 얼마인지 계산
			for (int i = 0; i < adultRadio.length; i++)
				if (adultRadio[i].isSelected())
				{
					price += Integer.parseInt(adultRadio[i].getText()) * 10000;
					personCount += Integer.parseInt(adultRadio[i].getText());
				}
			
			for (int i = 0; i < teenRadio.length; i++)
				if (teenRadio[i].isSelected())
				{
					price += Integer.parseInt(teenRadio[i].getText()) * 8000;
					personCount += Integer.parseInt(teenRadio[i].getText());
				}
			
			priceLabel.setText(Integer.toString(price) + "원");
			
			for (int i = 0; i < seat.length; i++)
				for (int j = 0; j < seat[i].length; j++)
					seat[i][j].setSelected(false);
		}
	}
	
	class seatCheckListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				// System.out.println(" 선택함");
				checkCount += 1;
			}
			else
			{
				// System.out.println(" 선택 해제");
				checkCount -= 1;
			}
		}
	}
	
	public static void main(String[] args) {
		new SeatSelection();
	}
}
