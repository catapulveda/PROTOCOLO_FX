package clases;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;

public class Celda<S, T> extends TextFieldTableCell<S, T> {

    private TextField txt;
    private boolean escape = false;
    private TableView<DatosTablaDos> tablaDos;

    public Celda(final StringConverter<T> converter, TableView<DatosTablaDos> tablaDos) {
        super(converter);
        this.tablaDos = tablaDos;
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if(isEditing()){
            if (txt == null) {
                System.out.println("el textField es nulo");
                txt = getTextField();
            }
            escape = false;
            
            if (txt != null) {
                System.out.println("el txt es diferente de null y se le asignara el texto "+getItemText());
                txt.setText(getItemText());
            }
            setText(null);
            setGraphic(txt);
            txt.selectAll();
            txt.requestFocus();            
        }
    }
    
    @Override
    public void cancelEdit() {
        if (escape) {            
            setText(getItemText());
        } else {           
            this.commitEdit(getConverter().fromString(txt.getText()));
        }
        super.cancelEdit();
        setGraphic(null);
    }

    @Override
    public void commitEdit(T newValue) {
        super.commitEdit(newValue);
        setGraphic(null);
        getTableView().requestFocus();
    }

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);        
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if(txt!=null){
                    txt.setText(getItemText());
                }
                setText(null);
                setGraphic(txt);
            } else {                
                setText(getItemText());
                setGraphic(null);
                
                TableRow<DatosTablaUno> row = getTableRow();
                double min = 0, max = 0;
                if(tablaDos.getItems().size()>0){
                    min = tablaDos.getItems().get(getIndex()).getMinima();
                    max = tablaDos.getItems().get(getIndex()).getMaxima();
                }                
                                
                if(min > Double.parseDouble(getItem().toString()) || Double.parseDouble(getItem().toString()) > max){
                    setStyle("-fx-background-color: #bd2707; -fx-text-fill: white; -fx-font-weight: bold;-fx-alignment: CENTER;");
                }else{
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        }
    }

    

    private String getItemText() {
        return getConverter().toString(getItem());
    }

    private TextField getTextField(){
        TextField textField = new TextField();

        textField.setOnAction(evt -> {
            this.commitEdit(getConverter().fromString(textField.getText()));
        });

        textField.setOnKeyPressed(evt -> {
            if (null != evt.getCode()) switch (evt.getCode()) {
                case ESCAPE:
                    escape = evt.getCode()==KeyCode.ESCAPE;
                    textField.setText(getItemText());
                    cancelEdit();
                    evt.consume();
                    break;
                case TAB:
                    getTableView().getSelectionModel().selectNext();
                    evt.consume();
                    break;
                case UP:
                    getTableView().getSelectionModel().selectAboveCell();
                    evt.consume();
                    break;
                case DOWN:
                    getTableView().getSelectionModel().selectBelowCell();
                    evt.consume();
                    break;
                default:
                    break;
            }
        });
        return textField;
    }
    
}
