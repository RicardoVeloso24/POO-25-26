package DomusControl;

import Dispositivos.Dispositivo;

public interface DomusControlCriacao {
     boolean registarCasa(String userID, String idCasa);
     boolean adicionarDivisaoACasa(String idCasa, String nomeDivisao);
     public String[] getcasasIdUtilizador(String userID);
     public String[] getcasasInfoUtilizador(String userID);
     String[] getcasasIdAdministrativas(String userID);

     String[] getIdsDivisoesCasa(String idCasa);
     boolean adicionarDispositivoADivisao(String idCasa, String idDivisao, Dispositivo dispositivo);
     String[] getIdsDispositivosDivisao(String idCasa, String idDivisao);
     boolean ligarDispositivo(String idCasa, String idDivisao, String idDispositivo);
     boolean desligarDispositivo(String idCasa, String idDivisao, String idDispositivo);
}