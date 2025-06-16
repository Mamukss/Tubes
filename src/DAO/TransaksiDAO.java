/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.DbConnection;
import Interface_DAO.IDAO;
import Interface_DAO.IGenerateID;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Model.Karyawan;
import Model.Customer;
import Model.Transaksi;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;   gatau di pakai kapan
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;


public class TransaksiDAO implements IDAO<Transaksi, String>, IGenerateID{
    protected DbConnection dbCon = new DbConnection();
    protected Connection con;
    
    @Override
    public int generateId(){
        con = dbCon.makeConnection();
        String sql = "SELECT MAX(CAST(SUBSTRING(id_pembelian, 2) AS SIGNED)) AS highest_number FROM transaksi WHERE id_pembelian LIKE 'T%';";
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
    

    public void updateHarga(Transaksi C){
        con = dbCon.makeConnection();
        String sql = "UPDATE transaksi " +
             "SET total_harga = '" + hitungTotalHarga(C.getId_pembelian()) + "' " +
             "WHERE id_pembelian = '" + C.getId_pembelian() + "'";

        System.out.println("Adding Insert Harga...");

        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Added " + result + " Transaksi");
            statement.close();
        }catch (Exception e){
            System.out.println("Error adding Harga Transaksi...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    
    @Override
    public void insert(Transaksi C){
        con = dbCon.makeConnection();
        
        String sql = "INSERT INTO transaksi (id_pembelian, id_karyawan, id_customer, tanggal_pesanan, total_harga) VALUES ('" 
                + C.getId_pembelian() + "', '" 
                + C.getId_karyawan() + "', '" 
                + C.getId_customer() + "', '" 
                + C.getTanggal_pesanan() + "', '"
                + C.getTotal_harga() + "')";
            System.out.println("Adding Transaksi...");

            try{
                Statement statement = con.createStatement();
                int result = statement.executeUpdate(sql);
                System.out.println("Added " + result + " Transaksi");
                statement.close();
            }catch (Exception e){
                System.out.println("Error adding Transaksi...");
                System.out.println(e);
            }
            dbCon.closeConnection();
    }
    
    
    @Override
    public List<Transaksi> showData(String data) {
        con = dbCon.makeConnection();

        String sql = "SELECT t.id_pembelian, t.id_karyawan, t.id_customer, t.tanggal_pesanan, t.total_harga, " 
                 + "k.nama_karyawan, k.jabatan, k.gaji, " 
                 + "c.nama, c.alamat, c.nomor_telepon " 
                 + "FROM transaksi t " 
                 + "JOIN karyawan k ON t.id_karyawan = k.id_karyawan " 
                 + "JOIN customer c ON t.id_customer = c.id_customer " 
                 + "WHERE t.id_pembelian LIKE '%" + data + "%' " 
                 + "OR t.id_karyawan LIKE '%" + data + "%' " 
                 + "OR t.id_customer LIKE '%" + data + "%' " 
                 + "OR k.nama_karyawan LIKE '%" + data + "%' " 
                 + "OR c.nama LIKE '%" + data + "%'"
                 + "ORDER BY t.id_pembelian";
        System.out.println("Fetching Data...");
        List<Transaksi> c = new ArrayList<>();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    Karyawan k = new Karyawan(
                            rs.getString("id_karyawan"),
                            rs.getString("nama_karyawan"),
                            rs.getString("jabatan"),
                            rs.getFloat("gaji")
                    );
                    Customer cs = new Customer(
                            rs.getString("id_customer"),
                            rs.getString("nama"),
                            rs.getString("alamat"),
                            rs.getString("nomor_telepon")
                    );
                    
                    c.add(new Transaksi(
                            rs.getString("id_pembelian"),
                            rs.getString("id_karyawan"),
                            rs.getString("id_customer"),
                            rs.getString("tanggal_pesanan"),
                            rs.getFloat("total_harga"),
                            k,
                            cs
                    ));
                }
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return c;
    }
    
    @Override
    public void update(Transaksi c, String id_pembelian){
        con = dbCon.makeConnection();
        
        String sql = "UPDATE `transaksi` SET "
                + "`id_pembelian`='"+ c.getId_pembelian()+"',"
                + "`id_karyawan`='"+ c.getId_karyawan()+"',"
                + "`id_customer`='"+ c.getId_customer()+"',"
                + "`tanggal_pesanan`='"+ c.getTanggal_pesanan()+"' "
                + "WHERE `id_pembelian`='" + id_pembelian + "'";
        System.out.println("Updating transaksi");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited" + result + " Transaksi " + id_pembelian);
            statement.close();
        }catch(Exception e){
            System.out.println("Error Updating Transaksi...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    @Override
    public void delete(String id_pembelian){
        con = dbCon.makeConnection();
        String sql = "DELETE FROM `transaksi` WHERE `id_pembelian`='" + id_pembelian + "'";
        System.out.println("Deleting Transaksi...");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited " + result + " Transaksi " + id_pembelian);
            statement.close();
        }catch(Exception e){
            System.out.println("Error Deleting Transaksi...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    public double hitungTotalHarga(String id_pembelian) {
        Connection con = dbCon.makeConnection();

        String sql = "SELECT SUM(sub_total) AS sub " +
                     "FROM pembelian_Kendaraan " +
                     "WHERE id_pembelian = '" + id_pembelian + "'";

        System.out.println("Hitung Total Harga...");
        double total = 0;

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                total = rs.getDouble("sub");
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            e.printStackTrace();
        } finally {
            dbCon.closeConnection();
        }

        return total;
    }
    
    public int cariJumlahProdukTerlaris(){
        int jumlah = 0;
        con = dbCon.makeConnection();
        
        String sql = "SELECT SUM(p.jumlah) AS jml " +
                 "FROM transaksi t " +
                 "JOIN pembelian_Kendaraan p ON t.id_pembelian = p.id_pembelian " +
                 "GROUP BY p.id_kendaraan " +
                 "ORDER BY jml DESC " +
                 "LIMIT 1";
        System.out.println("Fetching Data Mencari Produk dengan Penjualan Tertinggi...");
        
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                jumlah = rs.getInt("jml");
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            e.printStackTrace();
        } finally {
            dbCon.closeConnection();
        }

        return jumlah;
    }
    
    public String cariNamaMenuTerlaris() {
        String namaMenu = null;
        con = dbCon.makeConnection();

        String sql = "SELECT nama_kendaraan, SUM(p.jumlah) AS jml " +
                     "FROM transaksi t " +
                     "JOIN pembelian_Kendaraan p ON t.id_pembelian = p.id_pembelian " +
                     "JOIN kendaraan k ON p.id_kendaraan = k.id_kendaraan " +
                     "GROUP BY p.id_kendaraan " +
                     "ORDER BY jml DESC " +
                     "LIMIT 1";
        System.out.println("Fetching Data Mencari Produk dengan Penjualan Tertinggi...");

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                namaMenu = rs.getString("nama_kendaraan");
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            e.printStackTrace();
        } finally {
            dbCon.closeConnection();
        }

        return namaMenu;
    }
    
    public double hitungTotalOmset() {
        double totalOmset = 0;
        con = dbCon.makeConnection();

        String sql = "SELECT SUM(t.total_harga) AS omset " +
                     "FROM transaksi t";
        System.out.println("Fetching Data Mencari Omset...");

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                totalOmset = rs.getDouble("omset");
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            e.printStackTrace();
        } finally {
            dbCon.closeConnection();
        }

        return totalOmset;
    }
    
    public int hitungTotalTransaksi() {
        int totalTransaksi = 0;
        con = dbCon.makeConnection();

        String sql = "SELECT COUNT(t.id_pembelian) AS total " +
                     "FROM transaksi t";
        System.out.println("Fetching Data Mencari Total Transaksi...");

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                totalTransaksi = rs.getInt("total");
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            e.printStackTrace();
        } finally {
            dbCon.closeConnection();
        }

        return totalTransaksi;
    }
    
