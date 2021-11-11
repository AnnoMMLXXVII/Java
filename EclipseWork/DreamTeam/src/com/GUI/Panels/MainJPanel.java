package com.GUI.Panels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;

import com.Player.RosterManager.RosterManager;

public class MainJPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel centerJPnl;
    private JTable jtable;
    private RosterManager rm;

    public MainJPanel() {
        rm = RosterManager.getInstance();
        initBackgrd();
        initBord();
        initLayout();
        addJBtn();

    }

    private void initLayout() {
        setLayout(new BorderLayout());
    }

    private void initBackgrd() {
        setBackground(Color.cyan);
    }

    private void initBord() {
        Border bord = BorderFactory.createLineBorder(Color.black, 3, true);
        setBorder(bord);
    }


    private void addJBtn() {
//		initJButton();
        add(new JButton("CENTER"), BorderLayout.CENTER);
        add(new JButton("SOUTH"), BorderLayout.SOUTH);
        add(new JButton("NORTH"), BorderLayout.NORTH);
        add(new JButton("WEST"), BorderLayout.WEST);
        add(new JButton("EAST"), BorderLayout.EAST);
    }

    private JPanel addCenterJPnl() {
        centerJPnl = new JPanel();
        jtable = new JTable();

        return centerJPnl;
    }


}
