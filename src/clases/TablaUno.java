package clases;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TablaUno {

    private final IntegerProperty posicion = new SimpleIntegerProperty();
    private final IntegerProperty tension = new SimpleIntegerProperty();
    private final DoubleProperty faseu = new SimpleDoubleProperty();
    private final DoubleProperty fasev = new SimpleDoubleProperty();
    private final DoubleProperty fasew = new SimpleDoubleProperty();

    public TablaUno() {
    }
    
    public double getFasew() {
        return fasew.get();
    }

    public void setFasew(double value) {
        fasew.set(value);
    }

    public DoubleProperty fasewProperty() {
        return fasew;
    }    

    public double getFasev() {
        return fasev.get();
    }

    public void setFasev(double value) {
        fasev.set(value);
    }

    public DoubleProperty fasevProperty() {
        return fasev;
    }    

    public double getFaseu() {
        return faseu.get();
    }

    public void setFaseu(double value) {
        faseu.set(value);
    }

    public DoubleProperty faseuProperty() {
        return faseu;
    }   
    
    public int getTension() {
        return tension.get();
    }

    public void setTension(int value) {
        tension.set(value);
    }

    public IntegerProperty tensionProperty() {
        return tension;
    }
    

    public int getPosicion() {
        return posicion.get();
    }

    public void setPosicion(int value) {
        posicion.set(value);
    }

    public IntegerProperty posicionProperty() {
        return posicion;
    }
    
    
    
}
