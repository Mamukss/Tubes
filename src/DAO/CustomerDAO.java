/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author yohan
 */

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
        public void insert(Customer C){
        con = dbCon.makeConnection();
        
        String sql = 
        "INSERT INTO `customer` (`id_customer`, `nama`, `alamat`, `nomor_telepon`) " +
        "VALUES ('"+C.getId_customer()+"', '"+ C.getNama_customer() +"', '"+ C.getAlamat_customer() +"', '"+ C.getNomor_telepon() +"')";
    
        System.out.println("Adding customer...");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Added " + result + " customer");
            statement.close();
        }catch (Exception e){
            System.out.println("Error adding customer...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    @Override
    public Customer search(String id_customer){
        con = dbCon.makeConnection();
        
        String sql = "SELECT * FROM customer WHERE id_customer ='"+id_customer+"'";
        System.out.println("Searching customer...");
        Customer c = null;
        
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if(rs != null)
                while(rs.next())
                    c = new Customer(
                        rs.getString("id_customer"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("nomor_telepon"));
            
            rs.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return c;
    }
    
    @Override
    public List<Customer> showData(String data){
        con = dbCon.makeConnection();
        
        String sql = "SELECT * FROM customer WHERE "
                + "`id_customer` LIKE '%"+ data +"%' OR "
                + "`nama` LIKE '%"+ data +"%' OR "
                + "`alamat` LIKE '%"+ data +"%'";
        System.out.println("Fetching Data...");
        List<Customer> c = new ArrayList();
        
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if(rs != null)
                while(rs.next())
                    c.add(new Customer(
                        rs.getString("id_customer"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("nomor_telepon")));
            // ingat, jika tidak perlu pakai {} maka hilangkan saja (ytta)
            // selama bisa dibaca
            
            rs.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return c;
    }
    
    @Override
    public void update(Customer c, String id_customer){
        con = dbCon.makeConnection();
        
        String sql = "UPDATE `customer` SET "
                + "`id_customer`='" + c.getId_customer()+ "',"
                + "`nama`='" + c.getNama_customer()+ "',"
                + "`alamat`='" + c.getAlamat_customer()+ "',"
                + "`nomor_telepon`='" + c.getNomor_telepon()+ "' "
                + "WHERE `id_customer`='" + id_customer + "'";
        System.out.println("Updating customer");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited" + result + " customer" + id_customer);
            statement.close();
        }catch(Exception e){
            System.out.println("Error Updating customer...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    @Override
    public void delete(String id_customer){
        con = dbCon.makeConnection();
        String sql = "DELETE FROM `customer` WHERE `id_customer` = '" + id_customer + "' ";
        System.out.println("Deleting customer...");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited" + result + " customer " + id_customer);
            statement.close();
        }catch(Exception e){
            System.out.println("Error Updating customer...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    

    public List<Customer> showDataList(){
        con = dbCon.makeConnection();
        
        String sql = "SELECT * FROM customer";
        System.out.println("Fetching Data...");
        List<Customer> c = new ArrayList();
        
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if(rs != null)
                while(rs.next())
                    c.add(new Customer(
                        rs.getString("id_customer"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("nomor_telepon")));
            
            rs.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return c;
    }
    
    @Override
    public int generateId(){
        con = dbCon.makeConnection();
        String sql = "SELECT MAX(CAST(SUBSTRING(id_customer, 3) AS SIGNED)) AS highest_number FROM customer WHERE id_customer LIKE 'C-%';";
        //mendapatkan nilai tertinggi dari id yang ada di database
        
        System.out.println("Generating Id...");
        int id=0;
        
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if(rs != null && rs.next()){
                if(!rs.wasNull())
                    id = rs.getInt("highest_number")+1;
            }
                    
            //memasukan id terakhir ke dalam variabel id
                
            rs.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return id;
    }
} 

