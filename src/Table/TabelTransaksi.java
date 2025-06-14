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
import Model.Transaksi;

public class TabelTransaksi extends AbstractTableModel {
    private List<Transaksi> list;

    public TabelTransaksi(List<Transaksi> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaksi t = list.get(rowIndex);
        switch (columnIndex) {
            case 0: return t.getNoResi();
            case 1: return t.getIdKaryawan();
            case 2: return t.getIdCustomer();
            case 3: return t.getTanggalPesanan();
            case 4: return t.getTotalHarga();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "No Resi";
            case 1: return "ID Karyawan";
            case 2: return "ID Customer";
            case 3: return "Tanggal Pesanan";
            case 4: return "Total Harga";
            default: return null;
        }
    }
}
