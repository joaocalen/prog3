
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class LeitorDeArquivo {
	TreeSet<Ppg> ppgs;
	ArrayList<TipoProducao> producoes;
	TreeSet<Instituicao> instituicoes;
	TreeSet<Ppg_Instituicao> ppgs_instituicoes;
	private BufferedReader br;
	long numPaginas;
	int numDocs;

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
			String[] campos = new String[25];
			br = new BufferedReader(new FileReader(nome));
			linha = br.readLine();
			campos = linha.split(";");

			int natureza = verificarColuna(campos, "DS_NATUREZA");
			int titulo = verificarColuna(campos, "NM_TITULO");
			int pagFinal = verificarColuna(campos, "NR_PAGINA_FINAL");
			int pagInicial = verificarColuna(campos, "NR_PAGINA_INICIAL");
			int evento = verificarColuna(campos, "DS_EVENTO");
			int cidade = verificarColuna(campos, "NM_CIDADE");
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

			if (traducao != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					adicionarTraducoes(campos, natureza, titulo, paginas, idioma, editoraTraducao, cidade, traducao);
				}
			} else if (formacao != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					adicionarPartitura(campos, natureza, editora, cidade, formacao, paginas);
				}
			} else if (isbn != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					adicionarLivro(campos, natureza, titulo, idioma, editora, cidade, isbn, paginas);
				}
			} else if (data != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					adicionarArtjr(campos, cidade, titulo, idioma, data, issn, pagFinal, pagInicial);
				}
			} else if (issn != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					adicionarArtpe(campos, natureza, idioma, editora, cidade, volume, fasciculo, serie, issn, pagFinal,
							pagInicial);
				}
			} else if (titulo != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					adicionarAnais(campos, natureza, titulo, pagFinal, pagInicial, evento, cidade, idioma);
				}
			} else {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					adicionarOutros(campos, natureza, idioma, editora, cidade, paginas);
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

		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst);

		Artjr aj = new Artjr(campos[cidade].trim(), 0, cd_ppg, campos[titulo].trim(), campos[idioma].trim(), null,
				campos[issn].trim());
		aj.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal].trim(), campos[pagInicial].trim()));
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

		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst);

		Artpe ap = new Artpe(campos[cidade].trim(), 0, cd_ppg.trim(), campos[natureza].trim(), campos[idioma].trim(),
				campos[editora].trim(), campos[volume].trim(), campos[fasciculo].trim(), campos[serie].trim(),
				campos[issn].trim());
		ap.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal].trim(), campos[pagInicial].trim()));
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

		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst);

		Livro l = new Livro(campos[cidade].trim(), 0, cd_ppg, campos[natureza].trim(), campos[titulo].trim(),
				campos[idioma].trim(), campos[isbn].trim(), campos[editora].trim());
		l.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal].trim(), "1"));
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

		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst);

		Partitura p = new Partitura(campos[cidade].trim(), 0, cd_ppg, campos[natureza].trim(), campos[editora].trim(),
				campos[formacao].trim());
		p.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal].trim(), "1"));
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

		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst);
		Outro o = new Outro(campos[cidade].trim(), 0, cd_ppg, campos[natureza].trim(), campos[idioma].trim(),
				campos[editora].trim());
		o.setNumPaginas(adicionarNumeroPaginas(campos[paginas].trim(), "1"));
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

		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst);
		Traducao t = new Traducao(campos[cidade].trim(), 0, cd_ppg, campos[natureza].trim(), campos[titulo].trim(),
				campos[idioma].trim(), campos[editora].trim(), campos[traducao].trim());
		t.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal].trim(), "1"));
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

		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst);
		Anais a = new Anais(campos[natureza].trim(), campos[titulo].trim(), campos[idioma].trim(),
				campos[evento].trim(), campos[cidade].trim(), 0, cd_ppg);
		a.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal].trim(), campos[pagInicial].trim()));
		this.producoes.add(a);
	}

	public int adicionarNumeroPaginas(String pagFinal, String pagInicial) {
		try {
			long pags = Long.parseLong(pagFinal) - Long.parseLong(pagInicial) + 1;
			if (pags > 0 && pags < 2000 && Long.parseLong(pagFinal) > 0 && Long.parseLong(pagInicial) > 0) {
				numPaginas += pags;
				numDocs++;
				return (int) pags;
			}
		} catch (NumberFormatException e) {
			return 0;
		}
		return 0;
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

	public void inserePpg_Instituicao(String cd_ppg, String sigla) {
		Ppg_Instituicao ppgI = new Ppg_Instituicao(cd_ppg, sigla);
		if (this.ppgs_instituicoes.contains(ppgI)) {
			return;
		}
		this.ppgs_instituicoes.add(ppgI);
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

	public long getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(long numPaginas) {
		this.numPaginas = numPaginas;
	}

	public int getNumDocs() {
		return numDocs;
	}

	public void setNumDocs(int numDocs) {
		this.numDocs = numDocs;
	}
}
