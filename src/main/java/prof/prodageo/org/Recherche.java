package prof.prodageo.org;

import java.util.*;

public class Recherche {

  private String lieu;
  private Calendar arrivee, depart;
  private int min, max;
  private String messageErreur;


  public void fixerLieu(String lieu)  {
    if (lieu == "")
      messageErreur = "Vous devez renseigner un nom de lieu";
    else
      this.lieu = lieu;
  }


  public void fixerDateArrivee(int anneeArrivee, int moisArrivee, int dateArrivee) {
    Calendar c = Calendar.getInstance();
    c.set(anneeArrivee, moisArrivee, dateArrivee);
    this.arrivee = c;
  }


  public void fixerDateDepart(int anneeDepart, int moisDepart, int dateDepart) {
    Calendar c = Calendar.getInstance();
    c.set(anneeDepart, moisDepart, dateDepart);
    this.depart = c;
  }


  public void fourchettePrix(int min, int max) {
    if (min < max) {
      this.min = min;
      this.max = max;
    }
    else
      messageErreur = "Le prix maximum doit être inférieur ou égal au prix minimum.";
  }


  public String getMessageErreur() {
    return this.messageErreur;
  }


  public List<String> annoncesCorrespondantes() {
    Facade facade = new Facade();
    facade.selectionLieu(lieu);

    if (arrivee.compareTo(depart) >= 0)
      facade.selectionDates(arrivee, depart);
    else
      messageErreur = "Les dates sont incohérentes. La date de départ doit être ultérieure à la date d'arrivée.";

    facade.selectionPrix(min,max);

    List<String> listeAnnonce = facade.effectuerRecherche();
    if (listeAnnonce.size() == 0)
      messageErreur = "Il n'y a pas d'annonce correspondant à votre recherche.";

    return listeAnnonce;
  }
}
