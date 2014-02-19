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
import net.java.html.canvas.Image;
import net.java.html.canvas.ImageData;
import net.java.html.canvas.Style;
import net.java.html.canvas.Style.Color;
import net.java.html.canvas.Style.LinearGradient;
import net.java.html.canvas.Style.Pattern;
import net.java.html.canvas.Style.RadialGradient;
import net.java.html.canvas.spi.GraphicsEnvironment;
import net.java.html.js.JavaScriptBody;

/**
 *
 * @author Anton Epple <toni.epple@eppleton.de>
 */
public class HTML5GraphicsEnvironment implements GraphicsEnvironment {

    Object context;
    Object canvas;

    public HTML5GraphicsEnvironment() {
        this(100, 100, "canvas");
    }

    public HTML5GraphicsEnvironment(int width, int height, String id) {
        this.canvas = getOrCreate(id);
        setWidthImpl(canvas, width);
        setHeightImpl(canvas,height);
        this.context = getContext(canvas);
    }

    public static Object getOrCreate(String id) {
        Object canvas = getImpl(id);
        if (canvas == null) {
            canvas = createImpl(id);
        }
        return canvas;
    }

    @JavaScriptBody(args = {"id"}, body = "var canvas = document.getElementById('id');return canvas;")
    private static native Object getImpl(String id);

    @JavaScriptBody(args = {"id"}, body = "var canvas = document.createElement('canvas');"
            + "var body = document.getElementsByTagName(\"body\")[0];body.appendChild(canvas); return canvas;")
    private static native Object createImpl(String id);

    @JavaScriptBody(args = {"centerx", "centery", "radius", "startangle", "endangle", "ccw"},
            body = "this._context().arc(centerx,centery, radius, startangle, endangle,ccw);")
    @Override
    public native void arc(double centerX,
            double centerY,
            double startAngle,
            double radius,
            double endAngle,
            boolean ccw);

    @JavaScriptBody(args = {"x1", "y1", "x2", "y2", "r"},
            body = "this._context().arcTo(x1,y1,x2,y2,r);")
    @Override
    public native void arcTo(double x1,
            double y1,
            double x2,
            double y2,
            double r);

    @JavaScriptBody(args = {"x", "y"},
            body = "return this._context().isPointInPath(x,y);")
    @Override
    public native boolean isPointInPath(double x, double y);

    @JavaScriptBody(args = {}, body = "this._context().fill();")
    @Override
    public native void fill();

    @JavaScriptBody(args = {}, body = "this._context().stroke();")
    @Override
    public native void stroke();

    @JavaScriptBody(args = {}, body = "this._context().beginPath();")
    @Override
    public native void beginPath();

    @JavaScriptBody(args = {}, body = "this._context().closePath();")
    @Override
    public native void closePath();

    @JavaScriptBody(args = {}, body = "this._context().clip();")
    @Override
    public native void clip();

    @JavaScriptBody(args = {"x", "y"}, body = "this._context().moveTo(x,y);")
    @Override
    public native void moveTo(double x, double y);

    @JavaScriptBody(args = {"x", "y"}, body = "this._context().lineTo(x,y);")
    @Override
    public native void lineTo(double x, double y);

    @JavaScriptBody(args = {"cpx", "cpy", "x", "y"}, body = "this._context().quadraticCurveTo(cpx,cpy,x,y);")
    @Override
    public native void quadraticCurveTo(double cpx, double cpy, double x, double y);

