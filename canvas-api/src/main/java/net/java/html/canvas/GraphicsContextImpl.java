/**
 * Canvas API
 * Copyright (C) 2013 AntonEpple <toni.epple@eppleton.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. Look for COPYING file in the top folder.
 * If not, see http://opensource.org/licenses/GPL-2.0.
 */
package net.java.html.canvas;

import java.util.Map;
import net.java.html.canvas.Style.Color;
import net.java.html.canvas.Style.LinearGradient;
import net.java.html.canvas.Style.Pattern;
import net.java.html.canvas.Style.RadialGradient;
import net.java.html.canvas.spi.GraphicsEnvironment;

final class GraphicsContextImpl<Canvas> extends GraphicsContext {

    final GraphicsEnvironment<Canvas> graphicsEnvironmentImpl;
    final Canvas canvas;

    GraphicsContextImpl(GraphicsEnvironment<Canvas> graphicsEnvironment, Canvas c) {
        this.graphicsEnvironmentImpl = graphicsEnvironment;
        this.canvas = c;
    }

    /**
     * Adds path elements to the current path to make an arc.
     *
     * @param centerX the center x position of the arc.
     * @param centerY the center y position of the arc.
     * @param startAngle the startAngle of the arc
     * @param radius the radius of the arc.
     * @param endAngle the endAngle of the arc
     * @param ccw the direction of the arc (counterclockwise)
     */
    public void arc(double centerX,
            double centerY,
            double startAngle,
            double radius,
            double endAngle,
            boolean ccw) {
        graphicsEnvironmentImpl.arc(canvas, centerX, centerY, startAngle, radius, endAngle, ccw);
    }

    /**
     * Adds segments to the current path to make an arc.
     *
     * @param x1 the X coordinate of the first point of the arc.
     * @param y1 the Y coordinate of the first point of the arc.
     * @param x2 the X coordinate of the second point of the arc.
     * @param y2 the Y coordinate of the second point of the arc.
     * @param radius the radius of the arc in the range {0.0-positive infinity}.
     */
    public void arcTo(double x1,
            double y1,
            double x2,
            double y2,
            double radius) {
        graphicsEnvironmentImpl.arcTo(canvas, x1, y1, x2, y2, radius);
    }

    /**
     * Returns true if the the given x,y point is inside the path.
     *
     * @param x the X coordinate to use for the check.
     * @param y the Y coordinate to use for the check.
     * @return true if the point given is inside the path, false otherwise.
     */
    public boolean isPointInPath(double x, double y) {
        return graphicsEnvironmentImpl.isPointInPath(canvas, x, y);
    }

    /**
     * Fills the path with the current fill paint.
     */
    public void fill() {
        graphicsEnvironmentImpl.fill(canvas);
    }

    /**
     * Strokes the path with the current stroke paint.
     */
    public void stroke() {
        graphicsEnvironmentImpl.stroke(canvas);
    }

    /**
     * Starts a Path
     */
    public void beginPath() {
        graphicsEnvironmentImpl.beginPath(canvas);
    }

    /**
     * Closes the path.
     */
    public void closePath() {
        graphicsEnvironmentImpl.closePath(canvas);
    }

    /**
     * Clips using the current path
     */
    public void clip() {
        graphicsEnvironmentImpl.clip(canvas);
    }

    /**
     * Issues a move command for the current path to the given x,y coordinate.
     *
     * @param x0 the X position for the move to command.
     * @param y0 the Y position for the move to command.
     */
    public void moveTo(double x, double y) {
        graphicsEnvironmentImpl.moveTo(canvas, x, y);
    }

    /**
     * Adds segments to the current path to make a line at the given x,y
     * coordinate.
     *
     * @param x1 the X coordinate of the ending point of the line.
     * @param y1 the Y coordinate of the ending point of the line.
     */
    public void lineTo(double x, double y) {
        graphicsEnvironmentImpl.lineTo(canvas, x, y);
    }

    /**
     * Adds segments to the current path to make a quadratic curve.
     *
     * @param cpx the X coordinate of the control point
     * @param cpy the Y coordinate of the control point
     * @param x the X coordinate of the end point
     * @param y the Y coordinate of the end point
     */
    public void quadraticCurveTo(double cpx, double cpy, double x, double y) {
        graphicsEnvironmentImpl.quadraticCurveTo(canvas, cpx, cpy, x, y);
    }

