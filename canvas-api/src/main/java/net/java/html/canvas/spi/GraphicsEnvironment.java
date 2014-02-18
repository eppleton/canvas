/**
 * Back 2 Browser Bytecode Translator Copyright (C) 2012 Jaroslav Tulach
 * <jaroslav.tulach@apidesign.org>
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
package net.java.html.canvas.spi;

import net.java.html.canvas.Dimension;
import net.java.html.canvas.Image;
import net.java.html.canvas.ImageData;
import net.java.html.canvas.Style;

/**
 * Provider API for Canvas. Implement this to add support for your platform.
 *
 * @author antonepple
 */
public interface GraphicsEnvironment {

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
            boolean ccw);

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
            double radius);

    /**
     * Returns true if the the given x,y point is inside the path.
     *
     * @param x the X coordinate to use for the check.
     * @param y the Y coordinate to use for the check.
     * @return true if the point given is inside the path, false otherwise.
     */
    public boolean isPointInPath(double x, double y);

    /**
     * Fills the path with the current fill paint.
     */
    public void fill();

    /**
     * Strokes the path with the current stroke paint.
     */
    public void stroke();

    /**
     * Starts a Path
     */
    public void beginPath();

    /**
     * Closes the path.
     */
    public void closePath();

    /**
     * Clips using the current path
     */
    public void clip();

    /**
     * Issues a move command for the current path to the given x,y coordinate.
     *
     * @param x the X position for the move to command.
     * @param y the Y position for the move to command.
     */
    public void moveTo(double x, double y);

    /**
     * Adds segments to the current path to make a line at the given x,y
     * coordinate.
     *
     * @param x the X coordinate of the ending point of the line.
     * @param y the Y coordinate of the ending point of the line.
     */
    public void lineTo(double x, double y);

    /**
     * Adds segments to the current path to make a quadratic curve.
     *
     * @param cpx the X coordinate of the control point
     * @param cpy the Y coordinate of the control point
     * @param x the X coordinate of the end point
     * @param y the Y coordinate of the end point
     */
    public void quadraticCurveTo(double cpx, double cpy, double x, double y);

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
    public void bezierCurveTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y);

    /**
     * Fills a rectangle using the current fill paint.
     *
     * @param x the X position of the upper left corner of the rectangle.
     * @param y the Y position of the upper left corner of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public void fillRect(double x, double y, double width, double height);

    /**
     * Strokes a rectangle using the current stroke paint.
     *
     * @param x the X position of the upper left corner of the rectangle.
     * @param y the Y position of the upper left corner of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public void strokeRect(double x, double y, double width, double height);

    /**
     * Clears a portion of the canvas with a transparent color value.
     *
     * @param x X position of the upper left corner of the rectangle.
     * @param y Y position of the upper left corner of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     */
    public void clearRect(double x, double y, double width, double height);

    /**
     * Clears a portion of the canvas with a transparent color value.
     *
     * @param x X position of the upper left corner of the rectangle.
     * @param y Y position of the upper left corner of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     */
    public void rect(double x, double y, double width, double height);

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
    public void save();

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
    public void restore();

    /**
     * Rotates the current transform in degrees.
     *
     * @param angle value in degrees to rotate the current transform.
     */
    public void rotate(double angle);

    /**
     * Concatenates the input with the current transform.
     *
     * @param a - the X coordinate scaling element of the 3x4 matrix
     * @param b - the Y coordinate shearing element of the 3x4 matrix
     * @param c - the X coordinate shearing element of the 3x4 matrix
     * @param d - the Y coordinate scaling element of the 3x4 matrix
     * @param e - the X coordinate translation element of the 3x4 matrix
     * @param f - the Y coordinate translation element of the 3x4 matrix
     */
    public void transform(double a, double b, double c, double d, double e, double f);

    /**
     * Concatenates the input with the current transform.
     *
     * @param a - the X coordinate scaling element of the 3x4 matrix
     * @param b - the Y coordinate shearing element of the 3x4 matrix
     * @param c - the X coordinate shearing element of the 3x4 matrix
     * @param d - the Y coordinate scaling element of the 3x4 matrix
     * @param e - the X coordinate translation element of the 3x4 matrix
     * @param f - the Y coordinate translation element of the 3x4 matrix
     */
    public void setTransform(double a, double b, double c, double d, double e, double f);

    /**
     * Translates the current transform by x, y.
     *
     * @param x value to translate along the x axis.
     * @param y value to translate along the y axis.
     */
    public void translate(double x, double y);

    /**
     * Scales the current transform by x, y.
     *
     * @param x value to scale in the x axis.
     * @param y value to scale in the y axis.
     */
    public void scale(double x, double y);

    /**
     * Draws an image at the given x, y position using the width and height of
     * the given image.
     *
     * @param image the image to be drawn.
     * @param x the X coordinate on the destination for the upper left of the
     * image.
     * @param y the Y coordinate on the destination for the upper left of the
     * image.
     * @return the native Image for caching.
     */
    public Object drawImage(Image image, double x, double y, Object nativeImage);

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
     * @return the native Image for caching.
     *
     */
    public Object drawImage(Image image, double x, double y, double width, double height, Object nativeImage);

    /**
     * Draws the current source rectangle of the given image to the given
     * destination rectangle of the Canvas.
     *
     * @param image the image to be drawn.
     * @param sx the source rectangle's X coordinate position.
     * @param sy the source rectangle's Y coordinate position.
     * @param sWidth the source rectangle's width.
     * @param sHeight the source rectangle's height.
     * @param x the destination rectangle's X coordinate position.
     * @param y the destination rectangle's Y coordinate position.
     * @param width the destination rectangle's width.
     * @param height the destination rectangle's height.
     * @return the native Image for caching.
     */
    public Object drawImage(Image image, double sx, double sy, double sWidth, double sHeight, double x, double y, double width, double height, Object nativeImage);

    /**
     * Get the width of this Image
     *
     * @param image the image to measure
     * @param nativeImage the cached native Image or null
     * @return the width of the image
     */
    public int getWidth(Image image, Object nativeImage);

    /**
     * Get the height of this Image
     *
     * @param image the image to measure
     * @param nativeImage the cached native Image or null
     * @return the height of the image
     */
    public int getHeight(Image image, Object nativeImage);

    /**
     * When implementing you can return an Object of your choice to enable
     * caching. Returning null means no caching. When caching is enabled, and
     * the cache hasn't been invalidated, the Object you returned will be passed
     * as a parameter.
     *
     * @param style The style object you should use to create your native style
     * @param nativeStyle your native object if cached, null otherwise
     * @return return native Object for caching
     *
     */
    public Object setFillStyle(Style style, Object nativeStyle);

    /**
     * When implementing you can return an Object of your choice to enable
     * caching. Returning null means no caching. When caching is enabled, and
     * the cache hasn't been invalidated, the Object you returned will be passed
     * as a parameter.
     *
     * @param style The style object you should use to create your native style
     * @param nativeStyle your native object if cached, null otherwise
     * @return return native Object for caching
     *
     */
    public Object setStrokeStyle(Style style, Object nativeStyle);

