/**
 * Canvas API
 * Copyright (C) 2013 AntonEpple toni.epple@eppleton.de
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
package de.eppleton.canvas.html;

/**
 *
 * @author Anton Epple toni.epple@eppleton.de
 */
class PatternWrapper {

    private Object pattern;

    public PatternWrapper(Object pattern) {
        this.pattern = pattern;
    }

    Object object() {
        return pattern;
    }
}
