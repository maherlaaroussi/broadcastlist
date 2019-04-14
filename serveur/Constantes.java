package serveur;

import pro.diff.*;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;

public class Constantes {

    public static final String CREATE = "create";
    public static final String DELETE = "delete";
    public static final String DELETEALL = "deleteall";
    public static final String SUBSCRIBE = "subscribe";
    public static final String UNSUBSCRIBE = "unsubscribe";
    public static final String SEND = "send";
    public static final String GET = "get";
    public static final int PORT_SERVEUR = 7777;
    public static final int PORT_ADMIN = 8888;
    public static final int N_THREADS = 10;
    public static DiffContentHandlerFactory factory = new DiffContentHandlerFactory();
    public static boolean factorySet = false;
    public static final String SMTP = "smtp.univ-paris13.fr";
    public static final int PORT_MAIL = 587;

    public static String getTime() {

      DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
      Calendar cal = Calendar.getInstance();
      return dateFormat.format(cal.getTime());

    }

}
