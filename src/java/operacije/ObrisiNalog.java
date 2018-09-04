/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import dbb.DBBroker;
import domen.Dogadjaj;
import domen.Nalog;
import java.util.LinkedList;

/**
 *
 * @author Jaša
 */
public class ObrisiNalog extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {
        Nalog n = (Nalog) objekat;
        Dogadjaj d = new Dogadjaj("", "", "", null, "", n, null, null);
        LinkedList dogadjaji = DBBroker.getInstance().vratiSlozeniObjekat(d);
        if (!dogadjaji.isEmpty()) {
            throw  new Exception("Nemoguće izbrisati nalog, pre brisanja dogadjaja");
        }
    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        Nalog nalog = (Nalog) objekat;
        DBBroker.getInstance().obrisi(nalog);
    }
    
}
