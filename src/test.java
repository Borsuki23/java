public class test {
    public static void main(String[] args) {
        drawTriangle1();
    }

    public static void drawTriangle1() {
        for (int y = 1; y <= 10; y++) {
            for (int x = 1; x <= 10; x++) {
                if (x == 10 || x + y == 10 && x >= 5 || x >= 5 && x == y) {
                    System.out.print(" * ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
    }


