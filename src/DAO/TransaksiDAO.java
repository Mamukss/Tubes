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
import Interface_DAO.IGenerateID;
import Interface_DAO.IShowDataList;
import Interface_DAO.IDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import Model.Transaksi;

public class TransaksiDAO implements IDAO<Transaksi, String>, IShowDataList<Transaksi>, IGenerateID {

    protected DbConnection dbCon = new DbConnection();
    protected Connection con;

    @Override
    public void insert(Transaksi t) {
        con = dbCon.makeConnection();
        String sql = "INSERT INTO transaksi (no_resi, id_karyawan, id_customer, tanggal_pesanan, total_harga) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getNoResi());
            ps.setString(2, t.getIdKaryawan());
            ps.setString(3, t.getIdCustomer());
            ps.setDate(4, new Date(t.getTanggalPesanan().getTime()));
            ps.setFloat(5, t.getTotalHarga());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting Transaksi: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void update(Transaksi t, String id) {
        con = dbCon.makeConnection();
        String sql = "UPDATE transaksi SET id_karyawan=?, id_customer=?, tanggal_pesanan=?, total_harga=? WHERE no_resi=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getIdKaryawan());
            ps.setString(2, t.getIdCustomer());
            ps.setDate(3, new Date(t.getTanggalPesanan().getTime()));
            ps.setFloat(4, t.getTotalHarga());
            ps.setString(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating Transaksi: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void delete(String id) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM transaksi WHERE no_resi=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error deleting Transaksi: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public List<Transaksi> showData(String keyword) {
        con = dbCon.makeConnection();
        String sql = "SELECT * FROM transaksi WHERE no_resi LIKE ? OR id_customer LIKE ? OR id_karyawan LIKE ?";
        List<Transaksi> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaksi t = new Transaksi(
                    rs.getString("no_resi"),
                    rs.getString("id_karyawan"),
                    rs.getString("id_customer"),
                    rs.getDate("tanggal_pesanan"),
                    rs.getFloat("total_harga")
                );
                list.add(t);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error fetching Transaksi: " + e.getMessage());
        }
        dbCon.closeConnection();
        return list;
    }

    @Override
    public List<Transaksi> showDataList() {
        return showData("");
    }

    @Override
    public int generateId() {
        con = dbCon.makeConnection();
        String sql = "SELECT MAX(CAST(SUBSTRING(no_resi, 3) AS SIGNED)) AS max_id FROM transaksi WHERE no_resi LIKE 'TR%'";
        int id = 0;

        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs != null && rs.next()) {
                id = rs.getInt("max_id") + 1;
            }
        } catch (Exception e) {
            System.out.println("Error generating Transaksi ID: " + e.getMessage());
        }
        dbCon.closeConnection();
        return id;
    }
} 

