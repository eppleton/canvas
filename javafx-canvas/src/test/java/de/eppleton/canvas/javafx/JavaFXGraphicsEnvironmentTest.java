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
import net.java.html.canvas.Dimension;
import net.java.html.canvas.GraphicsContext2D;
import net.java.html.canvas.ImageData;
import net.java.html.canvas.Style;
import net.java.html.canvas.spi.GraphicsUtils;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
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

    @Test
    public void testClip() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testClipImpl");
    }

    public void testClipImpl() throws Exception {
        // need to save and restore the graphicscontext, 
        // otherwise the rest of the tests will fail because of the clip
        graphicsContext.save();
        // Clip a rectangular area
        graphicsContext.beginPath();
        graphicsContext.rect(20, 20, 60, 60);
        graphicsContext.clip();
        // Draw red rectangle after clip() only the parts inside clip should be red
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
        graphicsContext.beginPath();
        graphicsContext.rect(0, 0, 100, 100);
        graphicsContext.restore();
    }

    @Test
    public void testClosePath() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testClosePathImpl");
    }

    public void testClosePathImpl() throws Exception {
        graphicsContext.clearRect(0, 0, 100, 100);
        graphicsContext.beginPath();
        graphicsContext.moveTo(10, 10);
        graphicsContext.lineTo(20, 10);
        graphicsContext.lineTo(20, 20);
        graphicsContext.lineTo(10, 20);
        graphicsContext.stroke();
        Image snapShot = snapShot(canvas);
        graphicsContext.clearRect(0, 0, 100, 100);
        graphicsContext.closePath();
        graphicsContext.stroke();
        Image snapShot1 = snapShot(canvas);
        if (isSameImage(snapShot, snapShot1)) {
            storeImage("testClosePath", snapShot);
            storeImage("testClosePath1", snapShot1);
            fail("image with closed path should be different");
        }
        boolean checkColor = checkColor(snapShot1, 10, 10, 10, 20, Color.BLACK.getRGB());
        if (!checkColor) {
            storeImage("testClosePath2", snapShot1);
            fail("closed path should have a line between 10,10 and 10,20");
        }
        boolean checkColor2 = checkColor(snapShot, 10, 10, 10, 20, Color.BLACK.getRGB());
        if (!checkColor2) {
            storeImage("testClosePath3", snapShot);
            fail("unclosed path shouldn't have a line between 10,10 and 10,20");
        }
    }

    @Test
    public void testFill() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testFillImpl");
    }

    public void testFillImpl() throws Exception {
        graphicsContext.clearRect(0, 0, 100, 100);
        graphicsContext.beginPath();
        graphicsContext.moveTo(10, 10);
        graphicsContext.lineTo(20, 10);
        graphicsContext.lineTo(20, 20);
        graphicsContext.lineTo(10, 20);
        graphicsContext.closePath();
        graphicsContext.stroke();
        Image snapShot = snapShot(canvas);
        graphicsContext.setFillStyle(new Style.Color("#00ff00"));
        graphicsContext.fill();
        Image snapShot1 = snapShot(canvas);
        if (isSameImage(snapShot, snapShot1)) {
            storeImage("testFill", snapShot);
            storeImage("testFill1", snapShot1);
            fail("filled image should be different");
        }
        boolean checkColor = checkColor(snapShot1, 10, 10, 20, 20, Color.GREEN.getRGB());
        if (!checkColor) {
            storeImage("testFill2", snapShot1);
            fail("filled image should be green between 10,10 and 20,20");
        }
    }

    @Test
    public void testFillRect() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testFillRectImpl");
    }

    public void testFillRectImpl() throws Exception {
        graphicsContext.setFillStyle(new Style.Color("#0000ff"));

        graphicsContext.fillRect(10, 10, 15, 15);
        Image snapShot = snapShot(canvas);
        boolean checkColor = checkColor(snapShot, 10, 10, 15, 15, Color.BLUE.getRGB());
        if (!checkColor) {
            storeImage("testFillRect", snapShot);
            fail("area from 10,10 to 15,15 should be blue");
        }
        checkColor = checkColor(snapShot, 0, 0, 10, 10, Color.WHITE.getRGB());
        if (!checkColor) {
            storeImage("testFillRect1", snapShot);
            fail("area outside 10,10 to 15,15 should be white");
        }
    }

    @Test
    public void testFillText_4args() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testFillText_4argsImpl");
    }

    public void testFillText_4argsImpl() throws Exception {
        graphicsContext.clearRect(0, 0, 100, 100);
        graphicsContext.setFillStyle(new Style.Color("#0000ff"));
        graphicsContext.fillText("Hallo Welt", 10, 10);
    }

    @Test
    public void testFillText_5args() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testFillText_5argsImpl");
    }

    public void testFillText_5argsImpl() throws Exception {
        graphicsContext.clearRect(0, 0, 100, 100);
        graphicsContext.setFillStyle(new Style.Color("#0000ff"));
        graphicsContext.fillText("Hallo Welt", 10, 10);
        Image snapShot = snapShot(canvas);
        // a test to check if 5 args is different
        graphicsContext.clearRect(0, 0, 100, 100);
        graphicsContext.setFillStyle(new Style.Color("#0000ff"));
        graphicsContext.fillText("Hallo Welt", 10, 10, 20);
        Image snapShot1 = snapShot(canvas);
        if (isSameImage(snapShot, snapShot1)) {
            storeImage("testFillText_5args1", snapShot);
            storeImage("testFillText_5args2", snapShot1);
            fail("5 args filled text should be compressed compared to 4 args method");
        }
    }

    /**
     * Test of getFont method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetFont() {
        String font = graphicsContext.getFont();
        assert (font != null & !font.isEmpty());
    }

    /**
     * Test of getGlobalAlpha method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetGlobalAlpha() {
        graphicsContext.setGlobalAlpha(.5);
        double globalAlpha = graphicsContext.getGlobalAlpha();
        if (globalAlpha != .5) {
            fail("GlobalAlpha should be .5 but is " + globalAlpha);
        }
        graphicsContext.setGlobalAlpha(1.0);

    }

    /**
     * Test of getGlobalCompositeOperation method, of class
     * JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetGlobalCompositeOperation() {
        String globalCompositeOperation = graphicsContext.getGlobalCompositeOperation();
        if (globalCompositeOperation == null || globalCompositeOperation.isEmpty()) {
            fail("globalCompositeOperation shouldn't be empty " + globalCompositeOperation);
        }
    }

    /**
     * Test of getLineCap method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetLineCap() {
        graphicsContext.setLineCap("ROUND");
        String lineCap = graphicsContext.getLineCap();
        if (lineCap == null || lineCap.isEmpty() || !lineCap.toLowerCase().equals("round")) {
            fail("lineCap shouldbe 'round', but is " + lineCap);
        }
        graphicsContext.setLineCap("square");
    }

    /**
     * Test of getLineJoin method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetLineJoin() {
        String orig = graphicsContext.getLineJoin();
        graphicsContext.setLineJoin("ROUND");
        String lineJoin = graphicsContext.getLineJoin();
        if (lineJoin == null || lineJoin.isEmpty() || !lineJoin.toLowerCase().equals("round")) {
            fail("lineJoin shouldbe 'round', but is " + lineJoin);
        }
        graphicsContext.setLineJoin(orig);
    }

    /**
     * Test of getLineWidth method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetLineWidth() {
        double lw = 20;
        graphicsContext.setLineWidth(lw);
        double lineWidth = graphicsContext.getLineWidth();
        assertEquals(lineWidth, lw);
    }

    /**
     * Test of getMiterLimit method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetMiterLimit() {
        double ml = 20;
        graphicsContext.setMiterLimit(ml);
        double mlimit = graphicsContext.getMiterLimit();
        assertEquals(mlimit, ml);
    }

    /**
     * Test of getTextAlign method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetTextAlign() {
        graphicsContext.setTextAlign("left");
        String textAlign = graphicsContext.getTextAlign();
        assertEquals(textAlign, "left");
    }

    /**
     * Test of getTextBaseline method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetTextBaseline() {
        graphicsContext.setTextBaseline("top");
        String textBaseline = graphicsContext.getTextBaseline();
        assertEquals(textBaseline, "top");
    }

    @Test
    public void testIsPointInPath() throws Exception {
        graphicsContext.clearRect(0, 0, 100, 100);
        graphicsContext.beginPath();
        graphicsContext.rect(10, 10, 20, 20);
        boolean pointInPath = graphicsContext.isPointInPath(10, 20);
        if (!pointInPath) {
            storeImage("testIsPointInPath", snapShot(canvas));
            fail("10, 20 should be in path");
        }
        boolean pointNotInPath = graphicsContext.isPointInPath(5, 20);
        if (pointNotInPath) {
            storeImage("testIsPointInPath1", snapShot(canvas));
            fail("5,20 should not be in path");
        }
    }

    @Test
    public void testLineTo() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testLineToImpl");
    }

    public void testLineToImpl() throws Exception {
        graphicsContext.clearRect(0, 0, 100, 100);
        graphicsContext.setStrokeStyle(new Style.Color("#000000"));
        graphicsContext.setLineWidth(5);
        graphicsContext.beginPath();
        graphicsContext.moveTo(10, 10);
        graphicsContext.lineTo(20, 10);
        graphicsContext.stroke();
        Image snapShot = snapShot(canvas);
        boolean checkColor = checkColor(snapShot, 9, 9, 20, 11, Color.BLACK.getRGB());
        if (!checkColor) {
            storeImage("testLineTo", snapShot);
            fail("that area should be black");
        }
    }

    /**
     * Test of moveTo method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testMoveTo() {
        graphicsContext.clearRect(0, 0, 100, 100);
        graphicsContext.beginPath();
        graphicsContext.moveTo(10, 10);
        // nothing to really test here. This is tested in various other tests
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
        ImageData pm = graphicsContext.createPixelMap(10, 10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                pm.setB(i, j, 10);
                pm.setR(i, j, 20);
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if( pm.getB(i, j) != 10) fail("B should be 10, but is "+pm.getB(i, j)+" index "+i+", "+j);
                if( pm.getR(i, j) != 20) fail("B should be 20, but is "+pm.getR(i, j)+" index "+i+", "+j);
            }
        }
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
    @Test(enabled = false)
    public void testPutPixelMap_4args() {

    }

    /**
     * Test of getHeight method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetHeight_Canvas() {
        int height = graphicsContext.getHeight();
        assertEquals(100, height);
    }

    /**
     * Test of getWidth method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetWidth_Canvas() {
        int width = graphicsContext.getWidth();
        assertEquals(100, width);
    }

    /**
     * Test of measureText method, of class JavaFXGraphicsEnvironment.
     */
    @Test(enabled = false)
    public void testMeasureText() {
        Dimension measureText = graphicsContext.measureText("Hallo Welt!");
    }

    /**
     * Test of getHeight method, of class JavaFXGraphicsEnvironment.
     */
    @Test
    public void testGetDimension() {
        Dimension dimension = graphicsContext.getDimension(net.java.html.canvas.Image.create("image1.png"));
        assertEquals(100d, dimension.getHeight());
        assertEquals(100d, dimension.getWidth());

    }

    @Test
    public void testMergeImages() throws Exception {
        JavaFXTestUtil.runOnEventQueue(this, "testMergeImagesImpl");
    }

    public void testMergeImagesImpl() throws Exception {

        net.java.html.canvas.Image image1 = net.java.html.canvas.Image.create("image1.png");
        net.java.html.canvas.Image image2 = net.java.html.canvas.Image.create("image2.png");
        net.java.html.canvas.Image merged = graphicsContext.merge(image2, image1);
        graphicsContext.drawImage(merged, 0, 0);
        Image image = snapShot(canvas);
        boolean checkColor = checkColor(image, 0, 0, 49, 49, Color.RED.getRGB());
        boolean checkColor1 = checkColor(image, 50, 50, 50, 50, Color.BLUE.getRGB());
        if (!checkColor || !checkColor1) {

            storeImage("testMergeImages", image);
            fail("Image not merged correctly");
        }
//        
    }

//    @Test No test required setup is test enough
//    public void testGetOrCreateCanvas() throws Exception {
//        
//    }
}
