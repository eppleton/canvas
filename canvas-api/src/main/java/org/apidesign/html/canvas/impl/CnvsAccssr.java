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
package org.apidesign.html.canvas.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.html.canvas.GraphicsContext;
import net.java.html.canvas.spi.GraphicsEnvironment;

/**
 * 
 * @author antonepple
 */
public abstract class CnvsAccssr {

    static CnvsAccssr DEFAULT;

    public CnvsAccssr() {
        if (DEFAULT!=null) throw new IllegalStateException("Already initialized");
        DEFAULT = this;
    }

    public static CnvsAccssr getDefault() {
        if (DEFAULT== null) {
            try {
                Class.forName(GraphicsContext.class.getName(), true, GraphicsContext.class.getClassLoader());
            } catch (ClassNotFoundException ex) {
                throw new IllegalStateException(ex);
            }
        }
        return DEFAULT;
    }

    public abstract <Canvas> GraphicsContext create(GraphicsEnvironment<Canvas> environment, Canvas c);
}
