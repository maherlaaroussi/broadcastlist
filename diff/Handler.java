package diff;

import java.net.*;

public class Handler extends URLStreamHandler {

	public DiffURLConnection openConnection(URL u) throws MalformedURLException {

		return new DiffURLConnection(u);

	}

}
