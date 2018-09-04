/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import dbb.DBBroker;
import domen.Dogadjaj;
import domen.Nalog;
import domen.OpstiDomenskiObjekat;
import java.util.LinkedList;

/**
 *
 * @author Jaša
 */
public class PronadjiMojeDogadjaje extends OpstaSistemskaOperacija{

    LinkedList<OpstiDomenskiObjekat> dogadjaji;
    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {
        
    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        Nalog n = (Nalog) objekat;
        Dogadjaj d = new Dogadjaj("", "", "", null, "", n, null, null);
        dogadjaji = DBBroker.getInstance().vratiSlozeniObjekat(d);        
    }

    public LinkedList<OpstiDomenskiObjekat> getDogadjaji() {
        return dogadjaji;
    }
    
}
