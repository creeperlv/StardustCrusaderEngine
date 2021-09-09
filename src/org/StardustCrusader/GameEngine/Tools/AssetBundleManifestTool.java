package org.StardustCrusader.GameEngine.Tools;

import org.StardustCrusader.GameEngine.Assets.AssetsManager;
import java.util.*;

public class AssetBundleManifestTool {
    public static void main(String[] args) {
        ArrayList<String> bundles = new ArrayList<String>();
        String O = "assets.manifest";
        String P = "Assets\\";
        // int __i=0;
        if (args.length == 0) {

            if (args.length != 2 && args.length != 3) {
                System.out.println("SCE AssetBundle Tool v.1.00");
                System.out.println("");
                System.out.println(
                        "Usage: java -jar AssetBundleManifestTool.jar <Path-To-Bundle 0> <Path-To-Bundle 1> <Path-To-Bundle 2> ... [-O OutputFile] [-P PathPrefix] [-S|--SLIENT]");
                System.out.println("\tDefault Value of OutputFile is \"" + O + "\".");
                System.out.println("\tDefault Value of PathPrefix is \"" + P + "\".");
                System.exit(1);
            }
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].toUpperCase().equals("-O")) {
                i++;
                O = args[i];
            } else if (args[i].toUpperCase().equals("-P")) {
                i++;
                P = args[i];
            } else if (args[i].toUpperCase().equals("-S") || args[i].toUpperCase().equals("--SLIENT")) {
                AssetsManager.OUTPUT_STEPS = false;
            } else {
                bundles.add(args[i]);
            }
        }
        if (AssetsManager.OUTPUT_STEPS) {
            System.out.println("SCE AssetBundle Manifest Tool v.1.00");
        }
        AssetsManager.CreateManifest(bundles, O,P);
    }
}
