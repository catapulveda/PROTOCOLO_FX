package newpackage;

import clases.DatosTablaUno;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class FXMLController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<DatosTablaUno> tabla;
    @FXML
    private TableColumn<DatosTablaUno, Double> col1;
    @FXML
    private TableColumn<DatosTablaUno, Double> col2;
    @FXML
    private TableColumn<DatosTablaUno, Number> col3;
    @FXML
    private TableColumn<DatosTablaUno, Number> col4;
    @FXML
    private TableColumn<DatosTablaUno, Number> col5;

    DecimalFormat df = new DecimalFormat(".000");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tabla.setEditable(true);
        tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tabla.getSelectionModel().setCellSelectionEnabled(true);
        
        col1.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        col2.setCellValueFactory(new PropertyValueFactory<>("tension"));
        col3.setCellValueFactory(new PropertyValueFactory<>("faseu"));
        col4.setCellValueFactory(new PropertyValueFactory<>("fasev"));
        col5.setCellValueFactory(new PropertyValueFactory<>("fasew"));
        col1.setEditable(true);        
        col2.setEditable(true);
        col3.setEditable(true);
        col4.setEditable(true);
        col5.setEditable(true);        
                
        col3.setCellFactory(createNumberCellFactory());
        col4.setCellFactory(createNumberCellFactory());
        col5.setCellFactory(createNumberCellFactory());
        
        DatosTablaUno dt = new DatosTablaUno();
        dt.setFaseu(123);
        dt.setFasev(456);
        dt.setFasew(789);
        dt.setPosicion(1);
        dt.setTension(12340);
        
        tabla.getItems().addAll(dt,dt,dt,dt,dt);
        
        tabla.setOnKeyPressed(event->{
            TablePosition tp = tabla.getFocusModel().getFocusedCell();
            if(event.getCode().isDigitKey()){                
                tabla.edit(tp.getRow(), tp.getTableColumn());
            }else if(event.getCode()==KeyCode.ENTER){
                event.consume();
                if(tp.getRow()==tabla.getItems().size()-1){
                    int col = tabla.getVisibleLeafIndex(tp.getTableColumn());                    
                    tabla.getSelectionModel().select(0, tabla.getVisibleLeafColumn( (col+1) ));
                }else{
                    tabla.getSelectionModel().selectBelowCell();
                }
            }
        });
//        tabla.setOnKeyReleased(event->{
//            if(event.getCode() == KeyCode.ENTER) {
//                TablePosition pos = tabla.getFocusModel().getFocusedCell();
//                if(pos.getRow()==tabla.getItems().size()-1){
//                    tabla.getSelectionModel().selectNext();
//                    tabla.getSelectionModel().selectFirst();
//                }else{
//                    tabla.getSelectionModel().clearAndSelect(pos.getRow()+1, pos.getTableColumn());
//                }                
//            }
//        });
    }
    
    private Callback<TableColumn<DatosTablaUno, Number>, TableCell<DatosTablaUno, Number>> createNumberCellFactory() {
        Callback<TableColumn<DatosTablaUno, Number>, TableCell<DatosTablaUno, Number>> factory = TextFieldTableCell.forTableColumn(new NumberStringConverter(){
            @Override
            public Number fromString(String string) {                
                return Double.parseDouble(string.replace(",", "."));
            }

            @Override
            public String toString(Number object) {                
                return df.format(object);
            }
        });
        return factory;
    }
    
}
