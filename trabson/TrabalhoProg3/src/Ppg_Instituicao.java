/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 2018103815
 */

// funciona como tabela intermediaria entra a classe Ppg e Instituicao, para melhor busca.
public class Ppg_Instituicao implements Comparable<Ppg_Instituicao> {
    String codigoPpg;
    String siglaInst;
    String nomePpg;
    String nomeInst;   
	
	public Ppg_Instituicao(String codigoPpg, String siglaInst, String nomePpg, String nomeInst) {
		super();
		this.codigoPpg = codigoPpg;
		this.siglaInst = siglaInst;
		this.nomePpg = nomePpg;
		this.nomeInst = nomeInst;
	}
	public String getCodigoPpg() {
		return codigoPpg;
	}
	public void setCodigoPpg(String codigoPpg) {
		this.codigoPpg = codigoPpg;
	}
	public String getSiglaInst() {
		return siglaInst;
	}
	public void setSiglaInst(String siglaInst) {
		this.siglaInst = siglaInst;
	}
	
	public String getNomePpg() {
		return nomePpg;
	}
	public void setNomePpg(String nomePpg) {
		this.nomePpg = nomePpg;
	}
	public String getNomeInst() {
		return nomeInst;
	}
	public void setNomeInst(String nomeInst) {
		this.nomeInst = nomeInst;
	}
	@Override
	public int compareTo(Ppg_Instituicao o) {
		if(this.codigoPpg.equals(o.codigoPpg)) {
			return siglaInst.compareTo(o.siglaInst);
		} else {
			return codigoPpg.compareTo(o.codigoPpg);	
		}
		
		
	}
    
    
}
