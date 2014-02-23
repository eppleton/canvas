/**
 * Canvas API Copyright (C) 2013 AntonEpple <toni.epple@eppleton.de>
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. Look for COPYING file in the top folder. If not, see
 * http://opensource.org/licenses/GPL-2.0.
 */
package de.eppleton.canvas.javafx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import net.java.html.canvas.Dimension;
import net.java.html.canvas.Image;
import net.java.html.canvas.ImageData;
import net.java.html.canvas.Style;
import net.java.html.canvas.spi.GraphicsEnvironment;
//import org.openide.util.lookup.ServiceProviders;
//import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author antonepple
 */
//@ServiceProviders( value = {
//    @ServiceProvider(service = GraphicsEnvironment.class),
//    @ServiceProvider(service = CanvasProvider.class)})
public class JavaFXGraphicsEnvironment implements GraphicsEnvironment<Canvas> {

    @Override
    public void arc(Canvas canvas, double centerX, double centerY, double startAngle, double radius, double endAngle, boolean ccw) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void arcTo(Canvas canvas, double x1, double y1, double x2, double y2, double r) {
        canvas.getGraphicsContext2D().arcTo(x1, y1, x2, y2, r);
    }

    @Override
    public void beginPath(Canvas canvas) {
        canvas.getGraphicsContext2D().beginPath();
    }

    @Override
    public void bezierCurveTo(Canvas canvas, double cp1x, double cp1y, double cp2x, double cp2y, double x, double y) {
        canvas.getGraphicsContext2D().bezierCurveTo(cp1x, cp1y, cp2x, cp2y, x, y);
    }

    @Override
    public void clearRect(Canvas canvas, double x, double y, double width, double height) {
        canvas.getGraphicsContext2D().clearRect(x, y, width, height);
    }

    @Override
    public void clip(Canvas canvas) {
        canvas.getGraphicsContext2D().clip();
    }

    @Override
    public void closePath(Canvas canvas) {
        canvas.getGraphicsContext2D().closePath();
    }

    @Override
    public void fill(Canvas canvas) {
        canvas.getGraphicsContext2D().fill();
    }

    @Override
    public void fillRect(Canvas canvas, double x, double y, double width, double height) {
        canvas.getGraphicsContext2D().fillRect(x, y, width, height);
    }

    @Override
    public void fillText(Canvas canvas, String text, double x, double y) {
        canvas.getGraphicsContext2D().fillText(text, x, y);
    }

    @Override
    public void fillText(Canvas canvas, String text, double x, double y, double maxWidth) {
        canvas.getGraphicsContext2D().fillText(text, x, y, maxWidth);
    }

    @Override
    public String getFont(Canvas canvas) {
        return canvas.getGraphicsContext2D().getFont().toString();
    }

    @Override
    public double getGlobalAlpha(Canvas canvas) {
        return canvas.getGraphicsContext2D().getGlobalAlpha();
    }

    @Override
    public String getGlobalCompositeOperation(Canvas canvas) {
        return canvas.getGraphicsContext2D().getGlobalBlendMode().name();
    }

    @Override
    public String getLineCap(Canvas canvas) {
        return canvas.getGraphicsContext2D().getLineCap().name().toLowerCase();
    }

    @Override
    public String getLineJoin(Canvas canvas) {
        return canvas.getGraphicsContext2D().getLineJoin().name();
    }

    @Override
    public double getLineWidth(Canvas canvas) {
        return canvas.getGraphicsContext2D().getLineWidth();
    }

    @Override
    public double getMiterLimit(Canvas canvas) {
        return canvas.getGraphicsContext2D().getMiterLimit();
    }

    @Override
    public String getTextAlign(Canvas canvas) {
        return canvas.getGraphicsContext2D().getTextAlign().name();
    }

    @Override
    public String getTextBaseline(Canvas canvas) {
        return canvas.getGraphicsContext2D().getTextBaseline().name();
    }

    @Override
    public boolean isPointInPath(Canvas canvas, double x, double y) {
        return canvas.getGraphicsContext2D().isPointInPath(x, y);
    }

    @Override
    public void lineTo(Canvas canvas, double x, double y) {
        canvas.getGraphicsContext2D().lineTo(x, y);
    }

    @Override
    public void moveTo(Canvas canvas, double x, double y) {
        canvas.getGraphicsContext2D().moveTo(x, y);
    }

    @Override
    public void quadraticCurveTo(Canvas canvas, double cpx, double cpy, double x, double y) {
        canvas.getGraphicsContext2D().quadraticCurveTo(cpx, cpy, x, y);
    }

    @Override
    public void rect(Canvas canvas, double x, double y, double width, double height) {
        canvas.getGraphicsContext2D().rect(x, y, width, height);
    }

    @Override
    public void restore(Canvas canvas) {
        canvas.getGraphicsContext2D().restore();
    }

    @Override
    public void rotate(Canvas canvas, double angle) {
        canvas.getGraphicsContext2D().rotate(angle);
    }

