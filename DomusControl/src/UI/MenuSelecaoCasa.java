package UI;

import java.util.Scanner;

public class MenuSelecaoCasa {

    private TextUI menu;
    private Scanner scanner;
    private String[] casasUtilizador;
    private String[] casasAdministrador;
    private String casaSelecionada;

    public MenuSelecaoCasa(String[] casasUtilizador, String[] casasAdministrador) {
        this.scanner = TextUI.getScanner();
        this.casasUtilizador = casasUtilizador;
        this.casasAdministrador = casasAdministrador;
        this.casaSelecionada = null;

        String[] junto = new String[casasUtilizador.length + casasAdministrador.length];

        System.arraycopy(casasUtilizador, 0, junto, 0, casasUtilizador.length);
        System.arraycopy(casasAdministrador, 0, junto, casasUtilizador.length, casasAdministrador.length);

        this.menu = new TextUI("Selecione uma casa:", junto);

        for (int i = 0; i < junto.length; i++) {
            final String casaId = junto[i];
            this.menu.setHandler(i + 1, () -> this.casa_selecionada(casaId));
        }
    }

    public void casa_selecionada(String casaID) {
        this.casaSelecionada = casaID;
        System.out.println("Casa selecionada: " + casaID);
    }

    public String selecionarCasa() {
        this.menu.run();
        return this.casaSelecionada;
    }
}