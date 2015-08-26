package org.umc.gui.qt;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.gui.QWidget;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.umc.core.Comun;

/**
 * This class use QtJambi. See documentation an license in http://qtjambi.org/
 * 
 * @author http://github.com/arkadoel
 * 
 */
public class MainWindow extends QWidget{
    
    //controls
    private QPushButton btnRefresh;
    private QPushButton btnChange;
    private QPushButton btnRandom;
    protected QComboBox cmbInterface;
    protected QLabel lblTitle;
    private QLabel lblInterface;
    private QLabel lblNewMac;
    private QTextEdit  lblDescription;
    private QLabel jLabel5;
    protected QLineEdit txtMac;  
    
    private Comun comun = new Comun();
    private final int WIDTH = 400;
    private final int HEIGHT = 220;
    
    /**
     * Constructor
     */
    public MainWindow(){
        this.setWindowTitle(Comun.getAppNameAndVersion());
        this.setFixedSize(WIDTH, HEIGHT);
        this.setWindowIcon(new QIcon("classpath:com/trolltech/images/qt-logo.png"));
        
        initControls();
        initComboItems();
        this.setStyleSheet(loadStyles());
        this.centerWindow();
        this.initEvents();
        
        this.show();
    } 
    
    private void centerWindow(){
        QDesktopWidget qdw = new QDesktopWidget();

        int screenWidth = qdw.width();
        int screenHeight = qdw.height();
        
        int x = (screenWidth - WIDTH) / 2;
        int y = (screenHeight - HEIGHT) / 2;
        this.move(x, y);
    }
    
    /**
     * Start the controls
     */
    private void initControls(){
        QGridLayout grid = new QGridLayout();
                
        //buttons
        btnRefresh = new QPushButton("Refresh");
        btnRandom = new QPushButton("Random");
        btnChange = new QPushButton("CHANGE");
        btnChange.setObjectName("btnChange");
        
        //labels
        lblTitle = new QLabel(Comun.getAppNameAndVersion(), this);
        lblTitle.setObjectName("Titulo");
        
        lblInterface = new QLabel("Interface: ", this);
        lblInterface.setMaximumWidth(90);
        lblNewMac = new QLabel("New Mac: ", this);
        lblNewMac.setMaximumWidth(90);
        lblDescription = new QTextEdit("Alert: when you press the button \"change\", "
                    + "the system will ask password for security.", this);
        lblDescription.setReadOnly(true);
                
        txtMac = new QLineEdit("22:22:22:22:22:22", this);
        txtMac.setMinimumWidth(100);
        cmbInterface = new QComboBox(this);
        cmbInterface.setMinimumWidth(100);
        
        //design
        grid.addWidget(lblTitle, 0, 0, 1, 3);
        grid.addWidget(lblInterface, 1, 0);
        grid.addWidget(lblNewMac, 2, 0);
        
        grid.addWidget(btnRefresh, 1, 2);
        grid.addWidget(btnRandom, 2, 2);
        grid.addWidget(btnChange, 3, 2, 1, 1);
        
        grid.addWidget(lblDescription, 3, 0, 2, 2);
        grid.addWidget(txtMac, 2, 1);
        grid.addWidget(cmbInterface, 1, 1);
        
        this.setLayout(grid);
    }
    
    private void initEvents(){
        MainWindow_events eventos = new MainWindow_events(this);
        
        btnRefresh.clicked.connect(eventos, "btnRefresh_click()");
        btnRandom.clicked.connect(eventos, "btnRandom_click()");
        btnChange.clicked.connect(eventos, "btnChange_click()");
    }
    
    protected void initComboItems(){
        ArrayList<String> interfaceScan = comun.interfaceScan();
        interfaceScan.remove("lo");
        cmbInterface.clear();
        cmbInterface.addItems(interfaceScan);
    }
    
    /**
     * Carga el estilo Qt desde un archivo
     * OJO: COPIAR ESTILO.QSS EN EL DIRECTORIO DONDE SE EJECUTA
     * @return 
     */
    private String loadStyles(){
        try{
            File f = new File("estilo.qss");
            byte[] bytes = Files.readAllBytes(f.toPath());
            String lineas = new String(bytes,"UTF-8");
            return lineas;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }
    
    
}