    @Override
    public void save(Canvas canvas) {
        canvas.getGraphicsContext2D().save();
    }

    @Override
    public void scale(Canvas canvas, double x, double y) {
        canvas.getGraphicsContext2D().scale(x, y);
    }

    @Override
    public void setFont(Canvas canvas, String font) {
        canvas.getGraphicsContext2D().setFont(parseFont(canvas, font));
    }

    @Override
    public void setGlobalAlpha(Canvas canvas, double alpha) {
        canvas.getGraphicsContext2D().setGlobalAlpha(alpha);
    }

    @Override
    public void setGlobalCompositeOperation(Canvas canvas, String operation) {
        canvas.getGraphicsContext2D().setGlobalBlendMode(BlendMode.valueOf(operation));
    }

    @Override
    public void setLineCap(Canvas canvas, String style) {
        canvas.getGraphicsContext2D().setLineCap(StrokeLineCap.valueOf(style.toUpperCase()));
    }

    @Override
    public void setLineJoin(Canvas canvas, String style) {
        canvas.getGraphicsContext2D().setLineJoin(StrokeLineJoin.valueOf(style));
    }

    @Override
    public void setLineWidth(Canvas canvas, double width) {
        canvas.getGraphicsContext2D().setLineWidth(width);
    }

    @Override
    public void setMiterLimit(Canvas canvas, double limit) {
        canvas.getGraphicsContext2D().setMiterLimit(limit);
    }

    @Override
    public void setTextAlign(Canvas canvas, String textAlign) {
        canvas.getGraphicsContext2D().setTextAlign(TextAlignment.valueOf(textAlign));
    }

    @Override
    public void setTextBaseline(Canvas canvas, String textbaseline) {
        canvas.getGraphicsContext2D().setTextBaseline(VPos.valueOf(textbaseline));
    }

    @Override
    public void setTransform(Canvas canvas, double a, double b, double c, double d, double e, double f) {
        canvas.getGraphicsContext2D().setTransform(a, b, c, d, e, f);
    }

    @Override
    public void stroke(Canvas canvas) {
        canvas.getGraphicsContext2D().stroke();
    }

    @Override
    public void strokeRect(Canvas canvas, double x, double y, double width, double height) {
        canvas.getGraphicsContext2D().strokeRect(x, y, width, height);
    }

    @Override
    public void strokeText(Canvas canvas, String text, double x, double y) {
        canvas.getGraphicsContext2D().strokeText(text, x, y);
    }

    @Override
    public void strokeText(Canvas canvas, String text, double x, double y, double maxWidth) {
        canvas.getGraphicsContext2D().strokeText(text, x, y, maxWidth);
    }

    @Override
    public void transform(Canvas canvas, double a, double b, double c, double d, double e, double f) {
        canvas.getGraphicsContext2D().transform(a, b, c, d, e, f);
    }

    @Override
    public void translate(Canvas canvas, double x, double y) {
        canvas.getGraphicsContext2D().translate(x, y);
    }

