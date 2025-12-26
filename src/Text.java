
//интерфейс
class Text extends GameObject {
    String content;
    int width;

    Text(int x, int y, String content, int width) {
        this.x = x;
        this.y = y;
        this.content = content;
        this.width = width;
    }


    public void render() {
        Point[][] canvas = GameContext.getCanvas();
        String[] prepared = this.prepare();
        for (int y = 0; y < prepared.length; y++) {
            for (int i = 0; i < prepared[y].length(); i++) {
                try {
                    canvas[(int)this.y + y][(int)(i + this.x)].sprite = prepared[y].charAt(i);
                    canvas[(int)this.y + y][(int)(i + this.x)].hasSprite = true;
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
    }


    public String[] prepare() {
        int lineCount = (int)Math.ceil((double)this.content.length() / this.width);
        String[] withBorder = new String[lineCount + 2];
        int totalWidth = this.width + 2;

        withBorder[0] = "+" + "-".repeat(totalWidth - 2) + "+";

        for (int i = 0; i < lineCount; i++) {
            int start = i * this.width;
            int end = Math.min(start + this.width, this.content.length());
            String line = this.content.substring(start, end);
            int padding = this.width - line.length();
            String formattedLine = " ".repeat((int)Math.floor(padding / 2.0)) + line + " ".repeat((int)Math.ceil(padding / 2.0));
            withBorder[i + 1] = "|" + formattedLine + "|";
        }

        withBorder[lineCount + 1] = "+" + "-".repeat(totalWidth - 2) + "+";
        return withBorder;
    }
}