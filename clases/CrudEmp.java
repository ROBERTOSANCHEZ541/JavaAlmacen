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

public class CrudEmp {

    public void Insertar(String nombre, Integer p_Edad, String p_rfc, String p_telefono, String p_curp,
            String p_nss, String p_puesto, Integer p_idsucursal) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection()) {
            String sql = "CALL InsertarEmpleadoEncriptado(?, ?, ?, ?,?,?,?,?)";
            try (CallableStatement cstmt = conexion.prepareCall(sql)) {
                cstmt.setString(1, nombre);
                cstmt.setInt(2, p_Edad);
                cstmt.setString(3, p_rfc);
                cstmt.setString(4, p_telefono);
                cstmt.setString(5, p_curp);
                cstmt.setString(6, p_nss);
                cstmt.setString(7, p_puesto);
                cstmt.setInt(8, p_idsucursal);
                cstmt.execute();
            }
            JOptionPane.showMessageDialog(null, "El registro fue exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardó " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Mostrar(JTable Empleado, Integer idempleado) {
        Conexion conn = new Conexion();

    try (Connection conexion = conn.getConnection();
         CallableStatement cstmt = conexion.prepareCall("call ObtenerEmpleadoDesencriptado(?)")) {

        // Configurar parámetros del procedimiento almacenado
        cstmt.setInt(1, idempleado);

        // Ejecutar el procedimiento almacenado
        cstmt.execute();
boolean results = false;
        int rowCount = 0;

        do {
            try (ResultSet rs = cstmt.getResultSet()) {
                if (rs != null) {
                    results = true;

                    DefaultTableModel modelo = new DefaultTableModel();
                    modelo.addColumn("idempleado");
                    modelo.addColumn("nombre");
                    modelo.addColumn("edad");
                    modelo.addColumn("rfc");
                    modelo.addColumn("telefono");
                    modelo.addColumn("curp");
                    modelo.addColumn("nss");
                    modelo.addColumn("puesto");
                    modelo.addColumn("idsucursal");

                    while (rs.next()) {
                        int id = rs.getInt("idempleado");
                        String nombre = rs.getString("nombre");
                        int edad = rs.getInt("edad");
                        String rfcDesencriptado = rs.getString("rfc");
                        String tel = rs.getString("telefono");
                        String curp = rs.getString("curp");
                        String nss = rs.getString("nss");
                        String puesto = rs.getString("puesto");
                        int idSuc = rs.getInt("idsucursal");

                        modelo.addRow(new Object[]{id, nombre, edad, rfcDesencriptado, tel, curp, nss, puesto, idSuc});
                        rowCount++;
                    }

                    // Establecer el modelo en la tabla
                    Empleado.setModel(modelo);
                }
            }
        } while (cstmt.getMoreResults() || cstmt.getUpdateCount() != -1);

        if (results) {
            JOptionPane.showMessageDialog(null, "Se encontraron actualizaciones: " + rowCount, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron resultados", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "El registro no se pudo Mostrar " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
    }
}
}
