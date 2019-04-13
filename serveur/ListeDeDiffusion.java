package serveur;

import java.util.*;

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

  public static enum Theme {
    Theme1 ("1"),
    Theme2 ("2"),
    Theme3 ("3");
    private final String theme;
    Theme(String theme) {
      this.theme = theme;
    }
  }

  /* ================== CONSTRUCTORs ================== */

	public ListeDeDiffusion(String nom, Theme theme, Personne diffuseur, String password, List<Personne> abonnes) {
		this.nom = nom;
		this.theme = theme;
		this.diffuseur = diffuseur;
		this.password = password;
		this.abonnes = abonnes;
	}

  /* ================== METHODs ================== */


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
