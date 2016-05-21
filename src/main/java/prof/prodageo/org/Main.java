package prof.prodageo.org;

import java.util.*;

public class Main {

  public static void main(String[] args) {

    Recherche r = new Recherche();
    Calendar c = Calendar.getInstance();
    c.set(2017,1,1);
    Calendar c2 = Calendar.getInstance();
    c2.set(2017,1,18);

    r.fixerLieu("Paris");
    r.fixerDateArrivee(c);
    r.fixerDateDepart(c2);
    r.fouchettePrix(15,50);
    List<String> l = r.annoncesCorrespondantes();

    for (String s : l)
      System.out.println(s);
  }
}
