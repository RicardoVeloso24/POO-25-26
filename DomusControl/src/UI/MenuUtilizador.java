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

        System.out.println("Bem-vindo " + this.user.getUserID() + " !");

        this.menu = new TextUI("Menu Utilizador", new String[]{
                "Registar Nova Casa",
                "Listar Casas do Utilizador",
                "Listar Casas Administradas"
        });

        this.menu.setHandler(1, this::registar_casa);
        this.menu.setHandler(2, this::listar_casas_utilizador);
        this.menu.setHandler(3, this::listar_casas_administradas);

        this.menu.run();
    }

    public void registar_casa() {
        System.out.print("ID da Casa: ");
        String idCasa = scanner.nextLine();

        boolean ok = this.facade.registarCasa(this.user.getUserID(), idCasa);

        if (ok) {
            System.out.println("Casa registada com sucesso.");
        } else {
            System.out.println("Não foi possível registar a casa.");
        }
    }

    public void listar_casas_utilizador() {
        String[] casas = this.facade.getcasasIdUtilizador(this.user.getUserID());

        if (casas.length == 0) {
            System.out.println("O utilizador não tem casas associadas.");
            return;
        }

        System.out.println("Casas do utilizador:");
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
}