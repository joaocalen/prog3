import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

//import java.util.ArrayList;

public class Saida {
	public void escolherOperacao(String comando, LeitorDeArquivo l) {
		switch (comando.substring(0, 4).trim()) {
		case "rede":
			imprimirRede(l);
			break;
		case "ppg":
			imprimirPpg(comando.substring(4), l);
			break;
		case "ies":
			imprimirIes(comando.substring(4), l);
			break;
		case "csv":
			imprimirCsv(comando.substring(4), l);
			break;
		default:
			System.out.println("Comando desconhecido.");
			System.exit(0);
			break;
		}
	}

	public void imprimirAnais(LeitorDeArquivo l) {
		System.out.println("Instituicoes que publicaram em anais: " + l.getInstituicoes().size());
		System.out.println("PPGs que publicaram em anais: " + l.getPpgs().size());
		System.out.println("Quantidade de producoes em anais: " + l.getProducoes().size());
		System.out.println("Quantidade de paginas publicadas em anais: " + l.getNumPaginas());
		System.out.printf("Media de paginas por publicacao: %.1f\n", (double) l.getNumPaginas() / l.getNumDocs());
	}

	public void imprimirRede(LeitorDeArquivo leitor) {

		List<Ppg_Instituicao> lista = new ArrayList<Ppg_Instituicao>(leitor.getPpgs_instituicoes());

		System.out.println("Programas em rede:");
		for (int i = 1; i < lista.size(); i++) {
			if (lista.get(i).getCodigoPpg().equals(lista.get(i - 1).getCodigoPpg())) {
				Ppg_Instituicao p = lista.get(i - 1);
				System.out.println(p.codigoPpg + ": " + p.nomePpg);
				System.out.println("	- " + p.siglaInst + " (" + p.nomeInst + ")");
				while (i < lista.size() && lista.get(i).getCodigoPpg().equals(p.getCodigoPpg())) {
					System.out
							.println("	- " + lista.get(i).getSiglaInst() + " (" + lista.get(i).getNomeInst() + ")");
					i++;
				}
			}
		}

	}

	public void imprimirPpg(String cd_ppg, LeitorDeArquivo leitor) {
		Ppg ppg = null;
		for (Ppg p : leitor.getPpgs()) {
			if (p.getCodigo().equals(cd_ppg)) {
				ppg = p;
				break;
			}
		}
		if (ppg != null) {
			System.out.println("Programa: " + ppg.getNome());
			System.out.println("Instituicoes:");
			int n = 0;
			for (Ppg_Instituicao pi : leitor.getPpgs_instituicoes()) {
				if (pi.getCodigoPpg().equals(ppg.getCodigo())) {
					System.out.println("	- " + pi.getSiglaInst() + " (" + pi.getNomeInst() + ")");
					n++;
				} else if (n != 0) {
					System.out.println();
					break;
				}
			}
			int numAnais = 0, numArtjr = 0, numArtpe = 0, numLivros = 0, numPartituras = 0, numTraducoes = 0,
					numOutros = 0, numPaginas = 0;

			for (TipoProducao tp : leitor.getProducoes()) {
				if (tp.getCd_Ppg().equals(ppg.getCodigo())) {
					if (tp instanceof Anais) {
						numAnais++;
					} else if (tp instanceof Artjr) {
						numArtjr++;
					} else if (tp instanceof Artpe) {
						numArtpe++;
					} else if (tp instanceof Livro) {
						numLivros++;
					} else if (tp instanceof Partitura) {
						numPartituras++;
					} else if (tp instanceof Traducao) {
						numTraducoes++;
					} else if (tp instanceof Outro) {
						numOutros++;
					} else {
						System.out.println("ERRO INESPERADO");
					}
					numPaginas += tp.getNumPaginas();
				}
			}

			System.out.println("Quantidade de producoes por tipo:");
			System.out.println("	- Artigos em anais de eventos: " + numAnais);
			System.out.println("	- Artigos em jornais e revistas: " + numArtjr);
			System.out.println("	- Artigos em periodicos cientificos: " + numArtpe);
			System.out.println("	- Livros: " + numLivros);
			System.out.println("	- Partituras musicais: " + numPartituras);
			System.out.println("	- Traducoes: " + numTraducoes);
			System.out.println("	- Outros: " + numOutros);
			System.out.println();
			System.out.println("Total de paginas produzidas pelo PPG: " + numPaginas);
		} else {
			System.out.println("PPG nao encontrado. ");
		}

	}

