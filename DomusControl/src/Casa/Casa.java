package Casa;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Casa {
    private String idCasa;
    private Map<String, Divisao> divisoes;

    public Casa() {
        this.idCasa = "";
        this.divisoes = new HashMap<>();
    }

    public Casa(String idCasa) {
        this.idCasa = idCasa;
        this.divisoes = new HashMap<>();
    }

    public Casa(String idCasa, Map<String, Divisao> divisoes) {
        this.idCasa = idCasa;
        this.divisoes = divisoes.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().clone()
                ));
    }

    public Casa(Casa casa) {
        this.idCasa = casa.getIdCasa();
        this.divisoes = casa.getDivisoes();
    }

    public String getIdCasa() {
        return idCasa;
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

    public void setIdCasa(String idCasa) {
        this.idCasa = idCasa;
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