    public List<Transaksi> showDatabyTanggal(String tanggalMulai, String tanggalSelesai) {
        con = dbCon.makeConnection();

        String sql = "SELECT t.id_pembelian, t.id_karyawan, t.id_customer, t.tanggal_pesanan, t.total_harga, " 
                 + "k.nama_karyawan, k.jabatan, k.gaji, " 
                 + "c.nama, c.alamat, c.nomor_telepon " 
                 + "FROM transaksi t " 
                 + "JOIN karyawan k ON t.id_karyawan = k.id_karyawan " 
                 + "JOIN pembelian_Kendaraan c ON t.id_customer = c.id_customer " 
                 + "WHERE t.tanggal_pesanan BETWEEN '" + tanggalMulai + "' AND '" + tanggalSelesai + "'"
                 + "ORDER BY t.id_pembelian";
        System.out.println("Fetching Data...");
        List<Transaksi> c = new ArrayList<>();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    Karyawan k = new Karyawan(
                            rs.getString("id_karyawan"),
                            rs.getString("nama_karyawan"),
                            rs.getString("jabatan"),
                            rs.getFloat("gaji")
                    );
                    Customer cs = new Customer(
                            rs.getString("id_customer"),
                            rs.getString("nama"),
                            rs.getString("alamat"),
                            rs.getString("nomor_telepon")
                    );
                    
                    c.add(new Transaksi(
                            rs.getString("id_pembelian"),
                            rs.getString("id_karyawan"),
                            rs.getString("id_customer"),
                            rs.getString("tanggal_pesanan"),
                            rs.getFloat("total_harga"),
                            k,
                            cs
                    ));
                }
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return c;
    }
    
//    
   public void createReceipt(String id_pesanan) {
//        con = dbCon.makeConnection();
//       
//        try { // UBAH PATHNYA SESUAI JARXML MASING - MASING
//            String reportSrcFile = "D:\\Semester 4\\PBOGuided\\TUBES\\A_3_Restoran\\src\\report\\transaksiReport.jrxml";
//            JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
//
//            Map<String, Object> parameters = new HashMap<>();
//            parameters.put("id_pesanan", id_pesanan);
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
//
//            JasperViewer.viewReport(jasperPrint, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } 
    }
}
