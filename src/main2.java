public class main2 {
    public static void main(String[] args) {
        drawTriangle3();
    }

    public static void drawTriangle3() {
        for (int y = 1; y <= 10; y++) {
            for (int x = 1; x <= 10; x++) {
                if (x == 1 || x + y == 11 && x <= 5 || x <= 5 && x == y) {
                    System.out.print(" * ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
}



