
public class Traducao extends TipoProducao {

	String natureza;
	String titulo;
	String idioma;
	String editora;
	String idiomaTraducao;
	public Traducao(String cidade, int numPaginas, String cd_Ppg, String natureza, String titulo, String idioma,
			String editora, String idiomaTraducao) {
		super(cidade, numPaginas, cd_Ppg);
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
	
	


}
