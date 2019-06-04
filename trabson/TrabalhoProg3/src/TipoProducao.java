/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 2018103815
 */
public class TipoProducao {

    String cidade;
    int numPaginas;
    String cd_Ppg;

    public TipoProducao(String cidade, int numPaginas, String cd_Ppg) {
        this.cidade = cidade;
        this.numPaginas = numPaginas;
        this.cd_Ppg = cd_Ppg;
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
