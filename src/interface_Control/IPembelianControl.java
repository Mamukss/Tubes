/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_Control;

import java.util.List;
import Model.PembelianKendaraan;

/**
 *
 * @author yobel
 */
public interface IPembelianControl {
    void insertDataPembelian(List<PembelianKendaraan> PembelianKendaraanList);
    void updateDataPembelian(PembelianKendaraan pembelianKendaraan);
    void deleteDataPembelian(String id_pembelian, String id_kendaraan);
}
