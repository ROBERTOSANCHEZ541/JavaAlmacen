
import PSQL.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CrudLogin {

    public void VerificarLogin(String nombre, String rfcDesencriptado) {
        Conexion conn = new Conexion();
        try (Connection conexion = conn.getConnection();
                CallableStatement cstmt = conexion.prepareCall("call VerificarLogin1(?, ?, ?)")) {
            cstmt.setString(1, nombre);
            cstmt.setString(2, rfcDesencriptado);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.execute();
            String resultado = cstmt.getString(3);
            if ("EXITOSO".equals(resultado)) {
                JOptionPane.showMessageDialog(null, "Verificación de login exitosa", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                MainMenu mainMenuFrame = new MainMenu();
                mainMenuFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales inválidas", "Mensaje", JOptionPane.ERROR_MESSAGE);
                Login frmLogin = new Login();
                frmLogin.setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la verificación de login: " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }
}
