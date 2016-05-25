package prof.prodageo.org;

import java.util.*;
import java.text.*;

public class Annonce {

  private String nomChambreHote;
  private String lieu;
  private String description;
  private double note;
  private String image;
  private double prix;
  private Map<String,Boolean> dateDispo;

  public Annonce(String nom, String lieu, double prix, Calendar dateDebut, Calendar datefin, String desc, double note, String image ) {
    this.nomChambreHote = nom;
    this.lieu = lieu;
    this.prix = prix;
    this.dateDispo = new HashMap<String,Boolean>();
    this.image = image;
    this.note = note;
    this.description = desc;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String formatDateDebut = sdf.format(dateDebut.getTime());
    String formatDateFin = sdf.format(datefin.getTime());

    while (dateDebut.compareTo(datefin) <= 0) {
      dateDispo.put(formatDateDebut, true);
      dateDebut.add(Calendar.DATE, 1);
      formatDateDebut = sdf.format(dateDebut.getTime());
    }
  }

  public String getLieu() {
    return this.lieu;
  }

  public double getPrix() {
    return this.prix;
  }

  public boolean getDisponibilite(Calendar d) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String formatDateDebut = sdf.format(d.getTime());
    return dateDispo.get(formatDateDebut);
  }

  public String getNom() {
    return this.nomChambreHote;
  }

  public String getDesc() {
    return this.description;
  }

  public String getImage() {
    return this.image;
  }

  public double getNote() {
    return this.note;
  }
}
