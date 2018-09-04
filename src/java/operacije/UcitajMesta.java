/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import dbb.DBBroker;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import java.util.LinkedList;

/**
 *
 * @author Jaša
 */
public class UcitajMesta extends OpstaSistemskaOperacija{

    LinkedList<OpstiDomenskiObjekat> mesta;
    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {
        
    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        mesta = DBBroker.getInstance().vratiSlozeniObjekat((new Mesto()));
        System.out.println("PROBA "+((Mesto)mesta.get(0)).getNaziv());
    }

    public LinkedList<OpstiDomenskiObjekat> getMesta() {
        return mesta;
    }
    
    
    
}
