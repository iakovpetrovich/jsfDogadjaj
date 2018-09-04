/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import domen.Aktivnost;
import domen.Dogadjaj;
import domen.Mesto;
import domen.Nalog;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import kontroler.Kontroler;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Jaša
 */
@ManagedBean
@ViewScoped
public class IzmeniBean implements Serializable {

    public IzmeniBean() {
    }

    private String naziv;
    private String opis;
    private Date datum;
    private String adresa;
    private Nalog kreator;
    private LinkedList<Aktivnost> spisakAktivnosti;
    private Mesto mesto;
    private LinkedList<Mesto> listaMesta = new LinkedList<Mesto>();
    private String ptt;
    String idd;

    boolean zapamcen = false;

    @ManagedProperty(value = "#{loginBean}")
    LoginBean log;

    public LoginBean getLog() {
        return log;
    }

    public void setLog(LoginBean log) {
        this.log = log;
    }

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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Nalog getKreator() {
        return kreator;
    }

    public void setKreator(Nalog kreator) {
        this.kreator = kreator;
    }

    public LinkedList<Aktivnost> getSpisakAktivnosti() {
        return spisakAktivnosti;
    }

    public void setSpisakAktivnosti(LinkedList<Aktivnost> spisakAktivnosti) {
        this.spisakAktivnosti = spisakAktivnosti;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public void obrisiAktivnost(Aktivnost a) {
        spisakAktivnosti.remove(a);
    }

    public LinkedList<Mesto> getListaMesta() {
        return listaMesta;
    }

    public void setListaMesta(LinkedList<Mesto> listaMesta) {
        this.listaMesta = listaMesta;
    }

    @PostConstruct
    private final void popuniMesta() {
        listaMesta = Kontroler.getInstance().ucitajMesta();
        spisakAktivnosti = new LinkedList<Aktivnost>();
        Dogadjaj d = Kontroler.getInstance().prikaziDogadjaj(log.getDogadjaj());
        naziv = d.getNaziv();
        opis = d.getOpis();
        mesto = d.getMesto();
        datum = d.getDatum();
        adresa = d.getAdresa();
        ptt = d.getMesto().getPtt();
        idd = d.getIDD();
    }

    public String getPtt() {
        return ptt;
    }

    public void setPtt(String ptt) {
        this.ptt = ptt;
    }

    public boolean isZapamcen() {
        return zapamcen;
    }

    public void setZapamcen(boolean zapamcen) {
        this.zapamcen = zapamcen;
    }

    public void izmeni() {
        for (Mesto mesto1 : listaMesta) {
            if (mesto1.getPtt().equalsIgnoreCase(ptt)) {
                mesto = mesto1;
                System.out.println("NASAO MESTO");
            }
        }
        Dogadjaj d = new Dogadjaj(idd, naziv, opis, datum, adresa, kreator, null, mesto);
        boolean b = Kontroler.getInstance().izmeniDogadjaj(d);
        zapamcen = true;
    }

}
