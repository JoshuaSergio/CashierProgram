package crud_tugas;
import java.sql.*;
import crud_tugas.koneksi;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Transaksi extends javax.swing.JFrame {
    koneksi con;
    private Object [][]tbl_input = null;
    private String []label = {"Id Transaksi", "Id User", "Nama Barang", "Jumlah", "Harga", "Waktu"};
    String level;
        
    public Transaksi() {
        initComponents();
        con = new koneksi();
        con.Class();
        BacaTabel();  
        identitas();
    }
    
    private void BacaTabel(){
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select id_transaksi, id_pegawai, nama_barang, qty, harga_satuan, tanggal_waktu from transaksi INNER JOIN pembelian ON id_transaksi=id_transaksi";
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
                tbl_input [x][0] = con.rr.getString("id_transaksi");
                tbl_input [x][1] = con.rr.getString("id_pegawai");
                tbl_input [x][2] = con.rr.getString("nama_barang");
                tbl_input [x][3] = con.rr.getString("qty");
                tbl_input [x][4] = con.rr.getString("harga_satuan");
                tbl_input [x][5] = con.rr.getString("tanggal_waktu");
                x++;
            }
            txttabel.setModel(new DefaultTableModel(tbl_input, label));                    
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setTabel(){
        int row = txttabel.getSelectedRow();
        ttxtidtr.setText((String)txttabel.getValueAt(row, 0));
        txtidpeg.setText((String)txttabel.getValueAt(row, 1));        
        txtnamabar.setText((String)txttabel.getValueAt(row, 2));
        txtjumlah.setText((String)txttabel.getValueAt(row, 3));
        txthargas.setText((String)txttabel.getValueAt(row, 4));
        txtwakgal.setText((String)txttabel.getValueAt(row, 5));
    }
    
    private void hapus(){
        try {
            String sql = "Delete From pembelian Where id_transaksi = '"+ttxtidtr.getText()+"'";
            String trs = "Delete From transaksi Where idtransaksi = '"+ttxtidtr.getText()+"'";
            con.ss.executeUpdate(sql);
            con.ss.executeUpdate(trs);
            con.ss.close();
            JOptionPane.showMessageDialog(null, " Data berhasil dihapus");
            BacaTabel();
            ttxtidtr.requestFocus();
                       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
   
    private  void baru (){
        ttxtidtr.setText("");
        txtidpeg.setText("");
        txtnamabar.setText("");
        txtjumlah.setText(""); 
        txtwakgal.setText("");
    }
    
    private void identitas(){
        String idpegawai = String.valueOf(nextframe.id);
        try {
            String idp = "Select * From userlevel Where id_user='"+idpegawai+"'";
            con.rr = con.ss.executeQuery(idp);
                while(con.rr.next()){
                    level = con.rr.getString("level_user");
                }   
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txttabel = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        txtwakgal = new javax.swing.JTextField();
        txtjumlah = new javax.swing.JTextField();
        txtnamabar = new javax.swing.JTextField();
        txtidpeg = new javax.swing.JTextField();
        ttxtidtr = new javax.swing.JTextField();
        txtfilter = new javax.swing.JTextField();
        btn_hapus = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
        txthargas = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 790, 410));

        txtwakgal.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtwakgal.setBorder(null);
        txtwakgal.setOpaque(false);
        txtwakgal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtwakgalActionPerformed(evt);
            }
        });
        getContentPane().add(txtwakgal, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 660, 310, 30));

        txtjumlah.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtjumlah.setBorder(null);
        txtjumlah.setOpaque(false);
        getContentPane().add(txtjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 310, 40));

        txtnamabar.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtnamabar.setBorder(null);
        txtnamabar.setOpaque(false);
        txtnamabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamabarActionPerformed(evt);
            }
        });
        getContentPane().add(txtnamabar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 320, 30));

        txtidpeg.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtidpeg.setBorder(null);
        txtidpeg.setOpaque(false);
        getContentPane().add(txtidpeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 320, 40));

        ttxtidtr.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        ttxtidtr.setBorder(null);
        ttxtidtr.setOpaque(false);
        ttxtidtr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttxtidtrActionPerformed(evt);
            }
        });
        getContentPane().add(ttxtidtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 320, 30));

        txtfilter.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtfilter.setBorder(null);
        txtfilter.setOpaque(false);
        txtfilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfilterActionPerformed(evt);
            }
        });
        txtfilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfilterKeyReleased(evt);
            }
        });
        getContentPane().add(txtfilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 280, 30));

        btn_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hapusMouseClicked(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 660, 150, 30));

        btn_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluarMouseClicked(evt);
            }
        });
        getContentPane().add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 660, 150, 30));
        getContentPane().add(txthargas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 320, 40));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/detail transaksi_ res 1280 x 720-08.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txttabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttabelMouseClicked
        setTabel();
    }//GEN-LAST:event_txttabelMouseClicked

    private void txtfilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfilterKeyReleased
        DefaultTableModel table = (DefaultTableModel)txttabel.getModel();
        String search = txtfilter.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        txttabel.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_txtfilterKeyReleased

    private void txtfilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfilterActionPerformed

    private void ttxtidtrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttxtidtrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttxtidtrActionPerformed

    private void txtnamabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamabarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamabarActionPerformed

    private void txtwakgalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtwakgalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtwakgalActionPerformed

    private void btn_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseClicked
        hapus();
        baru();
    }//GEN-LAST:event_btn_hapusMouseClicked

    private void btn_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseClicked
          new Admin().setVisible(true);
          this.dispose(); 
    }//GEN-LAST:event_btn_keluarMouseClicked

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_hapus;
    private javax.swing.JLabel btn_keluar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField ttxtidtr;
    private javax.swing.JTextField txtfilter;
    private javax.swing.JLabel txthargas;
    private javax.swing.JTextField txtidpeg;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtnamabar;
    private javax.swing.JTable txttabel;
    private javax.swing.JTextField txtwakgal;
    // End of variables declaration//GEN-END:variables
}
