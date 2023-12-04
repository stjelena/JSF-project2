/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import entities.Order;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
@Named(value = "prodavacManagedBean")
@RequestScoped
public class ProdavacManagedBean {

    private String name;
    private String surname;
    private LocalDate birthday;
    private List<Order> orders;
    
    public ProdavacManagedBean() {
    }
    
    @PostConstruct
    public void initData(){
        HttpSession session=SessionUtils.getSession();
        name=(String) session.getAttribute("ime");
        surname=(String) session.getAttribute("prezime");
        birthday=(LocalDate) session.getAttribute("datum_rodjenja");
        orders=new ArrayList<>();
        Connection conn = DB.getInstance().getConnection();
        try(
                Statement stmt=conn.createStatement();
                PreparedStatement stmt2=conn.prepareStatement("SELECT* FROM narudzbinasadrzi WHERE narudzbina=?");
                ResultSet rs = stmt.executeQuery("SELECT* FROM narudzbine");){
            
            while(rs.next()){
                Order order = new Order();
                stmt2.setInt(1, rs.getInt(1));
                order.setKupac(rs.getString(2));
                order.setDatumNarudzbine(rs.getDate(3).toLocalDate());
               StringBuilder proizvodi = new StringBuilder();
               boolean first = true;
               try(ResultSet rs2 = stmt2.executeQuery()){
                   while(rs2.next()){
                       if(first)
                           first=false;
                       else
                           proizvodi.append(", ");
                       proizvodi.append(rs2.getString(2));
                   }
               }
                order.setProizvodi(proizvodi.toString());
                orders.add(order);
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdavacManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
                DB.getInstance().putConnection(conn);
        }   
    }
    
    public String getNajvernijiKupac() {
        Connection conn = DB.getInstance().getConnection();
        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT kupac, COUNT(*) AS br_naruzbina FROM narudzbine GROUP BY kupac ORDER BY br_naruzbina DESC LIMIT 1");
        ) {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdavacManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.getInstance().putConnection(conn);
        }
        return "Nema narudžbina?";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
}
