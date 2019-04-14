import java.net.*;
import java.io.*;
import serveur.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.URL;
import java.net.MalformedURLException;
import pro.diff.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;

public class Main {

  public static void main(String[] args) throws MalformedURLException {

    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Calendar cal = Calendar.getInstance();

    System.out.println("[" + dateFormat.format(cal.getTime()) + "] Hey yo ! Je lance la patate ...");
    System.getProperties().put("java.protocol.handler.pkgs", "pro");
    ExecutorService pool = Executors.newFixedThreadPool(Constantes.N_THREADS);

    Thread serveur = new Thread(new Serveur(Constantes.PORT_SERVEUR, pool));
    Thread admin = new Thread(new Serveur(Constantes.PORT_ADMIN, pool));

    serveur.start();
    admin.start();

  }

}
