package UI;

import Casa.Utilizador;
import DomusControl.DomusControlFacade;

import java.util.Scanner;

public class MenuUtilizador {

    private TextUI menu;
    private Scanner scanner;
    private Utilizador user;
    private DomusControlFacade facade;

    public MenuUtilizador(Utilizador user, DomusControlFacade facade) {
        this.scanner = TextUI.getScanner();
        this.user = user;
        this.facade = facade;

        this.menu = new TextUI("Menu Utilizador", new String[]{
                "Registar Nova Casa",
                "Listar Casas do Utilizador",
                "Listar Casas Administradas",
                "Gerir uma Casa"
        });

        this.menu.setHandler(1, this::registar_casa);
        this.menu.setHandler(2, this::listar_casas_utilizador);
        this.menu.setHandler(3, this::listar_casas_administradas);
        this.menu.setHandler(4, this::abrir_menu_gestao_casa);

        this.menu.run();
    }

    public void registar_casa() {
        System.out.print("Nome da Casa: ");
        String nomeCasa = scanner.nextLine();

        boolean ok = this.facade.registarCasa(this.user.getUserID(), nomeCasa);

        if (ok) {
            System.out.println("Casa registada com sucesso.");
        } else {
            System.out.println("Não foi possível registar a casa.");
        }
    }

    public void listar_casas_utilizador() {
        String[] casas = this.facade.getcasasInfoUtilizador(this.user.getUserID());

        if (casas.length == 0) {
            System.out.println("O utilizador não tem casas associadas.");
            return;
        }

        System.out.println("Casas do utilizador " + this.user.getUserID() + ":");
        for (String casa : casas) {
            System.out.println("- " + casa);
        }
    }

    public void listar_casas_administradas() {
        String[] casas = this.facade.getcasasIdAdministrativas(this.user.getUserID());

        if (casas.length == 0) {
            System.out.println("O utilizador não administra nenhuma casa.");
            return;
        }

        System.out.println("Casas administradas:");
        for (String casa : casas) {
            System.out.println("- " + casa);
        }
    }

    public void abrir_menu_gestao_casa() {
        String[] casasInfo = this.facade.getcasasInfoUtilizador(this.user.getUserID());
        String[] casasIds = this.facade.getcasasIdUtilizador(this.user.getUserID());

        if (casasIds.length == 0) {
            System.out.println("O utilizador não tem casas para gerir.");
            return;
        }

        System.out.println("Casas disponíveis:");
        for (String casa : casasInfo) {
            System.out.println("- " + casa);
        }

        System.out.print("Indique o ID da casa que pretende gerir: ");
        String idCasa = scanner.nextLine().trim();

        boolean existe = false;
        for (String casaId : casasIds) {
            if (casaId.equalsIgnoreCase(idCasa)) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            System.out.println("Casa inválida.");
            return;
        }

        new MenuGestaoCasa(this.user, this.facade, idCasa);
    }
}