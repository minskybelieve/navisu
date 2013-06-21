/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.controller.loader;

import java.io.FileReader;

/**
 *
 * @author Serge Morvan
 */
public class LibLoader {

    public LibLoader() {
        FileReader in = null;

        String system = System.getProperty("os.name");
        System.out.println("System.getProperty(\"os.name\") : " + System.getProperty("os.name"));
        boolean is64bit = System.getProperty("sun.arch.data.model").contains("64");
        System.out.println("sun.arch.data.model : " + System.getProperty("sun.arch.data.model"));
        System.out.println("os.arch : " + System.getProperty("os.arch"));
        String libExtension = "Windows".equals(system) ? ".dll" : ".so";
        /*
        String mylibName = "librxtxSerial" + libExtension;
        URL libUrl = LibLoader.class.getResource(mylibName);
        File file = new File(mylibName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            in = new FileReader(new File(libUrl.getFile()));
            final FileWriter out;
            out = new FileWriter(file);
            byte[] buffer = new byte[1048];
            //  while (in. > 0) {
            //      int read = in.read(buffer);
            //      out.write(buffer, 0, read);

            System.loadLibrary("myLibrary");
            in.close();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        * */
    }
}