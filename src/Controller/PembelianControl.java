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
import java.util.List;
import Model.PembelianKendaraan;

public class PembelianControl {
    private PembelianDAO pembelianDAO = new PembelianDAO();

    public void insertPembelian(PembelianKendaraan p) {
        pembelianDAO.insert(p);
    }

    public void updatePembelian(PembelianKendaraan p, String noResi) {
        pembelianDAO.update(p, noResi);
    }

    public void deletePembelian(String noResi) {
        pembelianDAO.delete(noResi);
    }

    public List<PembelianKendaraan> getPembelianList() {
        return pembelianDAO.showDataList();
    }

    public List<PembelianKendaraan> searchPembelian(String keyword) {
        return pembelianDAO.showData(keyword);
    }
} 