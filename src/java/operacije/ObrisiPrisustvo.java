/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import dbb.DBBroker;
import domen.Nalog;
import domen.OpstiDomenskiObjekat;
import domen.Prisustvo;
import java.util.LinkedList;

/**
 *
 * @author Jaša
 */
public class ObrisiPrisustvo extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {
        
    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        LinkedList<OpstiDomenskiObjekat> prisustvoINalog =  (LinkedList<OpstiDomenskiObjekat>) objekat;
        Prisustvo p = (Prisustvo) prisustvoINalog.get(0);
        Nalog n = (Nalog) prisustvoINalog.get(1);
        if (!n.getIme().equalsIgnoreCase(p.getKorisnik().getIme())) {
            throw new Exception();
        }
        DBBroker.getInstance().obrisi(p);
        
    }
    
}
