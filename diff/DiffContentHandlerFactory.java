package broadcastlist.diff;
import java.net.*;

class DiffContentHandlerFactory implements ContentHandlerFactory {

	public DiffContentHandlerFactory() {
	}

	public ContentHandler createContentHandler(String request) {
		return new DiffContentHandler();
	}
}
