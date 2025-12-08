public class Point {
    public char symbol;
    static char[] SPRITES = {' ','.','-','#','@'};
    public float brightness;

    {
        this.brightness = 50f;
    }

    public char render() {
        this.symbol = SPRITES[(int)(this.brightness / 100 * (SPRITES.length - 1))];
        return this.symbol;
    }
}