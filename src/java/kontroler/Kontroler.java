/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Dogadjaj;
import domen.Mesto;
import domen.Nalog;
import domen.OpstiDomenskiObjekat;
import domen.Prisustvo;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import operacije.IzmeniDogadjaj;
import operacije.ObrisiDogadjaj;
import operacije.ObrisiNalog;
import operacije.ObrisiPrisustvo;
import operacije.OpstaSistemskaOperacija;
import operacije.PrikaziDogadjaj;
import operacije.PrikaziPrisustvo;
import operacije.Pristupi;
import operacije.PronadjMojaPrisustva;
import operacije.Pronadji;
import operacije.PronadjiMojNalog;
import operacije.PronadjiMojeDogadjaje;
import operacije.SacuvajNalog;
import operacije.SacuvajPriustvo;
import operacije.UcitajMesta;
import operacije.VratiNajskorije;
import operacije.ZapamtiDogadjaj;
import paket.DogadjajBean;
import prenos.Odgovor;
import sun.security.krb5.internal.tools.Klist;

/**
 *
 * @author Jaša
 */
public class Kontroler {

    private static Kontroler instance;

    private Kontroler() {

    }
    


    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    OpstaSistemskaOperacija oso;
    Odgovor odg;

    public boolean zapamtiDogadjaj(Dogadjaj dogadjaj) {
        try {
            oso = new ZapamtiDogadjaj();
            oso.opsteIzvrsenjeSO(dogadjaj);
            odg = new Odgovor(((ZapamtiDogadjaj) oso).getDogadjaj(), true, "Sistem je zapamtio događaj.");
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da zapamti događaj.");
            return false;
        }
        
    }

    public LinkedList<OpstiDomenskiObjekat> vratiNajskorije() {
        oso = new VratiNajskorije();
        try {
            oso.opsteIzvrsenjeSO(new LinkedList<Dogadjaj>());
            System.out.println("Vratio sam dogadjaje");
            return ((VratiNajskorije) oso).getDogadjaji();
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da vrati listu događaja!");
        }
        return null;
    }

