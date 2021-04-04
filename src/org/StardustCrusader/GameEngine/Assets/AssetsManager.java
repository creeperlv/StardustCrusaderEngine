package org.StardustCrusader.GameEngine.Assets;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AssetsManager {

    static HashMap<String, String> FileMap = new HashMap<>();
    static HashMap<String, File> AssetsBundleMap = new HashMap<>();

    public static void LoadManifest(File Manifest) {
        try (BufferedReader BR = new BufferedReader(new FileReader(Manifest))) {
            String Line="";
            while ((Line=BR.readLine())!=null){
                if(Line.startsWith("#")){

                }else if(Line.indexOf("=")>0){
                    String Location=Line.substring(0, Line.indexOf("="));
                    String ABF=Line.substring(Line.indexOf("=")+1);
                    if(!FileMap.containsKey(Location)){
                        FileMap.put(Location,ABF);
                    }
                    if(!AssetsBundleMap.containsKey(ABF)){
                        AssetsBundleMap.put(ABF,new File(ABF));
                    }
                }
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
        } else return null;
    }

    public static ArrayList<String> GetLines(String Location) {
        if (FileMap.containsKey(Location)) {
            try {
                return AssetBundle.GetLines(AssetsBundleMap.get((Location)), Location);
            } catch (IOException e) {
                return null;
            }
        } else return null;
    }
}
