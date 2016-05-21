package prof.prodageo.org;

public class Annonce {

  private String nomChambreHote;
  private String lieu;
  private double prix;
  private String dateDispoDebut, dateDispoFin;

  public Annonce(String nom, String lieu, double prix, String dateDispoDebut, String dateDispoFin) {
    this.nomChambreHote = nom;
    this.lieu = lieu;
    this.prix = prix;
    this.dateDispoDebut = dateDispoDebut;
    this.dateDispoFin = dateDispoFin;
  }

  public String getLieu() {
    return this.lieu;
  }

  public double getPrix() {
    return this.prix;
  }

  public boolean getDisponibilité(String arrivee, String depart) {
    //TODO
    return true;
  }

  public String getNom() {
    return this.nomChambreHote;
  }
}
