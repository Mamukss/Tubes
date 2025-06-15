/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.MotorDAO;
import interface_Control.ICRUDControl;
import interface_Control.IShowTableBySearch;
import java.util.ArrayList;
import java.util.List;
import Model.Motor;
import Model.Kendaraan;
import Table.TabelMotor;


public class MotorControl extends KendaraanControl<Motor> implements ICRUDControl<Motor, String>, IShowTableBySearch<TabelMotor, String>{
    private MotorDAO mtDao;
    
    public MotorControl(MotorDAO mDao) {
        super(mDao);
        this.mtDao = mDao; // Inisialisasi mkDao
    }
    
    @Override
    protected boolean cekJenis(Kendaraan kendaraan) {
        return kendaraan instanceof Motor;
    }

    public void insert(Motor mt) {
        mt.setId_kendaraan(generateId());
        mtDao.insert(mt);  // Tidak perlu casting, karena mkDao sudah bertipe MakananDAO
    }

    @Override
    public void update(Motor mt) {
        mtDao.update(mt, mt.getId_kendaraan(), mt.getJumlah_tak());  // Tidak perlu casting, karena mkDao sudah bertipe MakananDAO
    }

    @Override
    public TabelMotor showTableBySearch(String search) {
        List<Kendaraan> data = mtDao.showData(search);
        List<Motor> temp = new ArrayList<>();
        for (Kendaraan kendaraan : data) {
            if (kendaraan.getJenis_kendaraan().equals("Motor") && cekJenis(kendaraan)) {
                temp.add((Motor) kendaraan);
                System.out.println("Adding Motor");
            }
        }
        return new TabelMotor(temp);
    }

    
    public List<Motor> showListKendaraan() {
        List<Kendaraan> data = mtDao.showDataList();
        List<Motor> temp = new ArrayList<>();
        for (Kendaraan kendaraan : data) {
            if (cekJenis(kendaraan)) {
                temp.add((Motor) kendaraan);
            }
        }
        return temp;
    }
}


