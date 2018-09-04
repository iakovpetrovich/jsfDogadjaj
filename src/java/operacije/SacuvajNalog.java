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
public class SacuvajNalog extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {
        Nalog n = (Nalog) objekat;
        LinkedList nalozi = DBBroker.getInstance().vratiSlozeniObjekat(n);
        if (!nalozi.isEmpty()) {
            throw  new Exception("Nalog sa datim korisničkim imenom već postoji!");
        }
    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        Nalog nalog = (Nalog) objekat;
        DBBroker.getInstance().sacuvajSlozeniObjekat(nalog);
    }
    
}
