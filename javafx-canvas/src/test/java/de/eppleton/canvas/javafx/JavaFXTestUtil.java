/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eppleton.canvas.javafx;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 *
 * @author antonepple
 */
public class JavaFXTestUtil {

    public static synchronized void runOnEventQueue(final Object instance, final String method) throws Exception {
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                Method method1 = instance.getClass().getMethod(method, new Class[0]);
                method1.invoke(instance);
                return null;
            }
        };
        Platform.runLater(task);
        task.get();
    }

    public static boolean checkColor(Image image, int fromX, int fromY, int toX, int toY, int argb){
        PixelReader pixelReader = image.getPixelReader();
        for (int i = fromX; i < toX; i++) {
            for (int j = fromY; j < toY; j++) {
                if (pixelReader.getArgb(i, j) != argb) return false;         
            }     
        }
        return true;
    }
    
    public static Image snapShot(Node node) throws Exception {

        WritableImage snapshot = node.snapshot(new SnapshotParameters(), null);
        return snapshot;
    }

    public static void storeImage(String name, Image snapshot) {
        File file = new File(name + ".png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
        } catch (IOException e) {
            // TODO: handle exception here
        }
    }

    public static boolean isSameImage(Image image, Image image2) {
        if (image.getWidth() != image2.getWidth() || image.getHeight() != image2.getHeight()) {
            return false;
        }
        PixelReader pixelReader = image.getPixelReader();
        PixelReader pixelReader1 = image2.getPixelReader();
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (pixelReader.getArgb(i, j) != pixelReader1.getArgb(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

//    boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
//    if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
//        for (int x = 0; x < img1.getWidth(); x++) {
//            for (int y = 0; y < img1.getHeight(); y++) {
//                if (img1.getRGB(x, y) != img2.getRGB(x, y))
//                    return false;
//            }
//        }
//    } else {
//        return false;
//    }
//    return true;
//}
}
