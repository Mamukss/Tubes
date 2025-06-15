package panelView;

import Controller.ReservasiControl;
import Controller.CustomerControl;
import DAO.CustomerDAO;

import exception.InputKosongException;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import java.awt.Component;

import Model.Reservasi;
import Model.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;
import Model.Karyawan;

public class ReservasiMainPanel extends javax.swing.JPanel {

    private CustomerControl singleDataControl;
    private ReservasiControl junctionControl;
    
    private Reservasi reservasi = null;
    private Customer pelanggan = null;
    private Karyawan karyawan;
    
    private ArrayList<Reservasi> reservasiList = new ArrayList<>();
    
    String action = null;
    private String selectedId = "";
    
    List<Customer> singleList;
 
    private Component rootPane;
    
    // DROPDOWN 
    // Customer
    public void setCustomerToDropdown(){
        singleList = singleDataControl.showListPelanggan();
        for(int i = 0; i < singleList.size(); i++){
            namaPelangganDropDown.addItem(singleList.get(i));
        }
    }
    // DROPDOWN
    
    // RADIO BUTTON
    public void setRadioButtonValue(){
        radioData1.setActionCommand("Out Door");
        radioData2.setActionCommand("In Door");
    }
    // RADIO BUTTON
    
    // TABLE SHOW
    public void showTableBySearch(String target){
        tabelReservasi.setModel(junctionControl.showTableBySearch(target));
    }
    // TABLE SHOW    

    // EXCEPTION
    public void inputKosongReservasiException() throws InputKosongException{
        if((namaPelangganDropDown.getSelectedIndex() == -1) || (!checkboxData1.isSelected() && !checkboxData2.isSelected() && !checkboxData3.isSelected())
            || radioButtonGroup.getSelection().getActionCommand() == null || totalHargaInputTextField.getText().isEmpty()){
            throw new InputKosongException();
        }
    }
    // EXCEPTION

    // RESET METHOD TO DEFAULT
    public void clearText(){
        // RESET DROPDOWN
        namaPelangganDropDown.setSelectedIndex(-1);
        
        // RESET CHECKBOX
        checkboxData1.setSelected(false);
        checkboxData2.setSelected(false);
        checkboxData3.setSelected(false);
        
        // RESET RADIOBUTTON
        radioButtonGroup.clearSelection();
        
        // RESET TEXT
        searchReservasiInputTextField.setText("");
        totalHargaInputTextField.setText("");
    }
    
    // SET METHOD BUTTON EDIT & DELETE
    public void setEditDeleteBtn(boolean value){
        barukanReservasiButton.setEnabled(value);
        hapusReservasiButton.setEnabled(value);
    }
    
    // METHOD FOR SET COMPONENT, ENABLE / DISABLE
    public void setComponent(boolean value){
        namaPelangganDropDown.setEnabled(value);
        tanggalReservasiInput.setEnabled(value);
        
        checkboxData1.setEnabled(value);
        checkboxData2.setEnabled(value);
        checkboxData3.setEnabled(value);
        
        simpanReservasiButton.setEnabled(value);
        batalkanReservasiButton.setEnabled(value);
    }

    // METHOD FOR SET RADIO BUTTON
    public void setRadioComponent(boolean value){
        radioData1.setEnabled(value);
        radioData2.setEnabled(value);
    }
    
    public void menghitungTotalHarga(){
        // SET ATRIBUT DAN METODE PEMBAYARAN
        // a = 20k , b = 30k, c = 50k
        if(radioData1.isSelected() || radioData2.isSelected()){
            if(checkboxData1.isSelected() && checkboxData2.isSelected() && checkboxData3.isSelected()){
                totalHargaInputTextField.setText("100000");
            }else{
                if(checkboxData1.isSelected() && checkboxData2.isSelected()){
                    totalHargaInputTextField.setText("50000");              
                } else

                if(checkboxData2.isSelected() && checkboxData3.isSelected()){
                    totalHargaInputTextField.setText("80000");
                } else 

                if(checkboxData1.isSelected() && checkboxData3.isSelected()){
                    totalHargaInputTextField.setText("70000");
                } else 
                
                if(checkboxData1.isSelected()){
                    totalHargaInputTextField.setText("20000");              
                } else 
                    
                if(checkboxData2.isSelected()){
                    totalHargaInputTextField.setText("30000");              
                } else 
                    
                if(checkboxData3.isSelected()){
                    totalHargaInputTextField.setText("50000");              
                } else 
                    
                if(!checkboxData1.isSelected() && !checkboxData2.isSelected() && !checkboxData3.isSelected()){
                    totalHargaInputTextField.setText("");
                }
            }
        }else{
            totalHargaInputTextField.setText("");
        }
        
    }
    
