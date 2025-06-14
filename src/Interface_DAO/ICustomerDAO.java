/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface_DAO;

/**
 *
 * @author yohan
 */
import Model.Customer;
import java.util.List;

public interface ICustomerDAO {
    void insert(Customer c);
    void update(Customer c, String id);
    void delete(String id);
    List<Customer> showData(String keyword);
}

