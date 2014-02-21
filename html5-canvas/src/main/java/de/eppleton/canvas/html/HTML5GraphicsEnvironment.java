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
package de.eppleton.canvas.html;

import java.util.Map;
import java.util.Set;
import net.java.html.canvas.Dimension;
import net.java.html.canvas.GraphicsContext;
import net.java.html.canvas.Image;
import net.java.html.canvas.ImageData;
import net.java.html.canvas.Style;
import net.java.html.canvas.Style.Color;
import net.java.html.canvas.Style.LinearGradient;
import net.java.html.canvas.Style.Pattern;
import net.java.html.canvas.Style.RadialGradient;
import net.java.html.canvas.spi.GraphicsEnvironment;
import net.java.html.canvas.spi.GraphicsUtils;

import net.java.html.js.JavaScriptBody;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Anton Epple <toni.epple@eppleton.de>
 */
@ServiceProvider(service = GraphicsEnvironment.class)
public final class HTML5GraphicsEnvironment implements GraphicsEnvironment<Object> {

    // XXX: Problematic, I think
    public HTML5GraphicsEnvironment() {
// XXX:        this(100, 100, "canvas");
    }
    
    /** Factory method for now...
     * @param id element id on the page
     * @param width 
     * @param height
     * @return 
     */
    public GraphicsContext create(String id, int width, int height) {
        Object canvas = getOrCreate(id);
        setWidthImpl(canvas, width);
        setHeightImpl(canvas, height);
        return GraphicsUtils.create(this, canvas);
    }
    
    public static Object getOrCreate(String id) {
        Object canvas = getImpl(id);
        if (canvas == null) {
            canvas = createImpl(id);
        }
        return canvas;
    }

    @JavaScriptBody(args = {"id"}, body = "var canvas = document.getElementById(id);return canvas;")
    private static native Object getImpl(String id);

    @JavaScriptBody(args = {"id"}, body = "var canvas = document.createElement('canvas');"
            + "var body = document.getElementsByTagName('body')[0];body.appendChild(canvas); return canvas;")
    private static native Object createImpl(String id);

    @JavaScriptBody(args = {"c", "centerx", "centery", "radius", "startangle", "endangle", "ccw"},
            body = "c.arc(centerx,centery, radius, startangle, endangle,ccw);")
    @Override
    public native void arc(Object c, double centerX,
            double centerY,
            double startAngle,
            double radius,
            double endAngle,
            boolean ccw);

    @JavaScriptBody(args = { "c", "x1", "y1", "x2", "y2", "r"},
            body = "c.arcTo(x1,y1,x2,y2,r);")
    @Override
    public native void arcTo(Object c, double x1,
            double y1,
            double x2,
            double y2,
            double r);

    @JavaScriptBody(args = { "c", "x", "y"},
            body = "return c.isPointInPath(x,y);")
    @Override
    public native boolean isPointInPath(Object c, double x, double y);

    @JavaScriptBody(args = { "c", }, body = "c.fill();")
    @Override
    public native void fill(Object c);

    @JavaScriptBody(args = { "c", }, body = "c.stroke();")
    @Override
    public native void stroke(Object c);

    @JavaScriptBody(args = { "c", }, body = "c.beginPath();")
    @Override
    public native void beginPath(Object c);

    @JavaScriptBody(args = { "c", }, body = "c.closePath();")
    @Override
    public native void closePath(Object c);

    @JavaScriptBody(args = { "c", }, body = "c.clip();")
    @Override
    public native void clip(Object c);

    @JavaScriptBody(args = { "c", "x", "y"}, body = "c.moveTo(x,y);")
    @Override
    public native void moveTo(Object c, double x, double y);

    @JavaScriptBody(args = { "c", "x", "y"}, body = "c.lineTo(x,y);")
    @Override
    public native void lineTo(Object c, double x, double y);

    @JavaScriptBody(args = { "c", "cpx", "cpy", "x", "y"}, body = "c.quadraticCurveTo(cpx,cpy,x,y);")
    @Override
    public native void quadraticCurveTo(Object c, double cpx, double cpy, double x, double y);

    @JavaScriptBody(args = { "c", "cp1x", "cp1y", "cp2x", "cp2y", "x", "y"},
            body = "c.bezierCurveTo(cp1x,cp1y,cp2x,cp2y,x,y);")
    @Override
    public native void bezierCurveTo(Object c, double cp1x, double cp1y, double cp2x, double cp2y, double x, double y);

