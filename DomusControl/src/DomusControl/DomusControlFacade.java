package DomusControl;

import Casa.Casa;
import Casa.Divisao;
import Casa.Utilizador;
import Dispositivos.Dispositivo;

import java.util.HashMap;
import java.util.Map;

public class DomusControlFacade implements DomusControlLogin, DomusControlCriacao {
    private Map<String, Utilizador> utilizadores;
    private Map<String, Casa> casas;

    public DomusControlFacade() {
        this.utilizadores = new HashMap<>();
        this.casas = new HashMap<>();
        adicionarUtilizadoresExemplo();
    }

    private void adicionarUtilizadoresExemplo() {
        this.utilizadores.put("joao123", new Utilizador("joao123", "senha123"));
        this.utilizadores.put("maria456", new Utilizador("maria456", "abc456"));
        this.utilizadores.put("admin", new Utilizador("admin", "admin123"));
    }

    public boolean existeUtilizador(String userID) {
        return this.utilizadores.containsKey(userID);
    }

    public boolean registarUtilizador(String userID, String password) {
        if (userID == null || password == null) {
            return false;
        }

        userID = userID.trim();
        password = password.trim();

        if (userID.isEmpty() || userID.length() < 3 || !userID.matches("[a-zA-Z0-9]+")) {
            return false;
        }

        if (password.length() < 4) {
            return false;
        }

        if (this.utilizadores.containsKey(userID)) {
            return false;
        }

        Utilizador novoUtilizador = new Utilizador(userID, password);
        this.utilizadores.put(userID, novoUtilizador);
        return true;
    }

    public Utilizador fazerLogin(String userID, String password) {
        if (userID == null || password == null) {
            return null;
        }

        Utilizador utilizador = this.utilizadores.get(userID.trim());

        if (utilizador != null && utilizador.getPassword().equals(password.trim())) {
            return utilizador.clone();
        }

        return null;
    }

    public boolean registarCasa(String userID, String nomeCasa) {
        if (userID == null || nomeCasa == null) {
            return false;
        }

        Utilizador utilizador = this.utilizadores.get(userID);
        if (utilizador == null) {
            return false;
        }

        nomeCasa = nomeCasa.trim();

        if (nomeCasa.isEmpty()) {
            return false;
        }

        Casa novaCasa = new Casa(nomeCasa);
        this.casas.put(novaCasa.getIdCasa(), novaCasa);
        utilizador.adicionarCasaAdministrada(novaCasa);
        utilizador.adicionarCasaUtilizador(novaCasa);

        return true;
    }

    public boolean adicionarDivisaoACasa(String idCasa, String nomeDivisao) {
        if (idCasa == null || nomeDivisao == null) {
            return false;
        }

        Casa casa = this.casas.get(idCasa.trim());
        if (casa == null) {
            return false;
        }

        nomeDivisao = nomeDivisao.trim();
        if (nomeDivisao.isEmpty()) {
            return false;
        }

        Divisao novaDivisao = new Divisao(nomeDivisao);
        return casa.adicionarDivisao(novaDivisao);
    }

    public Casa getCasa(String idCasa) {
        if (idCasa == null) {
            return null;
        }

        Casa casa = this.casas.get(idCasa.trim());
        return casa == null ? null : casa.clone();
    }

    public String[] getIdsDivisoesCasa(String idCasa) {
        if (idCasa == null) {
            return new String[0];
        }

        Casa casa = this.casas.get(idCasa.trim());
        if (casa == null) {
            return new String[0];
        }

        return casa.getDivisoes()
                .keySet()
                .toArray(new String[0]);
    }

    public String[] getDispositivosInfoDivisao(String idCasa, String idDivisao) {
        if (idCasa == null || idDivisao == null) {
            return new String[0];
        }

        Casa casa = this.casas.get(idCasa.trim());
        if (casa == null) {
            return new String[0];
        }

        Divisao divisao = casa.getDivisao(idDivisao.trim());
        if (divisao == null) {
            return new String[0];
        }

        return divisao.getDispositivos()
                .stream()
                .map(d -> d.getIdentificador() + " - " + d.getMarca() + " " + d.getModelo())
                .toArray(String[]::new);
    }

