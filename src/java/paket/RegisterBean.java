/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import domen.Mesto;
import domen.Nalog;
import java.io.Serializable;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import kontroler.Kontroler;

/**
 *
 * @author Jaša
 */
@ManagedBean(name = "registerBean")
@ViewScoped
public class RegisterBean implements Serializable {

    private String ime;
    private String lozinka;
    private String telefon;
    private Mesto mesto;
    private String poruka;
    private String ptt;
    private LinkedList<Mesto> listaMesta = new LinkedList<Mesto>();
    boolean ok = false;

    public RegisterBean() {
        popuniMesta();
    }

    @ManagedProperty(value = "#{loginBean}")
    LoginBean log;

    public LoginBean getLog() {
        return log;
    }

    public void setLog(LoginBean log) {
        this.log = log;
    }

    @PostConstruct
    public void init() {
        if (log.getKorisnik() != null) {
            ime = log.getKorisnik().getIme();
            telefon = log.getKorisnik().getTelefon();
        }

    }

    private final void popuniMesta() {
        listaMesta = Kontroler.getInstance().ucitajMesta();
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public LinkedList<Mesto> getListaMesta() {
        return listaMesta;
    }

    public void setListaMesta(LinkedList<Mesto> listaMesta) {
        this.listaMesta = listaMesta;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String registrujSe() {
        for (Mesto mesto1 : listaMesta) {
            if (mesto1.getPtt().equalsIgnoreCase(ptt)) {
                mesto = mesto1;
                System.out.println("NASAO MESTO");
            }
        }
        Nalog n = new Nalog(ime, lozinka, telefon, mesto);
        if (Kontroler.getInstance().sacuvajNalog(n)) {
            poruka = "Uspešna registracija, prijavite se!";
            ok = true;
        } else {
            poruka = "Korisničko ime je zauzeto, unestite drugo!";
        }
        return null;
    }

    public String getPtt() {
        return ptt;
    }

    public void setPtt(String ptt) {
        this.ptt = ptt;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String obrisi() {
        Kontroler.getInstance().obrisiNalog(log.getKorisnik());
        poruka = "Nalog obrisan!";
        log.odjava();
        ime = "";
        telefon = "";
        return "";
    }

}
