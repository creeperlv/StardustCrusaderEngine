package org.StardustCrusader.GameEngine.Assets;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.*;
import java.io.*;
import java.nio.charset.Charset;

public class AssetBundle {
    public static boolean OUTPUT_STEPS = true;

    public static void CreateBundle(String folder, String TargetBundle) throws Exception {
        File Bundle = new File(TargetBundle);
        if (!Bundle.exists())
            Bundle.createNewFile();
        File file = new File(folder);
        // InputStream input = null;
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(Bundle));
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; ++i) {
                RecursiveCreation(files[i], zipOut, file.getParentFile().getAbsolutePath());
            }
        } else {
            System.out.println("A directory is required.");
        }
        zipOut.close();
    }

    public static void RecursiveCreation(File Target, ZipOutputStream zipOut, String BasePath) throws IOException {
        if (Target.isDirectory()) {
            for (File item : Target.listFiles()) {
                RecursiveCreation(item, zipOut, BasePath);
            }
        } else {
            if (OUTPUT_STEPS)
                System.out.println("At:" + Target.getAbsolutePath());
            InputStream input = null;
            input = new FileInputStream(Target);
            var Name = Target.getAbsolutePath().substring(BasePath.length() + 1);
            if (OUTPUT_STEPS)
                System.out.println("\tTo:" + Name);
            zipOut.putNextEntry(new ZipEntry(Name));
            int temp = 0;
            while ((temp = input.read()) != -1) {
                zipOut.write(temp);
            }
            input.close();
        }
    }

    public static String GetContent(File AssetBundle, String Location) throws IOException {

        BufferedInputStream ins = GetInputStream(AssetBundle, Location);
        int filelength = ins.available();
        byte[] filecontent = new byte[filelength];
        try {
            ins.read(filecontent);
            ins.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(filecontent, Charset.forName("UTF-8"));
        // return "";
    }

    public static ArrayList<String> GetLines(File AssetBundle, String Location) throws IOException {
        ArrayList<String> Content = new ArrayList<String>();
        try (BufferedReader BR = new BufferedReader(new InputStreamReader(GetInputStream(AssetBundle, Location)))) {
            String line;
            while ((line = BR.readLine()) != null) {
                Content.add(line);
            }
        } catch (Exception e) {
        }
        return Content;
    }

    public static BufferedInputStream GetInputStream(File AssetBundle, String Location) throws IOException {
        ZipFile zip = new ZipFile(AssetBundle);

        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            if (zipEntryName.replace("/", "\\").equals(Location.replace("/", "\\"))) {

                InputStream in = zip.getInputStream(entry);
                // if (!in.markSupported()) {
                // System.out.println("File Item:" + Location + " do not support mark.");
                // }
                return new BufferedInputStream(in);
            }
        }
        zip.close();
        System.out.println("File Item:" + Location + " is not found.");
        return null;
    }
}