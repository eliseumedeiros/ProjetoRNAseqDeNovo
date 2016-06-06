/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rnaseqdenovo;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.swing.JOptionPane;

/**
 *
 * @author EliseuMedeiros
 */
public class RNAseqDeNovo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PrincipalFrame inicio = new PrincipalFrame();
        inicio.setVisible(true);  
        try{
          JSch jsch=new JSch();
          //variáveis para conexão
          String host=null; //@localhost
          String user=null; //usuário
          int porta=4422; //porta ssh
          String password=null;
          Session session=jsch.getSession(user, host, porta); //estabelecendo conexão
          session.setPassword(password);
          // criando 
          UserInfo ui = new MyUserInfo(){
            public void showMessage(String message){
              JOptionPane.showMessageDialog(null, message);
            }
            public boolean promptYesNo(String message){
              Object[] options={ "yes", "no" };
              int foo=JOptionPane.showOptionDialog(null, 
                                                   message,
                                                   "Warning", 
                                                   JOptionPane.DEFAULT_OPTION, 
                                                   JOptionPane.WARNING_MESSAGE,
                                                   null, options, options[0]);
              return foo==0;
            }
         };
        }catch (Exception e){}
    }    
     public static File createShellScript() {
	     String filename = "shellscript.sh";
	     File fstream = new File(filename);

	     try{
	          // Create file 
	         PrintStream out = new PrintStream(new FileOutputStream(fstream));
	         out.println("#!/bin/bash");
	         out.println("cd tutorialRNASEQ");
	         out.println("mkdir screen");
	         out.println("fastq_screen --outdir screen brain_1.fastq brain_2.fastq");
	         out.println("fastqc brain_1.fastq brain_2.fastq");
	         out.println("trim_galore -q 20 brain_1.fastq");
	         out.println("trim_galore -q 20 brain_2.fastq");
	         out.println("fastqc brain_1_trimmed.fq brain_2_trimmed.fq");

	         out.println("fastq_screen --outdir screen adrenal_1.fastq adrenal_2.fastq");
	         out.println("fastqc adrenal_1.fastq adrenal_2.fastq");
	         out.println("trim_galore -q 20 adrenal_1.fastq");
	         out.println("trim_galore -q 20 adrenal_2.fastq");
	         out.println("fastqc adrenal_1_trimmed.fq adrenal_2_trimmed.fq");

	         out.println("mkdir adrenal");
	         out.println("tophat2 -o adrenal --GTF hg19-chr19-gene-annotation.gtf /home/databases/hg19/hg19 adrenal_1.fastq,adrenal_2.fastq");
	         out.println("mkdir brain");
	         out.println("tophat2 -o brain --GTF hg19-chr19-gene-annotation.gtf /home/databases/hg19/hg19 brain_1.fastq,brain_2.fastq");
	         out.println("mkdir cuff_adrenal");
	         out.println("cufflinks -o cuff_adrenal -G hg19-chr19-gene-annotation.gtf adrenal/accepted_hits.bam");
	         out.println("mkdir cuff_brain");
	         out.println("cufflinks -o cuff_brain -G hg19-chr19-gene-annotation.gtf brain/accepted_hits.bam");
	         out.println("mkdir diff");
	         out.println("cuffdiff hg19-chr19-gene-annotation.gtf adrenal/accepted_hits.bam brain/accepted_hits.bam -o diff");
	         out.println("ls");
	         //Close the output stream
	         out.close();
	     }catch (Exception e){//Catch exception if any
	         System.err.println("Error: " + e.getMessage());
	     }
	     return fstream;

	}
}
//http://www.univale.com.br/unisite/mundo-j/artigos/58_JSch.pdf
