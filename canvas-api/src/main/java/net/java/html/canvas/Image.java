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

import java.util.Objects;

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
