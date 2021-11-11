import java.awt.Dimension;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int x = 500, y = 500;

    public MainWindow() {
        super("New Main Window");
        setPreferredSize(new Dimension(x, y));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
