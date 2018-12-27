package clases;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class TablaDos {

    private final DoubleProperty nominal = new SimpleDoubleProperty();
    private final DoubleProperty minima = new SimpleDoubleProperty();
    private final DoubleProperty maxima = new SimpleDoubleProperty();

    public double getMaxima() {
        return maxima.get();
    }

    public void setMaxima(double value) {
        maxima.set(value);
    }

    public DoubleProperty maximaProperty() {
        return maxima;
    }    

    public double getMinima() {
        return minima.get();
    }

    public void setMinima(double value) {
        minima.set(value);
    }

    public DoubleProperty minimaProperty() {
        return minima;
    }    

    public double getNominal() {
        return nominal.get();
    }

    public void setNominal(double value) {
        nominal.set(value);
    }

    public DoubleProperty nominalProperty() {
        return nominal;
    }

    
    
    public TablaDos() {
    }
        
}
