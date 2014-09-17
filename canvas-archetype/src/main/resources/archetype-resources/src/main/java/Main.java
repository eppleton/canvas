package ${package};

import net.java.html.boot.BrowserBuilder;
import de.eppleton.canvas.html.HTML5Graphics;
import net.java.html.boot.BrowserBuilder;
import net.java.html.canvas.GraphicsContext2D;
#if ($nbrwsr == "true")
import org.netbeans.api.nbrwsr.OpenHTMLRegistration;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
#end

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
#if ($nbrwsr == "true")
    //
    // the following annotations generate registration for NetBeans,
    // they are harmless in other packaging schemes
    //
    
    @ActionID(
        category = "Games",
        id = "${package}.OpenPage"
    )
    @OpenHTMLRegistration(
        url="index.html",
        displayName = "Open Your Page",
        iconBase = "${package.replace('.','/')}/icon.png"
    )
    @ActionReferences({
        @ActionReference(path = "Menu/Window"),
        @ActionReference(path = "Toolbars/Games")
    })
    //
    // end of NetBeans actions registration
    //
#end

    /**
     * Called when the page is ready.
     */
    public static void onPageLoad() throws Exception {
       GraphicsContext2D gc = HTML5Graphics.getOrCreate("canvas");
       gc.fillRect(0, 0,10, 10);
    }
    
}
