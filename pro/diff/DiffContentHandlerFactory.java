package pro.diff;
import java.net.*;

public class DiffContentHandlerFactory implements ContentHandlerFactory {

	public DiffContentHandlerFactory() {
	}

	public ContentHandler createContentHandler(String request) {
		return new DiffContentHandler();
	}
}
