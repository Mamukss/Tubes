/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.TransaksiDAO;
import interface_Control.ICRUDControl;
import interface_Control.IShowTableBySearch;
import java.util.List;
import Model.Transaksi;
import Table.TabelTransaksi;

/**
 *
 * @author Tok Se Ka 220711904
 */
public class TransaksiControl implements ICRUDControl<Transaksi, String>, IShowTableBySearch<TabelTransaksi, String> {
    private TransaksiDAO kDao;
    
    public TransaksiControl(TransaksiDAO tDao) {
        this.kDao = tDao;
    }
    
    @Override
    public String generateId() {
        return "T" + kDao.generateId();
    }

    @Override
    public void insert(Transaksi transaksi) {
        transaksi.setId_pembelian(generateId());
        kDao.insert(transaksi);
    }

    @Override
    public void update(Transaksi transaksi) {
        kDao.update(transaksi, transaksi.getId_pembelian());
    }

    @Override
    public void delete(String id) {
        kDao.delete(id);
    }

    @Override
    public TabelTransaksi showTableBySearch(String search) {
        List<Transaksi> data = kDao.showData(search);
        return new TabelTransaksi(data);
    }

    public void createReceipt(String id_pembelian){
        kDao.createReceipt(id_pembelian);
    }
    
    
    public void insertTotalHarga(Transaksi transaksi) {
        kDao.updateHarga(transaksi);
    }
    
    public TabelTransaksi showTableByTanggal(String tanggalMulai, String tanggalSelesai){
        List<Transaksi> data = kDao.showDatabyTanggal(tanggalMulai, tanggalSelesai);
        return new TabelTransaksi(data);
    }
    
    public String cariMenuTerlaris(){
        return kDao.cariNamaMenuTerlaris() + " (Terjual " + kDao.cariJumlahProdukTerlaris() +" Item)";
    }
    
    public double hitungTotalOmset(){
        return kDao.hitungTotalOmset();
    }
    
    public int hitungTotalTransaksi(){
        return kDao.hitungTotalTransaksi();
    }
}
