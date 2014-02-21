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

import java.util.ServiceLoader;
import java.util.logging.Logger;
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
     * Use this to get A GraphicsContext to draw on.
     *
     * @param environment
     * @return
     */
    public static GraphicsContext2D getOrCreate(String id) {
        ServiceLoader<GraphicsEnvironment> sl = ServiceLoader.load(GraphicsEnvironment.class);
        GraphicsEnvironment env = null;
        for (GraphicsEnvironment graphicsEnvironment : sl) {
            if (env != null) {
                Logger.getLogger(GraphicsUtils.class.getName()).warning("More than one GraphicsEnvironment registered, using this one "+env+" but also found "+graphicsEnvironment);
            } else {
                env = graphicsEnvironment;
            }
        }
        return CnvsAccssr.getDefault().create(env, id);
    }

}
