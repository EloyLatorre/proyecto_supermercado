package com.hibernate;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(12, 20));
        setForeground(new Color(0, 30, 61));
        setBackground(new Color(252, 138, 25));
    }
}
