#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import de.eppleton.canvas.html.HTML5Graphics;
import net.java.html.boot.BrowserBuilder;
import net.java.html.canvas.GraphicsContext2D;

public final class Main {
    private Main() {
    }
    
    public static void main(String... args) throws Exception {
        BrowserBuilder.newBrowser().
            loadPage("pages/index.html").
            loadClass(Main.class).
            invoke("onPageLoad", args).
            showAndWait();
        System.exit(0);
    }

    /**
     * Called when the page is ready.
     */
    public static void onPageLoad(String... args) throws Exception {
        GraphicsContext2D gc = HTML5Graphics.getOrCreate("canvas");
        gc.fillRect(0, 0,10, 10);
    }
    
}
