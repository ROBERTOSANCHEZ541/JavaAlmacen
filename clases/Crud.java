package PSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JTable;
public class Crud {

    Conexion conn = new Conexion();

    public void Insertar(String tipo_categoria) {
        try {
            Connection conexion = conn.getConnection();
            String sql = "SELECT InsertarCategoria(?)";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, tipo_categoria);
                pstmt.executeQuery();
            }
            conexion.close();
            JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
     public void Actualizar(Integer ID,String tipo_categoria) {
        try {
            Connection conexion = conn.getConnection();
            String sql = "call actualizarcategoria(?,?)";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(ID, tipo_categoria);
                pstmt.executeQuery();
            }
            conexion.close();
            JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Mostrar(JTable TotalCategoria) {
        try {
            Connection conexion = conn.getConnection();
            DefaultTableModel modelo = new DefaultTableModel();

           
            modelo.addColumn("id_categoria");
            modelo.addColumn("tipo_categoria");
            TotalCategoria.setModel(modelo);
             String sql = "SELECT * FROM categoria";
            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                modelo.setRowCount(0);
                while (rs.next()) {
                    int id = rs.getInt("id_categoria");
                    String tipo = rs.getString("tipo_categoria");
                    modelo.addRow(new Object[]{id, tipo});
                }
                 TotalCategoria.setModel(modelo);
            }
            conexion.close();
            JOptionPane.showMessageDialog(null, "Registro Cargado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

}
