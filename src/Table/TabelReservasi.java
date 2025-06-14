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
import Model.Reservasi;

public class TabelReservasi extends AbstractTableModel {
    private List<Reservasi> list;

    public TabelReservasi(List<Reservasi> list) {
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
        Reservasi r = list.get(rowIndex);
        switch (columnIndex) {
            case 0: return r.getIdReservasi();
            case 1: return r.getIdKaryawan();
            case 2: return r.getIdCustomer();
            case 3: return r.getTanggalReservasi();
            case 4: return r.getJenisReservasi();
            case 5: return r.getPaketReservasi();
            case 6: return r.getTotalHarga();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID Reservasi";
            case 1: return "ID Karyawan";
            case 2: return "ID Customer";
            case 3: return "Tanggal";
            case 4: return "Jenis";
            case 5: return "Paket";
            case 6: return "Total Harga";
            default: return null;
        }
    }
}
