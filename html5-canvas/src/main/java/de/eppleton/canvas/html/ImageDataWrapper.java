/*
 * #%L
 * DukeScript HTML5 Canvas - a library from the "DukeScript Canvas API" project.
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
package de.eppleton.canvas.html;

import net.java.html.canvas.ImageData;
import net.java.html.js.JavaScriptBody;



/**
 *
 * @author Anton Epple toni.epple@eppleton.de
 */
class ImageDataWrapper implements ImageData <Object>{

    private double width, height = -1;
    private Object imageData;
    private Data data;

    public ImageDataWrapper(Object imageData) {
        this.imageData = imageData;
    }

    private Data getData() {
        if (data == null) {
            data = new Data(getDataImpl(imageData));
        }
        return data;
    }

    @JavaScriptBody(args = {"imageData"}, body = "return imageData.data")
    public native Object getDataImpl(Object imageData);

    public double getWidth() {
        if (width == -1) {
            width = getWidthImpl(imageData);
        }
        return width;
    }

    @JavaScriptBody(args = {"imageData"}, body = "return imagedata.width;")
    private static native int getWidthImpl(Object imageData);

    public double getHeight() {
        if (height == -1) {
            height = getHeightImpl(imageData);
        }
        return height;
    }

    @JavaScriptBody(args = {"imageData"}, body = "return imagedata.height;")
    private static native int getHeightImpl(Object imageData);

    Object object() {
        return imageData;
    }

    @Override
    public int getR(int x, int y) {
        int idx = (x + y * (int)width) * 4;
        return getData().get(idx);
    }

    @Override
    public int getG(int x, int y) {
        int idx = (x + y * (int)width) * 4;
        return getData().get(idx + 1);
    }

    @Override
    public int getB(int x, int y) {
        int idx = (x + y * (int)width) * 4;
        return getData().get(idx + 2);
    }

    @Override
    public int getA(int x, int y) {
        int idx = (x + y * (int)width) * 4;
        return getData().get(idx + 3);
    }

    @Override
    public void setR(int x, int y, int value) {
        int idx = (x + y * (int)width) * 4;
        getData().set(idx, value);
    }

    @Override
    public void setG(int x, int y, int value) {
        int idx = (x + y * (int)width) * 4;
        getData().set(idx + 1, value);
    }

    @Override
    public void setB(int x, int y, int value) {
        int idx = (x + y * (int)width) * 4;
        getData().set(idx + 2, value);
    }

    @Override
    public void setA(int x, int y, int value) {
        int idx = (x + y * (int)width) * 4;
        getData().set(idx + 3, value);
    }

    @Override
    public Object getImage() {
        return imageData;
    }

    private static class Data {

        Object data;

        public Data(Object data) {
            this.data = data;
        }

        public int get(int index) {
            return getImpl(data, index);
        }

        public void set(int index, int value) {
            setImpl(data, index, value);
        }

        @JavaScriptBody(args = {"data", "index", "value"}, body = "data[index]=value;")
        private static native void setImpl(Object data, int index, int value);

        @JavaScriptBody(args = {"imagedata", "index"}, body = "return data[index];")
        private static native int getImpl(Object data, int index);
    }
}
