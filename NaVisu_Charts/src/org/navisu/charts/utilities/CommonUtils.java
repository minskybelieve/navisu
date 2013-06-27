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
package org.navisu.charts.utilities;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thibault
 */
public class CommonUtils {
    
    public static List<Path> listFilesRecursively(Path path, final String ext) {
        return listFilesRecursively(path.toAbsolutePath().toString(), ext);
    }
    
    public static List<Path> listFilesRecursively(String path, final String ext) {
        
        assert path != null;
        assert ext  != null;
        
        Path chartspath = Paths.get(path);
        List<Path> chartlist = new ArrayList<>();
        
        if(Files.notExists(chartspath)) {
            print(path + " does not exists.");
        }
        
        if(!Files.isDirectory(chartspath)) {
            print(path + " is not a directory.");
        }
        
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(ext);
            }
        };
        
        File[] files = chartspath.toFile().listFiles(filter);
        
        try {
            for(File file : files) {
                if(file.isDirectory()) {
                    chartlist.addAll(listFilesRecursively(file.getAbsolutePath(), ext));
                } else {
                    chartlist.add(file.toPath());
                }
            }
        } catch(NullPointerException ex) {
            printerr(ex);
        }
        
        return chartlist;
    }
    
    public static List<Path> listFiles(Path root, final String ext) {
        return listFiles(root.toAbsolutePath().toString(), ext);
    }
    
    public static List<Path> listFiles(String root, final String ext) {
        Path rootPath = Paths.get(root);
        List<Path> files = new ArrayList<>();
        
        if(Files.notExists(rootPath)) {
            print(root + " does not exists.");
        }
        
        if(!Files.isDirectory(rootPath)) {
            print(root + " is not a directory.");
        }
        
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(ext);
            }
        };
        
        File rootFile = rootPath.toFile();
        
        if(rootFile != null) {
            File[] listFiles = rootFile.listFiles(filter);
            if(listFiles != null) {
                for(File file : listFiles) {
                    if(!file.isDirectory()) {
                        files.add(file.toPath());
                    }
                }
            }
        }
        else {
            System.err.println("NUUUUUUUUUUUUUUUUUULL");
        }
        return files;
    }
    
    public static void println(Object string) {
        System.out.println(string);
    }
    
    public static void print(Object string) {
        System.out.print(string);
    }
    
    public static void printerr(Object string) {
        System.err.println(string);
    }
    
    public static void exit() {
        System.exit(0);
    }
    
    public static String fileNameWithoutExt(Path filepath) {
        String fileName = filepath.getFileName().toString();
        return fileName.substring(0, fileName.length()-4);
    }
    
    public static String fileExtension(Path filepath) {
        return CommonUtils.fileExtension(filepath.toFile());
    }
    
    public static String fileExtension(File file) {
        String fileName = file.getName();
        int dashpos = fileName.lastIndexOf(".");
        return fileName.substring(dashpos + 1);
    }
    
    public static List<File> getFilesByExtension(Path filepath, String ext) {
        List<File> files = new ArrayList<>();
        for(File file : filepath.toFile().listFiles()) {
            if(CommonUtils.fileExtension(file.toPath()).toLowerCase().equals(ext)) {
                files.add(file);
            }
        }
        
        return files;
    }
    
    public static void delete(File file) {
        delete(file.toPath());
    }
    
    public static void delete(String file) {
        delete(Paths.get(file));
    }
    
    public static void delete(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException ex) {
            Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException ex) {
            Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*public static byte[] serialize(Object o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject(o);
        return baos.toByteArray();
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream( bais );
        Object o = ois.readObject();
        return o;
    }*/
    
    private CommonUtils() {}
}
