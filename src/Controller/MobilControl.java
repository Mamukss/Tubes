/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author yohan
 */
import DAO.MobilDAO;
import interface_Control.ICRUDControl;
import interface_Control.IShowTableBySearch;
import java.util.ArrayList;
import java.util.List;
import Model.Mobil;
import Model.Kendaraan;
import Table.TabelMobil;


public class MobilControl extends KendaraanControl<Mobil> implements ICRUDControl<Mobil, String>, IShowTableBySearch<TabelMobil, String>{
    private MobilDAO mbDao;
    
    public MobilControl(MobilDAO mDao) {
        super(mDao);
        this.mbDao = mDao; // Inisialisasi mkDao
    }
    
    @Override
    protected boolean cekJenis(Kendaraan kendaraan) {
        return kendaraan instanceof Mobil;
    }

    public void insert(Mobil mb) {
        mb.setId_kendaraan(generateId());
        mbDao.insert(mb);  // Tidak perlu casting, karena mkDao sudah bertipe MakananDAO
    }

    @Override
    public void update(Mobil mb) {
        mbDao.update(mb, mb.getId_kendaraan(), mb.getJenis_mesin());  // Tidak perlu casting, karena mkDao sudah bertipe MakananDAO
    }

    @Override
    public TabelMobil showTableBySearch(String search) {
        List<Kendaraan> data = mbDao.showData(search);
        List<Mobil> temp = new ArrayList<>();
        for (Kendaraan kendaraan : data) {
            if (kendaraan.getJenis_kendaraan().equals("Mobil") && cekJenis(kendaraan)) {
                temp.add((Mobil) kendaraan);
                System.out.println("Adding Mobil");
            }
        }
        return new TabelMobil(temp);
    }

    
    public List<Mobil> showListKendaraan() {
        List<Kendaraan> data = mbDao.showDataList();
        List<Mobil> temp = new ArrayList<>();
        for (Kendaraan kendaraan : data) {
            if (cekJenis(kendaraan)) {
                temp.add((Mobil) kendaraan);
            }
        }
        return temp;
    }
}
