/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 2018103815
 */

// funciona como tabela intermediária entra a classe Ppg e Instituição, para melhor busca.
public class Ppg_Instituicao implements Comparable<Ppg_Instituicao> {
    String codigoPpg;
    String siglaInst;
    
	public Ppg_Instituicao(String codigoPpg, String siglaInst) {		
		this.codigoPpg = codigoPpg;
		this.siglaInst = siglaInst;
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
	@Override
	public int compareTo(Ppg_Instituicao o) {
		if(this.codigoPpg.equals(o.codigoPpg)) {
			return siglaInst.compareTo(o.siglaInst);
		} else {
			return codigoPpg.compareTo(o.codigoPpg);	
		}
		
		
	}
    
    
}
