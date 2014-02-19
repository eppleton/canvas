/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eppleton.canvas.javafx;

import javafx.scene.canvas.Canvas;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author antonepple
 */
@ServiceProvider(service = CanvasProvider.class)
public class JavaFXCanvasProvider implements CanvasProvider {

    private final static Canvas INSTANCE = new Canvas();

    @Override
    public Canvas getCanvas() {
        return INSTANCE;
    }

}
