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

public class CrudClient {
    public void Insertar(String nombre, String email, String tel) {
        Conexion conn = new Conexion();
         try (Connection conexion = conn.getConnection()) {
            String sql = "call InsertarCliente(?, ?, ?, ?)";

            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setString(1, nombre);
                cstmt.setString(2, email);
                cstmt.setString(3, tel);
                cstmt.registerOutParameter(4, Types.INTEGER);
                cstmt.execute();

                int nuevoID = cstmt.getInt(4);
                         JOptionPane.showMessageDialog(null, "El registro fue exitoso. Nuevo ID: " + nuevoID, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } 
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Actualizar(Integer idcliente,String nombre, String email, Integer tel) {
        Conexion conn = new Conexion();
        try {
            Connection conexion = conn.getConnection();
            String sql = "call actualizarcliente(?,?,?,?)";
           try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                 cstmt.setInt(1, idcliente);
               cstmt.setString(2, nombre);
                cstmt.setString(3, email);
                cstmt.setInt(4, tel);
                cstmt.execute();
            }
            conexion.close();
            JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Mostrar(JTable Clientes) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()){
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("idcliente");
            modelo.addColumn("nombre");
            modelo.addColumn("email");
            modelo.addColumn("telefono");
            Clientes.setModel(modelo);
            String sql = "SELECT * FROM Cliente";
            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                modelo.setRowCount(0);
                while (rs.next()) {
                    int id = rs.getInt("idcliente");
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");
                    String tel = rs.getString("telefono");
                    modelo.addRow(new Object[]{id, nombre,email,tel});
                }
                Clientes.setModel(modelo);
             } 
            JOptionPane.showMessageDialog(null, "Registro Cargado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
