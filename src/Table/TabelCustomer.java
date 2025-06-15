/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import Model.Customer;

public class TabelCustomer extends AbstractTableModel{
    private List<Customer> list;

    public TabelCustomer(List<Customer> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount(){
        return list.size();
    }
    
    @Override
    public int getColumnCount(){
        return 4;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getId_customer();
            case 1:
                return list.get(rowIndex).getNama_customer();
            case 2:
                return list.get(rowIndex).getNomor_telepon();
            case 3:
                return list.get(rowIndex).getAlamat_customer();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID Customer";
            case 1:
                return "Nama Customer";
            case 2:
                return "Nomor Telepon";
            case 3:
                return "Alamat";
            default:
                return null;
        }
    }
    
}
