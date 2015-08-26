package org.umc.gui.qt;

import java.io.File;
import java.io.IOException;
import org.umc.core.Comun;
import org.umc.core.LinuxModule;

/**
 *
 * @author fer
 */
public class MainWindow_events {
    private MainWindow parent;
    
    public MainWindow_events(MainWindow _win){
        parent = _win;
    }
    
    public void btnRefresh_click(){
        parent.initComboItems();
    }
    
    public void btnChange_click(){
        try {
            LinuxModule linux = new LinuxModule();            
            File temp = linux.scriptCreator(parent.txtMac.text(), parent.cmbInterface.currentText());

            Thread.sleep(1000); // Wait for the system to not go too fast.

            Process r = Runtime.getRuntime().exec("./.tempLixScrip.sh"); // run the script Linux in Bash

            Thread.sleep(1000);

            temp.delete();

        } catch (IOException ex) {
            parent.lblTitle.setText("ERROR!");
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            parent.lblTitle.setText("ERROR!");
            ex.printStackTrace();

        }


    }
    
    public void btnRandom_click(){
        Comun comun = new Comun();
        parent.txtMac.setText(comun.macRandom());
    }
}
