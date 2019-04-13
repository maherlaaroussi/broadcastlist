import java.net.ServerSocket;
import serveur.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {

    System.out.println("[+] Hey ya ! Lancement du serveur ...");
    System.getProperties().put("java.protocol.handler.pkgs", "diff");
    ExecutorService pool = Executors.newFixedThreadPool(1);
    Thread serveur = new Thread(new Serveur(33333, pool));
    serveur.start();

  }

}
