package crud_tugas;

import java.sql.*;
import java.text.SimpleDateFormat;
import crud_tugas.koneksi;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Kasir extends javax.swing.JFrame {

    koneksi con;
    private Object [][]tbl_input = null;
    private String []label = {"Id", "Nama Barang", "Harga", "Stock"};
    private String []labelnota = {"Nama Barang", "Harga Satuan", "Jumlah", "Harga"};
    DefaultTableModel dm;
    
    public Kasir() {
        initComponents();
        con = new koneksi();
        con.Class();
        BacaTabel();
        autonumber();
        identitas();
        BacaNota();       
        this.setLocationRelativeTo(null);
        new Thread(){
            public void run(){
                while (true) {                    
                    Calendar kal= new GregorianCalendar();
                    int tahun= kal.get(Calendar.YEAR);
                    int bulan= kal.get(Calendar.MONTH)+1;
                    int hari= kal.get(Calendar.DAY_OF_MONTH);
                    int jam= kal.get(Calendar.HOUR_OF_DAY);
                    int menit= kal.get(Calendar.MINUTE);
                    int detik= kal.get(Calendar.SECOND);
                    String tanggal = tahun+"-"+bulan+"-"+hari;
                    String waktu = jam+" : "+menit+" : "+detik;
                    txttanggal.setText(tanggal);
                    txtjam.setText(waktu);
                }
            }
        }.start();
    }

//    private void buatkolom(){
//        dm = (DefaultTableModel) tabelnota.getModel();
//        dm.addColumn("Nama Barang");
//        dm.addColumn("Harga Satuan");
//        dm.addColumn("Jumlah Barang");
//        dm.addColumn("Harga");
//
//    }
//    
//    private void input(String nama,String jumlah,String harga){
//        
//        dm=(DefaultTableModel) tabelnota.getModel();
//        String[] rowData={nama,jumlah, harga};
//        dm.addRow(rowData);
//    }
    
    private void autonumber(){
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select MAX(idtransaksi) From transaksi";
            con.rr = con.ss.executeQuery(sql);
                if(con.rr.next()){
                    int a = con.rr.getInt(1);
                    txt_transaksi.setText(Integer.toString(a+1)); //Nilai input yang terakhir ditambahkan 1
                }
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e);
        }
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
                tbl_input [x][2] = con.rr.getString("harga");
                tbl_input [x][3] = con.rr.getString("stock");
                x++;
            }
            txttabel.setModel(new DefaultTableModel(tbl_input, label));                    
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void BacaNota(){
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select * From pembelian Where id_transaksi ='"+txt_transaksi.getText()+"'";
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
                tbl_input [x][0] = con.rr.getString("nama_barang");
                tbl_input [x][1] = con.rr.getString("harga_satuan");
                tbl_input [x][2] = con.rr.getString("qty");
                tbl_input [x][3] = con.rr.getString("harga");
                x++;
            }
            tabelnota.setModel(new DefaultTableModel(tbl_input, labelnota));                    
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setTabel(){
        int row = txttabel.getSelectedRow();
        txt_id.setText((String)txttabel.getValueAt(row, 0));
        txtnamabarang_.setText((String)txttabel.getValueAt(row, 1));
        txt_harga.setText((String)txttabel.getValueAt(row, 2));
        txtjumlah.setText("1");
        penjumlah_.setText("0");
        txt_totharga.setText("0");
//        txtstock.setText((String)txttabel.getValueAt(row, 4));
    }
    
    private void setTabelNota(){
        int row = tabelnota.getSelectedRow();
        txtnamabarang_.setText((String)tabelnota.getValueAt(row, 0));
        txt_harga.setText((String)tabelnota.getValueAt(row, 1));
        txtjumlah.setText((String)tabelnota.getValueAt(row, 2));
        penjumlah_.setText((String)tabelnota.getValueAt(row, 2));
        txt_totharga.setText((String)tabelnota.getValueAt(row, 3));
//        txtstock.setText((String)txttabel.getValueAt(row, 4));
    }
    
    private void simpan(){
        String idtransaksi = this.txt_transaksi.getText();
        String idbarang = this.txt_id.getText();
        String namabarang = this.txtnamabarang_.getText();     
        String harga = this.txt_harga.getText();
        String qty = this.txtjumlah.getText();
        
        int x=0, y = Integer.valueOf(idbarang), a=0, z=0, c=0, g=0, h=0;
        int jml = Integer.valueOf(qty), b = Integer.valueOf(harga);
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select * From pembelian Where id_transaksi='"+txt_transaksi.getText()+"' And id_barang='"+txt_id.getText()+"'";
            con.rr = con.ss.executeQuery(sql);
                if(con.rr.next()){
                    x = con.rr.getInt(2);
                    c = con.rr.getInt(4);
                    a = con.rr.getInt(5);
                    z = con.rr.getInt(6);
                    penjumlah_.setText(Integer.toString(a));
                    txt_totharga.setText(Integer.toString(z));
                }
            if(x==y){
                int hasil = jml + a - 1;
                String hasilS = String.valueOf(hasil);
                int total = b + z - c;
                String totalS = String.valueOf(total);
                
                penjumlah_.setText(hasilS);
                txt_totharga.setText(totalS);
                edit();
            }
            else{
                String db2= "Insert into pembelian values (?,?,?,?,?,?)";
                PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(db2);
                p.setString(1, idtransaksi);
                p.setString(2, idbarang);
                p.setString(3, namabarang);
                p.setString(4, harga);
                p.setString(5, qty);
                p.setString(6, harga);
                p.executeUpdate();
            
                BacaNota();
            }
            txtfilter.setText("");
            String byr = "Select * From pembelian Where id_transaksi='"+txt_transaksi.getText()+"'";
            con.rr = con.ss.executeQuery(byr);
                while(con.rr.next()){
                    g = g + con.rr.getInt(6);
                    h = h + con.rr.getInt(5);
                    txt_jumlahitem.setText(Integer.toString(h));
                    txttotalbelanja.setText(Integer.toString(g));
                }
            BacaTabel();
        } catch (SQLException e) {    
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void edit(){
        String idtransaksi = this.txt_transaksi.getText();
        String idbarang = this.txt_id.getText();     
        String harga = this.txt_harga.getText();
        String qty = this.txtjumlah.getText();
        String jml = this.penjumlah_.getText();
        String tot = this.txt_totharga.getText();
        int a = Integer.valueOf(jml), b = Integer.valueOf(qty), g=0, h=0;
        int x = Integer.valueOf(tot), y = Integer.valueOf(harga);
        int hasil = a + b;
        int total = x + y;
        String hasilS = String.valueOf(hasil);
        String totalS = String.valueOf(total);
        
        try {
          String sql= "Update pembelian Set qty=?, harga=? Where id_barang=? And id_transaksi=?";
          PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(sql);
            p.setString(4, idtransaksi);
            p.setString(3, idbarang);
            p.setString(2, totalS);
            p.setString(1, hasilS);
            p.executeUpdate();
            
            String byr = "Select * From pembelian Where id_transaksi='"+txt_transaksi.getText()+"'";
            con.rr = con.ss.executeQuery(byr);
                while(con.rr.next()){
                    g = g + con.rr.getInt(6);
                    h = h + con.rr.getInt(5);
                    txttotalbelanja.setText(Integer.toString(g));
                    txt_jumlahitem.setText(Integer.toString(h));
                }
            
            BacaNota();
            BacaTabel();
            
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    private void hapus(){
        try {
            String sql = "Delete From pembelian Where id_transaksi = '"+txt_transaksi.getText()+"'";
            con.ss.executeUpdate(sql);
            con.ss.close();
            BacaTabel();
            txt_transaksi.requestFocus();
                       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void editNota(){
        String idtransaksi = this.txt_transaksi.getText();
        String nama = this.txtnamabarang_.getText();
        String harga = this.txt_harga.getText();
        String qty = this.txtjumlah.getText();
        int hasil = Integer.valueOf(qty), g=0, h=0;
        int y = Integer.valueOf(harga);
        int total = hasil * y;
 
        String hasilS = String.valueOf(hasil);
        String totalS = String.valueOf(total);
        
        try {
          String sql= "Update pembelian Set qty=?, harga=? Where nama_barang=? And id_transaksi=?";
          PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(sql);
            p.setString(4, idtransaksi);
            p.setString(3, nama);
            p.setString(2, totalS);
            p.setString(1, hasilS);
            p.executeUpdate();
            
            String byr = "Select * From pembelian Where id_transaksi='"+txt_transaksi.getText()+"'";
            con.rr = con.ss.executeQuery(byr);
                while(con.rr.next()){
                    g = g + con.rr.getInt(6);
                    h = h + con.rr.getInt(5);
                    txttotalbelanja.setText(Integer.toString(g));
                    txt_jumlahitem.setText(Integer.toString(h));
                }
            
            BacaNota();
            BacaTabel();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    private void hapusSelectNota(){
        int g=0, h=0;
        try {
          String sql = "Delete From pembelian Where id_transaksi = '"+txt_transaksi.getText()+"' And nama_barang = '"+txtnamabarang_.getText()+"'";
            con.ss.executeUpdate(sql);
            txt_transaksi.requestFocus();
            
            String byr = "Select * From pembelian Where id_transaksi='"+txt_transaksi.getText()+"'";
            con.rr = con.ss.executeQuery(byr);
                while(con.rr.next()){
                    g = g + con.rr.getInt(6);
                    h = h + con.rr.getInt(5);
                    txttotalbelanja.setText(Integer.toString(g));
                    txt_jumlahitem.setText(Integer.toString(h));
                }
            con.ss.close();
            BacaNota();
            BacaTabel();
        }catch (SQLException e){
            System.out.println(e);
        }
    }
   
    private void simpanTransaksi(){
        String idtransaksi = this.txt_transaksi.getText();
        String idpeg = this.txtidpegawai.getText();     
        String tot_bayar = this.txttotalbelanja.getText();
        
        try {
          String sql= "Insert into transaksi values (?,?,?,NOW())";
          PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(sql);
            p.setString(1, idtransaksi);
            p.setString(2, idpeg);
            p.setString(3, tot_bayar);
            p.executeUpdate();
            
                        
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    
    private void baru (){
        txt_id.setText("");
        txtnamabarang_.setText("");
        txt_harga.setText(""); 
        txtjumlah.setText("");
        txt_totharga.setText(""); 
        penjumlah_.setText("");
    }
    
    private void clear (){
        txttotalbelanja.setText("0");
        txtkembalian.setText("0");
        txt_jumlahitem.setText("");
        txtbayar.setText("");
        txtjumlah.setText("");
    }
    
    private void identitas(){
        String idpegawai = String.valueOf(nextframe.id);
        try {
            String idp = "Select * From userlevel Where id_user='"+idpegawai+"'";
            con.rr = con.ss.executeQuery(idp);
                while(con.rr.next()){
                    String nama = con.rr.getString("nama_user");
                    String id = con.rr.getString("id_user");
                    namapegawai.setText(nama);
                    txtidpegawai.setText(id);
                }
            
            BacaNota();
            
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
        txtfilter = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelnota = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; //Disallow the editing of any cell
            }
        };
        txtjumlah = new javax.swing.JTextField();
        txttotalbelanja = new javax.swing.JLabel();
        txtbayar = new javax.swing.JTextField();
        txtkembalian = new javax.swing.JLabel();
        namapegawai = new javax.swing.JLabel();
        txtidpegawai = new javax.swing.JLabel();
        txttanggal = new javax.swing.JLabel();
        txtjam = new javax.swing.JLabel();
        btn_LogOut = new javax.swing.JLabel();
        btn_hapus = new javax.swing.JLabel();
        btn_edit = new javax.swing.JLabel();
        btn_bataltr = new javax.swing.JLabel();
        txt_transaksi = new javax.swing.JLabel();
        txt_jumlahitem = new javax.swing.JLabel();
        txtnamabarang_ = new javax.swing.JLabel();
        txt_id = new javax.swing.JLabel();
        txt_harga = new javax.swing.JLabel();
        txt_totharga = new javax.swing.JLabel();
        penjumlah_ = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txttabel.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        txttabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        txttabel.setColumnSelectionAllowed(true);
        txttabel.setOpaque(false);
        txttabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txttabelMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(txttabel);
        txttabel.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 440, 290));

        txtfilter.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
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
        getContentPane().add(txtfilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 390, 50));

        tabelnota.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tabelnota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabelnota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabelnotaMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabelnota);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 720, 390));

        txtjumlah.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        txtjumlah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtjumlah.setBorder(null);
        txtjumlah.setOpaque(false);
        txtjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahActionPerformed(evt);
            }
        });
        txtjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtjumlahKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtjumlahKeyTyped(evt);
            }
        });
        getContentPane().add(txtjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(631, 184, 50, 30));

        txttotalbelanja.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        txttotalbelanja.setForeground(new java.awt.Color(255, 255, 255));
        txttotalbelanja.setText("0");
        getContentPane().add(txttotalbelanja, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, 220, 40));

        txtbayar.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        txtbayar.setBorder(null);
        txtbayar.setOpaque(false);
        txtbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbayarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbayarKeyTyped(evt);
            }
        });
        getContentPane().add(txtbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 124, 156, 40));

        txtkembalian.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        txtkembalian.setForeground(new java.awt.Color(255, 255, 255));
        txtkembalian.setText("0");
        getContentPane().add(txtkembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 120, 170, 40));

        namapegawai.setFont(new java.awt.Font("Roboto", 1, 25)); // NOI18N
        namapegawai.setForeground(new java.awt.Color(255, 255, 255));
        namapegawai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(namapegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 620, 200, 28));

        txtidpegawai.setFont(new java.awt.Font("Roboto", 1, 25)); // NOI18N
        txtidpegawai.setForeground(new java.awt.Color(255, 255, 255));
        txtidpegawai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(txtidpegawai, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 660, 200, 26));

        txttanggal.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        getContentPane().add(txttanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 127, 21));

        txtjam.setFont(new java.awt.Font("DS-Digital", 1, 20)); // NOI18N
        getContentPane().add(txtjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, 117, 21));

        btn_LogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogOut(evt);
            }
        });
        getContentPane().add(btn_LogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 180, 30));

        btn_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusnotaselect(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 660, 180, 30));

        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editnota(evt);
            }
        });
        getContentPane().add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 660, 200, 30));

        btn_bataltr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_bataltransaksi(evt);
            }
        });
        getContentPane().add(btn_bataltr, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 660, 200, 30));

        txt_transaksi.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txt_transaksi.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txt_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 184, 70, 30));

        txt_jumlahitem.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txt_jumlahitem.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txt_jumlahitem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 184, 70, 30));

        txtnamabarang_.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        getContentPane().add(txtnamabarang_, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 420, 40));

        txt_id.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        getContentPane().add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 120, 30));

        txt_harga.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        getContentPane().add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 140, 30));

        txt_totharga.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        getContentPane().add(txt_totharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 510, 120, 30));

        penjumlah_.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        getContentPane().add(penjumlah_, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 550, 140, 30));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/newkasirutama_ res 1280 x 720-07.png"))); // NOI18N
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfilterActionPerformed

    private void txtfilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfilterKeyReleased
        DefaultTableModel table = (DefaultTableModel)txttabel.getModel();
        String search = txtfilter.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        txttabel.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_txtfilterKeyReleased

    private void txtjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjumlahActionPerformed

    private void txttabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttabelMousePressed
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
                    evt.consume();
                    setTabel();
                    simpan();
                    txtfilterKeyReleased(null);
                    txtjumlah.requestFocus();
            }
    }//GEN-LAST:event_txttabelMousePressed

    private void txtjumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjumlahKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            int a = Integer.valueOf(txtjumlah.getText());
            int b = Integer.valueOf(txt_harga.getText());
            int total = a * b;
        
            txt_harga.setText(String.valueOf(total));
            edit();
            txtfilter.requestFocus();
        }
    }//GEN-LAST:event_txtjumlahKeyPressed

    private void txtbayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbayarKeyReleased
        String output;
        int a = Integer.valueOf(txtbayar.getText());
        int b = Integer.valueOf(txttotalbelanja.getText());
        int kembalian = a - b;
        output = String.valueOf(kembalian);
        txtkembalian.setText(output);
    }//GEN-LAST:event_txtbayarKeyReleased

    private void txtbayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbayarKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            int a = Integer.valueOf(txtbayar.getText());
            int b = Integer.valueOf(txttotalbelanja.getText());
            if (a<b){
                JOptionPane.showMessageDialog(null, "Bayarnya Kurang!!!");
            }
            else{
                simpanTransaksi();
                clear();
                baru();
                autonumber();
                BacaNota();
            }
        }
    }//GEN-LAST:event_txtbayarKeyPressed

    private void tabelnotaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelnotaMousePressed
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
                    evt.consume();
                    setTabelNota();
            }
    }//GEN-LAST:event_tabelnotaMousePressed

    private void LogOut(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOut
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LogOut

    private void hapusnotaselect(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusnotaselect
        hapusSelectNota();
    }//GEN-LAST:event_hapusnotaselect

    private void btn_editnota(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editnota
        editNota();
    }//GEN-LAST:event_btn_editnota

    private void btn_bataltransaksi(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_bataltransaksi
        hapus();
        baru();
        clear();
        BacaNota();
    }//GEN-LAST:event_btn_bataltransaksi

    private void txtjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtjumlahKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE) || (karakter == KeyEvent.VK_ENTER)))){
        getToolkit().beep();
        evt.consume();
        }
    }//GEN-LAST:event_txtjumlahKeyTyped

    private void txtbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbayarKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE) || (karakter == KeyEvent.VK_ENTER)))){
        getToolkit().beep();
        evt.consume();
        }
    }//GEN-LAST:event_txtbayarKeyTyped

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
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel btn_LogOut;
    private javax.swing.JLabel btn_bataltr;
    private javax.swing.JLabel btn_edit;
    private javax.swing.JLabel btn_hapus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel namapegawai;
    private javax.swing.JLabel penjumlah_;
    private javax.swing.JTable tabelnota;
    private javax.swing.JLabel txt_harga;
    private javax.swing.JLabel txt_id;
    private javax.swing.JLabel txt_jumlahitem;
    private javax.swing.JLabel txt_totharga;
    private javax.swing.JLabel txt_transaksi;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtfilter;
    private javax.swing.JLabel txtidpegawai;
    private javax.swing.JLabel txtjam;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JLabel txtkembalian;
    private javax.swing.JLabel txtnamabarang_;
    private javax.swing.JTable txttabel;
    private javax.swing.JLabel txttanggal;
    private javax.swing.JLabel txttotalbelanja;
    // End of variables declaration//GEN-END:variables
}
