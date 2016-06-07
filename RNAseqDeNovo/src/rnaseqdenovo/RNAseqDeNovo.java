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
	   
	         //Close the output stream
	         out.close();
	     }catch (Exception e){//Catch exception if any
	         System.err.println("Error: " + e.getMessage());
	     }
	     return fstream;

	}
}
//http://www.univale.com.br/unisite/mundo-j/artigos/58_JSch.pdf
