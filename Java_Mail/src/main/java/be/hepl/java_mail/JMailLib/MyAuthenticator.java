/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.hepl.java_mail.JMailLib;

import javax.mail.PasswordAuthentication;

/**
 *
 * @author Arkios
 */
public class MyAuthenticator extends javax.mail.Authenticator {
    private final String ident;
    private final String password;
    
    public MyAuthenticator(String ident, String password){
        this.ident = ident;
        this.password = password;
    }
    
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(ident, password);
    }
}
