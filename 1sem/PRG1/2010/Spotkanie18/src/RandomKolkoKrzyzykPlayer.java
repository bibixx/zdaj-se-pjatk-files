public class RandomKolkoKrzyzykPlayer extends KolkoKrzyzykPlayer {
    private int rozmiar;
    public RandomKolkoKrzyzykPlayer(String name, int rozmiar) {
        super(name);
        this.rozmiar=rozmiar;
    }
    public RandomKolkoKrzyzykPlayer(String name) {
        super(name);
    }
    public java.awt.Point getPoint() {
        java.util.Random random = new java.util.Random();

        int x = random.nextInt(rozmiar);
        int y = random.nextInt(rozmiar);

        return new java.awt.Point(x, y);
    }
    /*public void setWymiar(int rozmiar){
        if (rozmiar > 2) {
            this.rozmiar = rozmiar;
        }//else throw new zlyWymiar();
    }*/
} 