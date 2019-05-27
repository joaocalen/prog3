/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoprog3;

/**
 *
 * @author 2018103815
 */
public class TipoProducao {

    String cidade;
    int numPaginas;

    public TipoProducao(String cidade, int numPaginas) {
        this.cidade = cidade;
        this.numPaginas = numPaginas;
    }    

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    

}
