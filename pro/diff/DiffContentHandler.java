package pro.diff;

import java.net.*;
import java.io.*;
import serveur.Constantes;

public class DiffContentHandler extends ContentHandler {

	public Object getContent(URLConnection urlc) throws IOException {

		DiffEntry myDiff = new DiffEntry();
		URL url = urlc.getURL();
		myDiff.setCommande(url.getPath().substring(1));
		String query = url.getQuery();
		String[] args = null;
		String user = url.getAuthority();

		if (query != null) {
			args = url.getQuery().split("&");

		}

		try {

			myDiff.setMail(user);

			switch (myDiff.getCommande()) {

				case Constantes.CREATE: {
					for (String s : args) {
						String[] s2 = s.split("=");
						switch (s2[0]) {
							case "name": { myDiff.setNomListeDiffusion(s2[1]); break; }
							case "password": { myDiff.setPassword(s2[1]); break; }
							case "theme": { myDiff.setTheme(s2[1]); break; }
						}

					}
					break;
				}

				case Constantes.DELETE: {
					for (String s : args) {
						String[] s2 = s.split("=");
						switch (s2[0]) {
							case "name": { myDiff.setNomListeDiffusion(s2[1]); break; }
							case "password": { myDiff.setPassword(s2[1]); break; }
						}

					}
					break;
				}

				case Constantes.SUBSCRIBE: {
					for (String s : args) {
						String[] s2 = s.split("=");
						switch (s2[0]) {
							case "name": { myDiff.setNomListeDiffusion(s2[1]); break; }
						}

					}
					break;
				}

				case Constantes.UNSUBSCRIBE: {
					for (String s : args) {
						String[] s2 = s.split("=");
						switch (s2[0]) {
							case "name": { myDiff.setNomListeDiffusion(s2[1]); break; }
						}

					}
					break;
				}

				case Constantes.SEND: {
					for (String s : args) {
						String[] s2 = s.split("=");
						switch (s2[0]) {
							case "name": { myDiff.setNomListeDiffusion(s2[1]); break; }
							case "message": { myDiff.setMessage(s2[1]); break; }
							case "object": { myDiff.setObject(s2[1]); break; }
						}

					}
					break;
				}

				case Constantes.GET: {
					for (String s : args) {
						String[] s2 = s.split("=");
						switch (s2[0]) {
							case "theme": { myDiff.setNomListeDiffusion(s2[1]); break; }
						}

					}
					break;
				}

				case Constantes.DELETEALL: {
					break;
				}

				default:
					throw new MalformedURLException();

			}

		} catch (IndexOutOfBoundsException e) { throw new IOException(); }

		return myDiff;

	}


}
