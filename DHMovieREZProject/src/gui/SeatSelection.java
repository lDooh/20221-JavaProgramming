package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SeatSelection extends JFrame {
	private JCheckBox seat[][];
	
	public SeatSelection() {
		super("ÁÂ¼®¼±ÅÃ");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 700);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SeatSelection();
	}
}