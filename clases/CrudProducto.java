package PSQL;

import java.awt.Dimension;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class CrudProducto {

    public void Insertar(String p_nombreproducto, Integer p_precio, Integer p_stock, String p_descripcion, Integer p_idcategoria, Integer p_idsucursal) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            String sql = "CALL insertarproducto(?, ?, ?, ?,?,?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setString(1, p_nombreproducto);
                cstmt.setInt(2, p_precio);
                cstmt.setInt(3, p_stock);
                cstmt.setString(4, p_descripcion);
                cstmt.setInt(5, p_idcategoria);
                cstmt.setInt(6, p_idsucursal);
                cstmt.execute();
            }
            JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Actualizar(Integer idcliente, String p_nombreproducto, Integer p_precio, Integer p_stock, String p_descripcion, Integer p_idcategoria, Integer p_idsucursal) {
        Conexion conn = new Conexion();
                try (Connection conexion = conn.getConnection()) {
            String sql = "call actualizarproducto(?, ?, ?, ?,?,?,?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setInt(1, idcliente);
                cstmt.setString(2, p_nombreproducto);
                cstmt.setInt(3, p_precio);
                cstmt.setInt(4, p_stock);
                cstmt.setString(5, p_descripcion);
                cstmt.setInt(6, p_idcategoria);
                cstmt.setInt(7, p_idsucursal);
                cstmt.execute();
            }
            conexion.close();
            JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Mostrar(JTable productos) {
        Conexion conn = new Conexion();
        try {
            Connection conexion = conn.getConnection();
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("idarticulo");
            modelo.addColumn("nombreproducto");
            modelo.addColumn("precio");
            modelo.addColumn("stock");
            modelo.addColumn("descripcion");
            modelo.addColumn("ID_Categoria");
            modelo.addColumn("IDSucursal");
            productos.setModel(modelo);
            String sql = "SELECT * FROM productos";
            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                modelo.setRowCount(0);
                while (rs.next()) {
                    int id = rs.getInt("idarticulo");
                    String nombreproducto = rs.getString("nombreproducto");
                    int precio = rs.getInt("precio");
                    int stock = rs.getInt("stock");
                    String desc = rs.getString("descripcion");
                    int idcat = rs.getInt("ID_Categoria");
                    int idsuc = rs.getInt("IDSucursal");
                    modelo.addRow(new Object[]{id, nombreproducto, precio, stock, desc, idcat, idsuc});
                }
                productos.setModel(modelo);
            }
            conexion.close();
            JOptionPane.showMessageDialog(null, "Registro Cargado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
}
