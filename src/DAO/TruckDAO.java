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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.Truck;

public class TruckDAO implements IDAO<Truck, String> {

    protected DbConnection dbCon = new DbConnection();
    protected Connection con;

    @Override
    public void insert(Truck t) {
        con = dbCon.makeConnection();
        String sql = "INSERT INTO truck (id_kendaraan, jenis_roda) VALUES (?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getIdKendaraan());
            ps.setString(2, t.getJenisRoda());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting Truck: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void update(Truck t, String id) {
        con = dbCon.makeConnection();
        String sql = "UPDATE truck SET jenis_roda=? WHERE id_kendaraan=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getJenisRoda());
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating Truck: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void delete(String id) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM truck WHERE id_kendaraan=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error deleting Truck: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public List<Truck> showData(String keyword) {
        con = dbCon.makeConnection();
        String sql = "SELECT k.*, t.jenis_roda FROM kendaraan k JOIN truck t ON k.id_kendaraan = t.id_kendaraan WHERE k.nama LIKE ?";
        List<Truck> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Truck t = new Truck(
                    rs.getString("id_kendaraan"),
                    rs.getString("nama"),
                    rs.getFloat("harga"),
                    rs.getBytes("gambar"),
                    rs.getString("jenis_roda")
                );
                list.add(t);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error fetching Truck: " + e.getMessage());
        }
        dbCon.closeConnection();
        return list;
    }
} 

