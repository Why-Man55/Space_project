abstract public class GameObject {
    float x, y;
    char sprite;
    boolean isActive;

    abstract public void render();

    abstract public void checkActive();
}