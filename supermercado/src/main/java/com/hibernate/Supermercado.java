package com.hibernate;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.List;
import java.util.Locale;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableModel;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import com.hibernate.dao.AlmacenDAO;
import com.hibernate.model.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import java.util.ArrayList;

import javax.swing.JList;

import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;
import com.toedter.calendar.JDateChooser;

import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Supermercado {
	private JFrame frmSuper;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JTextField textFieldStock;
	private JList<String> listProductos;

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

	public Supermercado() {
		initialize();
	}

	private void initialize() {
		frmSuper = new JFrame();
		frmSuper.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Supermercado.class.getResource("/img/logosuper.png")));
		frmSuper.setResizable(false);
		frmSuper.getContentPane().setBackground(Color.DARK_GRAY);
		frmSuper.setBackground(new Color(0, 0, 0));
		frmSuper.setTitle("Supermercado");
		frmSuper.setBounds(100, 100, 1460, 720);
		frmSuper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSuper.getContentPane().setLayout(null);

		// Creación de los objetos bordes, que se utilizan posteriormente en los
		// textfields
		Border empty = new EmptyBorder(0, 5, 0, 0);

		AlmacenDAO almacenDAO = new AlmacenDAO();
		
		// Obtener todas las categorías de la base de datos
		List<Categoria> categoriasList = almacenDAO.selectAllCategorias();
		List<String> categorias = new ArrayList<>();
		for (Categoria categoria : categoriasList) {
			categorias.add(categoria.getNombre());
		}

		JComboBox<String> comboBoxCategorias = new JComboBox<>();
		((JLabel) comboBoxCategorias.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxCategorias.setBorder(null);
		comboBoxCategorias.setBounds(182, 132, 235, 27);
		comboBoxCategorias.setModel(new DefaultComboBoxModel<>(categorias.toArray(new String[0])));
		frmSuper.getContentPane().add(comboBoxCategorias);

		DefaultTableModel modelProductos = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
			// Hace que las celdas de la tabla no se puedan editar
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelProductos.addColumn("ID");
		modelProductos.addColumn("Nombre");
		modelProductos.addColumn("Precio");
		modelProductos.addColumn("Cantidad");
		modelProductos.addColumn("Categoría");

		// Crea una lista en la que se añaden todos los clientes que hay en la base de
		// datos
		List<Producto> listaProductos;
		listaProductos = almacenDAO.selectAllProductos();

		// Rellena la tabla con todos los clientes que tienen un ejercicio asignado y
		// con los mismos
		for (Producto producto : listaProductos) {
			Object[] row = new Object[5];
			row[0] = producto.getId();
			row[1] = producto.getNombre();
			row[2] = producto.getPrecio();
			row[3] = producto.getCantidadStock();
			row[4] = producto.getCategoria().getNombre();
			modelProductos.addRow(row);
		}

		TableRed tableProductos = new TableRed();
		tableProductos.addMouseListener(new MouseAdapter() {
			@Override
			// Cambia el indice de los combobox dependiendo de la fila seleccionada en la
			// tabla
			public void mouseClicked(MouseEvent e) {
				int index = tableProductos.getSelectedRow();
				TableModel model = tableProductos.getModel();
				textFieldNombre.setText(model.getValueAt(index, 1).toString());
				textFieldPrecio.setText(model.getValueAt(index, 2).toString());
				textFieldStock.setText(model.getValueAt(index, 3).toString());
				comboBoxCategorias.setSelectedItem(model.getValueAt(index, 4).toString());
			}
		});
		tableProductos.setModel(modelProductos);
		tableProductos.getColumnModel().getColumn(1).setPreferredWidth(100);
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
		scrollPaneProductos.setBackground(new Color(238, 68, 93));
		scrollPaneProductos.setBorder(null);
		scrollPaneProductos.setBounds(480, 84, 508, 290);
		scrollPaneProductos.getVerticalScrollBar().setBackground(new Color(252, 138, 25));
		scrollPaneProductos.setVerticalScrollBar(new ScrollBarCustom());
		frmSuper.getContentPane().add(scrollPaneProductos);

		// Obtener todos los productos de la base de datos
		List<Producto> productos = almacenDAO.selectAllProductos();
		DefaultListModel<String> modelProductos2 = new DefaultListModel<>();
		for (Producto producto : productos) {
			modelProductos2.addElement(producto.getNombre());
		}
		listProductos = new JList<>();
		listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listProductos.setModel(modelProductos2);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(238, 68, 93));
		lblNombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblNombre.setBounds(63, 84, 80, 25);
		frmSuper.getContentPane().add(lblNombre);

		JLabel lblCategora = new JLabel("Categoría:");
		lblCategora.setForeground(new Color(238, 68, 93));
		lblCategora.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblCategora.setBounds(63, 132, 80, 25);
		frmSuper.getContentPane().add(lblCategora);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setForeground(new Color(238, 68, 93));
		lblPrecio.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblPrecio.setBounds(63, 177, 80, 25);
		frmSuper.getContentPane().add(lblPrecio);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setForeground(new Color(238, 68, 93));
		lblStock.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblStock.setBounds(63, 228, 80, 25);
		frmSuper.getContentPane().add(lblStock);

		textFieldNombre = new JTextField();
		textFieldNombre.setBorder(empty);
		textFieldNombre.setBounds(182, 84, 235, 25);
		textFieldNombre.setColumns(10);
		frmSuper.getContentPane().add(textFieldNombre);

		textFieldPrecio = new JTextField();
		textFieldPrecio.setBorder(empty);
		textFieldPrecio.setBounds(182, 180, 235, 25);
		textFieldPrecio.setColumns(10);
		frmSuper.getContentPane().add(textFieldPrecio);

		textFieldStock = new JTextField();
		textFieldStock.setBorder(empty);
		textFieldStock.setBounds(182, 228, 235, 25);
		textFieldStock.setColumns(10);
		frmSuper.getContentPane().add(textFieldStock);

		JButton buttonCleanProducto = new JButton("");
		buttonCleanProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNombre.setText(null);
				comboBoxCategorias.setSelectedIndex(0);
				textFieldPrecio.setText(null);
				textFieldStock.setText(null);
			}
		});
		buttonCleanProducto.setIcon(new ImageIcon(Producto.class.getResource("/img/clean.png")));
		buttonCleanProducto.setBorder(null);
		buttonCleanProducto.setBackground(null);
		buttonCleanProducto.setBounds(387, 33, 30, 30);
		frmSuper.getContentPane().add(buttonCleanProducto);

		JButton btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldNombre.getText();
				String precioText = textFieldPrecio.getText();
				String cantidadText = textFieldStock.getText();
				String categoria = comboBoxCategorias.getSelectedItem().toString();

				// Verificar que los campos no estén vacíos
				if (nombre.isEmpty() || precioText.isEmpty() || cantidadText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verificar que el precio y la cantidad sean valores numéricos
				double precio;
				int cantidad;
				try {
					precio = Double.parseDouble(precioText);
					cantidad = Integer.parseInt(cantidadText);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El precio y la cantidad deben ser valores numéricos.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verificar que el nombre del producto no esté repetido
				if (almacenDAO.productoExiste(nombre)) {
					JOptionPane.showMessageDialog(null, "Ya existe un producto con este nombre.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verificar que la cantidad no sea negativa
				if (cantidad < 0) {
					JOptionPane.showMessageDialog(null, "La cantidad no puede ser un valor negativo.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verificar que el nombre contenga al menos una letra
				if (!nombre.matches(".*[a-zA-Z].*")) {
					JOptionPane.showMessageDialog(null, "El nombre debe contener al menos una letra.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Obtener la categoría seleccionada
				Categoria categoriaSeleccionada = null;
				for (Categoria cat : categoriasList) {
					if (cat.getNombre().equals(categoria)) {
						categoriaSeleccionada = cat;
						break;
					}
				}

				if (categoriaSeleccionada == null) {
					JOptionPane.showMessageDialog(null, "¡La categoría seleccionada no es válida!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Crear un nuevo objeto Productos
				Producto producto = new Producto();
				producto.setNombre(nombre);
				producto.setPrecio(precio);
				producto.setCantidadStock(cantidad);
				producto.setCategoria(categoriaSeleccionada);

				// Insertar el producto en la base de datos
				almacenDAO.insertProductos(producto);

				// Limpiar los campos de entrada
				textFieldNombre.setText("");
				textFieldPrecio.setText("");
				textFieldStock.setText("");

				modelProductos.setRowCount(0);

				List<Producto> listaProductos = almacenDAO.selectAllProductos();
				for (Producto productoAct : listaProductos) {
					Object[] row = new Object[5];
					row[0] = productoAct.getId();
					row[1] = productoAct.getNombre();
					row[2] = productoAct.getPrecio();
					row[3] = productoAct.getCantidadStock();
					row[4] = productoAct.getCategoria();
					modelProductos.addRow(row);
				}

				JOptionPane.showMessageDialog(null, "¡Producto agregado exitosamente!", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(new Color(255, 255, 255));
		btnAgregar.setIcon(new ImageIcon(Supermercado.class.getResource("/img/anadir.png")));
		btnAgregar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnAgregar.setBounds(63, 314, 90, 60);
		frmSuper.getContentPane().add(btnAgregar);

		JButton btnActualizar = new JButton("");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener el índice de la fila seleccionada en la tabla
				int selectedRow = tableProductos.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Selecciona un producto para actualizar.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Obtener el ID del producto seleccionado en la tabla
				int productId = (int) tableProductos.getValueAt(selectedRow, 0);

				// Obtener el producto de la base de datos por su ID
				Producto producto = almacenDAO.selectProductosById(productId);
				if (producto == null) {
					JOptionPane.showMessageDialog(null, "El producto seleccionado no existe.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Obtener los nuevos valores de los campos de entrada
				String nuevoNombre = textFieldNombre.getText();
				String nuevoPrecioText = textFieldPrecio.getText();
				String nuevaCantidadText = textFieldStock.getText();
				String nuevaCategoria = comboBoxCategorias.getSelectedItem().toString();

				// Verificar que los campos no estén vacíos
				if (nuevoNombre.isEmpty() || nuevoPrecioText.isEmpty() || nuevaCantidadText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verificar que el precio y la cantidad sean valores numéricos
				double nuevoPrecio;
				int nuevaCantidad;
				try {
					nuevoPrecio = Double.parseDouble(nuevoPrecioText);
					nuevaCantidad = Integer.parseInt(nuevaCantidadText);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El precio y la cantidad deben ser valores numéricos.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verificar que la cantidad no sea negativa
				if (nuevaCantidad < 0) {
					JOptionPane.showMessageDialog(null, "La cantidad no puede ser un valor negativo.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verificar que el nombre contenga al menos una letra
				if (!nuevoNombre.matches(".*[a-zA-Z].*")) {
					JOptionPane.showMessageDialog(null, "El nombre debe contener al menos una letra.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Obtener la categoría seleccionada
				Categoria categoriaSeleccionada = null;
				for (Categoria cat : categoriasList) {
					if (cat.getNombre().equals(nuevaCategoria)) {
						categoriaSeleccionada = cat;
						break;
					}
				}

				if (categoriaSeleccionada == null) {
					JOptionPane.showMessageDialog(null, "¡La categoría seleccionada no es válida!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Actualizar los valores del producto
				producto.setNombre(nuevoNombre);
				producto.setPrecio(nuevoPrecio);
				producto.setCantidadStock(nuevaCantidad);
				producto.setCategoria(categoriaSeleccionada);

				// Actualizar el producto en la base de datos
				almacenDAO.updateProductos(producto);

				// Limpiar los campos de entrada
				textFieldNombre.setText("");
				textFieldPrecio.setText("");
				textFieldStock.setText("");

				// Actualizar la tabla de productos mostrados
				modelProductos.setValueAt(producto.getNombre(), selectedRow, 1); // Columna del nombre
				modelProductos.setValueAt(producto.getPrecio(), selectedRow, 2); // Columna del precio
				modelProductos.setValueAt(producto.getCantidadStock(), selectedRow, 3); // Columna del stock

				JOptionPane.showMessageDialog(null, "¡Producto actualizado exitosamente!", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(new Color(255, 255, 255));
		btnActualizar.setIcon(new ImageIcon(Supermercado.class.getResource("/img/actualizar.png")));
		btnActualizar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnActualizar.setBounds(198, 314, 90, 60);
		frmSuper.getContentPane().add(btnActualizar);

		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener la fila seleccionada en la tabla
				int selectedRow = tableProductos.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Selecciona un producto para eliminar.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Obtener el ID del producto seleccionado en la tabla
				int productId = (int) tableProductos.getValueAt(selectedRow, 0);

				// Confirmar la eliminación del producto
				int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar el producto?",
						"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					// Eliminar el producto de la base de datos
					almacenDAO.deleteProductos(productId);

					// Eliminar la fila correspondiente en la tabla
					modelProductos.removeRow(selectedRow);

					JOptionPane.showMessageDialog(null, "¡Producto eliminado exitosamente!", "Información",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(255, 255, 255));
		btnEliminar.setIcon(new ImageIcon(Supermercado.class.getResource("/img/borrar.png")));
		btnEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnEliminar.setBounds(327, 314, 90, 60);
		frmSuper.getContentPane().add(btnEliminar);

		JButton btnMostrarTodos = new JButton("MOSTRAR TODOS");
		btnMostrarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Eliminar cualquier filtro aplicado a la tabla
				tableProductos.setRowSorter(null);

				// Actualizar la tabla de productos mostrando todos los registros
				List<Producto> productos = almacenDAO.selectAllProductos();

				DefaultTableModel modelProductos = (DefaultTableModel) tableProductos.getModel();
				modelProductos.setRowCount(0);

				for (Producto producto : productos) {
					Object[] rowData = { producto.getId(), producto.getNombre(), producto.getPrecio(),
							producto.getCantidadStock(), producto.getCategoria() };
					modelProductos.addRow(rowData);
				}

				// Mostrar mensaje de éxito
				JOptionPane.showMessageDialog(null, "Se han mostrado todos los productos.");
			}
		});
		btnMostrarTodos.setBorder(null);
		btnMostrarTodos.setBackground(new Color(255, 255, 255));
		btnMostrarTodos.setForeground(new Color(238, 90, 60));
		btnMostrarTodos.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		btnMostrarTodos.setBounds(63, 452, 355, 40);
		frmSuper.getContentPane().add(btnMostrarTodos);

		JComboBox<String> comboBoxCategoriaConcreta = new JComboBox<String>();
		((JLabel) comboBoxCategoriaConcreta.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxCategoriaConcreta.setBorder(null);
		comboBoxCategoriaConcreta.setBounds(63, 604, 355, 40);
		frmSuper.getContentPane().add(comboBoxCategoriaConcreta);

		// Obtener todas las categorías de la base de datos
		List<Categoria> categorias2 = almacenDAO.selectAllCategorias();

		// Agregar las categorías al JComboBox
		for (Categoria categoria2 : categorias2) {
			comboBoxCategoriaConcreta.addItem(categoria2.getNombre());
		}

		comboBoxCategoriaConcreta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener la categoría seleccionada
				String categoriaSeleccionada = comboBoxCategoriaConcreta.getSelectedItem().toString();

				// Filtrar la tabla por la categoría seleccionada
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableProductos.getModel());
				tableProductos.setRowSorter(sorter);
				List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
				int categoriaColumnIndex = 4; // Índice de la columna de categoría en el modelo de tabla
				filters.add(RowFilter.regexFilter(categoriaSeleccionada, categoriaColumnIndex));
				sorter.setRowFilter(RowFilter.andFilter(filters));
			}
		});

		JLabel lblMostrarCategoraConcreta = new JLabel("Mostrar Categoría:");
		lblMostrarCategoraConcreta.setForeground(new Color(238, 68, 93));
		lblMostrarCategoraConcreta.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblMostrarCategoraConcreta.setBounds(63, 572, 355, 25);
		frmSuper.getContentPane().add(lblMostrarCategoraConcreta);

		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setForeground(new Color(238, 68, 93));
		lblFechaFin.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblFechaFin.setBounds(1037, 228, 105, 25);
		frmSuper.getContentPane().add(lblFechaFin);

		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setForeground(new Color(238, 68, 93));
		lblFechaInicio.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblFechaInicio.setBounds(1037, 180, 105, 25);
		frmSuper.getContentPane().add(lblFechaInicio);

		JLabel lblNuevoProducto = new JLabel("Gestión de Productos");
		lblNuevoProducto.setForeground(new Color(238, 68, 93));
		lblNuevoProducto.setFont(new Font("Corbel", Font.BOLD, 30));
		lblNuevoProducto.setBounds(63, 33, 364, 40);
		frmSuper.getContentPane().add(lblNuevoProducto);

		JLabel lblGestinDeOfertas = new JLabel("Gestión de Ofertas");
		lblGestinDeOfertas.setForeground(new Color(238, 68, 93));
		lblGestinDeOfertas.setFont(new Font("Corbel", Font.BOLD, 30));
		lblGestinDeOfertas.setBounds(1037, 33, 270, 40);
		frmSuper.getContentPane().add(lblGestinDeOfertas);

		JLabel lblFechaInicio_1 = new JLabel("Producto:");
		lblFechaInicio_1.setForeground(new Color(238, 68, 93));
		lblFechaInicio_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblFechaInicio_1.setBounds(1037, 84, 105, 25);
		frmSuper.getContentPane().add(lblFechaInicio_1);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Supermercado.class.getResource("/img/logosuper.png")));
		lblLogo.setBounds(1242, 485, 149, 142);
		frmSuper.getContentPane().add(lblLogo);

		DefaultTableModel modelOfertas = new DefaultTableModel();
		modelOfertas.addColumn("ID");
		modelOfertas.addColumn("Fecha Inicio");
		modelOfertas.addColumn("Fecha Fin");
		modelOfertas.addColumn("Precio Oferta");
		modelOfertas.addColumn("Descuento");
		modelOfertas.addColumn("Producto");

		DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Formato para mostrar máximo dos decimales
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES")); // Formato de fecha

		List<Oferta> listaOfertas = almacenDAO.selectAllOfertas();
		for (Oferta oferta : listaOfertas) {
			double descuentoTotal = (1 - (oferta.getPrecioOferta() / oferta.getProducto().getPrecio())) * 100;
			Object[] row = new Object[6];
			row[0] = oferta.getId();
			row[1] = dateFormat.format(oferta.getFechaInicio());
			row[2] = dateFormat.format(oferta.getFechaFin());
			row[3] = decimalFormat.format(oferta.getPrecioOferta());
			row[4] = decimalFormat.format(descuentoTotal) + "%";
			row[5] = oferta.getProducto().getNombre();
			modelOfertas.addRow(row);
		}

		// Crear la tabla
		JTable tableOfertas = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			// Hace que las celdas de la tabla no se puedan editar
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		tableOfertas.setModel(modelOfertas);

		JScrollPane scrollPaneOfertas = new JScrollPane();
		scrollPaneOfertas.setBounds(480, 452, 508, 192);
		scrollPaneOfertas.setViewportView(tableOfertas);
		frmSuper.getContentPane().add(scrollPaneOfertas);

		JDateChooser fechaInicioChooser = new JDateChooser();
		fechaInicioChooser.setBounds(1156, 180, 235, 25);
		frmSuper.getContentPane().add(fechaInicioChooser);

		JDateChooser fechaFinChooser = new JDateChooser();
		fechaFinChooser.setBounds(1156, 228, 235, 25);
		frmSuper.getContentPane().add(fechaFinChooser);

		// Obtener todos los productos de la base de datos
		List<Producto> productosOferta = almacenDAO.selectAllProductos();
		DefaultComboBoxModel<Producto> comboBoxModel = new DefaultComboBoxModel<>();
		for (Producto producto : productosOferta) {
			comboBoxModel.addElement(producto);
		}

		JComboBox comboBoxOferta = new JComboBox();
		((JLabel) comboBoxOferta.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxOferta.setBorder(null);
		comboBoxOferta.setBounds(1156, 84, 235, 25);
		comboBoxOferta.setModel(comboBoxModel);
		frmSuper.getContentPane().add(comboBoxOferta);

		// Asignar el modelo del ComboBox al JComboBox

		JComboBox comboBoxDescuento = new JComboBox();
		((JLabel) comboBoxDescuento.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxDescuento.setBorder(null);
		comboBoxDescuento.setBounds(1156, 132, 235, 25);
		comboBoxDescuento.addItem("10% de descuento");
		comboBoxDescuento.addItem("20% de descuento");
		comboBoxDescuento.addItem("30% de descuento");
		comboBoxDescuento.addItem("40% de descuento");
		comboBoxDescuento.addItem("50% de descuento");
		comboBoxDescuento.addItem("60% de descuento");
		comboBoxDescuento.addItem("70% de descuento");
		frmSuper.getContentPane().add(comboBoxDescuento);

		// Botón Nueva Oferta
		JButton btnNuevaOferta = new JButton("");
		btnNuevaOferta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Obtener el producto seleccionado del JComboBox
				Producto productoSeleccionado = (Producto) comboBoxOferta.getSelectedItem();

				// Obtener las fechas seleccionadas de los JDateChooser
				Date fechaInicio = fechaInicioChooser.getDate();
				Date fechaFin = fechaFinChooser.getDate();

				// Obtener el descuento seleccionado del JComboBox
				String descuentoSeleccionado = (String) comboBoxDescuento.getSelectedItem();

				// Validar que se hayan seleccionado un producto, las fechas y el descuento
				if (productoSeleccionado == null || fechaInicio == null || fechaFin == null
						|| descuentoSeleccionado == null) {
					JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
					return;
				}

				// Validar que la fecha de inicio sea antes de la fecha de fin
				if (fechaInicio.after(fechaFin)) {
					JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser anterior a la fecha de fin.");
					return;
				}

				// Validar que la fecha de inicio sea posterior a la fecha actual
				Date fechaActual = new Date();
				if (fechaInicio.before(fechaActual)) {
					JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser posterior a la fecha actual.");
					return;
				}

				// Obtener el precio normal del producto
				double precioNormal = productoSeleccionado.getPrecio();

				// Calcular el precio de la oferta aplicando el descuento seleccionado
				double precioOferta;
				if (descuentoSeleccionado.equals("Sin descuento")) {
					precioOferta = precioNormal;
				} else if (descuentoSeleccionado.equals("10% de descuento")) {
					precioOferta = precioNormal * 0.9;
				} else if (descuentoSeleccionado.equals("20% de descuento")) {
					precioOferta = precioNormal * 0.8;
				} else if (descuentoSeleccionado.equals("30% de descuento")) {
					precioOferta = precioNormal * 0.7;
				} else if (descuentoSeleccionado.equals("40% de descuento")) {
					precioOferta = precioNormal * 0.6;
				} else if (descuentoSeleccionado.equals("50% de descuento")) {
					precioOferta = precioNormal * 0.5;
				} else if (descuentoSeleccionado.equals("60% de descuento")) {
					precioOferta = precioNormal * 0.4;
				} else if (descuentoSeleccionado.equals("70% de descuento")) {
					precioOferta = precioNormal * 0.3;
				} else {
					// Opción de descuento no válida
					JOptionPane.showMessageDialog(null, "Descuento seleccionado no válido.");
					return;
				}

				// Crear una instancia de la clase Ofertas y asignar los valores
				Oferta nuevaOferta = new Oferta();
				nuevaOferta.setProducto(productoSeleccionado);
				nuevaOferta.setFechaInicio(fechaInicio);
				nuevaOferta.setFechaFin(fechaFin);
				nuevaOferta.setPrecioOferta(precioOferta);

				// Guardar la nueva oferta en la base de datos
				almacenDAO.insertOferta(nuevaOferta);

				modelOfertas.setRowCount(0);

				DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Formato para mostrar máximo dos decimales
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES")); // Formato de

				List<Oferta> listaOfertas = almacenDAO.selectAllOfertas();
				for (Oferta oferta : listaOfertas) {
					double descuentoTotal = (1 - (oferta.getPrecioOferta() / oferta.getProducto().getPrecio())) * 100;
					Object[] row = new Object[6];
					row[0] = oferta.getId();
					row[1] = dateFormat.format(oferta.getFechaInicio());
					row[2] = dateFormat.format(oferta.getFechaFin());
					row[3] = decimalFormat.format(oferta.getPrecioOferta());
					row[4] = decimalFormat.format(descuentoTotal) + "%";
					row[5] = oferta.getProducto().getNombre();
					modelOfertas.addRow(row);
				}

				// Mostrar mensaje de éxito
				JOptionPane.showMessageDialog(null, "Oferta agregada exitosamente.");

				// Limpiar los campos
				comboBoxOferta.setSelectedIndex(0);
				fechaInicioChooser.setDate(null);
				fechaFinChooser.setDate(null);
				comboBoxDescuento.setSelectedIndex(0);
			}
		});
		btnNuevaOferta.setBorder(null);
		btnNuevaOferta.setIcon(new ImageIcon(Supermercado.class.getResource("/img/anadir.png")));
		btnNuevaOferta.setBackground(new Color(255, 255, 255));
		btnNuevaOferta.setBounds(1037, 314, 90, 60);
		frmSuper.getContentPane().add(btnNuevaOferta);

		// Botón Actualizar
		JButton btnActualizarOferta = new JButton("");
		btnActualizarOferta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Obtener la fila seleccionada en la tabla
				int filaSeleccionada = tableOfertas.getSelectedRow();

				// Validar que se haya seleccionado una fila
				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una oferta para actualizar.");
					return;
				}

				// Obtener el ID de la oferta seleccionada
				int idOferta = (int) tableOfertas.getValueAt(filaSeleccionada, 0);

				// Obtener el producto seleccionado del JComboBox
				Producto productoSeleccionado = (Producto) comboBoxOferta.getSelectedItem();

				// Obtener las fechas seleccionadas de los JDateChooser
				Date fechaInicio = fechaInicioChooser.getDate();
				Date fechaFin = fechaFinChooser.getDate();

				// Obtener el descuento seleccionado del JComboBox
				String descuentoSeleccionado = (String) comboBoxDescuento.getSelectedItem();

				// Validar que se hayan seleccionado un producto, las fechas y el descuento
				if (productoSeleccionado == null || fechaInicio == null || fechaFin == null
						|| descuentoSeleccionado == null) {
					JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
					return;
				}

				// Validar que la fecha de inicio sea antes de la fecha de fin
				if (fechaInicio.after(fechaFin)) {
					JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser anterior a la fecha de fin.");
					return;
				}

				// Validar que la fecha de inicio sea posterior a la fecha actual
				Date fechaActual = new Date();
				if (fechaInicio.before(fechaActual)) {
					JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser posterior a la fecha actual.");
					return;
				}

				// Obtener el precio normal del producto
				double precioNormal = productoSeleccionado.getPrecio();

				// Calcular el precio de la oferta aplicando el descuento seleccionado
				double precioOferta;
				if (descuentoSeleccionado.equals("Sin descuento")) {
					precioOferta = precioNormal;
				} else if (descuentoSeleccionado.equals("10% de descuento")) {
					precioOferta = precioNormal * 0.9;
				} else if (descuentoSeleccionado.equals("20% de descuento")) {
					precioOferta = precioNormal * 0.8;
				} else if (descuentoSeleccionado.equals("30% de descuento")) {
					precioOferta = precioNormal * 0.7;
				} else if (descuentoSeleccionado.equals("40% de descuento")) {
					precioOferta = precioNormal * 0.6;
				} else if (descuentoSeleccionado.equals("50% de descuento")) {
					precioOferta = precioNormal * 0.5;
				} else if (descuentoSeleccionado.equals("60% de descuento")) {
					precioOferta = precioNormal * 0.4;
				} else if (descuentoSeleccionado.equals("70% de descuento")) {
					precioOferta = precioNormal * 0.3;
				} else {
					// Opción de descuento no válida
					JOptionPane.showMessageDialog(null, "Descuento seleccionado no válido.");
					return;
				}

				// Crear una instancia de la clase Ofertas y asignar los valores
				Oferta ofertaActualizada = new Oferta();
				ofertaActualizada.setId(idOferta);
				ofertaActualizada.setProducto(productoSeleccionado);
				ofertaActualizada.setFechaInicio(fechaInicio);
				ofertaActualizada.setFechaFin(fechaFin);
				ofertaActualizada.setPrecioOferta(precioOferta);

				// Actualizar la oferta en la base de datos
				almacenDAO.updateOferta(ofertaActualizada);

				modelOfertas.setRowCount(0);

				DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Formato para mostrar máximo dos decimales
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES")); // Formato de
																											// fecha
				List<Oferta> listaOfertas = almacenDAO.selectAllOfertas();
				for (Oferta oferta : listaOfertas) {
					double descuentoTotal = (1 - (oferta.getPrecioOferta() / oferta.getProducto().getPrecio())) * 100;
					Object[] row = new Object[6];
					row[0] = oferta.getId();
					row[1] = dateFormat.format(oferta.getFechaInicio());
					row[2] = dateFormat.format(oferta.getFechaFin());
					row[3] = decimalFormat.format(oferta.getPrecioOferta());
					row[4] = decimalFormat.format(descuentoTotal) + "%";
					row[5] = oferta.getProducto().getNombre();
					modelOfertas.addRow(row);
				}

				// Mostrar mensaje de éxito
				JOptionPane.showMessageDialog(null, "Oferta actualizada exitosamente.");

				// Limpiar los campos
				comboBoxOferta.setSelectedIndex(0);
				fechaInicioChooser.setDate(null);
				fechaFinChooser.setDate(null);
				comboBoxDescuento.setSelectedIndex(0);
			}
		});
		btnActualizarOferta.setBorder(null);
		btnActualizarOferta.setIcon(new ImageIcon(Supermercado.class.getResource("/img/actualizar.png")));
		btnActualizarOferta.setBackground(new Color(255, 255, 255));
		btnActualizarOferta.setBounds(1172, 314, 90, 60);
		frmSuper.getContentPane().add(btnActualizarOferta);

		// Botón Borrar
		JButton btnBorrar = new JButton("");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Obtener la fila seleccionada en la tabla
				int filaSeleccionada = tableOfertas.getSelectedRow();

				// Validar que se haya seleccionado una fila
				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una oferta para borrar.");
					return;
				}

				// Obtener el ID de la oferta seleccionada
				int idOferta = (int) tableOfertas.getValueAt(filaSeleccionada, 0);

				// Confirmar si se desea borrar la oferta
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de borrar la oferta?",
						"Confirmar borrado", JOptionPane.YES_NO_OPTION);

				if (confirmacion == JOptionPane.YES_OPTION) {
					// Borrar la oferta de la base de datos
					almacenDAO.deleteOferta(idOferta);

					// Obtener el modelo de la tabla
					DefaultTableModel modelOfertas = (DefaultTableModel) tableOfertas.getModel();

					// Eliminar la fila correspondiente a la oferta borrada
					modelOfertas.removeRow(filaSeleccionada);

					// Mostrar mensaje de éxito
					JOptionPane.showMessageDialog(null, "Oferta borrada exitosamente.");
				}
			}
		});
		btnBorrar.setBorder(null);
		btnBorrar.setIcon(new ImageIcon(Supermercado.class.getResource("/img/borrar.png")));
		btnBorrar.setBackground(new Color(255, 255, 255));
		btnBorrar.setBounds(1301, 314, 90, 60);
		frmSuper.getContentPane().add(btnBorrar);

		JLabel lblFechaInicio_1_1 = new JLabel("Descuento:");
		lblFechaInicio_1_1.setForeground(new Color(238, 68, 93));
		lblFechaInicio_1_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblFechaInicio_1_1.setBounds(1037, 129, 105, 26);
		frmSuper.getContentPane().add(lblFechaInicio_1_1);

		// Botón Mostrar sin Stock
		JButton btnMostrarSinStock = new JButton("MOSTRAR SIN STOCK");
		btnMostrarSinStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualizar la tabla de productos
				DefaultTableModel modelProductos = (DefaultTableModel) tableProductos.getModel();
				modelProductos.setRowCount(0);

				List<Producto> listaProductosSinStock = almacenDAO.selectProductosSinStock();
				for (Producto producto : listaProductosSinStock) {
					Object[] row = new Object[5];
					row[0] = producto.getId();
					row[1] = producto.getNombre();
					row[2] = producto.getPrecio();
					row[3] = producto.getCantidadStock();
					row[4] = producto.getCategoria();
					modelProductos.addRow(row);
				}

				// Mostrar mensaje de éxito
				JOptionPane.showMessageDialog(null, "Se han mostrado los productos sin stock.");
			}
		});
		btnMostrarSinStock.setBorder(null);
		btnMostrarSinStock.setForeground(new Color(238, 68, 93));
		btnMostrarSinStock.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		btnMostrarSinStock.setBackground(Color.WHITE);
		btnMostrarSinStock.setBounds(63, 516, 354, 40);
		frmSuper.getContentPane().add(btnMostrarSinStock);

		JLabel lblOfertas = new JLabel("Ofertas");
		lblOfertas.setBounds(480, 401, 140, 40);
		frmSuper.getContentPane().add(lblOfertas);
		lblOfertas.setForeground(new Color(238, 68, 93));
		lblOfertas.setFont(new Font("Corbel", Font.BOLD, 30));

		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setBounds(480, 30, 160, 40);
		lblProductos.setForeground(new Color(238, 68, 93));
		lblProductos.setFont(new Font("Corbel", Font.BOLD, 30));
		frmSuper.getContentPane().add(lblProductos);

	}
}