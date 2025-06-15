/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Statement;
import Model.Kendaraan;
import Model.Motor;
import Interface_DAO.IKendaraanDAO;
import java.util.List;

public class MotorDAO extends KendaraanDAO implements IKendaraanDAO{

    public void insert(Motor mt) {
        super.insert(mt);
        insertNewJenis(mt);
    }
 
    public void insertNewJenis(Motor mt) {
        con = dbCon.makeConnection();

        String sql = 
                "INSERT INTO `motor`(`id_kendaraan`, `jumlah_tak`) VALUES ('"
                + mt.getId_kendaraan()
                + "','"
                + mt.getSpecial()
                + "')";

        System.out.println("Adding Kendaraan...");

        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Added " + result + " Kendaraan");
            statement.close();
        }catch (Exception e){
            System.out.println("Error adding Kendaraan...");
            System.out.println(e);
        }
        dbCon.closeConnection();  
    }

    @Override
    public void deleteOldJenis(String id_kendaraan) {
        con = dbCon.makeConnection();
        String sql = "DELETE FROM `Mobil` WHERE `id_kendaraan` = '"+id_kendaraan+"'";
        System.out.println("Deleting Motor...");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited" + result + " kendaraan " + id_kendaraan);
            statement.close();
        }catch(Exception e){
            System.out.println("Error Updating kendaraan...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    public void updateJenis (Motor mt, String id_kendaraan){
        con = dbCon.makeConnection();
        
        String sql = "UPDATE `"
                + mt.getJenis_kendaraan()
                + "` SET `jumlah_tak`='"
                + mt.getJumlah_tak()
                + "' WHERE `motor`.id_kendaraan = '"
                + id_kendaraan
                + "'";
        System.out.println("Updating Jenis kendaraan...");
        
        try{
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println("Edited" + result + " kendaraan" + id_kendaraan);
            statement.close();
        }catch(Exception e){
            System.out.println("Error Updating kendaraan...");
            System.out.println(e);
        }
        dbCon.closeConnection();
    }
    
    public void update(Kendaraan k, String id_kendaraan, String jumlah_tak) {
        Motor mt = new Motor(jumlah_tak, k.getId_kendaraan(), k.getNama_kendaraan(), k.getJenis_kendaraan(), k.getHarga(), k.getGambar());
        if(cekPerubahanJenis("Motor",id_kendaraan)){
            deleteOldJenis(id_kendaraan);
            insertNewJenis(mt);
        }else{
            updateJenis((Motor) k, id_kendaraan);
        }
        super.update(k, id_kendaraan);
    }
} 
