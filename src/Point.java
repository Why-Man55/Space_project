public class Point {
    public char sprite;
    static char[] SPRITES = {' ', '.', '-', '#', '@'};
    public float brightness;
    boolean hasSprite = false;

    {
        this.brightness = 82f;
    }


    public void reset() {
        brightness = 0f;
        hasSprite = false;
        sprite = ' ';
    }


    public char render() {
        if (this.brightness > 100f) {
            this.brightness = 100;
        }

        if (this.brightness < 0f) {
            this.brightness = 0;
        }

        if (!hasSprite) {
            int index = (int) (this.brightness / 100 * (SPRITES.length - 1));
            if (index < 0) {
                index = 0;
            }
            if (index >= SPRITES.length) {
                index = SPRITES.length - 1;
            }
            this.sprite = SPRITES[index];
        }
        return this.sprite;
    }
}

