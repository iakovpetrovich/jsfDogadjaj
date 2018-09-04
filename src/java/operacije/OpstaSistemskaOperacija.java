package operacije;


import dbb.DBBroker;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jaša
 */
public abstract class OpstaSistemskaOperacija {

    private void uspostaviKonekcijuSaBazom() throws IOException, SQLException {
        DBBroker.getInstance().uspostaviKonekciju();
    }

    protected abstract void proveriPreduslove(Object objekat) throws Exception;

    protected abstract void konkretnoIzvrsenjeSO(Object objekat) throws Exception;

    private void potvrdiTransakciju() throws SQLException {
        DBBroker.getInstance().potvrdi();
    }

    private void ponistiTransakciju() throws SQLException {
        DBBroker.getInstance().ponisti();
    }

    private void zatvoriKonekcijuSaBazom() {
        DBBroker.getInstance().raskiniKonekciju();
    }

    public final synchronized void opsteIzvrsenjeSO(Object objekat) throws Exception {
        try {
            uspostaviKonekcijuSaBazom();
            proveriPreduslove(objekat);
            konkretnoIzvrsenjeSO(objekat);
            potvrdiTransakciju();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            ponistiTransakciju();
            throw e;
        } finally {
            zatvoriKonekcijuSaBazom();
        }
    }

}
