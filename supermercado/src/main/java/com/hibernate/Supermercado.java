/**
 * @author Jaime Roselló Gómez & Eloy Latorre Briones
 * @version Final release
 */

package com.hibernate;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.List;
import java.util.Locale;
import java.awt.Color;
import java.awt.Desktop;

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

import com.formdev.flatlaf.json.ParseException;
import com.hibernate.dao.AlmacenDAO;
import com.hibernate.model.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
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
		frmSuper.setTitle("HacenDAO");
		frmSuper.setBounds(100, 100, 1460, 720);
		frmSuper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSuper.getContentPane().setLayout(null);

		// Creación de los objetos bordes, que se utilizan posteriormente en los
		// textfields
		Border empty = new EmptyBorder(0, 5, 0, 0);

		// Creación del objeto almacenDAO para llamar a las funciones
		AlmacenDAO almacenDAO = new AlmacenDAO();

		JLabel lblGestionDeOfertas = new JLabel("Gestión de Ofertas");
		lblGestionDeOfertas.setForeground(new Color(238, 68, 93));
		lblGestionDeOfertas.setFont(new Font("Dialog", Font.BOLD, 25));
		lblGestionDeOfertas.setBounds(1037, 33, 270, 40);
		frmSuper.getContentPane().add(lblGestionDeOfertas);

		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setForeground(new Color(238, 68, 93));
		lblProducto.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblProducto.setBounds(1037, 84, 105, 25);
		frmSuper.getContentPane().add(lblProducto);

		JLabel lblDescuento = new JLabel("Descuento:");
		lblDescuento.setForeground(new Color(238, 68, 93));
		lblDescuento.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblDescuento.setBounds(1037, 129, 105, 26);
		frmSuper.getContentPane().add(lblDescuento);

		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setForeground(new Color(238, 68, 93));
		lblFechaInicio.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblFechaInicio.setBounds(1037, 180, 105, 25);
		frmSuper.getContentPane().add(lblFechaInicio);

		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setForeground(new Color(238, 68, 93));
		lblFechaFin.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblFechaFin.setBounds(1037, 228, 105, 25);
		frmSuper.getContentPane().add(lblFechaFin);

		// Obteniene los productos de la base de datos y los guarda en un modelo
		List<Producto> productosOferta = almacenDAO.selectAllProductos();
		DefaultComboBoxModel<Producto> comboBoxModel = new DefaultComboBoxModel<>();
		for (Producto producto : productosOferta) {
			comboBoxModel.addElement(producto);
		}

		/**
		 * Creación del combobox en el que se muestran todos los prodoctos y al que se
		 * le aplica el modelo con todos los productos
		 */
		JComboBox comboBoxProducto = new JComboBox();
		((JLabel) comboBoxProducto.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxProducto.setBorder(null);
		comboBoxProducto.setBounds(1156, 84, 235, 25);
		comboBoxProducto.setModel(comboBoxModel);
		frmSuper.getContentPane().add(comboBoxProducto);

		/**
		 * Creación del combobox en el que se selecciona el descuento en la oferta
		 */
		Oferta oferta = new Oferta();
		JComboBox comboBoxDescuento = new JComboBox();
		((JLabel) comboBoxDescuento.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxDescuento.setBorder(null);
		comboBoxDescuento.setBounds(1156, 132, 235, 25);
		comboBoxDescuento.addItem("10%");
		comboBoxDescuento.addItem("20%");
		comboBoxDescuento.addItem("30%");
		comboBoxDescuento.addItem("40%");
		comboBoxDescuento.addItem("50%");
		comboBoxDescuento.addItem("60%");
		comboBoxDescuento.addItem("70%");
		comboBoxDescuento.setSelectedIndex(0);

		// Aplica el descuento a la oferta
		String selectedDescuento = (String) comboBoxDescuento.getSelectedItem();
		oferta.setDescuento(Integer.parseInt(selectedDescuento.substring(0, selectedDescuento.length() - 1)));

		frmSuper.getContentPane().add(comboBoxDescuento);
		;

		JDateChooser fechaInicioChooser = new JDateChooser();
		fechaInicioChooser.setBounds(1156, 180, 235, 25);
		fechaInicioChooser.setDateFormatString("dd/MM/yyyy");
		frmSuper.getContentPane().add(fechaInicioChooser);

		JDateChooser fechaFinChooser = new JDateChooser();
		fechaFinChooser.setBounds(1156, 228, 235, 25);
		fechaFinChooser.setDateFormatString("dd/MM/yyyy");
		frmSuper.getContentPane().add(fechaFinChooser);

		JLabel lblOfertas = new JLabel("Ofertas");
		lblOfertas.setBounds(480, 401, 140, 40);
		frmSuper.getContentPane().add(lblOfertas);
		lblOfertas.setForeground(new Color(238, 68, 93));
		lblOfertas.setFont(new Font("Dialog", Font.BOLD, 25));

		/**
		 * Creación de un modelo de tabla básico
		 */
		DefaultTableModel modelOfertas = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			// Hace que las celdas de la tabla no se puedan editar
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelOfertas.addColumn("ID");
		modelOfertas.addColumn("Fecha Inicio");
		modelOfertas.addColumn("Fecha Fin");
		modelOfertas.addColumn("Precio Oferta");
		modelOfertas.addColumn("Descuento");
		modelOfertas.addColumn("Producto");

		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));

		// Crea una lista en la que se añaden todas las ofertas que hay en la base de
		// datos y se añaden a la tabla
		List<Oferta> listaOfertas = almacenDAO.selectAllOfertas();
		for (Oferta oferta1 : listaOfertas) {
			Object[] row = new Object[6];
			row[0] = oferta1.getId();
			row[1] = dateFormat.format(oferta1.getFechaInicio());
			row[2] = dateFormat.format(oferta1.getFechaFin());
			row[3] = decimalFormat.format(oferta1.getPrecioOferta());
			row[4] = decimalFormat.format(oferta1.getDescuento()) + "%";
			row[5] = oferta1.getProducto().getNombre();
			modelOfertas.addRow(row);
		}

		/**
		 * Creación de un modelo de tabla personalizada en la que se muestran las
		 * ofertas de los productos
		 */
		TableRed tableOfertas = new TableRed();
		tableOfertas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableOfertas.getSelectedRow();
				TableModel model = tableOfertas.getModel();

				// Convertir y asignar el valor del producto
				String nombreProducto = model.getValueAt(index, 5).toString();
				Producto productoSeleccionado = null;
				for (Producto producto : productosOferta) {
					if (producto.getNombre().equals(nombreProducto)) {
						productoSeleccionado = producto;
						break;
					}
				}
				if (productoSeleccionado != null) {
				    if (((DefaultComboBoxModel<Producto>) comboBoxProducto.getModel()).getIndexOf(productoSeleccionado) != -1) {
				        comboBoxProducto.setSelectedItem(productoSeleccionado);
				    } else {
				        JOptionPane.showMessageDialog(null, "¡El producto seleccionado no existe en el combobox!", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				    }
				}

				// Convertir y asignar el valor del descuento
				String descuento = model.getValueAt(index, 4).toString();
				comboBoxDescuento.setSelectedItem(descuento);

				// Convertir y asignar las fechas de inicio y fin
				try {
					Date fechaInicio = dateFormat.parse(model.getValueAt(index, 1).toString());
					fechaInicioChooser.setDate(fechaInicio);

					Date fechaFin = dateFormat.parse(model.getValueAt(index, 2).toString());
					fechaFinChooser.setDate(fechaFin);
				} catch (ParseException parseException) {
					parseException.printStackTrace();
				} catch (java.text.ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tableOfertas.setModel(modelOfertas);
		tableOfertas.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableOfertas.getColumnModel().getColumn(5).setPreferredWidth(100);
		tableOfertas.setColumnAlignment(0, JLabel.CENTER);
		tableOfertas.setCellAlignment(0, JLabel.CENTER);
		tableOfertas.setColumnAlignment(1, JLabel.CENTER);
		tableOfertas.setCellAlignment(1, JLabel.CENTER);
		tableOfertas.setColumnAlignment(2, JLabel.CENTER);
		tableOfertas.setCellAlignment(2, JLabel.CENTER);
		tableOfertas.setColumnAlignment(3, JLabel.CENTER);
		tableOfertas.setCellAlignment(3, JLabel.CENTER);
		tableOfertas.setColumnAlignment(4, JLabel.CENTER);
		tableOfertas.setCellAlignment(4, JLabel.CENTER);
		tableOfertas.setColumnAlignment(5, JLabel.CENTER);
		tableOfertas.setCellAlignment(5, JLabel.CENTER);

		JScrollPane scrollPaneOfertas = new JScrollPane(tableOfertas);
		scrollPaneOfertas.setViewportBorder(null);
		scrollPaneOfertas.setBackground(new Color(238, 68, 93));
		scrollPaneOfertas.setBorder(new LineBorder(new Color(252, 138, 25), 0, true));
		scrollPaneOfertas.setBounds(480, 452, 508, 192);
		scrollPaneOfertas.getVerticalScrollBar().setBackground(new Color(252, 138, 25));
		scrollPaneOfertas.setVerticalScrollBar(new ScrollBarCustom());
		frmSuper.getContentPane().add(scrollPaneOfertas);

		/**
		 * Creación del botón que añade nuevas ofertas
		 */
		JButton btnNuevaOferta = new JButton("");
		btnNuevaOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtiene el producto seleccionado del JComboBox
				Producto productoSeleccionado = (Producto) comboBoxProducto.getSelectedItem();

				// Obtiene las fechas seleccionadas de los JDateChooser
				Date fechaInicio = fechaInicioChooser.getDate();
				Date fechaFin = fechaFinChooser.getDate();

				// Obtiene el descuento seleccionado del JComboBox
				String descuentoSeleccionado = (String) comboBoxDescuento.getSelectedItem();

				// Comprueba si ya existe una oferta para el producto y las fechas seleccionadas
				if (almacenDAO.existsOfertaForProductAndDates(productoSeleccionado, fechaInicio, fechaFin)) {
					JOptionPane.showMessageDialog(null,
							"¡Ya existe una oferta para este producto con las mismas fechas!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Valida que se hayan seleccionado un producto, las fechas y el descuento
				if (productoSeleccionado == null || fechaInicio == null || fechaFin == null
						|| descuentoSeleccionado == null) {
					JOptionPane.showMessageDialog(null, "¡Por favor, complete todos los campos!", "CUIDADO",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Valida que la fecha de inicio sea antes de la fecha de fin
				if (fechaInicio.after(fechaFin)) {
					JOptionPane.showMessageDialog(null, "¡La fecha de inicio debe ser anterior a la fecha de fin!",
							"CUIDADO", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Valida que la fecha de inicio sea posterior a la fecha actual
				Date fechaActual = new Date();
				if (fechaInicio.before(fechaActual)) {
					JOptionPane.showMessageDialog(null, "¡La fecha de inicio debe ser posterior a la echa actual!",
							"CUIDADO", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Obtiene el precio normal del producto
				double precioNormal = productoSeleccionado.getPrecio();

				// Calcula el precio de la oferta aplicando el descuento seleccionado
				double precioOferta;
				int descuento;
				if (descuentoSeleccionado.equals("Sin descuento")) {
					precioOferta = precioNormal;
					descuento = 0;
				} else if (descuentoSeleccionado.equals("10%")) {
					precioOferta = precioNormal * 0.9;
					descuento = 10;
				} else if (descuentoSeleccionado.equals("20%")) {
					precioOferta = precioNormal * 0.8;
					descuento = 20;
				} else if (descuentoSeleccionado.equals("30%")) {
					precioOferta = precioNormal * 0.7;
					descuento = 30;
				} else if (descuentoSeleccionado.equals("40%")) {
					precioOferta = precioNormal * 0.6;
					descuento = 40;
				} else if (descuentoSeleccionado.equals("50%")) {
					precioOferta = precioNormal * 0.5;
					descuento = 50;
				} else if (descuentoSeleccionado.equals("60%")) {
					precioOferta = precioNormal * 0.4;
					descuento = 60;
				} else if (descuentoSeleccionado.equals("70%")) {
					precioOferta = precioNormal * 0.3;
					descuento = 70;
				} else {
					JOptionPane.showMessageDialog(null, "¡Descuento seleccionado no válido!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Crea una instancia de la clase Ofertas y asignar los valores
				Oferta nuevaOferta = new Oferta();
				nuevaOferta.setProducto(productoSeleccionado);
				nuevaOferta.setFechaInicio(fechaInicio);
				nuevaOferta.setFechaFin(fechaFin);
				nuevaOferta.setPrecioOferta(precioOferta);
				nuevaOferta.setDescuento(descuento);

				// Guardar la nueva oferta en la base de datos
				almacenDAO.insertOferta(nuevaOferta);

				DecimalFormat decimalFormat = new DecimalFormat("#.##");
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));

				// Limpia la tabla y la rellena con la nueva oferta
				modelOfertas.setRowCount(0);
				List<Oferta> listaOfertas = almacenDAO.selectAllOfertas();
				for (Oferta oferta : listaOfertas) {
					Object[] row = new Object[6];
					row[0] = oferta.getId();
					row[1] = dateFormat.format(oferta.getFechaInicio());
					row[2] = dateFormat.format(oferta.getFechaFin());
					row[3] = decimalFormat.format(oferta.getPrecioOferta());
					row[4] = decimalFormat.format(oferta.getDescuento()) + "%";
					row[5] = oferta.getProducto().getNombre();
					modelOfertas.addRow(row);
				}

				JOptionPane.showMessageDialog(null, "¡Oferta agregada exitosamente!", "INFO",
						JOptionPane.INFORMATION_MESSAGE);

				// Limpiar los campos
				comboBoxProducto.setSelectedIndex(0);
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

		/**
		 * Creación del botón para actualizar una oferta existente
		 */
		JButton btnActualizarOferta = new JButton("");
		btnActualizarOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtiene la fila seleccionada en la tabla
				int filaSeleccionada = tableOfertas.getSelectedRow();

				// Valida que se haya seleccionado una fila
				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(null, "¡Por favor, seleccione una oferta para actualizar!", "CUIDADO",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Obtiene el ID de la oferta seleccionada
				int idOferta = (int) tableOfertas.getValueAt(filaSeleccionada, 0);

				// Obtiene el producto seleccionado del JComboBox
				Producto productoSeleccionado = (Producto) comboBoxProducto.getSelectedItem();

				// Obtiene las fechas seleccionadas de los JDateChooser
				Date fechaInicio = fechaInicioChooser.getDate();
				Date fechaFin = fechaFinChooser.getDate();

				// Obtiene el descuento seleccionado del JComboBox
				String descuentoSeleccionado = (String) comboBoxDescuento.getSelectedItem();

				// Valida que se hayan seleccionado un producto, las fechas y el descuento
				if (productoSeleccionado == null || fechaInicio == null || fechaFin == null
						|| descuentoSeleccionado == null) {
					JOptionPane.showMessageDialog(null, "¡Por favor, complete todos los campos!", "CUIDADO",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Valida que la fecha de inicio sea antes de la fecha de fin
				if (fechaInicio.after(fechaFin)) {
					JOptionPane.showMessageDialog(null, "¡La fecha de inicio debe ser anterior a la fecha de fin!",
							"CUIDADO", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Valida que la fecha de inicio sea posterior a la fecha actual
				Date fechaActual = new Date();
				if (fechaInicio.before(fechaActual)) {
					JOptionPane.showMessageDialog(null, "¡La fecha de inicio debe ser posterior a la fecha actual!",
							"CUIDADO", JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Obtiene el precio normal del producto
				double precioNormal = productoSeleccionado.getPrecio();

				// Calcula el precio de la oferta aplicando el descuento seleccionado
				double precioOferta;
				int descuento;
				if (descuentoSeleccionado.equals("Sin descuento")) {
					precioOferta = precioNormal;
					descuento = 0;
				} else if (descuentoSeleccionado.equals("10%")) {
					precioOferta = precioNormal * 0.9;
					descuento = 10;
				} else if (descuentoSeleccionado.equals("20%")) {
					precioOferta = precioNormal * 0.8;
					descuento = 20;
				} else if (descuentoSeleccionado.equals("30%")) {
					precioOferta = precioNormal * 0.7;
					descuento = 30;
				} else if (descuentoSeleccionado.equals("40%")) {
					precioOferta = precioNormal * 0.6;
					descuento = 40;
				} else if (descuentoSeleccionado.equals("50%")) {
					precioOferta = precioNormal * 0.5;
					descuento = 50;
				} else if (descuentoSeleccionado.equals("60%")) {
					precioOferta = precioNormal * 0.4;
					descuento = 60;
				} else if (descuentoSeleccionado.equals("70%")) {
					precioOferta = precioNormal * 0.3;
					descuento = 70;
				} else {
					JOptionPane.showMessageDialog(null, "¡Descuento seleccionado no válido!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Crea una instancia de la clase Ofertas y asignar los valores
				Oferta ofertaActualizada = new Oferta();
				ofertaActualizada.setId(idOferta);
				ofertaActualizada.setProducto(productoSeleccionado);
				ofertaActualizada.setFechaInicio(fechaInicio);
				ofertaActualizada.setFechaFin(fechaFin);
				ofertaActualizada.setPrecioOferta(precioOferta);
				ofertaActualizada.setDescuento(descuento);

				// Actualiza la oferta en la base de datos
				almacenDAO.updateOferta(ofertaActualizada);

				DecimalFormat decimalFormat = new DecimalFormat("#.##");
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));

				// Limpia la tabla y la vuelve a rellenar con la oferta actualizada
				modelOfertas.setRowCount(0);
				List<Oferta> listaOfertas = almacenDAO.selectAllOfertas();
				for (Oferta oferta : listaOfertas) {
					Object[] row = new Object[6];
					row[0] = oferta.getId();
					row[1] = dateFormat.format(oferta.getFechaInicio());
					row[2] = dateFormat.format(oferta.getFechaFin());
					row[3] = decimalFormat.format(oferta.getPrecioOferta());
					row[4] = decimalFormat.format(oferta.getDescuento()) + "%";
					row[5] = oferta.getProducto().getNombre();
					modelOfertas.addRow(row);
				}

				JOptionPane.showMessageDialog(null, "¡Oferta actualizada exitosamente!", "INFO",
						JOptionPane.INFORMATION_MESSAGE);

				// Limpiar los campos
				comboBoxProducto.setSelectedIndex(0);
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

		/**
		 * Creación del botón que elimina ofertas existentes
		 */
		JButton btnBorrarOferta = new JButton("");
		btnBorrarOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtiene la fila seleccionada en la tabla
				int filaSeleccionada = tableOfertas.getSelectedRow();

				// Valida que se haya seleccionado una fila
				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(null, "¡Seleccione una oferta para borrar!", "CUIDADO",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Obtiene el ID de la oferta seleccionada
				int idOferta = (int) tableOfertas.getValueAt(filaSeleccionada, 0);

				// Confirma si se desea borrar la oferta
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de borrar la oferta?", "CONFIRMAR",
						JOptionPane.YES_NO_OPTION);

				if (confirmacion == JOptionPane.YES_OPTION) {
					// Borra la oferta de la base de datos
					almacenDAO.deleteOferta(idOferta);

					// Obtiene el modelo de la tabla
					DefaultTableModel modelOfertas = (DefaultTableModel) tableOfertas.getModel();

					// Elimina la fila correspondiente a la oferta borrada
					modelOfertas.removeRow(filaSeleccionada);

					JOptionPane.showMessageDialog(null, "¡Oferta borrada exitosamente!", "INFO",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBorrarOferta.setBorder(null);
		btnBorrarOferta.setIcon(new ImageIcon(Supermercado.class.getResource("/img/borrar.png")));
		btnBorrarOferta.setBackground(new Color(255, 255, 255));
		btnBorrarOferta.setBounds(1301, 314, 90, 60);
		frmSuper.getContentPane().add(btnBorrarOferta);

		/**
		 * Creación del botón que establece los indices por defecto en los combobox y
		 * limpia las fechas de oferta
		 */
		JButton buttonCleanOferta = new JButton("");
		buttonCleanOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxProducto.setSelectedIndex(0);
				comboBoxDescuento.setSelectedIndex(0);
				fechaInicioChooser.setDate(null);
				fechaFinChooser.setDate(null);
			}
		});
		buttonCleanOferta.setIcon(new ImageIcon(Supermercado.class.getResource("/img/clean.png")));
		buttonCleanOferta.setBorder(null);
		buttonCleanOferta.setBackground((Color) null);
		buttonCleanOferta.setBounds(1361, 33, 30, 30);
		frmSuper.getContentPane().add(buttonCleanOferta);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Supermercado.class.getResource("/img/logosuper.png")));
		lblLogo.setBounds(1241, 472, 150, 150);
		frmSuper.getContentPane().add(lblLogo);

		JLabel lblQR = new JLabel("");
		lblQR.setIcon(new ImageIcon(Supermercado.class.getResource("/img/qr.png")));
		lblQR.setHorizontalAlignment(SwingConstants.CENTER);
		lblQR.setBounds(1037, 452, 153, 156);
		frmSuper.getContentPane().add(lblQR);
		
		JButton btnWeb = new JButton("WEB");
		btnWeb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Reemplaza la URL con la dirección del sitio web que deseas abrir
					String url = "https://candid-selkie-08f999.netlify.app/";
					Desktop.getDesktop().browse(new URI(url));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnWeb.setForeground(new Color(238, 68, 93));
		btnWeb.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		btnWeb.setBorder(null);
		btnWeb.setBackground(new Color(255, 255, 255));
		btnWeb.setBounds(1037, 614, 150, 30);
		frmSuper.getContentPane().add(btnWeb);

		JLabel lblGestionProducto = new JLabel("Gestión de Productos");
		lblGestionProducto.setForeground(new Color(238, 68, 93));
		lblGestionProducto.setFont(new Font("Dialog", Font.BOLD, 25));
		lblGestionProducto.setBounds(63, 33, 320, 40);
		frmSuper.getContentPane().add(lblGestionProducto);

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

		// Obtiene todas las categorías de la base de datos
		List<Categoria> categoriasList = almacenDAO.selectAllCategorias();
		List<String> categorias = new ArrayList<>();
		for (Categoria categoria : categoriasList) {
			categorias.add(categoria.getNombre());
		}

		/**
		 * Creación del combobox que muestra todas las categorias de los productos
		 */
		JComboBox<String> comboBoxCategorias = new JComboBox<>();
		((JLabel) comboBoxCategorias.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxCategorias.setBorder(null);
		comboBoxCategorias.setBounds(182, 132, 235, 27);
		comboBoxCategorias.setModel(new DefaultComboBoxModel<>(categorias.toArray(new String[0])));
		frmSuper.getContentPane().add(comboBoxCategorias);

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

		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setBounds(480, 30, 160, 40);
		lblProductos.setForeground(new Color(238, 68, 93));
		lblProductos.setFont(new Font("Dialog", Font.BOLD, 25));
		frmSuper.getContentPane().add(lblProductos);

		/**
		 * Creación de un modelo de tabla básico
		 */
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

		// Crea una lista en la que se añaden todos los productos que hay en la base de
		// datos y rellena la tabla con los mismos
		List<Producto> listaProductos;
		listaProductos = almacenDAO.selectAllProductos();
		for (Producto producto : listaProductos) {
			Object[] row = new Object[5];
			row[0] = producto.getId();
			row[1] = producto.getNombre();
			row[2] = producto.getPrecio();
			row[3] = producto.getCantidadStock();
			row[4] = producto.getCategoria().getNombre();
			modelProductos.addRow(row);
		}

		/**
		 * Creación de un modelo de tabla personaliizada en la que se muestran todos los
		 * productos de la base de datos
		 */
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
		scrollPaneProductos.setBorder(new LineBorder(new Color(252, 138, 25), 0, true));
		scrollPaneProductos.setBounds(480, 84, 508, 290);
		scrollPaneProductos.getVerticalScrollBar().setBackground(new Color(252, 138, 25));
		scrollPaneProductos.setVerticalScrollBar(new ScrollBarCustom());
		frmSuper.getContentPane().add(scrollPaneProductos);

		/**
		 * Creación del botón para agregar nuevos productos
		 */
		JButton btnAgregar = new JButton("");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldNombre.getText();
				String precioText = textFieldPrecio.getText();
				String cantidadText = textFieldStock.getText();
				String categoria = comboBoxCategorias.getSelectedItem().toString();

				// Verifica que los campos no estén vacíos
				if (nombre.isEmpty() || precioText.isEmpty() || cantidadText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "¡Por favor, llena todos los campos!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verifica que el precio y la cantidad sean valores numéricos
				double precio;
				int cantidad;
				try {
					precio = Double.parseDouble(precioText);
					cantidad = Integer.parseInt(cantidadText);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "¡El precio y la cantidad deben ser valores numéricos!",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verifica que el nombre del producto no esté repetido
				if (almacenDAO.productoExiste(nombre)) {
					JOptionPane.showMessageDialog(null, "¡Ya existe un producto con este nombre!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verifica que la cantidad no sea negativa
				if (cantidad < 0) {
					JOptionPane.showMessageDialog(null, "¡El stock no puede ser un valor negativo!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verifica que el nombre contenga al menos una letra
				if (!nombre.matches(".*[a-zA-Z].*")) {
					JOptionPane.showMessageDialog(null, "¡El nombre debe contener al menos una letra!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Obtiene la categoría seleccionada
				Categoria categoriaSeleccionada = null;
				for (Categoria cat : categoriasList) {
					if (cat.getNombre().equals(categoria)) {
						categoriaSeleccionada = cat;
						break;
					}
				}

				if (categoriaSeleccionada == null) {
					JOptionPane.showMessageDialog(null, "¡La categoría seleccionada no es válida!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Crea un nuevo objeto Productos
				Producto producto = new Producto();
				producto.setNombre(nombre);
				producto.setPrecio(precio);
				producto.setCantidadStock(cantidad);
				producto.setCategoria(categoriaSeleccionada);

				// Inserta el producto en la base de datos
				almacenDAO.insertProductos(producto);

				// Limpia los campos de entrada
				textFieldNombre.setText("");
				textFieldPrecio.setText("");
				textFieldStock.setText("");
				comboBoxCategorias.setSelectedIndex(0);

				// Limpia la tabla y la rellena con el nuevo producto
				modelProductos.setRowCount(0);
				List<Producto> listaProductos = almacenDAO.selectAllProductos();
				for (Producto productoAg : listaProductos) {
					Object[] row = new Object[5];
					row[0] = productoAg.getId();
					row[1] = productoAg.getNombre();
					row[2] = productoAg.getPrecio();
					row[3] = productoAg.getCantidadStock();
					row[4] = productoAg.getCategoria();
					modelProductos.addRow(row);
				}

				// Selecciona todos los productos de la base de datos, los guarda en un modelo
				// de combobox y lo aplica al que muestra todos los productos, en el apartado
				// gestión de ofertas
				List<Producto> productosOferta = almacenDAO.selectAllProductos();
				DefaultComboBoxModel<Producto> comboBoxModel = new DefaultComboBoxModel<>();
				for (Producto productoAg : productosOferta) {
					comboBoxModel.addElement(productoAg);
				}
				comboBoxProducto.setModel(comboBoxModel);

				JOptionPane.showMessageDialog(null, "¡Producto agregado exitosamente!", "INFO",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(new Color(255, 255, 255));
		btnAgregar.setIcon(new ImageIcon(Supermercado.class.getResource("/img/anadir.png")));
		btnAgregar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnAgregar.setBounds(63, 314, 90, 60);
		frmSuper.getContentPane().add(btnAgregar);

		/**
		 * Creación del botón que actualiza productos existentes
		 */
		JButton btnActualizar = new JButton("");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtiene el índice de la fila seleccionada en la tabla
				int selectedRow = tableProductos.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "¡Selecciona un producto para actualizar!", "CUIDADO",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Obtiene el ID del producto seleccionado en la tabla
				int productId = (int) tableProductos.getValueAt(selectedRow, 0);

				// Obtiene el producto de la base de datos por su ID
				Producto producto = almacenDAO.selectProductosById(productId);
				if (producto == null) {
					JOptionPane.showMessageDialog(null, "¡El producto seleccionado no existe!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Obtiene los nuevos valores de los campos de entrada
				String nuevoNombre = textFieldNombre.getText();
				String nuevoPrecioText = textFieldPrecio.getText();
				String nuevaCantidadText = textFieldStock.getText();
				String nuevaCategoria = comboBoxCategorias.getSelectedItem().toString();

				// Verifica que los campos no estén vacíos
				if (nuevoNombre.isEmpty() || nuevoPrecioText.isEmpty() || nuevaCantidadText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "¡Por favor, llena todos los campos!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verifica que el precio y la cantidad sean valores numéricos
				double nuevoPrecio;
				int nuevaCantidad;
				try {
					nuevoPrecio = Double.parseDouble(nuevoPrecioText);
					nuevaCantidad = Integer.parseInt(nuevaCantidadText);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "¡El precio y la cantidad deben ser valores numéricos!",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verifica que la cantidad no sea negativa
				if (nuevaCantidad < 0) {
					JOptionPane.showMessageDialog(null, "¡El stock no puede ser un valor negativo!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Verifica que el nombre contenga al menos una letra
				if (!nuevoNombre.matches(".*[a-zA-Z].*")) {
					JOptionPane.showMessageDialog(null, "¡El nombre debe contener al menos una letra!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Obtiene la categoría seleccionada
				Categoria categoriaSeleccionada = null;
				for (Categoria cat : categoriasList) {
					if (cat.getNombre().equals(nuevaCategoria)) {
						categoriaSeleccionada = cat;
						break;
					}
				}

				if (categoriaSeleccionada == null) {
					JOptionPane.showMessageDialog(null, "¡La categoría seleccionada no es válida!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Actualiza los valores del producto
				producto.setNombre(nuevoNombre);
				producto.setPrecio(nuevoPrecio);
				producto.setCantidadStock(nuevaCantidad);
				producto.setCategoria(categoriaSeleccionada);

				// Actualiza el producto en la base de datos
				almacenDAO.updateProductos(producto);

				// Obtiene la oferta asociada a este producto, si existe
				Oferta oferta = almacenDAO.selectOfertaByProductoId(productId);
				if (oferta != null) {
					// Calcula el nuevo precio de oferta basado en el nuevo precio del producto y el
					// descuento
					double nuevoPrecioOferta = producto.getPrecio() * ((100.0 - oferta.getDescuento()) / 100.0);
					oferta.setPrecioOferta(nuevoPrecioOferta);

					// Actualiza la oferta en la base de datos
					almacenDAO.updateOferta(oferta);
				}

				// Limpia los campos de entrada
				textFieldNombre.setText("");
				textFieldPrecio.setText("");
				textFieldStock.setText("");
				comboBoxCategorias.setSelectedIndex(0);

				// Limpia la tabla y la rellena con el producto actualizado
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

				DecimalFormat decimalFormat = new DecimalFormat("#.##");
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));

				// Limpia la tabla de ofertas y la rellena con los productos actualizados, en
				// caso de que estuviera compuesta por uno de estos
				modelOfertas.setRowCount(0);
				List<Oferta> listaOfertas = almacenDAO.selectAllOfertas();
				for (Oferta oferta1 : listaOfertas) {
					Object[] row = new Object[6];
					row[0] = oferta1.getId();
					row[1] = dateFormat.format(oferta1.getFechaInicio());
					row[2] = dateFormat.format(oferta1.getFechaFin());
					row[3] = decimalFormat.format(oferta1.getPrecioOferta());
					row[4] = decimalFormat.format(oferta1.getDescuento()) + "%";
					row[5] = oferta1.getProducto().getNombre();
					modelOfertas.addRow(row);
				}

				// Selecciona todos los productos de la base de datos, los guarda en un modelo
				// de combobox y lo aplica al mismo en el que se muestran todos los productos,
				// en el apartado de gestión de ofertas
				List<Producto> productosOferta = almacenDAO.selectAllProductos();
				DefaultComboBoxModel<Producto> comboBoxModel = new DefaultComboBoxModel<>();
				for (Producto productoAct : productosOferta) {
					comboBoxModel.addElement(productoAct);
				}
				comboBoxProducto.setModel(comboBoxModel);

				JOptionPane.showMessageDialog(null, "¡Producto actualizado exitosamente!", "INFO",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(new Color(255, 255, 255));
		btnActualizar.setIcon(new ImageIcon(Supermercado.class.getResource("/img/actualizar.png")));
		btnActualizar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnActualizar.setBounds(196, 314, 90, 60);
		frmSuper.getContentPane().add(btnActualizar);

		/**
		 * Creación del botón que elimina productos de la base de datos
		 */
		JButton btnEliminar = new JButton("");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtiene la fila seleccionada en la tabla
				int selectedRow = tableProductos.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "¡Selecciona un producto para eliminar!", "CUIDADO",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				// Obtiene el ID del producto seleccionado en la tabla
				int productId = (int) tableProductos.getValueAt(selectedRow, 0);

				// Confirma la eliminación del producto
				int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar el producto?",
						"CONFIRMAR", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					// Elimina el producto de la base de datos
					almacenDAO.deleteProductos(productId);

					// Elimina la fila correspondiente en la tabla
					modelProductos.removeRow(selectedRow);

					// Limpia los campos
					textFieldNombre.setText("");
					textFieldPrecio.setText("");
					textFieldStock.setText("");
					comboBoxCategorias.setSelectedIndex(0);

					DecimalFormat decimalFormat = new DecimalFormat("#.##");
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));

					// Limpia la tabla de ofertas y la rellena con las demás
					modelOfertas.setRowCount(0);
					List<Oferta> listaOfertas = almacenDAO.selectAllOfertas();
					for (Oferta oferta : listaOfertas) {
						Object[] row = new Object[6];
						row[0] = oferta.getId();
						row[1] = dateFormat.format(oferta.getFechaInicio());
						row[2] = dateFormat.format(oferta.getFechaFin());
						row[3] = decimalFormat.format(oferta.getPrecioOferta());
						row[4] = decimalFormat.format(oferta.getDescuento()) + "%";
						row[5] = oferta.getProducto().getNombre();
						modelOfertas.addRow(row);
					}

					// Selecciona todos los productos de la base de datos, los guarda en un modelo
					// de combobox y se aplica al mismo en el que se muestran todos los productos,
					// en el apartado de gestión de ofertas
					List<Producto> productosOferta = almacenDAO.selectAllProductos();
					DefaultComboBoxModel<Producto> comboBoxModel = new DefaultComboBoxModel<>();
					for (Producto productoEl : productosOferta) {
						comboBoxModel.addElement(productoEl);
					}
					comboBoxProducto.setModel(comboBoxModel);

					JOptionPane.showMessageDialog(null, "¡Producto eliminado exitosamente!", "INFO",
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

		/**
		 * Creación del botón que restablece los combobox y les pone el valor por
		 * defecto y limpia los campos
		 */
		JButton buttonCleanProducto = new JButton("");
		buttonCleanProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNombre.setText(null);
				textFieldPrecio.setText(null);
				textFieldStock.setText(null);
				comboBoxCategorias.setSelectedIndex(0);
			}
		});
		buttonCleanProducto.setIcon(new ImageIcon(Producto.class.getResource("/img/clean.png")));
		buttonCleanProducto.setBorder(null);
		buttonCleanProducto.setBackground(null);
		buttonCleanProducto.setBounds(387, 33, 30, 30);
		frmSuper.getContentPane().add(buttonCleanProducto);

		/**
		 * Creación del botón que muestra todos los productos de la base de datos
		 */
		JButton btnMostrarTodos = new JButton("MOSTRAR TODOS");
		btnMostrarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Elimina cualquier filtro aplicado a la tabla
				tableProductos.setRowSorter(null);

				// Actualiza la tabla de productos mostrando todos los registros
				List<Producto> productos = almacenDAO.selectAllProductos();

				//Limpia la tabla y la rellena con todos los productos
				modelProductos.setRowCount(0);
				for (Producto producto : productos) {
					Object[] rowData = { producto.getId(), producto.getNombre(), producto.getPrecio(),
							producto.getCantidadStock(), producto.getCategoria() };
					modelProductos.addRow(rowData);
				}

				JOptionPane.showMessageDialog(null, "¡Se han mostrado todos los productos.!", "INFO",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnMostrarTodos.setBorder(null);
		btnMostrarTodos.setBackground(new Color(255, 255, 255));
		btnMostrarTodos.setForeground(new Color(238, 90, 60));
		btnMostrarTodos.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		btnMostrarTodos.setBounds(63, 452, 355, 40);
		frmSuper.getContentPane().add(btnMostrarTodos);

		/**
		 * Creación del botón que muestra los productos que no tienen stock
		 */
		JButton btnMostrarSinStock = new JButton("MOSTRAR SIN STOCK");
		btnMostrarSinStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Limpia la tabla y la rellena con todos los productos que no tienen stock
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

				JOptionPane.showMessageDialog(null, "¡Se han mostrado los productos sin stock!", "INFO",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnMostrarSinStock.setBorder(null);
		btnMostrarSinStock.setForeground(new Color(238, 68, 93));
		btnMostrarSinStock.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		btnMostrarSinStock.setBackground(Color.WHITE);
		btnMostrarSinStock.setBounds(63, 516, 354, 40);
		frmSuper.getContentPane().add(btnMostrarSinStock);

		JLabel lblMostrarCategoriaConcreta = new JLabel("Mostrar Categoría:");
		lblMostrarCategoriaConcreta.setForeground(new Color(238, 68, 93));
		lblMostrarCategoriaConcreta.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblMostrarCategoriaConcreta.setBounds(63, 572, 355, 25);
		frmSuper.getContentPane().add(lblMostrarCategoriaConcreta);

		/**
		 * Creación del combobox que muestra todas las categorías de producto y filtra por las mismas
		 */
		JComboBox<String> comboBoxCategoriaConcreta = new JComboBox<String>();
		comboBoxCategoriaConcreta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtiene la categoría seleccionada
				String categoriaSeleccionada = comboBoxCategoriaConcreta.getSelectedItem().toString();

				// Filtra la tabla por la categoría seleccionada
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableProductos.getModel());
				tableProductos.setRowSorter(sorter);
				List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();
				int categoriaColumnIndex = 4; // Índice de la columna de categoría en el modelo de tabla

				// Si se selecciona "Todas las categorías", no se aplica ningún filtro
				if (!categoriaSeleccionada.equals("Todas las categorías")) {
					filters.add(RowFilter.regexFilter(categoriaSeleccionada, categoriaColumnIndex));
				}
				sorter.setRowFilter(RowFilter.andFilter(filters));
			}
		});
		((JLabel) comboBoxCategoriaConcreta.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBoxCategoriaConcreta.setBorder(null);
		comboBoxCategoriaConcreta.setBounds(63, 604, 355, 40);
		frmSuper.getContentPane().add(comboBoxCategoriaConcreta);

		// Obtiene todas las categorías de la base de datos
		List<Categoria> listaCategorias = almacenDAO.selectAllCategorias();

		// Agrega la opción "Todas las categorías" al principio del JComboBox
		comboBoxCategoriaConcreta.addItem("Todas las categorías");

		// Agrega las categorías al JComboBox
		for (Categoria categoria : listaCategorias) {
			comboBoxCategoriaConcreta.addItem(categoria.getNombre());
		}

		// Selecciona "Todas las categorías" por defecto
		comboBoxCategoriaConcreta.setSelectedIndex(0);
	}
}