    public String[] getDivisoesInfoCasa(String idCasa) {
        if (idCasa == null) {
            return new String[0];
        }

        Casa casa = this.casas.get(idCasa.trim());
        if (casa == null) {
            return new String[0];
        }

        return casa.getDivisoes()
                .values()
                .stream()
                .map(d -> d.getIdDivisao() + " - " + d.getNome_comum_divisao())
                .toArray(String[]::new);
    }

    public boolean adicionarDispositivoADivisao(String idCasa, String idDivisao, Dispositivo dispositivo) {
        if (idCasa == null || idDivisao == null || dispositivo == null) {
            return false;
        }

        Casa casa = this.casas.get(idCasa.trim());
        if (casa == null) {
            return false;
        }

        Divisao divisao = casa.getDivisaoInterna(idDivisao.trim());
        if (divisao == null) {
            return false;
        }

        return divisao.adicionarDispositivo(dispositivo);
    }

    public String[] getIdsDispositivosDivisao(String idCasa, String idDivisao) {
        if (idCasa == null || idDivisao == null) {
            return new String[0];
        }

        Casa casa = this.casas.get(idCasa.trim());
        if (casa == null) {
            return new String[0];
        }

        Divisao divisao = casa.getDivisao(idDivisao.trim());
        if (divisao == null) {
            return new String[0];
        }

        return divisao.getDispositivos()
                .stream()
                .map(Dispositivo::getIdentificador)
                .toArray(String[]::new);
    }

    public boolean ligarDispositivo(String idCasa, String idDivisao, String idDispositivo) {
        if (idCasa == null || idDivisao == null || idDispositivo == null) {
            return false;
        }

        Casa casa = this.casas.get(idCasa.trim());
        if (casa == null) {
            return false;
        }

        Divisao divisao = casa.getDivisaoInterna(idDivisao.trim());
        if (divisao == null) {
            return false;
        }

        Dispositivo dispositivo = divisao.getDispositivoInterno(idDispositivo.trim());
        if (dispositivo == null) {
            return false;
        }

        dispositivo.turnON();
        return true;
    }

    public boolean desligarDispositivo(String idCasa, String idDivisao, String idDispositivo) {
        if (idCasa == null || idDivisao == null || idDispositivo == null) {
            return false;
        }

        Casa casa = this.casas.get(idCasa.trim());
        if (casa == null) {
            return false;
        }

        Divisao divisao = casa.getDivisaoInterna(idDivisao.trim());
        if (divisao == null) {
            return false;
        }

        Dispositivo dispositivo = divisao.getDispositivoInterno(idDispositivo.trim());
        if (dispositivo == null) {
            return false;
        }

        dispositivo.turnOFF();
        return true;
    }

    @Override
    public String[] getcasasIdUtilizador(String userID) {
        Utilizador utilizador = this.utilizadores.get(userID);
        if (utilizador == null) {
            return new String[0];
        }

        return utilizador.getCasasUtilizador()
                .stream()
                .map(Casa::getIdCasa)
                .toArray(String[]::new);
    }

    @Override
    public String[] getcasasInfoUtilizador(String userID) {
        Utilizador utilizador = this.utilizadores.get(userID);
        if (utilizador == null) {
            return new String[0];
        }

        return utilizador.getCasasUtilizador()
                .stream()
                .map(c -> c.getIdCasa() + " - " + c.getNomeCasa())
                .toArray(String[]::new);
    }

    public String[] getcasasIdAdministrativas(String userID) {
        Utilizador utilizador = this.utilizadores.get(userID);
        if (utilizador == null) {
            return new String[0];
        }

        return utilizador.getCasasAdministradas()
                .stream()
                .map(Casa::getIdCasa)
                .toArray(String[]::new);
    }
}