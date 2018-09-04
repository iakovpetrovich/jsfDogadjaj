/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import dbb.DBBroker;
import domen.Prisustvo;

/**
 *
 * @author Jaša
 */
public class PrikaziPrisustvo extends OpstaSistemskaOperacija{

    Prisustvo prisustvo; 
            
    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {
        
    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        prisustvo = (Prisustvo) objekat;
        prisustvo = (Prisustvo) DBBroker.getInstance().vratiJednoznacno(prisustvo);
    }

    public Prisustvo getPrisustvo() {
        return prisustvo;
    }
    
    
    
}
