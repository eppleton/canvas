/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.java.html.canvas.spi;

import net.java.html.canvas.GraphicsContext2D;

/**
 * A Factory to get or create a GraphicsContext with a certain id. If a 
 * Canvas with this ID already exists, implementors should make sure it is reused.
 * @author antonepple
 */
public interface GraphicsFactory {

    /**
     * Looks for the Canvas with the specified canvasID. If there is one, it will be 
     * returned. If there is none a new one will be created. 
     * @param canvasId The canvasId to look for.
     * @return a Canvas with the specified canvasId. 
     */
    GraphicsContext2D getOrCreate(String canvasId);
    
}
