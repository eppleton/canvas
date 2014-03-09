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
package net.java.html.canvas.spi;

import net.java.html.canvas.GraphicsContext2D;
import org.apidesign.html.canvas.impl.CnvsAccssr;

/**
 *
 * @author antonepple
 */
public class GraphicsUtils {

    private GraphicsUtils() {
    }

    /**
     * Use this to get A GraphicsContext2D to draw on. Pass in a  
     * {@link net.java.html.canvas.spi.GraphicsEnvironment} and a String to identify
     * your Canvas. 
     * In the HTML5 implementation, the {@link net.java.html.canvas.spi.GraphicsEnvironment} 
     * will look for a Canvas with that ID in your HTML (or add a new one to the page).
     * @param env the GraphicsEnvironment
     * @param id used by the {@link GraphicsEnvironment to identify the Canvas}
     * @return A GraphicsContext2D
     */
    public static GraphicsContext2D getOrCreate(GraphicsEnvironment env, String id) {       
        return CnvsAccssr.getDefault().create(env, env.getOrCreateCanvas(id));
    }

}