	public void imprimirIes(String sigla, LeitorDeArquivo leitor) {
		Instituicao ies = null;
		for (Instituicao i : leitor.getInstituicoes()) {
			if (i.getSigla().equals(sigla)) {
				ies = i;
			}
		}
		if (ies != null) {
			List<Ppg_Instituicao> listaAuxiliar = new ArrayList<Ppg_Instituicao>();
			System.out.println(ies.getNome() + " (" + ies.getSigla() + "):");
			int numProducoes = 0;
			for (Ppg_Instituicao pi : leitor.getPpgs_instituicoes()) {
				if (pi.getSiglaInst().equals(ies.getSigla())) {
					listaAuxiliar.add(pi);
				}
			}
			Collections.sort(listaAuxiliar, new ComparadorNome());
			for (Ppg_Instituicao pi : listaAuxiliar) {
				for (TipoProducao tp : leitor.getProducoes()) {
					if (tp.getCd_Ppg().equals(pi.getCodigoPpg())) {
						numProducoes++;
					}
				}
				System.out.println("	- " + pi.getNomePpg() + ": " + numProducoes + " producoes");
				numProducoes = 0;
			}
		} else {
			System.out.println("IES nao encontrada.");
		}
	}

	public void imprimirCsv(String comando, LeitorDeArquivo leitor) {
		String[] divisao = comando.split(" ");
		String cd_ppg = divisao[0];
		String tipo = divisao[1];
		
		switch (tipo) {
		case "anais":
			gerarCsvAnais(cd_ppg, leitor);
			break;
		case "artjr":
			gerarCsvArtjr(cd_ppg, leitor);
			break;
		case "artpe":
			gerarCsvArtpe(cd_ppg, leitor);
			break;
		case "livro":
			gerarCsvLivro(cd_ppg, leitor);
			break;
		case "outro":
			gerarCsvOutro(cd_ppg, leitor);
			break;
		case "partmu":
			gerarCsvPartitura(cd_ppg, leitor);
			break;
		case "tradu":
			gerarCsvTraducao(cd_ppg, leitor);
			break;
		default:
			break;
		}
	}

	public void gerarCsvAnais(String cd_ppg, LeitorDeArquivo leitor) {
		TreeSet<Anais> colecaoAnais = new TreeSet<Anais>();
		for(TipoProducao tp : leitor.getProducoes()) {
			if(tp instanceof Anais && tp.getCd_Ppg().equals(cd_ppg)) {
				Anais a = (Anais) tp;
				colecaoAnais.add(a);
			}
		}
		
		System.out.println("Natureza;Titulo;Idioma;Evento;Cidade;Paginas");
		for(Anais a : colecaoAnais) {
			System.out.println(a.toString());
		}
	}

	public void gerarCsvArtjr(String cd_ppg, LeitorDeArquivo leitor) {

	}

	public void gerarCsvArtpe(String cd_ppg, LeitorDeArquivo leitor) {

	}

	public void gerarCsvLivro(String cd_ppg, LeitorDeArquivo leitor) {

	}

	public void gerarCsvOutro(String cd_ppg, LeitorDeArquivo leitor) {

	}

	public void gerarCsvPartitura(String cd_ppg, LeitorDeArquivo leitor) {

	}

	public void gerarCsvTraducao(String cd_ppg, LeitorDeArquivo leitor) {

	}

}
