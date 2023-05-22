package com.hibernate;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.List;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
//import com.hibernate.Gimnasio;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import com.hibernate.dao.AlmacenDAO;
import com.hibernate.model.*;
import com.hibernate.util.HibernateUtil;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Supermercado {
	private JFrame frmSuper;

	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JTextField textFieldStock;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Supermercado window = new Supermercado();
					window.frmSuper.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Supermercado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSuper = new JFrame();
		frmSuper.setResizable(false);
		frmSuper.getContentPane().setBackground(Color.DARK_GRAY);
		frmSuper.setBackground(new Color(250, 153, 56));
		frmSuper.setTitle("Supermercado");
		frmSuper.setIconImage(Toolkit.getDefaultToolkit().getImage(Supermercado.class.getResource("/img/icon.png")));
		frmSuper.setBounds(100, 100, 1510, 700);
		frmSuper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSuper.getContentPane().setLayout(null);
		
		
		
		/**
		 * Creación de un modelo de tabla personalizado utilizando DefaultTableModel.
		 */

		DefaultTableModel productos = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		productos.addColumn("ID");
		productos.addColumn("Producto");
		productos.addColumn("Categoría");
		productos.addColumn("Precio");
		productos.addColumn("Stock");

		List<Producto> listaProductos;
		listaProductos = AlmacenDAO.selectAllProductos();

		for (Producto producto : listaProductos) {
		    Object[] row = new Object[5];
		    row[0] = producto.getId();
		    row[1] = producto.getNombre();
		    row[2] = producto.getCategoria();
		    row[3] = producto.getPrecio();
		    row[4] = producto.getCantidadStock();
		    productos.addRow(row);
		}

		TableOrange tableProductos = new TableOrange();
		tableProductos.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int index = tableProductos.getSelectedRow();
		        TableModel model = tableProductos.getModel();
		        textFieldNombre.setText(model.getValueAt(index, 0).toString());
		        textFieldPrecio.setText(model.getValueAt(index, 1).toString());
		      //  textFieldCategoria.setText(model.getValueAt(index, 2).toString());
		        textFieldPrecio.setText(model.getValueAt(index, 3).toString());
		        textFieldStock.setText(model.getValueAt(index, 4).toString());
		    }
		});
		tableProductos.setModel(productos);
		tableProductos.setBounds(516, 266, -395, -100);
		tableProductos.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableProductos.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableProductos.getColumnModel().getColumn(2).setPreferredWidth(80);
		tableProductos.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableProductos.getColumnModel().getColumn(4).setPreferredWidth(60);
		tableProductos.setColumnAlignment(0, JLabel.CENTER);
		tableProductos.setCellAlignment(0, JLabel.CENTER);
		tableProductos.setColumnAlignment(1, JLabel.CENTER);
		tableProductos.setCellAlignment(1, JLabel.CENTER);
		tableProductos.setColumnAlignment(2, JLabel.CENTER);
		tableProductos.setCellAlignment(2, JLabel.CENTER);
		tableProductos.setColumnAlignment(3, JLabel.CENTER);
		tableProductos.setCellAlignment(3, JLabel.CENTER);
		tableProductos.setColumnAlignment(4, JLabel.CENTER);
		tableProductos.setCellAlignment(4, JLabel.CENTER);

		JScrollPane scrollPaneProductos = new JScrollPane(tableProductos);
		scrollPaneProductos.setViewportBorder(null);
		scrollPaneProductos.setBackground(new Color(252, 138, 25));
		scrollPaneProductos.setBorder(new LineBorder(new Color(252, 138, 25), 0, true));
		scrollPaneProductos.setBounds(582, 129, 808, 467);
		scrollPaneProductos.getVerticalScrollBar().setBackground(new Color(252, 138, 25));
		scrollPaneProductos.setVerticalScrollBar(new ScrollBarCustom());
		frmSuper.getContentPane().add(scrollPaneProductos);

		JLabel lblNewLabel = new JLabel("Producto");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(149, 58, 118, 61);
		frmSuper.getContentPane().add(lblNewLabel);
		
		JLabel lblFiltrar = new JLabel("Filtrar");
		lblFiltrar.setForeground(Color.WHITE);
		lblFiltrar.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		lblFiltrar.setBounds(171, 341, 96, 61);
		frmSuper.getContentPane().add(lblFiltrar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNombre.setBounds(67, 142, 78, 29);
		frmSuper.getContentPane().add(lblNombre);
		
		JLabel lblCategora = new JLabel("Categoría");
		lblCategora.setForeground(Color.WHITE);
		lblCategora.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblCategora.setBounds(67, 189, 78, 29);
		frmSuper.getContentPane().add(lblCategora);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPrecio.setBounds(67, 230, 78, 29);
		frmSuper.getContentPane().add(lblPrecio);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblStock.setBounds(67, 271, 78, 29);
		frmSuper.getContentPane().add(lblStock);
		
		JButton btnMostrarTodos = new JButton("Mostrar todos");
		btnMostrarTodos.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Obtener todos los productos de la base de datos
		        List<Producto> listaProductos = AlmacenDAO.selectAllProductos();

		        // Limpiar la tabla antes de agregar los nuevos datos
		        DefaultTableModel modelo = (DefaultTableModel) tableProductos.getModel();
		        modelo.setRowCount(0);

		        // Agregar los productos a la tabla
		        for (Producto producto : listaProductos) {
		            Object[] row = new Object[5];
		            row[0] = producto.getId();
		            row[1] = producto.getNombre();
		            row[2] = producto.getCategoria();
		            row[3] = producto.getPrecio();
		            row[4] = producto.getCantidadStock();
		            modelo.addRow(row);
		        }
		    }
		});

		btnMostrarTodos.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnMostrarTodos.setForeground(new Color(238, 68, 93));
		btnMostrarTodos.setBackground(Color.WHITE);
		btnMostrarTodos.setBounds(67, 438, 279, 40);
		frmSuper.getContentPane().add(btnMostrarTodos);
		
		JComboBox<Categoria> comboBoxFiltrarCategoria = new JComboBox<>();
		comboBoxFiltrarCategoria.setForeground(new Color(238, 68, 93));
		comboBoxFiltrarCategoria.setBackground(Color.WHITE);
		comboBoxFiltrarCategoria.setBounds(67, 560, 279, 40);
		frmSuper.getContentPane().add(comboBoxFiltrarCategoria);

		// Cargar las categorías desde la base de datos y agregarlas al combo box
		List<Categoria> categorias = AlmacenDAO.selectAllCategorias(); 
		for (Categoria categoria : categorias) {
		    comboBoxFiltrarCategoria.addItem(categoria);
		}


		
		JButton btnMostrarSinStock = new JButton("Sin Stock");
		btnMostrarSinStock.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Obtener los productos fuera de stock de la base de datos
		        List<Producto> productosSinStock = AlmacenDAO.selectProductosSinStock();

		        // Limpiar la tabla antes de agregar los nuevos datos
		        DefaultTableModel modelo = (DefaultTableModel) tableProductos.getModel();
		        modelo.setRowCount(0);

		        // Agregar los productos fuera de stock a la tabla
		        for (Producto producto : productosSinStock) {
		            Object[] row = new Object[5];
		            row[0] = producto.getId();
		            row[1] = producto.getNombre();
		            row[2] = producto.getCategoria();
		            row[3] = producto.getPrecio();
		            row[4] = producto.getCantidadStock();
		            modelo.addRow(row);
		        }
		    }
		});

		btnMostrarSinStock.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnMostrarSinStock.setForeground(new Color(238, 68, 93));
		btnMostrarSinStock.setBackground(Color.WHITE);
		btnMostrarSinStock.setBounds(67, 503, 279, 40);
		frmSuper.getContentPane().add(btnMostrarSinStock);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(173, 144, 173, 26);
		frmSuper.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(173, 232, 173, 26);
		frmSuper.getContentPane().add(textFieldPrecio);
		
		textFieldStock = new JTextField();
		textFieldStock.setColumns(10);
		textFieldStock.setBounds(173, 273, 173, 26);
		frmSuper.getContentPane().add(textFieldStock);
		
		JComboBox comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setForeground(new Color(238, 68, 93));
		comboBoxCategoria.setBackground(Color.WHITE);
		comboBoxCategoria.setBounds(173, 192, 173, 27);
		frmSuper.getContentPane().add(comboBoxCategoria);
		
		

		/**
		 * Creación del objeto para llamar a las funciones de gymDAO
		 */
		AlmacenDAO almacenDAO = new AlmacenDAO();

		/**
		 * Creación de los objetos bordes, que se utilizan posteriormente en los
		 * textfields
		 */
		Border empty = new EmptyBorder(0, 5, 0, 0);

	
	}
}
