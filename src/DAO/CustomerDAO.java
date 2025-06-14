/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author yohan
 */
package dao;

import Connection.DbConnection;
import Interface_DAO.IDAO;
import Interface_DAO.IGenerateID;
import Interface_DAO.IShowDataList;
import Interface_DAO.ISearchData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Customer;

public class CustomerDAO implements IDAO<Customer,String>, IShowDataList<Customer>, IGenerateID ,ISearchData<Customer, String> {

    protected DbConnection dbCon = new DbConnection();
    protected Connection con;

    @Override
    public void insert(Customer c) {
        con = dbCon.makeConnection();
        String sql = "INSERT INTO customer (id_customer, nama, alamat, no_telepon) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getIdCustomer());
            ps.setString(2, c.getNama());
            ps.setString(3, c.getAlamat());
            ps.setString(4, c.getNoTelepon());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting Customer: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void update(Customer c, String id) {
        con = dbCon.makeConnection();
        String sql = "UPDATE customer SET nama=?, alamat=?, no_telepon=? WHERE id_customer=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNama());
            ps.setString(2, c.getAlamat());
            ps.setString(3, c.getNoTelepon());
            ps.setString(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating Customer: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void delete(String id) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM customer WHERE id_customer=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error deleting Customer: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public List<Customer> showData(String keyword) {
        con = dbCon.makeConnection();
        String sql = "SELECT * FROM customer WHERE id_customer LIKE ? OR nama LIKE ?";
        List<Customer> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer c = new Customer(
                    rs.getString("id_customer"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("no_telepon")
                );
                list.add(c);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error fetching Customer: " + e.getMessage());
        }
        dbCon.closeConnection();
        return list;
    }

    @Override
    public List<Customer> showDataList() {
        return showData("");
    }

    @Override
    public int generateId() {
        con = dbCon.makeConnection();
        String sql = "SELECT MAX(CAST(SUBSTRING(id_customer, 2) AS SIGNED)) AS max_id FROM customer WHERE id_customer LIKE 'C%'";
        int id = 0;

        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs != null && rs.next()) {
                id = rs.getInt("max_id") + 1;
            }
        } catch (Exception e) {
            System.out.println("Error generating Customer ID: " + e.getMessage());
        }
        dbCon.closeConnection();
        return id;
    }

    @Override
    public Customer search(String keyword) {
        con = dbCon.makeConnection();
        String sql = "SELECT * FROM customer WHERE id_customer = ? OR nama LIKE ?";
        Customer c = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, keyword); 
            ps.setString(2, "%" + keyword + "%"); 
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Customer(
                    rs.getString("id_customer"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("no_telepon")
                );
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error searching Customer: " + e.getMessage());
        }
        dbCon.closeConnection();
        return c;
    }
} 

