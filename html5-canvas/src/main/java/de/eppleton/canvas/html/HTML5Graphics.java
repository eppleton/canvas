/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eppleton.canvas.html;

import net.java.html.canvas.GraphicsContext2D;
import net.java.html.canvas.spi.GraphicsUtils;

/**
 *
 * @author antonepple
 */
public class HTML5Graphics {

    public GraphicsContext2D getOrCreate(String canvasId) {
        return GraphicsUtils.getOrCreate(new HTML5GraphicsEnvironment(), canvasId);
    }
}