//    public void setShadowColor(String color);
//
//    public void setShadowBlur(double blur);
//
//    public void setShadowOffsetX(double x);
//
//    public void setShadowOffsetY(double y);
//    
//    public String getShadowColor();
//
//    public double getShadowBlur();
//
//    public double getShadowOffsetX();
//
//    public double getShadowOffsetY();
    /**
     * Gets the current stroke line cap.
     *
     * @return {@code StrokeLineCap} with a value of Butt, Round, or Square.
     */
    public String getLineCap();

    /**
     * Sets the current stroke line cap.
     *
     * @param style a value of Butt, Round, or Square.
     */
    public void setLineCap(String style);

    /**
     * Gets the current stroke line join.
     *
     * @return a value of Miter, Bevel, or Round.
     */
    public String getLineJoin();

    /**
     * Sets the current stroke line join.
     *
     * @param style with a value of Miter, Bevel, or Round.
     */
    public void setLineJoin(String style);

    /**
     * Gets the current line width.
     *
     * @return value between 0 and infinity.
     */
    public double getLineWidth();

    /**
     * Sets the current line width.
     *
     * @param width value in the range {0-positive infinity}, with any other
     * value being ignored and leaving the value unchanged.
     */
    public void setLineWidth(double width);

    /**
     * Gets the current miter limit. v
     *
     * @return the miter limit value in the range {@code 0.0-positive infinity}
     */
    public double getMiterLimit();

    /**
     * Sets the current miter limit.
     *
     * @param limit miter limit value between 0 and positive infinity with any
     * other value being ignored and leaving the value unchanged.
     */
    public void setMiterLimit(double limit);

    /**
     * Gets the current Font.
     *
     * @return the Font
     */
    public String getFont();

    /**
     * Sets the current Font.
     *
     */
    public void setFont(String font);

    /**
     * Gets the current {@code TextAlignment}.
     *
     * @return TextAlignment with values of Left, Center, Right, or Justify.
     */
    public String getTextAlign();

    /**
     * Defines horizontal text alignment, relative to the text origin.
     *
     * @param textAlign with values of Left, Center, Right.
     */
    public void setTextAlign(String textAlign);

    /**
     * Sets the current Text Baseline.
     *
     * @param baseline with values of Top, Center, Baseline, or Bottom
     */
    public String getTextBaseline();

    /**
     * Sets the current Text Baseline.
     *
     * @param baseline with values of Top, Center, Baseline, or Bottom
     */
    public void setTextBaseline(String baseline);

    /**
     * Fills the given string of text at position x, y (0,0 at top left) with
     * the current fill paint attribute.
     *
     * @param text the string of text.
     * @param x position on the x axis.
     * @param y position on the y axis.
     */
    public void fillText(String text, double x, double y);

    /**
     * Fills text and includes a maximum width of the string.
     *
     * If the width of the text extends past max width, then it will be sized to
     * fit.
     *
     * @param text the string of text.
     * @param x position on the x axis.
     * @param y position on the y axis.
     * @param maxWidth maximum width the text string can have.
     */
    public void fillText(String text, double x, double y, double maxWidth);

    /**
     * The Dimension of this text using the current Font settings
     *
     * @param text
     * @return the Dimension of this text using the current Font settings
     */
    public Dimension measureText(String text);

    /**
     * draws the given string of text at position x, y (0,0 at top left) with
     * the current stroke paint attribute.
     *
     * @param text the string of text.
     * @param x position on the x axis.
     * @param y position on the y axis.
     */
    public void strokeText(String text, double x, double y);

    /**
     * Draws text with stroke paint and includes a maximum width of the string.
     *
     * If the width of the text extends past max width, then it will be sized to
     * fit.
     *
     * @param text the string of text.
     * @param x position on the x axis.
     * @param y position on the y axis.
     * @param maxWidth maximum width the text string can have.
     */
    public void strokeText(String text, double x, double y, double maxWidth);

    /**
     * Get a pixel array that you can manipulate, e.g. apply effects / transparency
     * @param x width
     * @param y height
     * @return a PixelMap
     */
    public ImageData createPixelMap(double x, double y);

    /**
     * Create a new ImageData object with the same dimensions as the 
     * object specified by imageData (this does not copy the image data)
     * @param imageData
     * @return 
     */
    public ImageData createPixelMap(ImageData imageData);

    /**
     * Get the pixels for a region of your GraphicsContext
     * @param x start x coordinate
     * @param y start y coordinate
     * @param width width
     * @param height height
     * @return 
     */
    public ImageData getPixelMap(double x, double y, double width, double height);

    /**
     * Render an ImageData Object at the specified position
     * @param imageData the Pixel array
      * @param x start x coordinate
     * @param y start y coordinate
     */
   public void putPixelMap(ImageData imageData, double x, double y);

    /**
     * Render an ImageData Object at the specified position
     * @param imageData the Pixel array to draw
     * @param x start x coordinate
     * @param y start y coordinate
     * @param dirtyx The horizontal (x) value, in pixels, where to place the image on the canvas
     * @param dirtyy The vertical (y) value, in pixels, where to place the image on the canvas
     * @param dirtywidth The width to use to draw the image on the canvas
     * @param dirtyheight The height to use to draw the image on the canvas
     */
    public void putPixelMap(ImageData imageData, double x, double y, double dirtyx, double dirtyy, double dirtywidth, double dirtyheight);
    /**
     * Sets the global alpha of the current state.
     *
     * @param alpha value in the range {@code 0.0-1.0}. The value is clamped if
     * it is out of range.
     */
    public void setGlobalAlpha(double alpha);

    /**
     * Get the global alpha of the current state.
     *
     * @return alpha value in the range {@code 0.0-1.0}.
     */
    public double getGlobalAlpha();

    /**
     * Sets the global blend mode.
     *
     * @param operation the BlendMode that will be set.
     */
    public void setGlobalCompositeOperation(String operation);

    /**
     * Gets the global blend mode.
     *
     * @return the global BlendMode of the current state.
     */
    public String getGlobalCompositeOperation();

    /**
     * Get the height of this GraphicsContext (which should be the same as the
     * enclosing canvas height)
     *
     * @return the height of this GraphicsContext
     */
    public int getHeight();

    /**
     * Get the width of this GraphicsContext (which should be the same as the
     * enclosing canvas height)
     *
     * @return the width of this GraphicsContext
     */
    public int getWidth();

//    public void setHeight(int height);
//
//    public void setWidth(int width);
     /**
     * Merges two images drawing one on top of the other and returning the
     * result.
     *
     * @param a the lower Image
     * @param b the upper Image
     * @param cachedA the native cached Image, if available, or null.
     * @param cachedB the native cached Image, if available, or null.
     * @return
     */  
    public Object mergeImages(Image a, Image b, Object cachedA, Object cachedB);
}
