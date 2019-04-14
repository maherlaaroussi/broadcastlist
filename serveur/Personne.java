package serveur;

import java.util.*;
import java.util.regex.*;
import java.util.function.Predicate;

/**
*
* @author Maher LAAROUSSI, Hamid OUFKIR, Hachemi DRIS
*
*/

public class Personne {

  /* ================== ATTRIBUTs ================== */

  private String mail;
  public static List<Personne> personnes = new ArrayList<Personne>();
  private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


  /* ================== CONSTRUCTORs ================== */

  public Personne(String mail) {
    this.mail = mail;
  }

  public Personne() {
  }

  /* ================== GETTERs & SETTERs ================== */

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public static List<Personne> getPersonnes() {
    return personnes;
  }

  public static void setPersonnes(List<Personne> personnes) {
    Personne.personnes = personnes;
  }

  /* ================== toString ================== */

  @Override
  public String toString() {
    return "Personne [mail=" + mail + "]";
  }


  /* ================== toString ================== */

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Personne))  {
      return false;
    }
    Personne other = (Personne) obj;
    return this.mail.equals(other.mail);
  }

  @Override
  public int hashCode() {
    return 31 * this.mail.hashCode();
  }

  /* ================== METHODs ================== */

  public static Personne fiundByMail(String mail) {
    Personne p = null;
    try {
      p = personnes.stream().filter(e -> e.getMail().equalsIgnoreCase(mail)).findFirst().get();
    } catch (NoSuchElementException e) { }
    return p;
  }

  public static boolean isValid(String mail) {

    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(mail);
    if (matcher.find()) { return true; }
    return false;

  }

}
