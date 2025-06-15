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
import Model.Karyawan;

public class TabelKaryawan extends AbstractTableModel{
    private List<Karyawan> list;

    public TabelKaryawan(List<Karyawan> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount(){
        return list.size();
    }
    
    @Override
    public int getColumnCount(){
        return 6;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getId_karyawan();
            case 1:
                return list.get(rowIndex).getNama_karyawan();
            case 2:
                return list.get(rowIndex).getJabatan();
            case 3:
                return list.get(rowIndex).getGaji();
            case 4:
                return list.get(rowIndex).getUsername();
            case 5:
                return list.get(rowIndex).getPassword();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID Karyawan";
            case 1:
                return "Nama Karyawan";
            case 2:
                return "Jabatan";
            case 3:
                return "Gaji";
            case 4:
                return "Username";
            case 5:
                return "Password";
            default:
                return null;
        }
    }
}
