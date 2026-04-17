package Casa;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Casa {

    private static int proximoId = 0;

    private String idCasa;
    private String nomeCasa;
    private Map<String, Divisao> divisoes;

    public Casa() {
        this.idCasa = String.valueOf(proximoId++);
        this.nomeCasa = "";
        this.divisoes = new HashMap<>();
    }

    public Casa(String nomeCasa) {
        this.idCasa = String.valueOf(proximoId++);
        this.nomeCasa = nomeCasa;
        this.divisoes = new HashMap<>();
    }

    public Casa(String nomeCasa, Map<String, Divisao> divisoes) {
        this.idCasa = String.valueOf(proximoId++);
        this.nomeCasa = nomeCasa;
        this.divisoes = divisoes.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().clone()
                ));
    }

    public Casa(Casa casa) {
        this.idCasa = casa.getIdCasa();
        this.nomeCasa = casa.getNomeCasa();
        this.divisoes = casa.getDivisoes();
    }

    public String getIdCasa() {
        return this.idCasa;
    }

    public String getNomeCasa() {
        return this.nomeCasa;
    }

    public Map<String, Divisao> getDivisoes() {
        return this.divisoes.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().clone()
                ));
    }

    public boolean adicionarDivisao(Divisao divisao) {
        if (divisao == null || this.divisoes.containsKey(divisao.getIdDivisao())) {
            return false;
        }

        this.divisoes.put(divisao.getIdDivisao(), divisao.clone());
        return true;
    }

    public boolean removerDivisao(String idDivisao) {
        return this.divisoes.remove(idDivisao) != null;
    }

    public Divisao getDivisao(String idDivisao) {
        Divisao divisao = this.divisoes.get(idDivisao);
        return divisao == null ? null : divisao.clone();
    }

    public Divisao getDivisaoInterna(String idDivisao) {
        return this.divisoes.get(idDivisao);
    }

    public boolean existeDivisao(String idDivisao) {
        return this.divisoes.containsKey(idDivisao);
    }

    public int getNumeroDivisoes() {
        return this.divisoes.size();
    }

    public void setNomeCasa(String nomeCasa) {
        this.nomeCasa = nomeCasa;
    }

    public void setDivisoes(Map<String, Divisao> divisoes) {
        this.divisoes = divisoes.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().clone()
                ));
    }

    public Casa clone() {
        return new Casa(this);
    }
}