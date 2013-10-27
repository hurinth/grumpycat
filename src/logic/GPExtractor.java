package logic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * Most of the logic in this class was taken from the following pages:
 * http://javadevtips.blogspot.com/2011/10/unzip-files.html
 * http://www.avajava.com/tutorials/lessons/how-do-i-unzip-the-contents-of-a-zip-file.html?page=1
 * http://commons.apache.org/proper/commons-compress/examples.html
 */

public class GPExtractor {

    public boolean extractThemAll(String filePath) {

        File selectedFile = new File(filePath);

        if (selectedFile.isDirectory()) {
            
            for(File child : selectedFile.listFiles()){
                extractThemAll(child.toString());
            }
            
        } else if (filePath.endsWith(".txt.gz")) {

            /**
             * Passes filepPath as the source, and filePath without the .gz as
             * the destination file to extract to.
             */
            unGZipThemAll(filePath,
                    filePath.substring(0, filePath.lastIndexOf(".")));

        } else if (filePath.endsWith(".zip")) {

            String destDir = filePath;
            destDir = destDir.substring(0, destDir.lastIndexOf("."));
            logger("destDir is: " + destDir);

            File destDirectory = new File(destDir);

            try {

                ZipFile zipFile = new ZipFile(selectedFile);
                Enumeration<?> enu = zipFile.entries();

                while (enu.hasMoreElements()) {
                    ZipEntry zipEntry = (ZipEntry) enu.nextElement();

                    // avoid extracting unnecessary XML files
                    if (!zipEntry.toString().endsWith(".xml")) {
                        String name = zipEntry.getName();//returns full path within the ZIP Folder
//                        long size = zipEntry.getSize();
//                        long compressedSize = zipEntry.getCompressedSize();
//                    System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n",
//                            name, size, compressedSize);

                        File extractedFile = new File(destDirectory, name);
                        if (name.endsWith("/")) {
                            extractedFile.mkdirs();
                            continue;
                        }

                        unZipThemAll(zipFile, zipEntry, extractedFile);

                        if (zipEntry.getName().endsWith(".gz")) {
                            String efName = extractedFile.toString();
                            unGZipThemAll(efName,
                                    efName.substring(0, efName.lastIndexOf(".")));
                            extractedFile.delete();
                        }

                    }// END OF XML IF
                }//END OF ITERATION THROUGH THE ZIP CONTAINER
                zipFile.close();
            } catch (ZipException ex) {
                Logger.getLogger(GPExtractor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GPExtractor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;

    }// END OF UNZIPTHEMALL

    private void logger(Object l) {
        System.out.println(l);
    }

    private void unZipThemAll(ZipFile zipFile, ZipEntry zipEntry, File extractedFile) {
        InputStream is = null;
        try {
            is = zipFile.getInputStream(zipEntry);
            FileOutputStream fos = new FileOutputStream(extractedFile);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = is.read(bytes)) >= 0) {
                fos.write(bytes, 0, length);
            }
            is.close();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(GPExtractor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(GPExtractor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void unGZipThemAll(String source, String destination) {
        try {

            FileInputStream fis = new FileInputStream(source);
            BufferedInputStream bis = new BufferedInputStream(fis);
            FileOutputStream fos = new FileOutputStream(destination);
            GZIPInputStream gzis = new GZIPInputStream(bis);

            final byte[] buffer = new byte[2048];
            int n = 0;
            while (-1 != (n = gzis.read(buffer))) {
                fos.write(buffer, 0, n);
            }

            fos.close();
            gzis.close();

        } catch (FileNotFoundException ex) {
            ;
        } catch (IOException ex) {
            ;
        }
    }
}// END OF CLASS