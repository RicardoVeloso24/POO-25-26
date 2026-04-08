package UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextUI {

    public interface Handler {
        void execute();
    }

    public interface Precondition {
        boolean validate();
    }

    private static Scanner scanner = new Scanner(System.in);
    private String titulo;
    private List<String> opcoes;
    private List<Precondition> disponivel;
    private List<Handler> handlers;

    public TextUI(String titulo, List<String> opcoes) {
        this.titulo = titulo;
        this.opcoes = new ArrayList<>(opcoes);
        this.disponivel = new ArrayList<>();
        this.handlers = new ArrayList<>();

        this.opcoes.forEach(s -> {
            this.disponivel.add(() -> true);
            this.handlers.add(() -> System.out.println(Text.ANSI_RED +
                    "\n[!] Opção não implementada!" + Text.ANSI_RESET));
        });
    }

    public TextUI(String titulo, String[] opcoes) {
        this(titulo, Arrays.asList(opcoes));
    }

    public TextUI(String[] opcoes) {
        this("Domus Control", Arrays.asList(opcoes));
    }

    public void setHandler(int i, Handler h) {
        this.handlers.set(i-1, h);
    }

    public void setPreCondition(int i, Precondition p) {
        this.disponivel.set(i-1, p);
    }

    public void addOption(String nome, Precondition p, Handler h) {
        this.opcoes.add(nome);
        this.disponivel.add(p);
        this.handlers.add(h);
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void runOnce() {
        int op;
        mostrar();
        op = lerOpcao();

        // Testar precondição
        if (op > 0 && !this.disponivel.get(op-1).validate()) {
            System.out.println(Text.ANSI_RED +
                    "[!] Opção indisponível! Sem permissões." +
                    Text.ANSI_RESET);
        } else if (op > 0) {
            // Executar handler
            System.out.println(Text.ANSI_CYAN + "─".repeat(60) + Text.ANSI_RESET);
            this.handlers.get(op-1).execute();
            System.out.println(Text.ANSI_CYAN + "─".repeat(60) + Text.ANSI_RESET);
        }
    }

    /*
    Loop do menu (termina com opção 0)
    */
    public void run() {
        int op;
        do {
            mostrar();
            op = lerOpcao();

            // Testar precondição
            if (op > 0 && !this.disponivel.get(op-1).validate()) {
                System.out.println(Text.ANSI_RED +
                        "\n[!] Opção indisponível! Sem permissões." +
                        Text.ANSI_RESET);
            } else if (op > 0) {
                // Executar handler
                System.out.println(Text.ANSI_CYAN + "─".repeat(60) + Text.ANSI_RESET);
                this.handlers.get(op-1).execute();
                System.out.println(Text.ANSI_CYAN + "─".repeat(60) + Text.ANSI_RESET);
            }
        } while (op != 0);
    }

    /*
    Mostra o menu formatado
    */
    private void mostrar() {
        limparEcra();
        mostrarTitulo(this.titulo);
        System.out.println(Text.ANSI_CYAN + "─".repeat(60) + Text.ANSI_RESET);

        for (int i = 0; i < this.opcoes.size(); i++) {
            // Verifica se está disponível
            if (this.disponivel.get(i).validate()) {
                System.out.println(Text.ANSI_PURPLE +
                        "[" + (i+1) + "] " +
                        Text.ANSI_RESET + Text.ANSI_BOLD +
                        this.opcoes.get(i) +
                        Text.ANSI_RESET);
            } else {
                // Opção indisponível (cinza)
                System.out.println(Text.ANSI_BLACK +
                        "[" + (i+1) + "] ---" +
                        Text.ANSI_RESET);
            }
        }

        System.out.println(Text.ANSI_CYAN + "─".repeat(60) + Text.ANSI_RESET);
        System.out.println(Text.ANSI_RED + "[0] Sair" + Text.ANSI_RESET);
        System.out.println(Text.ANSI_CYAN + "─".repeat(60) + Text.ANSI_RESET);
    }

    /*
    Mostra título com bordas
    */
    private void mostrarTitulo(String titulo) {
        String border = Text.ANSI_BLUE + Text.ANSI_BOLD +
                "╔" + "═".repeat(titulo.length() + 2) + "╗" +
                Text.ANSI_RESET;
        String content = Text.ANSI_BLUE + Text.ANSI_BOLD +
                "║ " + Text.ANSI_ITALIC + titulo +
                Text.ANSI_RESET + Text.ANSI_BLUE + Text.ANSI_BOLD +
                " ║" + Text.ANSI_RESET;

        System.out.println("\n" + border);
        System.out.println(content);
        System.out.println(border.replace('╔', '╚').replace('╗', '╝') + "\n");
    }

    private int lerOpcao() {
        int op;
        System.out.print(Text.ANSI_YELLOW +
                "\n» Escolha uma opção: " +
                Text.ANSI_RESET);

        try {
            String line = scanner.nextLine();
            op = Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            op = -1;
        }

        if (op < 0 || op > this.opcoes.size()) {
            System.out.println(Text.ANSI_RED +
                    "\n[!] Opção inválida!" +
                    Text.ANSI_RESET);
            op = -1;
        }

        return op;
    }

    private void limparEcra() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
class Text {
    // Cores
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Estilos
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_ITALIC = "\u001B[3m";
    public static final String ANSI_UNDERLINE = "\u001B[4m";

    // Backgrounds
    public static final String ANSI_BG_RED = "\u001B[41m";
    public static final String ANSI_BG_GREEN = "\u001B[42m";
    public static final String ANSI_BG_YELLOW = "\u001B[43m";
}