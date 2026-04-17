package UI;

import Casa.Utilizador;
import DomusControl.DomusControlFacade;
import Dispositivos.Dispositivo;

import java.util.Scanner;

public class MenuGestaoDivisao {

    private TextUI menu;
    private Scanner scanner;
    private Utilizador user;
    private DomusControlFacade facade;
    private String idCasa;
    private String idDivisao;

    public MenuGestaoDivisao(Utilizador user, DomusControlFacade facade, String idCasa, String idDivisao) {
        this.scanner = TextUI.getScanner();
        this.user = user;
        this.facade = facade;
        this.idCasa = idCasa;
        this.idDivisao = idDivisao;

        this.menu = new TextUI("Gestão da Divisão - " + this.idDivisao + " (Casa " + this.idCasa + ")", new String[]{
                "Adicionar Dispositivo",
                "Listar Dispositivos",
                "Ligar Dispositivo",
                "Desligar Dispositivo"
        });

        this.menu.setHandler(1, this::adicionar_dispositivo);
        this.menu.setHandler(2, this::listar_dispositivos);
        this.menu.setHandler(3, this::ligar_dispositivo);
        this.menu.setHandler(4, this::desligar_dispositivo);

        this.menu.run();
    }

    public void adicionar_dispositivo() {
        System.out.print("ID do Dispositivo: ");
        String idDispositivo = scanner.nextLine().trim().toUpperCase();

        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine().trim();

        System.out.print("Consumo por hora: ");
        double consumoPorHora;
        try {
            consumoPorHora = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Consumo inválido.");
            return;
        }

        Dispositivo dispositivo = new Dispositivo(idDispositivo, marca, modelo, consumoPorHora);

        boolean ok = this.facade.adicionarDispositivoADivisao(this.idCasa, this.idDivisao, dispositivo);

        if (ok) {
            System.out.println("Dispositivo adicionado com sucesso.");
        } else {
            System.out.println("Não foi possível adicionar o dispositivo.");
        }
    }

    public void listar_dispositivos() {
        String[] dispositivosInfo = this.facade.getDispositivosInfoDivisao(this.idCasa, this.idDivisao);

        if (dispositivosInfo.length == 0) {
            System.out.println("Esta divisão não tem dispositivos.");
            return;
        }

        System.out.println("Dispositivos da divisão " + this.idDivisao + ":");
        for (String dispositivo : dispositivosInfo) {
            System.out.println("- " + dispositivo);
        }
    }

    public void ligar_dispositivo() {
        String[] dispositivosInfo = this.facade.getDispositivosInfoDivisao(this.idCasa, this.idDivisao);
        String[] dispositivosIds = this.facade.getIdsDispositivosDivisao(this.idCasa, this.idDivisao);

        if (dispositivosIds.length == 0) {
            System.out.println("Esta divisão não tem dispositivos.");
            return;
        }

        System.out.println("Dispositivos disponíveis:");
        for (String dispositivo : dispositivosInfo) {
            System.out.println("- " + dispositivo);
        }

        System.out.print("Indique o ID do dispositivo que pretende ligar: ");
        String idDispositivo = scanner.nextLine().trim().toUpperCase();

        boolean existe = false;
        for (String dispositivoId : dispositivosIds) {
            if (dispositivoId.equalsIgnoreCase(idDispositivo)) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            System.out.println("Dispositivo inválido.");
            return;
        }

        boolean ok = this.facade.ligarDispositivo(this.idCasa, this.idDivisao, idDispositivo);

        if (ok) {
            System.out.println("Dispositivo ligado com sucesso.");
        } else {
            System.out.println("Não foi possível ligar o dispositivo.");
        }
    }

    public void desligar_dispositivo() {
        String[] dispositivosInfo = this.facade.getDispositivosInfoDivisao(this.idCasa, this.idDivisao);
        String[] dispositivosIds = this.facade.getIdsDispositivosDivisao(this.idCasa, this.idDivisao);

        if (dispositivosIds.length == 0) {
            System.out.println("Esta divisão não tem dispositivos.");
            return;
        }

        System.out.println("Dispositivos disponíveis:");
        for (String dispositivo : dispositivosInfo) {
            System.out.println("- " + dispositivo);
        }

        System.out.print("Indique o ID do dispositivo que pretende desligar: ");
        String idDispositivo = scanner.nextLine().trim().toUpperCase();

        boolean existe = false;
        for (String dispositivoId : dispositivosIds) {
            if (dispositivoId.equalsIgnoreCase(idDispositivo)) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            System.out.println("Dispositivo inválido.");
            return;
        }

        boolean ok = this.facade.desligarDispositivo(this.idCasa, this.idDivisao, idDispositivo);

        if (ok) {
            System.out.println("Dispositivo desligado com sucesso.");
        } else {
            System.out.println("Não foi possível desligar o dispositivo.");
        }
    }
}