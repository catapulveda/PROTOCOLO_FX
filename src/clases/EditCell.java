package clases;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

/**
 *
 * @author PROGRAMADOR
 */
public class EditCell<T> extends TextFieldTableCell<T, Double> {

    private final TextField textField;
    private boolean escapePressed = false;

    public EditCell() {
        
        textField = new TextField();
        
        textField.setOnAction((ActionEvent event) -> {
            if (getConverter() == null) {
                throw new IllegalStateException("StringConverter is null.");
            }
            this.commitEdit(getConverter().fromString(textField.getText()));
            event.consume();
        });

        textField.focusedProperty().addListener((evt, neww, old)->{
            commitEdit(getConverter().fromString(textField.getText()));
        });

        textField.setOnKeyPressed(evt->{
            escapePressed = evt.getCode()==KeyCode.ESCAPE;            
        });
        
        textField.setOnKeyPressed(event->{
            if (null != event.getCode()) switch (event.getCode()) {
                case ESCAPE:
                    textField.setText(getConverter().toString(getItem()));
                    cancelEdit();
                    event.consume();
                    break;
                case RIGHT:
                case TAB:
                    getTableView().getSelectionModel().selectNext();
                    event.consume();
                    break;
                case LEFT:
                    getTableView().getSelectionModel().selectPrevious();
                    event.consume();
                    break;
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
        
        setGraphic(textField);
        setContentDisplay(ContentDisplay.TEXT_ONLY);        
    }

    @Override
    public void startEdit() {
        System.out.println("startEdit");
        super.startEdit();                           
        escapePressed = false;
        textField.setText(getItemText());
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);        
        textField.requestFocus();
        textField.end();        
    }

    @Override
    public void commitEdit(Double newValue) {
        System.out.println("commitEdit");
        super.commitEdit(newValue);
        setContentDisplay(ContentDisplay.TEXT_ONLY);
        getTableView().requestFocus();
        
//        if (!isEditing())
//            return;
//        
//        final TableView< S> table = getTableView();
//        if (table != null) {
//            // Inform the TableView of the edit being ready to be committed.
//            CellEditEvent editEvent = new CellEditEvent(table, tablePos,TableColumn.editCommitEvent(), newValue);
//            Event.fireEvent(getTableColumn(), editEvent);
//        }
//        // we need to setEditing(false):
//        super.cancelEdit(); // this fires an invalid EditCancelEvent.
//        // update the item within this cell, so that it represents the new value
//        updateItem(newValue, false);
//        if (table != null) {
//            // reset the editing cell on the TableView
//            table.edit(-1, null);
//        }
    }

    @Override
    public void cancelEdit() {
        System.out.println("cancelEdit");
        if (escapePressed) {
            super.cancelEdit();
            setText(getItemText()); 
        } else {
            String newText = textField.getText();
            this.commitEdit(getConverter().fromString(newText));
        }
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    public void updateItem(Double item, boolean empty) {
        System.out.println("updateItem");
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else if (isEditing()) {           
            textField.setText(getItemText());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }else {
            setText(getItemText());
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
    }

    private String getItemText() {
        return getConverter() == null
                ? getItem() == null ? "" : getItem().toString()
                : getConverter().toString(getItem());

    }

}
