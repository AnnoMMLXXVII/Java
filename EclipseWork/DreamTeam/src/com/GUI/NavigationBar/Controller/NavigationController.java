package com.GUI.NavigationBar.Controller;

import javax.swing.JFrame;

import com.GUI.NavigationBar.NavBar;

/*
 *
 * Purpose of this class is to organize all Nav-menu
 */
public class NavigationController {

    private JFrame instance;

    public NavigationController(JFrame instance) {
        this.instance = instance;
        initRun();
    }

    public void initRun() {
        instance.setJMenuBar(new NavBar());
    }

}
