/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Model.Motor;
import Model.Kendaraan;

public class TabelMotor extends AbstractTableModel{
    private List<Motor> list;

    public TabelMotor(List<Motor> list) {
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
         Motor motor = (Motor) list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return motor.getId_kendaraan();
            case 1:
                return motor.getNama_kendaraan();
            case 2:
                return motor.getJenis_kendaraan();
            case 3:
                return motor.getSpecial();
            case 4:
                return "Rp " + motor.getHarga();
            case 5:
                return motor.getGambar();
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
                return "Jenis Kendaraan";
            case 3:
                return "jumlah_tak";
            case 4:
                return "Harga";
            default:
                return null;
        }
    }
    
    
}