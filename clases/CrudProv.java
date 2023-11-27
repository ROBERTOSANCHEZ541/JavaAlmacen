package PSQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Types;
import javax.swing.JTable;

public class CrudProv {

    public void Insertar(String nombre, String direccion, String tel, String rfc) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            String sql = "call insertarproveedor(?, ?, ?, ?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setString(1, nombre);
                cstmt.setString(3, direccion);
                cstmt.setString(2, tel);
                cstmt.setString(4, rfc);
                cstmt.execute();
               
            }
             JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Actualizar(Integer idProv, String nombre, String direccion, String tel, String rfc) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            String sql = "call actualizarproveedor(?,?,?,?,?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
               
                cstmt.setInt(1, idProv);
                cstmt.setString(2, nombre);
                cstmt.setString(3, tel);
                cstmt.setString(4, direccion);
                cstmt.setString(5, rfc);
                cstmt.execute();
            }
            JOptionPane.showMessageDialog(null, "El cambio fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El cambio no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Mostrar(JTable proveedor) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("idproveedor");
            modelo.addColumn("nombre");
            modelo.addColumn("telefono");
            modelo.addColumn("direccion");
             modelo.addColumn("rfc");
            proveedor.setModel(modelo);
            String sql = "SELECT * FROM proveedor";
            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                modelo.setRowCount(0);
                while (rs.next()) {
                    int id = rs.getInt("idproveedor");
                    String nombre = rs.getString("nombre");
                    String tel = rs.getString("telefono");
                    String direccion = rs.getString("direccion");
                     String rfc = rs.getString("rfc");
                    modelo.addRow(new Object[]{id, nombre, tel, direccion,rfc});
                }
                proveedor.setModel(modelo);
            }
            JOptionPane.showMessageDialog(null, "Registro Cargado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
}
