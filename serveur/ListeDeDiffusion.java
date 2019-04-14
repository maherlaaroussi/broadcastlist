package serveur;

import java.util.*;
import java.util.function.Predicate;

/**
*
* @author Maher LAAROUSSI, Hamid OUFKIR, Hachemi DRIS
*
*/

public class ListeDeDiffusion {

  /* ================== ATTRIBUTs ================== */

  private String nom;
  private Theme theme;
  private Personne diffuseur;
  private String password;
  private List<Personne> abonnes = new ArrayList<Personne>();
  public static List<ListeDeDiffusion> liste = new ArrayList<ListeDeDiffusion>();

  /* ================== CONSTRUCTORs ================== */

  public ListeDeDiffusion(String nom, Theme theme, Personne diffuseur, String password) {
    this.nom = nom;
    this.theme = theme;
    this.diffuseur = diffuseur;
    this.password = password;
    this.abonnes = new ArrayList<Personne>();
  }

  /* ================== METHODs ================== */

  public static ListeDeDiffusion findListeDeDiffusionByNom(String nom) {
    ListeDeDiffusion l = null;
    try {
      l = liste.stream().filter(e -> e.getNom().equalsIgnoreCase(nom)).findFirst().get();
    } catch (NoSuchElementException e) { }
    return l;
  }

  public boolean isAbonneeExist(Personne p) {
    if (this.abonnes.contains(p)) { return true; }
    return false;
  }

  public void addAbonnee(Personne p) {
    this.abonnes.add(p);
  }

  public void removeAbonnee(Personne p) {
    this.abonnes.remove(p);
  }

  /* ================== GETTERs & SETTERs ================== */

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Theme getTheme() {
    return theme;
  }

  public void setTheme(Theme theme) {
    this.theme = theme;
  }

  public Personne getDiffuseur() {
    return diffuseur;
  }

  public void setDiffuseur(Personne diffuseur) {
    this.diffuseur = diffuseur;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Personne> getAbonnes() {
    return abonnes;
  }

  public void setAbonnes(List<Personne> abonnes) {
    this.abonnes = abonnes;
  }


  /* ================== toString ================== */

  @Override
  public String toString() {
    return "ListeDeDiffusion [nom=" + nom + ", theme=" + theme + ", diffuseur=" + diffuseur + ", password=" + password + ", abonnes=" + abonnes + ", theme=" + theme + "]";
  }

}
