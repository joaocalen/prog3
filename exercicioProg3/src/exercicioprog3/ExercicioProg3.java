/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicioprog3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author 2018103815
 */
public class ExercicioProg3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Digite o número do exercício: ");
        Scanner entrada = new Scanner(System.in);
        int num = entrada.nextInt();
        switch (num) {
            case 1:
                exercicio1();
                break;
            case 2:
                exercicio2();
                break;
            default:
                break;
        }
    }

    private static void exercicio1() {
        try {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o nome do arquivo: ");

            String nomeArquivo = scanner.nextLine();

            BufferedReader buffer = new BufferedReader(new FileReader(nomeArquivo));

            String linha = "";

            while (linha != null) {
                System.out.println(linha);
                linha = buffer.readLine();
            }
            buffer.close();
        } catch (FileNotFoundException fnf) {
            System.out.println("ERRO! ARQUIVO NÃO ENCONTRADO");
        } catch (IOException e) {
            System.out.println("ERRO!" + e);
        }
    }

    private static Aluno exercicio2() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o nome do Aluno");
        String nome = entrada.nextLine();
        System.out.println("Digite a nota do Aluno");
        float nota = entrada.nextInt();

        Aluno aluno = new Aluno(nome, nota);
        return aluno;

    }
    
    private static void exercicio3(){
        
    }

}
