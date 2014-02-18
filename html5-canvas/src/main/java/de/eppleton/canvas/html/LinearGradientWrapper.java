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
package de.eppleton.canvas.html;

import net.java.html.js.JavaScriptBody;


/**
 *
 * @author Anton Epple <toni.epple@eppleton.de>
 */
public class LinearGradientWrapper{

    private final Object gradient;

    LinearGradientWrapper(Object linearGradient) {
        this.gradient = linearGradient;
    }

    Object object() {
        return gradient;
    }

    public void addColorStop(double position, String color) {
        addColorStopImpl(gradient, position, color);
    }

    @JavaScriptBody(args = {"gradient", "position", "color"}, body =
            "gradient.addColorStop(position,color)")
    private static native void addColorStopImpl(Object gradient, double position, String color);
}
