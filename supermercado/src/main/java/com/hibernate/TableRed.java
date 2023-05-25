package com.hibernate;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRed extends JTable {

	private TableRedHeader header;
	private TableRedCell cell;

	public TableRed() {
		header = new TableRedHeader();
		cell = new TableRedCell();
		getTableHeader().setDefaultRenderer(header);
		getTableHeader().setPreferredSize(new Dimension(0, 35));
		setDefaultRenderer(Object.class, cell);
		setRowHeight(30);
		setGridColor(new Color(238, 68, 93));
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(false);
	}

	public void setColumnAlignment(int column, int align) {
		header.setAlignment(column, align);
	}

	public void setCellAlignment(int column, int align) {
		cell.setAlignment(column, align);
	}

	public void setColumnWidth(int column, int width) {
		getColumnModel().getColumn(column).setPreferredWidth(width);
		getColumnModel().getColumn(column).setMinWidth(width);
		getColumnModel().getColumn(column).setMaxWidth(width);
		getColumnModel().getColumn(column).setMinWidth(10);
		getColumnModel().getColumn(column).setMaxWidth(10000);
	}

	public void fixTable(JScrollPane scroll) {
		scroll.setVerticalScrollBar(new ScrollBarCustom());
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 30, 30));
		scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
	}

	private class TableRedHeader extends DefaultTableCellRenderer {

		private Map<Integer, Integer> alignment = new HashMap<>();

		public void setAlignment(int column, int align) {
			alignment.put(column, align);
		}

		@Override
		public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i,
				int i1) {
			Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
			com.setBackground(new Color(238, 68, 93));
			com.setForeground(new Color(255, 255, 255));
			com.setFont(new Font("Kalimati", Font.BOLD, 12));
			if (alignment.containsKey(i1)) {
				setHorizontalAlignment(alignment.get(i1));
			} else {
				setHorizontalAlignment(JLabel.LEFT);
			}
			return com;
		}
	}

	private class TableRedCell extends DefaultTableCellRenderer {

		private Map<Integer, Integer> alignment = new HashMap<>();

		public void setAlignment(int column, int align) {
			alignment.put(column, align);
		}

		@Override
		public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row,
				int column) {
			Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
			if (isCellSelected(row, column)) {
				if (row % 2 == 0) {
					com.setBackground(new Color(255, 149, 143));
				} else {
					com.setBackground(new Color(250, 165, 160));
				}
			} else {
				if (row % 2 == 0) {
					com.setBackground(new Color(242, 242, 242));
				} else {
					com.setBackground(new Color(230, 230, 230));
				}
			}
			setFont(new Font("Kalimati", Font.PLAIN, 11));
			com.setForeground(Color.black);
			if (alignment.containsKey(column)) {
				setHorizontalAlignment(alignment.get(column));
			} else {
				setHorizontalAlignment(JLabel.LEFT);
			}
			return com;
		}
	}
}