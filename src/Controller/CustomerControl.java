/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author yohan
 */
import DAO.CustomerDAO;
import java.util.List;
import Model.Customer;

public class CustomerControl {
    private CustomerDAO customerDAO = new CustomerDAO();

    public void insertCustomer(Customer c) {
        customerDAO.insert(c);
    }

    public void updateCustomer(Customer c, String id) {
        customerDAO.update(c, id);
    }

    public void deleteCustomer(String id) {
        customerDAO.delete(id);
    }

    public List<Customer> getCustomerList() {
        return customerDAO.showDataList();
    }

    public List<Customer> searchCustomer(String keyword) {
        return customerDAO.showData(keyword);
    }

    public Customer searchExact(String keyword) {
        return customerDAO.search(keyword);
    }

    public int generateId() {
        return customerDAO.generateId();
    }
} 
