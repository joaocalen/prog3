import java.util.ArrayList;
import java.util.List;

//import java.util.ArrayList;

public class Saida {
	public void escolherOperacao(String comando, LeitorDeArquivo l) {
		switch (comando.substring(0, 4).trim()) {
		case "rede":
			imprimirRede(l);
			break;
		case "ppg":
			break;
		case "csv":
			break;
		case "ies":
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
		for(int i = 1; i < lista.size(); i++) {
			if (lista.get(i).getCodigoPpg().equals(lista.get(i-1).getCodigoPpg())) {
				Ppg_Instituicao p = lista.get(i-1);
				System.out.println(p.codigoPpg + ": " + p.nomePpg);
				System.out.println("	- " + p.siglaInst + " (" + p.nomeInst + ")");
				while(i< lista.size() && lista.get(i).getCodigoPpg().equals(p.getCodigoPpg())) {
					System.out.println("	- " + lista.get(i).getSiglaInst() + " (" + lista.get(i).getNomeInst() + ")");
					i++;
				}
			}
		}
		
	}

}
