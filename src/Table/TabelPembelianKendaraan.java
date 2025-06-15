/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

/**
 *
 * @author yohan
 */
import java.util.List;
import javax.swing.table.AbstractTableModel;
import Model.PembelianKendaraan;

public class TabelPembelianKendaraan extends AbstractTableModel {
    private List<PembelianKendaraan> list;

    public TabelPembelianKendaraan(List<PembelianKendaraan> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PembelianKendaraan p = list.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getId_pembelian();
            case 1: return p.getId_customer();
            case 2: return p.getId_kendaraan();
            case 3: return p.getJumlah();
            case 4: return p.getTambahan();
            case 5: return p.getMetodePembayaran();
            case 6: return p.getSubTotal();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "id_pembelian";
            case 1: return "ID Customer";
            case 2: return "ID Kendaraan";
            case 3: return "Jumlah";
            case 4: return "Tambahan";
            case 5: return "Metode Pembayaran";
            case 6: return "Sub Total";
            default: return null;
        }
    }
}