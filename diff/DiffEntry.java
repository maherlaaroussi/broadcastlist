package diff;

public class DiffEntry {


  /* ================== ATTRIBUTs ================== */

	private String nomListeDiffusion;
	private String password;
	private String mail;
	private String message;
	private String object;
	private String theme;
	private String commande;


  /* ================== CONSTRUCTORs ================== */

	public DiffEntry(String nomListeDiffusion, String password, String mail, String message, String object, String theme, String commande) {
		super();
		this.nomListeDiffusion = nomListeDiffusion;
		this.password = password;
		this.mail = mail;
		this.message = message;
		this.object = object;
		this.theme = theme;
		this.commande = commande;
	}

	public DiffEntry() {
	}


  /* ================== GETTERs & SETTERs ================== */

	public String getNomListeDiffusion() {
		return nomListeDiffusion;
	}

	public void setNomListeDiffusion(String nomListeDiffusion) {
		this.nomListeDiffusion = nomListeDiffusion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getCommande() {
		return commande;
	}

	public void setCommande(String commande) {
		this.commande = commande;
	}
}
