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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RNAseqDeNovoFrame().setVisible(true);
            }
        });
    }
}
//http://www.univale.com.br/unisite/mundo-j/artigos/58_JSch.pdf
