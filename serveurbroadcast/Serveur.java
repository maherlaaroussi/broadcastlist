package serveurbroadcast;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import serveurbroadcast.ListeDeDiffusion.Theme;

public class Serveur {

  /* ================== ATTRIBUTs ================== */

  private ServerSocket serverSocket;
  private Socket socket;
  public static final int port = 33333;
  private static final int poolSize = 10;
  private ExecutorService pool = null;
  private Boolean isFinished = false;


  /* ================== CONSTRUCTORs ================== */

  public Serveur(int port, int size) {

    try {
      serverSocket = new ServerSocket(port,size);
      pool = Executors.newFixedThreadPool(poolSize);
    } catch (IOException ex) {
      Logger.getLogger(Serveur.class.
      getName()).log(Level.SEVERE, null, ex);
    }

  }

  public void manageDiffusion() {

    while (!isFinished) {

      try {
        pool.execute(new Esclave(serverSocket.accept(),this));
      }

      catch (IOException e) {System.out.println(e);}

      finally {
        try { if (socket != null) socket.close();}
        catch (IOException e) {}
        }

      }

    }


    /* ================== METHODs ================== */

    public static ListeDeDiffusion createListeDeDiffusion(String nom) {
      Predicate<Enseignant> isFound = e -> e.nom.equals(nom);
      //        Predicate<Enseignant> isFound = e -> {
      //           e.getClass().getDeclaredField(nom).equals(nom);
      //        };
      Enseignant e = Enseignant.personnel.stream().
      filter(isFound).findFirst().get();
      return e;
    }

    /* ================== GETTERs & SETTERs ================== */

    public ServerSocket getServerSocket() {
      return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
      this.serverSocket = serverSocket;
    }

    public Socket getSocket() {
      return socket;
    }

    public void setSocket(Socket socket) {
      this.socket = socket;
    }

  }
