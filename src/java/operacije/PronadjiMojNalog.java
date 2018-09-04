/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import dbb.DBBroker;
import domen.Nalog;

/**
 *
 * @author Jaša
 */
public class PronadjiMojNalog extends OpstaSistemskaOperacija{

    Nalog nalog;
    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {
        
    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        Nalog n = (Nalog) objekat;
        nalog = (Nalog) DBBroker.getInstance().vratiJednoznacno(n);
    }

    public Nalog getNalog() {
        return nalog;
    }
    
    
    
}
