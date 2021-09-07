package org.StardustCrusader.GameEngine.Tools;

import org.StardustCrusader.GameEngine.Assets.AssetBundle;

public class AssetBundleTool {
    public static void main(String[] args) {
        if (args.length != 2 && args.length != 3) {
            System.out.println("SCE AssetBundle Tool v.1.00");
            System.out.println("");
            System.out.println("Usage: AssetBundleTool.jar <Path-To-Pack> <Output-File> [-q|--quiet]");
            System.out.println(
                    "\tNOTE: <Path-To-Pack> will preserve the folder name. e.g.: When <Path-to-Pack> is \".\\Res\\\", the output will make \"Res\" as the root.");
            System.exit(1);
        }
        var PackPath = args[0];
        var Output = args[1];
        if (args.length == 3) {
            if (args[2].equals("-q") || args[2].equals("--quiet")) {
                AssetBundle.OUTPUT_STEPS = false;
            }
        }
        if (AssetBundle.OUTPUT_STEPS)
            System.out.println("SCE AssetBundle Tool v.1.00");
        try {
            if (AssetBundle.OUTPUT_STEPS)
                System.out.println("Target:" + PackPath);
            AssetBundle.CreateBundle(PackPath, Output);
        } catch (Exception e) {
            System.out.println("Build AssetBundle failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
