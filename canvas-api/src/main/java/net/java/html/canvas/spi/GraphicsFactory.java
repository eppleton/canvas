/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.java.html.canvas.spi;

import net.java.html.canvas.GraphicsContext2D;

/**
 *
 * @author antonepple
 */
public interface GraphicsFactory {

    GraphicsContext2D getOrCreate(String canvasId);
    
}
