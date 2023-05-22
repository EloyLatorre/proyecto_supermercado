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
        frmSuper.setResizable(false);
        frmSuper.getContentPane().setBackground(Color.DARK_GRAY);
        frmSuper.setBackground(new Color(250, 153, 56));
        frmSuper.setTitle("Supermercado");
        frmSuper.setBounds(100, 100, 1510, 700);
        frmSuper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSuper.getContentPane().setLayout(null);
        JPanel panelProductos = new JPanel();
        panelProductos.setBackground(Color.WHITE);
        panelProductos.setBounds(466, 129, 524, 467);
        frmSuper.getContentPane().add(panelProductos);
        panelProductos.setLayout(null);

        JLabel lblProductos = new JLabel("Productos");
        lblProductos.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
        lblProductos.setBounds(201, 6, 129, 46);
        panelProductos.add(lblProductos);

        JScrollPane scrollPaneProductos = new JScrollPane();
        scrollPaneProductos.setBounds(6, 59, 512, 402);
        panelProductos.add(scrollPaneProductos);

      
     // Crear la tabla
        JTable tableProductos = new JTable();

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
            Object[] rowData = {
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCantidadStock(),
                producto.getCategorias().getNombre() // Obtener el nombre de la categoría
            };
            modelProductos.addRow(rowData);
        }

        // Asignar el modelo de tabla a la tabla
        tableProductos.setModel(modelProductos);




        // Agregar la tabla al JScrollPane
        scrollPaneProductos.setViewportView(tableProductos);

    
     

        listProductos = new JList<>();
        listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      //  scrollPaneProductos.setViewportView(listProductos);

        // Agregar la tabla al JScrollPane
        scrollPaneProductos.setViewportView(tableProductos);
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblNombre.setBounds(62, 140, 69, 26);
        frmSuper.getContentPane().add(lblNombre);

        JLabel lblCategora = new JLabel("Categoría");
        lblCategora.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblCategora.setBounds(62, 188, 80, 26);
        frmSuper.getContentPane().add(lblCategora);

        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblPrecio.setBounds(62, 236, 57, 26);
        frmSuper.getContentPane().add(lblPrecio);

        JLabel lblStock = new JLabel("Stock");
        lblStock.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblStock.setBounds(62, 284, 48, 26);
        frmSuper.getContentPane().add(lblStock);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(143, 140, 201, 26);
        frmSuper.getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JComboBox<String> comboBoxCategorias = new JComboBox<>();
        comboBoxCategorias.setBounds(143, 188, 201, 27);
        frmSuper.getContentPane().add(comboBoxCategorias);

        textFieldPrecio = new JTextField();
        textFieldPrecio.setBounds(143, 236, 201, 26);
        frmSuper.getContentPane().add(textFieldPrecio);
        textFieldPrecio.setColumns(10);

        textFieldStock = new JTextField();
        textFieldStock.setBounds(143, 284, 201, 26);
        frmSuper.getContentPane().add(textFieldStock);
        textFieldStock.setColumns(10);
        // Obtener todas las categorías de la base de datos
        List<Categorias> categoriasList = AlmacenDAO.selectAllCategorias();

        // Convertir la lista de objetos Categorias a una lista de cadenas (nombres de categorías)
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
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = textFieldNombre.getText();
                double precio = Double.parseDouble(textFieldPrecio.getText());
                int cantidad = Integer.parseInt(textFieldStock.getText());
                String categoria = comboBoxCategorias.getSelectedItem().toString();

                // Obtener la categoría seleccionada
                Categorias categoriaSeleccionada = null;
                for (Categorias cat : categoriasList) {
                    if (cat.getNombre().equals(categoria)) {
                        categoriaSeleccionada = cat;
                        break;
                    }
                }

                if (categoriaSeleccionada == null) {
                    JOptionPane.showMessageDialog(null, "¡La categoría seleccionada no es válida!", "Error", JOptionPane.ERROR_MESSAGE);
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

                // Actualizar la lista de productos mostrados
                List<Productos> productos = AlmacenDAO.selectAllProductos();
                DefaultListModel<String> modelProductos = new DefaultListModel<>();
                for (Productos prod : productos) {
                    modelProductos.addElement(prod.getNombre());
                }
                listProductos.setModel(modelProductos);

                JOptionPane.showMessageDialog(null, "¡Producto agregado exitosamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnAgregar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        btnAgregar.setBounds(62, 369, 130, 40);
        frmSuper.getContentPane().add(btnAgregar);


        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Obtener el índice de la fila seleccionada en la tabla
                int selectedRow = tableProductos.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un producto para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Obtener el ID del producto seleccionado en la tabla
                int productId = (int) tableProductos.getValueAt(selectedRow, 0);

                // Obtener el producto de la base de datos por su ID
                AlmacenDAO almacenDAO = new AlmacenDAO();
                Productos producto = almacenDAO.selectProductosById(productId);
                if (producto == null) {
                    JOptionPane.showMessageDialog(null, "El producto seleccionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Obtener los nuevos valores de los campos de entrada
                String nuevoNombre = textFieldNombre.getText();
                double nuevoPrecio = Double.parseDouble(textFieldPrecio.getText());
                int nuevaCantidad = Integer.parseInt(textFieldStock.getText());
                String nuevaCategoria = comboBoxCategorias.getSelectedItem().toString();

                // Obtener la categoría seleccionada
                Categorias categoriaSeleccionada = null;
                for (Categorias cat : categoriasList) {
                    if (cat.getNombre().equals(nuevaCategoria)) {
                        categoriaSeleccionada = cat;
                        break;
                    }
                }

                if (categoriaSeleccionada == null) {
                    JOptionPane.showMessageDialog(null, "¡La categoría seleccionada no es válida!", "Error", JOptionPane.ERROR_MESSAGE);
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
                modelProductos.setValueAt(producto.getNombre(), selectedRow, 1);  // Columna del nombre
                modelProductos.setValueAt(producto.getPrecio(), selectedRow, 2);  // Columna del precio
                modelProductos.setValueAt(producto.getCantidadStock(), selectedRow, 3);  // Columna del stock

                JOptionPane.showMessageDialog(null, "¡Producto actualizado exitosamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnActualizar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        btnActualizar.setBounds(181, 369, 130, 40);
        frmSuper.getContentPane().add(btnActualizar);

   


        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Obtener la fila seleccionada en la tabla
                int selectedRow = tableProductos.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Selecciona un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Obtener el ID del producto seleccionado en la tabla
                int productId = (int) tableProductos.getValueAt(selectedRow, 0);

                // Confirmar la eliminación del producto
                int option = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar el producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Eliminar el producto de la base de datos
                    AlmacenDAO almacenDAO = new AlmacenDAO();
                    almacenDAO.deleteProductos(productId);

                    // Eliminar la fila correspondiente en la tabla
                    modelProductos.removeRow(selectedRow);

                    JOptionPane.showMessageDialog(null, "¡Producto eliminado exitosamente!", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnEliminar.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        btnEliminar.setBounds(301, 369, 130, 40);
        frmSuper.getContentPane().add(btnEliminar);

        JButton btnMostrarTodos = new JButton("Mostrar todos");
        btnMostrarTodos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Eliminar cualquier filtro aplicado a la tabla
                tableProductos.setRowSorter(null);
            }
        });

        btnMostrarTodos.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        btnMostrarTodos.setBounds(62, 421, 369, 40);
        frmSuper.getContentPane().add(btnMostrarTodos);
        
        JComboBox<String> comboBoxCategoriaConcreta = new JComboBox<String>();
        comboBoxCategoriaConcreta.setBounds(62, 513, 369, 22);
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
        lblMostrarCategoraConcreta.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblMostrarCategoraConcreta.setBounds(71, 472, 360, 26);
        frmSuper.getContentPane().add(lblMostrarCategoraConcreta);
        
        JButton btnMostrarSinStock = new JButton("Mostrar Sin Stock");
        btnMostrarSinStock.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Filtrar la tabla para mostrar solo los productos sin stock
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableProductos.getModel());
                tableProductos.setRowSorter(sorter);
                RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                    public boolean include(Entry<? extends Object, ? extends Object> entry) {
                        // Obtener el valor de la columna "Cantidad" del producto en la fila actual
                        int cantidad = (int) entry.getValue(3); // 3 es el índice de la columna "Cantidad" en el modelo de tabla

                        // Mostrar solo los productos con cantidad igual a 0
                        return cantidad == 0;
                    }
                };
                sorter.setRowFilter(filter);
            }
        });

        btnMostrarSinStock.setFont(new Font("Dialog", Font.PLAIN, 16));
        btnMostrarSinStock.setBounds(62, 556, 369, 40);
        frmSuper.getContentPane().add(btnMostrarSinStock);


    }
}