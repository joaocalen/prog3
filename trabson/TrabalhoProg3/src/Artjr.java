import java.util.Date;

public class Artjr extends TipoProducao implements Comparable<Artjr> {
	String titulo;
	String idioma;
	Date data;
	String issn;

	public Artjr(String cidade, int numPaginas, String cd_Ppg, String instituicao, String titulo, String idioma,
			Date data, String issn) {
		super(cidade, numPaginas, cd_Ppg, instituicao);
		this.titulo = titulo;
		this.idioma = idioma;
		this.data = data;
		this.issn = issn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	@Override
	public String toString() {
		return titulo + ";" + idioma + ";"  + cidade + ";" + data + ";" + issn + ";" + numPaginas;
	}
	
	public String comparacao() {
		return titulo  + idioma   + cidade  + data  + issn  + numPaginas;
	}

	@Override
	public int compareTo(Artjr o) {
		return this.comparacao().compareToIgnoreCase(o.comparacao());
	}

}
