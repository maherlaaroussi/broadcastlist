package pro.diff;

import java.net.*;
import java.io.*;

import serveur.Constantes;

public class DiffURLConnection extends URLConnection {

  public DiffURLConnection(URL url) throws MalformedURLException {
    super(url);
    if (!Constantes.factorySet) {
        setContentHandlerFactory(Constantes.factory);
        Constantes.factorySet = true;
    }
  }

  public void connect() {
  }
  public InputStream getInputStream() {
    return null;
  }

  public String getHeaderField(String name) {
    if (name.equals("content-type")) return "diff";
    return null;
  }

}