    /**
     * Adds segments to the current path to make a cubic bezier curve.
     *
     * @param cp1x the X coordinate of first bezier control point.
     * @param cp1y the Y coordinate of the first bezier control point.
     * @param cp2x the X coordinate of the second bezier control point.
     * @param cp2y the Y coordinate of the second bezier control point.
     * @param x the X coordinate of the end point.
     * @param y the Y coordinate of the end point.
     */
    public void bezierCurveTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y) {
        graphicsEnvironmentImpl.bezierCurveTo(canvas, cp1x, cp1y, cp2x, cp2y, x, y);
    }

    /**
     * Fills a rectangle using the current fill paint.
     *
     * @param x the X position of the upper left corner of the rectangle.
     * @param y the Y position of the upper left corner of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public void fillRect(double x, double y, double width, double height) {
        graphicsEnvironmentImpl.fillRect(canvas, x, y, width, height);
    }

    /**
     * Strokes a rectangle using the current stroke paint.
     *
     * @param x the X position of the upper left corner of the rectangle.
     * @param y the Y position of the upper left corner of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public void strokeRect(double x, double y, double width, double height) {
        graphicsEnvironmentImpl.strokeRect(canvas, x, y, width, height);
    }

    /**
     * Clears a portion of the canvas with a transparent color value.
     *
     * @param x X position of the upper left corner of the rectangle.
     * @param y Y position of the upper left corner of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     */
    public void clearRect(double x, double y, double width, double height) {
        graphicsEnvironmentImpl.clearRect(canvas, x, y, width, height);
    }

    /**
     * Clears a portion of the canvas with a transparent color value.
     *
     * @param x X position of the upper left corner of the rectangle.
     * @param y Y position of the upper left corner of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     */
    public void rect(double x, double y, double width, double height) {
        graphicsEnvironmentImpl.rect(canvas, x, y, width, height);
    }

    /**
     * Saves the following attributes onto a stack.
     * <ul>
     * <li>Global Alpha</li>
     * <li>Global Blend Operation</li>
     * <li>Transform</li>
     * <li>Fill Paint</li>
     * <li>Stroke Paint</li>
     * <li>Line Width</li>
     * <li>Line Cap</li>
     * <li>Line Join</li>
     * <li>Miter Limit</li>
     * <li>Number of Clip Paths</li>
     * <li>Font</li>
     * <li>Text Align</li>
     * <li>Text Baseline</li>
     * <li>Effect</li>
     * <li>Fill Rule</li>
     * </ul>
     * This method does NOT alter the current state in any way. Also, not that
     * the current path is not saved.
     */
    public void save() {
        graphicsEnvironmentImpl.save(canvas);
    }

    /**
     * Pops the state off of the stack, setting the following attributes to
     * their value at the time when that state was pushed onto the stack. If the
     * stack is empty then nothing is changed.
     *
     * <ul>
     * <li>Global Alpha</li>
     * <li>Global Blend Operation</li>
     * <li>Transform</li>
     * <li>Fill Paint</li>
     * <li>Stroke Paint</li>
     * <li>Line Width</li>
     * <li>Line Cap</li>
     * <li>Line Join</li>
     * <li>Miter Limit</li>
     * <li>Number of Clip Paths</li>
     * <li>Font</li>
     * <li>Text Align</li>
     * <li>Text Baseline</li>
     * <li>Effect</li>
     * <li>Fill Rule</li>
     * </ul>
     */
    public void restore() {
        graphicsEnvironmentImpl.restore(canvas);
    }

    /**
     * Rotates the current transform in degrees.
     *
     * @param angle value in degrees to rotate the current transform.
     */
    public void rotate(double angle) {
        graphicsEnvironmentImpl.rotate(canvas, angle);
    }

    /**
     * Concatenates the input with the current transform.
     *
     * @param mxx - the X coordinate scaling element of the 3x4 matrix
     * @param myx - the Y coordinate shearing element of the 3x4 matrix
     * @param mxy - the X coordinate shearing element of the 3x4 matrix
     * @param myy - the Y coordinate scaling element of the 3x4 matrix
     * @param mxt - the X coordinate translation element of the 3x4 matrix
     * @param myt - the Y coordinate translation element of the 3x4 matrix
     */
    public void transform(double mxx, double myx, double mxy, double myy, double mxt, double myt) {
        graphicsEnvironmentImpl.transform(canvas, mxx, myx, mxy, myy, mxt, myt);
    }

    /**
     * Concatenates the input with the current transform.
     *
     * @param mxx - the X coordinate scaling element of the 3x4 matrix
     * @param myx - the Y coordinate shearing element of the 3x4 matrix
     * @param mxy - the X coordinate shearing element of the 3x4 matrix
     * @param myy - the Y coordinate scaling element of the 3x4 matrix
     * @param mxt - the X coordinate translation element of the 3x4 matrix
     * @param myt - the Y coordinate translation element of the 3x4 matrix
     */
    public void setTransform(double mxx, double myx, double mxy, double myy, double mxt, double myt) {
        graphicsEnvironmentImpl.setTransform(canvas, mxx, myx, mxy, myy, mxt, myt);
    }

    /**
     * Translates the current transform by x, y.
     *
     * @param x value to translate along the x axis.
     * @param y value to translate along the y axis.
     */
    public void translate(double x, double y) {
        graphicsEnvironmentImpl.translate(canvas, x, y);
    }

    /**
     * Scales the current transform by x, y.
     *
     * @param x value to scale in the x axis.
     * @param y value to scale in the y axis.
     */
    public void scale(double x, double y) {
        graphicsEnvironmentImpl.scale(canvas, x, y);
    }

    /**
     * Draws an image at the given x, y position using the width and height of
     * the given image.
     *
     * @param image the image to be drawn.
     * @param x the X coordinate on the destination for the upper left of the
     * image.
     * @param y the Y coordinate on the destination for the upper left of the
     * image.
     */
    public void drawImage(Image image, double x, double y) {
        Object nativeImage = graphicsEnvironmentImpl.drawImage(canvas, image, x, y, image.getCached());
        image.cache(nativeImage);
    }

    /**
     * Draws an image into the given destination rectangle of the canvas. The
     * Image is scaled to fit into the destination rectagnle.
     *
     * @param image the image to be drawn.
     * @param x the X coordinate on the destination for the upper left of the
     * image.
     * @param y the Y coordinate on the destination for the upper left of the
     * image.
     * @param width the width of the destination rectangle.
     * @param height the height of the destination rectangle.
     */
    public void drawImage(Image image, double x, double y, double width, double height) {
        Object nativeImage = graphicsEnvironmentImpl.drawImage(canvas, image, x, y, width, height, image.getCached());
        image.cache(nativeImage);
    }

    /**
     * Draws the current source rectangle of the given image to the given
     * destination rectangle of the Canvas.
     *
     * @param image the image to be drawn.
     * @param sx the source rectangle's X coordinate position.
     * @param sy the source rectangle's Y coordinate position.
     * @param sw the source rectangle's width.
     * @param sh the source rectangle's height.
     * @param dx the destination rectangle's X coordinate position.
     * @param dy the destination rectangle's Y coordinate position.
     * @param dw the destination rectangle's width.
     * @param dh the destination rectangle's height.
     */
    public void drawImage(Image image, double sx, double sy, double sw, double sh, double dx, double dy, double dw, double dh) {
        Object nativeImage = graphicsEnvironmentImpl.drawImage(canvas, image, sx, sy, sw, sh, dx, dy, dw, dh, image.getCached());
        image.cache(nativeImage);
    }

    /**
     * Merges two images drawing one on top of the other and returning the
     * result.
     *
     * @param a the lower Image
     * @param b the upper Image
     * @return
     */
    public Image merge(Image a, Image b) {
        if (a.getCached() == null) {
            drawImage(a, 0, 0);
        }
        if (b.getCached() == null) {
            drawImage(b, 0, 0);
        }
        Object nativeImage = graphicsEnvironmentImpl.mergeImages(canvas, a, b, a.getCached(), b.getCached());
        Image merged = Image.create("should add real path here");
        merged.cache(nativeImage);
        return merged;
    }

