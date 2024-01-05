/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.hepl.java_mail.JMailLib;

import be.hepl.java_mail.GUI.HomePage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author Arkios
 */
public class RunnableRefreshMail implements Runnable {
    // <editor-fold defaultstate="collapsed" desc="Properties">
        private HomePage _home;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
        public RunnableRefreshMail(HomePage Home) {
            _home = Home;
        }
    // </editor-fold>
    
    @Override
    public void run() {
            try {
                System.out.println("TryRefreshMail:");
                this._home.onRefreshMail();
            } catch (MessagingException ex) {
                Logger.getLogger(RunnableRefreshMail.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
