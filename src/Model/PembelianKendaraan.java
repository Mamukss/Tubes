/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author yohan
 */
public class PembelianKendaraan {
    private String noResi;
    private String idCustomer;
    private String idKendaraan;
    private int jumlah;
    private String tambahan;
    private String metodePembayaran;
    private float subTotal;

    public PembelianKendaraan(String noResi, String idCustomer, String idKendaraan, int jumlah,
                               String tambahan, String metodePembayaran, float subTotal) {
        this.noResi = noResi;
        this.idCustomer = idCustomer;
        this.idKendaraan = idKendaraan;
        this.jumlah = jumlah;
        this.tambahan = tambahan;
        this.metodePembayaran = metodePembayaran;
        this.subTotal = subTotal;
    }

    public void setNoResi(String noResi) {
        this.noResi = noResi;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setIdKendaraan(String idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setTambahan(String tambahan) {
        this.tambahan = tambahan;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public String getNoResi() {
        return noResi;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public String getIdKendaraan() {
        return idKendaraan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getTambahan() {
        return tambahan;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public float getSubTotal() {
        return subTotal;
    }
    
    
}
