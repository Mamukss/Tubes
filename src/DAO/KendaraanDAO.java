/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author yohan
 */
import com.mysql.jdbc.PreparedStatement;
import Connection.DbConnection;
import Interface_DAO.IDAO;
import Interface_DAO.IGenerateID;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Kendaraan;
import Model.Mobil;
import Model.Motor;
import Model.Truck;
import Interface_DAO.IShowDataList;

public class KendaraanDAO implements IDAO<Kendaraan, String>, IShowDataList<Kendaraan>, IGenerateID {

    protected DbConnection dbCon = new DbConnection();
    protected Connection con;

    @Override
    public void insert(Kendaraan K) {
        con = dbCon.makeConnection();

        String sql = "INSERT INTO kendaraan (id_kendaraan, nama_kendaraan, jenis_kendaraan, harga, gambar) VALUES (?, ?, ?, ?, ?)";
        System.out.println("Adding kendaraan...");

        PreparedStatement st = null;
        FileInputStream fis = null;

        try {
            st = (PreparedStatement) con.prepareStatement(sql);
            st.setString(1, K.getId_kendaraan());
            st.setString(2, K.getNama_kendaraan());
            st.setString(3, K.getJenis_kendaraan());
            st.setFloat(4, K.getHarga());
            byte[] imageBytes = K.getGambar();
            if (imageBytes != null && imageBytes.length > 0) {
                st.setBytes(5, imageBytes);
            } else {
                st.setNull(5, java.sql.Types.BLOB);
            }

            int result = st.executeUpdate();
            System.out.println("Added " + result + " kendaraan");

        } catch (Exception e) {
            System.out.println("Error adding kendaraan...");
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (st != null) {
                    st.close();
                }
                dbCon.closeConnection();
            } catch (Exception ex) {
                System.out.println("Error closing resources...");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Kendaraan> showData(String search) {
        con = dbCon.makeConnection();

        String sql = "SELECT kendaraan.*, motor.jumlah_tak, mobil.jenis_mesin, truck.jenis_roda FROM kendaraan\n"
                + "LEFT JOIN motor ON kendaraan.id_kendaraan = motor.id_kendaraan\n"
                + "LEFT JOIN mobil ON kendaraan.id_kendaraan = mobil.id_kendaraan\n"
                + "LEFT JOIN truck ON kendaraan.id_kendaraan = truck.id_kendaraan\n"
                + "WHERE kendaraan.id_kendaraan LIKE '%" + search + "%' "
                + "OR kendaraan.nama_kendaraan LIKE '%" + search + "%' "
                + "OR kendaraan.jenis_kendaraan LIKE '%" + search + "%' ";
        System.out.println("Searching kendaraan...");
        Kendaraan k = null;
        List<Kendaraan> list = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString("jenis_kendaraan").equals("Motor")) {
                        k = new Motor(
                                rs.getString("jumlah_tak"), //SQL ukuran
                                rs.getString("id_kendaraan"), //SQL id_menu
                                rs.getString("nama_kendaraan"), //SQL nama_menu
                                rs.getString("jenis_kendaraan"), //SQL jenis_menu
                                rs.getFloat("harga"), //SQL harga
                                rs.getBytes("gambar"));
                        
                    } else if (rs.getString("jenis_kendaraan").equals("Mobil")){
                        k = new Mobil(
                                rs.getString("jenis_mesin"), //SQL catatan
                                rs.getString("id_kendaraan"), //SQL id_menu
                                rs.getString("nama_kendaraan"), //SQL nama_menu
                                rs.getString("jenis_kendaraan"), //SQL jenis_menu
                                rs.getFloat("harga"), //SQL harga
                                rs.getBytes("gambar"));
                    }else{
                       k = new Truck(
                                rs.getString("jenis_roda"), //SQL catatan
                                rs.getString("id_kendaraan"), //SQL id_menu
                                rs.getString("nama_kendaraan"), //SQL nama_menu
                                rs.getString("jenis_kendaraan"), //SQL jenis_menu
                                rs.getFloat("harga"), //SQL harga
                                rs.getBytes("gambar"));
                        
                    }
                    list.add(k);
                }
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
    public void update(Kendaraan k, String id_kendaraan) {
        con = dbCon.makeConnection();

        String sql = "UPDATE kendaraan SET nama_kendaraan = ?, jenis_kendaraan = ?, harga = ?, gambar = ? WHERE id_kendaraan = ?";
        System.out.println("Updating kendaraan...");

        PreparedStatement st = null;

        try {
            st = (PreparedStatement) con.prepareStatement(sql);
            st.setString(1, k.getNama_kendaraan());
            st.setString(2, k.getJenis_kendaraan());
            st.setFloat(3, k.getHarga());

            byte[] imageBytes = k.getGambar();
            if (imageBytes != null && imageBytes.length > 0) {
                st.setBytes(4, imageBytes);
            } else {
                st.setNull(4, java.sql.Types.BLOB);
            }

            st.setString(5, id_kendaraan);

            int result = st.executeUpdate();
            System.out.println("Updated " + result + " Menu with id_kendaraan " + id_kendaraan);

        } catch (Exception e) {
            System.out.println("Error updating kendaraan...");
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                dbCon.closeConnection();
            } catch (Exception ex) {
                System.out.println("Error closing resources...");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String id_kendaraan) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM `kendaraan` WHERE `id_kendaraan` = '" + id_kendaraan + "'";
        System.out.println("Deleting kendaraan...");

        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited" + result + " kendaraan " + id_kendaraan);
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Updating kendaraan...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }

    @Override
    public int generateId() {
        con = dbCon.makeConnection();
        String sql = "SELECT MAX(CAST(SUBSTRING(id_kendaraan, 2) AS SIGNED)) AS highest_number FROM kendaraan WHERE id_kendaraan LIKE 'K%';";
        //mendapatkan nilai tertinggi dari id yang ada di database

        System.out.println("Generating Id...");
        int id = 0;

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null && rs.next()) {
                if (!rs.wasNull()) {
                    id = rs.getInt("highest_number") + 1;
                }
            }

            //memasukan id terakhir ke dalam variabel id
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        return id;
    }

