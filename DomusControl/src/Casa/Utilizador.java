package Casa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utilizador {

    private String userID;
    private String password;
    private List<Casa> casasUtilizador;
    private List<Casa> casasAdministradas;

    public Utilizador() {
        this.userID = "";
        this.password = "";
        this.casasUtilizador = new ArrayList<>();
        this.casasAdministradas = new ArrayList<>();
    }

    public Utilizador(String userID, String password) {
        this.userID = userID;
        this.password = password;
        this.casasAdministradas = new ArrayList<>();
        this.casasUtilizador = new ArrayList<>();
    }

    public Utilizador(Utilizador u) {
        this.userID = u.getUserID();
        this.password = u.getPassword();
        this.casasUtilizador = u.getCasasUtilizador();
        this.casasAdministradas = u.getCasasAdministradas();
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public List<Casa> getCasasUtilizador() {
        return this.casasUtilizador.stream()
                .map(Casa::clone)
                .collect(Collectors.toList());
    }

    public List<Casa> getCasasAdministradas() {
        return this.casasAdministradas.stream()
                .map(Casa::clone)
                .collect(Collectors.toList());
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCasasUtilizador(List<Casa> casasUtilizador) {
        this.casasUtilizador = casasUtilizador.stream()
                .map(Casa::clone)
                .collect(Collectors.toList());
    }

    public void setCasasAdministradas(List<Casa> casasAdministradas) {
        this.casasAdministradas = casasAdministradas.stream()
                .map(Casa::clone)
                .collect(Collectors.toList());
    }

    public void adicionarCasaUtilizador(Casa casa) {
        this.casasUtilizador.add(casa.clone());
    }

    public void adicionarCasaAdministrada(Casa casa) {
        this.casasAdministradas.add(casa.clone());
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }
}