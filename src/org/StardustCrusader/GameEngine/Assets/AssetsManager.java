package org.StardustCrusader.GameEngine.Assets;

import java.io.*;
import java.util.*;

import java.util.zip.*;

public class AssetsManager {

    static HashMap<String, String> FileMap = new HashMap<>();
    static HashMap<String, File> AssetsBundleMap = new HashMap<>();

    public static void LoadManifest(File Manifest) {
        try (BufferedReader BR = new BufferedReader(new FileReader(Manifest))) {
            String Line = "";
            while ((Line = BR.readLine()) != null) {
                if (Line.startsWith("#")) {

                } else if (Line.indexOf("=") > 0) {
                    String Location = Line.substring(0, Line.indexOf("="));
                    String ABF = Line.substring(Line.indexOf("=") + 1);
                    if (!FileMap.containsKey(Location)) {
                        FileMap.put(Location, ABF);
                    }
                    if (!AssetsBundleMap.containsKey(ABF)) {
                        AssetsBundleMap.put(ABF, new File(ABF));
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    public static boolean OUTPUT_STEPS = true;

    public static void CreateManifest(ArrayList<String> AssetBundles, String OutputFile, String PathPrefix) {
        File file = new File(OutputFile);
        if (file.exists()) {
            file.delete();
        }
        try {

            file.createNewFile();
        } catch (Exception e) {
        }
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OutputFile)))) {
            File ff= new File(OutputFile);
            for (String item : AssetBundles) {
                File f = new File(item);
                ZipFile zip = new ZipFile(item);
                for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
                    ZipEntry entry = (ZipEntry) entries.nextElement();
                    String zipEntryName = entry.getName();
                    if (OUTPUT_STEPS)
                        System.out.println("Hit: " + zipEntryName + " in " + f.getName());
                    out.println(zipEntryName + "=" + PathPrefix + f.getName());
                }
                zip.close();
            }
            if(OUTPUT_STEPS){
                System.out.println("Manifest Created At: "+ff.getAbsolutePath());
            }
        } catch (Exception e) {

        }
    }

    public static BufferedInputStream GetFile(String Location) {
        if (FileMap.containsKey(Location)) {
            try {
                return AssetBundle.GetInputStream(AssetsBundleMap.get((Location)), Location);
            } catch (IOException e) {
                return null;
            }
        } else
            return null;
    }

    public static ArrayList<String> GetLines(String Location) {
        if (FileMap.containsKey(Location)) {
            try {
                return AssetBundle.GetLines(AssetsBundleMap.get((Location)), Location);
            } catch (IOException e) {
                return null;
            }
        } else
            return null;
    }
}
