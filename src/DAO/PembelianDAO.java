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
import Interface_DAO.IShowDataList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.PembelianKendaraan;

public class PembelianDAO implements IDAO<PembelianKendaraan, String>, IShowDataList<PembelianKendaraan> {

    protected DbConnection dbCon = new DbConnection();
    protected Connection con;

    @Override
    public void insert(PembelianKendaraan p) {
        con = dbCon.makeConnection();
        String sql = "INSERT INTO pembelian_kendaraan (no_resi, id_customer, id_kendaraan, jumlah, tambahan, metode_pembayaran, sub_total) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNoResi());
            ps.setString(2, p.getIdCustomer());
            ps.setString(3, p.getIdKendaraan());
            ps.setInt(4, p.getJumlah());
            ps.setString(5, p.getTambahan());
            ps.setString(6, p.getMetodePembayaran());
            ps.setFloat(7, p.getSubTotal());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting PembelianKendaraan: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void update(PembelianKendaraan p, String id) {
        con = dbCon.makeConnection();
        String sql = "UPDATE pembelian_kendaraan SET id_customer=?, id_kendaraan=?, jumlah=?, tambahan=?, metode_pembayaran=?, sub_total=? WHERE no_resi=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getIdCustomer());
            ps.setString(2, p.getIdKendaraan());
            ps.setInt(3, p.getJumlah());
            ps.setString(4, p.getTambahan());
            ps.setString(5, p.getMetodePembayaran());
            ps.setFloat(6, p.getSubTotal());
            ps.setString(7, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating PembelianKendaraan: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void delete(String id) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM pembelian_kendaraan WHERE no_resi=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error deleting PembelianKendaraan: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public List<PembelianKendaraan> showData(String keyword) {
        con = dbCon.makeConnection();
        String sql = "SELECT * FROM pembelian_kendaraan WHERE no_resi LIKE ? OR id_customer LIKE ? OR id_kendaraan LIKE ?";
        List<PembelianKendaraan> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PembelianKendaraan p = new PembelianKendaraan(
                    rs.getString("no_resi"),
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
        } catch (Exception e) {
            System.out.println("Error fetching PembelianKendaraan: " + e.getMessage());
        }
        dbCon.closeConnection();
        return list;
    }

    @Override
    public List<PembelianKendaraan> showDataList() {
        return showData("");
    }
} 

