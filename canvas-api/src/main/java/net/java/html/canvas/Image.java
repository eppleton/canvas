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

import java.util.Objects;
import java.util.ServiceLoader;
import net.java.html.canvas.spi.GraphicsEnvironment;

/**
 * Image represents an Image Resource defined by a path.
 *
 * @author antonepple
 */
public final class Image {

    private String src;
    private int cacheHash;
    private Object cached;

    /** Creates a new image referencing a specified URL.
     * @param src the (relative) URL to the image
     * @return an object representing the image
     */
    public static Image create(String src) {
        return new Image(src);
    }

    private Image(String src) {
        this.src = src;
    }

    void cache(Object toCache) {
        this.cached = toCache;
        cacheHash = hashCode();
    }

    private boolean isCached() {
        return cacheHash == hashCode();
    }

    Object getCached() {
        return isCached() ? cached : null;
    }

    public String getSrc() {
        return src;
    }

    /**
     * get the width of this Image. Might be an expensive operation, depending on the 
     * GraphicsEnvironment, it might need to be rendered.
     * @return the width of this Image.
     */
    public int getWidth() {
        ServiceLoader<GraphicsEnvironment> loader = ServiceLoader.load(GraphicsEnvironment.class);
        GraphicsEnvironment ge = null;
        for (GraphicsEnvironment graphicsEnvironment : loader) {
            ge = graphicsEnvironment;
            break;
        }
        return ge.getWidth(this, cached);
    }

    /**
     * get the height of this Image. Might be an expensive operation, depending on the 
     * GraphicsEnvironment, it might need to be rendered.
     * @return the height of this Image.
     */
    public int getHeight() {
        ServiceLoader<GraphicsEnvironment> loader = ServiceLoader.load(GraphicsEnvironment.class);
        GraphicsEnvironment ge = null;
        for (GraphicsEnvironment graphicsEnvironment : loader) {
            ge = graphicsEnvironment;
            break;
        }
        return ge.getHeight(this, cached);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.src) ^ (cached == null ? 1231 : 1237);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Image other = (Image) obj;
        if (!Objects.equals(this.src, other.src)) {
            return false;
        }
        if ((cached == null) != (other.getCached() == null)) {
            return false;
        }
        return true;
    }
}
