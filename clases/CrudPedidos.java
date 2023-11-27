
package PSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CrudPedidos {
    public void Insertar(String Fecha,Integer IdClient) {
         Conexion conn = new Conexion();
        try {
            Connection conexion = conn.getConnection();
            String sql = "INSERT INTO Pedidos (fechapedido, clienteid) VALUES (?, ?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setString(1, Fecha);
                cstmt.setInt(2, IdClient);     
                cstmt.execute();
            }
            conexion.close();
            JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Mostrar(JTable pedidos) {
        Conexion conn = new Conexion();
        try {
            Connection conexion = conn.getConnection();
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("pedidoid");
            modelo.addColumn("fechapedido");
            modelo.addColumn("clienteid");
            pedidos.setModel(modelo);
            String sql = "SELECT * FROM pedidos";
            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                modelo.setRowCount(0);
                while (rs.next()) {
                    int id = rs.getInt("pedidoid");
                    String fecha = rs.getString("fechapedido");
                    int idClient = rs.getInt("clienteid");                 
                    modelo.addRow(new Object[]{id, fecha,idClient});
                }
                pedidos.setModel(modelo);
            }
            conexion.close();
            JOptionPane.showMessageDialog(null, "Registro Cargado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
}
