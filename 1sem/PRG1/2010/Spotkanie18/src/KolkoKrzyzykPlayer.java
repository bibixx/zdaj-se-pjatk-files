public class KolkoKrzyzykPlayer {
    private final String name;

    public KolkoKrzyzykPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public java.awt.Point getPoint() {
        throw new UnsupportedOperationException("Ta operacja jest niedozwolona wewnatrz getPoint()!");
    }
} 