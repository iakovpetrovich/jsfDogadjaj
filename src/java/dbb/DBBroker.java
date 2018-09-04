/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import domen.Aktivnost;
import domen.Dogadjaj;
import domen.Mesto;
import domen.Nalog;
import domen.OpstiDomenskiObjekat;
import domen.Prisustvo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import prenos.Odgovor;

/**
 *
 * @author Jaša
 */
public class DBBroker {

    private Connection connection;
    private static DBBroker instance;

    private DBBroker() {

    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public void uspostaviKonekciju() throws IOException, SQLException {
        DBUtil util = new DBUtil();
        String url = util.vratiURL();
        String user = util.vratiKorisnika();
        String pass = util.vratiSifru();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection = DriverManager.getConnection(url, user, pass);
        connection.setAutoCommit(false);
        System.out.println("Uspostavljena konekcija sa bazom!");
    }

    private OpstiDomenskiObjekat sacuvaj(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES " + odo.vratiVrednostiZaUnos();
        Statement st = connection.createStatement();
        System.out.println(upit);
        st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
        odo.podesiAtributeDobijeneIzBaze(st.getGeneratedKeys());
        System.out.println("Sacuvan " + odo.vratiNazivTabele());
        return odo;
    }

    public LinkedList<OpstiDomenskiObjekat> vratiSlozeniObjekat(OpstiDomenskiObjekat odo) throws SQLException, Exception {
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() + " " + odo.vratiUslovZaJoin() + " " + odo.vratiUslovZaPretragu();
        System.out.println(upit);
        Statement st = connection.createStatement();
        return odo.napuni(st.executeQuery(upit));
    }

    public ResultSet pronadji(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM " + odo.vratiNazivTabele() +" " + odo.vratiUslovZaJoin() + " " +" WHERE " + odo.vratiJednoznacno();
        System.out.println(upit);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return rs;
    }

    public boolean obrisi(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "DELETE FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiUslovZaBrisanje();
        Statement st = connection.createStatement();
        System.out.println(upit);
        st.execute(upit);
        st.close();
        return true;

    }
    
    public OpstiDomenskiObjekat vratiJednoznacno(OpstiDomenskiObjekat odo) throws Exception{
        LinkedList<OpstiDomenskiObjekat> listaOdo =odo.napuni(pronadji(odo));
        if (listaOdo.isEmpty()) {
            throw new Exception("Sistem ne može da pronađe "+odo.vratiNazivTabele()+".");
        }
        return listaOdo.getFirst();
    }
    
    public OpstiDomenskiObjekat izmeni(OpstiDomenskiObjekat odo) throws SQLException{
        String upit = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiVreddnostZaIzmenu() + " WHERE " + odo.vratiUslovZaBrisanje();
        Statement st = connection.createStatement();
        System.out.println(upit);
        st.executeUpdate(upit);
        System.out.println("Izmenjen red tabele " + odo.vratiNazivTabele());
        return odo;
    }
    

    public OpstiDomenskiObjekat sacuvajSlozeniObjekat(OpstiDomenskiObjekat odo) throws SQLException {
        sacuvaj(odo);
        if (odo.vratiVezaneObjekte() == null) {
            return odo;
        }
        for (OpstiDomenskiObjekat object : odo.vratiVezaneObjekte()) {
            sacuvajSlozeniObjekat(object);
        }

        return odo;
    }

    public void raskiniKonekciju() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void potvrdi() throws SQLException {
        connection.commit();
        System.out.println("Transakcija potvrđena!");
    }

    public void ponisti() throws SQLException {
        connection.rollback();
        System.out.println("Transakcija poništena!");
    }

//    public static void main(String[] args) {
//        try {
//            Nalog n = new Nalog("Jakov", "jakov", "123456", new Mesto("11000", "NEBITAN"));
//            Dogadjaj d = new Dogadjaj("", "Proba", "proba isprobavanje", new java.util.Date(), "Adresa", n, null, new Mesto("11000", "NEBITAN"));
//            Aktivnost a = new Aktivnost(d, 5, "naziv", "opis");
//            Aktivnost b = new Aktivnost(d, 5, "naziv", "opis");
//            LinkedList l = new LinkedList();
//            l.add(a);
//            l.add(b);
//            d.setSpisakAktivnosti(l);
//            DBBroker.getInstance().uspostaviKonekciju();
//
//            DBBroker.getInstance().sacuvajSlozeniObjekat(d);
//            DBBroker.getInstance().potvrdi();
//
//            DBBroker.getInstance().raskiniKonekciju();
//        } catch (IOException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//            DBBroker.getInstance().raskiniKonekciju();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//            DBBroker.getInstance().raskiniKonekciju();
//        } catch (Exception ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//            DBBroker.getInstance().raskiniKonekciju();
//        }
//    }
//    public static void main(String[] args) {
//        try {
//            Nalog n = new Nalog("Jakov", null, null, null);
//            Prisustvo p = new Prisustvo(n, null, 0, "");
//            DBBroker.getInstance().uspostaviKonekciju();
//            LinkedList<OpstiDomenskiObjekat> lista = DBBroker.getInstance().vratiSlozeniObjekat(p);
//            if (lista == null || lista.isEmpty()) {
//                System.out.println("Nema rezultata pretrage");
//            }
//            Prisustvo pr = (Prisustvo) lista.get(0);
//            System.out.println(pr.getKorisnik().getMesto().getNaziv());
//            System.out.println(pr.getBrojOsoba());
//            System.out.println(pr.getDogadjaj().getNaziv());
//        } catch (IOException ex) {
//            DBBroker.getInstance().raskiniKonekciju();
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            DBBroker.getInstance().raskiniKonekciju();
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            DBBroker.getInstance().raskiniKonekciju();
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//                
//    }
}
