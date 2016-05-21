package prof.prodageo.org;

import java.util.*;

public class Recherche {

  private String lieu;
  private String arrivee, depart;
  private int min, max;

  public void fixerLieu(String lieu)  {
    this.lieu = lieu;
  }

  public void fixerDateArrivee(String arrivee) {
    this.arrivee = arrivee;
  }

  public void fixerDateDepart(String depart) {
    this.depart = depart;
  }

  public void fouchettePrix(int min, int max) {
    this.min = min;
    this.max = max;
  }

  public List<String> annoncesCorrespondantes() {
    Facade facade = new Facade();
    facade.selectionLieu(lieu);
    facade.selectionDates(arrivee, depart);
    facade.selectionPrix(min,max);
    return facade.effectuerRecherche();
  }
}
