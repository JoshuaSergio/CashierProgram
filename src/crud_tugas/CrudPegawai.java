package crud_tugas;
import java.sql.*;
import crud_tugas.koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CrudPegawai extends javax.swing.JFrame {
    koneksi con;
    private Object [][]tbl_input = null;
    private String []label = {"ID USER", "ID PEGAWAI", "NAMA LENGKAP", "EMAIL", "ALAMAT", "NO HP"};
    int a;
        
    public CrudPegawai() {
        initComponents();
        con = new koneksi();
        con.Class();
        BacaTabel();
        autonumber();
    }
    
    private void BacaTabel(){
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select * From pegawai Order By id_pegawai";
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
                tbl_input [x][1] = con.rr.getString("id_pegawai");
                tbl_input [x][2] = con.rr.getString("nama_lengkap");
                tbl_input [x][3] = con.rr.getString("email");
                tbl_input [x][4] = con.rr.getString("alamat");
                tbl_input [x][5] = con.rr.getString("no_hp");
                x++;
            }
            txttabel.setModel(new DefaultTableModel(tbl_input, label));                    
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setTabel(){
        int row = txttabel.getSelectedRow();
        txtid.setText((String)txttabel.getValueAt(row, 1));
        txtnamalengkap.setText((String)txttabel.getValueAt(row, 2));        
        txtemail.setText((String)txttabel.getValueAt(row, 3));
        txtalamat.setText((String)txttabel.getValueAt(row, 4));
        txtnohp.setText((String)txttabel.getValueAt(row, 5));
        txthps.setText((String)txttabel.getValueAt(row, 0));
    }
    
    private void simpan(){
        String id_pegawai = this.txtid.getText();
        String nama_lengkap = this.txtnamalengkap.getText();       
        String email = this.txtemail.getText();
        String alamat = this.txtalamat.getText();
        String no_hp = this.txtnohp.getText();
        String iduser = String.valueOf(a);
        String level = "0";
        try {
          String sql= "Insert into pegawai values (?,?,?,?,?,?)";
          PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(sql);
            p.setString(1, iduser);
            p.setString(2, id_pegawai);
            p.setString(3, nama_lengkap);
            p.setString(4, email);
            p.setString(5, alamat);
            p.setString(6, no_hp);
            p.executeUpdate();
            
          String lvl= "Insert into userlevel values (?,?,?,?)";
          PreparedStatement q = (PreparedStatement) con.cc.prepareStatement(lvl);
            q.setString(1, iduser);
            q.setString(2, iduser);
            q.setString(3, nama_lengkap);
            q.setString(4, level);
            q.executeUpdate();
            
            BacaTabel();
            JOptionPane.showMessageDialog(this, "Data sukses di input");
                        
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    private void edit(){
        String id_pegawai = this.txtid.getText();
        String nama_lengkap = this.txtnamalengkap.getText();
        String email = this.txtemail.getText();
        String alamat = this.txtalamat.getText();
        String no_hp = this.txtnohp.getText();
        
        try {
          String sql= "Update pegawai Set nama_lengkap=?, email=?, alamat=?, no_hp=? Where id_pegawai=?";
          PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(sql);
            p.setString(5, id_pegawai);
            p.setString(1, nama_lengkap);
            p.setString(2, email);
            p.setString(3, alamat);
            p.setString(4, no_hp);
            p.executeUpdate();
            
            BacaTabel();
            JOptionPane.showMessageDialog(this, "Data sukses di edit");
                        
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    private void hapus(){
        try {
            String usr = "Delete From userlevel Where id_user = '"+txthps.getText()+"'";
            con.ss.executeUpdate(usr);
            con.ss.close();
            String sql = "Delete From pegawai Where id_pegawai = '"+txtid.getText()+"'";
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
        txtnamalengkap.setText("");
        txtemail.setText("");
        txtalamat.setText(""); 
        txtnohp.setText("");
    }
    
     private void autonumber(){
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select MAX(id_user) From userlevel";
            con.rr = con.ss.executeQuery(sql);
                if(con.rr.next()){
                    a = con.rr.getInt(1);
                    a = a + 1;
                }
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txttabel = new javax.swing.JTable();
        txtnohp = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        txtemail = new javax.swing.JTextField();
        txtnamalengkap = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        btn_hapus = new javax.swing.JLabel();
        btn_edit = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JLabel();
        btn_keluar = new javax.swing.JLabel();
        txthps = new javax.swing.JLabel();
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 810, 480));

        txtnohp.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtnohp.setBorder(null);
        txtnohp.setOpaque(false);
        getContentPane().add(txtnohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 597, 320, -1));

        txtalamat.setColumns(20);
        txtalamat.setRows(5);
        jScrollPane2.setViewportView(txtalamat);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 350, 70));

        txtemail.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtemail.setBorder(null);
        txtemail.setOpaque(false);
        getContentPane().add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 320, 30));

        txtnamalengkap.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtnamalengkap.setBorder(null);
        txtnamalengkap.setOpaque(false);
        txtnamalengkap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamalengkapActionPerformed(evt);
            }
        });
        getContentPane().add(txtnamalengkap, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 320, 30));

        txtid.setFont(new java.awt.Font("Roboto", 0, 25)); // NOI18N
        txtid.setBorder(null);
        txtid.setOpaque(false);
        getContentPane().add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 320, 30));

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
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 660, 140, 30));

        btn_keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_keluarMouseClicked(evt);
            }
        });
        getContentPane().add(btn_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 660, 150, 30));

        txthps.setBackground(new java.awt.Color(255, 255, 255));
        txthps.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txthps, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 20, 10));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/data pegawai_ res 1280 x 720-05.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txttabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttabelMouseClicked
        setTabel();
    }//GEN-LAST:event_txttabelMouseClicked

    private void txtnamalengkapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamalengkapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamalengkapActionPerformed

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
        new Admin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_keluarMouseClicked

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrudPegawai().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txtemail;
    private javax.swing.JLabel txthps;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnamalengkap;
    private javax.swing.JTextField txtnohp;
    private javax.swing.JTable txttabel;
    // End of variables declaration//GEN-END:variables
}
