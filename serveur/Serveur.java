package broadcastlist.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import broadcastlist.serveur.ListeDeDiffusion.Theme;

public class Serveur implements Runnable {

  /* ================== ATTRIBUTs ================== */

  private ServerSocket serverSocket;
  public static final int port = 33333;
  private ExecutorService pool = null;


  /* ================== CONSTRUCTORs ================== */

  public Serveur(int port, ExecutorService pool) {

    try {
      this.serverSocket = new ServerSocket(port, 1);
      this.pool = pool;
    } catch (IOException ex) {
      Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  @Override
  public void run() {

    while (true) {

      try {
        pool.execute(new Esclave(serverSocket.accept()));
      }

      catch (IOException e) { System.out.println(e); }

    }

  }


  /* ================== METHODs ================== */


  /* ================== GETTERs & SETTERs ================== */

  public ServerSocket getServerSocket() {
    return serverSocket;
  }

  public void setServerSocket(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
  }

  public static int getPort() {
    return port;
  }

  public ExecutorService getPool() {
    return pool;
  }

  public void setPool(ExecutorService pool) {
    this.pool = pool;
  }

}
