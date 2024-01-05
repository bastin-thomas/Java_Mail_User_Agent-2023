/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.hepl.java_mail.JMailLib;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import javax.mail.Address;
import javax.mail.Header;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author Arkios
 */
public class Email {
    
    // <editor-fold defaultstate="collapsed" desc="Properties">
    static String charset = "iso-8859-1";
    
    private String _id;
    private String _from;
    private ArrayList<String> _to;
    private ArrayList<String> _CC;
    private String _subject;
    private String _message;
    private ArrayList<Header> _headers;
    private ArrayList<String> _filePaths;    
    
    private MimeMessage source;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * default constructor
     */
    public Email() {
        _id = "";
        _from = "";
        _to = new ArrayList<>();
        _CC = new ArrayList<>();
        _subject = "";
        _message = "";
        _headers = new ArrayList<>();
        _filePaths = new ArrayList<>();
        
        source = null;
    }
    
    public Email(MimeMessage message) throws MessagingException, IOException {
        setId(message.getMessageID());
        setFrom(message.getFrom());
        setTo(message.getRecipients(RecipientType.TO));
        setCC(message.getRecipients(RecipientType.CC));
        setSubject(message.getSubject());
        setMessage(message);
        setFilePaths(message);
        setHeaders(message.getAllHeaders());
        
        source = message;
    }        
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters/Setters">
   
    /**
     * Set the value of _from
     * @param from
     * @throws javax.mail.MessagingException
     */
    private void setFrom(Address[] from) throws MessagingException {
        //Get From 
        this._from = (from.length < 1) ? "" : ((InternetAddress)from[0]).getAddress();
    }
    
    /**
     * set the To addresses
     * @param _to 
     */
    private void setTo(Address[] _to) {
        ArrayList<String> tmp = new ArrayList<>();
        if(_to == null){
            this._to = tmp;
            return;
        }
        
        for(Address row : _to){
            tmp.add(((InternetAddress)row).getAddress());
        }
        this._to = tmp;
    }
    
    /**
     * set the CC addresses
     * @param _CC 
     */
    private void setCC(Address[] _CC) {
        ArrayList<String> tmp = new ArrayList<>();
        
        if(_CC == null){
            this._CC = tmp;
            return;
        }
        
        for(Address row : _CC){
            tmp.add(((InternetAddress)row).getAddress());
        }
        this._CC = tmp;
    }
    
    /**
     * Set the value of _message
     *
     * @param _message new value of _message
     */
    private void setMessage(MimeMessage _message) throws MessagingException, IOException {
        String ReturnedMessage = "";
        
        //RECUPERATION MESSAGE SIMPLEPART
        if(!_message.isMimeType("multipart/*")){
            ReturnedMessage = MimeUtility.decodeText((String) _message.getContent());
            this._message = ReturnedMessage;
            return;
        }
        
        //Else Try to Reach the Text Part:
        Multipart MultiMsg = (Multipart) _message.getContent();
        int n = MultiMsg.getCount();
        
        //RECUPERATION MESSAGE MULTIPART
        for(int i = 0 ; i<n ; i++){
            Part part = MultiMsg.getBodyPart(i);        //get a nested part
            
            //Message Principal en Directe
            if(part.isMimeType("text/plain") && part.getFileName() == null) {
                ReturnedMessage = MimeUtility.decodeText((String) part.getContent());
                break;
            }
            //Message Principal encapsulé dans un autre multiparts (outlook)
            else{
                if(part.isMimeType("multipart/alternative")){
                    //Recuperation du nested MultiPart
                    Multipart NestedMP = (Multipart) part.getContent();                    
                    int n2 = NestedMP.getCount();       //Recuperation nombre de part
                    
                    //On loop dans le multipart a la recherche d'un textplain
                    for(int j = 0 ; j<n2 ; j++){
                        Part part2 = NestedMP.getBodyPart(0);
                        if(part2.isMimeType("text/plain") && part.getFileName() == null){
                            ReturnedMessage = MimeUtility.decodeText((String) part2.getContent());
                            break;
                        }
                    }
                }
            }
        }
        
        this._message = ReturnedMessage;
    }
    
