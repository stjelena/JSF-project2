/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import entities.Product;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;
import util.SessionUtils;

/**
 *
 * @author Jelena
 */
@Named(value = "kupacManagedBean")
@SessionScoped
public class KupacManagedBean implements Serializable{
    
    private List<Product> allProducts;

    public KupacManagedBean() {
    }
    
    @PostConstruct
    public void initData(){
        allProducts = new ArrayList<>();
        Connection conn = DB.getInstance().getConnection();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT* FROM proizvodi")){
        while(rs.next()){
            Product p = new Product();
            p.setNaziv(rs.getString("naziv"));
            p.setOpis(rs.getString("opis"));
            p.setCena(rs.getInt("cena"));
            p.setPromocija(rs.getBoolean("promocija"));
            allProducts.add(p);
        }
        }catch (SQLException ex) {
            Logger.getLogger(KupacManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
    public void remove(Product p){
        p.setKolicina(0);
        p.setKupiti(false);
    }
    
    public String order(){
        Connection conn=DB.getInstance().getConnection();
        try(PreparedStatement stmt=conn.prepareStatement("INSERT INTO narudzbine(kupac, datum) VALUES(?, NOW())"
                , Statement.RETURN_GENERATED_KEYS);
                PreparedStatement stmt2=conn.prepareStatement("INSERT INTO narudzbinasadrzi(narudzbina, proizvod, kolicina) VALUES(?,?,?)")){
            
            stmt.setString(1, (String) SessionUtils.getSession().getAttribute("kor_ime"));
            stmt.executeUpdate();
            try(ResultSet rs=stmt.getGeneratedKeys()){
                if(rs.next()){
                    stmt2.setInt(1, rs.getInt(1));
                }else{
                    return null;
                }
            }
            for (Product p:allProducts){
                if(p.getKolicina()<=0 || !p.isKupiti())
                    continue;
                stmt2.setString(2, p.getNaziv());
                stmt2.setInt(3, p.getKolicina());
                stmt2.executeUpdate();
                p.setKupiti(false);
                p.setKolicina(0);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(KupacManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
        return "kupac?faces=redirected=true";
    }
    

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }
    
    
    
}
