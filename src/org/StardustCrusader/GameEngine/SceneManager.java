package org.StardustCrusader.GameEngine;

import java.io.BufferedInputStream;
import java.util.HashMap;

public class SceneManager {
    public static void LoadScene(BufferedInputStream BIS) {
        SetRenderingScene(null, false);
    }

    static HashMap<Integer, Scene> LoadedScenes = new HashMap<>();

    private static void SetRenderingScene(Scene scene, boolean isAdditive) {
        if (isAdditive)
            LoadedScenes.put(scene.GetID(), scene);
        else {
            LoadedScenes.clear();

            LoadedScenes.put(scene.GetID(), scene);
        }
    }

    public static void LoadBuiltSceneList() {

    }
}
