package Dispositivos;

public class Dispositivo {

    private String identificador;
    private String marca;
    private String modelo;
    private double consumoPorHora;
    private boolean isON;
    private int numeroAtivacoes;
    private double horasDeUso;

    public Dispositivo() {
        this.identificador = "";
        this.marca = "";
        this.modelo = "";
        this.consumoPorHora = 0.0;
        this.isON = false;
        this.numeroAtivacoes = 0;
        this.horasDeUso = 0.0;
    }

    public Dispositivo(String identificador, String marca, String modelo, double consumoPorHora) {
        this.identificador = identificador;
        this.marca = marca;
        this.modelo = modelo;
        this.consumoPorHora = consumoPorHora;
        this.isON = false;
        this.numeroAtivacoes = 0;
        this.horasDeUso = 0.0;
    }

    public Dispositivo(Dispositivo dispositivo) {
        this.identificador = dispositivo.getIdentificador();
        this.marca = dispositivo.getMarca();
        this.modelo = dispositivo.getModelo();
        this.consumoPorHora = dispositivo.getConsumoPorHora();
        this.isON = dispositivo.isON();
        this.numeroAtivacoes = dispositivo.getNumeroAtivacoes();
        this.horasDeUso = dispositivo.getHorasDeUso();
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getConsumoPorHora() {
        return consumoPorHora;
    }

    public boolean isON() {
        return isON;
    }

    public int getNumeroAtivacoes() {
        return numeroAtivacoes;
    }

    public double getHorasDeUso() {
        return horasDeUso;
    }

    public double getConsumoTotal() {
        return this.horasDeUso * this.consumoPorHora;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setConsumoPorHora(double consumoPorHora) {
        this.consumoPorHora = consumoPorHora;
    }

    public void setNumeroAtivacoes(int numeroAtivacoes) {
        this.numeroAtivacoes = numeroAtivacoes;
    }

    public void setHorasDeUso(double horasDeUso) {
        this.horasDeUso = horasDeUso;
    }

    public void turnON() {
        if (!this.isON) {
            this.isON = true;
            this.numeroAtivacoes++;
        }
    }

    public void turnOFF() {
        this.isON = false;
    }

    public void adicionarHorasDeUso(double horas) {
        if (horas > 0) {
            this.horasDeUso += horas;
        }
    }

    public Dispositivo clone() {
        return new Dispositivo(this);
    }
}