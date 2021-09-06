package org.StardustCrusader.GameEngine.Tools;

import org.StardustCrusader.GameEngine.Assets.AssetBundle;

public class AssetBundleTool {
    public static void main(String[] args) {
        System.out.println("SCE AssetBundle Tool v.1.00");
        if(args.length!=2){
            System.out.println("Usage: AssetBundleTool.jar <Path-To-Pack> <Output-File>");
            System.exit(1);
        }
        var PackPath=args[0];
        var Output=args[1];
        try {
            AssetBundle.CreateBundle(PackPath, Output);
        } catch (Exception e) {
            System.out.println("Build AssetBundle failed: " + e.getMessage());
        }
    }
}
