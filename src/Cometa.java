public class Cometa extends GameObject{
    float vx, vy;
    static public float max_v = 2f;

    Cometa(float x, float y, float vx, float vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        sprite = 'C';
    }
    @Override
    void render() {
        x += vx;
        y += vy;

        int ix = (int) x;
        int iy = (int) y;
        if (GameContext.isInBounds(ix, iy)) {
            Point point = GameContext.getCanvas()[iy][ix];
            point.sprite = sprite;
            point.hasSprite = true;
        }
    }

    @Override
    void checkActive() {
        isActive = GameContext.isInBounds(x, y);
    }
}
