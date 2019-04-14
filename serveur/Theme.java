package serveur;

public enum Theme {

  Licence("Licence"),
  Master("Master"),
  Doctorat("Doctorat");

  private String name = "";

  Theme(String name) { this.setName(name); }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
