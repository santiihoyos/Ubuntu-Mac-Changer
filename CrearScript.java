package crearscript;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author santiihoyos
 */
public class CrearScript {

   void cambiarMAC(String MAC, String interfaz) {

        File fichero = new File(".tempLixScrip.sh");

        if (fichero.exists()) {
            fichero.delete();
        }

        BufferedWriter out = null;
        try {

            // A partir del objeto File creamos el fichero físicamente
            if (fichero.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente");

                Thread.sleep(1000);

                out = new BufferedWriter(new FileWriter(".tempLixScrip.sh", true));
                out.write("!/bin/bash\n"
                        + "$USER - sudo with zenity password window\n"
                        + "run:	adminex command\n"
                        + "zenity	--title=\"We need your authorization:\" --text=\"Change MAC requires authorization please enter the Administrator password:\" \\\n"
                        + "--entry --entry-text \"\" --hide-text \\\n"
                        + "|sudo -S \"$@\" rfkill block all\n"
                        + "sleep 1s\n"
                        + "sudo -S \"$@\" ifconfig "+interfaz+" hw ether "+MAC+" \n"
                        + "sleep 1s\n"
                        + "sudo -S \"$@\" rfkill unblock all");

                Process pro = Runtime.getRuntime().exec("chmod 777 .tempLixScrip.sh");

                out.close();

                pro = Runtime.getRuntime().exec("./.tempLixScrip.sh");
                
                

                Thread.sleep(1000);

                fichero.delete();

                Thread.sleep(10000);
                
                System.exit(0);
                
            } else {
                System.out.println("No ha podido ser creado el fichero");

            }
        } catch (Exception ioe) {

            System.out.println(ioe.getMessage());
        }

    }

}