    public boolean cekPerubahanJenis(String jenis_kendaraan, String id_kendaraan) {
        con = dbCon.makeConnection();

        String sql = "SELECT  jenis_kendaraan!='"
                + jenis_kendaraan
                + "'"
                + "as result"
                + " FROM `kendaraan`"
                + " WHERE id_kendaraan = '"
                + id_kendaraan
                + "';";
        System.out.println(sql);
        System.out.println("Checking Result...");
        boolean result = false;

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    result = rs.getBoolean("result");
                }
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Fetching data...");
            System.out.println(e);
        }
        dbCon.closeConnection();
        System.out.println("The result is" + result);
        return result;
    }

    @Override
    public List<Kendaraan> showDataList() {
        con = dbCon.makeConnection();

        String sql = "SELECT K.*, MB.jenis_mesin, MT.jumlah_tak, TK.jenis_roda FROM kendaraan K "
                + "LEFT JOIN mobil MB ON K.id_kendaraan = MB.id_kendaraan "
                + "LEFT JOIN motor MB ON K.id_kendaraan = MT.id_kendaraan "
                + "LEFT JOIN truck MB ON K.id_kendaraan = TK.id_kendaraan ";

        System.out.println("Fetching Data...");

        List<Kendaraan> list = new ArrayList();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Kendaraan data = null;

            int i = 0;

            if (rs != null) {
                while (rs.next()) {
                    if (rs.getString("jenis_kendaraan").equals("Motor")) {
                        data = new Motor(
                                rs.getString("jumlah_tak"), //SQL ukuran
                                rs.getString("id_kendaraan"), //SQL id_menu
                                rs.getString("nama_kendaraan"), //SQL nama_menu
                                rs.getString("jenis_kendaraan"), //SQL jenis_menu
                                rs.getFloat("harga"), //SQL harga
                                rs.getBytes("gambar"));
                        
                    } else if (rs.getString("jenis_kendaraan").equals("Mobil")){
                        data = new Mobil(
                                rs.getString("jenis_mesin"), //SQL catatan
                                rs.getString("id_kendaraan"), //SQL id_menu
                                rs.getString("nama_kendaraan"), //SQL nama_menu
                                rs.getString("jenis_kendaraan"), //SQL jenis_menu
                                rs.getFloat("harga"), //SQL harga
                                rs.getBytes("gambar"));
                    }else{
                       data = new Truck(
                                rs.getString("jenis_roda"), //SQL catatan
                                rs.getString("id_kendaraan"), //SQL id_menu
                                rs.getString("nama_kendaraan"), //SQL nama_menu
                                rs.getString("jenis_kendaraan"), //SQL jenis_menu
                                rs.getFloat("harga"), //SQL harga
                                rs.getBytes("gambar"));
                        
                    }
                    list.add(data);
                }
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

    public float searchHarga(String data) { // khusus mencari harga menu
        con = dbCon.makeConnection();

        String sql = "SELECT * FROM `kendaraan` WHERE id_kendaraan = '" + data + "' ";
        System.out.println("Searching Harga kendaraan...");
        float c = 0;

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    c = rs.getFloat("harga");
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

}

