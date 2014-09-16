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
public class HTML5Graphics{
    private HTML5Graphics() {
    }
    
    /**
     * Looks for the Canvas with the specified canvasID. If there is one, it will be 
     * returned. If there is none a new one will be created. 
     * @param canvasId The canvasId to look for.
     * @return a Canvas with the specified canvasId. 
     */
    public static GraphicsContext2D getOrCreate(String canvasId) {
        return GraphicsUtils.getOrCreate(new HTML5GraphicsEnvironment(), canvasId);
    }
}
