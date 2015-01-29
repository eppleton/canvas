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
package org.apidesign.html.canvas.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.html.canvas.GraphicsContext2D;
import net.java.html.canvas.spi.GraphicsEnvironment;

/**
 *
 * @author antonepple
 */
public abstract class CnvsAccssr {

    static CnvsAccssr DEFAULT;

    public CnvsAccssr() {
        if (DEFAULT != null) {
            throw new IllegalStateException("Already initialized");
        }
        DEFAULT = this;
    }

    public static CnvsAccssr getDefault() {
        if (DEFAULT == null) {
            try {
                Class.forName(GraphicsContext2D.class.getName(), true, GraphicsContext2D.class.getClassLoader());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CnvsAccssr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return DEFAULT;
    }

    public abstract <Canvas> GraphicsContext2D create(GraphicsEnvironment<Canvas> environment, Canvas c);
}
