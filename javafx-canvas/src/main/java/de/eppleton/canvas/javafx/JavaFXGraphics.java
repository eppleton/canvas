/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.eppleton.canvas.javafx;

import net.java.html.canvas.GraphicsContext2D;
import net.java.html.canvas.spi.GraphicsFactory;
import net.java.html.canvas.spi.GraphicsUtils;

/**
 *
 * @author antonepple
 */
public class JavaFXGraphics implements GraphicsFactory{
    
    public GraphicsContext2D getOrCreate(String canvasId) {
        return GraphicsUtils.getOrCreate(new JavaFXGraphicsEnvironment(), canvasId);
    }
}
