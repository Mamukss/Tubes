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
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import Model.Reservasi;

public class ReservasiDAO implements IDAO<Reservasi, String>, IShowDataList<Reservasi> {

    protected DbConnection dbCon = new DbConnection();
    protected Connection con;

    @Override
    public void insert(Reservasi r) {
        con = dbCon.makeConnection();
        String sql = "INSERT INTO reservasi (id_reservasi, id_karyawan, id_customer, tanggal_reservasi, jenis_reservasi, paket_reservasi, total_harga) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getIdReservasi());
            ps.setString(2, r.getIdKaryawan());
            ps.setString(3, r.getIdCustomer());
            ps.setDate(4, new Date(r.getTanggalReservasi().getTime()));
            ps.setString(5, r.getJenisReservasi());
            ps.setString(6, r.getPaketReservasi());
            ps.setFloat(7, r.getTotalHarga());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting Reservasi: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void update(Reservasi r, String id) {
        con = dbCon.makeConnection();
        String sql = "UPDATE reservasi SET id_karyawan=?, id_customer=?, tanggal_reservasi=?, jenis_reservasi=?, paket_reservasi=?, total_harga=? WHERE id_reservasi=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, r.getIdKaryawan());
            ps.setString(2, r.getIdCustomer());
            ps.setDate(3, new Date(r.getTanggalReservasi().getTime()));
            ps.setString(4, r.getJenisReservasi());
            ps.setString(5, r.getPaketReservasi());
            ps.setFloat(6, r.getTotalHarga());
            ps.setString(7, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating Reservasi: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void delete(String id) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM reservasi WHERE id_reservasi=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error deleting Reservasi: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public List<Reservasi> showData(String keyword) {
        con = dbCon.makeConnection();
        String sql = "SELECT * FROM reservasi WHERE id_reservasi LIKE ? OR id_customer LIKE ? OR jenis_reservasi LIKE ?";
        List<Reservasi> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reservasi r = new Reservasi(
                    rs.getString("id_reservasi"),
                    rs.getString("id_karyawan"),
                    rs.getString("id_customer"),
                    rs.getDate("tanggal_reservasi"),
                    rs.getString("jenis_reservasi"),
                    rs.getString("paket_reservasi"),
                    rs.getFloat("total_harga")
                );
                list.add(r);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error fetching Reservasi: " + e.getMessage());
        }
        dbCon.closeConnection();
        return list;
    }

    @Override
    public List<Reservasi> showDataList() {
        return showData("");
    }
} 

