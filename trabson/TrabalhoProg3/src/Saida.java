

public class Saida {	
	public void escolherOperacao(String comando, LeitorDeArquivo l) {
		switch (comando.substring(0,4).trim()) {
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
			System.out.println("Erro de I/O");
			System.exit(0);
			break;
		}
	}

	public void imprimirAnais(LeitorDeArquivo l) {
		System.out.println("Instituicoes que publicaram em anais: " + l.instituicoes.size());
		System.out.println("PPGs que publicaram em anais: " + l.ppgs.size());
		System.out.println("Quantidade de producoes em anais: " + l.producoes.size());
		System.out.println("Quantidade de paginas publicadas em anais: " + l.getNumPaginas());
		System.out.printf("Media de paginas por publicacao: %.1f\n", (double) l.getNumPaginas() / l.getNumDocs());
	}
	
	public void imprimirRede(LeitorDeArquivo leitor) {
		//leitor.getPpgs_instituicoes().
	}
	
	
}
