/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rnaseqdenovo;

import java.io.IOException;

/**
 *
 * @author EliseuMedeiros
 */
public class RNAseqDeNovo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String comando = "/home/eliseumedeiros/testandoCriarPasta";

        Runtime run = Runtime.getRuntime();
 
        try {
            System.out.print("\n#Criando arquivo "+comando);

            String command = "mkdir "+comando;

            run.exec(command);
            System.out.print("\n##Processo Finalizado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