    public LinkedList<Dogadjaj> ponadjiDogadjaje(Dogadjaj dogadjaj) {
        oso = new Pronadji();
        LinkedList<Dogadjaj> dogadjaji = new LinkedList<Dogadjaj>();
        try {
            oso.opsteIzvrsenjeSO(dogadjaj);
            LinkedList<OpstiDomenskiObjekat> lista = ((Pronadji) oso).getDogadjaji();
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
                Dogadjaj d = (Dogadjaj) opstiDomenskiObjekat;
                dogadjaji.add(d);
            }
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da nađe događaje po zadatim vrenostima!");
        }
        return dogadjaji;
    }

    public Dogadjaj prikaziDogadjaj(Dogadjaj dogadjaj) {
        oso = new PrikaziDogadjaj();
        Dogadjaj d = null;
        try {
            oso.opsteIzvrsenjeSO(dogadjaj);
            d = ((PrikaziDogadjaj) oso).getDogadjaj();
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return d;
    }

    public LinkedList<OpstiDomenskiObjekat> pronadjiMojeDogadjaje(Nalog nalog) {
        oso = new PronadjiMojeDogadjaje();
        
        try {
            oso.opsteIzvrsenjeSO(nalog);
            odg = new Odgovor(((PronadjiMojeDogadjaje) oso).getDogadjaji(), true, "Sistem je našao događaje po zadatim vrednostima.");
            return ((PronadjiMojeDogadjaje) oso).getDogadjaji();
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da nađe događaje po zadatim vrenostima!");
        }
        return null;
    }

    public boolean izmeniDogadjaj(Dogadjaj dogadjaj) {
        oso = new IzmeniDogadjaj();
        
        try {
            oso.opsteIzvrsenjeSO(dogadjaj);
            odg = new Odgovor(((IzmeniDogadjaj) oso).getDogadjaj(), true, "Sistem je izmenio događaj.");
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da zapamti događaj.");
            return false;
        }
        
    }

    public boolean obrisiDogadjaj(Dogadjaj dogadjaj, Nalog korisnik) {
        LinkedList<OpstiDomenskiObjekat> dogadjajINalog = new LinkedList<OpstiDomenskiObjekat>();
        dogadjajINalog.add(dogadjaj);
        dogadjajINalog.add(korisnik);
        oso = new ObrisiDogadjaj();
        try {
            oso.opsteIzvrsenjeSO(dogadjajINalog);
            odg = new Odgovor(null, null, true, "Sistem je obrisao događaj.");
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da obriše događaj.");
            return false;
        }
        
    }

    public boolean sacuvajNalog(Nalog nalog) {
        oso = new SacuvajNalog();
        try {
            oso.opsteIzvrsenjeSO(nalog);
            odg = new Odgovor(null, null, true, "Sistem je sačuvao nalog.");
            //Odjavi ga!
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da sačuva nalog." + ex.getMessage());
            return false;
        }
        return true;
    }

    public Odgovor pronadjiMojNalog(Nalog nalog) {
        oso = new PronadjiMojNalog();
        try {
            oso.opsteIzvrsenjeSO(nalog);
            odg = new Odgovor(((PronadjiMojNalog) oso).getNalog(), true, "Sistem je pronašao nalog.");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da prikaže vaš nalog.");
        }
        return odg;
    }

    public String sacuvajPrisustvo(Prisustvo prisustvo) throws Exception {
        oso = new SacuvajPriustvo();
        try {
            oso.opsteIzvrsenjeSO(prisustvo);
            odg = new Odgovor(((SacuvajPriustvo) oso).getPrisustvo(), true, "Sistem je sačuvao prisustvo.");
            return "Sistem je sačuvao prisustvo.";
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da sačuva prisustvo.");
            throw new Exception("Prisustvo je već sačuvano.");
        }
        
    }

    public LinkedList<Prisustvo> pronadjiMojaPrisustva(Nalog nalog) {
        oso = new PronadjMojaPrisustva();
        LinkedList<Prisustvo> prisustva = new LinkedList<Prisustvo>();
        try {
            oso.opsteIzvrsenjeSO(nalog);
            LinkedList<OpstiDomenskiObjekat> prisustvaBaza =((PronadjMojaPrisustva) oso).getPrisustva();
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : prisustvaBaza) {
                Prisustvo p = (Prisustvo) opstiDomenskiObjekat;
                prisustva.add(p);
            }
 
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da nađr prisustva po zadatoj vrednosti.");
        }
        return prisustva;
    }

    public Odgovor prikaziPrisustvo(Prisustvo prisustvo) {
        oso = new PrikaziPrisustvo();
        try {
            oso.opsteIzvrsenjeSO(prisustvo);
            odg = new Odgovor(((PrikaziPrisustvo) oso).getPrisustvo(), true, "Sistem je pronašao prisustvo!");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da vrati prisustvo.");
        }
        return odg;
    }

    public Odgovor obrisiPriustvo(Prisustvo prisustvo, Nalog nalog) {
        LinkedList<OpstiDomenskiObjekat> prisustvoINalog = new LinkedList<OpstiDomenskiObjekat>();
        prisustvoINalog.add(prisustvo);
        prisustvoINalog.add(nalog);
        oso = new ObrisiPrisustvo();
        try {
            oso.opsteIzvrsenjeSO(prisustvoINalog);
            odg = new Odgovor(null, null, true, "");
            System.out.println("Obrisano");
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da obiše prisustvo.");
            System.out.println("Sistem ne može da obiše prisustvo.");
        }
        return odg;
    }

    public Nalog pristupi(Nalog nalog) {
        oso = new Pristupi();
        Nalog n = null;
        try {
            oso.opsteIzvrsenjeSO(nalog);
            n = ((Pristupi) oso).getNalog();
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            ex.getMessage();
        }
        return n;
    }

    public Odgovor obrisiNalog(Nalog nalog) {
        oso = new ObrisiNalog();
        try {
            oso.opsteIzvrsenjeSO(nalog);
            odg = new Odgovor(null, null, true, "Sistem je obrisao nalog.");
            //Odjavi ga!
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "Sistem ne može da obriše nalog." + ex.getMessage());
        }
        return odg;

    }

    public LinkedList<Mesto> ucitajMesta() {
        oso = new UcitajMesta();
        LinkedList<Mesto> mesta = new LinkedList<Mesto>();
        try {
            oso.opsteIzvrsenjeSO(new Mesto());

            LinkedList<OpstiDomenskiObjekat> lista = ((UcitajMesta) oso).getMesta();
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
                Mesto m = (Mesto) opstiDomenskiObjekat;
                mesta.add(m);
            }
        } catch (Exception ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            odg = new Odgovor(null, null, false, "");
        }
        return mesta;
    }

}
