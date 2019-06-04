import java.util.Date;

public class Artjr extends TipoProducao {
	String titulo;
	String idioma;
	Date data;
	String issn;

	public Artjr(String cidade, int numPaginas, String cd_Ppg, String titulo, String idioma, Date data, String issn) {
		super(cidade, numPaginas, cd_Ppg);
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

}
