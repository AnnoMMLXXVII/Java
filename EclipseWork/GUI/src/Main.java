import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;
    private int x = 600, y = 600;
    private JPanel jpnl;
    private Random r;

    public Main() {
        super("Sample GUI");
        setSize(new Dimension(x, y));
        setPreferredSize(new Dimension(x, y));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        frameContents();
        JTableContents();
    }

    private void frameContents() {
        jpnl = new JPanel();
        jpnl.setBackground(Color.YELLOW);
        add(jpnl);
    }

    private void JTableContents() {
        r = new Random();
        int rand = r.nextInt(9999) + 0;

        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();
        columns.add("First Name");
        columns.add("Last Name");
        columns.add("ID");
        for (int i = 0; i < 1000; i++) {
            rand = r.nextInt(9999) + 0;
            values.add(new String[]{"val" + i + " col1", "val" + i + " col2", "" + rand * i + ""});
        }
        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][]{}), columns.toArray());
        JTable table = new JTable(tableModel);
        jpnl.add(new JScrollPane(table), BorderLayout.CENTER);
        jpnl.add(table.getTableHeader(), BorderLayout.NORTH);
    }

    public static void main(String args[]) {
        new Main().setVisible(true);
    }

}
