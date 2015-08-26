package org.umc.core;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;


/**
 *
 * @author santiago
 */
public class Comun {
    //CONSTANTES
    public static final String APP_TITLE = "Ubuntu Mac Changer";
    public static final String APP_VERSION = "1.2";
    
    /**
     * Return the complete Application Name
     * @return String
     */
    public static String getAppNameAndVersion(){
        return APP_TITLE + " " + APP_VERSION;
    }

    public String macRandom() {

        Random r = new Random();
        char[] posibilities = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String result = "02:";

        for (int i = 0; i < 5; i++) {

            result = result + posibilities[r.nextInt(16)] + posibilities[r.nextInt(16)] + ":";

        }
        return result.substring(0, 17);
    }

    public ArrayList<String> interfaceScan() {

        ArrayList<String> interfaces= new ArrayList<String>();
        
        try {
            Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();

            while (ni.hasMoreElements()) {

                NetworkInterface n = ni.nextElement();

                interfaces.add(n.getName());
            }
            return interfaces;

        } catch (SocketException ex) {
            System.out.println("Error! Scan interfaces failed");
            return interfaces;
        }

    }

}
