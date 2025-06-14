/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author yohan
 */
import DAO.TransaksiDAO;
import java.util.List;
import Model.Transaksi;

public class TransaksiControl {
    private TransaksiDAO transaksiDAO = new TransaksiDAO();

    public void insertTransaksi(Transaksi t) {
        transaksiDAO.insert(t);
    }

    public void updateTransaksi(Transaksi t, String noResi) {
        transaksiDAO.update(t, noResi);
    }

    public void deleteTransaksi(String noResi) {
        transaksiDAO.delete(noResi);
    }

    public List<Transaksi> getTransaksiList() {
        return transaksiDAO.showDataList();
    }

    public List<Transaksi> searchTransaksi(String keyword) {
        return transaksiDAO.showData(keyword);
    }

    public int generateId() {
        return transaksiDAO.generateId();
    }
} 
