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
 * Just a simple class to replace the need of java.awt.Dimension, since we only
 * want to use Java core APIs to keep porting simple. You shouldn't need to
 * ever create one, unless you write an implementation of your own Graphicsenvironment.
 *
 * @author antonepple
 */
public final class Dimension {

    final double width, height;

    /**
     * Constructor 
     * @param width the width
     * @param height the height
     */
    public Dimension(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the height of this Dimension in double precision
     *
     * @return the width of this Dimension.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the width of this Dimension in double precision.
     *
     * @return the height of this Dimension.
     */
    public double getHeight() {
        return height;
    }
}
