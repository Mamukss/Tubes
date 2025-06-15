/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Model.Truck;
import Model.Kendaraan;

public class TabelTruck extends AbstractTableModel{
    private List<Truck> list;

    public TabelTruck(List<Truck> list) {
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
         Truck truck = (Truck) list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return truck.getId_kendaraan();
            case 1:
                return truck.getNama_kendaraan();
            case 2:
                return truck.getJenis_kendaraan();
            case 3:
                return truck.getSpecial();
            case 4:
                return "Rp " + truck.getHarga();
            case 5:
                return truck.getGambar();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID Menu";
            case 1:
                return "Nama Menu";
            case 2:
                return "Jenis Menu";
            case 3:
                return "jenis_Roda";
            case 4:
                return "Harga";
            default:
                return null;
        }
    }
    
}
    