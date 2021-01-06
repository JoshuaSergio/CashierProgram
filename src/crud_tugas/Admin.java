package crud_tugas;

import java.sql.SQLException;
import crud_tugas.koneksi;
import java.sql.Statement;



/**
 *
 * @author user
 */
public class Admin extends javax.swing.JFrame {

    koneksi con;
    
    public Admin() {
        initComponents();
        con = new koneksi();
        con.Class();
        identitas();
    }

    private void identitas(){
        String idpegawai = String.valueOf(nextframe.id), id, nama, email, alamat, notelp;
        try {
            con.ss = (Statement) con.cc.createStatement();
            String idp = "Select * From pegawai Where id_user='"+idpegawai+"'";
            con.rr = con.ss.executeQuery(idp);
                while(con.rr.next()){
                    id = con.rr.getString("id_pegawai");
                    nama = con.rr.getString("nama_lengkap");
                    email = con.rr.getString("email");
                    alamat = con.rr.getString("alamat");
                    notelp = con.rr.getString("no_hp");
                    txtidpegawai.setText(id);
                    txtnamalengkap.setText(nama);
                    txtemail.setText(email);
                    txtalamat.setText(alamat);
                    txtnotelp.setText(notelp);
                }
            
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtnamalengkap = new javax.swing.JLabel();
        txtnotelp = new javax.swing.JLabel();
        txtemail = new javax.swing.JLabel();
        txtidpegawai = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        btn_pegawai = new javax.swing.JLabel();
        btn_barang = new javax.swing.JLabel();
        btn_user = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
        btn_transaksi = new javax.swing.JLabel();
        btn_laporan = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setName("frame5"); // NOI18N
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txtnamalengkap, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 294, 320, 30));
        getContentPane().add(txtnotelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, 320, 30));
        getContentPane().add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 460, 320, 30));
        getContentPane().add(txtidpegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 294, 320, 30));

        txtalamat.setColumns(20);
        txtalamat.setRows(5);
        jScrollPane1.setViewportView(txtalamat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 370, 340, 90));

        btn_pegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pegawaiMouseClicked(evt);
            }
        });
        getContentPane().add(btn_pegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 150, 50));

        btn_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_barangMouseClicked(evt);
            }
        });
        getContentPane().add(btn_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 150, 50));

        btn_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_userMouseClicked(evt);
            }
        });
        getContentPane().add(btn_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 150, 50));

        btn_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluarMouseClicked(evt);
            }
        });
        getContentPane().add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 100, 150, 50));

        btn_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_transaksiMouseClicked(evt);
            }
        });
        getContentPane().add(btn_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, 140, 50));

        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_laporanMouseClicked(evt);
            }
        });
        getContentPane().add(btn_laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 100, 150, 50));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profil manager_ res 1280 x 720-03.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pegawaiMouseClicked
        new CrudPegawai().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_pegawaiMouseClicked

    private void btn_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_barangMouseClicked
        new CrudBarangGudang().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_barangMouseClicked

    private void btn_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_userMouseClicked
        new User().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_userMouseClicked

    private void btn_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseClicked
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_keluarMouseClicked

    private void btn_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_transaksiMouseClicked
        new Transaksi().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_transaksiMouseClicked

    private void btn_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseClicked
        new Laporan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_laporanMouseClicked

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_barang;
    private javax.swing.JLabel btn_keluar;
    private javax.swing.JLabel btn_laporan;
    private javax.swing.JLabel btn_pegawai;
    private javax.swing.JLabel btn_transaksi;
    private javax.swing.JLabel btn_user;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JLabel txtemail;
    private javax.swing.JLabel txtidpegawai;
    private javax.swing.JLabel txtnamalengkap;
    private javax.swing.JLabel txtnotelp;
    // End of variables declaration//GEN-END:variables
}
