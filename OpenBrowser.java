import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenBrowser {
    public static void main(String[] args) {
        String urlToOpen = "https://www.google.com"; // Replace with the URL you want to open

        openBrowser(urlToOpen);
    }

    public static void openBrowser(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Desktop browsing is not supported.");
            }
        } else {
            System.out.println("Desktop is not supported.");
        }
    }
}
