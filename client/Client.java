package client;

import java.io.*;
import java.net.*;
import java.security.Security;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;

public class Client {

  public static void main(String[] args) throws IOException {

    /*
    Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
    SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    SSLSocket connexion = (SSLSocket) factory.createSocket(InetAddress.getLocalHost(), serveur.Constantes.PORT_SERVEUR);

    //SSLSocket connexion = (SSLSocket) factory.createSocket(InetAddress.getLocalHost(), serveur.Constantes.PORT_ADMIN);
    */

    Socket connexion = new Socket(InetAddress.getLocalHost(), serveur.Constantes.PORT_SERVEUR); // Or PORT_ADMIN

    Writer output = new OutputStreamWriter(connexion.getOutputStream());
    BufferedReader input = new BufferedReader(new InputStreamReader(connexion.getInputStream()));

    output.write("diff://maher@outlook.fr/create?name=tata&password=toto&theme=Master");
    //output.write("diff://maher@outlook.fr/delete?name=tata&password=toto");
    //output.write("diff://maher@outlook.fr/subscribe?name=tata");
    //output.write("diff://maher@outlook.fr/send?name=tata&object=test&message=hola");
    //output.write("diff://maher@outlook.fr/get?theme=Master");
    //output.write("diff://maher@outlook.fr/deleteall");
    output.flush();

    //System.out.println(input.readLine());

    connexion.shutdownOutput();

  }

}
