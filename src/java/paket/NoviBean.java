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
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;
import kontroler.Kontroler;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Jaša
 */
@ManagedBean
@ViewScoped
public class NoviBean implements Serializable {

    public NoviBean() {
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
    private String nazivA;
    private String opisA;
    boolean zapamcen = false;

    UploadedFile uploadedFile;

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

    public void dodajAktivnost() {
        Aktivnost ak = new Aktivnost(null, 0, nazivA, opisA);
        spisakAktivnosti.add(ak);
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
    }

    public void zapamti() {
        for (Mesto mesto1 : listaMesta) {
            if (mesto1.getPtt().equalsIgnoreCase(ptt)) {
                mesto = mesto1;
                System.out.println("NASAO MESTO");
            }
        }

        Dogadjaj d = new Dogadjaj(null, naziv, opis, datum, adresa, LoginBean.korisnik, spisakAktivnosti, mesto);
        for (Aktivnost aktivnost : spisakAktivnosti) {
            aktivnost.setDogadjaj(d);
        }
        if (uploadedFile == null) {
            System.out.println("NUL???");
        }
        if (uploadedFile != null) {
            try {
                Path folder = Paths.get("C:\\Users\\Jaša\\Documents\\apache-tomcat-7.0.79-windows-x64\\apache-tomcat-7.0.79\\webapps\\foto");
                String filename = uploadedFile.getFileName();
                
                
                Path file = Files.createTempFile(folder, "SL", filename);
                InputStream input = uploadedFile.getInputstream();
                Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
                String fpath = file.toString();
                System.out.println(file.toString());
                int presek = fpath.indexOf("\\foto");
                String name = fpath.substring(presek);
                System.out.println(name);
                name = "\\"+name;
                String sl = fpath.substring(fpath.indexOf("\\SL"));
                sl = "\\\\foto\\"+sl;
                System.out.println("SLIKA:"+sl);
                d.setSlika(sl);
                System.out.println(file.toString());
                System.out.println("Sacuvao sliku.");
            } catch (IOException ex) {
                Logger.getLogger(NoviBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        zapamcen = Kontroler.getInstance().zapamtiDogadjaj(d);
    }

    public String getPtt() {
        return ptt;
    }

    public void setPtt(String ptt) {
        this.ptt = ptt;
    }

    public String getNazivA() {
        return nazivA;
    }

    public void setNazivA(String nazivA) {
        this.nazivA = nazivA;
    }

    public String getOpisA() {
        return opisA;
    }

    public void setOpisA(String opisA) {
        this.opisA = opisA;
    }

    public boolean isZapamcen() {
        return zapamcen;
    }

    public void setZapamcen(boolean zapamcen) {
        this.zapamcen = zapamcen;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

}
