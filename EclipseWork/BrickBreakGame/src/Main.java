import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

    private JFrame frame;
    private Gameplay gp;

    public static void main(String args[]) {
        new Main();
    }

    public Main() {
        initRun();
    }

    private void initRun() {
        frame = mainFrame();
        frame.setVisible(true);
    }

    private JFrame mainFrame() {
        int x = 700, y = 600;
        frame = new JFrame("Brick Break");
        frame.setSize(new Dimension(x, y));
        frame.setPreferredSize(new Dimension(x, y));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private void addGameplayPanel() {
        gp = new Gameplay();
        frame.add(gp);
    }

}
