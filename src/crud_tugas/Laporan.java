package crud_tugas;

import java.sql.*;
import java.text.SimpleDateFormat;
import crud_tugas.koneksi;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Laporan extends javax.swing.JFrame {

    koneksi con;
    private Object [][]tbl_input = null;
    private String []label = {"ID TRANSAKSI", "ID PEGAWAI", "TOTAL BELANJA", "TANGGAL"};
    
    
    public Laporan() {
        initComponents();
        con = new koneksi();
        con.Class();
        BacaTabel();
    }

    private void tanggalawal(){
        if(jDate1.getDate()!=null){
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            txttglawal.setText(String.valueOf(format.format(jDate1.getDate())));
        }
    }
    
    private void tanggalakhir(){
        if(jDate2.getDate()!=null){
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            txttglakhir.setText(String.valueOf(format.format(jDate2.getDate())));
        }
    }
    
    private void BacaTabel(){
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select * From transaksi Order By idtransaksi";
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
                tbl_input [x][0] = con.rr.getString("idtransaksi");
                tbl_input [x][1] = con.rr.getString("id_pegawai");
                tbl_input [x][2] = con.rr.getString("total_belanja");
                tbl_input [x][3] = con.rr.getString("tanggal_waktu");
                x++;
            }
            txttabel.setModel(new DefaultTableModel(tbl_input, label));                    
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void tampilkan(){
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select * From transaksi Where DATE(tanggal_waktu)>='"+txttglawal.getText()+"' AND DATE(tanggal_waktu)<='"+txttglakhir.getText()+"'";
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
                tbl_input [x][0] = con.rr.getString("idtransaksi");
                tbl_input [x][1] = con.rr.getString("id_pegawai");
                tbl_input [x][2] = con.rr.getString("total_belanja");
                tbl_input [x][3] = con.rr.getString("tanggal_waktu");
                x++;
            }
            txttabel.setModel(new DefaultTableModel(tbl_input, label));                    
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void hasil(){
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select SUM(total_belanja) From transaksi Where DATE(tanggal_waktu)>='"+txttglawal.getText()+"' AND DATE(tanggal_waktu)<='"+txttglakhir.getText()+"'";
            con.rr = con.ss.executeQuery(sql);
                if(con.rr.next()){
                    int a = con.rr.getInt(1);
                    txtpendapatan.setText(Integer.toString(a));
                }
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDate1 = new com.toedter.calendar.JDateChooser();
        jDate2 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txttabel = new javax.swing.JTable();
        btn_show = new javax.swing.JButton();
        txttglawal = new javax.swing.JLabel();
        txttglakhir = new javax.swing.JLabel();
        txtpendapatan = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDate1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDate1PropertyChange(evt);
            }
        });
        getContentPane().add(jDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 129, -1));

        jDate2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDate2PropertyChange(evt);
            }
        });
        getContentPane().add(jDate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 280, 136, -1));

        txttabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(txttabel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 700, 350));

        btn_show.setText("Tampilkan");
        btn_show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_showActionPerformed(evt);
            }
        });
        getContentPane().add(btn_show, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 280, -1, -1));
        getContentPane().add(txttglawal, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 214, 117, 3));
        getContentPane().add(txttglakhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 214, 114, 3));
        getContentPane().add(txtpendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 350, 150, 26));

        jButton1.setText("KELUAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 640, 101, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/laporan pendapatan_ res 1280 x 720-09.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDate1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDate1PropertyChange
        tanggalawal();
    }//GEN-LAST:event_jDate1PropertyChange

    private void jDate2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDate2PropertyChange
        tanggalakhir();
    }//GEN-LAST:event_jDate2PropertyChange

    private void btn_showActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_showActionPerformed
        tampilkan();
        hasil();
    }//GEN-LAST:event_btn_showActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Admin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_show;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDate1;
    private com.toedter.calendar.JDateChooser jDate2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtpendapatan;
    private javax.swing.JTable txttabel;
    private javax.swing.JLabel txttglakhir;
    private javax.swing.JLabel txttglawal;
    // End of variables declaration//GEN-END:variables
}
