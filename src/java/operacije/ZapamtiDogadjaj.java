/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import dbb.DBBroker;
import domen.Dogadjaj;

/**
 *
 * @author Jaša
 */
public class ZapamtiDogadjaj extends OpstaSistemskaOperacija{
    
    private Dogadjaj dogadjaj;

    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {
        
    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        Dogadjaj d = (Dogadjaj) objekat;
        dogadjaj = (Dogadjaj) DBBroker.getInstance().sacuvajSlozeniObjekat(d);
    }

    public Dogadjaj getDogadjaj() {
        return dogadjaj;
    }
    
    
    
}
