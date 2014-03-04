/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eppleton.canvas.javafx;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import net.java.html.canvas.ImageData;

/**
 *
 * @author antonepple
 */
class ImageDataWrapper implements ImageData<Image> {

    private final WritableImage image;
    private final double height;
    private final double width;
    private final PixelWriter pixelWriter;

    public ImageDataWrapper(WritableImage image, double width, double height) {
        this.image = image;
        this.width = width;
        this.height = height;
        pixelReader = image.getPixelReader();
        pixelWriter = image.getPixelWriter();
    }
    private PixelReader pixelReader;

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public int getR(int x, int y) {
        return (int) (255 * pixelReader.getColor(x, y).getRed());
    }

    @Override
    public int getG(int x, int y) {
        return (int) (255 * pixelReader.getColor(x, y).getGreen());
    }

    @Override
    public int getB(int x, int y) {
        return (int) (255 * pixelReader.getColor(x, y).getBlue());
    }

    @Override
    public int getA(int x, int y) {
        return (int) (255 * pixelReader.getColor(x, y).getOpacity());
    }

    @Override
    public void setR(int x, int y, int value) {
        double green = pixelReader.getColor(x, y).getGreen();
        double blue = pixelReader.getColor(x, y).getBlue();
        double opacity = pixelReader.getColor(x, y).getOpacity();
        pixelWriter.setColor(x, y, new Color(((double) value) / 255, green, blue, opacity));
    }

    @Override
    public void setG(int x, int y, int value) {
        double red = pixelReader.getColor(x, y).getRed();
        double blue = pixelReader.getColor(x, y).getBlue();
        double opacity = pixelReader.getColor(x, y).getOpacity();
        pixelWriter.setColor(x, y, new Color(red, ((double) value) / 255, blue, opacity));
    }

    @Override
    public void setB(int x, int y, int value) {
        double red = pixelReader.getColor(x, y).getRed();
        double green = pixelReader.getColor(x, y).getGreen();
        double opacity = pixelReader.getColor(x, y).getOpacity();
        pixelWriter.setColor(x, y, new Color(red, green, ((double) value) / 255, opacity));
    }

    @Override
    public void setA(int x, int y, int value) {
        double red = pixelReader.getColor(x, y).getRed();
        double green = pixelReader.getColor(x, y).getGreen();
        double blue = pixelReader.getColor(x, y).getBlue();
        pixelWriter.setColor(x, y, new Color(red, green, blue, ((double) value) / 255));
    }

    @Override
    public Image getImage() {
        return image;
    }

}
