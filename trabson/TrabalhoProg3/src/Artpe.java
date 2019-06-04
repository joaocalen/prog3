
public class Artpe extends TipoProducao {
	String natureza;
	String idioma;
	String editora;
	String volume;
	String fasciculo;
	String serie;
	String issn;

	public Artpe(String cidade, int numPaginas, String cd_Ppg, String natureza, String idioma, String editora,
			String volume, String fasciculo, String serie, String issn) {
		super(cidade, numPaginas, cd_Ppg);
		this.natureza = natureza;
		this.idioma = idioma;
		this.editora = editora;
		this.volume = volume;
		this.fasciculo = fasciculo;
		this.serie = serie;
		this.issn = issn;
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

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getFasciculo() {
		return fasciculo;
	}

	public void setFasciculo(String fasciculo) {
		this.fasciculo = fasciculo;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

}
