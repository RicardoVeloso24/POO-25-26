package UI;

import Casa.Utilizador;
import DomusControl.DomusControlFacade;

import java.util.Scanner;

public class MenuPrincipal {

    private TextUI menu;
    private Scanner scanner;
    private DomusControlFacade facade;

    public MenuPrincipal() {
        this.scanner = TextUI.getScanner();
        this.facade = new DomusControlFacade();

        this.menu = new TextUI("Domus Control - Menu Principal", new String[]{
                "Registar Novo Utilizador",
                "Login"
        });

        this.menu.setHandler(1, this::registar_novo_utilizador);
        this.menu.setHandler(2, this::fazer_login);

        mostrarBoasVindas();
        this.menu.run();
        mostrarDespedida();
    }

    private void mostrarBoasVindas() {
        limparEcra();
        System.out.println(Text.ANSI_YELLOW + Text.ANSI_BOLD);
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                           ║");
        System.out.println("║                BEM-VINDO À DOMUS CONTROL                  ║");
        System.out.println("║                                                           ║");
        System.out.println("║                       Menu Principal                      ║");
        System.out.println("║                                                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println(Text.ANSI_RESET);
    }

    private void mostrarDespedida() {
        limparEcra();
        System.out.println(Text.ANSI_GREEN + Text.ANSI_BOLD);
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                           ║");
        System.out.println("║               Obrigado por usar o sistema!                ║");
        System.out.println("║                                                           ║");
        System.out.println("║                        Até breve!                         ║");
        System.out.println("║                                                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println(Text.ANSI_RESET + "\n");
    }

    private void limparEcra() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void registar_novo_utilizador() {
        System.out.print("UserID: ");
        String userID = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean ok = this.facade.registarUtilizador(userID, password);

        if (ok) {
            System.out.println("Utilizador registado com sucesso.");
        } else {
            System.out.println("Não foi possível registar o utilizador. Verifique se o userID já existe e se os dados são válidos.");
        }
    }

    private void fazer_login() {
        System.out.print("UserID: ");
        String userID = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        Utilizador login = this.facade.fazerLogin(userID, password);

        if (login != null) {
            new MenuUtilizador(login, this.facade);
        } else {
            System.out.println("Login mal sucedido!");
        }
    }
}