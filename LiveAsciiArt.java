public class LiveAsciiArt {
    public static void main(String[] args) {
        while (true) {
            clearConsole();
            displayAsciiArt();
            try {
                Thread.sleep(100); // Adjust the delay to control animation speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void displayAsciiArt() {
        // Replace this with your ASCII art or animation frames
        String[] frames = {
            "  /\\_/\\",
            " ( o.o )",
            "  > ^ <"
        };

        for (String frame : frames) {
            System.out.println(frame);
        }
    }
}
