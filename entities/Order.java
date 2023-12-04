/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author Jelena
 */
public class Order {
    
    private String kupac;
    private LocalDate datumNarudzbine;
    private String proizvodi;

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
        this.kupac = kupac;
    }

    public LocalDate getDatumNarudzbine() {
        return datumNarudzbine;
    }

    public void setDatumNarudzbine(LocalDate datumNarudzbine) {
        this.datumNarudzbine = datumNarudzbine;
    }

    public String getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(String proizvodi) {
        this.proizvodi = proizvodi;
    }
    
    
    
    
}
