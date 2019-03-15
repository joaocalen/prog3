/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicioprog3;

/**
 *
 * @author 2018103815
 */
public class Aluno {

    private String nome;
    private float nota;

    public Aluno(String nome, float nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public Aluno() {
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the nota
     */
    public float getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(float nota) {
        this.nota = nota;
    }
    
    

}
