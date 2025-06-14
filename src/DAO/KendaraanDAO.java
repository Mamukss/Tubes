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

import java.sql.PreparedStatement;                                
import Connection.DbConnection;
import Interface_DAO.IDAO;
import Interface_DAO.IGenerateID;
import Interface_DAO.IShowDataList;
import Interface_DAO.IKendaraanDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Kendaraan;
import Model.Mobil;
import Model.Motor;
import Model.Truck;


public class KendaraanDAO implements IDAO<Kendaraan, String>, IShowDataList<Kendaraan>, IGenerateID, IKendaraanDAO {

    protected DbConnection dbCon = new DbConnection();
    protected Connection con;

    @Override
    public void insert(Kendaraan k) {
        con = dbCon.makeConnection();
        String sql = "INSERT INTO kendaraan (id_kendaraan, nama, jenis, harga, gambar) VALUES (?, ?, ?, ?, ?)";
        System.out.println("Adding Kendaraan...");

        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {
            st.setString(1, k.getIdKendaraan());
            st.setString(2, k.getNama());
            st.setString(3, k.getJenis());
            st.setFloat(4, k.getHarga());
            byte[] imageBytes = k.getGambar();
            if (imageBytes != null && imageBytes.length > 0) {
                st.setBytes(5, imageBytes);
            } else {
                st.setNull(5, java.sql.Types.BLOB);
            }

            int result = st.executeUpdate();
            System.out.println("Added " + result + " Kendaraan");
        } catch (Exception e) {
            System.out.println("Error adding Kendaraan...");
            e.printStackTrace();
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void update(Kendaraan k, String id) {
        con = dbCon.makeConnection();
        String sql = "UPDATE kendaraan SET nama = ?, jenis = ?, harga = ?, gambar = ? WHERE id_kendaraan = ?";
        System.out.println("Updating Kendaraan...");

        try (PreparedStatement st = (PreparedStatement) con.prepareStatement(sql)) {
            st.setString(1, k.getNama());
            st.setString(2, k.getJenis());
            st.setFloat(3, k.getHarga());
            byte[] imageBytes = k.getGambar();
            if (imageBytes != null && imageBytes.length > 0) {
                st.setBytes(4, imageBytes);
            } else {
                st.setNull(4, java.sql.Types.BLOB);
            }
            st.setString(5, id);

            int result = st.executeUpdate();
            System.out.println("Updated " + result + " Kendaraan with id " + id);
        } catch (Exception e) {
            System.out.println("Error updating Kendaraan...");
            e.printStackTrace();
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void delete(String id) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM kendaraan WHERE id_kendaraan = '" + id + "'";
        System.out.println("Deleting Kendaraan...");

        try (Statement statement = con.createStatement()) {
            int result = statement.executeUpdate(sql);
            System.out.println("Deleted " + result + " Kendaraan with id " + id);
        } catch (Exception e) {
            System.out.println("Error deleting Kendaraan...");
            e.printStackTrace();
        }
        dbCon.closeConnection();
    }

    @Override
    public List<Kendaraan> showData(String keyword) {
        con = dbCon.makeConnection();
        String sql = "SELECT k.*, m.jenis_mesin, mo.jumlah_tak, t.jenis_roda FROM kendaraan k " +
                     "LEFT JOIN mobil m ON k.id_kendaraan = m.id_kendaraan " +
                     "LEFT JOIN motor mo ON k.id_kendaraan = mo.id_kendaraan " +
                     "LEFT JOIN truck t ON k.id_kendaraan = t.id_kendaraan " +
                     "WHERE k.id_kendaraan LIKE '%" + keyword + "%' OR k.nama LIKE '%" + keyword + "%' OR k.jenis LIKE '%" + keyword + "%'";
        System.out.println("Searching Kendaraan...");

        List<Kendaraan> list = new ArrayList<>();

        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Kendaraan k = mapToKendaraan(rs);
                list.add(k);
            }
        } catch (Exception e) {
            System.out.println("Error fetching Kendaraan data...");
            e.printStackTrace();
        }
        dbCon.closeConnection();
        return list;
    }

    @Override
    public List<Kendaraan> showDataList() {
        return showData(""); // reuse dengan keyword kosong untuk ambil semua data
    }

    private Kendaraan mapToKendaraan(ResultSet rs) throws Exception {
        String id = rs.getString("id_kendaraan");
        String nama = rs.getString("nama");
        String jenis = rs.getString("jenis");
        float harga = rs.getFloat("harga");
        byte[] gambar = rs.getBytes("gambar");

        switch (jenis.toLowerCase()) {
            case "mobil":
                return new Mobil(id, nama, harga, gambar, rs.getString("jenis_mesin"));
            case "motor":
                return new Motor(id, nama, harga, gambar, rs.getInt("jumlah_tak"));
            case "truck":
                return new Truck(id, nama, harga, gambar, rs.getString("jenis_roda"));
            default:
                return null;
        }
    }

    @Override
    public int generateId() {
        con = dbCon.makeConnection();
        String sql = "SELECT MAX(CAST(SUBSTRING(id_kendaraan, 2) AS SIGNED)) AS max_id FROM kendaraan WHERE id_kendaraan LIKE 'K%'";
        int id = 0;

        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs != null && rs.next()) {
                id = rs.getInt("max_id") + 1;
            }
        } catch (Exception e) {
            System.out.println("Error generating ID...");
            e.printStackTrace();
        }
        dbCon.closeConnection();
        return id;
    }

    @Override
    public void deleteOldJenis(String data) {
        
    }
}
