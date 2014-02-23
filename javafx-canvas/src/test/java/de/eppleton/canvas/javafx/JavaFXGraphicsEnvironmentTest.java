/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eppleton.canvas.javafx;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import net.java.html.canvas.GraphicsContext2D;
import net.java.html.canvas.spi.GraphicsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author antonepple
 */
public class JavaFXGraphicsEnvironmentTest {

    public JavaFXGraphicsEnvironmentTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        new JFXPanel(); // initialize Platform
        javaFXGraphicsEnvironment = new JavaFXGraphicsEnvironment();
        graphicsContext = GraphicsUtils.getOrCreate(javaFXGraphicsEnvironment, "test");
        canvas = javaFXGraphicsEnvironment.getOrCreateCanvas("test");
        canvas.setHeight(20);
        canvas.setWidth(20);
        stackPane = new StackPane(canvas);
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                Scene scene = new Scene(stackPane);
                return null;
            }
        };
        Platform.runLater(task);
        task.get();
    }
    private static StackPane stackPane;
    private static Canvas canvas;
    private static JavaFXGraphicsEnvironment javaFXGraphicsEnvironment;
    private static GraphicsContext2D graphicsContext;

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "clear");
    }

    public void clear() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(enabled = false)
    public void arc() {
        graphicsContext.arc(100, 100, 1, 50, 34, true);
    }

    /**
     * Test of arc method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testArc() {
    }

    /**
     * Test of arcTo method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testArcTo() {
    }

    /**
     * Test of beginPath method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testBeginPath() {
    }

    /**
     * Test of bezierCurveTo method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testBezierCurveTo() {
    }

    /**
     * Test of clearRect method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testClearRect() {
    }

    /**
     * Test of clip method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testClip() {
    }

    /**
     * Test of closePath method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testClosePath() {
    }

    /**
     * Test of fill method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testFill() {
    }

    /**
     * Test of fillRect method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testFillRect() {
        graphicsContext.fillRect(10, 10, 15, 15);

    }

    /**
     * Test of fillText method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testFillText_4args() {
    }

    /**
     * Test of fillText method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testFillText_5args() {
    }

    /**
     * Test of getFont method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetFont() {
    }

    /**
     * Test of getGlobalAlpha method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetGlobalAlpha() {
    }

    /**
     * Test of getGlobalCompositeOperation method, of class
     * JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetGlobalCompositeOperation() {
    }

    /**
     * Test of getLineCap method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetLineCap() {
    }

    /**
     * Test of getLineJoin method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetLineJoin() {
    }

    /**
     * Test of getLineWidth method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetLineWidth() {
    }

    /**
     * Test of getMiterLimit method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetMiterLimit() {
    }

    /**
     * Test of getTextAlign method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetTextAlign() {
    }

    /**
     * Test of getTextBaseline method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetTextBaseline() {
    }

    /**
     * Test of isPointInPath method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testIsPointInPath() {
    }

    /**
     * Test of lineTo method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testLineTo() {
    }

    /**
     * Test of moveTo method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testMoveTo() {
    }

    /**
     * Test of quadraticCurveTo method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testQuadraticCurveTo() {
    }

    /**
     * Test of rect method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testRect() {
    }

    /**
     * Test of restore method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testRestore() {
    }

    /**
     * Test of rotate method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testRotate() {
    }

    /**
     * Test of save method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSave() {
    }

    /**
     * Test of scale method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testScale() {
    }

    /**
     * Test of setFont method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetFont() {
    }

    /**
     * Test of setGlobalAlpha method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetGlobalAlpha() {
    }

    /**
     * Test of setGlobalCompositeOperation method, of class
     * JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetGlobalCompositeOperation() {
    }

    /**
     * Test of setLineCap method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetLineCap() {
    }

    /**
     * Test of setLineJoin method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetLineJoin() {
    }

    /**
     * Test of setLineWidth method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetLineWidth() {
    }

    /**
     * Test of setMiterLimit method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetMiterLimit() {
    }

    /**
     * Test of setTextAlign method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetTextAlign() {
    }

    /**
     * Test of setTextBaseline method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetTextBaseline() {
    }

    /**
     * Test of setTransform method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetTransform() {
    }

    /**
     * Test of stroke method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testStroke() {
    }

    /**
     * Test of strokeRect method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testStrokeRect() {
    }

    /**
     * Test of strokeText method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testStrokeText_4args() {
    }

    /**
     * Test of strokeText method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testStrokeText_5args() {
    }

    /**
     * Test of transform method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testTransform() {
    }

    /**
     * Test of translate method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testTranslate() {
    }

    /**
     * Test of drawImage method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testDrawImage_5args() {
    }

    /**
     * Test of drawImage method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testDrawImage_7args() {
    }

    /**
     * Test of drawImage method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testDrawImage_11args() {
    }

    /**
     * Test of setFillStyle method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetFillStyle() {
    }

    /**
     * Test of setStrokeStyle method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetStrokeStyle() {
    }

    /**
     * Test of createPixelMap method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testCreatePixelMap_3args() {
    }

    /**
     * Test of createPixelMap method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testCreatePixelMap_Canvas_ImageData() {
    }

    /**
     * Test of getPixelMap method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetPixelMap() {
    }

    /**
     * Test of putPixelMap method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testPutPixelMap_4args() {
    }

    /**
     * Test of putPixelMap method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testPutPixelMap_8args() {
    }

    /**
     * Test of getHeight method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetHeight_Canvas() {
    }

    /**
     * Test of getWidth method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetWidth_Canvas() {
    }

    /**
     * Test of setHeight method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetHeight() {
    }

    /**
     * Test of setWidth method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testSetWidth() {
    }

    /**
     * Test of measureText method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testMeasureText() {
    }

    /**
     * Test of getWidth method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetWidth_3args() {
    }

    /**
     * Test of getHeight method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetHeight_3args() {
    }

    /**
     * Test of mergeImages method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testMergeImages() {
    }

    /**
     * Test of getOrCreateCanvas method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetOrCreateCanvas() throws Exception  {
            JavaFXTestUtil.runOnEventQueue(this, "snapShot");
    }

    public void snapShot() throws Exception {

        WritableImage snapshot = stackPane.snapshot(new SnapshotParameters(), null);
        PixelReader pixelReader = snapshot.getPixelReader();
        for (int i = 0; i < snapshot.getWidth(); i++) {
            for (int j = 0; j < snapshot.getHeight(); j++) {
                System.out.print("" + pixelReader.getColor(i, j).getRed() + ",");
            }
            System.out.println("");
        }
        


    }

}
