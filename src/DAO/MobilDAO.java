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
import Interface_DAO.IDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.Mobil;

public class MobilDAO implements IDAO<Mobil, String> {

    protected DbConnection dbCon = new DbConnection();
    protected Connection con;

    @Override
    public void insert(Mobil m) {
        con = dbCon.makeConnection();
        String sql = "INSERT INTO mobil (id_kendaraan, jenis_mesin) VALUES (?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getIdKendaraan());
            ps.setString(2, m.getJenisMesin());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting Mobil: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void update(Mobil m, String id) {
        con = dbCon.makeConnection();
        String sql = "UPDATE mobil SET jenis_mesin=? WHERE id_kendaraan=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getJenisMesin());
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating Mobil: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public void delete(String id) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM mobil WHERE id_kendaraan=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error deleting Mobil: " + e.getMessage());
        } finally {
            dbCon.closeConnection();
        }
    }

    @Override
    public List<Mobil> showData(String keyword) {
        con = dbCon.makeConnection();
        String sql = "SELECT k.*, m.jenis_mesin FROM kendaraan k JOIN mobil m ON k.id_kendaraan = m.id_kendaraan WHERE k.nama LIKE ?";
        List<Mobil> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mobil m = new Mobil(
                    rs.getString("id_kendaraan"),
                    rs.getString("nama"),
                    rs.getFloat("harga"),
                    rs.getBytes("gambar"),
                    rs.getString("jenis_mesin")
                );
                list.add(m);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error fetching Mobil: " + e.getMessage());
        }
        dbCon.closeConnection();
        return list;
    }
} 

