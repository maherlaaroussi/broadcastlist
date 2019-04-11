
package serveurbroadcast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class Esclave implements Runnable {

  private final Socket socket;
  private final Serveur serveur;
  Esclave(Socket socket, Serveur serveur) {
    this.socket = socket;
    this.serveur = serveur;
  }

  @Override
  public void run() {

    try {

      BufferedReader input = new BufferedReader(
      new InputStreamReader(socket.getInputStream(),
      "8859_1"),1024); // flux en lecture
      StringBuffer sb = new StringBuffer();
      sb.append(input.readLine());
      System.out.println(sb);
      String[] commande = sb.toString().split(" ");

      switch (commande[0]) {

        case "Créer":
          System.out.println("Hello modafaka !");
          break;

        case "Supprimer":
          System.out.println("Dégage connard !");
          break;

        case "S'abonner":
          System.out.println("Oh wi !");
          break;

        case "Désabonnement":
          System.out.println("Ho nan !");
          break;

        case "Fin":
        System.out.println("Sayonara :(");
          break;

        default: ;

      }

    }

    catch (IOException e) { System.out.println(e); }

    finally {
      try { if (socket != null) socket.close(); }
      catch (IOException e) { System.out.println(e); }
    }
  }


}
