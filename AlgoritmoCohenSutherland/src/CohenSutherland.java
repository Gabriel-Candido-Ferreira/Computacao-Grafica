public class CohenSutherland {
    DrawRect drawRect;
    public CohenSutherland(DrawRect drawRect) {
        this.drawRect = drawRect;

    }

    private int getPointCode(Pontos pontos) {
        int pointCode = 0; //0000

        if (pontos.getX() < drawRect.getX1()) {
            pointCode += 1; //0001
        }
        if (pontos.getX() > drawRect.getX2()) {
            pointCode += 2; //0010
        }
        if (pontos.getY() < drawRect.getY1()) {
            pointCode += 8; //1000
        }
        if (pontos.getY() > drawRect.getY2()) {
            pointCode += 4; //0100
        }
        return pointCode;
    }
    public Retas findSubLine(Retas retas) {
        Retas result = new Retas(retas.getPoint1(), retas.getPoint2());
        Pontos pontos;
        Pontos pontos1 = retas.getPoint1();
        Pontos pontos2 = retas.getPoint2();
        int code1 = getPointCode(pontos1);
        int code2 = getPointCode(pontos2);
        int code;
        while ((code1 | code2) != 0) {
            if (code1 == 0 && code2 == 0) {
                return result; 
            }

            if (code1 != 0) {
                pontos = new Pontos(pontos1.getX(), pontos1.getY());
                code = code1;
            } else {
                pontos = new Pontos(pontos2.getX(), pontos2.getY());
                code = code2;
            }

            if (pontos.getX() < drawRect.getX1()) {
                pontos.setY(pontos.getY() + (pontos1.getY() - pontos2.getY()) * (drawRect.getX1() - pontos.getX())
                        / (pontos1.getX() - pontos2.getX()));
                pontos.setX(drawRect.getX1());
            }
            if (pontos.getX() > drawRect.getX2()) {
                pontos.setY( pontos.getY() +(pontos1.getY() - pontos2.getY()) * (drawRect.getX2() - pontos.getX())
                        / (pontos1.getX() - pontos2.getX()));
                pontos.setX(drawRect.getX2());
            }
            if (pontos.getY() < drawRect.getY1()) {
                pontos.setX( pontos.getX() + (pontos1.getX() - pontos2.getX()) * (drawRect.getY1() - pontos.getY())
                        / (pontos1.getY() - pontos2.getY()));
                pontos.setY(drawRect.getY1());

            }
            if (pontos.getY() > drawRect.getY2()) {
                pontos.setX(pontos.getX() +(pontos1.getX() - pontos2.getX()) * (drawRect.getY2() - pontos.getY())
                        / (pontos1.getY() - pontos2.getY()));
                pontos.setY(drawRect.getY2());
            }

            if (code == code1) {
                result.setPoint1(pontos);
                code1 = 0;getPointCode(result.getPoint1());
            } else {
                result.setPoint2(pontos);
                code2 = 0;getPointCode(result.getPoint2());
            }
        }

        return result;
    }
    public int getClassLine(Retas retas) {
        int lineClass = 0;
        int firstPointCode = getPointCode(retas.getPoint1());
        int secondPointCode = getPointCode(retas.getPoint2());

        if (firstPointCode == 0 && secondPointCode == 0) {
            lineClass = 0;
        } else if ((firstPointCode & secondPointCode) == 0) {
            lineClass = 1;
        } else {
            lineClass = 2;
        }
        return lineClass;
    }
}