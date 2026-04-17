package UI;

import Casa.Utilizador;
import DomusControl.DomusControlFacade;

import java.util.Scanner;

public class MenuGestaoCasa {

    private TextUI menu;
    private Scanner scanner;
    private Utilizador user;
    private DomusControlFacade facade;
    private String idCasa;

    public MenuGestaoCasa(Utilizador user, DomusControlFacade facade, String idCasa) {
        this.scanner = TextUI.getScanner();
        this.user = user;
        this.facade = facade;
        this.idCasa = idCasa;

        this.menu = new TextUI("Gestão da Casa - " + this.idCasa, new String[]{
                "Adicionar Divisão",
                "Listar Divisões",
                "Gerir Divisão"
        });

        this.menu.setHandler(1, this::adicionar_divisao);
        this.menu.setHandler(2, this::listar_divisoes);
        this.menu.setHandler(3, this::abrir_menu_gestao_divisao);

        this.menu.run();
    }

    public void adicionar_divisao() {
        System.out.print("Nome da Divisão: ");
        String nomeDivisao = scanner.nextLine();

        boolean ok = this.facade.adicionarDivisaoACasa(this.idCasa, nomeDivisao);

        if (ok) {
            System.out.println("Divisão adicionada com sucesso.");
        } else {
            System.out.println("Não foi possível adicionar a divisão.");
        }
    }

    public void listar_divisoes() {
        String[] divisoes = this.facade.getDivisoesInfoCasa(this.idCasa);

        if (divisoes.length == 0) {
            System.out.println("Esta casa ainda não tem divisões.");
            return;
        }

        System.out.println("Divisões da casa " + this.idCasa + ":");
        for (String divisao : divisoes) {
            System.out.println("- " + divisao);
        }
    }

    public void abrir_menu_gestao_divisao() {
        String[] divisoesInfo = this.facade.getDivisoesInfoCasa(this.idCasa);
        String[] divisoesIds = this.facade.getIdsDivisoesCasa(this.idCasa);

        if (divisoesIds.length == 0) {
            System.out.println("Esta casa ainda não tem divisões.");
            return;
        }

        System.out.println("Divisões disponíveis:");
        for (String divisao : divisoesInfo) {
            System.out.println("- " + divisao);
        }

        System.out.print("Indique o ID da divisão que pretende gerir: ");
        String idDivisao = scanner.nextLine().trim();

        boolean existe = false;
        for (String divisaoId : divisoesIds) {
            if (divisaoId.equalsIgnoreCase(idDivisao)) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            System.out.println("Divisão inválida.");
            return;
        }

        new MenuGestaoDivisao(this.user, this.facade, this.idCasa, idDivisao);
    }
}