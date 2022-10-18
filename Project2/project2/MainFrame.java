package project2;
//Ryan Murphey
//CSCI 3381 OO with Java Project 2
//
//
//
import java.awt.BorderLayout;
import javax.swing.*;
public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Rebound");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(557, 539);
		frame.getContentPane().add(new MainPanel());
		frame.pack();
		frame.setVisible(true);
	}
}