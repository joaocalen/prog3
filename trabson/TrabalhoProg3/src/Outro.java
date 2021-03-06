
public class Outro extends TipoProducao implements Comparable<Outro> {
	String natureza;
	String idioma;
	String editora;
	
	public Outro(String cidade, int numPaginas, String cd_Ppg,String instituicao, String natureza, String idioma, String editora) {
		super(cidade, numPaginas, cd_Ppg, instituicao);
		this.natureza = natureza;
		this.idioma = idioma;
		this.editora = editora;
	}
	public String getNatureza() {
		return natureza;
	}
	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	
	
	@Override
	public String toString() {
		String pag = "";
		if(numPaginas != 0) {
			pag = Integer.toString(numPaginas);
		}
		return natureza + ";" + idioma + ";"  + editora + ";" + cidade + ";" + pag;
	}
	
	public String comparacao() {
		return toString().replaceAll(";", Character.toString((char)1));
	}
	
	@Override
	public int compareTo(Outro o) {
		return this.comparacao().compareToIgnoreCase(o.comparacao());
	}
	
	
	
	
}
