package clases;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class Metodos {

    public static DoubleValidator doubleValidator = new DoubleValidator();
    public static NumberValidator numberValidator = new NumberValidator();
    
    public static void fontComboBox(ComboBox combo) {
        combo.setButtonCell(new ListCell() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty && item != null) {
                    setText(item.toString());
                    //setTextFill(Color.RED);
                    setFont(Font.font("System Regular", 10));
                } else {
                    setText("VACIO");
                }
            }
        });
    }
    
    public static void onlyDouble(JFXTextField txt, Node node, Node up){
        txt.getValidators().add(doubleValidator);
//        doubleValidator.setMessage("Sólo numeros");
        txt.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(newValue!=null){
                txt.validate();
            }
        });
        txt.setTextFormatter(new TextFormatter<Double>(new StringConverter<Double>(){
            @Override
            public String toString(Double object) {
//                System.out.println("setTextFormatter object es "+object);
                if(object==null)
                    return "0";
                
                return object.toString();
            }

            @Override
            public Double fromString(String string) {
                return Double.parseDouble(string);
            }            
        }));
        txt.setOnKeyPressed((evt)->{
            if(evt.getCode()==KeyCode.ENTER || evt.getCode()==KeyCode.DOWN){
                if(null!=node)
                    node.requestFocus();
            }else if(evt.getCode()==KeyCode.UP){
                up.requestFocus();
            }
        });
    }
    
    public static void onlyInteger(JFXTextField txt, Node node, Node up){
        txt.getValidators().add(numberValidator);
        txt.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if(newValue!=null){
                txt.validate();
            }
        });
        txt.setTextFormatter(new TextFormatter<Integer>(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                if(object==null)
                    return "0";
                
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        }));
        
        txt.setOnKeyPressed((evt)->{            
            if(evt.getCode()==KeyCode.ENTER || evt.getCode()==KeyCode.DOWN){
                node.requestFocus();
            }else if(evt.getCode()==KeyCode.UP){
                up.requestFocus();
            }
        });
    }
    
    public static Double getDouble(JFXTextField txt){
        try {
            return Double.parseDouble(txt.getText());
        } catch (NumberFormatException e) {
            System.out.println("Error getDouble() "+txt+"\t"+e.getMessage()+"\t"+e.getCause());
            txt.validate();
            return 0.0;
        }
    }
    public static Integer getInteger(JFXTextField txt){
        try {
            return Integer.parseInt(txt.getText());
        } catch (NumberFormatException e) {
            System.out.println("Error getInteger() "+txt+"\t"+e.getMessage()+"\t"+e.getCause());
            txt.validate();
            return 0;
        }
    }
    
    public static void configTable(TableView table){
        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getColumns().forEach((column) -> {
            ((TableColumn)column).setStyle("-fx-alignment: CENTER;");
            ((TableColumn)column).setEditable(true);
        });
    }
    
    public static void rotarError(Node node){
        RotateTransition rt = new RotateTransition(Duration.millis(100), node);
        rt.setCycleCount(7);
        rt.setAutoReverse(true);
        rt.setFromAngle(-5);
        rt.setToAngle(5);
        rt.setOnFinished((e)->{node.setRotate(0);});
        rt.play(); 
    }

}
