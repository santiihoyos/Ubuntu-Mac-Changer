package MainPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author @santiihoyos
 */
public class LinuxModule{

    File scriptCreator(String MAC, String interfaz) throws InterruptedException, IOException {

        File fichero = new File(".tempLixScrip.sh");
        BufferedWriter out = null;

        if (fichero.exists()) {

            return fichero;
        } else {

                out = new BufferedWriter(new FileWriter(".tempLixScrip.sh", true));
                out.write("!/bin/bash\n"
                        + "$USER - sudo with zenity password window\n"
                        + "run:	adminex command\n"
                        + "zenity	--title=\"We need your authorization:\" --text=\"Change MAC requires authorization please enter the Administrator password:\" \\\n"
                        + "--entry --entry-text \"\" --hide-text \\\n"
                        + "|sudo -S \"$@\" rfkill block all\n"
                        + "sleep 1s\n"
                        + "sudo -S \"$@\" ifconfig " + interfaz + " hw ether " + MAC + " \n"
                        + "sleep 1s\n"
                        + "sudo -S \"$@\" rfkill unblock all \n");

                Process pro = Runtime.getRuntime().exec("chmod 777 .tempLixScrip.sh");

                out.close();
                
            return fichero;
        }
    }
}
