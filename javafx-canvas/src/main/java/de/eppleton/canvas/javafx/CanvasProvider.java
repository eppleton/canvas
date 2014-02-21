/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.eppleton.canvas.javafx;

import javafx.scene.canvas.Canvas;

/**
 *
 * @author antonepple
 */
public interface CanvasProvider {
    
    public Canvas getCanvas(String id);
    
}
