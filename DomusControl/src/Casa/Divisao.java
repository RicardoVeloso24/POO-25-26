package Casa;

import Dispositivos.Dispositivo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Divisao {

    private static int proximoId = 0;

    private String idDivisao;
    private String nome_comum_divisao;
    private List<Dispositivo> dispositivos;

    public Divisao() {
        this.idDivisao = String.valueOf(proximoId++);
        this.nome_comum_divisao = "";
        this.dispositivos = new ArrayList<>();
    }

    public Divisao(String nome_comum_divisao) {
        this.idDivisao = String.valueOf(proximoId++);
        this.nome_comum_divisao = nome_comum_divisao;
        this.dispositivos = new ArrayList<>();
    }

    public Divisao(String nome_comum_divisao, List<Dispositivo> dispositivos) {
        this.idDivisao = String.valueOf(proximoId++);
        this.nome_comum_divisao = nome_comum_divisao;
        this.dispositivos = dispositivos.stream()
                .map(Dispositivo::clone)
                .collect(Collectors.toList());
    }

    public Divisao(Divisao divisao) {
        this.idDivisao = divisao.getIdDivisao();
        this.nome_comum_divisao = divisao.getNome_comum_divisao();
        this.dispositivos = divisao.getDispositivos();
    }

    public String getIdDivisao() {
        return this.idDivisao;
    }

    public String getNome_comum_divisao() {
        return this.nome_comum_divisao;
    }

    public List<Dispositivo> getDispositivos() {
        return this.dispositivos.stream()
                .map(Dispositivo::clone)
                .collect(Collectors.toList());
    }

    public Dispositivo getDispositivoInterno(String idDispositivo) {
        return this.dispositivos.stream()
                .filter(d -> d.getIdentificador().equals(idDispositivo))
                .findFirst()
                .orElse(null);
    }

    public void setNome_comum_divisao(String nome_comum_divisao) {
        this.nome_comum_divisao = nome_comum_divisao;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos.stream()
                .map(Dispositivo::clone)
                .collect(Collectors.toList());
    }

    public boolean adicionarDispositivo(Dispositivo dispositivo) {
        if (dispositivo == null || existeDispositivo(dispositivo.getIdentificador())) {
            return false;
        }

        this.dispositivos.add(dispositivo.clone());
        return true;
    }

    public boolean removerDispositivo(String idDispositivo) {
        return this.dispositivos.removeIf(d -> d.getIdentificador().equals(idDispositivo));
    }

    public boolean existeDispositivo(String idDispositivo) {
        return this.dispositivos.stream()
                .anyMatch(d -> d.getIdentificador().equals(idDispositivo));
    }

    public Dispositivo getDispositivo(String idDispositivo) {
        return this.dispositivos.stream()
                .filter(d -> d.getIdentificador().equals(idDispositivo))
                .findFirst()
                .map(Dispositivo::clone)
                .orElse(null);
    }

    public int getNumeroDispositivos() {
        return this.dispositivos.size();
    }

    public Divisao clone() {
        return new Divisao(this);
    }
}