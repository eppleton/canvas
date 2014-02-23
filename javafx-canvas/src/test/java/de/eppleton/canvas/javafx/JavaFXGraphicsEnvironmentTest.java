/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eppleton.canvas.javafx;

import static de.eppleton.canvas.javafx.JavaFXTestUtil.checkColor;
import static de.eppleton.canvas.javafx.JavaFXTestUtil.isSameImage;
import static de.eppleton.canvas.javafx.JavaFXTestUtil.snapShot;
import static de.eppleton.canvas.javafx.JavaFXTestUtil.storeImage;
import java.awt.Color;
import java.util.concurrent.ExecutionException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import net.java.html.canvas.GraphicsContext2D;
import net.java.html.canvas.Style;
import net.java.html.canvas.spi.GraphicsUtils;
import org.testng.Assert;
import static org.testng.Assert.fail;
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
        canvas.setHeight(100);
        canvas.setWidth(100);
        stackPane = new StackPane(canvas);
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                Scene scene = new Scene(stackPane, 100, 100);
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

    @Test(expectedExceptions = ExecutionException.class)
    public void testArc() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testArcImpl");
    }

    public void testArcImpl() {
        graphicsContext.arc(10, 10, 1.5, 5, 90, true);
    }

    /**
     * Test of arcTo method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testArcTo() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testArcToImpl");
    }

    public void testArcToImpl() throws Exception {
        graphicsContext.setFillStyle(new Style.Color("#000000"));
//        graphicsContext.fillRect(0, 0, 100,100);
        graphicsContext.beginPath();
        graphicsContext.moveTo(1, 1);           // Create a starting point
        graphicsContext.lineTo(10, 1);          // Create a horizontal line
        graphicsContext.arcTo(15, 1, 15, 20, 5); // Create an arc
        graphicsContext.lineTo(15, 100);
        graphicsContext.stroke();
//        storeImage( "testArcTo", snapShot(canvas));
    }

    /**
     * Test of beginPath method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testBeginPath() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testBeginPathImpl");
    }

    public void testBeginPathImpl() throws Exception {
        graphicsContext.setFillStyle(new Style.Color("#000000"));
        // creating two lines, clearing gc after first line is stroked
        graphicsContext.beginPath();
        graphicsContext.moveTo(1, 1);           // Create a starting point
        graphicsContext.lineTo(10, 1);
        graphicsContext.stroke();
        graphicsContext.clearRect(0, 0, 100, 100);
        graphicsContext.beginPath();
        graphicsContext.moveTo(10, 1);           // Create a starting point
        graphicsContext.lineTo(10, 10);
        graphicsContext.stroke();
        Image snapShot = snapShot(canvas);
        // this time we don't begin a new path. 
        // clear should have no effect
        graphicsContext.setFillStyle(new Style.Color("#000000"));
        graphicsContext.beginPath();
        graphicsContext.moveTo(1, 1);           // Create a starting point
        graphicsContext.lineTo(10, 1);
        graphicsContext.clearRect(0, 0, 100, 100);
        graphicsContext.moveTo(10, 1);           // Create a starting point
        graphicsContext.lineTo(10, 10);
        graphicsContext.stroke();
        Image snapShot1 = snapShot(canvas);
        boolean sameImage = isSameImage(snapShot, snapShot1);
        if (sameImage) {
            storeImage("testBeginPath", snapShot);
            storeImage("testBeginPath1", snapShot1);
            Assert.fail("images are the same and shouldn't be");
        }
        graphicsContext.stroke();
    }

    /**
     * Test of bezierCurveTo method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testBezierCurveTo() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testBezierCurveToImpl");
    }

    public void testBezierCurveToImpl() throws Exception {
        graphicsContext.beginPath();
        graphicsContext.moveTo(20, 20);
        graphicsContext.bezierCurveTo(20, 100, 100, 100, 100, 20);
        graphicsContext.stroke();
//        storeImage("testBezierCurveTo", snapShot(canvas));
    }

    /**
     * Test of clearRect method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testClearRect() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testClearRectImpl");
    }

    public void testClearRectImpl() throws Exception {
        graphicsContext.fillRect(0, 0, 100, 100);
        Image snapShot = snapShot(canvas);
        graphicsContext.clearRect(0, 0, 100, 100);
        Image snapShot1 = snapShot(canvas);
        boolean sameImage = isSameImage(snapShot, snapShot1);
        if (sameImage) {
            storeImage("testClearRect", snapShot);
            storeImage("testClearRect1", snapShot1);
            Assert.fail("images are the same and shouldn't be");
        }
        boolean checkColor = checkColor(snapShot1, 0, 0, 100, 100, Color.WHITE.getRGB());
        if (!checkColor) {
            storeImage("testClearRect2", snapShot1);
            fail("this isn't cleared");
        }
    }

    /**
     * Test of clip method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testClip() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testClipImpl");
    }

    public void testClipImpl() throws Exception {
        // Clip a rectangular area
        graphicsContext.beginPath();
        graphicsContext.rect(20, 20, 60, 60);
//        graphicsContext.stroke();
        graphicsContext.clip();
// Draw red rectangle after clip()
        graphicsContext.setFillStyle(new Style.Color("#ff0000"));
        graphicsContext.fillRect(0, 0, 100, 100);
        Image snapShot = snapShot(canvas);
        boolean checkColor = checkColor(snapShot, 0, 0, 20, 100, Color.WHITE.getRGB());
        if (!checkColor) {
            storeImage("testClip", snapShot);
            fail("Area outside clip should be white");
        }
        boolean checkColor1 = checkColor(snapShot, 20, 20, 80, 80, Color.RED.getRGB());
        if (!checkColor1) {
            storeImage("testClip1", snapShot);
            fail("Area inside clip should be red");
        }
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
    public void testFillRect() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testFillRectImpl");
    }

    public void testFillRectImpl() throws Exception {
        graphicsContext.setFillStyle(new Style.Color("#ff00ff67"));
        graphicsContext.fillRect(10, 10, 15, 15);
        Image snapShot = JavaFXTestUtil.snapShot(stackPane);
        boolean sameImage = JavaFXTestUtil.isSameImage(snapShot, snapShot);
        System.out.println("is same " + sameImage);
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
    public void testGetOrCreateCanvas() throws Exception {
//        JavaFXTestUtil.runOnEventQueue(this, "snapShot");
    }

}
