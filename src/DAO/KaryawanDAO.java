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
import Interface_DAO.IGenerateID;
import Interface_DAO.IShowDataList;
import Interface_DAO.ISearchData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Karyawan;

public class KaryawanDAO implements IDAO<Karyawan,String>, IShowDataList<Karyawan>, IGenerateID, ISearchData<Karyawan, String> {

    protected DbConnection dbCon = new DbConnection();
    protected Connection con;

    @Override
    public void insert(Karyawan k) {
        con = dbCon.makeConnection();
        String sql = "INSERT INTO karyawan (id_karyawan, nama_karyawan, jabatan, gaji, username, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, k.getIdKaryawan());
            ps.setString(2, k.getNamaKaryawan());
            ps.setString(3, k.getJabatan());
            ps.setFloat(4, k.getGaji());
            ps.setString(5, k.getUsername());
            ps.setString(6, k.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting Karyawan: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void update(Karyawan k, String id) {
        con = dbCon.makeConnection();
        String sql = "UPDATE karyawan SET nama_karyawan=?, jabatan=?, gaji=?, username=?, password=? WHERE id_karyawan=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, k.getNamaKaryawan());
            ps.setString(2, k.getJabatan());
            ps.setFloat(3, k.getGaji());
            ps.setString(4, k.getUsername());
            ps.setString(5, k.getPassword());
            ps.setString(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating Karyawan: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void delete(String id) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM karyawan WHERE id_karyawan=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error deleting Karyawan: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public List<Karyawan> showData(String keyword) {
        con = dbCon.makeConnection();
        String sql = "SELECT * FROM karyawan WHERE id_karyawan LIKE ? OR nama_karyawan LIKE ? OR jabatan LIKE ?";
        List<Karyawan> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Karyawan k = new Karyawan(
                    rs.getString("id_karyawan"),
                    rs.getString("nama_karyawan"),
                    rs.getString("jabatan"),
                    rs.getFloat("gaji"),
                    rs.getString("username"),
                    rs.getString("password")
                );
                list.add(k);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error fetching Karyawan: " + e.getMessage());
        }
        dbCon.closeConnection();
        return list;
    }

    @Override
    public List<Karyawan> showDataList() {
        return showData("");
    }

    @Override
    public int generateId() {
        con = dbCon.makeConnection();
        String sql = "SELECT MAX(CAST(SUBSTRING(id_karyawan, 2) AS SIGNED)) AS max_id FROM karyawan WHERE id_karyawan LIKE 'K%'";
        int id = 0;

        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs != null && rs.next()) {
                id = rs.getInt("max_id") + 1;
            }
        } catch (Exception e) {
            System.out.println("Error generating Karyawan ID: " + e.getMessage());
        }
        dbCon.closeConnection();
        return id;
    }
    @Override
    public Karyawan search(String keyword) {
        con = dbCon.makeConnection();
        String sql = "SELECT * FROM karyawan WHERE id_karyawan = ? OR nama_karyawan LIKE ?";
        Karyawan k = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, keyword);
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                k = new Karyawan(
                    rs.getString("id_karyawan"),
                    rs.getString("nama_karyawan"),
                    rs.getString("jabatan"),
                    rs.getFloat("gaji"),
                    rs.getString("username"),
                    rs.getString("password")
                );
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error searching Karyawan: " + e.getMessage());
        }
        dbCon.closeConnection();
        return k;
    }

} 
