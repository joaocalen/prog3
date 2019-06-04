public class Livro extends TipoProducao {

	String natureza;
	String titulo;
	String idioma;
	String isbn;

	public Livro(String cidade, int numPaginas, String cd_Ppg, String natureza, String titulo, String idioma,
			String isbn) {
		super(cidade, numPaginas, cd_Ppg);
		this.natureza = natureza;
		this.titulo = titulo;
		this.idioma = idioma;
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

}
