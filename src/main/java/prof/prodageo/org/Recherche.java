package prof.prodageo.org;

import java.util.*;

public class Recherche {

  private String lieu;
  private Calendar arrivee, depart;
  private int min, max;

  public void fixerLieu(String lieu)  {
    this.lieu = lieu;
  }

  public void fixerDateArrivee(Calendar arrivee) {
    this.arrivee = arrivee;
  }

  public boolean fixerDateDepart(Calendar depart) {
    if (depart.compareTo(arrivee) <= 0)
      return false;
    this.depart = depart;
    return true;
  }

  public boolean fouchettePrix(int min, int max) {
    if (min < max) {
      this.min = min;
      this.max = max;
      return true;
    }
    return false;
  }

  public List<String> annoncesCorrespondantes() {
    Facade facade = new Facade();
    facade.selectionLieu(lieu);
    facade.selectionDates(arrivee, depart);
    facade.selectionPrix(min,max);
    return facade.effectuerRecherche();
  }
}
