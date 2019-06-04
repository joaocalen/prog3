
public class Outro extends TipoProducao {
	String natureza;
	String idioma;
	String editora;
	
	public Outro(String cidade, int numPaginas, String cd_Ppg, String natureza, String idioma, String editora) {
		super(cidade, numPaginas, cd_Ppg);
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
	
	
	
	
}
