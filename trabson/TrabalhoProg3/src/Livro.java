public class Livro extends TipoProducao implements Comparable<Livro> {

	String natureza;
	String titulo;
	String idioma;
	String editora;
	String isbn;

	public Livro(String cidade, int numPaginas, String cd_Ppg, String instituicao, String natureza, String titulo,
			String idioma, String isbn, String editora) {
		super(cidade, numPaginas, cd_Ppg, instituicao);
		this.natureza = natureza;
		this.titulo = titulo;
		this.idioma = idioma;
		this.editora = editora;
		this.isbn = isbn;
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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return natureza + ";" + titulo + ";" + idioma + ";" + editora + ";"  + cidade + ";" + isbn + ";" + numPaginas;
	}
	
	public String comparacao() {
		return natureza  + titulo  + idioma  + editora   + cidade  + isbn  + numPaginas;
	}

	@Override
	public int compareTo(Livro o) {
		return this.comparacao().compareToIgnoreCase(o.comparacao());
	}

}
