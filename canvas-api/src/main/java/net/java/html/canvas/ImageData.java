/*
 * #%L
 * DukeScript Canvas API - a library from the "DukeScript Canvas API" project.
 * Visit http://dukescript.com for support and commercial license.
 * %%
 * Copyright (C) 2015 Eppleton IT Consulting
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
package net.java.html.canvas;

/**
 * ImageData is an updateable 2-Dimensional Map of Color values. Created (
 * createPixelMap / getSnapShot ) by GraphicsContext2D. you can modify the
 * individual pixels and render it using paintPixelMap on GraphicsContext2D
 *
 * @author antonepple
 * @param <Image> The native Image Type
 */
public interface ImageData <Image> {

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
     * @param x x coordinate
     * @param y y coordinate
     * @return the red value as an int (0 -255)
     */
    public int getR(int x, int y);

    /**
     * get the green value at a specified coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return the green value as an int (0 -255)
     */
    public int getG(int x, int y);

    /**
     * get the blue value at a specified coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return the blue value as an int (0 -255)
     */
    public int getB(int x, int y);

    /**
     * get the alpha (transparency) value at a specified coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return the alpha value as an int (0 - 255)
     */
    public int getA(int x, int y);

     /**
     * set the red value at a specified coordinate
     *
     * @param x x coordinate 
     * @param y y coordinate
     * @param value the red value as an int (0 - 255)
     */
    public void setR(int x, int y, int value);

     /**
     * set the green value at a specified coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param value the green value as an int (0 - 255)
     */
    public void setG(int x, int y, int value);

     /**
     * set the blue value at a specified coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param value the blue value as an int (0 - 255)
     */
    public void setB(int x, int y, int value);

    /**
     * set the alpha (transparency) value at a specified coordinate
     *
     * @param x x coordinate
     * @param y  y coordinate
     * @param value the alpha value as an int (0 - 255)
     */
    public void setA(int x, int y, int value);
    
    public Image getImage();
}
