
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorDeArquivo {
	ArrayList<Ppg> ppgs;
	ArrayList<Anais> anais;
	ArrayList<Instituicao> instituicoes;
	ArrayList<Ppg_Instituicao> ppgs_instituicoes;
	private BufferedReader br;
	long numPaginas;
	int numDocs;

	public LeitorDeArquivo() {
		ppgs = new ArrayList<>();
		anais = new ArrayList<>();
		instituicoes = new ArrayList<>();
		ppgs_instituicoes = new ArrayList<>();
		numPaginas = 0;
		numDocs = 0;
	}

	public ArrayList<Ppg> getPpgs() {
		return ppgs;
	}

	public ArrayList<Anais> getAnais() {
		return anais;
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
					inserirTraducoes(campos);
				}
			} else if (formacao != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					inserirPartitura(campos);
				}
			} else if (isbn != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					inserirLivro(campos);
				}
			} else if (data != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					inserirArtjr(campos);
				}
			} else if (issn != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					inserirArtpe(campos);
				}
			} else if (titulo != -1) {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					inserirAnais(campos, natureza, titulo, pagFinal, pagInicial, evento, cidade, idioma);
				}
			} else {
				while (br.ready()) {
					linha = br.readLine();
					campos = linha.split(";");
					inserirOutros(campos);
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

	public void inserirArtjr(String[] campos) {

	}

	public void inserirArtpe(String[] campos) {

	}

	public void inserirLivro(String[] campos) {

	}

	public void inserirPartitura(String[] campos) {

	}

	public void inserirOutros(String[] campos) {

	}

	public void inserirTraducoes(String[] campos) {

	}

	public void inserirAnais(String[] campos, int a, int b, int c, int d, int e, int f, int g) {
		String cd_ppg;
		String nome_ppg;
		String sigla_inst;
		String nome_inst;
		String natureza;
		String titulo;
		String pagFinal;
		String pagInicial;
		String evento;
		String cidade;
		String idioma;

		cd_ppg = campos[0].trim();
		nome_ppg = campos[1].trim();
		sigla_inst = campos[2].trim();
		nome_inst = campos[3].trim();

		natureza = campos[a].trim();
		titulo = campos[b].trim();
		pagFinal = campos[c].trim();
		pagInicial = campos[d].trim();
		evento = campos[e].trim();
		cidade = campos[f].trim();
		idioma = campos[g].trim();

		insereAnais(natureza, titulo, pagFinal, pagInicial, evento, cidade, idioma, cd_ppg);
		inserePpg(cd_ppg, nome_ppg);
		insereInstituicao(sigla_inst, nome_inst);
		inserePpg_Instituicao(cd_ppg, sigla_inst);

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

	public void insereAnais(String natureza, String titulo, String pagFinal, String pagInicial, String evento,
			String cidade, String idioma, String cd_ppg) {
		Anais a = new Anais(natureza, titulo, idioma, evento, cidade, 0, cd_ppg);
		try {
			long pags = Long.parseLong(pagFinal) - Long.parseLong(pagInicial) + 1;
			if (pags > 0 && pags < 2000 && Long.parseLong(pagFinal) > 0 && Long.parseLong(pagInicial) > 0) {
				a.setNumPaginas((int) pags);
				numPaginas += pags;
				numDocs++;
			}
		} catch (NumberFormatException e) {
			this.anais.add(a);
			return;
		}
		this.anais.add(a);
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
		System.out.println("Quantidade de producoes em anais: " + this.anais.size());
		System.out.println("Quantidade de paginas publicadas em anais: " + numPaginas);
		System.out.printf("Media de paginas por publicacao: %.1f\n", (double) numPaginas / numDocs);
	}
}