//    public void setShadowColor(String color) {
//        graphicsEnvironmentImpl.setShadowColor(color);
//    }
//
//    public void setShadowBlur(double blur) {
//        graphicsEnvironmentImpl.setShadowBlur(blur);
//    }
//
//    public void setShadowOffsetX(double x) {
//        graphicsEnvironmentImpl.setShadowOffsetX(x);
//    }
//
//    public void setShadowOffsetY(double y) {
//        graphicsEnvironmentImpl.setShadowOffsetY(y);
//    }
//
//    public String getShadowColor() {
//        return graphicsEnvironmentImpl.getShadowColor();
//    }
//
//    public double getShadowBlur() {
//        return graphicsEnvironmentImpl.getShadowBlur();
//    }
//
//    public double getShadowOffsetX() {
//        return graphicsEnvironmentImpl.getShadowOffsetX();
//    }
//
//    public double getShadowOffsetY() {
//        return graphicsEnvironmentImpl.getShadowOffsetY();
//    }
    /**
     * Gets the current stroke line cap attribute.
     *
     * @return a value of butt, round, or square.
     */
    public String getLineCap() {
        return graphicsEnvironmentImpl.getLineCap(canvas);
    }

    /**
     * Sets the current stroke line cap attribute.
     *
     * @param style a value of miter, bevel, or round.
     */
    public void setLineCap(String style) {
        graphicsEnvironmentImpl.setLineCap(canvas, style);
    }

    /**
     * Gets the current stroke line join attribute.
     *
     * @return a value of miter, bevel, or round.
     */
    public String getLineJoin() {
        return graphicsEnvironmentImpl.getLineJoin(canvas);
    }

    /**
     * Sets the current stroke line join attribute.
     *
     * @param style a value of miter, bevel, or round.
     */
    public void setLineJoin(String style) {
        graphicsEnvironmentImpl.setLineJoin(canvas, style);
    }

    /**
     * Gets the current line width attribute.
     *
     * @return value between 0 and infinity, with any other value being ignored
     * and leaving the value unchanged.
     *
     */
    public double getLineWidth() {
        return graphicsEnvironmentImpl.getLineWidth(canvas);
    }

    /**
     * Sets the current line width attribute.
     *
     * @param lw value between 0 and infinity, with any other value being
     * ignored and leaving the value unchanged.
     *
     */
    public void setLineWidth(double width) {
        graphicsEnvironmentImpl.setLineWidth(canvas, width);
    }

    /**
     * Gets the current miter limit attribute.
     *
     * @return limit value between 0 and positive infinity with any other value
     * being ignored and leaving the value unchanged.
     */
    public double getMiterLimit() {
        return graphicsEnvironmentImpl.getMiterLimit(canvas);
    }

    /**
     * Sets the current miter limit attribute.
     *
     * @param ml miter limit value between 0 and positive infinity with any
     * other value being ignored and leaving the value unchanged.
     */
    public void setMiterLimit(double limit) {
        graphicsEnvironmentImpl.setMiterLimit(canvas, limit);
    }

    /**
     * Sets the fill style. Will be used when rendering something, e.g. calling
     * one of the fillText Methods.
     *
     * @param style
     */
    public void setFillStyle(Style style) {
        Object nativeFillStyle = graphicsEnvironmentImpl.setFillStyle(canvas, style, style.getCached());
        style.cache(nativeFillStyle);
    }

    /**
     * get the current font
     *
     * @return current Font. of the fillText Methods.
     */
    public String getFont() {
        return graphicsEnvironmentImpl.getFont(canvas);
    }

    /**
     * Set the Font. Will be used when rendering Text, e.g. by calling one of
     * the fillText Methods.
     *
     * @param font
     */
    public void setFont(String font) {
        graphicsEnvironmentImpl.setFont(canvas, font);
    }

    /**
     * sets the Style of the Stroke.
     *
     * @param style
     */
    public void setStrokeStyle(Style style) {
        Object nativeStrokeStyle = graphicsEnvironmentImpl.setStrokeStyle(canvas, style, style.getCached());
        style.cache(nativeStrokeStyle);
    }

    /**
     * Gets the current TextAlignment attribute
     *
     * @return TextAlignment with values of left, center, right, or justify.
     */
    public String getTextAlign() {
        return graphicsEnvironmentImpl.getTextAlign(canvas);
    }

    /**
     * Defines horizontal text alignment, relative to the text {@code x} origin.
     * <p>
     * Let horizontal bounds represent the logical width of a single line of
     * text. Where each line of text has a separate horizontal bounds.
     * <p>
     * Then TextAlignment is specified as:
     * <ul>
     * <li>left: the left edge of the horizontal bounds will be at {@code x}.
     * <li>center: the center, halfway between left and right edge, of the
     * horizontal bounds will be at {@code x}.
     * <li>right: the right edge of the horizontal bounds will be at {@code x}.
     * </ul>
     * <p>
     *
     * Note: Canvas does not support line wrapping, therefore the text alignment
     * Justify is identical to left aligned text.
     * <p>
     *
     * @param textAlign with values of left, center, right.
     */
    public void setTextAlign(String textAlign) {
        graphicsEnvironmentImpl.setTextAlign(canvas, textAlign);
    }

    /**
     * Gets the current Text Baseline attribute.
     *
     * @return baseline with values of top, center, baseline, or bottom
     */
    public String getTextBaseline() {
        return graphicsEnvironmentImpl.getTextBaseline(canvas);
    }

    /**
     * Sets the current Text Baseline attribute.
     *
     * @param baseline with values of top, center, baseline, or bottom
     */
    public void setTextBaseline(String textbaseline) {
        graphicsEnvironmentImpl.setTextBaseline(canvas, textbaseline);
    }

    /**
     * Renders the indicated String with current fill. default is black.
     *
     * @param text the text to stroke
     * @param x x coordinate of start position
     * @param y y coordinate of start position
     */
    public void fillText(String text, double x, double y) {
        graphicsEnvironmentImpl.fillText(canvas, text, x, y);
    }

    /**
     * Renders the indicated String with current fill. default is black.
     *
     * @param text the text to stroke
     * @param x x coordinate of start position
     * @param y y coordinate of start position
     * @param maxWidth maximum width of text
     */
    public void fillText(String text, double x, double y, double maxWidth) {
        graphicsEnvironmentImpl.fillText(canvas, text, x, y, maxWidth);
    }

    /**
     * Check the length of a text before writing it to the Canvas. Takes into
     * account the current Font.
     *
     * @param text the text to measure
     * @return the length in pixels
     */
    public Dimension measureText(String text) {
        return graphicsEnvironmentImpl.measureText(canvas, text);
    }

    /**
     * Renders the indicated String (with no fill)
     *
     * @param text the text to stroke
     * @param x x coordinate of start position
     * @param y y coordinate of start position
     */
    public void strokeText(String text, double x, double y) {
        graphicsEnvironmentImpl.strokeText(canvas, text, x, y);
    }

    /**
     * Renders the indicated String (with no fill)
     *
     * @param text the text to stroke
     * @param x x coordinate of start position
     * @param y y coordinate of start position
     * @param maxWidth maximum width of text
     */
    public void strokeText(String text, double x, double y, double maxWidth) {
        graphicsEnvironmentImpl.strokeText(canvas, text, x, y, maxWidth);
    }

    /**
     * Get a pixel array that you can manipulate, e.g. apply effects /
     * transparency
     *
     * @param x width
     * @param y height
     * @return a PixelMap
     */
    public ImageData createPixelMap(double x, double y) {
        return graphicsEnvironmentImpl.createPixelMap(canvas, x, y);
    }

    /**
     * Create a new ImageData object with the same dimensions as the object
     * specified by imageData (this does not copy the image data)
     *
     * @param pixelMap 
     * @return
     */
    public ImageData createPixelMap(ImageData pixelMap) {
        return graphicsEnvironmentImpl.createPixelMap(canvas, pixelMap);
    }
    
    /**
     * Get the pixels for a region of your GraphicsContext
     * @param x start x coordinate
     * @param y start y coordinate
     * @param width width
     * @param height height
     * @return 
     */
    public ImageData getSnapshot(double x, double y, double width, double height) {
        return graphicsEnvironmentImpl.getPixelMap(canvas, x, y, width, height);
    }
    
    /**
     * Render an ImageData Object at the specified position
     * @param pixelMap  the Pixel array
      * @param x start x coordinate
     * @param y start y coordinate
     */
    public void drawPixelMap(ImageData pixelMap, double x, double y) {
        graphicsEnvironmentImpl.putPixelMap(canvas, pixelMap, x, y);
    }

      /**
     * Render an ImageData Object at the specified position
     * @param pixelMap  the Pixel array to draw
     * @param x start x coordinate
     * @param y start y coordinate
     * @param dirtyx The horizontal (x) value, in pixels, where to place the image on the canvas
     * @param dirtyy The vertical (y) value, in pixels, where to place the image on the canvas
     * @param dirtywidth The width to use to draw the image on the canvas
     * @param dirtyheight The height to use to draw the image on the canvas
     */
    public void drawPixelMap(ImageData pixelMap, double x, double y, double dirtyx, double dirtyy, double dirtywidth, double dirtyheight) {
        graphicsEnvironmentImpl.putPixelMap(canvas, pixelMap, x, y, dirtyx, dirtyy, dirtywidth, dirtyheight);
    }

    /**
     * Sets the global alpha of the current state.
     *
     * @param alpha value in the range {@code 0.0-1.0}. The value is clamped if
     * it is out of range.
     */
    public void setGlobalAlpha(double alpha) {
        graphicsEnvironmentImpl.setGlobalAlpha(canvas, alpha);
    }

    /**
     * Gets the current global alpha.
     *
     * @return the current global alpha.
     */
    public double getGlobalAlpha() {
        return graphicsEnvironmentImpl.getGlobalAlpha(canvas);
    }

    /**
     * Sets the global blend mode.
     *
     * @param op the BlendMode that will be set.
     */
    public void setGlobalCompositeOperation(String operation) {
        graphicsEnvironmentImpl.setGlobalCompositeOperation(canvas, operation);
    }

    /**
     * Gets the global blend mode.
     *
     * @return the global BlendMode of the current state.
     */
    public String getGlobalCompositeOperation() {
        return graphicsEnvironmentImpl.getGlobalCompositeOperation(canvas);
    }

    /**
     * Create a LinearGradient to use in Canvas.
     *
     * @param x0 x coordinate of start point
     * @param y0 y coordinate of start point
     * @param x1 x coordinate of end point
     * @param y1 y coordinate of end point
     * @return the gradient
     */
    public LinearGradient createLinearGradient(double x0, double y0, double x1, double y1, Map<Double, String> stops) {
        return Style.LinearGradient.create(x0, y0, x1, y1, stops);
    }

    /**
     * Create an Image Pattern from a source Image and a repeat style. Possible
     * Styles are repeat, repeat-x, repeat-y, or no-repeat. defaults to repeat
     *
     * @param image the Image
     * @param repeat the repeat style
     * @return the Pattern
     */
    public Pattern createPattern(Image image, String repeat) {
        return new Pattern(image, repeat);
    }

    /**
     * Create a RadialGradient
     *
     * @param x0 x Coordinate of starting circle
     * @param y0 y Coordinate of starting circle
     * @param r0 radius of starting circle
     * @param x1 x coordinate of ending circle
     * @param y1 y coordinate of ending circle
     * @param r1 radius of ending circle
     * @return the Gradient
     */
    public RadialGradient createRadialGradient(double x0, double y0, double r0, double x1, double y1, double r1, Map<Double, String> stops) {
        return RadialGradient.create(x0, y0, r0, x1, y1, r1, stops);
    }

    /**
     * Convert this String Representation of a Color to a Color Object.
     *
     * @param webColor
     * @return The Color represented by the input
     */
    public Color getWebColor(String webColor) {
        return new Style.Color(webColor);
    }

    /**
     * Get the height of this GraphicsContext (which should be the same as the
     * enclosing canvas height)
     *
     * @return the height of this GraphicsContext
     */
    public int getHeight() {
        return graphicsEnvironmentImpl.getHeight(canvas);
    }

    /**
     * Get the width of this GraphicsContext (which should be the same as the
     * enclosing canvas height)
     *
     * @return the width of this GraphicsContext
     */
    public int getWidth() {
        return graphicsEnvironmentImpl.getWidth(canvas);
    }

