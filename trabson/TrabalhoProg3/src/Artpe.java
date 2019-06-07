import java.util.Comparator;

public class Artpe extends TipoProducao implements Comparable<Artpe> {
	String natureza;
	String idioma;
	String editora;
	int volume;
	int fasciculo;
	int serie;
	String issn;

	public Artpe(String cidade, int numPaginas, String cd_Ppg, String instituicao, String natureza, String idioma,
			String editora, int volume, int fasciculo, int serie, String issn) {
		super(cidade, numPaginas, cd_Ppg, instituicao);
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

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getFasciculo() {
		return fasciculo;
	}

	public void setFasciculo(int fasciculo) {
		this.fasciculo = fasciculo;
	}

	public int getSerie() {
		return serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	@Override
	public String toString() {
		return natureza + ";" + idioma + ";" + editora + ";" + cidade + ";" + volume + ";" + fasciculo + ";" + serie
				+ ";" + issn + ";" + numPaginas;
	}

	public String comparacao() {
		return natureza + idioma + editora + cidade + volume + fasciculo + serie + issn + numPaginas;
	}

	@Override
	public int compareTo(Artpe o) {
		return o.comparacao().compareToIgnoreCase(this.comparacao());
	}
}
