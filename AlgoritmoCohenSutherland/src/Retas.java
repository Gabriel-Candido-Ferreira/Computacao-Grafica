public class Retas {
    private Pontos pontos1;
    private Pontos pontos2;

    public Retas() {
        pontos1 = new Pontos();
        pontos2 = new Pontos();
    }

    public Retas(Pontos pontos1, Pontos pontos2) {
        this.pontos1 = pontos1;
        this.pontos2 = pontos2;
    }

    public Pontos getPoint1() {
        return pontos1;
    }

    public void setPoint1(Pontos pontos1) {
        this.pontos1 = pontos1;
    }
    public void setPoint1(int x, int y) {
        pontos1.setX(x);
        pontos1.setY(y);
    }

    public Pontos getPoint2() {
        return pontos2;
    }

    public void setPoint2(Pontos pontos2) {
        this.pontos2 = pontos2;
    }

    public void setPoint2(int x, int y) {
        pontos2.setX(x);
        pontos2.setY(y);
    }

    public void setPoints(int x1, int y1, int x2, int y2) {
        this.pontos1.setX(x1);
        this.pontos1.setY(y1);
        this.pontos2.setX(x2);
        this.pontos2.setY(y2);
    }
}