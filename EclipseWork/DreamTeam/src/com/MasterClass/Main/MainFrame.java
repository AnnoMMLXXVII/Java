package com.MasterClass.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.GUI.NavigationBar.NavBar;
import com.GUI.NavigationBar.Controller.NavigationController;
import com.GUI.Panels.MainJPanel;

public class MainFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int x = 500, y = 400;
    private NavigationController nc;

    public MainFrame() {
        super("NFL Dream Team");
        setSize(new Dimension(x, y));
        setPreferredSize(new Dimension(x, y));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        frameAttributes();
        addOtherComponents();
    }

    private void frameAttributes() {
        setBackground(Color.DARK_GRAY);
    }

    private void addOtherComponents() {
        add(new MainJPanel(), BorderLayout.CENTER);
        nc = new NavigationController(this);
//		add(, BorderLayout.NORTH);


    }
}
