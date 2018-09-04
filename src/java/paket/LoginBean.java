/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import domen.Dogadjaj;
import domen.Nalog;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import kontroler.Kontroler;

/**
 *
 * @author Jaša
 */
@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class LoginBean implements Serializable{

    public LoginBean() {
    }
    
    @ManagedProperty(value= "#{korisnik}")
    public static Nalog korisnik;
    
    public Dogadjaj dogadjaj;

    public Nalog getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Nalog korisnik) {
        this.korisnik = korisnik;
    }
    
    public String prijaviSe(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String korIme = ec.getRequestParameterMap().get("loginForma:korIme");
        String lozinka = ec.getRequestParameterMap().get("loginForma:lozinka");
        Nalog n = new Nalog(korIme, lozinka, "", null);
        korisnik = Kontroler.getInstance().pristupi(n);
        if (korisnik == null) {
            return "login.xhtml?faces-redirect=true";
        }
        return null;
    }
    
    public String odjava(){
        korisnik = null;
        return "login.xhtml?faces-redirect=true";
    }

    public Dogadjaj getDogadjaj() {
        return dogadjaj;
    }

    public void setDogadjaj(Dogadjaj dogadjaj) {
        this.dogadjaj = dogadjaj;
    }
    
    
}
