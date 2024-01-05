/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.hepl.java_mail.JMailLib;
import javax.mail.MessagingException;
import be.hepl.java_mail.GUI.LoginPage;
import java.util.logging.Level;

/**
 *
 * @author Arkios
 */
public class RunnableConnexion implements Runnable {
    
    private final LoginPage login;
    
    public RunnableConnexion(LoginPage login){
        this.login = login;
    }
    
    
    @Override
    public void run() {
        login.doConnexionLogic();
    }
}
