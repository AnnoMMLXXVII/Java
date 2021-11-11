package com.javatpoint.MenuExample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class MenuExample {

    private JMenu menu, subMenu, edit, help;
    private JMenuItem item;
    private JFrame frame;
    private JPanel jPnl;
    private JMenuBar jBar;
    private JTextArea jText;
    private String[] cmdAct = new String[]{"Cut", "Copy", "Paste", "Select All"};

    public static void main(String[] args) {
        new MenuExample();

    }

    MenuExample() {
        constructFrame();
        constructPanel();
        constructJMenuBar();
        constructJMenu();
        constructJMenuItems();
        addToBar();
        frame.setVisible(true);
    }

    private void constructFrame() {
        frame = new JFrame("Menu and MenuItems Example");
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void constructPanel() {
        jPnl = new JPanel();
        jPnl.setSize(frame.getWidth(), frame.getHeight());
        jPnl.setBackground(Color.ORANGE);
    }

    private void constructJMenuBar() {
        jBar = new JMenuBar();
    }

    private void constructJMenu() {
        menu = new JMenu("File");
        subMenu = new JMenu("Sub File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");

    }

    private void constructJMenuItems() {
        for (int i = 1; i <= 5; i++) {
            if (i <= 3) {
                menu.add(new JMenuItem("Item " + i));
            } else {
                subMenu.add(new JMenuItem("Item" + i));
            }
        }

        constructJText();
        for (int i = 0; i < cmdAct.length; i++) {
            item = new JMenuItem(cmdAct[i]);
            edit.add(item);
            item.addActionListener(new CommandListener(jText));
        }
    }

    private void constructJText() {
        jText = new JTextArea("The Quick Brown Fox Jumped Over The Lazy Dog");
//		JScrollPane jSP = new JScrollPane(jText);
        jText.setBounds(5, 5, 360, 320);
        frame.add(jText);
    }

    private void addToBar() {
        jBar.add(menu);
        menu.add(subMenu);

        jBar.add(edit);

        jBar.add(help);


//		jPnl.add();
        frame.add(jPnl, BorderLayout.CENTER);
        frame.setJMenuBar(jBar);
    }

    private class CommandListener implements ActionListener {
        private JTextArea jText;

        public CommandListener(JTextArea jText) {
            this.jText = jText;
        }

        @Override
        public void actionPerformed(ActionEvent src) {
            if (src.getActionCommand().equalsIgnoreCase("CUT")) {
                jText.cut();
                System.out.println("CUT");
            } else if (src.getActionCommand().equalsIgnoreCase("COPY")) {
                jText.copy();
                System.out.println("COPY");
            } else if (src.getActionCommand().equalsIgnoreCase("PASTE")) {
                jText.paste();
                System.out.println("PASTE");
            } else {
                jText.selectAll();
                System.out.println("SELECT ALL");
            }


        }

    }

}
