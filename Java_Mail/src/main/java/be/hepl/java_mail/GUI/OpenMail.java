/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.hepl.java_mail.GUI;

import be.hepl.java_mail.JMailLib.Email;
import java.io.File;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * 
 * @author student
 */
public class OpenMail extends javax.swing.JFrame {
    // <editor-fold defaultstate="collapsed" desc="My Properties">
    private final Email mail;
    private final HomePage _page;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates new form OpenMail
     */
    public OpenMail(Email m, HomePage page) throws MessagingException, IOException {        
        initComponents();
        mail = m;
        _page = page;
        
        if(mail == null){this.dispose();}
        
        //Put the adresses to the text boxes
        From.setText(mail.getFrom());
        To.setText(mail.getTo_String());
        Cc.setText(mail.getCC_String());
        
        //put subject into the text box and the title
        Subject.setText(mail.getSubject());
        this.setTitle(mail.getSubject());
        
        //put the message in the textbox
        Message.setText(mail.getMessage());
        
        
        //Create the ComboxModel with filename and activate it (if there is file)
        if(!mail.getFilePaths().isEmpty()){
            
            DefaultComboBoxModel tmpModel= new DefaultComboBoxModel();
            for(String filename : mail.getFilePaths()){
                tmpModel.addElement(filename);
            }
            jComboBox1.setModel(tmpModel);

            Save.setEnabled(true);
            jComboBox1.setEnabled(true);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane6 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        From = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        Subject = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        Cc = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        To = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        Message = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        Save = new javax.swing.JButton();
        More = new javax.swing.JButton();

        jScrollPane6.setViewportView(jEditorPane1);

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(618, 596));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("From :");

        jLabel2.setText("To :");

        jLabel3.setText("Cc :");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        From.setEditable(false);
        From.setFocusCycleRoot(false);
        From.setFocusable(false);
        jScrollPane1.setViewportView(From);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Subject.setEditable(false);
        Subject.setFocusCycleRoot(false);
        Subject.setFocusable(false);
        jScrollPane3.setViewportView(Subject);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Cc.setEditable(false);
        Cc.setFocusCycleRoot(false);
        Cc.setFocusable(false);
        jScrollPane4.setViewportView(Cc);

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        To.setEditable(false);
        To.setFocusCycleRoot(false);
        To.setFocusable(false);
        jScrollPane5.setViewportView(To);

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Message.setEditable(false);
        Message.setColumns(20);
        Message.setLineWrap(true);
        Message.setRows(5);
        Message.setWrapStyleWord(true);
        Message.setFocusable(false);
        jScrollPane8.setViewportView(Message);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Attachement" }));
        jComboBox1.setEnabled(false);

        jLabel4.setText("Attatchement :");

        Save.setText("Enregistrer");
        Save.setEnabled(false);
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        More.setText("MoreInfo");
        More.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8)
                            .addComponent(jScrollPane3))))
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, 0, 515, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(More)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Save)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Save)
                    .addComponent(More, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Events">
    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        String FileName = (String) jComboBox1.getSelectedItem();
        
        //Recupération de l'extension
        String extension = FileName;
        String[] se = extension.split("\\.");
        extension = se[se.length-1];
        
        
        //Creation d'un file Chooser
        JFileChooser fc = new JFileChooser((FileSystemView.getFileSystemView().getHomeDirectory() + "/Bureau/"));
        fc.setMultiSelectionEnabled(false);
        
        //Permet de mettre un nom par défaut
        fc.setSelectedFile(new File(se[0]));
        
        //Reset des Filtres
        fc.resetChoosableFileFilters();
        
        //Creation d'un filtre sur base de l'extension existante
        FileNameExtensionFilter filter = new FileNameExtensionFilter(extension, extension);
        fc.setFileFilter(filter);
        //Ouverture du savedialog
        fc.showSaveDialog(this);
        
        //File Path to Save
        String Path = fc.getSelectedFile().getAbsolutePath() + "." + extension;
        
        
        try {
            //Redirection du fichier sélectionner du message Correspondant, au path demander.
            mail.SaveFileTo(Path, FileName);
        } catch (MessagingException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        System.out.println("Enregistrement Réussi: " + Path);        
    }//GEN-LAST:event_SaveActionPerformed
    
    private void MoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MoreActionPerformed
        OpenMoreInfoMail window;
        try {
            window = new OpenMoreInfoMail(this, true, this.mail.getHeaders(), this.mail.getSubject());
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        window.setVisible(true);
    }//GEN-LAST:event_MoreActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        _page.StartClock();
    }//GEN-LAST:event_formWindowClosed
    
    
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="DefaultProperties">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane Cc;
    private javax.swing.JTextPane From;
    private javax.swing.JTextArea Message;
    private javax.swing.JButton More;
    private javax.swing.JButton Save;
    private javax.swing.JTextPane Subject;
    private javax.swing.JTextPane To;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
}
