import java.net.ServerSocket;
import broadcastlist.serveur.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {

    System.getProperties().put("java.protocol.handler.pkgs", "diff");
    ExecutorService pool = Executors.newFixedThreadPool(1);
    Thread serveur = new Thread(new Serveur(Serveur.port, pool));
    serveur.start();

  }

}
