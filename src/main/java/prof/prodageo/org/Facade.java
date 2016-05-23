package prof.prodageo.org;

import java.util.*;

public class Facade {

  private List<Annonce> annoncesCorrespondantes;
  private List<Annonce> annoncesNonCorrespondantes;

  public Facade() {
    //Creation d'annonces temporaires
    this.annoncesCorrespondantes = new LinkedList<Annonce>();
    Calendar d = Calendar.getInstance();
    Calendar d2 = Calendar.getInstance();
    d.set(2016, 5, 21);
    d2.set(2020, 1, 1);


    Annonce a1 = new Annonce("Paris Square", "Paris", 20, d, d2);
    Annonce a2 = new Annonce("My Open Paris", "Paris", 35, d, d2);
    Annonce a3 = new Annonce("The Malte House", "Londres", 55, d, d2);
    Annonce a4 = new Annonce("Camden B&B", "Londres", 49, d, d2);
    Annonce a5 = new Annonce("My Little Poney - Enora's Palace", "Londres", 30, d, d2);
    annoncesCorrespondantes.add(a1);
    annoncesCorrespondantes.add(a2);
    annoncesCorrespondantes.add(a3);
    annoncesCorrespondantes.add(a4);
    annoncesCorrespondantes.add(a5);

    this.annoncesNonCorrespondantes = new LinkedList<Annonce>();
  }

  public void selectionLieu(String lieu) {
    this.annoncesNonCorrespondantes.clear();
    //On recherche les annonces n'ayant pas le lieu voulu
    for (Annonce a : this.annoncesCorrespondantes)
      if (a.getLieu() != lieu)
        this.annoncesNonCorrespondantes.add(a);

    //On retire les annonces non correspondantes
    for (Annonce a : this.annoncesNonCorrespondantes)
      if (this.annoncesCorrespondantes.contains(a))
        this.annoncesCorrespondantes.remove(a);
  }

  public void selectionDates(Calendar arrivee, Calendar depart) {
    this.annoncesNonCorrespondantes.clear();
    Calendar date;

    //Pour chaque annonce, on vérifie que chaque jour entre la date d'arrivee et la date de départ est
    //la date de départ est disponible
    for (Annonce a : this.annoncesCorrespondantes) {
      date = arrivee;
      while (date.compareTo(depart) <= 0) {
        //Si elle ne l'est pas et que l'annonce n'est pas déjà dans la liste d'annoncesNonCorrespondantes
        //alors on l'ajoute à celle ci.
        if (!a.getDisponibilite(date) && !annoncesNonCorrespondantes.contains(a))
          annoncesNonCorrespondantes.add(a);
        date.add(Calendar.DATE, 1);
      }
    }

      //On retire les annonces non disponibles aux dates souhaitées
    for (Annonce a : this.annoncesNonCorrespondantes)
      if (this.annoncesCorrespondantes.contains(a))
        this.annoncesCorrespondantes.remove(a);
  }

  public void selectionPrix(int min, int max) {
    this.annoncesNonCorrespondantes.clear();
    for (Annonce a : this.annoncesCorrespondantes)
      if (a.getPrix() < (double) min || a.getPrix() > (double) max)
        this.annoncesNonCorrespondantes.add(a);

    for (Annonce a : this.annoncesNonCorrespondantes)
      if (this.annoncesCorrespondantes.contains(a))
        this.annoncesCorrespondantes.remove(a);
  }

  public List<String> effectuerRecherche() {
    List<String> annonces = new LinkedList<String>();
    for (Annonce a : annoncesCorrespondantes)
      annonces.add(a.getNom() + ", " + a.getLieu() + ", prix de la chambre : " + a.getPrix() +"\n");

    return annonces;
  }
}
