package crud_tugas;
import java.sql.*;
import java.text.SimpleDateFormat;
import crud_tugas.koneksi;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CrudBarangGudang extends javax.swing.JFrame {
    koneksi con;
    private Object [][]tbl_input = null;
    private String []label = {"Id", "Nama Barang", "Jenis", "Harga", "Stock"};
    String level;
        
    public CrudBarangGudang() {
        initComponents();
        con = new koneksi();
        con.Class();
        BacaTabel();  
        identitas();
    }
    
    private void BacaTabel(){
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select * From barang Order By idbarang";
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
                tbl_input [x][0] = con.rr.getString("idbarang");
                tbl_input [x][1] = con.rr.getString("namabarang");
                tbl_input [x][2] = con.rr.getString("jenisbarang");
                tbl_input [x][3] = con.rr.getString("harga");
                tbl_input [x][4] = con.rr.getString("stock");
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
        txtnamabarang.setText((String)txttabel.getValueAt(row, 1));        
        txtjenisbarang.setText((String)txttabel.getValueAt(row, 2)); ;
        txtharga.setText((String)txttabel.getValueAt(row, 3));
        txtstock.setText((String)txttabel.getValueAt(row, 4));
    }
    
    private void simpan(){
        String idbarang = this.txtid.getText();
        String namabarang = this.txtnamabarang.getText();       
        String jenisbarang = this.txtjenisbarang.getText();
        String harga = this.txtharga.getText();
        String stock = this.txtstock.getText();
        try {
          String sql= "Insert into barang values (?,?,?,?,?)";
          PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(sql);
            p.setString(1, idbarang);
            p.setString(2, namabarang);
            p.setString(3, jenisbarang);
            p.setString(4, harga);
            p.setString(5, stock);
            p.executeUpdate();
            
            BacaTabel();
            JOptionPane.showMessageDialog(this, "Data sukses di input");
                        
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    private void edit(){
        String idbarang = this.txtid.getText();
        String namabarang = this.txtnamabarang.getText();
        String jenisbarang = this.txtjenisbarang.getText();
        String harga = this.txtharga.getText();
        String stock = this.txtstock.getText();
        
        try {
          String sql= "Update barang Set namabarang=?, jenisbarang=?, harga=?, stock=? Where idbarang=?";
          PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(sql);
            p.setString(5, idbarang);
            p.setString(1, namabarang);
            p.setString(2, jenisbarang);
            p.setString(3, harga);
            p.setString(4, stock);
            p.executeUpdate();
            
            BacaTabel();
            JOptionPane.showMessageDialog(this, "Data sukses di edit");
                        
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    private void hapus(){
        try {
            String sql = "Delete From barang Where idbarang = '"+txtid.getText()+"'";
            con.ss.executeUpdate(sql);
            con.ss.close();
            JOptionPane.showMessageDialog(null, " Data berhasil dihapus");
            BacaTabel();
            txtid.requestFocus();
                       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
   
    private  void baru (){
        txtid.setText("");
        txtnamabarang.setText("");
        txtjenisbarang.setText("");
        txtharga.setText(""); 
        txtstock.setText("");
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
        txttabel = new javax.swing.JTable();
        txtstock = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        txtjenisbarang = new javax.swing.JTextField();
        txtnamabarang = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        txtfilter = new javax.swing.JTextField();
        btn_hapus = new javax.swing.JLabel();
        btn_edit = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
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

        txtstock.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtstock.setBorder(null);
        txtstock.setOpaque(false);
        txtstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstockActionPerformed(evt);
            }
        });
        getContentPane().add(txtstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 600, 320, 30));

        txtharga.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtharga.setBorder(null);
        txtharga.setOpaque(false);
        getContentPane().add(txtharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 320, 40));

        txtjenisbarang.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtjenisbarang.setBorder(null);
        txtjenisbarang.setOpaque(false);
        txtjenisbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjenisbarangActionPerformed(evt);
            }
        });
        getContentPane().add(txtjenisbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 320, 30));

        txtnamabarang.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtnamabarang.setBorder(null);
        txtnamabarang.setOpaque(false);
        getContentPane().add(txtnamabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 320, 30));

        txtid.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtid.setBorder(null);
        txtid.setOpaque(false);
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        getContentPane().add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 320, 30));

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
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 660, 140, 30));

        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editMouseClicked(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 660, 140, 30));

        btn_simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpanMouseClicked(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 660, 150, 30));

        btn_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluarMouseClicked(evt);
            }
        });
        getContentPane().add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 660, 130, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/data barang_ res 1280 x 720-06.png"))); // NOI18N
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

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtjenisbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjenisbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjenisbarangActionPerformed

    private void txtstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstockActionPerformed

    private void btn_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMouseClicked
        hapus();
        baru();
    }//GEN-LAST:event_btn_hapusMouseClicked

    private void btn_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseClicked
        edit();
        baru();
    }//GEN-LAST:event_btn_editMouseClicked

    private void btn_simpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpanMouseClicked
        simpan();
        baru();
    }//GEN-LAST:event_btn_simpanMouseClicked

    private void btn_keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_keluarMouseClicked
        if(level.equals("1")){
          new Admin().setVisible(true);
          this.dispose(); 
        }
        else{
          new Login().setVisible(true);
          this.dispose();  
        }
    }//GEN-LAST:event_btn_keluarMouseClicked

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrudBarangGudang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel btn_edit;
    private javax.swing.JLabel btn_hapus;
    private javax.swing.JLabel btn_keluar;
    private javax.swing.JLabel btn_simpan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtfilter;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtjenisbarang;
    private javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txtstock;
    private javax.swing.JTable txttabel;
    // End of variables declaration//GEN-END:variables
}