//    public void setHeight(int height) {
//        graphicsEnvironmentImpl.setHeight(height);
//    }
//
//    public void setWidth(int width) {
//        graphicsEnvironmentImpl.setWidth(width);
//    }
    /**
     * Fill a circle with a center position of centerX, centerY and the
     * specified radius.
     *
     * @param centerX
     * @param centerY
     * @param radius
     */
    public void fillCircle(float centerX, float centerY, float radius) {
        graphicsEnvironmentImpl.arc(canvas, centerX, centerY, radius, 0, Math.PI * 2, false);
    }

    /**
     * Fills a polygon with the given points using the currently set fill paint.
     *
     * @param x_coord array containing the x coordinates of the polygon's
     * points.
     * @param y_coord array containing the y coordinates of the polygon's
     * points.
     * @param vertexCount the number of points that make the polygon.
     */
    public void fillPolygon(double[] x_coord, double[] y_coord, int vertexCount) {
        if (vertexCount >= 1 && x_coord != null && x_coord.length >= vertexCount && y_coord != null && y_coord.length >= vertexCount) {
            graphicsEnvironmentImpl.beginPath(canvas);
        }
        graphicsEnvironmentImpl.moveTo(canvas, x_coord[0], y_coord[0]);
        for (int i = 1; i < vertexCount; i++) {
            graphicsEnvironmentImpl.lineTo(canvas, x_coord[i], y_coord[i]);

        }
        graphicsEnvironmentImpl.closePath(canvas);
        graphicsEnvironmentImpl.fill(canvas);
        graphicsEnvironmentImpl.stroke(canvas);
    }
}