    @JavaScriptBody(args = { "c", "x", "y", "width", "height"}, body = "c.fillRect(x,y,width,height);")
    @Override
    public native void fillRect(Object c, double x, double y, double width, double height);

    @JavaScriptBody(args = { "c", "x", "y", "width", "height"}, body = "c.strokeRect(x,y,width,height);")
    @Override
    public native void strokeRect(Object c, double x, double y, double width, double height);

    @JavaScriptBody(args = { "c", "x", "y", "width", "height"}, body = "c.clearRect(x,y,width,height);")
    @Override
    public native void clearRect(Object c, double x, double y, double width, double height);

    @JavaScriptBody(args = { "c", "x", "y", "width", "height"}, body = "c.rect(x,y,width,height);")
    @Override
    public native void rect(Object c, double x, double y, double width, double height);

    @JavaScriptBody(args = { "c", }, body = "c.save();")
    @Override
    public native void save(Object c);

    @JavaScriptBody(args = { "c", }, body = "c.restore();")
    @Override
    public native void restore(Object c);

    @JavaScriptBody(args = { "c", "angle"}, body = "c.rotate(angle);")
    @Override
    public native void rotate(Object c, double angle);

    @JavaScriptBody(args = { "ctx", "a", "b", "c", "d", "e", "f"}, body = "ctx.transform(a,b,c,d,e,f);")
    @Override
    public native void transform(Object ctx, double a, double b, double c, double d, double e, double f);

    @JavaScriptBody(args = { "ctx", "a", "b", "c", "d", "e", "f"}, body = "ctx.setTransform(a,b,c,d,e,f);")
    @Override
    public native void setTransform(Object ctx, double a, double b, double c, double d, double e, double f);

    @JavaScriptBody(args = { "c", "x", "y"}, body = "c.translate(x,y);")
    @Override
    public native void translate(Object c, double x, double y);

    @JavaScriptBody(args = { "c", "x", "y"}, body = "c.scale(x,y);")
    @Override
    public native void scale(Object c, double x, double y);

    @Override
    public Object drawImage(Object c, Image image, double x, double y, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        return drawImageImpl(c, nativeImage, x, y);
    }

    @Override
    public Object drawImage(Object c, Image image, double x, double y, double width, double height, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        return drawImageImpl(c, nativeImage, x, y, width, height);
    }

    @Override
    public Object drawImage(Object c, Image image, double sx, double sy, double sWidth, double sHeight, double x, double y, double width, double height, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        return drawImageImpl(c, nativeImage, sx, sy, sWidth, sHeight, x, y, width, height);
    }

    @JavaScriptBody(args = {"ctx", "img", "x", "y", "width", "height"}, body = "img.onload=function(){ctx.drawImage(img,x,y,width,height);};ctx.drawImage(img,x,y,width,height); return img;")
    private native static Object drawImageImpl(Object ctx, Object img, double x, double y, double width, double height);

    @JavaScriptBody(args = {"ctx", "img", "sx", "sy", "swidth", "sheight", "x", "y", "width", "height"}, body = "img.onload=function(){ctx.drawImage(img,sx,sy,swidth,sheight,x,y,width,height);}; ctx.drawImage(img,sx,sy,swidth,sheight,x,y,width,height); return img;")
    private native static Object drawImageImpl(Object ctx, Object img, double sx, double sy, double sWidth, double sHeight, double x, double y, double width, double height);

    @JavaScriptBody(args = {"ctx", "img", "x", "y"}, body = "img.onload=function(){ctx.drawImage(img,x,y);}; ctx.drawImage(img,x,y); return img;")
    private native static Object drawImageImpl(Object ctx, Object img, double x, double y);

    @Override
    public Object setFillStyle(Object c, Style style, Object nativeStyle) {
        if (nativeStyle == null) {
            nativeStyle = createNativeStyle(c, style);
        }
        setFillStyleImpl(c, nativeStyle);
        return nativeStyle;
    }

