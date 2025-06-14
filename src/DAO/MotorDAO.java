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
import Model.Motor;

public class MotorDAO implements IDAO<Motor, String> {

    protected DbConnection dbCon = new DbConnection();
    protected Connection con;

    @Override
    public void insert(Motor m) {
        con = dbCon.makeConnection();
        String sql = "INSERT INTO motor (id_kendaraan, jumlah_tak) VALUES (?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getIdKendaraan());
            ps.setInt(2, m.getJumlahTak());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting Motor: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void update(Motor m, String id) {
        con = dbCon.makeConnection();
        String sql = "UPDATE motor SET jumlah_tak=? WHERE id_kendaraan=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, m.getJumlahTak());
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating Motor: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void delete(String id) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM motor WHERE id_kendaraan=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error deleting Motor: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public List<Motor> showData(String keyword) {
        con = dbCon.makeConnection();
        String sql = "SELECT k.*, mo.jumlah_tak FROM kendaraan k JOIN motor mo ON k.id_kendaraan = mo.id_kendaraan WHERE k.nama LIKE ?";
        List<Motor> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Motor m = new Motor(
                    rs.getString("id_kendaraan"),
                    rs.getString("nama"),
                    rs.getFloat("harga"),
                    rs.getBytes("gambar"),
                    rs.getInt("jumlah_tak")
                );
                list.add(m);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error fetching Motor: " + e.getMessage());
        }
        dbCon.closeConnection();
        return list;
    }
} 
