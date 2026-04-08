package DomusControl;

import Casa.Utilizador;

public interface DomusControlLogin {
     boolean registarUtilizador(String userID, String password);
     Utilizador fazerLogin(String userID, String password);
}