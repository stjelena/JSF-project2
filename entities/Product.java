/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Jelena
 */
public class Product {
    
    private String naziv;
    private String opis;
    private int cena;
    private boolean promocija;
    private boolean kupiti;
    private int kolicina;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public boolean isPromocija() {
        return promocija;
    }

    public void setPromocija(boolean promocija) {
        this.promocija = promocija;
    }

    public boolean isKupiti() {
        return kupiti;
    }

    public void setKupiti(boolean kupiti) {
        this.kupiti = kupiti;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
    
    
    
    
}
