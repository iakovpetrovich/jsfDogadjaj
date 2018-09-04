/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import dbb.DBBroker;
import domen.Aktivnost;
import domen.Dogadjaj;
import domen.Nalog;
import domen.OpstiDomenskiObjekat;
import java.util.LinkedList;

/**
 *
 * @author Jaša
 */
public class ObrisiDogadjaj extends OpstaSistemskaOperacija {

    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {
        LinkedList<OpstiDomenskiObjekat> dogadjajINalog = (LinkedList<OpstiDomenskiObjekat>) objekat;
        Dogadjaj dogadjaj = (Dogadjaj) dogadjajINalog.get(0);
        Nalog kreator = (Nalog) DBBroker.getInstance().vratiJednoznacno(dogadjaj.getKreator());
        Nalog korisnik = (Nalog) dogadjajINalog.get(1);
        System.out.println(kreator.getIme()+" k "+ kreator.getLozinka()+ " ulog "+korisnik.getIme()+ " "+korisnik.getLozinka());
        if (!korisnik.equals(kreator)) {
            throw new Exception("Korisnik nije autorizovan da ukloni tuđ događaj.");
        }
        
    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        LinkedList<OpstiDomenskiObjekat> dogadjajINalog = (LinkedList<OpstiDomenskiObjekat>) objekat;
        Dogadjaj dogadjaj = (Dogadjaj) dogadjajINalog.get(0);
        dogadjaj = (Dogadjaj) DBBroker.getInstance().vratiJednoznacno(dogadjaj);
        Aktivnost a = new Aktivnost(dogadjaj, 0, "", "");
        LinkedList<OpstiDomenskiObjekat> listaStavki = DBBroker.getInstance().vratiSlozeniObjekat(a);
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaStavki) {
            Aktivnost aktivnost = (Aktivnost) opstiDomenskiObjekat;
            DBBroker.getInstance().obrisi(aktivnost);
        }
        DBBroker.getInstance().obrisi(dogadjaj);
    }

}