    public ReservasiMainPanel(Karyawan k) {
        initComponents();
        this.karyawan = k;
        setOpaque(false);
        
        junctionControl = new ReservasiControl();
        singleDataControl = new CustomerControl(new CustomerDAO());
        
        setCustomerToDropdown();
        setRadioButtonValue();
        
        showTableBySearch("");
        
        // RESET
        clearText();
        setComponent(false);
        setRadioComponent(false);
        setEditDeleteBtn(false);
        totalHargaInputTextField.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioButtonGroup = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelReservasi = new javax.swing.JTable();
        simpanReservasiButton = new javax.swing.JButton();
        batalkanReservasiButton = new javax.swing.JButton();
        searchReservasiInputPanel = new javax.swing.JPanel();
        searchReservasiInputLabel = new javax.swing.JLabel();
        searchReservasiInputTextField = new javax.swing.JTextField();
        searchReservasiInputButton = new javax.swing.JButton();
        reservasiFormPanel = new javax.swing.JPanel();
        reservasiButtonPanel = new javax.swing.JPanel();
        barukanReservasiButton = new javax.swing.JButton();
        hapusReservasiButton = new javax.swing.JButton();
        tambahReservasiButton = new javax.swing.JButton();
        namaPelangganInputPanel = new javax.swing.JPanel();
        namaPelangganInputLabel = new javax.swing.JLabel();
        namaPelangganDropDown = new javax.swing.JComboBox<>();
        tanggalReservasiInput = new com.toedter.calendar.JDateChooser();
        checkboxPanel = new javax.swing.JPanel();
        checkboxLabel = new javax.swing.JLabel();
        checkboxData1 = new javax.swing.JCheckBox();
        checkboxData2 = new javax.swing.JCheckBox();
        checkboxData3 = new javax.swing.JCheckBox();
        radioPanel = new javax.swing.JPanel();
        radioLabel = new javax.swing.JLabel();
        radioData1 = new javax.swing.JRadioButton();
        radioData2 = new javax.swing.JRadioButton();
        totalHargaPanel = new javax.swing.JPanel();
        totalHargaLabel = new javax.swing.JLabel();
        totalHargaInputTextField = new javax.swing.JTextField();
        radioPanel1 = new javax.swing.JPanel();
        radioLabel1 = new javax.swing.JLabel();
        radioData3 = new javax.swing.JRadioButton();
        radioData4 = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 218, 182));

        mainPanel.setBackground(new java.awt.Color(255, 218, 182));

        tabelReservasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Reservasi", "ID Pelanggan", "Nama Pelanggan", "No Telepon Pelanggan", "Alamat Pelanggan", "Jenis", "Paket", "Tanggal", "Total Harga"
            }
        ));
        tabelReservasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelReservasiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelReservasi);

        simpanReservasiButton.setBackground(new java.awt.Color(51, 151, 56));
        simpanReservasiButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        simpanReservasiButton.setForeground(new java.awt.Color(255, 255, 255));
        simpanReservasiButton.setText("Simpan");
        simpanReservasiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanReservasiButtonActionPerformed(evt);
            }
        });

        batalkanReservasiButton.setBackground(new java.awt.Color(237, 78, 5));
        batalkanReservasiButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        batalkanReservasiButton.setForeground(new java.awt.Color(255, 255, 255));
        batalkanReservasiButton.setText("Batalkan");
        batalkanReservasiButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batalkanReservasiButtonMouseClicked(evt);
            }
        });
        batalkanReservasiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalkanReservasiButtonActionPerformed(evt);
            }
        });

        searchReservasiInputPanel.setBackground(new java.awt.Color(255, 218, 182));

        searchReservasiInputLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        searchReservasiInputLabel.setForeground(new java.awt.Color(137, 92, 3));
        searchReservasiInputLabel.setText("Pencarian Reservasi");

        searchReservasiInputTextField.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        searchReservasiInputTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchReservasiInputTextFieldKeyPressed(evt);
            }
        });

        searchReservasiInputButton.setBackground(new java.awt.Color(137, 92, 3));
        searchReservasiInputButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        searchReservasiInputButton.setForeground(new java.awt.Color(255, 255, 255));
        searchReservasiInputButton.setText("Cari");
        searchReservasiInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchReservasiInputButtonActionPerformed(evt);
            }
        });

        reservasiFormPanel.setBackground(new java.awt.Color(255, 218, 182));

        reservasiButtonPanel.setBackground(new java.awt.Color(255, 218, 182));

        barukanReservasiButton.setBackground(new java.awt.Color(255, 175, 47));
        barukanReservasiButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        barukanReservasiButton.setForeground(new java.awt.Color(255, 255, 255));
        barukanReservasiButton.setText("Barukan");
        barukanReservasiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barukanReservasiButtonActionPerformed(evt);
            }
        });

        hapusReservasiButton.setBackground(new java.awt.Color(237, 78, 5));
        hapusReservasiButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        hapusReservasiButton.setForeground(new java.awt.Color(255, 255, 255));
        hapusReservasiButton.setText("Hapus");
        hapusReservasiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusReservasiButtonActionPerformed(evt);
            }
        });

        tambahReservasiButton.setBackground(new java.awt.Color(51, 151, 56));
        tambahReservasiButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        tambahReservasiButton.setForeground(new java.awt.Color(255, 255, 255));
        tambahReservasiButton.setText("Tambah");
        tambahReservasiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahReservasiButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reservasiButtonPanelLayout = new javax.swing.GroupLayout(reservasiButtonPanel);
        reservasiButtonPanel.setLayout(reservasiButtonPanelLayout);
        reservasiButtonPanelLayout.setHorizontalGroup(
            reservasiButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservasiButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tambahReservasiButton)
                .addGap(18, 18, 18)
                .addComponent(barukanReservasiButton)
                .addGap(18, 18, 18)
                .addComponent(hapusReservasiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        reservasiButtonPanelLayout.setVerticalGroup(
            reservasiButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservasiButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reservasiButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barukanReservasiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambahReservasiButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hapusReservasiButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        namaPelangganInputPanel.setBackground(new java.awt.Color(255, 218, 182));

        namaPelangganInputLabel.setBackground(new java.awt.Color(0, 0, 0));
        namaPelangganInputLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        namaPelangganInputLabel.setForeground(new java.awt.Color(137, 92, 3));
        namaPelangganInputLabel.setText("Nama Customer");

        javax.swing.GroupLayout namaPelangganInputPanelLayout = new javax.swing.GroupLayout(namaPelangganInputPanel);
        namaPelangganInputPanel.setLayout(namaPelangganInputPanelLayout);
        namaPelangganInputPanelLayout.setHorizontalGroup(
            namaPelangganInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namaPelangganInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(namaPelangganInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaPelangganInputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(namaPelangganInputPanelLayout.createSequentialGroup()
                        .addComponent(namaPelangganDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(tanggalReservasiInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        namaPelangganInputPanelLayout.setVerticalGroup(
            namaPelangganInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namaPelangganInputPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(namaPelangganInputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(namaPelangganInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(namaPelangganDropDown, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(tanggalReservasiInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        checkboxPanel.setBackground(new java.awt.Color(255, 218, 182));

        checkboxLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        checkboxLabel.setForeground(new java.awt.Color(137, 92, 3));
        checkboxLabel.setText("Jenis Kendaraan");

        checkboxData1.setBackground(new java.awt.Color(255, 218, 182));
        checkboxData1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        checkboxData1.setForeground(new java.awt.Color(137, 92, 3));
        checkboxData1.setText("Mobil");
        checkboxData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxData1ActionPerformed(evt);
            }
        });

        checkboxData2.setBackground(new java.awt.Color(255, 218, 182));
        checkboxData2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        checkboxData2.setForeground(new java.awt.Color(137, 92, 3));
        checkboxData2.setText("Motor");
        checkboxData2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxData2ActionPerformed(evt);
            }
        });

        checkboxData3.setBackground(new java.awt.Color(255, 218, 182));
        checkboxData3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        checkboxData3.setForeground(new java.awt.Color(137, 92, 3));
        checkboxData3.setText("Truck");
        checkboxData3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxData3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout checkboxPanelLayout = new javax.swing.GroupLayout(checkboxPanel);
        checkboxPanel.setLayout(checkboxPanelLayout);
        checkboxPanelLayout.setHorizontalGroup(
            checkboxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkboxPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(checkboxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkboxLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(checkboxPanelLayout.createSequentialGroup()
                        .addGroup(checkboxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkboxData1)
                            .addComponent(checkboxData2)
                            .addComponent(checkboxData3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        checkboxPanelLayout.setVerticalGroup(
            checkboxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkboxPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkboxLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkboxData1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkboxData2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkboxData3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        radioPanel.setBackground(new java.awt.Color(255, 218, 182));

        radioLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        radioLabel.setForeground(new java.awt.Color(137, 92, 3));
        radioLabel.setText("Jenis");

        radioData1.setBackground(new java.awt.Color(255, 218, 182));
        radioButtonGroup.add(radioData1);
        radioData1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        radioData1.setForeground(new java.awt.Color(137, 92, 3));
        radioData1.setText("Test Drive");
        radioData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioData1ActionPerformed(evt);
            }
        });

        radioData2.setBackground(new java.awt.Color(255, 218, 182));
        radioButtonGroup.add(radioData2);
        radioData2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        radioData2.setForeground(new java.awt.Color(137, 92, 3));
        radioData2.setText("Cek Kendaraan");
        radioData2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioData2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout radioPanelLayout = new javax.swing.GroupLayout(radioPanel);
        radioPanel.setLayout(radioPanelLayout);
        radioPanelLayout.setHorizontalGroup(
            radioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(radioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(radioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioLabel)
                    .addComponent(radioData1)
                    .addComponent(radioData2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        radioPanelLayout.setVerticalGroup(
            radioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(radioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioData1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioData2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        totalHargaPanel.setBackground(new java.awt.Color(255, 218, 182));

        totalHargaLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        totalHargaLabel.setForeground(new java.awt.Color(137, 92, 3));
        totalHargaLabel.setText("Total Harga");

        totalHargaInputTextField.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N

        javax.swing.GroupLayout totalHargaPanelLayout = new javax.swing.GroupLayout(totalHargaPanel);
        totalHargaPanel.setLayout(totalHargaPanelLayout);
        totalHargaPanelLayout.setHorizontalGroup(
            totalHargaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalHargaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totalHargaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalHargaLabel)
                    .addComponent(totalHargaInputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        totalHargaPanelLayout.setVerticalGroup(
            totalHargaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalHargaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalHargaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalHargaInputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        radioPanel1.setBackground(new java.awt.Color(255, 218, 182));

        radioLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        radioLabel1.setForeground(new java.awt.Color(137, 92, 3));
        radioLabel1.setText("Jenis");

        radioData3.setBackground(new java.awt.Color(255, 218, 182));
        radioButtonGroup.add(radioData3);
        radioData3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        radioData3.setForeground(new java.awt.Color(137, 92, 3));
        radioData3.setText("Out Door");
        radioData3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioData3ActionPerformed(evt);
            }
        });

        radioData4.setBackground(new java.awt.Color(255, 218, 182));
        radioButtonGroup.add(radioData4);
        radioData4.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        radioData4.setForeground(new java.awt.Color(137, 92, 3));
        radioData4.setText("In Door");
        radioData4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioData4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout radioPanel1Layout = new javax.swing.GroupLayout(radioPanel1);
        radioPanel1.setLayout(radioPanel1Layout);
        radioPanel1Layout.setHorizontalGroup(
            radioPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(radioPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(radioPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioLabel1)
                    .addComponent(radioData3)
                    .addComponent(radioData4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        radioPanel1Layout.setVerticalGroup(
            radioPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(radioPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioData3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioData4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout reservasiFormPanelLayout = new javax.swing.GroupLayout(reservasiFormPanel);
        reservasiFormPanel.setLayout(reservasiFormPanelLayout);
        reservasiFormPanelLayout.setHorizontalGroup(
            reservasiFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservasiFormPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reservasiFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reservasiButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(reservasiFormPanelLayout.createSequentialGroup()
                        .addGroup(reservasiFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namaPelangganInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(reservasiFormPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(checkboxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(radioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(reservasiFormPanelLayout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(radioPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(107, 107, 107)
                        .addComponent(totalHargaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))))
        );
        reservasiFormPanelLayout.setVerticalGroup(
            reservasiFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservasiFormPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reservasiButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(namaPelangganInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(reservasiFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservasiFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(reservasiFormPanelLayout.createSequentialGroup()
                            .addComponent(radioPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(radioPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(checkboxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(totalHargaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout searchReservasiInputPanelLayout = new javax.swing.GroupLayout(searchReservasiInputPanel);
        searchReservasiInputPanel.setLayout(searchReservasiInputPanelLayout);
        searchReservasiInputPanelLayout.setHorizontalGroup(
            searchReservasiInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchReservasiInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchReservasiInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchReservasiInputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(searchReservasiInputPanelLayout.createSequentialGroup()
                        .addComponent(searchReservasiInputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchReservasiInputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(searchReservasiInputPanelLayout.createSequentialGroup()
                .addComponent(reservasiFormPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 149, Short.MAX_VALUE))
        );
        searchReservasiInputPanelLayout.setVerticalGroup(
            searchReservasiInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchReservasiInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchReservasiInputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchReservasiInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchReservasiInputTextField)
                    .addComponent(searchReservasiInputButton, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reservasiFormPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(617, 617, 617))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                        .addComponent(searchReservasiInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(batalkanReservasiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(simpanReservasiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(simpanReservasiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(batalkanReservasiButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchReservasiInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchReservasiInputTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchReservasiInputTextFieldKeyPressed

    }//GEN-LAST:event_searchReservasiInputTextFieldKeyPressed

    private void searchReservasiInputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchReservasiInputButtonActionPerformed
        showTableBySearch(searchReservasiInputTextField.getText());
        
        clearText();
        setComponent(false);
        setEditDeleteBtn(false);
        setRadioComponent(false);
        radioButtonGroup.clearSelection();
        tambahReservasiButton.setEnabled(true);
    }//GEN-LAST:event_searchReservasiInputButtonActionPerformed

    private void barukanReservasiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barukanReservasiButtonActionPerformed
        action = "update";
        setComponent(true);
        namaPelangganDropDown.setEnabled(true);
        setRadioComponent(true);
        setEditDeleteBtn(true);
    }//GEN-LAST:event_barukanReservasiButtonActionPerformed

    private void hapusReservasiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusReservasiButtonActionPerformed
        if (action == null) {
            return;
        }
        int opsi = JOptionPane.showConfirmDialog(rootPane, "Yakin Ingin Hapus ?", "Hapus Data", JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.NO_OPTION || opsi == JOptionPane.CLOSED_OPTION) {
            return;
        }
        action = "delete";
        
        junctionControl.delete(selectedId);
        
        clearText();
        setComponent(false);
        setEditDeleteBtn(false);
        setRadioComponent(false);
        radioButtonGroup.clearSelection();
        tambahReservasiButton.setEnabled(true);
        showTableBySearch("");
    }//GEN-LAST:event_hapusReservasiButtonActionPerformed

    private void tambahReservasiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahReservasiButtonActionPerformed
        action = "add";
        setComponent(true);
        setRadioComponent(true);
    }//GEN-LAST:event_tambahReservasiButtonActionPerformed

    private void simpanReservasiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanReservasiButtonActionPerformed
        try{
            // EXCEPTION
            inputKosongReservasiException();
            
            // Aksi
            int dialog = JOptionPane.showConfirmDialog(rootPane, "yakin ingin melakukan " + "Simpan Reservasi" + "?");
            if (dialog == JOptionPane.CLOSED_OPTION || dialog == JOptionPane.NO_OPTION || dialog == JOptionPane.CANCEL_OPTION) {
                return;
            }
            // SET ATRIBUT DAN METODE PEMBAYARAN
            int counter = 0;
            String checkbox = "";
            String radio = "";
            
            // MENCARI INDEX YANG DITUNJUK OLEH DROPDOWN
            // Customer
            int selectedIndexSingle = namaPelangganDropDown.getSelectedIndex();            
            Customer selectedSingle = singleList.get(selectedIndexSingle);
            
            if(checkboxData1.isSelected() && checkboxData2.isSelected() && checkboxData3.isSelected()){
                checkbox = "Kenikmatan Hakiki";
            }else{
                if(checkboxData1.isSelected()){
                    checkbox += "Private";
                    counter++;                    
                }
                
                if(checkboxData2.isSelected()){
                    if(counter == 0){
                        checkbox += "Romantis";
                    }else{
                        checkbox += ", Romantis";
                    }
                    counter++;
                }
                
                if(checkboxData3.isSelected()){
                    if(counter == 0){
                        checkbox += "Brutal";
                    }else{
                        checkbox += ", Brutal";
                    }
                }
            }
            
            radio = radioButtonGroup.getSelection().getActionCommand();

            String toDate = tanggalReservasiInput.getDate().toString();
            
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");            
            Date date = null;

            try {
                date = inputFormat.parse(toDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = outputFormat.format(date);
            System.out.println(formattedDate);
            
            if(action.equals("add")){
                Reservasi dataNew = new Reservasi(karyawan.getId_karyawan(),selectedSingle.getId_customer(), formattedDate, radio, 
                        checkbox, Float.parseFloat(totalHargaInputTextField.getText()));
                junctionControl.insert(dataNew);
            } else if(action.equals("update")){
                Reservasi dataNew = new Reservasi(selectedId, karyawan.getId_karyawan(), selectedSingle.getId_customer(), formattedDate, radio, 
                        checkbox, Float.parseFloat(totalHargaInputTextField.getText()), selectedSingle);
                junctionControl.update(dataNew);
                selectedId = "";
            }
        }catch(InputKosongException e){
            JOptionPane.showMessageDialog(this, e.message());
        }
                
        clearText();
        setComponent(false);
        setEditDeleteBtn(false);
        setRadioComponent(false);
        radioButtonGroup.clearSelection();
        tambahReservasiButton.setEnabled(true);
        showTableBySearch("");
    }//GEN-LAST:event_simpanReservasiButtonActionPerformed

    private void tabelReservasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelReservasiMouseClicked
        int indexSingle = -1;
        action = "update";
        
        tambahReservasiButton.setEnabled(false);
        batalkanReservasiButton.setEnabled(true);
        simpanReservasiButton.setEnabled(true);        
        setEditDeleteBtn(true);
        
        setComponent(false);
        setRadioComponent(false);
        radioButtonGroup.clearSelection();
        
        int clickedRow = tabelReservasi.getSelectedRow();
        TableModel tableModel = tabelReservasi.getModel();
        
        selectedId = tableModel.getValueAt(clickedRow, 0).toString();

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = inputFormat.parse(tableModel.getValueAt(clickedRow, 7).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 17);
            calendar.set(Calendar.MINUTE, 19);
            calendar.set(Calendar.SECOND, 27);
        }
        
        Date dateWithTime = calendar.getTime();
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        
        tanggalReservasiInput.setDate(dateWithTime);
        totalHargaInputTextField.setText(tableModel.getValueAt(clickedRow, 8).toString().replace("Rp ", ""));
        
        String checkbox = tableModel.getValueAt(clickedRow, 6).toString();                
        String radio = tableModel.getValueAt(clickedRow, 5).toString();

        switch(radio){
            case "Out Door" :
                radioData1.setSelected(true);
                break;
            case "In Door" :
                radioData2.setSelected(true);
                break;
        }
        
        checkboxData1.setSelected(false);
        checkboxData2.setSelected(false);
        checkboxData3.setSelected(false);
        
        switch(checkbox){
            case "Private" :
                checkboxData1.setSelected(true);
                break;
            case "Romantis" :
                checkboxData2.setSelected(true);
                break;
            case "Brutal" :
                checkboxData3.setSelected(true);
                break;
            case "Kenikmatan Hakiki" :
                checkboxData1.setSelected(true);
                checkboxData2.setSelected(true);
                checkboxData3.setSelected(true);
                break;
            case "Private, Romantis" :
                checkboxData1.setSelected(true);
                checkboxData2.setSelected(true);
                break;
            case "Private, Brutal" :
                checkboxData1.setSelected(true);
                checkboxData3.setSelected(true);
                break;
            case "Romantis, Brutal" :
                checkboxData2.setSelected(true);
                checkboxData3.setSelected(true);
                break;
        }
        
        
        
        for(Customer first : singleList){
            if(first.getId_customer().equals(tableModel.getValueAt(clickedRow, 1).toString())){
                indexSingle = singleList.indexOf(first);
            }
        }
        
        namaPelangganDropDown.setSelectedIndex(indexSingle);
        batalkanReservasiButton.setEnabled(true);
    }//GEN-LAST:event_tabelReservasiMouseClicked

    private void batalkanReservasiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalkanReservasiButtonActionPerformed
        clearText();
        setEditDeleteBtn(false);
        setComponent(false);
        setEditDeleteBtn(false);
        setRadioComponent(false);
        showTableBySearch("");
        tambahReservasiButton.setEnabled(true);
    }//GEN-LAST:event_batalkanReservasiButtonActionPerformed

    private void batalkanReservasiButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalkanReservasiButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_batalkanReservasiButtonMouseClicked

    private void checkboxData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxData1ActionPerformed
        menghitungTotalHarga();
    }//GEN-LAST:event_checkboxData1ActionPerformed

    private void checkboxData2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxData2ActionPerformed
        menghitungTotalHarga();
    }//GEN-LAST:event_checkboxData2ActionPerformed

    private void checkboxData3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxData3ActionPerformed
        menghitungTotalHarga();
    }//GEN-LAST:event_checkboxData3ActionPerformed

    private void radioData2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioData2ActionPerformed
        menghitungTotalHarga();
    }//GEN-LAST:event_radioData2ActionPerformed

    private void radioData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioData1ActionPerformed
        menghitungTotalHarga();
    }//GEN-LAST:event_radioData1ActionPerformed

    private void radioData3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioData3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioData3ActionPerformed

    private void radioData4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioData4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioData4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton barukanReservasiButton;
    private javax.swing.JButton batalkanReservasiButton;
    private javax.swing.JCheckBox checkboxData1;
    private javax.swing.JCheckBox checkboxData2;
    private javax.swing.JCheckBox checkboxData3;
    private javax.swing.JLabel checkboxLabel;
    private javax.swing.JPanel checkboxPanel;
    private javax.swing.JButton hapusReservasiButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JComboBox<Customer> namaPelangganDropDown;
    private javax.swing.JLabel namaPelangganInputLabel;
    private javax.swing.JPanel namaPelangganInputPanel;
    private javax.swing.ButtonGroup radioButtonGroup;
    private javax.swing.JRadioButton radioData1;
    private javax.swing.JRadioButton radioData2;
    private javax.swing.JRadioButton radioData3;
    private javax.swing.JRadioButton radioData4;
    private javax.swing.JLabel radioLabel;
    private javax.swing.JLabel radioLabel1;
    private javax.swing.JPanel radioPanel;
    private javax.swing.JPanel radioPanel1;
    private javax.swing.JPanel reservasiButtonPanel;
    private javax.swing.JPanel reservasiFormPanel;
    private javax.swing.JButton searchReservasiInputButton;
    private javax.swing.JLabel searchReservasiInputLabel;
    private javax.swing.JPanel searchReservasiInputPanel;
    private javax.swing.JTextField searchReservasiInputTextField;
    private javax.swing.JButton simpanReservasiButton;
    private javax.swing.JTable tabelReservasi;
    private javax.swing.JButton tambahReservasiButton;
    private com.toedter.calendar.JDateChooser tanggalReservasiInput;
    private javax.swing.JTextField totalHargaInputTextField;
    private javax.swing.JLabel totalHargaLabel;
    private javax.swing.JPanel totalHargaPanel;
    // End of variables declaration//GEN-END:variables
}
