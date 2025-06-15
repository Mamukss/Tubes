/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.CustomerDAO;
import interface_Control.ICRUDControl;
import interface_Control.IShowTableBySearch;
import java.util.List;
import Model.Customer;
import Table.TabelCustomer; 
/**
 *
 * @author yohan
 */
import DAO.CustomerDAO;
import java.util.List;
import Model.Customer;
import interface_Control.ICRUDControl;
import interface_Control.IShowTableBySearch;

public class CustomerControl implements ICRUDControl<Customer, String>, IShowTableBySearch<TabelCustomer, String> {
    private CustomerDAO pDao;

    public CustomerControl(CustomerDAO pDao) {
        this.pDao = pDao;
    }

    @Override
    public void insert(Customer customer) {
        customer.setId_customer(generateId());
        pDao.insert(customer);
    }

    @Override
    public void update(Customer customer)  {
        pDao.update(customer, customer.getId_customer());
    }

    @Override
    public void delete(String id) {
        pDao.delete(id);
    }

    @Override
    public String generateId() {
        return "C-" + pDao.generateId();
    }

    @Override
    public TabelCustomer showTableBySearch(String search) {
        List<Customer> data = pDao.showData(search);
        return new TabelCustomer(data);
    }

    public Customer searchDataPelanggan(String id) {
        return pDao.search(id);
    }
    
    public List<Customer> showListPelanggan(){
        List<Customer> dataCustomer = pDao.showDataList();
        return dataCustomer;
    }

} 
