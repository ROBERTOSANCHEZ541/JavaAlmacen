import javax.swing.JFrame;
public class MainMenu extends JFrame {
    public MainMenu() {
        initComponents();            
        CbMenu.removeAllItems();
        CbMenu.addItem("Menu");
        CbMenu.addItem("Clientes");
        CbMenu.addItem("Categorias");
        CbMenu.addItem("Compras");
        CbMenu.addItem("DetCompras");
        CbMenu.addItem("DetPedidos");
        CbMenu.addItem("Empleados");
        CbMenu.addItem("Pedidos");
        CbMenu.addItem("Productos");
        CbMenu.addItem("Proveedores");
        CbMenu.addItem("Sucursales");
        CbMenu.addItem("Transportes");
        CbMenu.updateUI();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        CbMenu = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Cerrar Sesion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Menu desplegable:");

        CbMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CbMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 166, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jButton1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(237, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
Login frmLogin = new Login();
        frmLogin.setLocationRelativeTo(this);
        frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmLogin.setVisible(true);
        this.setVisible(false);    }//GEN-LAST:event_jButton1ActionPerformed

    private void CbMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbMenuActionPerformed
        Object selectedObject = CbMenu.getSelectedItem();
         if ("Clientes".equals(selectedObject)) {
                   Client frmClient = new Client();
        frmClient.setLocationRelativeTo(this);
        frmClient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmClient.setVisible(true);
        this.setVisible(false);
                }
         if ("Categorias".equals(selectedObject)) {
                   Categoria frmCategoria = new Categoria();
        frmCategoria.setLocationRelativeTo(this);
        frmCategoria.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmCategoria.setVisible(true);
        this.setVisible(false);
                }
         if ("Compras".equals(selectedObject)) {
                   Compras frmgo = new Compras();
        frmgo.setLocationRelativeTo(this);
        frmgo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmgo.setVisible(true);
        this.setVisible(false);
                }
         if ("DetCompras".equals(selectedObject)) {
                   DetCompras FrmGo = new DetCompras();
        FrmGo.setLocationRelativeTo(this);
        FrmGo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrmGo.setVisible(true);
        this.setVisible(false);
                }
          if ("DetPedidos".equals(selectedObject)) {
                   DetPedido FrmGo = new DetPedido();
        FrmGo.setLocationRelativeTo(this);
        FrmGo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrmGo.setVisible(true);
        this.setVisible(false);
                }
          if ("Empleados".equals(selectedObject)) {
                   Empleado FrmGo = new Empleado();
        FrmGo.setLocationRelativeTo(this);
        FrmGo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrmGo.setVisible(true);
        this.setVisible(false);
                }
          if ("Pedidos".equals(selectedObject)) {
                   Pedidos FrmGo = new Pedidos();
        FrmGo.setLocationRelativeTo(this);
        FrmGo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrmGo.setVisible(true);
        this.setVisible(false);
                }
           if ("Productos".equals(selectedObject)) {
                   Productos FrmGo = new Productos();
        FrmGo.setLocationRelativeTo(this);
        FrmGo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrmGo.setVisible(true);
        this.setVisible(false);
                }
             if ("Proveedores".equals(selectedObject)) {
                   Proveedor FrmGo = new Proveedor();
        FrmGo.setLocationRelativeTo(this);
        FrmGo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrmGo.setVisible(true);
        this.setVisible(false);
                }
              if ("Sucursales".equals(selectedObject)) {
                   Sucursales FrmGo = new Sucursales();
        FrmGo.setLocationRelativeTo(this);
        FrmGo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrmGo.setVisible(true);
        this.setVisible(false);
                }
               if ("Transportes".equals(selectedObject)) {
                   Transporte FrmGo = new Transporte();
        FrmGo.setLocationRelativeTo(this);
        FrmGo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrmGo.setVisible(true);
        this.setVisible(false);
                }
    }//GEN-LAST:event_CbMenuActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CbMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