    private Object createNativeStyle(Object c, Style style) {
        if (style instanceof RadialGradient) {
            RadialGradientWrapper gradient = createRadialGradientWrapper(c,
                    ((RadialGradient) style).getX0(),
                    ((RadialGradient) style).getY0(),
                    ((RadialGradient) style).getR0(),
                    ((RadialGradient) style).getX1(),
                    ((RadialGradient) style).getY1(),
                    ((RadialGradient) style).getR1());
            Map<Double, String> stops = ((LinearGradient) style).getStops();
            Set<Double> keySet = stops.keySet();
            for (Double double1 : keySet) {
                addColorStopImpl(style, double1, stops.get(double1));
            }
            return gradient;

        } else if (style instanceof LinearGradient) {
            LinearGradientWrapper gradient = createLinearGradientWrapper(c,
                    ((LinearGradient) style).getX0(),
                    ((LinearGradient) style).getY0(),
                    ((LinearGradient) style).getX1(),
                    ((LinearGradient) style).getY1());
            Map<Double, String> stops = ((LinearGradient) style).getStops();
            Set<Double> keySet = stops.keySet();
            for (Double double1 : keySet) {
                addColorStopImpl(style, double1, stops.get(double1));
            }
            return gradient;
        } else if (style instanceof Pattern) {
            return createPatternWrapper(c, ((Pattern) style).getImageResource(), ((Pattern) style).getRepeat());
        } else if (style instanceof Color) {
            return ((Color) style)
                    .getAsString();
        }
        return null;
    }

    @JavaScriptBody(args = {"gradient", "position", "color"}, body
            = "gradient.addColorStop(position,color)")
    private static native void addColorStopImpl(Object gradient, double position, String color);

    @JavaScriptBody(args = {"context", "obj"}, body = "context.fillStyle=obj;")
    private native void setFillStyleImpl(Object context, Object obj);

    @JavaScriptBody(args = {"c", "style"}, body = "c.strokeStyle=style.valueOf();")
    public native void setStrokeStyle(Object c, String style);

    @Override
    public Object setStrokeStyle(Object c, Style style, Object nativeStyle) {
        if (nativeStyle == null) {
            nativeStyle = createNativeStyle(c, style);
        }
        setStrokeStyleImpl(c, nativeStyle);
        return nativeStyle;
    }

    @JavaScriptBody(args = {"context", "obj"}, body = "context.strokeStyle=obj;")
    private native void setStrokeStyleImpl(Object context, Object obj);
    /*
     @JavaScriptBody(args = {"color"}, body = "this._context().shadowColor=color.valueOf();")
     @Override
     public native void setShadowColor(String color);

     @JavaScriptBody(args = {"blur"}, body = "this._context().shadowBlur=blur;")
     @Override
     public native void setShadowBlur(double blur);

     @JavaScriptBody(args = {"x"}, body = "this._context().shadowOffsetX=x;")
     @Override
     public native void setShadowOffsetX(double x);

     @JavaScriptBody(args = {"y"}, body = "this._context().shadowOffsetY=y;")
     @Override
     public native void setShadowOffsetY(double y);

     @JavaScriptBody(args = {}, body = "return this._context().strokeStyle;")
     public native String getStrokeStyle();

     @JavaScriptBody(args = {}, body = "return this._context().shadowColor;")
     @Override
     public native String getShadowColor();

     @JavaScriptBody(args = {}, body = "return this._context().shadowBlur;")
     @Override
     public native double getShadowBlur();

     @JavaScriptBody(args = {}, body = "return this._context().shadowOffsetX;")
     @Override
     public native double getShadowOffsetX();

     @JavaScriptBody(args = {}, body = "return this._context().shadowOffsetY;")
     @Override
     public native double getShadowOffsetY();
     */

    @JavaScriptBody(args = { "c", }, body = "return c.lineCap;")
    @Override
    public native String getLineCap(Object c);

    @JavaScriptBody(args = { "c", "style"}, body = "c.lineCap=style.valueOf();")
    @Override
    public native void setLineCap(Object c, String style);

    @JavaScriptBody(args = { "c", }, body = "return c.lineJoin;")
    @Override
    public native String getLineJoin(Object c);

    @JavaScriptBody(args = { "c", "style"}, body = "c.lineJoin=style.valueOf();")
    @Override
    public native void setLineJoin(Object c, String style);

    @JavaScriptBody(args = { "c", }, body = "return c.lineWidth;")
    @Override
    public native double getLineWidth(Object c);

    @JavaScriptBody(args = { "c", "width"}, body = "c.lineWidth=width;")
    @Override
    public native void setLineWidth(Object c, double width);

    @JavaScriptBody(args = { "c", }, body = "return c.miterLimit;")
    @Override
    public native double getMiterLimit(Object c);

    @JavaScriptBody(args = { "c", "limit"}, body = "c.miterLimit=limit;")
    @Override
    public native void setMiterLimit(Object c, double limit);

    @JavaScriptBody(args = { "c", }, body = "return c.font;")
    @Override
    public native String getFont(Object c);

