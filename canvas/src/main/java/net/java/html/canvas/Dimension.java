/**
 * Back 2 Browser Bytecode Translator
 * Copyright (C) 2012 Jaroslav Tulach <jaroslav.tulach@apidesign.org>
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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
     * @param width
     * @param height 
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
