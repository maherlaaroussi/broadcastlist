package client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

  public static void main(String[] args) throws IOException {

    Socket connexion = new Socket(InetAddress.getLocalHost(), 33333);
    Writer output = new OutputStreamWriter(
    connexion.getOutputStream());

    output.write("Test 12476389");
    output.flush();

    connexion.shutdownOutput();

  }

}
