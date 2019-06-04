
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorDeArquivo {
	ArrayList<Ppg> ppgs;
	ArrayList<TipoProducao> producoes;
	ArrayList<Instituicao> instituicoes;
	ArrayList<Ppg_Instituicao> ppgs_instituicoes;
	private BufferedReader br;
	long numPaginas;
	int numDocs;

	public LeitorDeArquivo() {
		ppgs = new ArrayList<>();
		producoes = new ArrayList<>();
		instituicoes = new ArrayList<>();
		ppgs_instituicoes = new ArrayList<>();
		numPaginas = 0;
		numDocs = 0;
	}

	public ArrayList<Ppg> getPpgs() {
		return ppgs;
	}

	public ArrayList<TipoProducao> getProducoes() {
		return producoes;
	}

	public ArrayList<Instituicao> getInstituicoes() {
		return instituicoes;
	}

	public ArrayList<Ppg_Instituicao> getPpgs_instituicoes() {
		return ppgs_instituicoes;
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
			int paginas = verificarColuna(campos, "DS_NATUREZA");
			int volume = verificarColuna(campos, "NR_VOLUME");
			int fasciculo = verificarColuna(campos, "DS_FASCICULO");
			int serie = verificarColuna(campos, "NR_SERIE");
			int editora = verificarColuna(campos, "NM_EDITORA");
			int isbn = verificarColuna(campos, "DS_ISBN");
			int formacao = verificarColuna(campos, "DS_FORMACAO_INSTRUMENTAL");
			int traducao = verificarColuna(campos, "DS_IDIOMA_TRADUCAO");

			if (traducao != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					adicionarTraducoes(campos, natureza, titulo, paginas, idioma, editora, cidade, traducao);
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

		Artjr aj = new Artjr(campos[cidade], 0, cd_ppg, campos[titulo], campos[idioma], null, campos[issn]);
		aj.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal], campos[pagInicial]));
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

		Artpe ap = new Artpe(campos[cidade], 0, cd_ppg, campos[natureza], campos[idioma], campos[editora],
				campos[volume], campos[fasciculo], campos[serie], campos[issn]);
		ap.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal], campos[pagInicial]));
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

		Livro l = new Livro(campos[cidade], 0, cd_ppg, campos[natureza], campos[titulo], campos[idioma], campos[isbn],
				campos[editora]);
		l.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal], "1"));
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

		Partitura p = new Partitura(campos[cidade], 0, cd_ppg, campos[natureza], campos[editora], campos[formacao]);
		p.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal], "1"));
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
		Outro o = new Outro(campos[cidade], 0, cd_ppg, campos[natureza], campos[idioma], campos[editora]);
		o.setNumPaginas(adicionarNumeroPaginas(campos[paginas], "1"));
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
		Traducao t = new Traducao(campos[cidade].trim(), 0, cd_ppg, campos[natureza].trim(), campos[titulo],
				campos[idioma], campos[editora], campos[traducao]);
		t.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal], "1"));
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
		Anais a = new Anais(campos[natureza], campos[titulo], campos[idioma], campos[evento], campos[cidade], 0,
				cd_ppg);
		a.setNumPaginas(adicionarNumeroPaginas(campos[pagFinal], campos[pagInicial]));
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
		for (int i = this.ppgs.size() - 1; i >= 0; i--) {
			if (this.ppgs.get(i).getCodigo().equals(cd)) {
				return;
			}
		}
		this.ppgs.add(ppg);
	}

	public void insereInstituicao(String sigla, String nome) {
		for (int i = this.instituicoes.size() - 1; i >= 0; i--) {
			if (this.instituicoes.get(i).getSigla().equals(sigla) && this.instituicoes.get(i).getNome().equals(nome)) {
				return;
			}
		}
		Instituicao i = new Instituicao(sigla, nome);
		this.instituicoes.add(i);
	}

	public void inserePpg_Instituicao(String cd_ppg, String sigla) {
		Ppg_Instituicao ppgI = new Ppg_Instituicao(cd_ppg, sigla);
		if (this.ppgs_instituicoes.size() > 0) {
			int i = this.ppgs_instituicoes.size() - 1;
			while (i > 0) {
				if (this.ppgs_instituicoes.get(i).getSiglaInst().equals(sigla)
						&& this.ppgs_instituicoes.get(i).getCodigoPpg().equals(cd_ppg)) {
					return;
				}
				i--;
			}
			this.ppgs_instituicoes.add(ppgI);
		} else {
			this.ppgs_instituicoes.add(ppgI);
		}
	}

	public void imprimirAnais() {
		System.out.println("Instituicoes que publicaram em anais: " + this.instituicoes.size());
		System.out.println("PPGs que publicaram em anais: " + this.ppgs.size());
		System.out.println("Quantidade de producoes em anais: " + this.producoes.size());
		System.out.println("Quantidade de paginas publicadas em anais: " + numPaginas);
		System.out.printf("Media de paginas por publicacao: %.1f\n", (double) numPaginas / numDocs);
	}
}
