/**
 * This file is part of NaVisu.
 *
 * NaVisu is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * NaVisu is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * NaVisu. If not, see <http://www.gnu.org/licenses/>.
 */
package org.navisu.nmea.devices.controller.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thibault
 */
public class NativeLibraries {

    private static final Logger LOG = Logger.getLogger(NativeLibraries.class.getSimpleName());
    private static final String WIN_EXT = ".dll";
    private static final String MAC_EXT = ".jnilib";
    private static final String LIN_EXT = ".so";
    private static String ext = "";
    private static final String[] LIB = new String[]{"rxtxSerial" + ext()};

    private static String ext() {
        //String ext = "";

        if (OSValidator.isWindows()) {
            ext = WIN_EXT;
        } else if (OSValidator.isMac()) {
            ext = MAC_EXT;
        } else if (OSValidator.isUnix()) {
            ext = LIN_EXT;
        }
        return ext;
    }

    private static String getWorkingDirectory() {
        String dir = System.getProperty("user.dir");
        final String coreModuleName = "NaVisu_NMEADevices";
        if (!dir.endsWith(coreModuleName)) {
            dir += File.separator + coreModuleName;
        }
        return dir;
    }

    private static boolean removeExisting() {
        String name;
        boolean success = true;
        for (File f : Paths.get(getWorkingDirectory()).toFile().listFiles()) {
            name = f.getName();
            if (name.endsWith(WIN_EXT) || name.endsWith(WIN_EXT) || name.endsWith(WIN_EXT)) {
                //LOG.log(Level.INFO, "Deleting {0}", name);
                success &= f.delete();
            }
        }
        return success;
    }

    public static void load() {
        /*
         boolean is64 = System.getProperty("os.arch").contains("64");
         Path libDir = Paths.get(getWorkingDirectory(), "bin", (is64 ? "/win64/" : "/win32/"));
        
         InputStream is;
         OutputStream os;
        
         removeExisting();
        
         for (String lib : LIB) {
            
         try {
         if (OSValidator.isMac() || OSValidator.isUnix()) {
         lib = "bin" + lib;
         }

         //LOG.log(Level.INFO, "Loading {0} from {1}", new Object[]{lib, libDir});
         is = new FileInputStream(Paths.get(libDir.toString(), lib).toFile());
         os = new FileOutputStream(Paths.get(getWorkingDirectory(), lib).toFile());
         // TODO ne fonctionne pas sous Mac
         //  FileUtil.copy(is, os);

         is.close();
         os.close();
                
         lib = lib.replace(ext, "");
         // System.setProperty("java.library.path", libDir.toAbsolutePath() + File.separator+lib+ext+File.separator+";"+System.getProperty("java.library.path") );
                
         //  System.out.println("path : " + System.getProperty("java.library.path"));
         // System.out.println("libDir.toAbsolutePath() : " + libDir.toAbsolutePath().toString() + "\\" + lib + ext);
         // Runtime.getRuntime().load(libDir.toAbsolutePath().toString() + "\\" + lib+ext);
                

         // for (File f : Paths.get(libDir.toAbsolutePath().toString()).toFile().listFiles()) {
         //     System.out.println(f.getName());
         //  }
                
                
                
         */
        System.out.println("System.getProperty(\"os.arch\")" + System.getProperty("os.arch"));
        System.out.println("System.getProperty(\"os.name\")" + System.getProperty("os.name"));
        try {
            System.loadLibrary("rxtxSerial" + ext);
        } catch (UnsatisfiedLinkError ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        //  } catch (IOException ex) {
        //    LOG.log(Level.SEVERE, ex.getMessage());
        // }

        //  }
    }

    private NativeLibraries() {
    }
}
