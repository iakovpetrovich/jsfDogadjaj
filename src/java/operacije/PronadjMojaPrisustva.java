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
public class PronadjMojaPrisustva extends OpstaSistemskaOperacija{

    LinkedList<OpstiDomenskiObjekat> prisustva;
    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {
        
    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        Nalog n = (Nalog) objekat;
        Prisustvo p = new Prisustvo(n, null, 0, "");
        prisustva = DBBroker.getInstance().vratiSlozeniObjekat(p);
    }

    public LinkedList<OpstiDomenskiObjekat> getPrisustva() {
        return prisustva;
    }
    
    
}
