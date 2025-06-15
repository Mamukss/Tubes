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

public class TabelTransaksi extends AbstractTableModel{
    private List<Transaksi> list;

    public TabelTransaksi(List<Transaksi> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount(){
        return list.size();
    }
    
    @Override
    public int getColumnCount(){
        return 5;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getId_pembelian();
            case 1:
                return list.get(rowIndex).getKaryawan().getNama_karyawan();
            case 2:
                return list.get(rowIndex).getCustomer().getNama_customer();
            case 3:
                return list.get(rowIndex).getTanggal_pesanan();
            case 4:
                return "Rp " + list.get(rowIndex).getTotal_harga();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID Transaksi";
            case 1:
                return "Nama Kasir";
            case 2:
                return "Nama Customer";
            case 3:
                return "Tanggal Transaksi";
            case 4:
                return "Total Transaksi";
            default:
                return null;
        }
    }
}
