package broadcastlist.serveur;

import broadcastlist.diff.DiffEntry;

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
import java.net.URL;
import java.io.*;


public class Esclave implements Runnable {

  /* ================== ATTRIBUTs ================== */

  private final Socket client;
  URL url;
  PrintWriter pw;
  BufferedReader read;


  /* ================== CONSTRUCTORs ================== */

  Esclave(Socket client) throws IOException {
    this.client = client;
    read = new BufferedReader(new InputStreamReader(client.getInputStream()));
    pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
  }


  /* ================== METHODs ================== */

  @Override
  public void run() {

    String req;
    DiffEntry data = null;

    while (true) {

      try {

        while ((req = read.readLine()) != null) {

          url = new URL(req);

          try { data = (DiffEntry) url.getContent(); }
          catch (IOException ex) { Logger.getLogger(Esclave.class.getName()).log(Level.SEVERE, null, ex); }

          String commande = data.getCommande();

          try {

            switch (commande) {

              case "Test": {
                pw.println("Modafaka !!.");
                pw.flush();
                break;
              }

            }

          } catch (Exception e) {

            try {
              pw.println(e.getMessage());
              pw.flush();

            } catch (Exception e1) { e1.printStackTrace(); }

          }

        }

      } catch (IOException e) {
        
        pw.println("format d'url incorrect !");
        pw.flush();

      }
    }

  }

}
