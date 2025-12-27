class Point {
    float l;
    char sprite = ' ';
    boolean hasSprite = false;
    int x, y;

    static char[] SPRITES = {' ', '.', '-', '#', '@'};

    Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.l = 0f;
    }

    public void reset() {
        this.l = 0f;
        this.hasSprite = false;
        this.sprite = ' ';
    }

    public char render() {
        if (this.l > 100f) {
            this.l = 100f;
        }
        if (!this.hasSprite) {
            int index = (int)(this.l / 100 * (SPRITES.length - 1));
            if (index < 0) index = 0;
            if (index >= SPRITES.length) index = SPRITES.length - 1;
            this.sprite = SPRITES[index];
        }
        return this.sprite;
    }
}
