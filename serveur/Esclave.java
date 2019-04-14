package serveur;

import pro.diff.DiffEntry;

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
import java.util.ArrayList;
import java.net.URL;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import serveur.Constantes;


public class Esclave implements Runnable {

  /* ================== ATTRIBUTs ================== */

  private final Socket client;
  URL url = null;
  PrintWriter pw;
  BufferedReader reader;


  /* ================== CONSTRUCTORs ================== */

  Esclave(Socket client) throws IOException {
    this.client = client;
    reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
    pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
  }


  /* ================== METHODs ================== */

  @Override
  public void run() {

    String req;
    DiffEntry data = null;


    try {

      if ((req = reader.readLine()) != null) {

        System.out.println("[+][" + Constantes.getTime() + "] " + req);

        url = new URL(req);

        try { data = (DiffEntry) url.getContent(); }
        catch (IOException ex) { Logger.getLogger(Esclave.class.getName()).log(Level.SEVERE, null, ex); }

        String commande = data.getCommande();

        try {

          switch (commande) {

            case Constantes.CREATE: {
              createListeDeDiffusion(data.getNomListeDiffusion(), data.getPassword(), Theme.valueOf(data.getTheme()), data.getMail());
              break;
            }

            case Constantes.DELETE: {
              deleteListeDeDiffusion(data.getNomListeDiffusion(), data.getPassword());
              break;
            }

            case Constantes.SUBSCRIBE: {
              subscribeListeDeDiffusion(data.getNomListeDiffusion(), data.getMail());
              break;
            }

            case Constantes.UNSUBSCRIBE: {
              unsubscribeListeDeDiffusion(data.getNomListeDiffusion(), data.getMail());
              break;
            }

            case Constantes.SEND: {
              sendMessageListeDeDiffusion(data.getNomListeDiffusion(), data.getMessage(), data.getObject());
              break;
            }

            case Constantes.GET: {
              getListesDeDiffusion(data.getTheme());
              break;
            }

            case Constantes.DELETEALL: {
              deleteAllLists();
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

      pw.println("Format de l'URL incorrect.");
      pw.flush();

    }

  }

  private synchronized void createListeDeDiffusion(String nom, String password, Theme theme, String mail) {

    if (Personne.isValid(mail)) {

      Personne diffuseur = Personne.fiundByMail(mail);
      ListeDeDiffusion listeDiffusion = ListeDeDiffusion.findListeDeDiffusionByNom(nom);

      if (diffuseur == null) {
        diffuseur = new Personne(mail);
      }

      if (listeDiffusion == null) {
        ListeDeDiffusion.liste.add(new ListeDeDiffusion(nom, theme, diffuseur, password));
        showMessage("erreur", "Création de la liste " + nom + " par " + mail, "Liste créee.");
      } else {
        showMessage("erreur", "La liste " + nom + " existe déjà.", "Liste déjà existante.");
      }

    }

  }

  private synchronized void deleteListeDeDiffusion(String nom, String password) {

    ListeDeDiffusion listeDiffusion = ListeDeDiffusion.findListeDeDiffusionByNom(nom);

    if (listeDiffusion == null) {
      showMessage("erreur", "La liste " + nom + " n'existe pas", "Aucune liste ne porte ce nom.");
    } else {
      if (listeDiffusion.getPassword().equals(password) || client.getLocalPort() == Constantes.PORT_ADMIN) {
        ListeDeDiffusion.liste.remove(listeDiffusion);
        showMessage("information","Suppression de la liste " + nom, "Liste supprimée !");
      } else {
        showMessage("erreur", "Mot de passe incorrect :/", "Mot de passe incorrect !");
      }
    }

  }

  private synchronized void subscribeListeDeDiffusion(String nom, String mail) {

    if (Personne.isValid(mail)) {

      Personne diffuseur = Personne.fiundByMail(mail);
      ListeDeDiffusion listeDiffusion = ListeDeDiffusion.findListeDeDiffusionByNom(nom);

      if (diffuseur == null) {
        diffuseur = new Personne(mail);
      }

      if (listeDiffusion != null && !listeDiffusion.isAbonneeExist(diffuseur)) {
        listeDiffusion.addAbonnee(diffuseur);
        showMessage("information", mail + " s'est abonnée à la liste " + nom, "Vous êtes maintenant abonnée.");
      } else {
        showMessage("erreur", "Oww, erreur quelque part :/ ...", "Erreur, à toi de trouver ....");
      }

    }

  }

  private synchronized void unsubscribeListeDeDiffusion(String nom, String mail) {

    if (Personne.isValid(mail)) {

      Personne diffuseur = Personne.fiundByMail(mail);
      ListeDeDiffusion listeDiffusion = ListeDeDiffusion.findListeDeDiffusionByNom(nom);

      if (diffuseur == null) {
        diffuseur = new Personne(mail);
      }

      if (listeDiffusion != null && listeDiffusion.isAbonneeExist(diffuseur)) {
        listeDiffusion.removeAbonnee(diffuseur);
        showMessage("information", mail + " s'est déqabonnée à la liste " + nom, "Vous êtes maintenant déqabonnée.");
      } else {
        showMessage("erreur", "Ayy, erreur quelque part :/ ...", "Erreur, contact toi-même ....");
      }

    }

  }

  private synchronized void sendMessageListeDeDiffusion(String nom, String mail, String message) {

    ListeDeDiffusion l = ListeDeDiffusion.findListeDeDiffusionByNom(nom);

    if (l != null) {
      try {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", Constantes.SMTP);
        props.put("mail.smtp.port", Constantes.PORT_MAIL);

        /* Timeout de 3s */
        props.put("mail.smtp.connectiontimeout", "3000");
        props.put("mail.smtp.timeout", "3000");

        Session session = Session.getInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(l.getDiffuseur().getMail()));
        msg.setSubject("Test");
        msg.setText(message);

        for (Personne p : l.getAbonnes()) {
          msg.addRecipient(Message.RecipientType.TO, new InternetAddress(p.getMail()));
        }

        Transport.send(msg);
        showMessage("information", "Les mails viennent juste de prendre leur envol youpi !", "Les mails ont été envoyés.");

      } catch (MessagingException e) {
        showMessage("erreur", "Oh gaad, problème d'envoi !!", "Erreur pendant l'envoi du message !");
      }
    } else {
      showMessage("erreur", "Eyy la liste est introuvable :/!", "La liste n'existe pas !");
    }
  }

  private synchronized void getListesDeDiffusion(String theme) {

    StringBuilder sb = new StringBuilder();
    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
    sb.append("<ListeDeDiffusions>");
    final boolean[] found = { false };
    ListeDeDiffusion.liste.stream().filter(e -> e.getTheme().equals(theme)).forEach(e -> {
      found[0] = true;
      sb.append("<ListeDeDiffusion>");
      sb.append("<nom>");
      sb.append(e.getNom());
      sb.append("</nom>");
      sb.append("<motDePasse>");
      sb.append(e.getPassword());
      sb.append("</motDePasse>");
      sb.append("<theme>");
      sb.append(e.getTheme().getName());
      sb.append("</theme>");
      sb.append("<diffuseur>");
      sb.append("<email>");
      sb.append(e.getDiffuseur().getMail());
      sb.append("</email>");
      sb.append("</diffuseur>");
      sb.append("<abonnes>");
      for (Personne p : e.getAbonnes()) {
        sb.append("<personne>");
        sb.append("<email>");
        sb.append(p.getMail());
        sb.append("</email>");
        sb.append("</personne>");
      }
      sb.append("</abonnes>");
      sb.append("</ListeDeDiffusion>");
    });
    sb.append("</ListeDeDiffusions>\n");
    try {
      client.getOutputStream().write(sb.toString().getBytes());
      showMessage("information", "XML envoyé hé ouais !", "XML généré avec succès.");
    } catch (IOException e) {
      showMessage("erreur", "Ho nan ...", "Erreur récupération du XML !");
    }

  }

  private synchronized void deleteAllLists() {
      if (client.getLocalPort() == Constantes.PORT_ADMIN) {
          ListeDeDiffusion.liste.clear();
          showMessage("erreur", "Hop tout a disparu !!", "Toutes les listes ont été effacées.");
      } else {
          showMessage("information", "Haalte ! un intrus essaye de tout supprimer !", "Halte là, t'es pas autorisé à faire cela !");
      }
  }

  private String getTime() {
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    return dateFormat.format(cal.getTime());
  }

  private void showMessage(String m, String mInterne, String mExterne) {

    String symb = "";

    switch (m) {
      case "information":
      symb = "[!]"; break;
      case "erreur":
      symb = "[?]"; break;
      case "plus":
      symb = "[+]"; break;
    }

    symb = symb + "[" + Constantes.getTime() + "]";

    System.out.println(symb + " " + mInterne);
    pw.write(symb + " " + mExterne);
    pw.flush();

  }

}
