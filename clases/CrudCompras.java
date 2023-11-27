package PSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CrudCompras {
     
     public void Insertar(String Fecha,Integer ProvId,Integer EmpId) {
         Conexion conn = new Conexion();
        try {
            Connection conexion = conn.getConnection();
            String sql = "INSERT INTO Compras (fechacompra, proveedorid, empleadoid) VALUES (?, ?, ?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setString(1, Fecha);
                cstmt.setInt(2, ProvId);
                 cstmt.setInt(3, EmpId);
                cstmt.execute();
            }
            conexion.close();
            JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Mostrar(JTable Compras) {
        Conexion conn = new Conexion();
        try {
            Connection conexion = conn.getConnection();
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("compraid");
            modelo.addColumn("fechacompra");
            modelo.addColumn("proveedorid");
            modelo.addColumn("empleadoid");
            Compras.setModel(modelo);
            String sql = "SELECT * FROM Compras";
            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                modelo.setRowCount(0);
                while (rs.next()) {
                    int id = rs.getInt("compraid");
                    String fecha = rs.getString("fechacompra");
                    int idProv = rs.getInt("proveedorid");
                    int idEmp = rs.getInt("empleadoid");
                    modelo.addRow(new Object[]{id, fecha,idProv,idEmp});
                }
                Compras.setModel(modelo);
            }
            conexion.close();
            JOptionPane.showMessageDialog(null, "Registro Cargado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
}
