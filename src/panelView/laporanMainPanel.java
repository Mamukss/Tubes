package panelView;

import Controller.PembelianControl;
import Controller.TransaksiControl;
import DAO.TransaksiDAO;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Model.Karyawan;

public class laporanMainPanel extends javax.swing.JPanel {
    private PembelianControl pc;
    private final TransaksiControl tc;
    private Karyawan k = null;
    String actionKaryawan = null;
    private String selectedId = null;
    private Component rootPane;

    public laporanMainPanel() {
        tc = new TransaksiControl(new TransaksiDAO());
        pc = new PembelianControl();
        initComponents();
        showTransaksi("");
        showPesanan("AWALAN TIDAK PERLU DIBUKA");
        menuTerlarisValue.setText(tc.cariMenuTerlaris());
        totalOmsetValue.setText("Rp" + String.valueOf(tc.hitungTotalOmset()));
        totalTransaksiValue.setText(String.valueOf(tc.hitungTotalTransaksi())+" Transaksi");
    }

    private static void addHeaderClickListener(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int col = header.columnAtPoint(e.getPoint());
            }
        });
        TableModel tableModel = table.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>((TableModel) tableModel);

        sorter.setComparator(4, Comparator.comparingDouble(value -> {
            if (value instanceof Number) {
                return ((Number) value).doubleValue();
            }
            return Double.parseDouble(value.toString());
        }));
        table.setRowSorter(sorter);
    }
    
    private static void addHeaderClickListenerPesanan(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int col = header.columnAtPoint(e.getPoint());
            }
        });
        TableModel tableModel = table.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>((TableModel) tableModel);

        sorter.setComparator(3, Comparator.comparingDouble(value -> {
            if (value instanceof Number) {
                return ((Number) value).doubleValue();
            }
            return Double.parseDouble(value.toString());
        }));
        table.setRowSorter(sorter);
    }
    
    private void showTransaksi(String search) {
        tabelKaryawan.setModel(tc.showTableBySearch(search));
        addHeaderClickListener(tabelKaryawan);
    
    }
    
    private void showPesanan(String id_pesanan) {
        tabelPesanan.setModel(pc.showTableBySearch(id_pesanan));
        addHeaderClickListenerPesanan(tabelPesanan);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        mainPanel = new javax.swing.JPanel();
        searchKendaraanInputPanel = new javax.swing.JPanel();
        searchKaryawanInputLabel = new javax.swing.JLabel();
        searchKaryawanInputTextField = new javax.swing.JTextField();
        searchKaryawanInputButton = new javax.swing.JButton();
        batalkanKaryawanButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKaryawan = new javax.swing.JTable();
        minumanScrollPane1 = new javax.swing.JScrollPane();
        tabelPesanan = new javax.swing.JTable();
        searchKaryawanInputLabel1 = new javax.swing.JLabel();
        searchKaryawanInputLabel2 = new javax.swing.JLabel();
        searchKaryawanInputButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        totalTransaksiTextField = new javax.swing.JTextField();
        menuTerlarisTextField = new javax.swing.JTextField();
        batalkanKaryawanButton1 = new javax.swing.JButton();
        totalTransaksiValue = new javax.swing.JTextField();
        menuTerlarisValue = new javax.swing.JTextField();
        totalOmsetTextField = new javax.swing.JTextField();
        totalOmsetValue = new javax.swing.JTextField();
        tanggalMulai = new com.toedter.calendar.JDateChooser();
        tanggalSelesai = new com.toedter.calendar.JDateChooser();

        jMenuItem1.setText("jMenuItem1");

        setBackground(new java.awt.Color(255, 218, 182));

        mainPanel.setBackground(new java.awt.Color(153, 153, 255));
        mainPanel.setPreferredSize(new java.awt.Dimension(1208, 794));

        searchKendaraanInputPanel.setBackground(new java.awt.Color(153, 153, 255));

        searchKaryawanInputLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        searchKaryawanInputLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchKaryawanInputLabel.setText("Pencarian Pesanan");

        searchKaryawanInputTextField.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        searchKaryawanInputTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKaryawanInputTextFieldKeyTyped(evt);
            }
        });

        searchKaryawanInputButton.setBackground(new java.awt.Color(137, 92, 3));
        searchKaryawanInputButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        searchKaryawanInputButton.setForeground(new java.awt.Color(255, 255, 255));
        searchKaryawanInputButton.setText("Cari");
        searchKaryawanInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchKaryawanInputButtonActionPerformed(evt);
            }
        });

        batalkanKaryawanButton.setBackground(new java.awt.Color(237, 78, 5));
        batalkanKaryawanButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        batalkanKaryawanButton.setForeground(new java.awt.Color(255, 255, 255));
        batalkanKaryawanButton.setText("Batalkan");
        batalkanKaryawanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalkanKaryawanButtonActionPerformed(evt);
            }
        });

        tabelKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Transaksi", "Nama Kasir", "Nama Pelanggan", "Tanggal Transaksi", "Total Transaksi"
            }
        ));
        tabelKaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKaryawanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelKaryawan);

        tabelPesanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Menu", "Nama Menu", "Jumlah", "Sub Total"
            }
        ));
        minumanScrollPane1.setViewportView(tabelPesanan);

        searchKaryawanInputLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        searchKaryawanInputLabel1.setForeground(new java.awt.Color(255, 255, 255));
        searchKaryawanInputLabel1.setText("Pencarian Pesanan Berdasarkan Tanggal");

        searchKaryawanInputLabel2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        searchKaryawanInputLabel2.setForeground(new java.awt.Color(255, 255, 255));
        searchKaryawanInputLabel2.setText("Sampai");

        searchKaryawanInputButton1.setBackground(new java.awt.Color(137, 92, 3));
        searchKaryawanInputButton1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        searchKaryawanInputButton1.setForeground(new java.awt.Color(255, 255, 255));
        searchKaryawanInputButton1.setText("Cari");
        searchKaryawanInputButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchKaryawanInputButton1ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(153, 153, 255));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("PESANAN");
        jTextField1.setBorder(null);

        totalTransaksiTextField.setEditable(false);
        totalTransaksiTextField.setBackground(new java.awt.Color(153, 153, 255));
        totalTransaksiTextField.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        totalTransaksiTextField.setForeground(new java.awt.Color(255, 255, 255));
        totalTransaksiTextField.setText("Total Transaksi:");
        totalTransaksiTextField.setBorder(null);

        menuTerlarisTextField.setEditable(false);
        menuTerlarisTextField.setBackground(new java.awt.Color(153, 153, 255));
        menuTerlarisTextField.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        menuTerlarisTextField.setForeground(new java.awt.Color(255, 255, 255));
        menuTerlarisTextField.setText("Menu Terlaris:");
        menuTerlarisTextField.setBorder(null);
        menuTerlarisTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTerlarisTextFieldActionPerformed(evt);
            }
        });

        batalkanKaryawanButton1.setBackground(new java.awt.Color(51, 151, 56));
        batalkanKaryawanButton1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        batalkanKaryawanButton1.setForeground(new java.awt.Color(255, 255, 255));
        batalkanKaryawanButton1.setText("Cetak");
        batalkanKaryawanButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalkanKaryawanButton1ActionPerformed(evt);
            }
        });

        totalTransaksiValue.setEditable(false);
        totalTransaksiValue.setBackground(new java.awt.Color(255, 218, 182));
        totalTransaksiValue.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        totalTransaksiValue.setForeground(new java.awt.Color(137, 92, 3));
        totalTransaksiValue.setBorder(null);
        totalTransaksiValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTransaksiValueActionPerformed(evt);
            }
        });

        menuTerlarisValue.setEditable(false);
        menuTerlarisValue.setBackground(new java.awt.Color(255, 218, 182));
        menuTerlarisValue.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        menuTerlarisValue.setForeground(new java.awt.Color(137, 92, 3));
        menuTerlarisValue.setBorder(null);
        menuTerlarisValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTerlarisValueActionPerformed(evt);
            }
        });

        totalOmsetTextField.setEditable(false);
        totalOmsetTextField.setBackground(new java.awt.Color(153, 153, 255));
        totalOmsetTextField.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        totalOmsetTextField.setForeground(new java.awt.Color(255, 255, 255));
        totalOmsetTextField.setText("Total Omset:");
        totalOmsetTextField.setBorder(null);

        totalOmsetValue.setEditable(false);
        totalOmsetValue.setBackground(new java.awt.Color(255, 218, 182));
        totalOmsetValue.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        totalOmsetValue.setForeground(new java.awt.Color(137, 92, 3));
        totalOmsetValue.setBorder(null);
        totalOmsetValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalOmsetValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchKendaraanInputPanelLayout = new javax.swing.GroupLayout(searchKendaraanInputPanel);
        searchKendaraanInputPanel.setLayout(searchKendaraanInputPanelLayout);
        searchKendaraanInputPanelLayout.setHorizontalGroup(
            searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchKendaraanInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchKendaraanInputPanelLayout.createSequentialGroup()
                        .addGroup(searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchKaryawanInputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchKendaraanInputPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(batalkanKaryawanButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(batalkanKaryawanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchKendaraanInputPanelLayout.createSequentialGroup()
                                .addGroup(searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(searchKendaraanInputPanelLayout.createSequentialGroup()
                                        .addComponent(totalTransaksiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalTransaksiValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(searchKendaraanInputPanelLayout.createSequentialGroup()
                                        .addComponent(menuTerlarisTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(menuTerlarisValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
                                .addComponent(minumanScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(searchKaryawanInputLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(searchKendaraanInputPanelLayout.createSequentialGroup()
                                .addComponent(searchKaryawanInputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchKaryawanInputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(searchKendaraanInputPanelLayout.createSequentialGroup()
                        .addComponent(tanggalMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchKaryawanInputLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tanggalSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(searchKaryawanInputButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(167, 167, 167))
                    .addGroup(searchKendaraanInputPanelLayout.createSequentialGroup()
                        .addComponent(totalOmsetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(totalOmsetValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(searchKendaraanInputPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        searchKendaraanInputPanelLayout.setVerticalGroup(
            searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchKendaraanInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchKaryawanInputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchKaryawanInputTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(searchKaryawanInputButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(searchKaryawanInputLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchKaryawanInputLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchKaryawanInputButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tanggalMulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tanggalSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchKendaraanInputPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(minumanScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchKendaraanInputPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(menuTerlarisTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(menuTerlarisValue, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalTransaksiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalTransaksiValue, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalOmsetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalOmsetValue, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)))
                .addGroup(searchKendaraanInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batalkanKaryawanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batalkanKaryawanButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchKendaraanInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(searchKendaraanInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1214, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchKaryawanInputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchKaryawanInputButtonActionPerformed
        showTransaksi(searchKaryawanInputTextField.getText());
    }//GEN-LAST:event_searchKaryawanInputButtonActionPerformed

    private void tabelKaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKaryawanMouseClicked
        int clickedRow = tabelKaryawan.getSelectedRow();
        TableModel tableModel = tabelKaryawan.getModel();
        if (tabelKaryawan.getRowSorter() != null) {
            clickedRow = tabelKaryawan.convertRowIndexToModel(clickedRow);
        }
        // Setter Data dari tabel
        selectedId =  tableModel.getValueAt(clickedRow, 0).toString();
        showPesanan(tableModel.getValueAt(clickedRow, 0).toString());
    }//GEN-LAST:event_tabelKaryawanMouseClicked

    private void batalkanKaryawanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalkanKaryawanButtonActionPerformed
        tabelKaryawan.clearSelection();
        tabelPesanan.clearSelection();
        searchKaryawanInputTextField.setText("");
        showPesanan("TIDAK AKAN KETEMU"); // MENCARI ID YANG TIDAK ADA KARENA BATAL
        selectedId = null;
    }//GEN-LAST:event_batalkanKaryawanButtonActionPerformed

    private void searchKaryawanInputTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKaryawanInputTextFieldKeyTyped
        if (evt.getKeyChar() == '\n') {
            showTransaksi(searchKaryawanInputTextField.getText());
        }
    }//GEN-LAST:event_searchKaryawanInputTextFieldKeyTyped

    private void searchKaryawanInputButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchKaryawanInputButton1ActionPerformed
        
        if(tanggalMulai.getDate() == null){
            JOptionPane.showMessageDialog(rootPane, "Tanggal tidak boleh kosong!!!");
            return;
        }
        String toDate1 = tanggalMulai.getDate().toString();
        SimpleDateFormat inputFormat1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date date1 = null;
        try {
            date1 = inputFormat1.parse(toDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate1 = outputFormat1.format(date1);
        
        if(tanggalSelesai.getDate() == null){
            JOptionPane.showMessageDialog(rootPane, "Tanggal tidak boleh kosong!!!");
            return;
        }
        String toDate2 = tanggalSelesai.getDate().toString();
        SimpleDateFormat inputFormat2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date date2 = null;
        try {
            date2 = inputFormat2.parse(toDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate2 = outputFormat2.format(date2);
        
        if (toDate1.isEmpty() || toDate2.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Tanggal mulai dan akhir harus diisi!");
            return;
        }
        
        tabelKaryawan.setModel(tc.showTableByTanggal(formattedDate1, formattedDate2));
        addHeaderClickListener(tabelKaryawan);
        if (tc.showTableByTanggal(formattedDate1, formattedDate2).getRowCount()==0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada transaksi ditemukan dalam rentang tanggal ini.");
        }
    }//GEN-LAST:event_searchKaryawanInputButton1ActionPerformed

    private void menuTerlarisTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTerlarisTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuTerlarisTextFieldActionPerformed

    private void batalkanKaryawanButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalkanKaryawanButton1ActionPerformed
        if(selectedId == null){
            return;
        }
        tc.createReceipt(selectedId);
    }//GEN-LAST:event_batalkanKaryawanButton1ActionPerformed

    private void totalTransaksiValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalTransaksiValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalTransaksiValueActionPerformed

    private void menuTerlarisValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTerlarisValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuTerlarisValueActionPerformed

    private void totalOmsetValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalOmsetValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalOmsetValueActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batalkanKaryawanButton;
    private javax.swing.JButton batalkanKaryawanButton1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField menuTerlarisTextField;
    private javax.swing.JTextField menuTerlarisValue;
    private javax.swing.JScrollPane minumanScrollPane1;
    private javax.swing.JButton searchKaryawanInputButton;
    private javax.swing.JButton searchKaryawanInputButton1;
    private javax.swing.JLabel searchKaryawanInputLabel;
    private javax.swing.JLabel searchKaryawanInputLabel1;
    private javax.swing.JLabel searchKaryawanInputLabel2;
    private javax.swing.JTextField searchKaryawanInputTextField;
    private javax.swing.JPanel searchKendaraanInputPanel;
    private javax.swing.JTable tabelKaryawan;
    private javax.swing.JTable tabelPesanan;
    private com.toedter.calendar.JDateChooser tanggalMulai;
    private com.toedter.calendar.JDateChooser tanggalSelesai;
    private javax.swing.JTextField totalOmsetTextField;
    private javax.swing.JTextField totalOmsetValue;
    private javax.swing.JTextField totalTransaksiTextField;
    private javax.swing.JTextField totalTransaksiValue;
    // End of variables declaration//GEN-END:variables
}
