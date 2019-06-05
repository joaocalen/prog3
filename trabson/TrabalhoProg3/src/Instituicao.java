/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2018103815
 */
public class Instituicao implements Comparable<Instituicao> {
	String sigla;
	String nome;

	public Instituicao(String sigla, String nome) {
		super();
		this.sigla = sigla;
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int compareTo(Instituicao o) {
		if (this.getSigla().equals(o.getSigla())) {
			return nome.compareTo(o.nome);
		} else {
			return sigla.compareTo(o.sigla);
		}
	}
}

//class ComparadorIdade implements Comparator<Instituicao> {
//	public int compare(Instituicao o1, Instituicao o2) {
//		return o1.nome.compareTo(o2.nome);
//	}
//}
