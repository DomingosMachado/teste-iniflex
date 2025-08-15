import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    // Construtor
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento); // Chama o construtor da classe Pessoa
        this.salario = salario;
        this.funcao = funcao;
    }

    // Getters e Setters
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    // Método para aplicar aumento no salário
    public void aplicarAumento(double percentual) {
        BigDecimal aumento = salario.multiply(BigDecimal.valueOf(percentual / 100));
        this.salario = this.salario.add(aumento);
    }

    // toString para imprimir as informações formatadas
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        return String.format("Nome: %s, Data Nascimento: %s, Salário: %s, Função: %s",
                getNome(),
                getDataNascimento().format(formatter),
                nf.format(salario),
                funcao);
    }
}