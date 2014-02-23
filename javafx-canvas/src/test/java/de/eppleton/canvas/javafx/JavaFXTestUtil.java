/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.eppleton.canvas.javafx;

import java.lang.reflect.Method;
import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 *
 * @author antonepple
 */
public class JavaFXTestUtil {
    
    public static void runOnEventQueue(final Object instance, final String method) throws Exception{
        Task<Void> task = new Task<Void>() {
            
            @Override
            protected Void call() throws Exception {
                Method method1 = instance.getClass().getMethod(method, new Class[0]);
                method1.invoke(instance);
                return null;
            }
        };
       Platform.runLater(task);
       task.get();
    }
    
}
