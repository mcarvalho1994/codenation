package challenge;


import java.util.Objects;

public class Motorista {

    private final String nome;

    private final int idade;

    private final int pontos;

    private final String habilitacao;

    private Motorista(String nome, int idade, int pontos, String habilitacao) {
        this.nome = nome;
        this.idade = idade;
        this.pontos = pontos;
        this.habilitacao = habilitacao;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPontos() {
        return pontos;
    }

    public String getHabilitacao() {
        return habilitacao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Motorista motorista = (Motorista) o;
        return Objects.equals(habilitacao, motorista.habilitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(habilitacao);
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", pontos=" + pontos +
                ", habilitacao='" + habilitacao + '\'' +
                '}';
    }



    public static MotoristaBuilder builder() {
        return new MotoristaBuilder();
    }


    public static class MotoristaBuilder {

        private String nome;

        private int idade;

        private int pontos;

        private String habilitacao;

        private MotoristaBuilder() {
        }

        public MotoristaBuilder withNome(String nome) {
            this.nome = nome;
            verificarNome();
            return this;
        }

        public MotoristaBuilder withIdade(int idade) {
            this.idade = idade;
            verificarIdade();
            return this;
        }

        public MotoristaBuilder withPontos(int pontos) {
            this.pontos = pontos;
            verificarPontos();
            return this;
        }

        public MotoristaBuilder withHabilitacao(String habilitacao) {
            this.habilitacao = habilitacao;
            verificarHabilitacao();
            return this;
        }


        public Motorista build() {
            verificarCampos();
            return new Motorista(nome, idade, pontos, habilitacao);
        }

        public void verificarPontos() {
            if(this.pontos < 0) {
                throw new IllegalArgumentException("Valores não podem ser menores do que zero");
            }
        }

        public void verificarIdade() {
            if(this.idade < 0) {
                throw new IllegalArgumentException("Valores não podem ser menores do que zero");
            }
        }

        public void verificarNome() {
            if(this.nome == null) {
                throw new NullPointerException("Nome do motorista não pode ser nulo");
            }
        }

        public void verificarHabilitacao() {
            if(this.habilitacao == null) {
                throw new NullPointerException("Habilitação não pode ser nula");
            }
        }

        public void verificarCampos() {
            if(this.idade < 0 ||  this.pontos < 0) {
                throw new IllegalArgumentException("Valores não podem ser menores do que zero");
            }
            else if(this.nome == null)
            {
                throw new NullPointerException("Nome do motorista não pode ser nulo");
            }
            else if(this.habilitacao == null)
            {
                throw new NullPointerException("Habilitação não pode ser nula");
            }
        }
    }
}
