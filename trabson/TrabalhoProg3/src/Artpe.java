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
		String pag = "", vol = "", fasc = "", ser = "";
		if (numPaginas != 0) {
			pag = Integer.toString(numPaginas);
		}
		if (volume != 0) {
			vol = Integer.toString(volume);
		}
		if (fasciculo != 0) {
			fasc = Integer.toString(fasciculo);
		}
		if (serie != 0) {
			ser = Integer.toString(serie);
		}
		return natureza + ";" + idioma + ";" + editora + ";" + cidade + ";" + vol + ";" + fasc + ";" + ser + ";" + issn
				+ ";" + pag;
	}

	public String comparacao() {
		return toString().replaceAll(";", Character.toString((char) 1));
	}

	@Override
	public int compareTo(Artpe o) {
		if (this.natureza.equals(o.natureza)) {
			if (this.idioma.equals(o.idioma)) {
				if (this.editora.equals(o.editora)) {
					if (this.cidade.equals(o.cidade)) {
						if (this.volume == o.volume) {
							if (this.fasciculo == o.fasciculo) {
								if (this.serie == o.serie) {
									if (this.issn.equals(o.issn)) {
										return (this.numPaginas > o.numPaginas) ? 1 : -1;
									} else {
										return this.issn.compareTo(o.issn);
									}
								} else {
									return (this.serie > o.serie) ? 1 : -1;
								}
							} else {
								return (this.fasciculo > o.fasciculo) ? 1 : -1;
							}
						} else {
							return (this.volume > o.volume) ? 1 : -1;
						}
					} else {
						return this.cidade.compareTo(o.cidade);
					}
				} else {
					return this.editora.compareTo(o.editora);
				}
			} else {
				return this.idioma.compareTo(o.idioma);
			}
		} else {
			return this.natureza.compareTo(o.natureza);
		}
	}
}