    public Object drawImage(Canvas canvas, Image image, double x, double y, Object nativeImage) {
        if (nativeImage == null) {
            try {
                nativeImage = new javafx.scene.image.Image(image.getSrc());
            } catch (IllegalArgumentException iax) {
                try {
                    nativeImage = new javafx.scene.image.Image(new FileInputStream(image.getSrc()));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(JavaFXGraphicsEnvironment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        canvas.getGraphicsContext2D().drawImage((javafx.scene.image.Image) nativeImage, x, y);
        return nativeImage;
    }

    public Object drawImage(Canvas canvas, Image image, double x, double y, double width, double height, Object nativeImage) {
        if (nativeImage == null) {
            try {
                nativeImage = new javafx.scene.image.Image(image.getSrc());
            } catch (IllegalArgumentException iax) {
                try {
                    nativeImage = new javafx.scene.image.Image(new FileInputStream(image.getSrc()));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(JavaFXGraphicsEnvironment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        canvas.getGraphicsContext2D().drawImage((javafx.scene.image.Image) nativeImage, x, y, width, height);
        return nativeImage;
    }

    public Object drawImage(Canvas canvas, Image image, double sx, double sy, double sWidth, double sHeight, double x, double y, double width, double height, Object nativeImage) {
        if (nativeImage == null) {
            try {
                nativeImage = new javafx.scene.image.Image(image.getSrc());
            } catch (IllegalArgumentException iax) {
                try {
                    nativeImage = new javafx.scene.image.Image(new FileInputStream(image.getSrc()));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(JavaFXGraphicsEnvironment.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        canvas.getGraphicsContext2D().drawImage((javafx.scene.image.Image) nativeImage, sx, sy, sWidth, sHeight, x, y, width, height);
        return nativeImage;
    }

    public Object setFillStyle(Canvas canvas, Style style, Object nativeStyle) {
        if (nativeStyle == null) {
            if (style instanceof Style.LinearGradient) {
                Style.LinearGradient orig = (Style.LinearGradient) style;
                Map<Double, String> stops = orig.getStops();
            } else if (style instanceof Style.Color) {
                Style.Color originalColor = (Style.Color) style;
                nativeStyle = Color.web(originalColor.getAsString());
                canvas.getGraphicsContext2D().setFill((Paint) nativeStyle);
            } else if (style instanceof Style.Pattern) {
                Style.Pattern original = (Style.Pattern) style;
                Image imageResource = original.getImageResource();
                javafx.scene.image.Image image = new javafx.scene.image.Image(imageResource.getSrc());
                ImagePattern pattern = new ImagePattern(image, 0, 0, image.getWidth(), image.getHeight(), false);
                canvas.getGraphicsContext2D().setFill(pattern);
            }
        }

        return nativeStyle;
    }

    @Override
    public Object setStrokeStyle(Canvas canvas, Style style, Object nativeStyle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageData createPixelMap(Canvas canvas, double x, double y) {
        WritableImage writableImage = new WritableImage((int) x, (int) y);
        return new ImageDataWrapper(writableImage, x, y);
    }

    @Override
    public ImageData createPixelMap(Canvas canvas, ImageData imageData) {
        WritableImage writableImage = new WritableImage((int) imageData.getWidth(), (int) imageData.getHeight());
        return new ImageDataWrapper(writableImage, (int) imageData.getWidth(), (int) imageData.getHeight());
    }

    @Override
    public ImageData getPixelMap(Canvas canvas, double x, double y, double width, double height) {
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        snapshotParameters.setViewport(new Rectangle2D(x, y, width, height));
        WritableImage snapshot = canvas.snapshot(snapshotParameters, null);
        return new ImageDataWrapper(snapshot, width, height);
    }

    public void putPixelMap(Canvas canvas, ImageData imageData, double x, double y) {
        canvas.getGraphicsContext2D().drawImage((javafx.scene.image.Image)imageData.getImage(), x, y);
    }

//    public void putPixelMap(Canvas canvas, ImageData imageData, double x, double y, double dirtyx, double dirtyy, double dirtywidth, double dirtyheight) {
//        canvas.getGraphicsContext2D().drawImage((javafx.scene.image.Image)imageData.getImage(), x, y,y, y, y, y, y, y);
//    }

    public int getHeight(Canvas canvas) {
        return (int) canvas.getHeight();
    }

    public int getWidth(Canvas canvas) {
        return (int) canvas.getWidth();
    }

    public void setHeight(Canvas canvas, int height) {
        canvas.setHeight(height);
    }

    public void setWidth(Canvas canvas, int width) {
        canvas.setWidth(width);
    }

    public Dimension measureText(Canvas canvas, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Font parseFont(Canvas canvas, String font) {
        List<String> list = new ArrayList<String>();
        Matcher m = Pattern.compile("([^']\\S*|'.+?')\\s*").matcher(font);
        while (m.find()) {
            list.add(m.group(1)); // Add .replace("\"", "") to remove surrounding quotes.
        }
        return Font.font(list.get(list.size() - 1), Double.parseDouble(list.get(list.size() - 2)));
    }

    public int getWidth(Canvas canvas, Image image, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = drawImage(canvas, image, 0, 0, nativeImage);
        }
        return (int) ((javafx.scene.image.Image) nativeImage).getWidth();

    }

    public int getHeight(Canvas canvas, Image image, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = drawImage(canvas, image, 0, 0, nativeImage);
        }
        return (int) ((javafx.scene.image.Image) nativeImage).getHeight();
    }

    public Object mergeImages(Canvas canvas, Image a, Image b, Object cachedA, Object cachedB) {
        if (cachedA == null) {
            cachedA = drawImage(canvas, a, 0, 0, cachedA);
        }
        if (cachedB == null) {
            cachedB = drawImage(canvas, b, 0, 0, cachedB);
        }
        return ImageUtilities.merge((javafx.scene.image.Image) cachedA, (javafx.scene.image.Image) cachedB);
    }

    @Override
    public Canvas getOrCreateCanvas(String id) {
        Logger.getLogger(JavaFXGraphicsEnvironment.class.getName()).info("Creating canvas with id " + id);
        Canvas canvas = getCanvas(id);

        if (canvas == null) {
            canvas = new Canvas();
            canvas.setId(id);
            canvasList.put(canvas, id);
        }
        return canvas;
    }

    private static WeakHashMap<Canvas, String> canvasList = new WeakHashMap<Canvas, String>();

    /**
     * get the Canvas with this ID. My return null, if the Canvas doesn't exist.
     *
     * @param id
     * @return the Canvas
     */
    private Canvas getCanvas(String id) {
        Logger.getLogger(JavaFXGraphicsEnvironment.class.getName()).info("Getting canvas with id " + id);

        if (canvasList.containsValue(id)) {
            Set<Map.Entry<Canvas, String>> entrySet = canvasList.entrySet();
            for (Map.Entry<Canvas, String> entry : entrySet) {
                if (entry.getValue().equals(id)) {
                    return entry.getKey();

                }
            }
        }
        return null;
    }

}
