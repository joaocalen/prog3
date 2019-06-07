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
    String instituicao;

    public TipoProducao(String cidade, int numPaginas, String cd_Ppg, String instituicao) {
        this.cidade = cidade;
        this.numPaginas = numPaginas;
        this.cd_Ppg = cd_Ppg;
        this.instituicao = instituicao;
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

	public String getCd_Ppg() {
		return cd_Ppg;
	}

	public void setCd_Ppg(String cd_Ppg) {
		this.cd_Ppg = cd_Ppg;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}
    
    
    
    

}
