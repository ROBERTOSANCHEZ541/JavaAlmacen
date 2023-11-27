package PSQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CrudTransp {

    public void Insertar(String nombre, String tipo, Integer p_capacidadkg, String p_numeroplaca) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            String sql = "call insertartransporte(?, ?, ?, ?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setString(1, nombre);
                cstmt.setString(2, tipo);
                cstmt.setInt(3, p_capacidadkg);
                cstmt.setString(4, p_numeroplaca);
                cstmt.execute();
                JOptionPane.showMessageDialog(null, "El registro fue exitoso.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Actualizar(Integer idTransp, String nombre, String tipo, Integer p_capacidadkg, String p_numeroplaca) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            String sql = "call actualizartransporte(?,?,?,?,?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setInt(1, idTransp);
                cstmt.setString(2, nombre);
                cstmt.setString(3, tipo);
                cstmt.setInt(4, p_capacidadkg);
                cstmt.setString(5, p_numeroplaca);
                cstmt.execute();
            }
            JOptionPane.showMessageDialog(null, "El cambio fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El cambio no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     public void Mostrar(JTable transp) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()){
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("idtransporte");
            modelo.addColumn("nombre");
            modelo.addColumn("tipo");
            modelo.addColumn("capacidadkg");
             modelo.addColumn("numeroplaca");
            transp.setModel(modelo);
            String sql = "SELECT * FROM transporte";
            try (Statement stmt = conexion.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
                modelo.setRowCount(0);
                while (rs.next()) {
                    int id = rs.getInt("idtransporte");
                    String nombre = rs.getString("nombre");
                    String tipo = rs.getString("tipo");
                    int kg = rs.getInt("capacidadkg");
                    String nplaca = rs.getString("numeroplaca");
                    modelo.addRow(new Object[]{id, nombre,tipo,kg,nplaca});
                }
                transp.setModel(modelo);
             } 
            JOptionPane.showMessageDialog(null, "Registro Cargado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
      public void eliminarTransporte(int idTransporte) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection();
             CallableStatement cstmt = conexion.prepareCall("call EliminarTransporte(?)")) {
            cstmt.setInt(1, idTransporte);
            cstmt.execute();
            System.out.println("Transporte eliminado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al intentar eliminar el transporte: " + e.getMessage());
        }
    }
}
