
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class LeitorDeArquivo {
	private TreeSet<Ppg> ppgs;
	private ArrayList<TipoProducao> producoes;
	private TreeSet<Instituicao> instituicoes;
	private TreeSet<Ppg_Instituicao> ppgs_instituicoes;
	private BufferedReader br;
	private int numPaginas;
	private int numDocs;

	public LeitorDeArquivo() {
		ppgs = new TreeSet<Ppg>();
		producoes = new ArrayList<>();
		instituicoes = new TreeSet<Instituicao>();
		ppgs_instituicoes = new TreeSet<Ppg_Instituicao>();
		numPaginas = 0;
		numDocs = 0;
	}

	public void lerCSV(String nome) {
		try {
			String linha;
			String[] campos = new String[50];

			br = new BufferedReader(new FileReader(nome));
			linha = br.readLine();
			campos = linha.split(";");

			int natureza = verificarColuna(campos, "DS_NATUREZA");
			int titulo = verificarColuna(campos, "NM_TITULO");
			int pagFinal = verificarColuna(campos, "NR_PAGINA_FINAL");
			int pagInicial = verificarColuna(campos, "NR_PAGINA_INICIAL");
			int evento = verificarColuna(campos, "DS_EVENTO");
			int cidade = verificarColuna(campos, "NM_CIDADE");
			int cidadePais = verificarColuna(campos, "NM_CIDADE_PAIS");
			int idioma = verificarColuna(campos, "DS_IDIOMA");
			int data = verificarColuna(campos, "DT_PUBLICACAO");
			int issn = verificarColuna(campos, "DS_ISSN");
			int paginas = verificarColuna(campos, "NR_PAGINAS");
			int volume = verificarColuna(campos, "NR_VOLUME");
			int fasciculo = verificarColuna(campos, "DS_FASCICULO");
			int serie = verificarColuna(campos, "NR_SERIE");
			int editora = verificarColuna(campos, "NM_EDITORA");
			int isbn = verificarColuna(campos, "DS_ISBN");
			int formacao = verificarColuna(campos, "DS_FORMACAO_INSTRUMENTAL");
			int traducao = verificarColuna(campos, "DS_IDIOMA_TRADUCAO");
			int editoraTraducao = verificarColuna(campos, "NM_EDITORA_TRADUCAO");
			int paginasContribuicao = verificarColuna(campos, "NR_PAGINAS_CONTRIBUICAO");
			if (traducao != -1) {
				while (br.ready()) {
					linha = br.readLine();
					String linhaAux = retiraPontoVirgula(linha);
					campos = linhaAux.split(";");
					if (linhaAux.equals(linha)) {
						adicionarTraducoes(campos, natureza, titulo, paginas, idioma, editoraTraducao, cidade,
								traducao);
					} else {
						campos = devolvePontoVirgula(campos);
						adicionarTraducoes(campos, natureza, titulo, paginas, idioma, editoraTraducao, cidade,
								traducao);
					}

				}
			} else if (formacao != -1) {
				while (br.ready()) {
					linha = br.readLine();
					String linhaAux = retiraPontoVirgula(linha);
					campos = linhaAux.split(";");
					if (linhaAux.equals(linha)) {
						adicionarPartitura(campos, natureza, editora, cidade, formacao, paginas);
					} else {
						campos = devolvePontoVirgula(campos);
						adicionarPartitura(campos, natureza, editora, cidade, formacao, paginas);
					}

				}
			} else if (isbn != -1) {
				while (br.ready()) {
					linha = br.readLine();
					String linhaAux = retiraPontoVirgula(linha);
					campos = linhaAux.split(";");
					if (linhaAux.equals(linha)) {
						adicionarLivro(campos, natureza, titulo, idioma, editora, cidadePais, isbn, paginasContribuicao);
					} else {
						campos = devolvePontoVirgula(campos);
						adicionarLivro(campos, natureza, titulo, idioma, editora, cidadePais, isbn, paginasContribuicao);
					}

				}
			} else if (data != -1) {
				while (br.ready()) {
					linha = br.readLine();
					String linhaAux = retiraPontoVirgula(linha);
					campos = linhaAux.split(";");
					if (linhaAux.equals(linha)) {
						adicionarArtjr(campos, cidade, titulo, idioma, data, issn, pagFinal, pagInicial);
					} else {
						campos = devolvePontoVirgula(campos);
						adicionarArtjr(campos, cidade, titulo, idioma, data, issn, pagFinal, pagInicial);
					}

				}
			} else if (issn != -1) {
				while (br.ready()) {
					linha = br.readLine();
					String linhaAux = retiraPontoVirgula(linha);
					campos = linhaAux.split(";");
					if (linhaAux.equals(linha)) {
						adicionarArtpe(campos, natureza, idioma, editora, cidade, volume, fasciculo, serie, issn,
								pagFinal, pagInicial);
					} else {
						campos = devolvePontoVirgula(campos);
						adicionarArtpe(campos, natureza, idioma, editora, cidade, volume, fasciculo, serie, issn,
								pagFinal, pagInicial);
					}
				}
			} else if (titulo != -1) {
				while (br.ready()) {
					linha = br.readLine();
					String linhaAux = retiraPontoVirgula(linha);
					campos = linhaAux.split(";");
					if (linhaAux.equals(linha)) {
						adicionarAnais(campos, natureza, titulo, pagFinal, pagInicial, evento, cidade, idioma);
					} else {
						campos = devolvePontoVirgula(campos);
						adicionarAnais(campos, natureza, titulo, pagFinal, pagInicial, evento, cidade, idioma);
					}
				}
			} else {
				while (br.ready()) {
					linha = br.readLine();
					String linhaAux = retiraPontoVirgula(linha);
					campos = linhaAux.split(";");
					if (linhaAux.equals(linha)) {
						adicionarOutros(campos, natureza, idioma, editora, cidade, paginas);
					} else {
						campos = devolvePontoVirgula(campos);
						adicionarOutros(campos, natureza, idioma, editora, cidade, paginas);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Erro de I/O");
			System.exit(0);
		} catch (IOException io) {
			System.out.println("Erro de I/O");
			System.exit(0);
		}
	}

	public int verificarColuna(String[] campos, String nomeColuna) {
		for (int i = 0; i < campos.length; i++) {
			if (campos[i].trim().equals(nomeColuna)) {
				return i;
			}
		}
		return -1;
	}

	public void adicionarArtjr(String[] campos, int cidade, int titulo, int idioma, int data, int issn, int pagFinal,
			int pagInicial) {
		String cd_ppg;
		String nome_ppg;
		String sigla_inst;
		String nome_inst;

		cd_ppg = campos[0].trim();
		nome_ppg = campos[1].trim();
		sigla_inst = campos[2].trim();
		nome_inst = campos[3].trim();

		int pags = adicionarNumeroPaginas(campos[pagFinal].trim(), campos[pagInicial].trim());
		
		Date dataConvertida = converterData(campos[data].trim());

		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst, nome_ppg, nome_inst);

		Artjr aj = new Artjr(campos[cidade].trim(), 0, cd_ppg, sigla_inst, campos[titulo].trim(), campos[idioma].trim(),
				dataConvertida, campos[issn].trim());
		aj.setNumPaginas(pags);
		this.producoes.add(aj);
	}

	public void adicionarArtpe(String[] campos, int natureza, int idioma, int editora, int cidade, int volume,
			int fasciculo, int serie, int issn, int pagFinal, int pagInicial) {
		String cd_ppg;
		String nome_ppg;
		String sigla_inst;
		String nome_inst;

		cd_ppg = campos[0].trim();
		nome_ppg = campos[1].trim();
		sigla_inst = campos[2].trim();
		nome_inst = campos[3].trim();

		int fasc = conversaoInt(campos[fasciculo].trim());
		int ser = conversaoInt(campos[serie].trim());
		int vol = conversaoInt(campos[volume].trim());

		int pags = adicionarNumeroPaginas(campos[pagFinal].trim(), campos[pagInicial].trim());

		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst, nome_ppg, nome_inst);

		Artpe ap = new Artpe(campos[cidade].trim(), 0, cd_ppg, sigla_inst, campos[natureza].trim(),
				campos[idioma].trim(), campos[editora].trim(), vol, fasc, ser, campos[issn].trim());
		ap.setNumPaginas(pags);
		this.producoes.add(ap);
	}

	public void adicionarLivro(String[] campos, int natureza, int titulo, int idioma, int editora, int cidade, int isbn,
			int pagFinal) {
		String cd_ppg;
		String nome_ppg;
		String sigla_inst;
		String nome_inst;

		cd_ppg = campos[0].trim();
		nome_ppg = campos[1].trim();
		sigla_inst = campos[2].trim();
		nome_inst = campos[3].trim();

		int pags = adicionarNumeroPaginas(campos[pagFinal].trim());
		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst, nome_ppg, nome_inst);

		Livro l = new Livro(campos[cidade].trim(), 0, cd_ppg, sigla_inst, campos[natureza].trim(),
				campos[titulo].trim(), campos[idioma].trim(), campos[isbn].trim(), campos[editora].trim());
		l.setNumPaginas(pags);
		this.producoes.add(l);
	}

	public void adicionarPartitura(String[] campos, int natureza, int editora, int cidade, int formacao, int pagFinal) {

		String cd_ppg;
		String nome_ppg;
		String sigla_inst;
		String nome_inst;

		cd_ppg = campos[0].trim();
		nome_ppg = campos[1].trim();
		sigla_inst = campos[2].trim();
		nome_inst = campos[3].trim();

		int pags = adicionarNumeroPaginas(campos[pagFinal].trim());
		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst, nome_ppg, nome_inst);

		Partitura p = new Partitura(campos[cidade].trim(), 0, cd_ppg, sigla_inst, campos[natureza].trim(),
				campos[editora].trim(), campos[formacao].trim());
		p.setNumPaginas(pags);
		this.producoes.add(p);

	}

	public void adicionarOutros(String[] campos, int natureza, int idioma, int editora, int cidade, int paginas) {

		String cd_ppg;
		String nome_ppg;
		String sigla_inst;
		String nome_inst;

		cd_ppg = campos[0].trim();
		nome_ppg = campos[1].trim();
		sigla_inst = campos[2].trim();
		nome_inst = campos[3].trim();

		int pags = adicionarNumeroPaginas(campos[paginas].trim());

		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst, nome_ppg, nome_inst);
		Outro o = new Outro(campos[cidade].trim(), 0, cd_ppg, sigla_inst, campos[natureza].trim(),
				campos[idioma].trim(), campos[editora].trim());
		o.setNumPaginas(pags);
		this.producoes.add(o);
	}

	public void adicionarTraducoes(String[] campos, int natureza, int titulo, int pagFinal, int idioma, int editora,
			int cidade, int traducao) {
		String cd_ppg;
		String nome_ppg;
		String sigla_inst;
		String nome_inst;

		cd_ppg = campos[0].trim();
		nome_ppg = campos[1].trim();
		sigla_inst = campos[2].trim();
		nome_inst = campos[3].trim();

		int pags = adicionarNumeroPaginas(campos[pagFinal].trim());
		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst, nome_ppg, nome_inst);
		Traducao t = new Traducao(campos[cidade].trim(), 0, cd_ppg, sigla_inst, campos[natureza].trim(),
				campos[titulo].trim(), campos[idioma].trim(), campos[editora].trim(), campos[traducao].trim());
		t.setNumPaginas(pags);
		this.producoes.add(t);

	}

	public void adicionarAnais(String[] campos, int natureza, int titulo, int pagFinal, int pagInicial, int evento,
			int cidade, int idioma) {
		String cd_ppg;
		String nome_ppg;
		String sigla_inst;
		String nome_inst;

		cd_ppg = campos[0].trim();
		nome_ppg = campos[1].trim();
		sigla_inst = campos[2].trim();
		nome_inst = campos[3].trim();

		int pags = adicionarNumeroPaginas(campos[pagFinal].trim(), campos[pagInicial].trim());

		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst, nome_ppg, nome_inst);
		Anais a = new Anais(campos[natureza].trim(), campos[titulo].trim(), campos[idioma].trim(),
				campos[evento].trim(), campos[cidade].trim(), 0, cd_ppg, sigla_inst);
		a.setNumPaginas(pags);
		this.producoes.add(a);
	}

	public int conversaoInt(String inteiro) {
		try {
			return Integer.parseInt(inteiro);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public Date converterData(String data) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataConvertida;
		try {
			dataConvertida = formato.parse(data);
			return dataConvertida;
		} catch (ParseException e) {
			return null;
		}
	}

	public int adicionarNumeroPaginas(String pagFinal, String pagInicial) {
		try {
			int pagF = Integer.parseInt(pagFinal);
			int pagI = Integer.parseInt(pagInicial);
			int pags = pagF - pagI + 1;
			if (pags > 0 && pags < 2000 && pagF >= 0 && pagI >= 0) {
				numPaginas += pags;
				numDocs++;
				return pags;
			}
		} catch (NumberFormatException e) {
			return 0;
		}
		return 0;
	}

	public int adicionarNumeroPaginas(String paginas) {
		try {
			int pag = Integer.parseInt(paginas);
			return pag;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public void inserePpg(String cd, String nome) {
		Ppg ppg = new Ppg(cd, nome);
		if (this.ppgs.contains(ppg)) {
			return;
		}
		this.ppgs.add(ppg);
	}

	public void insereInstituicao(String sigla, String nome) {
		Instituicao i = new Instituicao(sigla, nome);
		if (this.instituicoes.contains(i)) {
			return;
		}
		this.instituicoes.add(i);
	}

	public void inserePpg_Instituicao(String cd_ppg, String sigla, String nomePpg, String nomeInst) {
		Ppg_Instituicao ppgI = new Ppg_Instituicao(cd_ppg, sigla, nomePpg, nomeInst);
		if (this.ppgs_instituicoes.contains(ppgI)) {
			return;
		}
		this.ppgs_instituicoes.add(ppgI);
	}

	public String retiraPontoVirgula(String linha) {
		boolean possuiAspa = false;
		for (int i = 0; i < linha.length(); i++) {
			if (linha.charAt(i) == '"') {
				possuiAspa = !possuiAspa;
			}
			if (linha.charAt(i) == ';' && possuiAspa) {
				linha = linha.substring(0, i) + (char) 127 + linha.substring(i + 1);
			}
		}
		return linha;
	}

	public String[] devolvePontoVirgula(String[] campos) {
		int i = 0;
		for (String s : campos) {
			for (int j = 0; j < campos[i].length(); j++) {
				if (campos[i].charAt(j) == (char) 127) {
					campos[i] = campos[i].substring(0, j) + ";" + campos[i].substring(j + 1);
				}
			}
			i++;
		}
		return campos;
	}

	public TreeSet<Ppg> getPpgs() {
		return ppgs;
	}

	public void setPpgs(TreeSet<Ppg> ppgs) {
		this.ppgs = ppgs;
	}

	public ArrayList<TipoProducao> getProducoes() {
		return producoes;
	}

	public void setProducoes(ArrayList<TipoProducao> producoes) {
		this.producoes = producoes;
	}

	public TreeSet<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(TreeSet<Instituicao> instituicoes) {
		this.instituicoes = instituicoes;
	}

	public TreeSet<Ppg_Instituicao> getPpgs_instituicoes() {
		return ppgs_instituicoes;
	}

	public void setPpgs_instituicoes(TreeSet<Ppg_Instituicao> ppgs_instituicoes) {
		this.ppgs_instituicoes = ppgs_instituicoes;
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public int getNumDocs() {
		return numDocs;
	}

	public void setNumDocs(int numDocs) {
		this.numDocs = numDocs;
	}

}
