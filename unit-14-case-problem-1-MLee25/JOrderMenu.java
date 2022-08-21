import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class JOrderMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private MenuItem[] chosenItems = new MenuItem[3];
	private double runningTotal = 0.00;
	private boolean isUpgradeApplied = false;
	private JTextArea label;

	public JOrderMenu() {
		super("Menu Order");
		setSize(new Dimension(450, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		add(orderDrinksSection());
		add(orderSideSection());
		add(orderEntreeSection());
		add(optionalUpgradeSection());
		add(outputSection());
		add(label);
		setVisible(true);
	}

	private JComboBox<Drink> orderDrinksSection() {
		Drink[] drinks = new Drink[5];
		drinks[0] = new Drink("No Drink", 0.00);
		drinks[1] = new Drink("Small Slippery Slurp", 1.99);
		drinks[2] = new Drink("Slippery Slurp ", 3.99);
		drinks[3] = new Drink("Super Slippery Slurp ", 6.99);
		drinks[4] = new Drink("Usurped", 9.99);
		JComboBox<Drink> drinkComboBox = new JComboBox<Drink>(drinks);
		chosenItems[0] = drinks[0];
		drinkComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Drink temp = ((Drink) drinkComboBox.getSelectedItem());
				updateSelection(drinks[0].getName(), 0, e, temp, drinks[0]);
				System.out.printf("%s - %.2f\n", chosenItems[0], getRunningTotal());
			}
		});
		return drinkComboBox;
	}

	private JComboBox<Side> orderSideSection() {
		Side[] sides = new Side[5];
		sides[0] = new Side("Nah to Sides", 0.00);
		sides[1] = new Side("Small Warm and Brown", 3.99);
		sides[2] = new Side("Small (not-so) Warm and Brown", 7.99);
		sides[3] = new Side("Small Warm and (not-so) Brown", 12.99);
		sides[4] = new Side("Small Only Brown", 16.99);
		JComboBox<Side> sidesComboBox = new JComboBox<Side>(sides);
		chosenItems[1] = sides[0];
		sidesComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Side temp = ((Side) sidesComboBox.getSelectedItem());
				updateSelection(sides[0].getName(), 1, e, temp, sides[0]);
				System.out.printf("%s - %.2f\n", chosenItems[1], getRunningTotal());
			}
		});
		return sidesComboBox;
	}

	private JComboBox<Entree> orderEntreeSection() {
		Entree[] entrees = new Entree[6];
		entrees[0] = new Entree("No Entree", 0.00);
		entrees[1] = new Entree("Stuned Walobean", 2.99);
		entrees[2] = new Entree("Super Stuned Walobean", 17.99);
		entrees[3] = new Entree("Omega Stuned Walobean", 23.99);
		entrees[4] = new Entree("Super Omega Stuned Walobean", 28.99);
		entrees[5] = new Entree("Absolutely Knocked Out Walobean", 37.99);
		JComboBox<Entree> entreesComboBox = new JComboBox<Entree>(entrees);
		chosenItems[2] = entrees[0];
		entreesComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Entree temp = ((Entree) entreesComboBox.getSelectedItem());
				updateSelection(entrees[0].getName(), 2, e, temp, entrees[0]);
				System.out.printf("%s - %.2f\n", chosenItems[2], getRunningTotal());
			}
		});
		return entreesComboBox;
	}

	private JCheckBox optionalUpgradeSection() {
		JCheckBox checkBox = new JCheckBox("Apply 10% Upgrade", false);
		checkBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calcRunningTotal();
				if (checkBox.isSelected()) {
					isUpgradeApplied = true;
					setRunnningTotal(getRunningTotal() * 1.10);
				} else {
					isUpgradeApplied = false;
					setRunnningTotal(getRunningTotal());
				}
				calcRunningTotal();

			}
		});
		return checkBox;
	}

	private JButton outputSection() {
		label = new JTextArea("");
		JButton submit = new JButton("Submit Order");
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equalsIgnoreCase("Submit Order")) {
					if (getRunningTotal() == 0.0) {
						label.setText("No Orders were entered");
					} else {
						label.setText("---- Ordered Summary ----\n");
						String outputString = label.getText();
						for (int i = 0; i < chosenItems.length; i++) {
							if (chosenItems[i] != null) {
								outputString += chosenItems[i].getType() + ":\n----" + chosenItems[i].getName() + " - $"
										+ chosenItems[i].getPrice() + "\n";
							}
						}
						if (!isUpgradeApplied) {
							outputString += "Meal Size: Standard\n";
							outputString += "--------Total Price--------\n$" + getRunningTotal();
						} else {
							outputString += "Meal Size: Upgraded\n";
							outputString += "--------Total Price--------\n$" + getRunningTotal();
						}
						label.setText(outputString);
					}
					label.setEditable(false);
					repaint();
				}

			}
		});

		return submit;
	}

	private void calcRunningTotal() {
		double runningTotal = 0.00;
		for (int i = 0; i < chosenItems.length; i++) {
			if (chosenItems[i] != null) {
				runningTotal += chosenItems[i].getPrice();
			}
		}
		setRunnningTotal(runningTotal);
		if (isUpgradeApplied) {
			setRunnningTotal(getRunningTotal() * 1.10);
		}
	}

	public void setRunnningTotal(double runningTotal) {
		this.runningTotal = runningTotal;
	}

	public double getRunningTotal() {
		return runningTotal;
	}

	private void updateSelection(String text, int index, ActionEvent e, MenuItem temp, MenuItem array) {
		if (!e.getActionCommand().equalsIgnoreCase(text)) {
			if (temp != null) {
				chosenItems[index] = temp;
			}
		}
		calcRunningTotal();
	}

}
