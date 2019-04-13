package broadcastlist.diff;

import java.net.*;
import java.io.*;

public class DiffURLConnection extends URLConnection {

    public DiffURLConnection(URL url) throws MalformedURLException {
  		super(url);
  		setContentHandlerFactory(new DiffContentHandlerFactory());
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