    @JavaScriptBody(args = {"cp1x", "cp1y", "cp2x", "cp2y", "x", "y"},
            body = "this._context().bezierCurveTo(cp1x,cp1y,cp2x,cp2y,x,y);")
    @Override
    public native void bezierCurveTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y);

    @JavaScriptBody(args = {"x", "y", "width", "height"}, body = "this._context().fillRect(x,y,width,height);")
    @Override
    public native void fillRect(double x, double y, double width, double height);

    @JavaScriptBody(args = {"x", "y", "width", "height"}, body = "this._context().strokeRect(x,y,width,height);")
    @Override
    public native void strokeRect(double x, double y, double width, double height);

    @JavaScriptBody(args = {"x", "y", "width", "height"}, body = "this._context().clearRect(x,y,width,height);")
    @Override
    public native void clearRect(double x, double y, double width, double height);

    @JavaScriptBody(args = {"x", "y", "width", "height"}, body = "this._context().rect(x,y,width,height);")
    @Override
    public native void rect(double x, double y, double width, double height);

    @JavaScriptBody(args = {}, body = "this._context().save();")
    @Override
    public native void save();

    @JavaScriptBody(args = {}, body = "this._context().restore();")
    @Override
    public native void restore();

    @JavaScriptBody(args = {"angle"}, body = "this._context().rotate(angle);")
    @Override
    public native void rotate(double angle);

    @JavaScriptBody(args = {"a", "b", "c", "d", "e", "f"}, body = "this._context().transform(a,b,c,d,e,f);")
    @Override
    public native void transform(double a, double b, double c, double d, double e, double f);

    @JavaScriptBody(args = {"a", "b", "c", "d", "e", "f"}, body = "this._context().setTransform(a,b,c,d,e,f);")
    @Override
    public native void setTransform(double a, double b, double c, double d, double e, double f);

    @JavaScriptBody(args = {"x", "y"}, body = "this._context().translate(x,y);")
    @Override
    public native void translate(double x, double y);

    @JavaScriptBody(args = {"x", "y"}, body = "this._context().scale(x,y);")
    @Override
    public native void scale(double x, double y);

    @Override
    public Object drawImage(Image image, double x, double y, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        return drawImageImpl(context, nativeImage, x, y);
    }

    @Override
    public Object drawImage(Image image, double x, double y, double width, double height, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        return drawImageImpl(context, nativeImage, x, y, width, height);
    }

    @Override
    public Object drawImage(Image image, double sx, double sy, double sWidth, double sHeight, double x, double y, double width, double height, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        return drawImageImpl(context, nativeImage, sx, sy, sWidth, sHeight, x, y, width, height);
    }

    @JavaScriptBody(args = {"ctx", "img", "x", "y", "width", "height"}, body = "img.onload=function(){ctx.drawImage(img,x,y,width,height);};ctx.drawImage(img,x,y,width,height); return img;")
    private native static Object drawImageImpl(Object ctx, Object img, double x, double y, double width, double height);

    @JavaScriptBody(args = {"ctx", "img", "sx", "sy", "swidth", "sheight", "x", "y", "width", "height"}, body = "img.onload=function(){ctx.drawImage(img,sx,sy,swidth,sheight,x,y,width,height);}; ctx.drawImage(img,sx,sy,swidth,sheight,x,y,width,height); return img;")
    private native static Object drawImageImpl(Object ctx, Object img, double sx, double sy, double sWidth, double sHeight, double x, double y, double width, double height);

    @JavaScriptBody(args = {"ctx", "img", "x", "y"}, body = "img.onload=function(){ctx.drawImage(img,x,y);}; ctx.drawImage(img,x,y); return img;")
    private native static Object drawImageImpl(Object ctx, Object img, double x, double y);

    public Object setFillStyle(Style style, Object nativeStyle) {
        if (nativeStyle == null) {
            nativeStyle = createNativeStyle(style);
        }
        setFillStyleImpl(context, nativeStyle);
        return nativeStyle;
    }

    private Object createNativeStyle(Style style) {
        if (style instanceof RadialGradient) {
            RadialGradientWrapper gradient = createRadialGradientWrapper(
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
            LinearGradientWrapper gradient = createLinearGradientWrapper(
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
            return createPatternWrapper(((Pattern) style).getImageResource(), ((Pattern) style).getRepeat());
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

    @JavaScriptBody(args = {"style"}, body = "this._context().strokeStyle=style.valueOf();")
    public native void setStrokeStyle(String style);

    @Override
    public Object setStrokeStyle(Style style, Object nativeStyle) {
        if (nativeStyle == null) {
            nativeStyle = createNativeStyle(style);
        }
        setStrokeStyleImpl(context, nativeStyle);
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

    @JavaScriptBody(args = {}, body = "return this._context().lineCap;")
    @Override
    public native String getLineCap();

    @JavaScriptBody(args = {"style"}, body = "this._context().lineCap=style.valueOf();")
    @Override
    public native void setLineCap(String style);

    @JavaScriptBody(args = {}, body = "return this._context().lineJoin;")
    @Override
    public native String getLineJoin();

    @JavaScriptBody(args = {"style"}, body = "this._context().lineJoin=style.valueOf();")
    @Override
    public native void setLineJoin(String style);

    @JavaScriptBody(args = {}, body = "return this._context().lineWidth;")
    @Override
    public native double getLineWidth();

    @JavaScriptBody(args = {"width"}, body = "this._context().lineWidth=width;")
    @Override
    public native void setLineWidth(double width);

    @JavaScriptBody(args = {}, body = "return this._context().miterLimit;")
    @Override
    public native double getMiterLimit();

    @JavaScriptBody(args = {"limit"}, body = "this._context().miterLimit=limit;")
    @Override
    public native void setMiterLimit(double limit);

    @JavaScriptBody(args = {}, body = "return this._context().font;")
    @Override
    public native String getFont();

    @JavaScriptBody(args = {"font"}, body = "this._context().font=font.valueOf();")
    @Override
    public native void setFont(String font);

    @JavaScriptBody(args = {}, body = "return this._context().textAlign;")
    @Override
    public native String getTextAlign();

    @JavaScriptBody(args = {"textalign"}, body = "this._context().textAlign=textalign.valueOf();")
    @Override
    public native void setTextAlign(String textAlign);

    @JavaScriptBody(args = {}, body = "return this._context().textBaseline;")
    @Override
    public native String getTextBaseline();

    @JavaScriptBody(args = {"textbaseline"}, body = "this._context().textBaseline=textbaseline.valueOf();")
    @Override
    public native void setTextBaseline(String textbaseline);

    @JavaScriptBody(args = {"text", "x", "y"}, body = "this._context().fillText(text,x,y);")
//    @JavaScriptBody(args = {"text", "x", "y"}, body = "console.log(text);")
    @Override
    public native void fillText(String text, double x, double y);

    @JavaScriptBody(args = {"text", "x", "y", "maxwidth"}, body = "this._context().fillText(text,x,y,maxwidth);")
    @Override
    public void fillText(String text, double x, double y, double maxWidth) {
    }

    @Override
    public Dimension measureText(String text) {
        measureTextImpl(text);
        return new Dimension(1, 1);
    }

    @JavaScriptBody(args = {"text"},
            body = "return this._context().measureText(text);")
    private native Object measureTextImpl(String text);

    @JavaScriptBody(args = {"text", "x", "y"}, body = "this._context().strokeText(text,x,y);")
    @Override
    public native void strokeText(String text, double x, double y);

    @JavaScriptBody(args = {"text", "x", "y", "maxWidth"}, body = "this._context().strokeText(text,x,y,maxWidth);")
    @Override
    public native void strokeText(String text, double x, double y, double maxWidth);

    @Override
    public ImageData createPixelMap(double x, double y) {
        return new ImageDataWrapper(createPixelMapImpl(x, y));
    }

    @JavaScriptBody(args = {"x", "y"},
            body = "return this._context().createImageData(x,y);")
    private native Object createPixelMapImpl(double x, double y);

    @Override
    public ImageData createPixelMap(ImageData imageData) {
        return new ImageDataWrapper(createPixelMapImpl(imageData.getWidth(), imageData.getHeight()));
    }

    @Override
    public ImageData getPixelMap(double x, double y, double width, double height) {
        return new ImageDataWrapper(getPixelMapImpl(x, y, width, height));
    }

    @JavaScriptBody(args = {"x", "y", "width", "height"},
            body = "return this._context().getImageData(x,y,width,height);")
    private native Object getPixelMapImpl(double x, double y, double width, double height);

    @Override
    public void putPixelMap(ImageData imageData, double x, double y) {
        putPixelMapImpl(((ImageDataWrapper) imageData).object(), x, y);
    }

    @JavaScriptBody(args = {"imageData", "x", "y"},
            body = "this._context().putImageData(imageData,x,y);")
    private native void putPixelMapImpl(Object imageData, double x, double y);

    @Override
    public void putPixelMap(ImageData imageData, double x, double y, double dirtyx, double dirtyy, double dirtywidth, double dirtyheight) {
        putPixelMapImpl(((ImageDataWrapper) imageData).object(), x, y, dirtyx, dirtyy, dirtywidth, dirtyheight);
    }

    @JavaScriptBody(args = {"imageData", "x", "y", "dirtyx", "dirtyy", "dirtywidth", "dirtyheight"},
            body = "this._context().putImageData(imageData,x,y, dirtyx, dirtyy, dirtywidth,dirtyheight);")
    private native void putPixelMapImpl(Object imageData, double x, double y, double dirtyx, double dirtyy, double dirtywidth, double dirtyheight);

    @JavaScriptBody(args = {"alpha"}, body = "this._context().globalAlpha=alpha;")
    @Override
    public native void setGlobalAlpha(double alpha);

    @JavaScriptBody(args = {}, body = "return this._context().globalAlpha;")
    @Override
    public native double getGlobalAlpha();

    @JavaScriptBody(args = {"operation"}, body = "this._context().globalCompositeOperation=operation.valueOf();")
    @Override
    public native void setGlobalCompositeOperation(String operation);

    @JavaScriptBody(args = {}, body = "return this._context().globalCompositeOperation;")
    @Override
    public native String getGlobalCompositeOperation();

    public LinearGradientWrapper createLinearGradientWrapper(double x0, double y0, double x1, double y1) {
        return new LinearGradientWrapper(createLinearGradientImpl(context, x0, y0, x1, y1));
    }

    @JavaScriptBody(args = {"context", "x0", "y0", "x1", "y1"}, body = "return context.createLinearGradient(x0,y0,x1,y1);")
    private native Object createLinearGradientImpl(Object context, double x0, double y0, double x1, double y1);

    public PatternWrapper createPatternWrapper(Image image, String repeat) {
        return new PatternWrapper(createPatternImpl(context, image, repeat));
    }

    @JavaScriptBody(args = {"context", "image", "repeat"}, body = "return context.createPattern(image, repeat);")
    private static native Object createPatternImpl(Object context, Image image, String repeat);

    public RadialGradientWrapper createRadialGradientWrapper(double x0, double y0, double r0, double x1, double y1, double r1) {
        return new RadialGradientWrapper(createRadialGradientImpl(context, x0, y0, r0, x1, y1, r1));
    }

    @JavaScriptBody(args = {"context", "x0", "y0", "r0", "x1", "y1", "r1"}, body = "return context.createRadialGradient(x0,y0,r0,x1,y1,r1);")
    private static native Object createRadialGradientImpl(Object context, double x0, double y0, double r0, double x1, double y1, double r1);

//    @JavaScriptBody(args = {"path"}, body = "var b = new Image(); b.src=path; return b;")
//    public native Image getImageForPathImpl(String path);
    @Override
    public int getHeight() {
        return getHeightImpl(canvas);
    }

    @Override
    public int getWidth() {
        return getWidthImpl(canvas);
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
    public int getWidth(Image image, Object nativeImage) {

        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());

        }
        return getWidth(nativeImage);
    }

    @JavaScriptBody(args = {"nativeImage"}, body = "return nativeImage.naturalWidth;")
    private static native int getWidth(Object nativeImage);

    @Override
    public int getHeight(Image image, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        return getHeight(nativeImage);
    }

    @JavaScriptBody(args = {"nativeImage"}, body = "return nativeImage.naturalHeight;")
    private static native int getHeight(Object nativeImage);

    @Override
    public Object mergeImages(Image a, Image b, Object cachedA, Object cachedB) {
        return mergeImages(cachedA, cachedB);
    }

    @JavaScriptBody(args = {"img1", "img2"}, body = "var canvas = document.createElement('img');\n"
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
    public static native Object mergeImages(Object img1, Object img2);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d');")
    private static native Object getContext(Object canvas);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.width;")
    private native int getHeightImpl(Object canvas);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.height;")
    private native int getWidthImpl(Object canvas);
    
   @JavaScriptBody(args = {"canvas", "width"}, body = "canvas.width = width;")
    private native void setHeightImpl(Object canvas, int width);

    @JavaScriptBody(args = {"canvas", "height"}, body = "canvas.height = height;")
    private native void setWidthImpl(Object canvas, int width);
}
