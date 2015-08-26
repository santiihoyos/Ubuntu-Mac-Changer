package org.umc;

import com.trolltech.qt.gui.QApplication;
import org.umc.gui.qt.MainWindow;
import org.umc.gui.swing.App;

/**
 *
 * @author Santiago
 * @contributor http://github.com/arkadoel
 */
public class Main {
    
    /**
     * Iniciar la version Swing
     */
    public static void iniciarSwing(){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }
    
    /**
     * Iniciar version QT4
     * @param args 
     */
    public static void iniciarQT(String[] args){
        QApplication.initialize(args);
        MainWindow mainWindow = new org.umc.gui.qt.MainWindow();
        QApplication.exec();
        QApplication.exit(0);
    }
    
    public static void main(String[] args){
        
        iniciarQT(args);
        
        
        if(args.length >0 ){
            String primerParam = args[0];
            
            switch (primerParam) {
                case "--qt-gui":
                    iniciarQT(args);
                    break;
                case "--help":
                    break;
            }
        }
        else{
            //iniciarSwing();
        }
        
    }
}
