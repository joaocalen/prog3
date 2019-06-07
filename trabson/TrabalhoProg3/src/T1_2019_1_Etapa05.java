import java.util.Locale;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joao Felipe Gobeti Calenzani
 */
public class T1_2019_1_Etapa05 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// tempo de início da execução do programa para cálculos de desempenho no
		// desenvolvimento do mesmo
		// long initialTime = System.currentTimeMillis();
		Locale.setDefault(new Locale("pt", "BR"));
		Scanner entrada = new Scanner(System.in);

		String pasta = entrada.nextLine();
		String arquivo = entrada.nextLine();
		LeitorDeArquivo leitor = new LeitorDeArquivo();

		while (arquivo.length() >= 4 && arquivo.substring(arquivo.length() - 4, arquivo.length()).equals(".csv")) {
			leitor.lerCSV(pasta + arquivo);
			arquivo = entrada.nextLine();
		}

		// Leitura para Etapa1
		// leitor.lerCSV(pasta + arquivo);

		Saida s = new Saida();

		s.escolherOperacao(arquivo, leitor);
		// etapa 1
		// s.imprimirAnais(leitor);

		entrada.close();
		/
		/long endTime = System.currentTimeMillis();
		// System.out.println("Tempo total em segundos: " + (endTime - initialTime) /
		// 1000);
	}

}
