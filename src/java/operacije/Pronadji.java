/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import dbb.DBBroker;
import domen.Dogadjaj;
import domen.OpstiDomenskiObjekat;
import java.util.LinkedList;

/**
 *
 * @author Jaša
 */
public class Pronadji extends OpstaSistemskaOperacija {

    private LinkedList<OpstiDomenskiObjekat> dogadjaji = new LinkedList<OpstiDomenskiObjekat>();

    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {

    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        Dogadjaj d = (Dogadjaj) objekat;
        dogadjaji = DBBroker.getInstance().vratiSlozeniObjekat(d);
    }

    public LinkedList<OpstiDomenskiObjekat> getDogadjaji() {
        return dogadjaji;
    }

}
