/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Model.Mobil;
import Model.Kendaraan;

public class TabelMobil extends AbstractTableModel{
    private List<Mobil> list;

    public TabelMobil(List<Mobil> list) {
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
         Mobil mobil = (Mobil) list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return mobil.getId_kendaraan();
            case 1:
                return mobil.getNama_kendaraan();
            case 2:
                return mobil.getJenis_kendaraan();
            case 3:
                return mobil.getSpecial();
            case 4:
                return "Rp " + mobil.getHarga();
            case 5:
                return mobil.getGambar();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID Kendaraan";
            case 1:
                return "Nama Kendaraan";
            case 2:
                return "Jenis ";
            case 3:
                return "Jenis_mesin";
            case 4:
                return "Harga";
            default:
                return null;
        }
    }
    
    
}