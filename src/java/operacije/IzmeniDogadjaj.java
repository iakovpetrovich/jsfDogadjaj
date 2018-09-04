/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import dbb.DBBroker;
import domen.Aktivnost;
import domen.Dogadjaj;
import domen.OpstiDomenskiObjekat;
import java.util.LinkedList;

/**
 *
 * @author Jaša
 */
public class IzmeniDogadjaj extends OpstaSistemskaOperacija{

    Dogadjaj dogadjaj;
    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {
        
    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        dogadjaj = (Dogadjaj) objekat;
        dogadjaj = (Dogadjaj) DBBroker.getInstance().izmeni(dogadjaj);
        Aktivnost a = new Aktivnost(dogadjaj, 0, "", "");
        LinkedList<OpstiDomenskiObjekat> listaStavki = DBBroker.getInstance().vratiSlozeniObjekat(a);
        LinkedList<Aktivnost> listaAktivnosti = new LinkedList<Aktivnost>();

        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaStavki) {
            Aktivnost aktivnost = (Aktivnost) opstiDomenskiObjekat;
            listaAktivnosti.add(aktivnost);
        }

        dogadjaj.setSpisakAktivnosti(listaAktivnosti);
    }

    public Dogadjaj getDogadjaj() {
        return dogadjaj;
    }
    
    
    
}
