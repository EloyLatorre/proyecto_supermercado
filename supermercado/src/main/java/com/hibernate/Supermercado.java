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
import javax.swing.ComboBoxModel;
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
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.util.Date;
import java.text.DecimalFormat;



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
		frmSuper.setBounds(100, 100, 1510, 700);
		frmSuper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSuper.getContentPane().setLayout(null);
		JPanel panelProductos = new JPanel();
		panelProductos.setBackground(Color.DARK_GRAY);
		panelProductos.setBounds(470, 67, 524, 358);
		frmSuper.getContentPane().add(panelProductos);
		panelProductos.setLayout(null);

		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setForeground(new Color(238, 68, 93));
		lblProductos.setFont(new Font("Corbel", Font.BOLD, 30));
		lblProductos.setBounds(201, 6, 171, 46);
		panelProductos.add(lblProductos);

		JComboBox<String> comboBoxCategorias = new JComboBox<>();
		comboBoxCategorias.setBounds(144, 115, 288, 27);
		frmSuper.getContentPane().add(comboBoxCategorias);

		JScrollPane scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(10, 57, 508, 290);
		panelProductos.add(scrollPaneProductos);

		// Crear la tabla
		JTable tableProductos = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			// Hace que las celdas de la tabla no se puedan editar
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Obtener todos los productos de la base de datos
		List<Productos> productos = AlmacenDAO.selectAllProductos();

		// Crear el modelo de tabla
		DefaultTableModel modelProductos = new DefaultTableModel();
		modelProductos.addColumn("ID");
		modelProductos.addColumn("Nombre");
		modelProductos.addColumn("Precio");
		modelProductos.addColumn("Cantidad");
		modelProductos.addColumn("Categoría"); // Nueva columna para la categoría

		// Agregar los datos de los productos al modelo de tabla
		for (Productos producto : productos) {
			Object[] rowData = { producto.getId(), producto.getNombre(), producto.getPrecio(),
					producto.getCantidadStock(), producto.getCategorias().getNombre() // Obtener el nombre de la
																						// categoría
			};
			modelProductos.addRow(rowData);
		}

		// Asignar el modelo de tabla a la tabla
		tableProductos.setModel(modelProductos);
		tableProductos.addMouseListener(new MouseAdapter() {
			@Override
			// Al clicar en alguna de las celdas, se rellenan todos los campos con todos los
			// datos correspondientes a la fila de la misma celda
			public void mouseClicked(MouseEvent e) {
				int index = tableProductos.getSelectedRow();
				TableModel model = tableProductos.getModel();
				textFieldNombre.setText(model.getValueAt(index, 1).toString());
				textFieldPrecio.setText(model.getValueAt(index, 2).toString());
				textFieldStock.setText(model.getValueAt(index, 3).toString());
				comboBoxCategorias.setSelectedItem(model.getValueAt(index, 4).toString());
			}
		});

		// Agregar la tabla al JScrollPane
		scrollPaneProductos.setViewportView(tableProductos);

		listProductos = new JList<>();
		listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// scrollPaneProductos.setViewportView(listProductos);

		// Agregar la tabla al JScrollPane
		scrollPaneProductos.setViewportView(tableProductos);
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(238, 68, 93));
		lblNombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblNombre.setBounds(63, 67, 69, 26);
		frmSuper.getContentPane().add(lblNombre);

		JLabel lblCategora = new JLabel("Categoría");
		lblCategora.setForeground(new Color(238, 68, 93));
		lblCategora.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblCategora.setBounds(63, 115, 80, 26);
		frmSuper.getContentPane().add(lblCategora);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setForeground(new Color(238, 68, 93));
		lblPrecio.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblPrecio.setBounds(63, 163, 57, 26);
		frmSuper.getContentPane().add(lblPrecio);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(new Color(238, 68, 93));
		lblStock.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblStock.setBounds(63, 211, 48, 26);
		frmSuper.getContentPane().add(lblStock);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(144, 67, 288, 26);
		frmSuper.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(144, 163, 288, 26);
		frmSuper.getContentPane().add(textFieldPrecio);
		textFieldPrecio.setColumns(10);

		textFieldStock = new JTextField();
		textFieldStock.setBounds(144, 211, 288, 26);
		frmSuper.getContentPane().add(textFieldStock);
		textFieldStock.setColumns(10);
		// Obtener todas las categorías de la base de datos
		List<Categorias> categoriasList = AlmacenDAO.selectAllCategorias();

		// Convertir la lista de objetos Categorias a una lista de cadenas (nombres de
		// categorías)
		List<String> categorias = new ArrayList<>();
		for (Categorias categoria : categoriasList) {
			categorias.add(categoria.getNombre());
		}

		comboBoxCategorias.setModel(new DefaultComboBoxModel<>(categorias.toArray(new String[0])));

		// Obtener todos los productos de la base de datos
		List<Productos> productos2 = AlmacenDAO.selectAllProductos();
		// Mostrar los productos en la lista
		DefaultListModel<String> modelProductos2 = new DefaultListModel<>();
		for (Productos producto : productos) {
			modelProductos2.addElement(producto.getNombre());
		}
		listProductos.setModel(modelProductos2);
		JButton btnAgregar = new JButton("");
		btnAgregar.setBackground(new Color(255, 255, 255));
		btnAgregar.setIcon(new ImageIcon(Supermercado.class.getResource("/img/anadir.png")));
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
				Categorias categoriaSeleccionada = null;
				for (Categorias cat : categoriasList) {
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
				Productos producto = new Productos();
				producto.setNombre(nombre);
				producto.setPrecio(precio);
				producto.setCantidadStock(cantidad);
				producto.setCategorias(categoriaSeleccionada);

				// Insertar el producto en la base de datos
				AlmacenDAO almacenDAO = new AlmacenDAO();
				almacenDAO.insertProductos(producto);

				// Limpiar los campos de entrada
				textFieldNombre.setText("");
				textFieldPrecio.setText("");
				textFieldStock.setText("");

				modelProductos.setRowCount(0);

				List<Productos> listaProductos = almacenDAO.selectAllProductos();
				for (Productos productoAct : listaProductos) {
					Object[] row = new Object[5];
					row[0] = productoAct.getId();
					row[1] = productoAct.getNombre();
					row[2] = productoAct.getPrecio();
					row[3] = productoAct.getCantidadStock();
					row[4] = productoAct.getCategorias();
					modelProductos.addRow(row);
				}

				JOptionPane.showMessageDialog(null, "¡Producto agregado exitosamente!", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAgregar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnAgregar.setBounds(32, 300, 130, 59);
		frmSuper.getContentPane().add(btnAgregar);

		JButton btnActualizar = new JButton("");
		btnActualizar.setBackground(new Color(255, 255, 255));
		btnActualizar.setIcon(new ImageIcon(Supermercado.class.getResource("/img/actualizar.png")));
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				AlmacenDAO almacenDAO = new AlmacenDAO();
				Productos producto = almacenDAO.selectProductosById(productId);
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
				Categorias categoriaSeleccionada = null;
				for (Categorias cat : categoriasList) {
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
				producto.setCategorias(categoriaSeleccionada);

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
		btnActualizar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnActualizar.setBounds(182, 300, 130, 59);
		frmSuper.getContentPane().add(btnActualizar);

		JButton btnEliminar = new JButton("");
		btnEliminar.setBackground(new Color(255, 255, 255));
		btnEliminar.setIcon(new ImageIcon(Supermercado.class.getResource("/img/borrar.png")));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
					AlmacenDAO almacenDAO = new AlmacenDAO();
					almacenDAO.deleteProductos(productId);

					// Eliminar la fila correspondiente en la tabla
					modelProductos.removeRow(selectedRow);

					JOptionPane.showMessageDialog(null, "¡Producto eliminado exitosamente!", "Información",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		btnEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnEliminar.setBounds(330, 300, 130, 59);
		frmSuper.getContentPane().add(btnEliminar);

		JButton btnMostrarTodos = new JButton("Mostrar todos");
		btnMostrarTodos.setBackground(new Color(255, 255, 255));
		btnMostrarTodos.setForeground(new Color(238, 68, 93));
		btnMostrarTodos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Eliminar cualquier filtro aplicado a la tabla
				tableProductos.setRowSorter(null);
			}
		});

		btnMostrarTodos.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		btnMostrarTodos.setBounds(32, 435, 428, 40);
		frmSuper.getContentPane().add(btnMostrarTodos);

		JComboBox<String> comboBoxCategoriaConcreta = new JComboBox<String>();
		comboBoxCategoriaConcreta.setBounds(32, 537, 428, 40);
		frmSuper.getContentPane().add(comboBoxCategoriaConcreta);

		// Obtener todas las categorías de la base de datos
		List<Categorias> categorias2 = AlmacenDAO.selectAllCategorias();

		// Agregar las categorías al JComboBox
		for (Categorias categoria2 : categorias2) {
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

		JLabel lblMostrarCategoraConcreta = new JLabel("Mostrar Categoría Concreta");
		lblMostrarCategoraConcreta.setForeground(new Color(238, 68, 93));
		lblMostrarCategoraConcreta.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblMostrarCategoraConcreta.setBounds(51, 500, 360, 26);
		frmSuper.getContentPane().add(lblMostrarCategoraConcreta);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setForeground(new Color(238, 68, 93));
		lblFechaFin.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblFechaFin.setBounds(1030, 254, 105, 26);
		frmSuper.getContentPane().add(lblFechaFin);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setForeground(new Color(238, 68, 93));
		lblFechaInicio.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblFechaInicio.setBounds(1030, 187, 105, 26);
		frmSuper.getContentPane().add(lblFechaInicio);
		
		JLabel lblNuevoProducto = new JLabel("Gestión de Productos");
		lblNuevoProducto.setForeground(new Color(238, 68, 93));
		lblNuevoProducto.setFont(new Font("Corbel", Font.BOLD, 30));
		lblNuevoProducto.setBounds(68, 11, 364, 46);
		frmSuper.getContentPane().add(lblNuevoProducto);
		
		JLabel lblGestinDeOfertas = new JLabel("Gestión de Ofertas");
		lblGestinDeOfertas.setForeground(new Color(238, 68, 93));
		lblGestinDeOfertas.setFont(new Font("Corbel", Font.BOLD, 30));
		lblGestinDeOfertas.setBounds(1015, 11, 364, 46);
		frmSuper.getContentPane().add(lblGestinDeOfertas);
		
		JLabel lblFechaInicio_1 = new JLabel("Producto:");
		lblFechaInicio_1.setForeground(new Color(238, 68, 93));
		lblFechaInicio_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblFechaInicio_1.setBounds(1030, 68, 105, 26);
		frmSuper.getContentPane().add(lblFechaInicio_1);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Supermercado.class.getResource("/img/logosuper.png")));
		lblLogo.setBounds(1321, 484, 149, 142);
		frmSuper.getContentPane().add(lblLogo);
		
		JPanel panelOfertas = new JPanel();
		panelOfertas.setLayout(null);
		panelOfertas.setBackground(Color.DARK_GRAY);
		panelOfertas.setBounds(470, 436, 524, 214);
		frmSuper.getContentPane().add(panelOfertas);
		
		JLabel lblOfertas = new JLabel("Ofertas");
		lblOfertas.setForeground(new Color(238, 68, 93));
		lblOfertas.setFont(new Font("Corbel", Font.BOLD, 30));
		lblOfertas.setBounds(202, 0, 171, 46);
		panelOfertas.add(lblOfertas);
		
		JScrollPane scrollPaneOfertas = new JScrollPane();
		scrollPaneOfertas.setBounds(6, 52, 508, 151);
		panelOfertas.add(scrollPaneOfertas);
		
		
		
		
		
		// Crear la tabla
				JTable tableOfertas = new JTable() {
				    private static final long serialVersionUID = 1L;

				    @Override
				    // Hace que las celdas de la tabla no se puedan editar
				    public boolean isCellEditable(int row, int column) {
				        return false;
				    }
				};

				// Obtener todas las ofertas de la base de datos
				List<Ofertas> ofertas = AlmacenDAO.selectAllOfertas();

				// Crear el modelo de tabla
				DefaultTableModel modelOfertas = new DefaultTableModel();
				modelOfertas.addColumn("ID");
				modelOfertas.addColumn("Fecha Inicio");
				modelOfertas.addColumn("Fecha Fin");
				modelOfertas.addColumn("Precio Oferta");
				modelOfertas.addColumn("Producto"); // Nueva columna para el producto
				DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Formato para mostrar máximo dos decimales

				for (Ofertas oferta : ofertas) {
				    Object[] rowData = { oferta.getId(), oferta.getProducto().getNombre(),
				                         oferta.getFechaInicio(), oferta.getFechaFin(),
				                         decimalFormat.format(oferta.getPrecioOferta()) }; // Aplicar formato decimal
				    modelOfertas.addRow(rowData);
				}

				// Asignar el modelo de tabla a la tabla
				tableOfertas.setModel(modelOfertas);

				// Agregar la tabla al JScrollPane
				scrollPaneOfertas.setViewportView(tableOfertas);
		
		JDateChooser fechaInicioChooser = new JDateChooser();
		fechaInicioChooser.setBounds(1145, 183, 288, 30);
		frmSuper.getContentPane().add(fechaInicioChooser);

		JDateChooser fechaFinChooser = new JDateChooser();
		fechaFinChooser.setBounds(1145, 250, 288, 30);
		frmSuper.getContentPane().add(fechaFinChooser);
		
		JComboBox comboBoxOferta = new JComboBox();
		comboBoxOferta.setBounds(1145, 67, 288, 27);
		frmSuper.getContentPane().add(comboBoxOferta);
		// Obtener todos los productos de la base de datos
		List<Productos> productosOferta = AlmacenDAO.selectAllProductos();

		// Crear un modelo de ComboBox
		DefaultComboBoxModel<Productos> comboBoxModel = new DefaultComboBoxModel<>();

		// Agregar los productos al modelo del ComboBox
		for (Productos producto : productosOferta) {
		    comboBoxModel.addElement(producto);
		}

		// Asignar el modelo del ComboBox al JComboBox
		comboBoxOferta.setModel(comboBoxModel);

		
		
		JComboBox comboBoxDescuento = new JComboBox();
		comboBoxDescuento.setBounds(1145, 120, 288, 27);
		frmSuper.getContentPane().add(comboBoxDescuento);
		comboBoxDescuento.addItem("10% de descuento");
		comboBoxDescuento.addItem("20% de descuento");
		comboBoxDescuento.addItem("30% de descuento");
		comboBoxDescuento.addItem("40% de descuento");
		comboBoxDescuento.addItem("50% de descuento");
		comboBoxDescuento.addItem("60% de descuento");
		comboBoxDescuento.addItem("70% de descuento");

		
		// Botón Nueva Oferta
		JButton btnNuevaOferta = new JButton("");
		btnNuevaOferta.setIcon(new ImageIcon(Supermercado.class.getResource("/img/anadir.png")));
		btnNuevaOferta.setBackground(new Color(255, 255, 255));
		btnNuevaOferta.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Obtener el producto seleccionado del JComboBox
		        Productos productoSeleccionado = (Productos) comboBoxOferta.getSelectedItem();
		        
		        // Obtener las fechas seleccionadas de los JDateChooser
		        Date fechaInicio = fechaInicioChooser.getDate();
		        Date fechaFin = fechaFinChooser.getDate();
		        
		        // Obtener el descuento seleccionado del JComboBox
		        String descuentoSeleccionado = (String) comboBoxDescuento.getSelectedItem();
		        
		        // Validar que se hayan seleccionado un producto, las fechas y el descuento
		        if (productoSeleccionado == null || fechaInicio == null || fechaFin == null || descuentoSeleccionado == null) {
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
		        Ofertas nuevaOferta = new Ofertas();
		        nuevaOferta.setProducto(productoSeleccionado);
		        nuevaOferta.setFechaInicio(fechaInicio);
		        nuevaOferta.setFechaFin(fechaFin);
		        nuevaOferta.setPrecioOferta(precioOferta);
		        
		        // Guardar la nueva oferta en la base de datos
		        AlmacenDAO.insertOferta(nuevaOferta);
		        
		        // Actualizar la tabla de ofertas
		        List<Ofertas> ofertas = AlmacenDAO.selectAllOfertas();
		        
		        DefaultTableModel modelOfertas = (DefaultTableModel) tableOfertas.getModel();
		        modelOfertas.setRowCount(0);
		        
		        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Formato para mostrar máximo dos decimales
		        
		        for (Ofertas oferta : ofertas) {
		            Object[] rowData = { oferta.getId(), oferta.getProducto().getNombre(),
		                                 oferta.getFechaInicio(), oferta.getFechaFin(),
		                                 decimalFormat.format(oferta.getPrecioOferta()) }; // Aplicar formato decimal
		            modelOfertas.addRow(rowData);
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
		btnNuevaOferta.setBounds(1040, 300, 112, 66);
		frmSuper.getContentPane().add(btnNuevaOferta);


		
		// Botón Actualizar
		JButton btnActualizarOferta = new JButton("");
		btnActualizarOferta.setIcon(new ImageIcon(Supermercado.class.getResource("/img/actualizar.png")));
		btnActualizarOferta.setBackground(new Color(255, 255, 255));
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
		        Productos productoSeleccionado = (Productos) comboBoxOferta.getSelectedItem();
		        
		        // Obtener las fechas seleccionadas de los JDateChooser
		        Date fechaInicio = fechaInicioChooser.getDate();
		        Date fechaFin = fechaFinChooser.getDate();
		        
		        // Obtener el descuento seleccionado del JComboBox
		        String descuentoSeleccionado = (String) comboBoxDescuento.getSelectedItem();
		        
		        // Validar que se hayan seleccionado un producto, las fechas y el descuento
		        if (productoSeleccionado == null || fechaInicio == null || fechaFin == null || descuentoSeleccionado == null) {
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
		        Ofertas ofertaActualizada = new Ofertas();
		        ofertaActualizada.setId(idOferta);
		        ofertaActualizada.setProducto(productoSeleccionado);
		        ofertaActualizada.setFechaInicio(fechaInicio);
		        ofertaActualizada.setFechaFin(fechaFin);
		        ofertaActualizada.setPrecioOferta(precioOferta);
		        
		        // Actualizar la oferta en la base de datos
		        AlmacenDAO.updateOferta(ofertaActualizada);
		        
		        // Actualizar la tabla de ofertas
		        List<Ofertas> ofertas = AlmacenDAO.selectAllOfertas();
		        
		        DefaultTableModel modelOfertas = (DefaultTableModel) tableOfertas.getModel();
		        modelOfertas.setRowCount(0);
		        
		        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Formato para mostrar máximo dos decimales
		        
		        for (Ofertas oferta : ofertas) {
		            Object[] rowData = { oferta.getId(), oferta.getProducto().getNombre(),
		                                 oferta.getFechaInicio(), oferta.getFechaFin(),
		                                 decimalFormat.format(oferta.getPrecioOferta()) }; // Aplicar formato decimal
		            modelOfertas.addRow(rowData);
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
		btnActualizarOferta.setBounds(1175, 300, 112, 66);
		frmSuper.getContentPane().add(btnActualizarOferta);

		
		// Botón Borrar
		JButton btnBorrar = new JButton("");
		btnBorrar.setIcon(new ImageIcon(Supermercado.class.getResource("/img/borrar.png")));
		btnBorrar.setBackground(new Color(255, 255, 255));
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
		            AlmacenDAO.deleteOferta(idOferta);
		            
		            // Obtener el modelo de la tabla
		            DefaultTableModel modelOfertas = (DefaultTableModel) tableOfertas.getModel();
		            
		            // Eliminar la fila correspondiente a la oferta borrada
		            modelOfertas.removeRow(filaSeleccionada);
		            
		            // Mostrar mensaje de éxito
		            JOptionPane.showMessageDialog(null, "Oferta borrada exitosamente.");
		        }
		    }
		});
		btnBorrar.setBounds(1321, 300, 112, 66);
		frmSuper.getContentPane().add(btnBorrar);

		
		JLabel lblFechaInicio_1_1 = new JLabel("Oferta:");
		lblFechaInicio_1_1.setForeground(new Color(238, 68, 93));
		lblFechaInicio_1_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblFechaInicio_1_1.setBounds(1030, 121, 105, 26);
		frmSuper.getContentPane().add(lblFechaInicio_1_1);



		



	}
}