package DecisionStructures;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class a extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //	Instance variables
    // Declaring instance variables
    // By convention, should be private
    private JLabel jlbName;
    private JPanel panelName;

    //	CONSTRUCTOR
    public a() {
        super("RENZON");    // using super to set Title
        panelName = new JPanel();    // instantiating JPanel
        jlbName = new JLabel("RENZON", JLabel.CENTER);    // instantiating JLabel
// Setup for JFrame or Window
        setSize(400, 300);    // Set size of the Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes program when closing frame
        // ALSO --> SetDefaultCloserOperation argument belongs to JFrame
        // 	--> You missed this in your code
// Customizing contents
//		jlbName.setText("RENZON");	// sets Text of JLabel
        panelName.setToolTipText("RENZON H. PURIFICATION");
        panelName.setLayout(new BorderLayout());
        panelName.add(jlbName, BorderLayout.CENTER);    // panel adds Label Component
// JFrame will add Panel
        add(panelName);
    } // endae CONSTRUCTOR

    public static void main(String args[]) {
        new a().setVisible(true); // instantiates a class and sets Frame visibility to TRUE
    } // endae main
} // endae CLASS
