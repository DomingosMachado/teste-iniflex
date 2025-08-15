import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.text.NumberFormat;

public class Principal {
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("SISTEMA DE GERENCIAMENTO DE FUNCIONÁRIOS - TESTE INIFLEX");
        System.out.println("=".repeat(60));

        // 3.1 - Inserir todos os funcionários
        System.out.println("\n3.1 - Inserindo funcionários...");
        inserirFuncionarios();
        System.out.println("Funcionários inseridos com sucesso!");

        // 3.2 - Remover funcionário "João"
        System.out.println("\n3.2 - Removendo funcionário João...");
        removerJoao();

        // 3.3 - Imprimir todos os funcionários
        System.out.println("\n3.3 - Lista de funcionários:");
        imprimirFuncionarios();

        // 3.4 - Aplicar aumento de 10%
        System.out.println("\n3.4 - Aplicando aumento de 10%...");
        aplicarAumento();
        System.out.println("Funcionários após aumento:");
        imprimirFuncionarios();

        // 3.5 e 3.6 - Agrupar e imprimir por função
        System.out.println("\n3.5/3.6 - Funcionários agrupados por função:");
        imprimirAgrupadosPorFuncao();

        // 3.8 - Aniversariantes outubro e dezembro
        System.out.println("\n3.8 - Funcionários que fazem aniversário em outubro e dezembro:");
        imprimirAniversariantesOutubroDezembro();

        // 3.9 - Funcionário mais velho
        System.out.println("\n3.9 - Funcionário com maior idade:");
        imprimirFuncionarioMaisVelho();

        // 3.10 - Ordem alfabética
        System.out.println("\n3.10 - Lista de funcionários por ordem alfabética:");
        imprimirOrdemAlfabetica();

        // 3.11 - Total dos salários
        System.out.println("\n3.11 - Total dos salários dos funcionários:");
        imprimirTotalSalarios();

        // 3.12 - Salários em salários mínimos
        System.out.println("\n3.12 - Quantos salários mínimos ganha cada funcionário:");
        imprimirSalariosMinimos();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXECUÇÃO CONCLUÍDA COM SUCESSO!");
        System.out.println("=".repeat(60));
    }

    // 3.1 - Inserir funcionários
    private static void inserirFuncionarios() {
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }

    // 3.2 - Remover funcionário João
    private static void removerJoao() {
        funcionarios.removeIf(f -> "João".equals(f.getNome()));
        System.out.println("Funcionário João removido com sucesso!");
    }

    // 3.3 - Imprimir todos os funcionários
    private static void imprimirFuncionarios() {
        funcionarios.forEach(System.out::println);
    }

    // 3.4 - Aplicar aumento de 10%
    private static void aplicarAumento() {
        funcionarios.forEach(f -> f.aplicarAumento(10.0));
        System.out.println("Aumento de 10% aplicado a todos os funcionários!");
    }

    // 3.5/3.6 - Agrupar funcionários por função
    private static void imprimirAgrupadosPorFuncao() {
        Map<String, List<Funcionario>> grupos = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        grupos.forEach((funcao, lista) -> {
            System.out.println("\n" + funcao + ":");
            lista.forEach(f -> System.out.println("  " + f));
        });
    }

    // 3.8 - Aniversariantes outubro e dezembro
    private static void imprimirAniversariantesOutubroDezembro() {
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 ||
                        f.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);
    }

    // 3.9 - Funcionário mais velho
    private static void imprimirFuncionarioMaisVelho() {
        funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .ifPresent(f -> System.out.println(f.getNome() + ", " + f.getIdade() + " anos"));
    }

    // 3.10 - Ordem alfabética
    private static void imprimirOrdemAlfabetica() {
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);
    }

    // 3.11 - Total dos salários
    private static void imprimirTotalSalarios() {
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        System.out.println("Total: " + nf.format(total));
    }

    // 3.12 - Salários em salários mínimos
    private static void imprimirSalariosMinimos() {
        funcionarios.forEach(f -> {
            BigDecimal salariosMinimos = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha " + salariosMinimos + " salários mínimos");
        });
    }
}