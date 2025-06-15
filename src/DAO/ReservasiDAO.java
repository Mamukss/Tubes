/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.DbConnection;
import Interface_DAO.IDAO;
import Interface_DAO.IGenerateID;
import Interface_DAO.ISearchData;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.*;


public class ReservasiDAO implements IDAO<Reservasi, String>, IGenerateID{
    protected DbConnection dbCon = new DbConnection();
    protected Connection con;
    
    @Override
    public void insert(Reservasi R){
        con = dbCon.makeConnection();
        
    String sql = 
        "INSERT INTO `reservasi`(`id_reservasi`, `id_karyawan`, `id_customer`, `tanggal_reservasi`, `jenis_reservasi`, `paket_reservasi`, `total_harga`) " +
        "VALUES ('" + R.getId_reservasi() + "', '" + R.getId_karyawan() + "', '" + R.getId_customer() + "', '" + R.getTanggal_reservasi() + "', '" + R.getJenis_reservasi()+"', '" + R.getPaket_reservasi() + "', '" + R.getTotal_harga() + "')";
    
        System.out.println("Adding Reservasi...");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Added " + result + " Reservasi");
            statement.close();
        }catch (Exception e){
            System.out.println("Error adding Reservasi...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    
    @Override
    public List<Reservasi> showData(String query){
        con = dbCon.makeConnection();
        
        String sql = "SELECT * "
                + "FROM reservasi R "
                + "JOIN customer C ON (R.id_customer= P.id_customer) "
                + "WHERE (P.nama_customer LIKE '%" + query + "%' "
                + "OR P.nomor_telepon LIKE '%" + query + "%' "
                + "OR P.alamat LIKE '%" + query + "%' "
                + "OR R.tanggal_reservasi LIKE '%" + query + "%' "
                + "OR R.jenis_reservasi LIKE '%" + query + "%' "
                + "OR R.paket_reservasi LIKE '%" + query + "%')";
        System.out.println("Mengambil data Reservasi...");
        List<Reservasi> listReservasi = new ArrayList<>();
        
        Customer targetSingleData = null;
        
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if(rs != null){
                while(rs.next()){
                    targetSingleData = new Customer(
                        rs.getString("p.id_customer"),
                        rs.getString("p.nama_customer"),
                        rs.getString("p.alamat"),
                        rs.getString("p.nomor_telepon")
                    );
                    
                    Reservasi r = new Reservasi(
                        rs.getString("r.id_reservasi"),
                        rs.getString("r.id_customer"),
                        rs.getString("r.tanggal_reservasi"),
                        rs.getString("r.jenis_reservasi"),
                        rs.getString("r.paket_reservasi"),
                        rs.getFloat("r.total_harga"),
                        targetSingleData
                    );
                    listReservasi.add(r);
                }
            }
            
            rs.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        
        System.out.println("Berhasil");
        dbCon.closeConnection();
        return listReservasi;
    }
    
    @Override
    public void update(Reservasi r, String id_reservasi){
        con = dbCon.makeConnection();
        
        String sql = "UPDATE `reservasi` SET "
                + "`id_reservasi`='" + r.getId_reservasi() + "',"
                + "`id_customer`='" + r.getId_customer()+ "',"
                + "`tanggal_reservasi`='" + r.getTanggal_reservasi()+ "',"
                + "`jenis_reservasi`='" + r.getJenis_reservasi()+ "',"
                + "`paket_reservasi`='" + r.getPaket_reservasi()+ "',"
                + "`total_harga`= " + r.getTotal_harga()+ " "
                + "WHERE `id_reservasi`='" + id_reservasi + "'";
        System.out.println("Updating Reservasi");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited" + result + " Reservasi " + id_reservasi);
            statement.close();
        }catch(Exception e){
            System.out.println("Error Updating Reservasi...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    @Override
    public void delete(String id_reservasi){
        con = dbCon.makeConnection();
        String sql = "DELETE FROM `reservasi` WHERE `id_reservasi` = '" + id_reservasi + "';";
        System.out.println("Deleting Reservasi...");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Deleted" + result + " Reservasi " + id_reservasi);
            statement.close();
        }catch(Exception e){
            System.out.println("Error Deleting Reservasi...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    @Override
    public int generateId() {
        con = dbCon.makeConnection();
        String sql = "SELECT MAX(CAST(SUBSTRING(id_reservasi, 2) AS SIGNED)) AS highest_number FROM reservasi WHERE id_reservasi LIKE 'R%';";
        //mendapatkan nilai tertinggi dari id yang ada di database

        System.out.println("Generating Id...");
        int id = 0;

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null && rs.next()) {
                if (!rs.wasNull()) {
                    id = rs.getInt("highest_number") + 1;
                }
            }

            //memasukan id terakhir ke dalam variabel id
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return id;
    }
}
