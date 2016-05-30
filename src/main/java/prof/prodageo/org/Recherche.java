package prof.prodageo.org;

import java.util.*;
import java.sql.SQLException;

//Controleur
public class Recherche {

  private String lieu;
  private Calendar arrivee, depart;
  private int min, max;
  private String messageErreur = "";
  private Facade facade = new Facade();

  private Calendar fixerDate(int annee, int mois, int date) {
    Calendar c = Calendar.getInstance();
    Date d;

    if (annee != 0 || mois != 0 || date != 0) {
      d = new Date(annee-1900,mois-1,date);
      if (d.compareTo(c.getTime()) > 0)
        c.set(annee, mois, date);
      else
        return null;
    }
    else
      return null;
    return c;
  }

  public void fixerLieu(String lieu)  {
    if (lieu == "")
      messageErreur = "Vous devez renseigner un nom de lieu";
    else
      this.lieu = lieu;
  }


  public void fixerDateArrivee(int anneeArrivee, int moisArrivee, int dateArrivee) {
    this.arrivee = fixerDate(anneeArrivee,moisArrivee,dateArrivee);
    if (this.arrivee == null)
      messageErreur = "La date d'arrivée doit être ultérieure à la date d'aujourd'hui";
  }


  public void fixerDateDepart(int anneeDepart, int moisDepart, int dateDepart) {
    this.depart = fixerDate(anneeDepart,moisDepart,dateDepart);
    if (this.depart == null)
      messageErreur = "La date de départ doit être ultérieure à la date d'aujourd'hui";
  }


  public void fourchettePrix(int min, int max) {
    if (min <= max) {
      this.min = min;
      this.max = max;
    }
    else
      messageErreur = "Le prix maximum doit être supérieur ou égal au prix minimum.";
  }


  public String getMessageErreur() {
    return this.messageErreur;
  }


  public List<String> annoncesCorrespondantes() {
    List<String> listeAnnonce = new LinkedList<String>();
    try{
        if (messageErreur == ""){
          if (arrivee.after(depart))
            messageErreur = "Les dates sont incohérentes. La date de départ doit être ultérieure à la date d'arrivée.";
          else{
            listeAnnonce = facade.effectuerRechercheBD(lieu,min,max,arrivee,depart);
            if (listeAnnonce.size() == 0 && messageErreur == "")
              messageErreur = "Il n'y a pas d'annonce correspondant à votre recherche.";
          }
        }
    }
    catch(SQLException e){}
    // facade.selectionLieu(lieu);
    //

    // if (messageErreur == "")
    //   facade.selectionPrix(min,max);
    //
    // if (messageErreur == "")
    //   listeAnnonce = facade.effectuerRecherche();
    return listeAnnonce;
  }

  public void setMessageErreur() {
    this.messageErreur = "";
  }
}
