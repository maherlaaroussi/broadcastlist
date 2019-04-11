import java.net.ServerSocket;
import serveurbroadcast.*;

public class Main {

  public static void main(String[] args) {

    Serveur serveur = new Serveur(Serveur.port, 1);
    serveur.manageDiffusion();

  }

}
