package main.java.gui.student;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StudentGUI {

	private final int MAX_SIZE = 10;

	private Integer[] studentIds;
	private String[] studentNames;
	private Double[] studentGPA;

	private JFrame mainFrame;
	private JTextField searchBox;
	private JButton searchBtn;

	public static void main(String[] args) {
		new StudentGUI();
	}

	public StudentGUI() {
		initializeData();
		createFrame();
		mainFrame.setVisible(true);
	}

	private void initializeData() {
		studentIds = new Integer[MAX_SIZE];
		studentNames = new String[MAX_SIZE];
		studentGPA = new Double[MAX_SIZE];

		try (Scanner z = new Scanner(new FileReader(new File("student-data.txt")))) {
			int i = 0;
			while (z.hasNextLine()) {
				String[] line = z.nextLine().split(",");
				initializeArrays(Integer.parseInt(line[0].trim()), line[1].trim(), Double.parseDouble(line[2]), i);
				i++;
			}
		} catch (IOException io) {
			io.printStackTrace();
		}

		printData();
	}

	private void initializeArrays(Integer id, String name, Double gpa, int index) {
		studentIds[index] = id;
		studentNames[index] = name;
		studentGPA[index] = gpa;
	}

	private void createFrame() {
		mainFrame = new JFrame("Student Search");
		mainFrame.setSize(600, 400);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setBackground(Color.BLACK);
		initializeSearchComponent();
		mainFrame.pack();
	}

	private void initializeSearchComponent() {
		mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		searchBtn = new JButton("Search");
		searchBox = new JTextField("", 15);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));

		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int studentId = Integer.parseInt(searchBox.getText());
				if (evt.getActionCommand().contains("Search")) {
					String result = searchStudent(studentId);
					if (result == null || result.isBlank() || result.isEmpty() || result.equals("")) {
						JOptionPane.showMessageDialog(panel, "STUDENT NOT FOUND!" + result);
					} else {
						JOptionPane.showMessageDialog(panel, "STUDENT RETRIEVED!\n" + result);
					}
				}
			}
		});
		
		searchBtn.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					int studentId = Integer.parseInt(searchBox.getText());
					String result = searchStudent(studentId);
					if (result == null || result.isBlank() || result.isEmpty() || result.equals("")) {
						JOptionPane.showMessageDialog(panel, "STUDENT NOT FOUND!" + result);
					} else {
						JOptionPane.showMessageDialog(panel, "STUDENT RETRIEVED!\n" + result);
					}
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

		panel.add(searchBox);
		panel.add(searchBtn);
		mainFrame.add(panel);
	}

	private String searchStudent(int id) {
		int i = 0;
		while (i++ < MAX_SIZE - 1) {
			if (id == studentIds[i]) {
				return String.format("Id: %d\nName: %-26s\nGPA: %.2f\n", studentIds[i], studentNames[i], studentGPA[i]);
			}
		}
		return "";
	}

	private void printData() {
		int i = 0;
		while (i++ < MAX_SIZE - 1) {
			System.out.printf("Id: %d | Name: %-26s | GPA: %.2f\n", studentIds[i], studentNames[i], studentGPA[i]);
		}
	}

}