    @JavaScriptBody(args = { "c", "font"}, body = "c.font=font.valueOf();")
    @Override
    public native void setFont(Object c, String font);

    @JavaScriptBody(args = { "c", }, body = "return c.textAlign;")
    @Override
    public native String getTextAlign(Object c);

    @JavaScriptBody(args = { "c", "textalign"}, body = "c.textAlign=textalign.valueOf();")
    @Override
    public native void setTextAlign(Object c, String textAlign);

    @JavaScriptBody(args = { "c", }, body = "return c.textBaseline;")
    @Override
    public native String getTextBaseline(Object c);

    @JavaScriptBody(args = { "c", "textbaseline"}, body = "c.textBaseline=textbaseline.valueOf();")
    @Override
    public native void setTextBaseline(Object c, String textbaseline);

    @JavaScriptBody(args = { "c", "text", "x", "y"}, body = "c.fillText(text,x,y);")
//    @JavaScriptBody(args = { "c", "text", "x", "y"}, body = "console.log(text);")
    @Override
    public native void fillText(Object c, String text, double x, double y);

    @JavaScriptBody(args = { "c", "text", "x", "y", "maxwidth"}, body = "c.fillText(text,x,y,maxwidth);")
    @Override
    public native void fillText(Object c, String text, double x, double y, double maxWidth);

    @Override
    public Dimension measureText(Object c, String text) {
        measureTextImpl(c, text);
        return new Dimension(1, 1);
    }

    @JavaScriptBody(args = { "c", "text"},
            body = "return c.measureText(text);")
    private native Object measureTextImpl(Object c, String text);

    @JavaScriptBody(args = { "c", "text", "x", "y"}, body = "c.strokeText(text,x,y);")
    @Override
    public native void strokeText(Object c, String text, double x, double y);

    @JavaScriptBody(args = { "c", "text", "x", "y", "maxWidth"}, body = "c.strokeText(text,x,y,maxWidth);")
    @Override
    public native void strokeText(Object c, String text, double x, double y, double maxWidth);

    @Override
    public ImageData createPixelMap(Object c, double x, double y) {
        return new ImageDataWrapper(createPixelMapImpl(c, x, y));
    }

    @JavaScriptBody(args = { "c", "x", "y"},
            body = "return c.createImageData(x,y);")
    private native Object createPixelMapImpl(Object c, double x, double y);

    @Override
    public ImageData createPixelMap(Object c, ImageData imageData) {
        return new ImageDataWrapper(createPixelMapImpl(c, imageData.getWidth(), imageData.getHeight()));
    }

    @Override
    public ImageData getPixelMap(Object c, double x, double y, double width, double height) {
        return new ImageDataWrapper(getPixelMapImpl(c, x, y, width, height));
    }

    @JavaScriptBody(args = { "c", "x", "y", "width", "height"},
            body = "return c.getImageData(x,y,width,height);")
    private native Object getPixelMapImpl(Object c, double x, double y, double width, double height);

    @Override
    public void putPixelMap(Object c, ImageData imageData, double x, double y) {
        putPixelMapImpl(c, ((ImageDataWrapper) imageData).object(), x, y);
    }

    @JavaScriptBody(args = { "c", "imageData", "x", "y"},
            body = "c.putImageData(imageData,x,y);")
    private native void putPixelMapImpl(Object c, Object imageData, double x, double y);

    @Override
    public void putPixelMap(Object c, ImageData imageData, double x, double y, double dirtyx, double dirtyy, double dirtywidth, double dirtyheight) {
        putPixelMapImpl(c, ((ImageDataWrapper) imageData).object(), x, y, dirtyx, dirtyy, dirtywidth, dirtyheight);
    }

    @JavaScriptBody(args = { "c", "imageData", "x", "y", "dirtyx", "dirtyy", "dirtywidth", "dirtyheight"},
            body = "c.putImageData(imageData,x,y, dirtyx, dirtyy, dirtywidth,dirtyheight);")
    private native void putPixelMapImpl(Object c, Object imageData, double x, double y, double dirtyx, double dirtyy, double dirtywidth, double dirtyheight);

    @JavaScriptBody(args = { "c", "alpha"}, body = "c.globalAlpha=alpha;")
    @Override
    public native void setGlobalAlpha(Object c, double alpha);

    @JavaScriptBody(args = { "c", }, body = "return c.globalAlpha;")
    @Override
    public native double getGlobalAlpha(Object c);

