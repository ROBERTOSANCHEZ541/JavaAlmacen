package PSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JTable;

public class CrudSucursal {

    public void Insertar(String p_nombresucursal, String direccion, String tel, String p_ciudad, String p_codigopostal) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            String sql = "call insertarsucursal(?, ?, ?, ?,?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setString(1, p_nombresucursal);
                cstmt.setString(2, direccion);
                cstmt.setString(4, p_codigopostal);
                cstmt.setString(3, p_ciudad);
                cstmt.setString(5, tel);
                cstmt.execute();
            }
            JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
     public void Mostrar(JTable sucursales) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()){
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("idsucursal");
            modelo.addColumn("nombresucursal");
            modelo.addColumn("direccion");
            modelo.addColumn("ciudad");
            modelo.addColumn("codigopostal");
            modelo.addColumn("telefono");
            sucursales.setModel(modelo);
            String sql = "SELECT * FROM sucursales";
            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                modelo.setRowCount(0);
                while (rs.next()) {
                    int id = rs.getInt("idsucursal");
                    String nombre = rs.getString("nombresucursal");
                    String direccion = rs.getString("direccion");
                    String ciudad = rs.getString("ciudad");
                    String cp = rs.getString("codigopostal");
                    String tel = rs.getString("telefono");
                    modelo.addRow(new Object[]{id, nombre,direccion,ciudad,cp,tel});
                }
                sucursales.setModel(modelo);
             } 
            JOptionPane.showMessageDialog(null, "Registro Cargado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
}
