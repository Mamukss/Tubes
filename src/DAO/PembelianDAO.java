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
import Interface_DAO.IDAOTransaksi;
import Interface_DAO.ISearchData;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import Model.Mobil;
import Model.Kendaraan;
import Model.Motor;
import Model.Truck;
import Model.PembelianKendaraan;


public class PembelianDAO implements IDAOTransaksi<PembelianKendaraan, String>{
    protected DbConnection dbCon = new DbConnection();
    protected Connection con;
    
    @Override
    public void insert(PembelianKendaraan C){
        con = dbCon.makeConnection();
        
    String sql = 
        "INSERT INTO `pembelian_kendaraan`(`id_pembelian`, `id_customer`, `id_kendaraan`, `jumlah`, `tambahan`, `metode_pembayaran`, `sub_total`) "
            + "VALUES "
            + "('"+ C.getId_pembelian()+"',"
            + "'"+ C.getId_customer()+"',"
            + "'"+ C.getId_kendaraan()+"',"
            + "'"+ C.getJumlah()+"'," 
            + "'"+ C.getTambahan()+"'," 
            + "'"+ C.getMetodePembayaran()+"'," 
            + "'"+ C.getSubTotal()+"')"; // nanti di control aja setSub_total
    
        System.out.println("Adding pembelian...");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Added " + result + " Pembelian");
            statement.close();
        }catch (Exception e){
            System.out.println("Error adding Pembelian...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }

    @Override
    public List<PembelianKendaraan> showData(String data) {
        con = dbCon.makeConnection();

        String sql = "SELECT * FROM "
                + "PembelianKendaraan P LEFT JOIN kendaraan K ON P.id_kendaraan = M.id_kendaraan "
                + "LEFT JOIN mobil MB ON K.id_kendaraan = MB.id_kendaraan "
                + "LEFT JOIN motor MT ON K.id_kendaraan = MT.id_kendaraan "
                + "LEFT JOIN truck TK ON K.id_kendaraan = TK.id_kendaraan "
                + "WHERE P.id_pembelian LIKE '%" + data + "%'";

        System.out.println("Fetching Data...");
        List<PembelianKendaraan> list = new ArrayList<>();
        Kendaraan k = null;

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                if ("Motor".equals(rs.getString("jenis_kendaraan"))) {
                    k = new Motor(
                            rs.getString("jumlah_tak"), // SQL ukuran
                            rs.getString("id_kendaraan"), // SQL id_menu
                            rs.getString("nama_kendaraan"), // SQL nama_menu
                            rs.getString("jenis_kendaraan"), // SQL jenis_menu
                            rs.getFloat("harga"), // SQL harga
                            rs.getBytes("gambar")); // SQL gambar
                } else if("Mobil".equals(rs.getString("jenis_kendaraan"))){
                    k = new Mobil(
                            rs.getString("jenis_mesin"), // SQL catatan
                            rs.getString("id_kendaraan"), // SQL id_menu
                            rs.getString("nama_kendaraan"), // SQL nama_menu
                            rs.getString("jenis_kendaraan"), // SQL jenis_menu
                            rs.getFloat("harga"), // SQL harga
                            rs.getBytes("gambar")); // SQL gambar
                }else{
                    k = new Truck(
                            rs.getString("jenis_roda"), // SQL catatan
                            rs.getString("id_kendaraan"), // SQL id_menu
                            rs.getString("nama_kendaraan"), // SQL nama_menu
                            rs.getString("jenis_kendaraan"), // SQL jenis_menu
                            rs.getFloat("harga"), // SQL harga
                            rs.getBytes("gambar")); // SQL gambar
                    
                }

                PembelianKendaraan p = new PembelianKendaraan(
                    rs.getString("id_pesanan"),
                    rs.getString("id_customer"),
                    rs.getString("id_kendaraan"),
                    rs.getInt("jumlah"),
                    rs.getString("tambahan"),
                    rs.getString("metode_pembayaran"),
                    rs.getFloat("sub_total")
                );


                list.add(p);
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }

        dbCon.closeConnection();

        return list;
    }
    
    @Override
    public void update(PembelianKendaraan c, String id_pembelian, String id_kendaraan){
        con = dbCon.makeConnection();
        
        String sql = "UPDATE `pembelian_kendaraan` SET "
                + "`id_kendaraan`='"+ c.getId_kendaraan()+"',"
                + "`jumlah`='"+ c.getJumlah()+"',"
                + "`sub_total`='"+ c.getSubTotal()+"' "
                + "WHERE `id_pembelian`='" + id_pembelian + "'"
                + "AND `id_kendaraan` = '" + id_kendaraan + "'";
        System.out.println("Updating pembelian");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited" + result + " pembelian " + id_pembelian);
            statement.close();
        }catch(Exception e){
            System.out.println("Error Updating Pesanan...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    @Override
    public void delete(String id_pembelian, String id_kendaraan){
        con = dbCon.makeConnection();
        String sql = "DELETE FROM `pembelian_kendaraan` "
                + "WHERE `id_pembelian`='" + id_pembelian + ""
                + "AND `id_kendaraan` = '" + id_kendaraan + "'";
        System.out.println("Deleting pembelian...???");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited " + result + " pembelian " + id_pembelian + " Kendaraan ");
            statement.close();
        }catch(Exception e){
            System.out.println("Error Deleting Pembelian...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    
}

