package br.com.codenation;

public class ModaQtdeUtil {
    private int moda = 0;
    private int qtde = 0;

    public ModaQtdeUtil(int moda, int qtde) {
        this.moda = moda;
        this.qtde = qtde;
    }

    public int getModa() {
        return moda;
    }

    public void setModa(int moda) {
        this.moda = moda;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
}
