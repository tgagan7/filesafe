/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author tgaga
 */
public class Editor extends javax.swing.JFrame {

    /**
     * Creates new form Editor
     */
    public Editor() {
        initComponents();
        filter = new FileNameExtensionFilter(
        "All Text Files","txt", "java", "c","py");
        FileChooser.setFileFilter(filter);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileChooser = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        editorPane = new javax.swing.JEditorPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuNew = new javax.swing.JMenuItem();
        menuOpen = new javax.swing.JMenuItem();
        menuSave = new javax.swing.JMenuItem();
        menuExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(editorPane);

        jMenu1.setText("File");

        menuNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuNew.setText("New");
        menuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNewActionPerformed(evt);
            }
        });
        jMenu1.add(menuNew);

        menuOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuOpen.setText("Open");
        menuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenActionPerformed(evt);
            }
        });
        jMenu1.add(menuOpen);

        menuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSave.setText("Save");
        menuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveActionPerformed(evt);
            }
        });
        jMenu1.add(menuSave);

        menuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        jMenu1.add(menuExit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewActionPerformed
        // TODO add your handling code here:
         editorPane.setText("");
    }//GEN-LAST:event_menuNewActionPerformed

    private void menuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenActionPerformed
        // TODO add your handling code here:
        int returnVal = FileChooser.showOpenDialog(this);
        if(returnVal==0){
            currentFilePath = FileChooser.getCurrentDirectory().getAbsolutePath()+"\\"+FileChooser.getSelectedFile().getName();
            try {
                String contents = 
                        new String(Files.readAllBytes(Paths.get(currentFilePath)));
                editorPane.setText(contents);
            } catch (IOException ex) {
                
            }
            setTitle(currentFilePath);
        }
        savedContent = editorPane.getText();
    }//GEN-LAST:event_menuOpenActionPerformed

    private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveActionPerformed
        // TODO add your handling code here:
        int ret = FileChooser.showSaveDialog(this);
        doSave(ret);
    }//GEN-LAST:event_menuSaveActionPerformed
    
    void doSave(int ch){
        if(ch==0){
           String path = 
                  FileChooser.getCurrentDirectory()+
                   "\\"+FileChooser.getSelectedFile().getName();
           try {
               File f = new File(path);
               if(f.exists()){
                   int res = JOptionPane.showConfirmDialog(this,"Do You Want to Overwrite the file");
                   if(res==0){
                       FileWriter fw = new FileWriter(f);
                       fw.write(editorPane.getText());
                       fw.close();
                   }
                   else if(res==1){
                       int ret = FileChooser.showSaveDialog(this);
                       doSave(ret);
                   }
               }else{
                   FileWriter fw = new FileWriter(f);
                   fw.write(editorPane.getText());
                   fw.close();
               }
           } catch (IOException ex) {
               
           }
           savedContent = editorPane.getText();
           setTitle(path);
       }
    
    }
    
    
    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        // TODO add your handling code here:
            recentContent = editorPane.getText();
            if(savedContent.equals(recentContent)){
                System.exit(0);
            }
            else{
                int ch = JOptionPane.showConfirmDialog(this, "You have unsaved content Do you want to save?");
                if (ch == 0){
                    int ret = FileChooser.showSaveDialog(this);
                    doSave(ret);
                    System.exit(0);
                }
                else if(ch == 1){
                    System.exit(0);
                }
            }
        
    }//GEN-LAST:event_menuExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JEditorPane editorPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuNew;
    private javax.swing.JMenuItem menuOpen;
    private javax.swing.JMenuItem menuSave;
    // End of variables declaration//GEN-END:variables
     String currentFilePath;
     FileNameExtensionFilter filter;
     String recentContent="";
     String savedContent= "";
}
