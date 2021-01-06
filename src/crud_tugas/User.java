package crud_tugas;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class User extends javax.swing.JFrame {

    koneksi con;
    private Object [][]tbl_input = null;
    private String []label = {"ID USER", "NAMA USER", "LEVEL"};
    
    
    public User() {
        initComponents();
        con = new koneksi();
        con.Class();
        BacaTabel();
    }

     private void BacaTabel(){
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select * From userlevel Order By id_user";
            con.rr = con.ss.executeQuery(sql);
            ResultSetMetaData m = con.rr.getMetaData();
            int kolom = m.getColumnCount();
            int baris = 0;
            while (con.rr.next()){
                baris = con.rr.getRow();
            }
            tbl_input = new Object[baris][kolom];
            int x = 0;
            con.rr.beforeFirst();            
            while (con.rr.next()){
                tbl_input [x][0] = con.rr.getString("id_user");
                tbl_input [x][1] = con.rr.getString("nama_user");
                tbl_input [x][2] = con.rr.getString("level_user");
                x++;
            }
            txttabel.setModel(new DefaultTableModel(tbl_input, label));                    
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
     private void setTabel(){
        int row = txttabel.getSelectedRow();
        txtid.setText((String)txttabel.getValueAt(row, 0));
    }
     
    private void ubahpass(){
        String iduser = this.txtid.getText();
        String pass = this.txtpass.getText();
        
        try {
          String sql= "Update userlevel Set pass_user=? Where id_user=?";
          PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(sql);
            p.setString(2, iduser);
            p.setString(1, pass);
            p.executeUpdate();
            
            BacaTabel();
            JOptionPane.showMessageDialog(this, "Data sukses di edit");
                        
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    private void ubahlvl(){
        String iduser = this.txtid.getText();
        int pil;
        String lvl;
        
        try {
          pil=combo_access.getSelectedIndex();
          if(pil==0){
              lvl="0";
          }
          else if(pil==1){
              lvl="1";
          }
          else if(pil==2){
              lvl="2";
          }
          else{
              lvl="3";
          }
          String sql= "Update userlevel Set level_user=? Where id_user=?";
          PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(sql);
            p.setString(2, iduser);
            p.setString(1, lvl);
            p.executeUpdate();
            
            BacaTabel();
            JOptionPane.showMessageDialog(this, "Data sukses di edit");
                        
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    private  void baru (){
        txtid.setText("");
        txtpass.setText("");
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txttabel = new javax.swing.JTable();
        txtid = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        combo_access = new javax.swing.JComboBox();
        btn_edit = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        btn_ubhpass = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txttabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        txttabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(txttabel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 710, 350));

        txtid.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        getContentPane().add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 290, 40));

        txtpass.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        getContentPane().add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, 290, 41));

        combo_access.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        combo_access.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NO ACCESS", "ADMIN", "KASIR", "GUDANG" }));
        getContentPane().add(combo_access, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 290, -1));

        btn_edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_edit.setText("UBAH ACCESS");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 650, 140, 45));

        btn_keluar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_keluar.setText("KELUAR");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 650, 140, 45));

        btn_ubhpass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_ubhpass.setText("UBAH PASSWORD");
        btn_ubhpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubhpassActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ubhpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 650, -1, 45));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/USER_ res 1280 x 720-10.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        ubahlvl();
        baru();
        BacaTabel();
    }//GEN-LAST:event_btn_editActionPerformed

    private void txttabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttabelMouseClicked
        setTabel();
    }//GEN-LAST:event_txttabelMouseClicked

    private void btn_ubhpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubhpassActionPerformed
        ubahpass();
        baru();
    }//GEN-LAST:event_btn_ubhpassActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        new Admin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_keluarActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_ubhpass;
    private javax.swing.JComboBox combo_access;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtid;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTable txttabel;
    // End of variables declaration//GEN-END:variables
}
