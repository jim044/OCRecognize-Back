package com.ocrecognize.utils;

import liquibase.util.file.FilenameUtils;
import java.io.*;
import java.net.URL;

public class DownloadUtils {

    public static void downloadFileByURL(String url, String destination){
        FilenameUtils.getName(url);
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(destination)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            byte[] response = out.toByteArray();

            FileOutputStream fos = new FileOutputStream(destination);
            fos.write(response);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
