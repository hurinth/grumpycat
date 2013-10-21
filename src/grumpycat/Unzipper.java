package grumpycat;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import sun.org.mozilla.javascript.regexp.SubString;

/**
 * Most of the logic in this class was taken from a post by Anton Udovichenko at
 * http://javadevtips.blogspot.com/2011/10/unzip-files.html
 * http://www.avajava.com/tutorials/lessons/how-do-i-unzip-the-contents-of-a-zip-file.html?page=1
 */
public class Unzipper {

    protected boolean unZipThemAll(String filePath) {

        File selectedFile = new File(filePath);

        if (!selectedFile.isDirectory()) {
            /* esta funcionando si el nombre de carpeta  destino es distinto al nombre 
             * de una carpeta o archivo ya existente*/

            String destDir = selectedFile.getPath();

            destDir = destDir.substring(0, destDir.lastIndexOf("."));

            logger("destDir is: " + destDir);

            File destDirectory = new File(destDir);

            try {

                ZipFile zipFile = new ZipFile(selectedFile);
                Enumeration<?> enu = zipFile.entries();

                while (enu.hasMoreElements()) {
                    ZipEntry zipEntry = (ZipEntry) enu.nextElement();

                    String name = zipEntry.getName();//returns full path within the ZIP Folder
                    long size = zipEntry.getSize();
                    long compressedSize = zipEntry.getCompressedSize();
                    System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n",
                            name, size, compressedSize);

                    File file = new File(destDirectory, name);
                    if (name.endsWith("/")) {
                        file.mkdirs();
                        continue;
                    }
                }

            } catch (ZipException ex) {
                Logger.getLogger(Unzipper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Unzipper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;

    }// END OF UNZIPTHEMALL

    private void logger(Object l) {
        System.out.println(l);
    }
}// END OF CLASS