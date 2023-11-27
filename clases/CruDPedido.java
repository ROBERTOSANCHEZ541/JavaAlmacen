
package PSQL;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class CruDPedido {
     public void Insertar(Integer pedidoid, Integer productoid, Integer idtransporte, Integer cant, BigDecimal precio) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            String sql = "CALL insertardetallepedido(?, ?, ?, ?,?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setInt(1, pedidoid);
                cstmt.setInt(2, productoid);
                cstmt.setInt(3, idtransporte);
                cstmt.setInt(4, cant);
                cstmt.setBigDecimal(5, precio);
                cstmt.execute();
            }
            JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Actualizar(Integer idarticulo, String p_nombreproducto, BigDecimal p_precio, Integer p_stock) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            String sql = "call actualizarproducto1(?, ?, ?,?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setInt(1, idarticulo);
                cstmt.setString(2, p_nombreproducto);
                cstmt.setBigDecimal(3, p_precio);
                cstmt.setInt(4, p_stock);
                cstmt.execute();
            }
            conexion.close();
            JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Mostrar(JTable DetPedido) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("detalleid");
            modelo.addColumn("pedidoid");
            modelo.addColumn("productoid");
            modelo.addColumn("idtransporte");
            modelo.addColumn("cantidad");
            modelo.addColumn("preciounitario");

            String sql = "SELECT * FROM detallespedido";

            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                // Limpia el modelo antes de agregar nuevos datos
                modelo.setRowCount(0);

                while (rs.next()) {
                    int detid = rs.getInt("detalleid");
                    int id = rs.getInt("pedidoid");
                    int idprod = rs.getInt("productoid");
                    int idtransp = rs.getInt("idtransporte");
                    int cant = rs.getInt("cantidad");
                    int precio = rs.getInt("preciounitario");

                    // Agrega una nueva fila al modelo
                    modelo.addRow(new Object[]{detid, id, idprod, idtransp, cant, precio});
                }

                // Establece el modelo en la JTable
                DetPedido.setModel(modelo);

                JOptionPane.showMessageDialog(null, "Registro Cargado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "El registro no se pudo Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cbProductN(JComboBox<String> cbProduc) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {

            String sql = "SELECT nombreproducto FROM productos";
            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                while (rs.next()) {
                    String nombreProducto = rs.getString("nombreproducto");
                    model.addElement(nombreProducto);
                }
                cbProduc.setModel(model);
            }
            conexion.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cbTransp(JComboBox<String> cbtranspN) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {

            String sql = "SELECT nombre FROM transporte";
            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                while (rs.next()) {
                    String nombreProducto = rs.getString("nombre");
                    model.addElement(nombreProducto);
                }
                cbtranspN.setModel(model);
            }
            conexion.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cbIDProd(JComboBox<String> cbIDProduc, String nombre) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            String sql = "SELECT idarticulo FROM productos WHERE nombreproducto=?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                try (ResultSet rs = stmt.executeQuery()) {
                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
                    while (rs.next()) {
                        String idProducto = rs.getString("idarticulo");
                        model.addElement(idProducto);
                    }
                    cbIDProduc.setModel(model);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void llenarJTextField(String nombreProducto, JTextField precioTextField, JTextField stockTextField) {
        Conexion conn = new Conexion();

        try (Connection conexion = conn.getConnection()) {
            String sql = "SELECT precio, stock FROM productos WHERE nombreproducto = ?";

            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, nombreProducto);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        double precio = rs.getDouble("precio");
                        int stock = rs.getInt("stock");
                        precioTextField.setText(String.valueOf(precio));
                        stockTextField.setText(String.valueOf(stock));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void txtTransp(String nombre, JTextField idTextField) {
        Conexion conn = new Conexion();

        try (Connection conexion = conn.getConnection()) {
            String sql = "SELECT idtransporte FROM transporte WHERE nombre = ?";

            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, nombre);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int idtransporte = rs.getInt("idtransporte");
                        idTextField.setText(String.valueOf(idtransporte));

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}