public class Cometa extends GameObject{
    int x, y;
    float vx, vy;
    static public float max_v = 2f;

    Cometa(float x, float y, Vector2 vector2) {
        this.x = (int) x;
        this.y = (int) y;
        this.vx = vector2.x;
        this.vy = vector2.y;
        sprite = 'C';
    }
    @Override
    public void render() {
        x += vx;
        y += vy;

        int ix = x;
        int iy = y;
        if (GameContext.isInBounds(ix, iy)) {
            Point point = GameContext.getCanvas()[iy][ix];
            point.sprite = sprite;
            point.hasSprite = true;
        }
    }

    void checkActive() {
        isActive = GameContext.isInBounds(x, y);
    }
}
