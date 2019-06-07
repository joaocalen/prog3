public class Partitura extends TipoProducao implements Comparable<Partitura> {
	String natureza;
	String editora;
	String formacao;

	public Partitura(String cidade, int numPaginas, String cd_Ppg, String instituicao, String natureza, String editora,
			String formacao) {
		super(cidade, numPaginas, cd_Ppg, instituicao);
		this.natureza = natureza;
		this.editora = editora;
		this.formacao = formacao;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	@Override
	public String toString() {
		return natureza + ";" + editora + ";"  + cidade + ";" + formacao + ";" + numPaginas;
	}
	
	public String comparacao() {
		return natureza  + editora   + cidade  + formacao  + numPaginas;
	}

	@Override
	public int compareTo(Partitura o) {
		return this.comparacao().compareToIgnoreCase(o.comparacao());
	}
	
	

}
