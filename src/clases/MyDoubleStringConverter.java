package clases;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MyDoubleStringConverter extends javafx.util.converter.DoubleStringConverter {

    private final DecimalFormat format = new DecimalFormat(".000");
    
    @Override
    public Double fromString(final String value) {
        try {
            return value.isEmpty() ? format.parse("0").doubleValue() : Double.parseDouble(value.replace(",", "."));
        } catch (NumberFormatException | ParseException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Verifique el valor digitado: "+value, ButtonType.OK);
            a.setHeaderText(ex.getMessage());
            a.show();
            //Logger.getLogger(MyDoubleStringConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }

    @Override
    public String toString(Double object) {
//        System.out.println("El solo object es: "+object+"\t Formateado es: "+format.format(object));
        return format.format(object);
    }
    
}
