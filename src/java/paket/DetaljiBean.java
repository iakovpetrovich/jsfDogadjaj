/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import domen.Aktivnost;
import domen.Dogadjaj;
import domen.Nalog;
import domen.Prisustvo;
import java.io.Serializable;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;


import javax.faces.bean.ViewScoped;


import kontroler.Kontroler;

/**
 *
 * @author Jaša
 */
@ManagedBean(name = "detaljiBean")
@ViewScoped
public class DetaljiBean implements Serializable{
    
    private Dogadjaj dogadjaj;
    private int brojGostiju = 1;
    private String zahtev;
    private boolean dolazakPotvrdjen = false;
    private String poruka;
    private boolean uspeh=false;
    private boolean neuspeh = false;
    
    
    public DetaljiBean() {
        System.out.println("NOVI ILI NE ___________________________________________________");
    }
    
    @ManagedProperty(value = "#{loginBean}")
    LoginBean loginBea;

    public LoginBean getLoginBea() {
        
        return loginBea;
    }

    public void setLoginBea(LoginBean loginBea) {
        this.loginBea = loginBea;
    }

    @PostConstruct
    public void init(){
    dogadjaj = Kontroler.getInstance().prikaziDogadjaj(loginBea.dogadjaj);
        System.out.println("slika:"+dogadjaj.getSlika());
        System.out.println("opis:"+dogadjaj.getOpis());
        
    }

    
    public Dogadjaj getDogadjaj() {
        return dogadjaj;
    }

    public void setDogadjaj(Dogadjaj dogadjaj) {
        System.out.println("Set dogadjaj "+dogadjaj.getNaziv());
        this.dogadjaj = dogadjaj;
    }

    public int getBrojGostiju() {
        return brojGostiju;
    }

    public void setBrojGostiju(int brojGostiju) {
        this.brojGostiju = brojGostiju;
    }

    public String getZahtev() {
        return zahtev;
    }

    public void setZahtev(String zahtev) {
        this.zahtev = zahtev;
    }


    public boolean isDolazakPotvrdjen() {
        return dolazakPotvrdjen;
    }

    public void setDolazakPotvrdjen(boolean dolazakPotvrdjen) {
        this.dolazakPotvrdjen = dolazakPotvrdjen;
    }
    

    public String prisustvuj(Nalog n,Dogadjaj d){
       
        System.out.println("------------SACE VIDIMO DALCE BACI EXCEPTION");
        
        Prisustvo p = new Prisustvo(n, this.dogadjaj, brojGostiju, zahtev);
        System.out.println("Kreirao prisustvo____________________________");
        try {
            poruka =  Kontroler.getInstance().sacuvajPrisustvo(p);
            uspeh = true;
            System.out.println(poruka+" PPPPPPPPPPPPPPPP");
            dolazakPotvrdjen = true;         
        } catch (Exception ex) {
            Logger.getLogger(DetaljiBean.class.getName()).log(Level.SEVERE, null, ex);
            poruka = ex.getMessage();
            neuspeh = true;
            System.out.println(poruka+" PPPPPPPPPPPPPPPP");
            
        }
        return null;
    }
    



    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public boolean isUspeh() {
        return uspeh;
    }

    public void setUspeh(boolean uspeh) {
        this.uspeh = uspeh;
    }

    public boolean isNeuspeh() {
        return neuspeh;
    }

    public void setNeuspeh(boolean neuspeh) {
        this.neuspeh = neuspeh;
    }
    
    public String obrisi(){
        Kontroler.getInstance().obrisiDogadjaj(loginBea.dogadjaj, LoginBean.korisnik);
        return "dogadjaj.xhtml?faces-redirect=true";
    }
    
    
}
