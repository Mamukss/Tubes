/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package panelView;

import Controller.MobilControl;
import Controller.MotorControl;
import Controller.TruckControl;
import DAO.MobilDAO;
import DAO.MotorDAO;
import DAO.TruckDAO;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Model.Truck;
import Model.Mobil;
import Model.Motor;
import Model.Kendaraan;
import exception.InputKosongException;
import exception.InputSpecialAtributeException;

public class KendaraanMainPane extends javax.swing.JPanel {
    private MotorControl motorControl;
    private MobilControl mobilControl;
    private TruckControl truckControl;
    private Kendaraan kendaraan = null;
    private Motor motor = null;
    private Mobil mobil = null;
    private Truck truck = null;
    String action = null;
    String actionTambahGambar = null;
    private Component rootPane;
    private String selectedId = "";
    private File selectedFile;
    private String selectedFilePath;
    private byte[] gambarBytes;
    /**
     * Creates new form KendaraanMainPane
     */
    public void inputKosongMenuException() throws InputKosongException {
        if (namaProdukInputTextField.getText().isEmpty() || hargaProdukInputTextfield.getText().isEmpty() || specialAtributeInputTextfield.getText().isEmpty()) {
            throw new InputKosongException();
        }
    }
    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public KendaraanMainPane() {
        initComponents();
        motorControl = new MotorControl(new MotorDAO());
        mobilControl = new MobilControl(new MobilDAO());
        truckControl = new TruckControl(new TruckDAO());
        setComponentsData(false);
        showTableBySearch("");
        setEditDeleteButton(false);
        clearTextData();
    }
    private void setComponentsData(boolean value) {
        idProdukInputTextField.setEnabled(value);
        namaProdukInputTextField.setEnabled(value);
        hargaProdukInputTextfield.setEnabled(value);
        specialAtributeInputTextfield.setEnabled(value);
        tambahGambarButton.setEnabled(value);
        motorCheckbox.setEnabled(value);
        mobilCheckbox.setEnabled(value);
        truckCheckbox.setEnabled(value);
    }
    
