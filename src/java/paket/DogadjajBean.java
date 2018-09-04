/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import domen.Dogadjaj;
import domen.OpstiDomenskiObjekat;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import kontroler.Kontroler;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jaša
 */
@ManagedBean(name = "dogadjajBean",eager = true)
@ViewScoped
public class DogadjajBean implements Serializable {

    public DogadjajBean() {
        popuniListuDogadjaja();
        
    }

    LinkedList<domen.Dogadjaj> dogadjaji = new LinkedList<domen.Dogadjaj>();

    @ManagedProperty(value = "#{uneteReci}")
    private String uneteReci;
    
    @ManagedProperty(value = "#{login}")
    private LoginBean login;
    

  
    private Dogadjaj odabrani;
    
    
    

    public final void popuniListuDogadjaja() {
        LinkedList<OpstiDomenskiObjekat> lista = Kontroler.getInstance().vratiNajskorije();
        if (!dogadjaji.isEmpty()) {
            dogadjaji.clear();
        }
        if(lista == null) {
        Dogadjaj dog = new Dogadjaj(null, null, null, new Date(2000, 1, 1), null, null, null, null);
        dogadjaji = Kontroler.getInstance().ponadjiDogadjaje(dog);
        return;
        }
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            domen.Dogadjaj d = (domen.Dogadjaj) opstiDomenskiObjekat;
            dogadjaji.add(d);
        }
    }

    public LinkedList<domen.Dogadjaj> getDogadjaji() {
        return dogadjaji;
    }

    public void setDogadjaji(LinkedList<domen.Dogadjaj> dogadjaji) {
        this.dogadjaji = dogadjaji;
    }

    public String getUneteReci() {
        return uneteReci;
    }

    public void setUneteReci(String uneteReci) {
        this.uneteReci = uneteReci;
    }

    public String pronadiDogadjaje() {
        Dogadjaj d = new Dogadjaj(null, null, uneteReci.trim(), null, null, null, null, null);
        dogadjaji = Kontroler.getInstance().ponadjiDogadjaje(d);
        return null;
    }

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public Dogadjaj getOdabrani() {
        return odabrani;
    }

    public void setOdabrani(Dogadjaj odabrani) {
        this.odabrani = odabrani;
    }
    
    
    public String postavi(Dogadjaj d){
        odabrani = d;
        return "detalji.xhtml";
    }



    public final void vratiMoje() {
        LinkedList<OpstiDomenskiObjekat> lista = Kontroler.getInstance().pronadjiMojeDogadjaje(LoginBean.korisnik);
        if (!dogadjaji.isEmpty()) {
            dogadjaji.clear();
        }
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
            domen.Dogadjaj d = (domen.Dogadjaj) opstiDomenskiObjekat;
            dogadjaji.add(d);
        }
        
    }

    
}
