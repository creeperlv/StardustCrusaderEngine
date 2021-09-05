package org.StardustCrusader.GameEngine.Input;

import org.StardustCrusader.GameEngine.GameBehavior;
import org.StardustCrusader.GameEngine.Vector2;

public class RaycastTarget extends GameBehavior{
    public Vector2 Position;
    public Vector2 Size;
    public int Z;

    public Boolean IsRaycastisHit(Vector2 MousePosition) {
        return MousePosition.x < Position.x + Size.x / 2 && MousePosition.x > Position.x - Size.x / 2
                && MousePosition.y < Position.y + Size.y / 2 && MousePosition.y > Position.y - Size.y / 2;
    }
}
