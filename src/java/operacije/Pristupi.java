/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt.GetOpt;
import dbb.DBBroker;
import domen.Nalog;

/**
 *
 * @author Jaša
 */
public class Pristupi extends OpstaSistemskaOperacija {

    Nalog nalog;

    @Override
    protected void proveriPreduslove(Object objekat) throws Exception {

    }

    @Override
    protected void konkretnoIzvrsenjeSO(Object objekat) throws Exception {
        nalog = (Nalog) objekat;
        Nalog nalogIzBaze = (Nalog) DBBroker.getInstance().vratiJednoznacno(nalog);
        System.out.println(nalogIzBaze.getIme());
        if (nalog.equals(nalogIzBaze)) {
            
            System.out.println(nalog.getIme());
            nalog = nalogIzBaze;

        } else {
            throw new Exception("Uneti podaci nisu tačni! Proverite unos i pokušajte ponovo.");
        }
    }

    public Nalog getNalog() {
        return nalog;
    }

}
