/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author yohan
 */
import DAO.PembelianDAO;
import interface_Control.IShowTableBySearch;
import java.util.List;
import Model.PembelianKendaraan;
import Table.TabelPembelianKendaraan;
import interface_Control.IPembelianControl;


public class PembelianControl implements IPembelianControl, IShowTableBySearch<TabelPembelianKendaraan, String>{
    PembelianDAO pDao = new PembelianDAO();
    
    public void insertDataPembelian(List<PembelianKendaraan> pesananList) {
       for (PembelianKendaraan pembelianKendaraan : pesananList) {
           pDao.insert(pembelianKendaraan);
       }
    } 
    public void updateDataPembelian(PembelianKendaraan pk){
        pDao.update(pk, pk.getId_pembelian(), pk.getId_kendaraan());
    } // update isi?
    
    public void deleteDataPembelian(String idPembelian, String idKendaraan){
        pDao.delete(idPembelian, idKendaraan);
    } // menghapus karyawan di database berdasarkan id
    
    public TabelPembelianKendaraan showTableBySearch(String target){
        List<PembelianKendaraan> data = pDao.showData(target);
        return new TabelPembelianKendaraan(data);
    }

    

} 