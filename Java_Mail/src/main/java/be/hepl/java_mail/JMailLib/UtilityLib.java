/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.hepl.java_mail.JMailLib;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author Arkios
 */
public class UtilityLib {
    
    /*Envoi de message texte basique sans piece jointe*/
    public static void createMessageSimple(MimeMessage mail, Address[] To, Address[] Cc, String Subject, String Text) throws MessagingException, UnsupportedEncodingException{
            System.out.println("Création Message Simple");
            
            mail.addHeader("Content-type", "text/plain; charset=UTF-8");
            
            mail.setFrom();
            
            //Put the To List into the MimeMessage Object
            mail.setRecipients(Message.RecipientType.TO, To);
            
            //Put the Cc List into the MimeMessage Object
            mail.setRecipients(Message.RecipientType.CC, Cc);
            
            //Define Object
            mail.setSubject(Subject);
            
            //Define MainMessage
            ((Message)mail).setContent(MimeUtility.encodeText(Text), "text/plain");
    }
    
    /*Envoi d'un message avec piece jointe multiple et document texte*/
    public static void createMessageMultiPart(MimeMessage mail, Multipart Multip, Address[] To, Address[] Cc, String Subject, String Text) throws MessagingException, UnsupportedEncodingException{
        
        mail.setFrom();
        
        mail.setRecipients(Message.RecipientType.TO, To);
        mail.setRecipients(Message.RecipientType.CC, Cc);

        mail.setSubject(Subject);
        
        System.out.println("Création Message MultiPart");
            //Ajout du Texte comme premier composant
            MimeBodyPart msgBP = new MimeBodyPart(); 
            msgBP.setText(MimeUtility.encodeText(Text));
            
            Multip.addBodyPart(msgBP);
    }
    
    /*Ajoute les fichier joints au message principal*/
    public static void setFilePart(Multipart Multip, String FilePath, ArrayList<String> FileList) throws IOException, MessagingException{
            String[] Names = FilePath.split("\\\\");
            String Name = Names[Names.length - 1];
            
            MimeBodyPart BodyPart = new MimeBodyPart();
            
            //Indiqué le type de multipart
            BodyPart.attachFile(new File(FilePath));            
            BodyPart.setDataHandler (new DataHandler(new FileDataSource (FilePath)));
            BodyPart.setFileName(Name);
            
            Multip.addBodyPart(BodyPart);
            
            FileList.add(Name);
    }
}
