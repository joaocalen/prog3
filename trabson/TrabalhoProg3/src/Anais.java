/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2018103815
 */
public class Anais extends TipoProducao implements Comparable<Anais> {

	private String natureza;
	private String titulo;
	private String idioma;
	private String evento;

	public Anais(String natureza, String titulo, String idioma, String evento, String cidade, int numPaginas,
			String cd_Ppg, String instituicao) {
		super(cidade, numPaginas, cd_Ppg, instituicao);
		this.natureza = natureza;
		this.titulo = titulo;
		this.idioma = idioma;
		this.evento = evento;
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

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	@Override
	public String toString() {
		return natureza + ";" + titulo + ";" + idioma + ";" + evento + ";" + cidade + ";" + numPaginas;
	}
	
	public String comparacao() {
		return natureza  + titulo  + idioma  + evento  + cidade  + numPaginas;
	}

	@Override
	public int compareTo(Anais o) {
		return this.comparacao().compareToIgnoreCase(o.comparacao());
	}

}
