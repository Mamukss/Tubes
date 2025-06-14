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
import Model.Kendaraan;

public class TabelKendaraan extends AbstractTableModel {
    private List<Kendaraan> list;

    public TabelKendaraan(List<Kendaraan> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kendaraan k = list.get(rowIndex);
        switch (columnIndex) {
            case 0: return k.getIdKendaraan();
            case 1: return k.getNama();
            case 2: return k.getJenis();
            case 3: return k.getHarga();
            // gambar tidak ditampilkan karena berupa byte[]
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "ID Kendaraan";
            case 1: return "Nama";
            case 2: return "Jenis";
            case 3: return "Harga";
            default: return null;
        }
    }
}
