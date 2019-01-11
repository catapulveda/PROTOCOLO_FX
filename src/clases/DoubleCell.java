package clases;

import com.jfoenix.controls.JFXTextField;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class DoubleCell<S, T> extends TextFieldTableCell<S, T> {
    
    private TextField textField;
    
    private boolean escapePressed = false;
    private TablePosition<S, ?> tablePos = null;
    
    private final DecimalFormat format = new DecimalFormat(".000");    
    StringConverter<Double> converter = null;

    public DoubleCell() {
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
                    Logger.getLogger(IntegerCell.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        
        setConverter(new StringConverter<T>() {
            @Override
            public String toString(T object) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public T fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    @Override
    public void cancelEdit() {
        if (escapePressed) {
            // this is a cancel event after escape key
            super.cancelEdit();
            setText(getItemText()); // restore the original text in the view
        } else {
            // this is not a cancel event after escape key
            // we interpret it as commit.
            String newText = textField.getText();
            // commit the new text to the model
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
            // restablecer la celda de edición en el TableView
            table.edit(-1, null);
        }
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
            }
        }
    }
    
    public String getItemText(){
        return format.format(getItem());
    }
    
    private TextField getTextField() {

        final JFXTextField txt = new JFXTextField(getItem().toString());

        // Use onAction here rather than onKeyReleased (with check for Enter),
        txt.setOnAction(event -> {
//            if (getConverter() == null) {
//                throw new IllegalStateException("StringConverter is null.");
//            }
            this.commitEdit((T) converter.fromString(txt.getText()));            
            event.consume();
        });

        txt.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            System.out.println("focusedProperty");
            if (!newValue) {
                commitEdit((T) converter.fromString(txt.getText()));
            }
        });

        txt.setOnKeyPressed(t -> {
            escapePressed = t.getCode() == KeyCode.ESCAPE;
        });
        txt.setOnKeyReleased(t -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                throw new IllegalArgumentException("did not expect esc key releases here.");
            }
        });

        txt.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (null != event.getCode()) switch (event.getCode()) {
                case ESCAPE:
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
