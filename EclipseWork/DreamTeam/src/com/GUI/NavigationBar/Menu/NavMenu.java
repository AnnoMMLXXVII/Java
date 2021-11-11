package com.GUI.NavigationBar.Menu;

import javax.swing.JMenu;

public class NavMenu extends JMenu {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //	private String title;
//	private String sub;
    private boolean isCalled;

    public NavMenu(String title) {
        super(title);
//		setTitle(title);
    }

    public NavMenu(String title, NavMenu... subMenu) {
        super(title);
        addSubMenus(subMenu);
    }

    public NavMenu(String title, String... subTitle) {
        super(title);
        isCalled(true);
        addMenuItem(subTitle);
    }

    private void isCalled(boolean isCalled) {
        this.isCalled = isCalled;
    }

    private boolean getIsCalled() {
        return isCalled;
    }

    //	private void setTitle(String title) {
//		this.title = title;
//	}
//	
//	private String getTitle() {
//		return title;
//	}
//	
//	private void setSub(String sub) {
//		this.sub = sub;
//	}
//	
//	private String getSub() {
//		return sub;
//	}
    private void addMenuItem(String... subItemTitle) {
        for (int i = 0; i < subItemTitle.length; i++) {
            add(new NavMenuItem(subItemTitle[i]));
        }
    }

    private void addSubMenus(NavMenu... subMenu) {
        for (int i = 0; i < subMenu.length; i++) {
            add(new NavMenu(subMenu[i].getName()));
        }
    }

}
