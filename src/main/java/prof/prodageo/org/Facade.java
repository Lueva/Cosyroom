package prof.prodageo.org;

import java.util.*;

public class Facade {

  private List<Annonce> annoncesCorrespondantes;
  private List<Annonce> annoncesNonCorrespondantes;

  public Facade() {
    //Creation d'annonces temporaires
    this.annoncesCorrespondantes = new LinkedList<Annonce>();
    Annonce a1 = new Annonce("Paris Square", "Paris", 20, "20/12/2015", "8/10/2017");
    Annonce a2 = new Annonce("My Open Paris", "Paris", 35, "20/12/2015", "8/10/2017");
    Annonce a3 = new Annonce("The Malte House", "Londres", 55, "20/12/2015", "8/10/2017");
    Annonce a4 = new Annonce("Camden B&B", "Londres", 49, "20/12/2015", "8/10/2017");
    Annonce a5 = new Annonce("My Little Poney - Enora's Palace", "Londres", 30, "20/12/2015", "8/10/2016");
    annoncesCorrespondantes.add(a1);
    annoncesCorrespondantes.add(a2);
    annoncesCorrespondantes.add(a3);
    annoncesCorrespondantes.add(a4);
    annoncesCorrespondantes.add(a5);

    this.annoncesNonCorrespondantes = new LinkedList<Annonce>();
  }

  public void selectionLieu(String lieu) {
    //On recherche les annonces n'ayant pas le lieu voulu
    for (Annonce a : this.annoncesCorrespondantes)
      if (a.getLieu() != lieu)
        this.annoncesNonCorrespondantes.add(a);

    //On retire les annonces non correspondantes
    for (Annonce a : this.annoncesNonCorrespondantes)
      if (this.annoncesCorrespondantes.contains(a))
        this.annoncesCorrespondantes.remove(a);
  }

  public void selectionDates(String arrivee, String depart) {
    //TODO
  }

  public void selectionPrix(int min, int max) {
    for (Annonce a : this.annoncesCorrespondantes)
      if (a.getPrix() < (double) min)
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
