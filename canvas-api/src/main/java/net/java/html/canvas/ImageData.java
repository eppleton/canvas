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

/**
 * ImageData is an updateable 2-Dimensional Map of Color values. Created (
 * createPixelMap / getSnapShot ) by GraphicsContext. you can modify the
 * individual pixels and render it using paintPixelMap on GraphicsContext
 *
 * @author antonepple
 */
public interface ImageData {

    /**
     * get the height.
     *
     * @return the height
     */
    public double getHeight();

    /**
     * get the width
     *
     * @return the width
     */
    public double getWidth();

    /**
     * get the red value at a specified coordinate
     *
     * @param x
     * @param y
     * @return the red value as an int (0 -255)
     */
    public int getR(int x, int y);

    /**
     * get the green value at a specified coordinate
     *
     * @param x
     * @param y
     * @return the green value as an int (0 -255)
     */
    public int getG(int x, int y);

    /**
     * get the blue value at a specified coordinate
     *
     * @param x
     * @param y
     * @return the blue value as an int (0 -255)
     */
    public int getB(int x, int y);

    /**
     * get the alpha (transparency) value at a specified coordinate
     *
     * @param x
     * @param y
     * @return the alpha value as an int (0 - 255)
     */
    public int getA(int x, int y);

     /**
     * set the red value at a specified coordinate
     *
     * @param x
     * @param y
     * @param value the red value as an int (0 - 255)
     */
    public void setR(int x, int y, int value);

     /**
     * set the green value at a specified coordinate
     *
     * @param x
     * @param y
     * @param value the green value as an int (0 - 255)
     */
    public void setG(int x, int y, int value);

     /**
     * set the blue value at a specified coordinate
     *
     * @param x
     * @param y
     * @param value the blue value as an int (0 - 255)
     */
    public void setB(int x, int y, int value);

    /**
     * set the alpha (transparency) value at a specified coordinate
     *
     * @param x
     * @param y
     * @param value the alpha value as an int (0 - 255)
     */
    public void setA(int x, int y, int value);
}
