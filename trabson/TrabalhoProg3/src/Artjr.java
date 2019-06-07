import java.text.SimpleDateFormat;
import java.util.Date;

public class Artjr extends TipoProducao implements Comparable<Artjr> {
	String titulo;
	String idioma;
	Date data;
	String issn;
	SimpleDateFormat formato;
	SimpleDateFormat formato2;

	public Artjr(String cidade, int numPaginas, String cd_Ppg, String instituicao, String titulo, String idioma,
			Date data, String issn) {
		super(cidade, numPaginas, cd_Ppg, instituicao);
		this.titulo = titulo;
		this.idioma = idioma;
		this.data = data;
		this.issn = issn;
		formato = new SimpleDateFormat("dd/MM/yyyy");
		formato2 = new SimpleDateFormat("yyyy/MM/dd");
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
		String pag = "";
		if(numPaginas != 0) {
			pag = Integer.toString(numPaginas);
		}
		return titulo + ";" + idioma + ";"  + cidade + ";" + formato.format(data) + ";" + issn + ";" + pag;
	}
	
	public String toString2() {
		return titulo + ";" + idioma + ";"  + cidade + ";" + formato2.format(data) + ";" + issn + ";" + numPaginas;
	}
	
	public String comparacao() {
		return toString2().replaceAll(";", Character.toString((char)1));
	}

	@Override
	public int compareTo(Artjr o) {
		return this.comparacao().compareToIgnoreCase(o.comparacao());
	}

}
