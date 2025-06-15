/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import DAO.TruckDAO;
import interface_Control.ICRUDControl;
import interface_Control.IShowTableBySearch;
import java.util.ArrayList;
import java.util.List;
import Model.Truck;
import Model.Kendaraan;
import Table.TabelTruck;


public class TruckControl extends KendaraanControl<Truck> implements ICRUDControl<Truck, String>, IShowTableBySearch<TabelTruck, String>{
    private TruckDAO tkDao;
    
    public TruckControl(TruckDAO tDao) {
        super(tDao);
        this.tkDao = tDao; // Inisialisasi mkDao
    }
    
    @Override
    protected boolean cekJenis(Kendaraan kendaraan) {
        return kendaraan instanceof Truck;
    }

    public void insert(Truck tk) {
        tk.setId_kendaraan(generateId());
        tkDao.insert(tk);  // Tidak perlu casting, karena mkDao sudah bertipe MakananDAO
    }

    @Override
    public void update(Truck tk) {
        tkDao.update(tk, tk.getId_kendaraan(), tk.getJenis_roda());  // Tidak perlu casting, karena mkDao sudah bertipe MakananDAO
    }

    @Override
    public TabelTruck showTableBySearch(String search) {
        List<Kendaraan> data = tkDao.showData(search);
        List<Truck> temp = new ArrayList<>();
        for (Kendaraan kendaraan : data) {
            if (kendaraan.getJenis_kendaraan().equals("Truck") && cekJenis(kendaraan)) {
                temp.add((Truck) kendaraan);
                System.out.println("Adding Truck");
            }
        }
        return new TabelTruck(temp);
    }

    
    public List<Truck> showListKendaraan() {
        List<Kendaraan> data = tkDao.showDataList();
        List<Truck> temp = new ArrayList<>();
        for (Kendaraan kendaraan : data) {
            if (cekJenis(kendaraan)) {
                temp.add((Truck) kendaraan);
            }
        }
        return temp;
    }
}


