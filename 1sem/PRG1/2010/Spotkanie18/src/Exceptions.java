class Exceptions extends RuntimeException  {
    int x;
    int y;

    public Exceptions(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

class indexOutofArrayException extends Exceptions {
    int maxX;
    int maxY;

    public indexOutofArrayException(int x, int y, int maxX, int maxY) {
        super(x, y);
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

}

class fullArrayException extends RuntimeException {

}

class gameOverException extends
        RuntimeException {

}

class pressCancelException extends RuntimeException {

}
class zlyWymiar extends RuntimeException {

}
class wrongSize extends RuntimeException {

}