    @JavaScriptBody(args = { "c", "operation"}, body = "c.globalCompositeOperation=operation.valueOf();")
    @Override
    public native void setGlobalCompositeOperation(Object c, String operation);

    @JavaScriptBody(args = { "c", }, body = "return c.globalCompositeOperation;")
    @Override
    public native String getGlobalCompositeOperation(Object c);

    public LinearGradientWrapper createLinearGradientWrapper(Object c, double x0, double y0, double x1, double y1) {
        return new LinearGradientWrapper(createLinearGradientImpl(c, x0, y0, x1, y1));
    }

    @JavaScriptBody(args = {"context", "x0", "y0", "x1", "y1"}, body = "return context.createLinearGradient(x0,y0,x1,y1);")
    private native Object createLinearGradientImpl(Object context, double x0, double y0, double x1, double y1);

    public PatternWrapper createPatternWrapper(Object c, Image image, String repeat) {
        return new PatternWrapper(createPatternImpl(c, image, repeat));
    }

    @JavaScriptBody(args = {"context", "image", "repeat"}, body = "return context.createPattern(image, repeat);")
    private static native Object createPatternImpl(Object context, Image image, String repeat);

    public RadialGradientWrapper createRadialGradientWrapper(Object c, double x0, double y0, double r0, double x1, double y1, double r1) {
        return new RadialGradientWrapper(createRadialGradientImpl(c, x0, y0, r0, x1, y1, r1));
    }

    @JavaScriptBody(args = {"context", "x0", "y0", "r0", "x1", "y1", "r1"}, body = "return context.createRadialGradient(x0,y0,r0,x1,y1,r1);")
    private static native Object createRadialGradientImpl(Object context, double x0, double y0, double r0, double x1, double y1, double r1);

//    @JavaScriptBody(args = {"path"}, body = "var b = new Image(); b.src=path; return b;")
//    public native Image getImageForPathImpl(String path);
    @Override
    public int getHeight(Object c) {
        return getHeightImpl(c);
    }

    @Override
    public int getWidth(Object c) {
        return getWidthImpl(c);
    }
    /*
     @Override
     public void setHeight(int height) {
     canvas.setHeight(height);
     }

     @Override
     public void setWidth(int width) {
     canvas.setWidth(width);
     }
     */
//    @JavaScriptBody(args = {"src"}, body = "var image = new Image();console.log('image complete '+image.complete);image.src = './'+ src; return image;")

    @JavaScriptBody(args = {"src"}, body = "console.log ('looking up image by id '+src);return document.getElementById(src);")
    private static native Object createImage(String src);

    @Override
    public int getWidth(Object c, Image image, Object nativeImage) {

        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());

        }
        return imageWidth(nativeImage);
    }

    @JavaScriptBody(args = {"nativeImage"}, body = "return nativeImage.naturalWidth;")
    private static native int imageWidth(Object nativeImage);

    @Override
    public int getHeight(Object c, Image image, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        return imageHeight(nativeImage);
    }

    @JavaScriptBody(args = {"nativeImage"}, body = "return nativeImage.naturalHeight;")
    private static native int imageHeight(Object nativeImage);

    @Override
    public Object mergeImages(Object c, Image a, Image b, Object cachedA, Object cachedB) {
        return mergeImages(c, cachedA, cachedB);
    }

    @JavaScriptBody(args = { "c", "img1", "img2"}, body = "var canvas = document.createElement('img');\n"
            + "var context = canvas.getContext(\"2d\");\n"
            + "var width = img1.width;\n"
            + "var height = img1.height;\n"
            + "canvas.width = width;\n"
            + "canvas.height = height;\n"
            + "context.drawImage(img1, 0, 0);\n"
            + "context.drawImage(img2, 0, 0);\n"
            + "url = canvas.toDataURL();\n"
            + "var resultImage = document.createElement('img');\n"
            + "resultImage.src=url;\n"
            + "return resultImage;")
    public static native Object mergeImages(Object c, Object img1, Object img2);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d');")
    private static native Object getContext(Object canvas);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.width;")
    private native int getHeightImpl(Object canvas);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.height;")
    private native int getWidthImpl(Object canvas);
    
   @JavaScriptBody(args = {"canvas", "width"}, body = "canvas.width = width;")
    private static native void setHeightImpl(Object canvas, int width);

    @JavaScriptBody(args = {"canvas", "height"}, body = "canvas.height = height;")
    private static native void setWidthImpl(Object canvas, int width);

   
}
