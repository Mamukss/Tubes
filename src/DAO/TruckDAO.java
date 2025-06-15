/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Statement;
import Model.Kendaraan;
import Model.Truck;
import Interface_DAO.IKendaraanDAO;
import java.util.List;

public class TruckDAO extends KendaraanDAO implements IKendaraanDAO{

    public void insert(Truck tk) {
        super.insert(tk);
        insertNewJenis(tk);
    }
 
    public void insertNewJenis(Truck tk) {
        con = dbCon.makeConnection();

        String sql = 
                "INSERT INTO `truck`(`id_kendaraan`, `jenis_roda`) VALUES ('"
                + tk.getId_kendaraan()
                + "','"
                + tk.getSpecial()
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
        String sql = "DELETE FROM `Truck` WHERE `id_kendaraan` = '"+id_kendaraan+"'";
        System.out.println("Deleting Truck...");
        
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
    
    public void updateJenis (Truck tk, String id_kendaraan){
        con = dbCon.makeConnection();
        
        String sql = "UPDATE `"
                + tk.getJenis_kendaraan()
                + "` SET `jenis_roda`='"
                + tk.getJenis_roda()
                + "' WHERE `truck`.id_kendaraan = '"
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
    
    public void update(Kendaraan k, String id_kendaraan, String jenis_roda) {
        Truck tk = new Truck(jenis_roda, k.getId_kendaraan(), k.getNama_kendaraan(), k.getJenis_kendaraan(), k.getHarga(), k.getGambar());
        if(cekPerubahanJenis("Truck",id_kendaraan)){
            deleteOldJenis(id_kendaraan);
            insertNewJenis(tk);
        }else{
            updateJenis((Truck) k, id_kendaraan);
        }
        super.update(k, id_kendaraan);
    }
} 

