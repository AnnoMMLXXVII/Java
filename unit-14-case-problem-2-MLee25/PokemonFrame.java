import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PokemonFrame extends JFrame {

	/**
	 * Dataset Url: https://www.kaggle.com/datasets/abcsds/pokemon
	 */
	private static final long serialVersionUID = -9133599759399875171L;
	private JList<Pokemon> jList;
	private Pokemon[] pokeList;
	private List<JLabel> jLabels;
	private List<JTextField> jTextFieldValues;
	private JButton addButton;
	private JButton removeButton;
	private String[] labels = {
//			"ID", 
			"NAME", "TYPE", "TYPE2",
//			"TOTAL", "HP", "ATTK", "DEF", "SP.ATK", "SP.DEF","SPEED", 
			"GENERATION", "LEGENDARY Status (Y/N)" };

	public PokemonFrame(List<Pokemon> pokeList) {
		super("Pokemon Data");
		this.pokeList = convertListToArray(pokeList);
		JPanel mainPanel = new JPanel();
		setSize(new Dimension(1000, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		BorderLayout borderLayout = new BorderLayout();

		JPanel temp = northComponent();
		borderLayout.addLayoutComponent(temp, BorderLayout.NORTH);
		mainPanel.add(temp);

		temp = centerComponent();
		borderLayout.addLayoutComponent(temp, BorderLayout.CENTER);
		mainPanel.add(temp);

		temp = southComponent();
		borderLayout.addLayoutComponent(temp, BorderLayout.SOUTH);
		mainPanel.add(temp);

		mainPanel.setLayout(borderLayout);
		add(mainPanel);
		setVisible(true);

	}

	private List<JLabel> generateLabels() {
		List<JLabel> jLabels = new ArrayList<>();
		for (int i = 0; i < labels.length; i++) {
			jLabels.add(new JLabel(labels[i]));
		}
		return jLabels;
	}

	private JPanel northComponent() {
		JPanel northPanel = new JPanel();
		jLabels = generateLabels();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		northPanel.setLayout(flowLayout);
		jTextFieldValues = new ArrayList<>();
		for (int i = 0; i < jLabels.size(); i++) {
			northPanel.add(jLabels.get(i));
			JTextField jText = new JTextField(10);
			jText.setName(jLabels.get(i).getText());
			jText.setSize(100, 25);
			jTextFieldValues.add(jText);
			northPanel.add(jText);
		}
		northPanel.setVisible(true);
		return northPanel;
	}

	private JPanel centerComponent() {
		JPanel centerPanel = new JPanel();
		addButton = new JButton("Add To List");
		removeButton = new JButton("Remove");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equalsIgnoreCase(addButton.getText())) {
					boolean isValid = true;
					for (int i = 0; i < jTextFieldValues.size(); i++) {
						if (jTextFieldValues.get(i).getText().equalsIgnoreCase("")) {
							jTextFieldValues.get(i)
									.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0, 1), 2));
							isValid = false;
						}
					}
					if (!isValid) {
						return;
					}
					// Create new Pokemon Object w/ the JTextFields
					updateJListComponent(new Pokemon(jTextFieldValues.get(0).getText(),
							jTextFieldValues.get(1).getText(), jTextFieldValues.get(2).getText(),
							Pokemon.GENERATION.valueOf(jTextFieldValues.get(3).getText()),
							jTextFieldValues.get(4).getText()));

					// Clear the JTextFields
					for (int i = 0; i < jTextFieldValues.size(); i++) {
						jTextFieldValues.get(i).setText("");
					}
				}
			}

		});
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equalsIgnoreCase("Remove")) {
					int idx = jList.getSelectedIndex();
					Pokemon[] lowerHalf, upperHalf, merged = null;
					for (int i = 0; i < pokeList.length; i++) {
						if (idx == i) {
							lowerHalf = new Pokemon[i - 1];
							for (int j = 0; j < lowerHalf.length; j++) {
								lowerHalf[j] = pokeList[j];
							}
							upperHalf = new Pokemon[pokeList.length - lowerHalf.length];
							for (int j = pokeList.length - lowerHalf.length; j > i; j--) {
								upperHalf[j] = pokeList[j];
							}
							merged = new Pokemon[lowerHalf.length + upperHalf.length];
							for (int k = 0, j = upperHalf.length - 1; k != j; k++, j--) {
								merged[k] = lowerHalf[k];
								merged[j] = lowerHalf[j];
							}
							break;
						}
					}

					updateJListComponent(merged);
				}

			}
		});
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		centerPanel.setLayout(flowLayout);
		centerPanel.add(addButton);
		centerPanel.add(removeButton);
		centerPanel.setVisible(true);
		return centerPanel;
	}

	private JPanel southComponent() {
		JPanel southPanel = new JPanel();
		jList = new JList<Pokemon>(pokeList);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

			}
		});
		JScrollPane scroll = new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(900, 250));
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.CENTER);
		southPanel.setLayout(flowLayout);
		southPanel.add(scroll);
		southPanel.setSize(1000, 166);
		southPanel.setVisible(true);
		return southPanel;
	}

	private void updateJListComponent(Pokemon pokemon) {
		Pokemon[] temp = Arrays.copyOf(pokeList, pokeList.length + 1);
		temp[temp.length - 1] = pokemon;
		pokeList = temp;
		jList.setListData(pokeList);
		jList.repaint();
	}

	private void updateJListComponent(Pokemon[] pokemon) {
		Pokemon[] temp = Arrays.copyOf(pokemon, pokemon.length);
		pokeList = temp;
		jList.setListData(pokeList);
		jList.repaint();
	}

	private Pokemon[] convertListToArray(List<Pokemon> pokeList) {
		Pokemon[] temp = new Pokemon[pokeList.size() / 2];
		for (int i = 0; i < pokeList.size() / 2; i++) {
			temp[i] = pokeList.get(i);
		}
		return temp;
	}

}
