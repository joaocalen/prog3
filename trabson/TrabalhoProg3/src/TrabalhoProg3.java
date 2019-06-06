import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 2018103815
 */
public class TrabalhoProg3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	//long initialTime = System.currentTimeMillis();
        Scanner entrada = new Scanner(System.in);
        
        String pasta = entrada.nextLine();
        String arquivo = entrada.nextLine();
        LeitorDeArquivo leitor = new LeitorDeArquivo();
        
        while (arquivo.length() >=4 && arquivo.substring(arquivo.length()-4,arquivo.length()).equals(".csv")) {        	        
            leitor.lerCSV(pasta+arquivo);
            arquivo = entrada.nextLine();
        }
        
        
        leitor.lerCSV(pasta+arquivo);
        
        Saida s = new Saida();
        
        s.escolherOperacao(arquivo,leitor);
        
        //s.imprimirAnais(leitor);
        
        entrada.close();
       // long endTime = System.currentTimeMillis();
        //System.out.println("Tempo total em segundos: " + (endTime - initialTime)/1000);
    }



}
