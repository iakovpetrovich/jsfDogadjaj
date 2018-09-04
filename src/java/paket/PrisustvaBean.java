/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
import com.sun.faces.el.ELConstants;
import domen.Nalog;
import domen.Prisustvo;
import java.io.Serializable;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import kontroler.Kontroler;

/**
 *
 * @author Jaša
 */
@ManagedBean(name = "prisustvaBean")
@ViewScoped
public class PrisustvaBean implements Serializable{

    public PrisustvaBean() {
    }
    
    LinkedList<Prisustvo> prisustva = new LinkedList<Prisustvo>();
    @ManagedProperty(value="#{korisnik}")
    Nalog korisnik;
    
    public String ucitajPrisustva(Nalog nalog){
        if (!prisustva.isEmpty()) {
            prisustva.clear();
        }
        prisustva = Kontroler.getInstance().pronadjiMojaPrisustva(nalog);
        return "prisustva.xhtml";
    }
    
    @ManagedProperty("#{login}")
    LoginBean login;

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }
            
            
    
    @PostConstruct
    public void init(){
        korisnik = LoginBean.korisnik;
        prisustva = Kontroler.getInstance().pronadjiMojaPrisustva(korisnik);

    }


    public LinkedList<Prisustvo> getPrisustva() {
        return prisustva;
    }

    public void setPrisustva(LinkedList<Prisustvo> prisustva) {
        this.prisustva = prisustva;
    }

    public Nalog getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Nalog korisnik) {
        this.korisnik = korisnik;
    }
    
    public String otkazi(Prisustvo p, Nalog n){
        System.out.println("BRISEEEEEEEEM");
        Kontroler.getInstance().obrisiPriustvo(p, n);
        prisustva = Kontroler.getInstance().pronadjiMojaPrisustva(n);
        System.out.println("Otkazano");
        return null;
    }
    
    
    
}
