
public class Traducao extends TipoProducao implements Comparable<Traducao> {

	String natureza;
	String titulo;
	String idioma;
	String editora;
	String idiomaTraducao;

	public Traducao(String cidade, int numPaginas, String cd_Ppg, String instituicao, String natureza, String titulo,
			String idioma, String editora, String idiomaTraducao) {
		super(cidade, numPaginas, cd_Ppg, instituicao);
		this.natureza = natureza;
		this.titulo = titulo;
		this.idioma = idioma;
		this.editora = editora;
		this.idiomaTraducao = idiomaTraducao;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
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

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getIdiomaTraducao() {
		return idiomaTraducao;
	}

	public void setIdiomaTraducao(String idiomaTraducao) {
		this.idiomaTraducao = idiomaTraducao;
	}

	@Override
	public String toString() {
		String pag = "";
		if(numPaginas != 0) {
			pag = Integer.toString(numPaginas);
		}		
		return natureza + ";" + titulo + ";" + idioma + ";" + editora + ";"  + cidade + ";" + idiomaTraducao + ";" + pag;
	}
	
	public String comparacao() {
		return toString().replaceAll(";", Character.toString((char)1));
	}

	@Override
	public int compareTo(Traducao o) {
		return this.comparacao().compareToIgnoreCase(o.comparacao());
	}

	
	
}
