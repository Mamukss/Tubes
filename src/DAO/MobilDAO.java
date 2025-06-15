/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Statement;
import Model.Kendaraan;
import Model.Mobil;
import Interface_DAO.IKendaraanDAO;
import java.util.List;

public class MobilDAO extends KendaraanDAO implements IKendaraanDAO{

    public void insert(Mobil mb) {
        super.insert(mb);
        insertNewJenis(mb);
    }
 
    public void insertNewJenis(Mobil mb) {
        con = dbCon.makeConnection();

        String sql = 
                "INSERT INTO `mobil`(`id_kendaraan`, `jenis_mesin`) VALUES ('"
                + mb.getId_kendaraan()
                + "','"
                + mb.getSpecial()
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
        String sql = "DELETE FROM `motor` WHERE `id_kendaraan` = '"+id_kendaraan+"'";
        System.out.println("Deleting Mobil...");
        
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
    
    public void updateJenis (Mobil mb, String id_kendaraan){
        con = dbCon.makeConnection();
        
        String sql = "UPDATE `"
                + mb.getJenis_kendaraan()
                + "` SET `jenis_mesin`='"
                + mb.getJenis_mesin()
                + "' WHERE `mobil`.id_kendaraan = '"
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
    
    public void update(Kendaraan k, String id_kendaraan, String jenis_mesin) {
        Mobil mb = new Mobil(jenis_mesin, k.getId_kendaraan(), k.getNama_kendaraan(), k.getJenis_kendaraan(), k.getHarga(), k.getGambar());
        if(cekPerubahanJenis("Mobil",id_kendaraan)){
            deleteOldJenis(id_kendaraan);
            insertNewJenis(mb);
        }else{
            updateJenis((Mobil) k, id_kendaraan);
        }
        super.update(k, id_kendaraan);
    }
} 