    private void setEditDeleteButton(boolean value) {
        barukanProdukButton.setEnabled(value);
        hapusProdukButton.setEnabled(value);
    }
    private void setImageIcon(byte[] gambarBytes) {
        if (gambarBytes != null) {
            try {
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(gambarBytes));
                Image dimg = img.getScaledInstance(gambarLabel.getWidth(), gambarLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(dimg);
                gambarLabel.setIcon(icon);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            gambarLabel.setIcon(null);
        }
    }
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // Jika tidak ada ekstensi
        }
        return name.substring(lastIndexOf + 1).toLowerCase();
    }
    
    private void clearTextData() {
        idProdukInputTextField.setText("");
        namaProdukInputTextField.setText("");
        hargaProdukInputTextfield.setText("");
        searchProdukInputTextField.setText("");
        specialAtributeInputTextfield.setText("");
        specialAtributeInputLabel.setText("");
        setImageIcon(null);
        motorCheckbox.setSelected(false);
        mobilCheckbox.setSelected(false);
        truckCheckbox.setSelected(false);

    }
    
    private void setSpecialAtributeLabel() {
        if (motorCheckbox.isSelected()) {
            specialAtributeInputLabel.setText("Jumlah Tak");
        } else if(mobilCheckbox.isSelected()) {
            specialAtributeInputLabel.setText("Jenis Mesin");
        }else{
            specialAtributeInputLabel.setText("Jenis Roda");
        }
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

    private static byte[] ubahGambar(JLabel labelGambar) throws IOException {
        ImageIcon icon = (ImageIcon) labelGambar.getIcon();
        Image img = icon.getImage();

        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(img, 0, 0, null);
        g2.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        baos.flush();
        byte[] gambarBytes = baos.toByteArray();
        baos.close();

        return gambarBytes;
    }
    
    private void showTableBySearch(String target) {
        if (motorControl != null) {
            tabelMotor.setModel(motorControl.showTableBySearch(target));
        }
        if (mobilControl != null) {
            tabelMobil.setModel(mobilControl.showTableBySearch(target));
        }
        if (truckControl != null) {
            tabelTruck.setModel(truckControl.showTableBySearch(target));
        }
        addHeaderClickListener(tabelMotor);
        addHeaderClickListener(tabelMobil);
        addHeaderClickListener(tabelTruck);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        searchProdukInputPanel = new javax.swing.JPanel();
        searchProdukInputLabel = new javax.swing.JLabel();
        searchProdukInputTextField = new javax.swing.JTextField();
        searchProdukInputButton = new javax.swing.JButton();
        ProdukFormPanel = new javax.swing.JPanel();
        ProdukButtonPanel = new javax.swing.JPanel();
        barukanProdukButton = new javax.swing.JButton();
        hapusProdukButton = new javax.swing.JButton();
        tambahProdukButton = new javax.swing.JButton();
        idProdukInputPanel = new javax.swing.JPanel();
        idProdukInputLabel = new javax.swing.JLabel();
        idProdukInputTextField = new javax.swing.JTextField();
        namaProdukInputPanel = new javax.swing.JPanel();
        namaProdukInputLabel = new javax.swing.JLabel();
        namaProdukInputTextField = new javax.swing.JTextField();
        hargaProdukInputPanel = new javax.swing.JPanel();
        hargaProdukInputLabel = new javax.swing.JLabel();
        hargaProdukInputTextfield = new javax.swing.JTextField();
        jenisKendaraanPanel = new javax.swing.JPanel();
        jenisKendaraanLabel = new javax.swing.JLabel();
        mobilCheckbox = new javax.swing.JCheckBox();
        motorCheckbox = new javax.swing.JCheckBox();
        truckCheckbox = new javax.swing.JCheckBox();
        kendaraanFormPanel2 = new javax.swing.JPanel();
        simpanProdukButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        specialAtributeInputPanel = new javax.swing.JPanel();
        specialAtributeInputLabel = new javax.swing.JLabel();
        specialAtributeInputTextfield = new javax.swing.JTextField();
        gambarPanel = new javax.swing.JPanel();
        gambarLabel = new javax.swing.JLabel();
        tambahGambarButton = new javax.swing.JButton();
        MobilScrollPane = new javax.swing.JScrollPane();
        tabelMobil = new javax.swing.JTable();
        MotrorScrollPane = new javax.swing.JScrollPane();
        tabelMotor = new javax.swing.JTable();
        TruckScrolPanel = new javax.swing.JScrollPane();
        tabelTruck = new javax.swing.JTable();

        mainPanel.setBackground(new java.awt.Color(153, 153, 255));

        searchProdukInputPanel.setBackground(new java.awt.Color(153, 153, 255));

        searchProdukInputLabel.setBackground(new java.awt.Color(255, 221, 186));
        searchProdukInputLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        searchProdukInputLabel.setText("Pencarian Produk");

        searchProdukInputTextField.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        searchProdukInputTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchProdukInputTextFieldKeyPressed(evt);
            }
        });

        searchProdukInputButton.setBackground(new java.awt.Color(137, 92, 3));
        searchProdukInputButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        searchProdukInputButton.setForeground(new java.awt.Color(255, 255, 255));
        searchProdukInputButton.setText("Cari");
        searchProdukInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchProdukInputButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchProdukInputPanelLayout = new javax.swing.GroupLayout(searchProdukInputPanel);
        searchProdukInputPanel.setLayout(searchProdukInputPanelLayout);
        searchProdukInputPanelLayout.setHorizontalGroup(
            searchProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchProdukInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchProdukInputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(searchProdukInputPanelLayout.createSequentialGroup()
                        .addComponent(searchProdukInputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchProdukInputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        searchProdukInputPanelLayout.setVerticalGroup(
            searchProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchProdukInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchProdukInputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchProdukInputTextField)
                    .addComponent(searchProdukInputButton, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        ProdukFormPanel.setBackground(new java.awt.Color(153, 153, 255));

        ProdukButtonPanel.setBackground(new java.awt.Color(153, 153, 255));

        barukanProdukButton.setBackground(new java.awt.Color(255, 175, 47));
        barukanProdukButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        barukanProdukButton.setForeground(new java.awt.Color(255, 255, 255));
        barukanProdukButton.setText("Barukan");
        barukanProdukButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barukanProdukButtonActionPerformed(evt);
            }
        });

        hapusProdukButton.setBackground(new java.awt.Color(237, 78, 5));
        hapusProdukButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        hapusProdukButton.setForeground(new java.awt.Color(255, 255, 255));
        hapusProdukButton.setText("Hapus");
        hapusProdukButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusProdukButtonActionPerformed(evt);
            }
        });

        tambahProdukButton.setBackground(new java.awt.Color(51, 151, 56));
        tambahProdukButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        tambahProdukButton.setForeground(new java.awt.Color(255, 255, 255));
        tambahProdukButton.setText("Tambah");
        tambahProdukButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahProdukButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ProdukButtonPanelLayout = new javax.swing.GroupLayout(ProdukButtonPanel);
        ProdukButtonPanel.setLayout(ProdukButtonPanelLayout);
        ProdukButtonPanelLayout.setHorizontalGroup(
            ProdukButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdukButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tambahProdukButton)
                .addGap(18, 18, 18)
                .addComponent(barukanProdukButton)
                .addGap(18, 18, 18)
                .addComponent(hapusProdukButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        ProdukButtonPanelLayout.setVerticalGroup(
            ProdukButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdukButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProdukButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barukanProdukButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambahProdukButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hapusProdukButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        idProdukInputPanel.setBackground(new java.awt.Color(153, 153, 255));

        idProdukInputLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        idProdukInputLabel.setText("ID Kendraan");

        idProdukInputTextField.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N

        javax.swing.GroupLayout idProdukInputPanelLayout = new javax.swing.GroupLayout(idProdukInputPanel);
        idProdukInputPanel.setLayout(idProdukInputPanelLayout);
        idProdukInputPanelLayout.setHorizontalGroup(
            idProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idProdukInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(idProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idProdukInputTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(idProdukInputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        idProdukInputPanelLayout.setVerticalGroup(
            idProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idProdukInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idProdukInputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idProdukInputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        namaProdukInputPanel.setBackground(new java.awt.Color(153, 153, 255));

        namaProdukInputLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        namaProdukInputLabel.setText("Nama Kendaraan");

        namaProdukInputTextField.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N

        javax.swing.GroupLayout namaProdukInputPanelLayout = new javax.swing.GroupLayout(namaProdukInputPanel);
        namaProdukInputPanel.setLayout(namaProdukInputPanelLayout);
        namaProdukInputPanelLayout.setHorizontalGroup(
            namaProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namaProdukInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(namaProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(namaProdukInputTextField)
                    .addComponent(namaProdukInputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        namaProdukInputPanelLayout.setVerticalGroup(
            namaProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(namaProdukInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(namaProdukInputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namaProdukInputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        hargaProdukInputPanel.setBackground(new java.awt.Color(153, 153, 255));

        hargaProdukInputLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        hargaProdukInputLabel.setText("Harga");

        hargaProdukInputTextfield.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        hargaProdukInputTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hargaProdukInputTextfieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout hargaProdukInputPanelLayout = new javax.swing.GroupLayout(hargaProdukInputPanel);
        hargaProdukInputPanel.setLayout(hargaProdukInputPanelLayout);
        hargaProdukInputPanelLayout.setHorizontalGroup(
            hargaProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hargaProdukInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hargaProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hargaProdukInputTextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(hargaProdukInputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        hargaProdukInputPanelLayout.setVerticalGroup(
            hargaProdukInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hargaProdukInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hargaProdukInputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hargaProdukInputTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jenisKendaraanPanel.setBackground(new java.awt.Color(153, 153, 255));

        jenisKendaraanLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        jenisKendaraanLabel.setText("Jenis Kendaraan");

        mobilCheckbox.setBackground(new java.awt.Color(153, 153, 255));
        mobilCheckbox.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        mobilCheckbox.setText("Mobil");
        mobilCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobilCheckboxActionPerformed(evt);
            }
        });

        motorCheckbox.setBackground(new java.awt.Color(153, 153, 255));
        motorCheckbox.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        motorCheckbox.setText("Motor");

        truckCheckbox.setBackground(new java.awt.Color(153, 153, 255));
        truckCheckbox.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        truckCheckbox.setText("Truck");

        javax.swing.GroupLayout jenisKendaraanPanelLayout = new javax.swing.GroupLayout(jenisKendaraanPanel);
        jenisKendaraanPanel.setLayout(jenisKendaraanPanelLayout);
        jenisKendaraanPanelLayout.setHorizontalGroup(
            jenisKendaraanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jenisKendaraanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jenisKendaraanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jenisKendaraanLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addGroup(jenisKendaraanPanelLayout.createSequentialGroup()
                        .addGroup(jenisKendaraanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(truckCheckbox)
                            .addComponent(motorCheckbox)
                            .addComponent(mobilCheckbox))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jenisKendaraanPanelLayout.setVerticalGroup(
            jenisKendaraanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jenisKendaraanPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jenisKendaraanLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mobilCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(motorCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(truckCheckbox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ProdukFormPanelLayout = new javax.swing.GroupLayout(ProdukFormPanel);
        ProdukFormPanel.setLayout(ProdukFormPanelLayout);
        ProdukFormPanelLayout.setHorizontalGroup(
            ProdukFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdukFormPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProdukFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProdukFormPanelLayout.createSequentialGroup()
                        .addComponent(ProdukButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 302, Short.MAX_VALUE))
                    .addGroup(ProdukFormPanelLayout.createSequentialGroup()
                        .addGroup(ProdukFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(namaProdukInputPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idProdukInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(ProdukFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProdukFormPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(hargaProdukInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(ProdukFormPanelLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jenisKendaraanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        ProdukFormPanelLayout.setVerticalGroup(
            ProdukFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdukFormPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ProdukButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ProdukFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(idProdukInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hargaProdukInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ProdukFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaProdukInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jenisKendaraanPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kendaraanFormPanel2.setBackground(new java.awt.Color(153, 153, 255));

        simpanProdukButton.setBackground(new java.awt.Color(51, 151, 56));
        simpanProdukButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        simpanProdukButton.setForeground(new java.awt.Color(255, 255, 255));
        simpanProdukButton.setText("Simpan");
        simpanProdukButton.setPreferredSize(new java.awt.Dimension(81, 21));
        simpanProdukButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanProdukButtonActionPerformed(evt);
            }
        });

        cancelButton.setBackground(new java.awt.Color(237, 78, 5));
        cancelButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setText("Batalkan");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        specialAtributeInputPanel.setBackground(new java.awt.Color(153, 153, 255));

        specialAtributeInputLabel.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        specialAtributeInputLabel.setText("Jenis Mesin");

        specialAtributeInputTextfield.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N

        javax.swing.GroupLayout specialAtributeInputPanelLayout = new javax.swing.GroupLayout(specialAtributeInputPanel);
        specialAtributeInputPanel.setLayout(specialAtributeInputPanelLayout);
        specialAtributeInputPanelLayout.setHorizontalGroup(
            specialAtributeInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(specialAtributeInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(specialAtributeInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(specialAtributeInputTextfield, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(specialAtributeInputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        specialAtributeInputPanelLayout.setVerticalGroup(
            specialAtributeInputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(specialAtributeInputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(specialAtributeInputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(specialAtributeInputTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        gambarPanel.setBackground(new java.awt.Color(254, 254, 254));
        gambarPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));

        gambarLabel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout gambarPanelLayout = new javax.swing.GroupLayout(gambarPanel);
        gambarPanel.setLayout(gambarPanelLayout);
        gambarPanelLayout.setHorizontalGroup(
            gambarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gambarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gambarLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );
        gambarPanelLayout.setVerticalGroup(
            gambarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gambarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gambarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tambahGambarButton.setText("Tambah Gambar");
        tambahGambarButton.setMinimumSize(new java.awt.Dimension(125, 21));
        tambahGambarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahGambarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kendaraanFormPanel2Layout = new javax.swing.GroupLayout(kendaraanFormPanel2);
        kendaraanFormPanel2.setLayout(kendaraanFormPanel2Layout);
        kendaraanFormPanel2Layout.setHorizontalGroup(
            kendaraanFormPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kendaraanFormPanel2Layout.createSequentialGroup()
                .addComponent(specialAtributeInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(kendaraanFormPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kendaraanFormPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gambarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambahGambarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(simpanProdukButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kendaraanFormPanel2Layout.setVerticalGroup(
            kendaraanFormPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kendaraanFormPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(specialAtributeInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gambarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(kendaraanFormPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpanProdukButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambahGambarButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabelMobil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Menu", "Nama Menu", "Jenis Menu", "Catatan", "Harga"
            }
        ));
        tabelMobil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMobilMouseClicked(evt);
            }
        });
        MobilScrollPane.setViewportView(tabelMobil);

        tabelMotor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Menu", "Nama Menu", "Jenis Menu", "Ukuran", "Harga"
            }
        ));
        tabelMotor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMotorMouseClicked(evt);
            }
        });
        MotrorScrollPane.setViewportView(tabelMotor);

        tabelTruck.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelTruck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelTruckMouseClicked(evt);
            }
        });
        TruckScrolPanel.setViewportView(tabelTruck);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchProdukInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(ProdukFormPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209)
                        .addComponent(kendaraanFormPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(MobilScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(MotrorScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TruckScrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(searchProdukInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ProdukFormPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kendaraanFormPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MotrorScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(MobilScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(TruckScrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchProdukInputTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchProdukInputTextFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == '\n') {
            showTableBySearch(searchProdukInputTextField.getText());
        }
    }//GEN-LAST:event_searchProdukInputTextFieldKeyPressed

    private void searchProdukInputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchProdukInputButtonActionPerformed
        // TODO add your handling code here:
        showTableBySearch(searchProdukInputTextField.getText());
    }//GEN-LAST:event_searchProdukInputButtonActionPerformed

    private void barukanProdukButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barukanProdukButtonActionPerformed
        // TODO add your handling code here:
        action = "update";
        setComponentsData(true);
        idProdukInputTextField.setEnabled(false);
    }//GEN-LAST:event_barukanProdukButtonActionPerformed

    private void hapusProdukButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusProdukButtonActionPerformed
        // TODO add your handling code here:
        int opsi = JOptionPane.showConfirmDialog(rootPane, "Yakin Ingin Hapus ?", "Hapus Data", JOptionPane.YES_NO_OPTION);
        if (opsi == JOptionPane.NO_OPTION || opsi == JOptionPane.CLOSED_OPTION) {
            return;
        }
        if(motorCheckbox.isSelected()){
            motorControl.delete(idProdukInputTextField.getText());
        }else if(mobilCheckbox.isSelected()){
            mobilControl.delete(idProdukInputTextField.getText());
        }else{
            truckControl.delete(idProdukInputTextField.getText());
        }
        clearTextData();
        setEditDeleteButton(false);
        setComponentsData(false);
        tambahProdukButton.setEnabled(true);
        showTableBySearch("");
    }//GEN-LAST:event_hapusProdukButtonActionPerformed

    private void tambahProdukButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahProdukButtonActionPerformed
        // TODO add your handling code here:
        action = "add";
        clearTextData();
        setEditDeleteButton(false);
        setComponentsData(true);
        setEditDeleteButton(false);

        idProdukInputTextField.setEnabled(false);
        if(motorCheckbox.isSelected()){
            idProdukInputTextField.setText(motorControl.generateId());
        }else if(mobilCheckbox.isSelected()){
            idProdukInputTextField.setText(mobilControl.generateId());
        }else{
            idProdukInputTextField.setText(truckControl.generateId());
        }
        
        setSpecialAtributeLabel();
    }//GEN-LAST:event_tambahProdukButtonActionPerformed

    private void hargaProdukInputTextfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaProdukInputTextfieldKeyTyped
        // TODO add your handling code here:
        char key = evt.getKeyChar();
        if (!Character.isDigit(key) && key != KeyEvent.VK_BACK_SPACE || key == '.') {
            evt.consume();
            JOptionPane.showMessageDialog(
                null, "Hanya bisa masukan angka !!", "Input Failure", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_hargaProdukInputTextfieldKeyTyped

    private void simpanProdukButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanProdukButtonActionPerformed
        // TODO add your handling code here:
        if (action == null) {
            return;
        }
        try {
            inputKosongMenuException();
//            InputUkuranSpecialException();

            int dialog = JOptionPane.showConfirmDialog(rootPane, "yakin ingin melakukan " + action + "?");
            if (dialog == JOptionPane.CLOSED_OPTION || dialog == JOptionPane.NO_OPTION || dialog == JOptionPane.CANCEL_OPTION) {
                return;
            }

            switch (action) {
                case "add":
                if (mobilCheckbox.isSelected()) {
                    mobil = new Mobil(specialAtributeInputTextfield.getText(), namaProdukInputTextField.getText(),
                        mobilCheckbox.getText(), Float.parseFloat(hargaProdukInputTextfield.getText()), gambarBytes);
                    mobilControl.insert(mobil);
                } else if(motorCheckbox.isSelected()){
                    motor = new Motor(specialAtributeInputTextfield.getText(), namaProdukInputTextField.getText(),
                        motorCheckbox.getText(), Float.parseFloat(hargaProdukInputTextfield.getText()), gambarBytes);
                    motorControl.insert(motor);
                }else{
                    truck = new Truck(specialAtributeInputTextfield.getText(), namaProdukInputTextField.getText(),
                        mobilCheckbox.getText(), Float.parseFloat(hargaProdukInputTextfield.getText()), gambarBytes);
                    truckControl.insert(truck);
                }
                break;

                case "update":
                if (mobilCheckbox.isSelected()) {
                    try {
                        mobil = new Mobil(specialAtributeInputTextfield.getText(), idProdukInputTextField.getText(), namaProdukInputTextField.getText(),
                            mobilCheckbox.getText(), Float.parseFloat(hargaProdukInputTextfield.getText()), ubahGambar(gambarLabel));
                    } catch (IOException ex) {
                        Logger.getLogger(KendaraanMainPane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    mobilControl.update(mobil);
                } else if(motorCheckbox.isSelected()){
                    try {
                        motor = new Motor(specialAtributeInputTextfield.getText(), idProdukInputTextField.getText(), namaProdukInputTextField.getText(),
                            motorCheckbox.getText(), Float.parseFloat(hargaProdukInputTextfield.getText()), ubahGambar(gambarLabel));
                    } catch (IOException ex) {
                        Logger.getLogger(KendaraanMainPane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    motorControl.update(motor);
                }else{
                    try {
                        truck = new Truck(specialAtributeInputTextfield.getText(), idProdukInputTextField.getText(), namaProdukInputTextField.getText(),
                            truckCheckbox.getText(), Float.parseFloat(hargaProdukInputTextfield.getText()), ubahGambar(gambarLabel));
                    } catch (IOException ex) {
                        Logger.getLogger(KendaraanMainPane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    truckControl.update(truck);
                }
                break;

                default:
                break;
            }
        } catch (InputKosongException e) {
            JOptionPane.showMessageDialog(rootPane, e.message());
        }
//        } catch (InputSpecialAtributeException e2) { // MENGECEK UKURAN MINUMAN YANG HARUS ANGKA
//            JOptionPane.showMessageDialog(rootPane, e2.message("Ukuran Hanya :  S / M / L"));
//        }
        clearTextData();
        setEditDeleteButton(false);
        setComponentsData(false);
        showTableBySearch("");
        tambahProdukButton.setEnabled(true);
        actionTambahGambar = null;
        action = null;
    }//GEN-LAST:event_simpanProdukButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        clearTextData();
        setEditDeleteButton(false);
        setComponentsData(false);
        setEditDeleteButton(false);
        tambahProdukButton.setEnabled(true);
        tabelMobil.clearSelection();
        tabelMotor.clearSelection();
        gambarLabel.setIcon(null);
        showTableBySearch("");
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void tambahGambarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahGambarButtonActionPerformed
        if (action == null) {
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Gambar", "jpg", "png", "jpeg"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(selectedFile);
                Image dimg = img.getScaledInstance(gambarLabel.getWidth(), gambarLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(dimg);
                gambarLabel.setIcon(icon);

                // Tentukan format berdasarkan ekstensi file
                String extension = getFileExtension(selectedFile);
                if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
                    throw new IOException("Unsupported file format: " + extension);
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, extension, baos);
                baos.flush();
                gambarBytes = baos.toByteArray();
                baos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_tambahGambarButtonActionPerformed

    private void tabelMobilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMobilMouseClicked
        tabelMotor.clearSelection();
        action = "update";
        tambahProdukButton.setEnabled(false);
        cancelButton.setEnabled(true);
        simpanProdukButton.setEnabled(true);
        setEditDeleteButton(true);

        setComponentsData(false);

        int clickedRow = tabelMobil.getSelectedRow();

        if (tabelMobil.getRowSorter() != null) {
            clickedRow = tabelMobil.convertRowIndexToModel(clickedRow);
        }

        TableModel tableModel = tabelMobil.getModel();

        selectedId = tableModel.getValueAt(clickedRow, 0).toString();

        idProdukInputTextField.setText(tableModel.getValueAt(clickedRow, 0).toString());
        namaProdukInputTextField.setText(tableModel.getValueAt(clickedRow, 1).toString());
        mobilCheckbox.setSelected(true);
        specialAtributeInputTextfield.setText(tableModel.getValueAt(clickedRow, 3).toString());
        hargaProdukInputTextfield.setText(tableModel.getValueAt(clickedRow, 4).toString().replace("Rp ", ""));
        setSpecialAtributeLabel();
        setImageIcon((byte[]) tableModel.getValueAt(clickedRow, 5));
        cancelButton.setEnabled(true);
    }//GEN-LAST:event_tabelMobilMouseClicked

    private void tabelMotorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMotorMouseClicked
        tabelMobil.clearSelection();

        action = "update";

        tambahProdukButton.setEnabled(false);
        cancelButton.setEnabled(true);
        simpanProdukButton.setEnabled(true);
        setEditDeleteButton(true);

        setComponentsData(false);

        int clickedRow = tabelMotor.getSelectedRow();
        TableModel tableModel = tabelMotor.getModel();

        if (tabelMotor.getRowSorter() != null) {
            clickedRow = tabelMotor.convertRowIndexToModel(clickedRow);
        }

        selectedId = tableModel.getValueAt(clickedRow, 0).toString();

        idProdukInputTextField.setText(tableModel.getValueAt(clickedRow, 0).toString());
        namaProdukInputTextField.setText(tableModel.getValueAt(clickedRow, 1).toString());
        motorCheckbox.setSelected(true);
        specialAtributeInputTextfield.setText(tableModel.getValueAt(clickedRow, 3).toString());
        hargaProdukInputTextfield.setText(tableModel.getValueAt(clickedRow, 4).toString().replace("Rp ", ""));
        setSpecialAtributeLabel();
        setImageIcon((byte[]) tableModel.getValueAt(clickedRow, 5));
        cancelButton.setEnabled(true);
    }//GEN-LAST:event_tabelMotorMouseClicked

    private void mobilCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobilCheckboxActionPerformed

    }//GEN-LAST:event_mobilCheckboxActionPerformed

    private void tabelTruckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTruckMouseClicked
        // TODO add your handling code here:
        tabelTruck.clearSelection();

        action = "update";

        tambahProdukButton.setEnabled(false);
        cancelButton.setEnabled(true);
        simpanProdukButton.setEnabled(true);
        setEditDeleteButton(true);

        setComponentsData(false);

        int clickedRow = tabelTruck.getSelectedRow();
        TableModel tableModel = tabelTruck.getModel();

        if (tabelTruck.getRowSorter() != null) {
            clickedRow = tabelTruck.convertRowIndexToModel(clickedRow);
        }

        selectedId = tableModel.getValueAt(clickedRow, 0).toString();

        idProdukInputTextField.setText(tableModel.getValueAt(clickedRow, 0).toString());
        namaProdukInputTextField.setText(tableModel.getValueAt(clickedRow, 1).toString());
        truckCheckbox.setSelected(true);
        specialAtributeInputTextfield.setText(tableModel.getValueAt(clickedRow, 3).toString());
        hargaProdukInputTextfield.setText(tableModel.getValueAt(clickedRow, 4).toString().replace("Rp ", ""));
        setSpecialAtributeLabel();
        setImageIcon((byte[]) tableModel.getValueAt(clickedRow, 5));
        cancelButton.setEnabled(true);
    }//GEN-LAST:event_tabelTruckMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane MobilScrollPane;
    private javax.swing.JScrollPane MotrorScrollPane;
    private javax.swing.JPanel ProdukButtonPanel;
    private javax.swing.JPanel ProdukFormPanel;
    private javax.swing.JScrollPane TruckScrolPanel;
    private javax.swing.JButton barukanProdukButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel gambarLabel;
    private javax.swing.JPanel gambarPanel;
    private javax.swing.JButton hapusProdukButton;
    private javax.swing.JLabel hargaProdukInputLabel;
    private javax.swing.JPanel hargaProdukInputPanel;
    private javax.swing.JTextField hargaProdukInputTextfield;
    private javax.swing.JLabel idProdukInputLabel;
    private javax.swing.JPanel idProdukInputPanel;
    private javax.swing.JTextField idProdukInputTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jenisKendaraanLabel;
    private javax.swing.JPanel jenisKendaraanPanel;
    private javax.swing.JPanel kendaraanFormPanel2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JCheckBox mobilCheckbox;
    private javax.swing.JCheckBox motorCheckbox;
    private javax.swing.JLabel namaProdukInputLabel;
    private javax.swing.JPanel namaProdukInputPanel;
    private javax.swing.JTextField namaProdukInputTextField;
    private javax.swing.JButton searchProdukInputButton;
    private javax.swing.JLabel searchProdukInputLabel;
    private javax.swing.JPanel searchProdukInputPanel;
    private javax.swing.JTextField searchProdukInputTextField;
    private javax.swing.JButton simpanProdukButton;
    private javax.swing.JLabel specialAtributeInputLabel;
    private javax.swing.JPanel specialAtributeInputPanel;
    private javax.swing.JTextField specialAtributeInputTextfield;
    private javax.swing.JTable tabelMobil;
    private javax.swing.JTable tabelMotor;
    private javax.swing.JTable tabelTruck;
    private javax.swing.JButton tambahGambarButton;
    private javax.swing.JButton tambahProdukButton;
    private javax.swing.JCheckBox truckCheckbox;
    // End of variables declaration//GEN-END:variables
}