    /**
     * Set the value of _filePaths
     *
     * @param Message
     * @throws javax.mail.MessagingException
     */
    private void setFilePaths(MimeMessage Message) throws MessagingException, IOException {
        ArrayList<String> ReturnedFilePaths = new ArrayList<>();
        
        //SI SIMPLEPART RETURN
        if(!Message.isMimeType("multipart/*")){
            this._filePaths = ReturnedFilePaths;
            return;
        }
        
        Multipart MultiMsg = (Multipart) Message.getContent();
        int n = MultiMsg.getCount();
        
        //RECUPERATION FILES
        for(int i = 0 ; i<n ; i++){
            Part part = MultiMsg.getBodyPart(i);
            
            //Check if it's a file
            if (part.getDisposition() != null && part.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {
                String FileName = MimeUtility.decodeText(part.getFileName());
                
                ReturnedFilePaths.add(FileName);
            }
        }
        
        this._filePaths = ReturnedFilePaths;
    }
    
    /**
     * 
     * @param _id 
     */
    private void setId(String _id) {
        this._id = _id;
    }

    /**
     * 
     * @param _subject 
     */
    private void setSubject(String _subject) {
        this._subject = _subject;
    }

    /**
     * 
     * @param _headers 
     */
    private void setHeaders(Enumeration _headers) throws UnsupportedEncodingException {
        ArrayList<Header> tmp = new ArrayList<>();
        
        while (_headers.hasMoreElements()) {
            tmp.add((Header)_headers.nextElement());
        }
        
        Collections.reverse(tmp);
        
        this._headers = tmp;
    }
    
    /**
     * Get To into a string
     * @return 
     */
    public String getTo_String() {
        if(_to.isEmpty()) {return "";}
        
        String txt = "";
        for(String addr : _to){
            txt += ", " + addr;
        }
        //On retire la première virgule
        txt = txt.substring(2);
        return txt;
    }

    /**
     * Get CC into a string
     * @return 
     */
    public String getCC_String() {
        if(_CC.isEmpty()) {return "";}
        
        String txt = "";
        for(String addr : _CC){
            txt += ", " + addr;
        }
        //On retire la première virgule
        txt = txt.substring(2);
        return txt;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters">
        public static String getCharset() {
            return charset;
        }

        public String getId() {
            return _id;
        }

        public String getFrom() {
            return _from;
        }

        public ArrayList<String> getTo() {
            return _to;
        }

        public ArrayList<String> getCC() {
            return _CC;
        }

        public String getSubject() {
            return _subject;
        }

        public String getMessage() {
            return _message;
        }

        public ArrayList<Header> getHeaders() {
            return _headers;
        }

        public ArrayList<String> getFilePaths() {
            return _filePaths;
        }

        public MimeMessage getSource() {
            return source;
        }
    // </editor-fold>
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * 
     * @param directory
     * @param fileName
     * @throws MessagingException
     * @throws IOException 
     */
    public void SaveFileTo(String directory, String fileName) throws MessagingException, IOException{
        if (source.isMimeType("multipart/*")) {
            Multipart msgMP = (Multipart) source.getContent();
            int n = msgMP.getCount();
            
            for(int i = 0 ; i<n ; i++){
                //Search the good file
                Part part = msgMP.getBodyPart(i);
                if (part.getDisposition() != null && part.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {
                    
                    //Si le part est un fichier et que le nom correspond, on récupère une référence vers sont flux d'entrée
                    if(MimeUtility.decodeText(part.getFileName()).equals(fileName)){
                        
                        System.out.println("Fichier a télécharger : " + MimeUtility.decodeText(part.getFileName()));
                        
                        //Connexion avec le serveur pour récupérer le fichier
                        InputStream readStream = part.getInputStream();
                        //Endroit ou il va falloir enregistrer le fichier
                        FileOutputStream writeStream = new FileOutputStream(directory);
                        //Temporary Stream
                        ByteArrayOutputStream tmp = new ByteArrayOutputStream();
                        
                        
                        int c;
                        //On met l'inputStream dans le buffer temporaire
                        while ((c = readStream.read()) != -1) {
                            writeStream.write(c);
                        }
                                                
                        
                        //On écrit depuis le stream tampons dans le fichier
                        tmp.writeTo(writeStream);
                        
                        //On ferme tout les flux
                        writeStream.close();
                        tmp.close();
                        readStream.close();
                        
                        break;
                    }
                }
            }
        }
    }
        
        
    @Override
    public String toString() {
        String toreturn = "";
        
        toreturn += "================================================ EMAIL ==================================================\n";
        toreturn += "      id: " + this._id + "\n";
        toreturn += "      from: " + this._from + "\n";
        
        toreturn += "      to: ";
        for(String s : _to){
            toreturn += ", " + s;
        }
        toreturn += "\n";
        
        toreturn += "      CC: ";
        for(String s : _CC){
            toreturn += ", " + s;
        }
        toreturn += "\n";
        
        toreturn += "      Subject: " + _subject + "\n";
        toreturn += "      Message:\n";
        toreturn += _message + "\n\n\n";
        
        toreturn += "      File: ";
        for(String s : _filePaths){
            toreturn += ", " + s;
        }
        toreturn += "\n";
        
        toreturn += "      Headers: \n";
        for(Header h : _headers){
            try {
                toreturn += "Name: " + h.getName() + ", Value: " + MimeUtility.encodeText(h.getValue()) + "\n";
            } catch (UnsupportedEncodingException ex) {
                toreturn += "Name: " + h.getName() + ", ValueUndecoded: " + h.getValue() + "\n";
            }
        }
        toreturn += "================================================ FIN ==================================================\n\n\n";
        
        return toreturn; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    // </editor-fold>    
}
