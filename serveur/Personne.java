package broadcastlist.serveur;

import java.util.*;
import java.util.regex.*;

/**
*
* @author Maher LAAROUSSI, Hamid OUFKIR, Hachemi DRIS
*
*/

public class Personne {

  /* ================== ATTRIBUTs ================== */

  private String mail;
  private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


  /* ================== CONSTRUCTORs ================== */
  public Personne(String mail) {

    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(mail);
    if (matcher.find()) { this.mail = mail; }

  }

  /* ================== GETTERs & SETTERs ================== */

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  /* ================== toString ================== */

	@Override
	public String toString() {
		return "Personne [mail=" + mail + "]";
	}

}
