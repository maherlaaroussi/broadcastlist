package diff;

import java.net.*;
import java.io.*;

public class DiffContentHandler extends ContentHandler {

	String queryValue = "";
	String resultValue = "";

	public Object getContent(URLConnection urlc) throws IOException {

		DiffEntry myDiff = new DiffEntry();
		URL url = urlc.getURL();
		myDiff.setCommande(url.getPath().substring(1));
		String query = url.getQuery();
		String[] args = null;

		if (query != null) {
			args = url.getQuery().split("&");
		}

		try {

			switch (myDiff.getCommande()) {

				case "Créer": {
					myDiff.setNomListeDiffusion(args[0].split("=")[1]);
					myDiff.setMail(args[1].split("=")[1]);
					myDiff.setTheme(args[2].split("=")[1]);
					myDiff.setPassword(args[3].split("=")[1]);
					break;
				}

				case "Supprimer": {
					myDiff.setNomListeDiffusion(args[0].split("=")[1]);
					myDiff.setPassword(args[1].split("=")[1]);
					break;
				}

				case "Abonnement": {
					myDiff.setNomListeDiffusion(args[0].split("=")[1]);
					myDiff.setMail(args[1].split("=")[1]);
					break;
				}

				case "Désabonnement": {
					myDiff.setNomListeDiffusion(args[0].split("=")[1]);
					myDiff.setMail(args[1].split("=")[1]);
					break;
				}

				case "Envoyer": {
					myDiff.setNomListeDiffusion(args[0].split("=")[1]);
					myDiff.setObject(args[1].split("=")[1]);
					myDiff.setMessage(args[2].split("=")[1]);
					break;
				}

				case "Récupérer": {
					myDiff.setTheme(args[0].split("=")[1]);
					break;
				}

				case "SupprimerTout": {
					break;
				}

				case "Theme": {
					break;
				}

				default:
				throw new MalformedURLException();

			}

		} catch (IndexOutOfBoundsException e) { throw new IOException(); }

		return myDiff;

	}


}
