package clases;

import com.jfoenix.controls.JFXTextField;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class DoubleCell<S, T> extends TextFieldTableCell<S, T> {
    
    private TextField textField;
    
    private boolean escapePressed = false;
    private TablePosition<S, ?> tablePos = null;
    
    private final DecimalFormat format = new DecimalFormat(".000");
    StringConverter<Double> converter = null;
    
    private TableView<DatosTablaDos> tablaDos;

    public DoubleCell(TableView tablaDos) {
        converter = new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                return object == null ? "" : object.toString();
            }
            @Override
            public Double fromString(String string) {
                try {                
                    return string.isEmpty() ? format.parse(string).doubleValue() : Double.parseDouble(string.replace(",", "."));
                } catch (ParseException ex) {
                    Logger.getLogger(DoubleCell.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        
        this.tablaDos = tablaDos;
        setAlignment(Pos.CENTER);
    }
    
    @Override
    public void startEdit() {
        super.startEdit();
        if(isEditing()){
            if (textField == null) {
                textField = getTextField();
            }
            escapePressed = false;
            
            if (textField != null) {
                textField.setText(getItemText());
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
            textField.requestFocus();
            
            final TableView<S> table = getTableView();
            tablePos = table.getEditingCell();
        }
    }

    @Override
    public void cancelEdit() {
        if (escapePressed) {
            System.out.println("cancelEdit ESCAPE es TRUE");
            super.cancelEdit();
            setText(getItemText()); // restore the original text in the view
        } else {
            System.out.println("cancelEdit ESCAPE es FALSE");
            String newText = textField.getText();
            this.commitEdit((T) converter.fromString(newText));
        }
        setGraphic(null); // stop editing with TextField
    }

    @Override
    public void commitEdit(T newValue) {
        if (!isEditing()) {
            return;
        }
        final TableView<S> table = getTableView();
        if (table != null) {            
            // Informar a TableView de la edición que está lista para ser confirmada.
            TableColumn.CellEditEvent editEvent = new TableColumn.CellEditEvent(table, tablePos, TableColumn.editCommitEvent(), newValue);
            Event.fireEvent(getTableColumn(), editEvent);
        }
        // we need to setEditing(false):
        super.cancelEdit(); //esto dispara un EditCancelEvent inválido.
        // actualizar el elemento dentro de esta celda, para que represente el nuevo valor
        updateItem(newValue, false);
        if (table != null) {
            table.getSelectionModel().selectBelowCell();
            // restablecer la celda de edición en el TableView
            table.edit(-1, null);
        }
    }

    

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (isEmpty()) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getItemText());
                }
                setText(null);
                setGraphic(textField);
            } else {                
                setText(getItemText());
                setGraphic(null);
                
                TableRow<DatosTablaUno> row = getTableRow();
                double min = 0, max = 0;
                if(tablaDos.getItems().size()>0){
                    min = tablaDos.getItems().get(getIndex()).getMinima();
                    max = tablaDos.getItems().get(getIndex()).getMaxima();
                }                
                                
//                if(min > Double.parseDouble(getItem().toString()) || Double.parseDouble(getItem().toString()) > max){
//                    setStyle("-fx-background-color: #D14836; -fx-text-fill: white; -fx-font-weight: bold;");
//                }else{
//                    setStyle(null);
//                }
            }
        }
    }
    
    public String getItemText(){
        return format.format(getItem());
    }
    
    private TextField getTextField() {

        final TextField txt = new TextField(getItem().toString());
        txt.setOnAction(event -> {
//            if (getConverter() == null) {
//                throw new IllegalStateException("StringConverter is null.");
//            }
            this.commitEdit((T) converter.fromString(txt.getText()));            
            event.consume();
        });
//        txt.setOnKeyPressed(t -> {
//            escapePressed = t.getCode() == KeyCode.ESCAPE;
//        });

        txt.setOnKeyReleased(t -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                throw new IllegalArgumentException("did not expect esc key releases here.");
            }
        });

        txt.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (null != event.getCode()) switch (event.getCode()) {
                case ESCAPE:
                    System.out.println("Presione ESCAPE");
                    escapePressed = event.getCode() == KeyCode.ESCAPE;
                    txt.setText(getItemText());
                    cancelEdit();
                    event.consume();
                    break;
//                case RIGHT:
                case TAB:
                    getTableView().getSelectionModel().selectNext();
                    event.consume();
                    break;
//                case LEFT:
//                    getTableView().getSelectionModel().selectPrevious();
//                    event.consume();
//                    break;
                case UP:
                    getTableView().getSelectionModel().selectAboveCell();
                    event.consume();
                    break;
                case DOWN:
                    getTableView().getSelectionModel().selectBelowCell();
                    event.consume();
                    break;
                default:
                    break;
            }
        });

        return txt;
    }
    
}
