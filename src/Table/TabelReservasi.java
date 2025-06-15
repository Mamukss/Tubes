/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Model.Reservasi;

public class TabelReservasi extends AbstractTableModel{
    private List<Reservasi> list;

    public TabelReservasi(List<Reservasi> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount(){
        return list.size();
    }
    
    @Override
    public int getColumnCount(){
        return 9;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getId_reservasi();
            case 1:
                return list.get(rowIndex).getId_customer();
            case 2:
                return list.get(rowIndex).getCustomer().getNama_customer();
            case 3:
                return list.get(rowIndex).getCustomer().getNomor_telepon();
            case 4:
                return list.get(rowIndex).getCustomer().getAlamat_customer();
            case 5:
                return list.get(rowIndex).getJenis_reservasi();
            case 6:
                return list.get(rowIndex).getPaket_reservasi();
            case 7:
                return list.get(rowIndex).getTanggal_reservasi();
            case 8:
                return "Rp " + list.get(rowIndex).getTotal_harga();
            case 9:
                return list.get(rowIndex).getCustomer();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID Reservasi";
            case 1:
                return "ID Customer";
            case 2:
                return "Nama Customer";
            case 3:
                return "No Telepon Customer";
            case 4:
                return "Alamat Customer";
            case 5:
                return "Jenis";
            case 6:
                return "Paket";
            case 7:
                return "Tanggal";
            case 8:
                return "Total Harga";
            default:
                return null;
        }
    }
}
