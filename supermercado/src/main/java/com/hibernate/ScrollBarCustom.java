package com.hibernate;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(12, 25));
        setForeground(new Color(163, 46, 63));
        setBackground(new Color(238, 68, 93));
    }
}
