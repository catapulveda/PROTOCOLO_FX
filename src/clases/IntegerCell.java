package clases;

import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

/**
 *
 * @author AUXPLANTA
 */
public class IntegerCell<T> extends TableCell<T, Double>{
    
    private final TextField txt ;

    private final DecimalFormat format = new DecimalFormat("000.000");
    private boolean esc = false;
    StringConverter<Double> converter = null;

    public IntegerCell() {
        this.txt = new TextField();
        
        converter = new StringConverter<Double>() {

            @Override
            public String toString(Double object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Double fromString(String string) {                
                try {                
                    return string.isEmpty() ? format.parse(string).doubleValue() : Double.parseDouble(string);
                } catch (ParseException ex) {
                    Logger.getLogger(IntegerCell.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
                
        txt.setOnAction(e ->{            
            commitEdit(converter.fromString(txt.getText()));
            getTableView().getSelectionModel().selectBelowCell();
        });
        
        txt.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (null != event.getCode()) switch (event.getCode()) {
                case ESCAPE:
                    esc = true;
                    txt.setText(getItem().toString());
                    cancelEdit();
                    esc = false;
                    event.consume();
                    break;
                case TAB:
                    getTableView().getSelectionModel().selectNext();
                    event.consume();
                    break;
                case RIGHT:
                    commitEdit(converter.fromString(txt.getText()));
                    getTableView().getSelectionModel().selectNext();
                    event.consume();
                    break;                
                case LEFT:
                    commitEdit(converter.fromString(txt.getText()));
                    getTableView().getSelectionModel().selectPrevious();
                    event.consume();
                    break;
                case UP:
                    commitEdit(converter.fromString(txt.getText()));
                    getTableView().getSelectionModel().selectAboveCell();
                    event.consume();
                    break;
                case DOWN:
                    commitEdit(converter.fromString(txt.getText()));
                    getTableView().getSelectionModel().selectBelowCell();
                    event.consume();
                    break;                    
                default:                    
                    break;
            }
        });        
                
        setGraphic(txt);
        setContentDisplay(ContentDisplay.TEXT_ONLY);
        setAlignment(Pos.BASELINE_RIGHT);
    }
    
    @Override
    protected void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else if (isEditing()) {
            txt.setText(item.toString());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        } else {
//            TableRow<Pedido> row = getTableRow();
//            try {
//                if (row.getItem().getOc().getNumerodeorden() > 0) {
//                    setDisabled(true);
//                }
//            } catch (Exception e) {
////                setDisabled(false);
//            }
            setText(format.format(item));
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }        
    }

    @Override
    public void startEdit() {
        super.startEdit();
        txt.setText((getItem().toString()));
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        txt.requestFocus();
        //textField.selectAll();
        txt.end();
    }

    @Override
    public void cancelEdit() {
        if(esc){
            setText(format.format(getItem()));
        }else{
            setText(""+converter.fromString(txt.getText()));
            commitEdit(converter.fromString(txt.getText()));
        }
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    public void commitEdit(Double newValue) {
        super.commitEdit(newValue);
        setContentDisplay(ContentDisplay.TEXT_ONLY);
        getTableView().requestFocus();
    }
    
}
