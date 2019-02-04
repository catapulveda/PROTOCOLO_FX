package view;

import clases.Conexion;
import clases.Metodos;
import static clases.Metodos.getDouble;
import clases.DatosTablaDos;
import clases.DatosTablaUno;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import static clases.Metodos.getInteger;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.sun.rowset.CachedRowSetImpl;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ProtocoloController implements Initializable {

    DecimalFormat df = new DecimalFormat(".000");
    private ObservableList<DatosTablaUno> listaDatosTablaUno = FXCollections.observableArrayList();
    private final StringProperty ESTADO_TRAFO = new SimpleStringProperty("Servicio:");       
    private final BooleanProperty ACTUALIZANDO = new SimpleBooleanProperty(false);      
    
    @FXML
    private VBox rootPane;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private BorderPane contenedorDatos;
    @FXML
    private JFXTextField cjprotocolo;
    @FXML
    private JFXTextField cjserie;
    @FXML
    private JFXTextField cjempresa;
    @FXML
    private JFXTextField cjmarca;
    @FXML
    private JFXTextField cjkva;
    @FXML
    private ComboBox<Integer> comboFases;
    @FXML
    private JFXTextField cjano;
    @FXML
    private JFXTextField cjvp;
    @FXML
    private JFXTextField cjvs;
    @FXML
    private ComboBox<String> comboServicio;
    @FXML
    private JFXTextField cjfrecuencia;
    @FXML
    private ComboBox<String> comboRefrigeracion;
    @FXML
    private JFXTextField cjtensionserie;
    @FXML
    private JFXTextField cjnba;
    @FXML
    private JFXTextField cjcalentamientodevanado;
    @FXML
    private ComboBox<String> comboClaseAislamiento;
    @FXML
    private JFXTextField cjalturadiseno;
    @FXML
    private JFXTextField cji1;
    @FXML
    private JFXTextField cji2;
    @FXML
    private ComboBox<String> comboDerivacion;
    @FXML
    private JFXTextField cjtemperaturadeprueba;
    @FXML
    private ComboBox<Integer> comboConmutador;
    @FXML
    private ComboBox<String> comboAceite;
    @FXML
    private ComboBox<String> comboReferencia;
    @FXML
    private JFXTextField cjruptura;
    @FXML
    private JFXTextField cjmetodo;
    @FXML
    private JFXTextField cjtiemporesistencia;
    @FXML
    private ComboBox<String> comboTensiondeprueba;
    @FXML
    private JFXTextField cjATcontraBT;
    @FXML
    private JFXTextField cjATcontraTierra;
    @FXML
    private JFXTextField cjBTcontraTierra;
    @FXML
    private ComboBox<String> comboConexion;
    @FXML
    private ComboBox<String> comboPolaridad;
    @FXML
    private JFXTextField cjUV;
    @FXML
    private JFXTextField cjVW;
    @FXML
    private JFXTextField cjWU;
    @FXML
    private JFXTextField cjproresalta;
    @FXML
    private ComboBox<String> comboMaterialAlta;
    @FXML
    private JFXTextField cjXY;
    @FXML
    private JFXTextField cjYZ;
    @FXML
    private JFXTextField cjZX;
    @FXML
    private JFXTextField cjproresbaja;
    @FXML
    private ComboBox<String> comboMaterialBaja;
    @FXML
    private JFXTextField cjBTcontraATyTierra;
    @FXML
    private JFXTextField cjATcontraBTyTierra;
    @FXML
    private JFXTextField cjtiempoaplicado;
    @FXML
    private JFXTextField cjtensionBT;
    @FXML
    private JFXTextField cjFrecuenciaInducida;
    @FXML
    private JFXTextField cjtiempoInducido;
    @FXML
    private JFXTextField cjtensionBT2;
    @FXML
    private JFXTextField cjiu;
    @FXML
    private JFXTextField cjiv;
    @FXML
    private JFXTextField cjiw;
    @FXML
    private JFXTextField cjpromedioi;
    @FXML
    private JFXTextField cjiogarantizado;
    @FXML
    private JFXTextField cjpomedido;
    @FXML
    private JFXTextField cjpogarantizado;
    @FXML
    private JFXTextField cjvcc;
    @FXML
    private JFXTextField cjpccmedido;
    @FXML
    private JFXTextField cjpcca85;
    @FXML
    private JFXTextField cjpccgarantizado;
    @FXML
    private JFXTextField cji2r;
    @FXML
    private JFXTextField cji2ra85;
    @FXML
    private JFXTextField cjz;
    @FXML
    private JFXTextField cjza85;
    @FXML
    private JFXTextField cjzgarantizado;
    @FXML
    private JFXTextField cjmasa;
    @FXML
    private JFXTextField cjaceite;
    @FXML
    private JFXTextField cjlargo;
    @FXML
    private JFXTextField cjancho;
    @FXML
    private JFXTextField cjalto;
    @FXML
    private JFXTextField cjcolor;
    @FXML
    private JFXTextField cjespesor;
    @FXML
    private JFXTextField cjelementos;
    @FXML
    private JFXTextField cjlargoelemento;
    @FXML
    private JFXTextField cjanchoelemento;
    @FXML
    private JFXTextField cjregulacion;
    @FXML
    private JFXTextField cjeficiencia;
    @FXML
    private TextArea cjobservaciones;
    @FXML
    private JFXTextField cjcliente;
    @FXML
    private TableView<DatosTablaUno> tablaUno;
    @FXML
    private TableView<DatosTablaDos> tablaDos;
    @FXML
    private TableColumn<DatosTablaUno, Integer> colPosicion;
    @FXML
    private TableColumn<DatosTablaUno, Integer> colTension;
    @FXML
    private TableColumn<DatosTablaUno, Double> colFaseU;
    @FXML
    private TableColumn<DatosTablaUno, Double> colFaseV;
    @FXML
    private TableColumn<DatosTablaUno, Double> colFaseW;
    @FXML
    private TableColumn<DatosTablaDos, Double> colNominal;
    @FXML
    private TableColumn<DatosTablaDos, Double> colMinima;
    @FXML
    private TableColumn<DatosTablaDos, Double> colMaxima;    
    @FXML
    private MenuItem menuSalir;
    @FXML
    private JFXButton btnCalcular;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private Label lblServicio;
    @FXML
    private Label lblInfo;
    @FXML
    private Label lblpcc;
    @FXML
    private JFXDatePicker cjfecha;
    @FXML
    private MenuItem menuCalcular;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                      
        try {
            Tab tabProtocolos = new Tab("Protocolos");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ListaProtocolos.fxml"));
            AnchorPane ap3 = loader.load();
            tabProtocolos.setContent(ap3);
            ListaProtocolosController lpc = loader.getController();
            lpc.setPc(this);
            tabPane.getTabs().add(tabProtocolos);
            
            Tab tabTablasMonofasicos = new Tab("Tablas Monofásicos");
            AnchorPane ap = new FXMLLoader(getClass().getResource("/view/TablasMonofasicos.fxml")).load();
            tabTablasMonofasicos.setContent(ap);
            tabPane.getTabs().add(tabTablasMonofasicos);
            
            Tab tabTablasTrifasicos = new Tab("Tablas Trifásicos");
            AnchorPane ap2 = new FXMLLoader(getClass().getResource("/view/TablasTrifasicos.fxml")).load();
            tabTablasTrifasicos.setContent(ap2);
            tabPane.getTabs().add(tabTablasTrifasicos);                        
        } catch (IOException ex) {
            Logger.getLogger(ProtocoloController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        menuCalcular.setOnAction(event->{
            btnCalcular.fire();
        });
        
        comboFases.getItems().addAll(1,3);
        comboFases.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboFases);
        
        comboServicio.getItems().addAll("NUEVO","RECONSTRUIDO","REPARADO","MANTENIMIENTO");
        comboServicio.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboServicio);
        
        comboRefrigeracion.getItems().addAll("ONAN","ONAF","AN");
        comboRefrigeracion.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboRefrigeracion);
        
        comboClaseAislamiento.getItems().addAll("Aº","A:(75ºC)","E:(85ºC)","B:(100ºC)","F:(120ºC)","H:(145ºC)");
        comboClaseAislamiento.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboClaseAislamiento);
        
        comboDerivacion.getItems().addAll("(-4)*2.5%","(+1-3)*2.5%","(+2-2)*2.5%","(+3-1)*2.5%","(+4)*2.5%");
        comboDerivacion.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboClaseAislamiento);
        
        comboConmutador.getItems().addAll(1,2,3,4,5);
        comboConmutador.getSelectionModel().selectFirst();
        
        comboAceite.getItems().addAll("MINERAL","VEGETAL","REGENERADO","SECO");
        comboAceite.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboAceite);
        
        comboReferencia.getItems().addAll("HYVOLT", "LUB TROIL TIPO II", "FR3", "INHIBIDO TIPO II", "NYTRO IZAR II", "N/A");
        comboReferencia.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboReferencia);
        
        comboTensiondeprueba.getItems().addAll("5000/500","5000/5000","500/500");
        comboTensiondeprueba.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboTensiondeprueba);
        
        comboConexion.getItems().addAll("Ii6","Ii0","DYn5","DYn11","Yy0","Yy6");
        comboConexion.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboConexion);
        
        comboPolaridad.getItems().addAll("SUSTRACTIVA","ADITIVA","NO APLICA");
        comboPolaridad.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboPolaridad);
        
        comboMaterialAlta.getItems().addAll("COBRE","ALUMINIO");
        comboMaterialAlta.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboMaterialAlta);
        
        comboMaterialBaja.getItems().addAll("COBRE","ALUMINIO");
        comboMaterialBaja.getSelectionModel().selectFirst();
        clases.Metodos.fontComboBox(comboMaterialBaja);                
        
        tablaUno.setOnKeyPressed(event -> {
            if (event.getCode().isDigitKey() || event.getCode() == KeyCode.BACK_SPACE) {
                final TablePosition focusedCell = tablaUno.focusModelProperty().get().focusedCellProperty().get();
                tablaUno.edit(focusedCell.getRow(), focusedCell.getTableColumn());
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.TAB) {
                tablaUno.getSelectionModel().selectNext();
                event.consume();
            }else if(event.getCode() == KeyCode.ENTER){
                TablePosition tp = tablaUno.getFocusModel().getFocusedCell();
                if(tp.getRow()==listaDatosTablaUno.size()-1){
                    tablaUno.getSelectionModel().select(0, tablaUno.getVisibleLeafColumn(tablaUno.getVisibleLeafIndex(tp.getTableColumn())+1));
                }else{
                    tablaUno.getSelectionModel().selectBelowCell();
                }     
                event.consume();
            }else if (event.getCode() == KeyCode.LEFT) {
                if (tablaUno.getSelectionModel().isCellSelectionEnabled()) {
                    TablePosition pos = tablaUno.getFocusModel().getFocusedCell();
                    if (pos.getColumn() - 1 >= 0) {
                        tablaUno.getSelectionModel().select(pos.getRow(), tablaUno.getVisibleLeafColumn((tablaUno.getVisibleLeafIndex(pos.getTableColumn()) + -1)));
                    } else if (pos.getRow() < tablaUno.getItems().size()) {
                        tablaUno.getSelectionModel().select(pos.getRow() - 1, tablaUno.getVisibleLeafColumn(tablaUno.getVisibleLeafColumns().size() - 1));
                    }
                } else {
                    int focusIndex = tablaUno.getFocusModel().getFocusedIndex();
                    if (focusIndex == -1) {
                        tablaUno.getSelectionModel().select(tablaUno.getItems().size() - 1);
                    } else if (focusIndex > 0) {
                        tablaUno.getSelectionModel().select(focusIndex - 1);
                    }
                }
                event.consume();
            }
        });
        
        tablaUno.setOnMouseClicked(evt->{
            if(evt.getClickCount()==2 && evt.getButton()==MouseButton.PRIMARY){
                System.out.println(evt.getSource());
            }
        });        
        
        tablaUno.setEditable(true);
        tablaUno.getSelectionModel().setCellSelectionEnabled(true);
        tablaUno.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tablaUno.setItems(listaDatosTablaUno);                                
        
        /***CONFIGURAR TABLA UNO DE LAS TENSIONES***/
        colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        colPosicion.setStyle("-fx-alignment: CENTER;");
        
        colTension.setCellValueFactory(new PropertyValueFactory<>("tension"));
        colTension.setStyle("-fx-alignment: CENTER;");
        
        colFaseU.setEditable(true);
        colFaseU.setCellValueFactory(new PropertyValueFactory<>("faseu"));
        colFaseU.setCellFactory(tc -> new clases.Celda(new clases.MyDoubleStringConverter(), tablaDos));
        
        colFaseV.setEditable(true);
        colFaseV.setCellValueFactory(new PropertyValueFactory<>("fasev"));
        colFaseV.setCellFactory(tc -> new clases.Celda(new clases.MyDoubleStringConverter(), tablaDos));
        
        colFaseW.setEditable(true);
        colFaseW.setCellValueFactory(new PropertyValueFactory<>("fasew"));
        colFaseW.setCellFactory(tc -> new clases.Celda(new clases.MyDoubleStringConverter(), tablaDos));
        
        /***CONFIGURAR TABLS DOS DE LAS NOMINALES***/
        colNominal.setCellValueFactory(new PropertyValueFactory<>("nominal"));        
        colMinima.setCellValueFactory(new PropertyValueFactory<>("minima"));
        colMaxima.setCellValueFactory(new PropertyValueFactory<>("maxima"));             
        
        UpDown(cjprotocolo, cjserie, cjprotocolo);
        UpDown(cjserie, cjempresa, cjprotocolo);
        UpDown(cjempresa, cjmarca, cjserie);
        UpDown(cjmarca, cjkva, cjempresa);                
        
        cjkva.setOnAction(event->{
            calcular(null);
        });
        cjano.setOnAction(event->{
            calcular(null);
        });
        cjvp.setOnAction(event->{
            calcular(null);
        });
        cjvs.setOnAction(event->{
            calcular(null);
        });
        cjtemperaturadeprueba.setOnAction(event->{
            calcular(null);
        });
        cjpomedido.setOnAction(event->{
            calcular(null);
        });
        cjvcc.setOnAction(event->{
            calcular(null);
        });
        cjpccmedido.setOnAction(event->{
            calcular(null);
        });
        comboConmutador.setOnAction(event->{
            calcular(null);
        });
                
        Metodos.onlyDouble(cjkva, comboFases, cjmarca);
        comboFases.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cjano.requestFocus();}});
        Metodos.onlyInteger(cjano, cjvp, cjkva);
        Metodos.onlyInteger(cjvp, cjvs, cjano);
        Metodos.onlyInteger(cjvs, comboServicio, cjvp);
        comboServicio.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){comboClaseAislamiento.requestFocus();}});
//        Metodos.onlyInteger(cjcalentamientodevanado, comboClaseAislamiento);
        comboClaseAislamiento.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cji1.requestFocus();}});
        Metodos.onlyDouble(cji1, cji2, cjvs);
        Metodos.onlyDouble(cji2, cjtemperaturadeprueba, cji1);
        Metodos.onlyInteger(cjtemperaturadeprueba, comboConmutador, cji2);
        comboConmutador.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){comboAceite.requestFocus();}});
        
        comboAceite.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){comboReferencia.requestFocus();}});
        comboReferencia.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cjruptura.requestFocus();}});                
        Metodos.onlyInteger(cjruptura, cjtiemporesistencia, cjtemperaturadeprueba);
        
        Metodos.onlyInteger(cjtiemporesistencia, cjATcontraBT, cjruptura);
        Metodos.onlyDouble(cjATcontraBT, cjATcontraTierra, cjtiemporesistencia);
        Metodos.onlyDouble(cjATcontraTierra, cjBTcontraTierra, cjATcontraBT);
        Metodos.onlyDouble(cjBTcontraTierra, comboConexion, cjATcontraTierra);
        comboConexion.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){comboPolaridad.requestFocus();}});
        comboPolaridad.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cjUV.requestFocus();}});
        
        Metodos.onlyDouble(cjUV, cjVW, cjBTcontraTierra);
        Metodos.onlyDouble(cjVW, cjWU, cjUV);
        Metodos.onlyDouble(cjWU, comboMaterialAlta, cjVW);
        Metodos.onlyDouble(cjproresalta, comboMaterialAlta, cjWU);
        comboMaterialAlta.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cjXY.requestFocus();}});        
        Metodos.onlyDouble(cjXY, cjYZ, cjproresalta);
        Metodos.onlyDouble(cjYZ, cjZX, cjXY);
        Metodos.onlyDouble(cjZX, comboMaterialBaja, cjYZ);        
        Metodos.onlyDouble(cjproresbaja, comboMaterialBaja, cjZX);
        comboMaterialBaja.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cjBTcontraATyTierra.requestFocus();}});
                
        Metodos.onlyDouble(cjBTcontraATyTierra, cjATcontraBTyTierra, cjproresbaja);
        Metodos.onlyDouble(cjATcontraBTyTierra, cjtiempoaplicado, cjBTcontraATyTierra);
        Metodos.onlyInteger(cjtiempoaplicado, cjFrecuenciaInducida, cjATcontraBTyTierra);
        //Metodos.onlyInteger(cjtensionBT, cjFrecuenciaInducida);
        Metodos.onlyInteger(cjFrecuenciaInducida, cjtiempoInducido, cjtiempoaplicado);
        Metodos.onlyInteger(cjtiempoInducido, cjiu, cjFrecuenciaInducida);
        
//        Metodos.onlyInteger(cjtensionBT2, cjiu);
        Metodos.onlyDouble(cjiu, cjiv, cjtiempoInducido);
        Metodos.onlyDouble(cjiv, cjiw, cjiu);
        Metodos.onlyDouble(cjiw, cjpromedioi, cjiv);
        Metodos.onlyDouble(cjpromedioi, cjiogarantizado, cjiw);
        Metodos.onlyDouble(cjiogarantizado, cjpomedido, cjpromedioi);
        Metodos.onlyDouble(cjpomedido, cjpogarantizado, cjiogarantizado);
        Metodos.onlyDouble(cjpogarantizado, cjvcc, cjpomedido);
        
        Metodos.onlyDouble(cjvcc, cjpccmedido, cjpogarantizado);
        Metodos.onlyDouble(cjpccmedido, cjpcca85, cjvcc);
        Metodos.onlyDouble(cjpcca85, cjpccgarantizado, cjpccmedido);
        Metodos.onlyDouble(cjpccgarantizado, cji2r, cjpcca85);
        Metodos.onlyDouble(cji2r, cji2ra85, cjpccgarantizado);
        Metodos.onlyDouble(cji2ra85, cjz, cji2r);
        Metodos.onlyDouble(cjz, cjza85, cji2ra85);
        Metodos.onlyDouble(cjza85, cjzgarantizado, cjz);
        Metodos.onlyDouble(cjzgarantizado, cjregulacion, cjza85);

        Metodos.onlyDouble(cjregulacion, cjeficiencia, cjzgarantizado);
        Metodos.onlyDouble(cjeficiencia, cjmasa, cjregulacion);
        
        Metodos.onlyInteger(cjmasa, cjaceite, cjeficiencia);
        Metodos.onlyInteger(cjaceite, cjlargo, cjmasa);
        Metodos.onlyInteger(cjlargo, cjancho, cjaceite);
        Metodos.onlyInteger(cjancho, cjalto, cjlargo);
        Metodos.onlyInteger(cjalto, cjcolor, cjancho);
        Metodos.onlyInteger(cjcolor, cjespesor, cjalto);
        Metodos.onlyInteger(cjespesor, cjelementos, cjcolor);
        Metodos.onlyInteger(cjelementos, cjlargoelemento, cjespesor);
        Metodos.onlyInteger(cjlargoelemento, cjanchoelemento, cjelementos);
        Metodos.onlyInteger(cjanchoelemento, cjcliente, cjlargoelemento);
        
        cjcliente.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cjobservaciones.requestFocus();}});                
        
        /*COPIAR AUTOMATICAMENTE EL VALOR DEL VOLTAJE SECUNDARIO EN LOS CAMPOS*/
        cjvs.textProperty().addListener((event, old, neww)->{
            cjtensionBT.setText(String.valueOf(Integer.parseInt(neww)*2));
            cjtensionBT2.setText(neww);
        });        
            
        /*HABILITAR CAMPOS SEGUN LA FASE DEL TRANSFORMADOR*/
        cjVW.editableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        cjVW.editableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        cjYZ.editableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        cjWU.editableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        cjiv.editableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        cjiw.editableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        
        comboClaseAislamiento.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> evt, Number old, Number neww) -> {
            switch(neww.intValue()){
                case 0:lblpcc.setText("Pcc a 85°(W):");
                break;
                case 1:lblpcc.setText("Pcc a 75°(W):");
                break;
                case 2:lblpcc.setText("Pcc a 85°(W):");
                break;
                case 3:lblpcc.setText("Pcc a 100°(W):");
                break;
                case 4:lblpcc.setText("Pcc a 120°(W):");
                break;
                case 5:lblpcc.setText("Pcc a 145°(W):");
                break;
            }
        });
        
        
        lblServicio.textProperty().bind(ESTADO_TRAFO);
        comboServicio.getSelectionModel().selectedItemProperty().addListener( (op,old,val) ->{
            if(val.equals("MANTENIMIENTO")){
                if(ESTADO_TRAFO.get().equals("Servicio:")){
                    ButtonType b1 = new ButtonType("ORIGINAL");
                    ButtonType b2 = new ButtonType("REPARADO");
                    Alert alert = new Alert(AlertType.CONFIRMATION, "SELECCIONE EL ESTADO DEL TRANSFORMADOR");
                    alert.getButtonTypes().clear();
                    alert.getButtonTypes().addAll(b1,b2, ButtonType.CLOSE);
                    alert.setTitle("Seleccione...");
                    while(true){
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.get()==b1){
                            setESTADO_TRAFO("Servicio:("+b1.getText()+")");                        
                            break;
                        }else if(result.get()==b2){
                            setESTADO_TRAFO("Servicio:("+b2.getText()+")");
                            ESTADO_TRAFO.set("Servicio:("+b2.getText()+")");
                            break;
                        }else if(result.get()==ButtonType.CLOSE){
                            setESTADO_TRAFO("Servicio:");
                            comboServicio.getSelectionModel().selectFirst();
                            break;
                        }
                    }
                }                                
            }else{
                ESTADO_TRAFO.set("Servicio:");
            }
        });
        
        
        cjpomedido.textProperty().addListener( (evt, old, neww)->{
            double POGARANTIZADO = getDouble(cjpogarantizado);
            if(neww!=null){
                try {
                    if(Double.parseDouble(neww)>POGARANTIZADO){
                        cjpogarantizado.setEffect(new javafx.scene.effect.DropShadow(5, Color.MAROON));
                        clases.Metodos.rotarError(cjpogarantizado);
                    }else{
                        cjpogarantizado.setEffect(null);
                    }
                }catch(NumberFormatException e){}                
            }
        });
        
        cjpccmedido.textProperty().addListener( (evt, old, neww)->{
            double I2R = getDouble(cji2r);
            if(neww!=null){
                try {
                    if(Double.parseDouble(neww)>I2R){
                        cji2r.setEffect(new javafx.scene.effect.DropShadow(5, Color.MAROON));
                        clases.Metodos.rotarError(cji2r);
                    }else{
                        cji2r.setEffect(null);
                    }
                }catch(NumberFormatException e){}                
            }
        });
        
        //AUTOMATIZAR EL COMBOBOX DE LA DERIVACION DEL CONMUTADOR AL SELECCIONAR LA POSICION DEL CONMUTADOR
        comboConmutador.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            comboDerivacion.getSelectionModel().select(newValue.intValue());
        });
        
        cjruptura.setText("40");
        cjBTcontraATyTierra.setText("10");
        cjATcontraBTyTierra.setText("34.5");
        cjtiemporesistencia.setText("60");
        cjtiempoaplicado.setText("60");
        cjtiempoInducido.setText("17");
        cjFrecuenciaInducida.setText("414");        
        cjcalentamientodevanado.setText("65");
        cjespesor.setText("100");
        cjfecha.setValue(LocalDate.now());
        
        
        cjUV.setOnAction(evt->{calcular(null);});
        cjVW.setOnAction(evt->{calcular(null);});
        cjWU.setOnAction(evt->{calcular(null);});
        cjXY.setOnAction(evt->{calcular(null);});
        cjYZ.setOnAction(evt->{calcular(null);});
        cjZX.setOnAction(evt->{calcular(null);});
        cjiu.setOnAction(evt->{calcular(null);});
        cjiv.setOnAction(evt->{calcular(null);});
        cjiw.setOnAction(evt->{calcular(null);});
    }
    
    
    void cargarTablaUno(ActionEvent evt){
        if(getInteger(cjvp)==0)return;
        double factor = 1.0;
        int pos = comboConmutador.getValue();
        if( (pos-1)>0 ){
            factor += (pos-1)*2.5/100;
        }        
        for (int i = 1; i <= 5; i++) {
            if(listaDatosTablaUno.size()==5){
                listaDatosTablaUno.get((i-1)).setTension((int) Math.round(Integer.parseInt(cjvp.getText())*factor));                
            }else{                
                DatosTablaUno tu = new DatosTablaUno();
                tu.setPosicion((i));
                tu.setTension((int) Math.round(Integer.parseInt(cjvp.getText())*factor));
                listaDatosTablaUno.add(tu);
            }
            factor -= 0.025;
        }
        tablaUno.refresh();
    }
    
    void cargarTablaDos(ActionEvent evt){
        int vs = getInteger(cjvs);
        if(vs==0)return;
        tablaDos.getItems().clear();
        tablaUno.getItems().forEach(i->{
            double multiplicador = (comboFases.getValue()==1)?1:Math.sqrt(3);
            DatosTablaDos td = new DatosTablaDos();
            td.setNominal( Math.round( ((i.getTension()*multiplicador)/vs)*1000d )/1000d );
            td.setMinima(Math.round(((((i.getTension()*multiplicador)/vs)*0.995)*1000d))/1000d);
            td.setMaxima( Math.round( (((i.getTension()*multiplicador)/vs)*1.005)*1000d )/1000d );
            tablaDos.getItems().add(td);
        });
    }        
    
    @FXML
    private void guardar(ActionEvent evt){
        Alert alerta = new Alert(AlertType.CONFIRMATION, "Desea continuar ?", ButtonType.YES, ButtonType.NO);
        if(alerta.showAndWait().get()==ButtonType.YES){
            
            String sql = "INSERT INTO protocolo (\n" +
"                          codigo, serie, empresa, marca, kva, fase, ano, vp, vs, servicio,\n" +
"                          estadoservicio, frecuencia, refrigeracion, tensionserie, nba, caldev,\n" +
"                          claseaislamiento, altdiseno, i1, i2, dervprim, temperatura, conmutador,\n" +
"                          aceite, referenciaaceite, ruptura, metodo, tiemporesistencia, tensiondeprueba,\n" +
"                          atcontrabt, atcontratierra, btcontratierra, grupodeconexion, polaridad,\n" +
"                          uv, vw, wu, proresalta, materialalta, xy, yz, zx, proresbaja, materialbaja, btcontraatytierra,\n" +
"                          atcontrabtytierra, tiempodeprueba, tensionbt, frecuencia2, tiempodeprueba2,\n" +
"                          tensionbt2, iu, iv, iw,promedioi, iogarantizado, pomedido, pogarantizado,\n" +
"                          vcc, pccmedido, pcca85, pccgarantizado, i2r, i2ra85, z, za85, zgarantizado, reg,\n" +
"                          ef, masa, volumen, largo, ancho, alto, color, espesor, elementos, largoelemento,\n" +
"                          anchoelemento, cliente, observaciones, fechadeprotocolo,\n" +
"                          punou, punov, punow, pdosu, pdosv, pdosw, ptresu, ptresv, ptresw, pcuatrou,\n" +
"                          pcuatrov, pcuatrow, pcincou, pcincov, pcincow)\n" +
"                      VALUES (\n" +
"                          '"+cjprotocolo.getText().trim()+"', '"+cjserie.getText().trim()+"',\n" +
"                          '"+cjempresa.getText().trim()+"', '"+cjmarca.getText().trim()+"',\n" +
"                          '"+cjkva.getText().trim()+"', '"+comboFases.getValue()+"',\n" +
"                          '"+cjano.getText().trim()+"', '"+cjvp.getText().trim()+"',\n" +
"                          '"+cjvs.getText().trim()+"', '"+comboServicio.getValue()+"',\n" +
"                          '"+ESTADO_TRAFO.get()+"', '"+cjfrecuencia.getText().trim()+"',\n" +
"                          '"+comboRefrigeracion.getValue()+"', '"+cjtensionserie.getText().trim()+"',\n" +
"                          '"+cjnba.getText().trim()+"', '"+cjcalentamientodevanado.getText().trim()+"',\n" +
"                          '"+comboClaseAislamiento.getValue()+"', '"+cjalturadiseno.getText().trim()+"',\n" +
"                          '"+cji1.getText().trim()+"', '"+cji2.getText().trim()+"',\n" +
"                          '"+comboDerivacion.getValue()+"', '"+cjtemperaturadeprueba.getText().trim()+"',\n" +
"                          '"+comboConmutador.getValue()+"', '"+comboAceite.getValue()+"',\n" +
"                          '"+comboReferencia.getValue()+"', '"+cjruptura.getText().trim()+"',\n" +
"                          '"+cjmetodo.getText().trim()+"', '"+cjtiemporesistencia.getText().trim()+"',\n" +
"                          '"+comboTensiondeprueba.getValue()+"', '"+cjATcontraBT.getText().trim()+"',\n" +
"                          '"+cjATcontraTierra.getText().trim()+"', '"+cjBTcontraTierra.getText().trim()+"',\n" +
"                          '"+comboConexion.getValue()+"', '"+comboPolaridad.getValue()+"',\n" +
"                          '"+cjUV.getText().trim()+"', '"+cjVW.getText().trim()+"',\n" +
"                          '"+cjWU.getText().trim()+"', '"+cjproresalta.getText().trim()+"',\n" +
"                          '"+comboMaterialAlta.getValue()+"', '"+cjXY.getText().trim()+"',\n" +
"                          '"+cjYZ.getText().trim()+"', '"+cjZX.getText().trim()+"',\n" +
"                          '"+cjproresbaja.getText().trim()+"', '"+comboMaterialBaja.getValue()+"',\n" +
"                          '"+cjBTcontraATyTierra.getText().trim()+"', '"+cjATcontraBTyTierra.getText().trim()+"',\n" +
"                          '"+cjtiempoaplicado.getText().trim()+"', '"+cjtensionBT.getText().trim()+"',\n" +
"                          '"+cjFrecuenciaInducida.getText().trim()+"', '"+cjtiempoInducido.getText().trim()+"',\n" +
"                          '"+cjtensionBT2.getText().trim()+"', '"+cjiu.getText().trim()+"',\n" +
"                          '"+cjiv.getText().trim()+"', '"+cjiw.getText().trim()+"iw',\n" +
"                          '"+cjpromedioi.getText().trim()+"', '"+cjiogarantizado.getText().trim()+"',\n" +
"                          '"+cjpomedido.getText().trim()+"', '"+cjpogarantizado.getText().trim()+"',\n" +
"                          '"+cjvcc.getText().trim()+"', '"+cjpccmedido.getText().trim()+"',\n" +
"                          '"+cjpcca85.getText().trim()+"', '"+cjpccgarantizado.getText().trim()+"',\n" +
"                          '"+cji2r.getText().trim()+"', '"+cji2ra85.getText().trim()+"',\n" +
"                          '"+cjz.getText().trim()+"', '"+cjza85.getText().trim()+"',\n" +
"                          '"+cjzgarantizado.getText().trim()+"', '"+cjregulacion.getText().trim()+"',\n" +
"                          '"+cjeficiencia.getText().trim()+"', '"+cjmasa.getText().trim()+"',\n" +
"                          '"+cjaceite.getText().trim()+"', '"+cjlargo.getText().trim()+"',\n" +
"                          '"+cjancho.getText().trim()+"', '"+cjalto.getText().trim()+"',\n" +
"                          '"+cjcolor.getText().trim()+"', '"+cjespesor.getText().trim()+"',\n" +
"                          '"+cjelementos.getText().trim()+"', '"+cjlargoelemento.getText().trim()+"',\n" +
"                          '"+cjanchoelemento.getText().trim()+"', '"+cjcliente.getText().trim()+"',\n" +
"                          '"+cjobservaciones.getText().trim()+"', '"+cjfecha.getValue()+"',\n" +
"                          '"+listaDatosTablaUno.get(0).getFaseu()+"', '"+listaDatosTablaUno.get(0).getFasev()+"',\n" +
"                          '"+listaDatosTablaUno.get(0).getFasew()+"', '"+listaDatosTablaUno.get(1).getFaseu()+"',\n" +
"                          '"+listaDatosTablaUno.get(1).getFasev()+"', '"+listaDatosTablaUno.get(1).getFasew()+"',\n" +
"                          '"+listaDatosTablaUno.get(2).getFaseu()+"', '"+listaDatosTablaUno.get(2).getFasev()+"',\n" +
"                          '"+listaDatosTablaUno.get(2).getFasew()+"', '"+listaDatosTablaUno.get(3).getFaseu()+"',\n" +
"                          '"+listaDatosTablaUno.get(3).getFasev()+"', '"+listaDatosTablaUno.get(3).getFasew()+"',\n" +
"                          '"+listaDatosTablaUno.get(4).getFaseu()+"', '"+listaDatosTablaUno.get(4).getFasev()+"',\n" +
"                          '"+listaDatosTablaUno.get(4).getFasew()+"'\n" +
"                      );";

            clases.Conexion con = new Conexion();            
            try {
                PreparedStatement pst = con.getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                if(pst.executeUpdate()>0){
                    ResultSet rs = pst.getGeneratedKeys();
                    rs.next();
                    JasperReport reporte = (JasperReport) JRLoader.loadObject(new URL(this.getClass().getResource("PROTOCOLO.jasper").toString()));         
                    Map<String, Object> p = new HashMap<>();
                    p.put("IDPROTOCOLO", rs.getInt(1));
                    JasperPrint jasperprint = JasperFillManager.fillReport(reporte, p, con.getCon());
                    JasperViewer.viewReport(jasperprint, false);
                }
            } catch (MalformedURLException | SQLException | JRException ex) {
                Logger.getLogger(ProtocoloController.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                con.cerrar();
            }
        }   
    }
    
    @FXML
    void calcular(ActionEvent evt){ 
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Faltan Datos");

            int vp = getInteger(cjvp), 
                vs = getInteger(cjvs), 
                ano = getInteger(cjano), 
                fase = comboFases.getValue(),
                temperatura = getInteger(cjtemperaturadeprueba);

            double KVA = Metodos.getDouble(cjkva), KC = 0, K = 0;

            String SERVICIO = comboServicio.getValue(), 
                   ACEITE = comboAceite.getValue(),
                   TABLA = null,
                   MSG_ERROR = "";

            if(KVA==0){
//                MSG_ERROR = "INGRESE EL VOLTAJE DE ALTA TENSIÓN\n";
                clases.Metodos.rotarError(cjkva);
                return;
            }
            if(vp==0){
//                MSG_ERROR = "INGRESE EL VOLTAJE DE ALTA TENSIÓN\n";
                clases.Metodos.rotarError(cjvp);
            }        
            if(vs == 0){
//                MSG_ERROR += "INGRESE EL VOLTAJE DE BAJA TENSIÓN\n";
                clases.Metodos.rotarError(cjvs);
            }            
            if(ano == 0){
//                MSG_ERROR += "INGRESE EL AÑO\n";
                clases.Metodos.rotarError(cjano);
            }
            if(temperatura==0){                
//                MSG_ERROR += "INGRESE LA TEMPERATURA DE PRUEBA\n";
                clases.Metodos.rotarError(cjtemperaturadeprueba);
            }

            if(ACEITE.equals("SECO")){
                TABLA = (vp<=15000)?"trifasicosecoserie1212":"trifasicosecoserie1512";
            }else{
                switch(SERVICIO){
                    case "NUEVO":
                    case "RECONSTRUIDO":
    //                    TABLA = (vp<=15000)?(fase==1)?"monofasiconuevo":"trifasiconuevo":(vp > 15000 && vp <= 35000)?(fase==1)?"monofasiconuevoserie35":"trifasiconuevoserie35":null;
                        if(vp<=15000){
                            TABLA = (fase==1)?"monofasiconuevo":"trifasiconuevo";
                        }else if(vp > 15000 && vp <= 35000){
                            TABLA = (fase==1)?"monofasiconuevoserie35":"trifasiconuevoserie35";
                        }
                        break;
                    case "REPARADO":
                        if(vp <= 15000){
                            TABLA = (fase==1)?(ano < 1996)?"monofasicoantesde1996":"monofasicodespuesde1996":(ano < 1996)?"trifasicoantesde1996":"trifasicodespuesde1996";
                        }else if (vp > 15000 && vp <= 35000){
                            TABLA = (fase==1)?(ano <= 1996)?"monofasicoantesde1996serie35":"monofasicodespuesde1996serie35":(ano < 1996)?"trifasicoantesde1996serie35":"trifasicodespuesde1996serie35";
                        }else if(vp > 35000 && vp <= 46000 && fase==3 ){
                            TABLA = "trifasicodespuesde1996serie46";
                        }
                        break;
                    case "MANTENIMIENTO":
                        if(ESTADO_TRAFO.get().contains("REPARADO")){
                            if(ano < 1996){
                                TABLA = (fase==1)?"monofasicoantesde1996":"trifasicoantesde1996";
                            }else{
                                TABLA = (fase==1)?"monofasicodespuesde1996":"trifasicodespuesde1996";                            
                            }
                        }else if(ESTADO_TRAFO.get().contains("ORIGINAL")){
                            TABLA = (fase==1)?"monofasiconuevo":"trifasiconuevo";
                        }
                        break;
                }
            }
            
            if(!MSG_ERROR.isEmpty()){
                alert.setContentText(MSG_ERROR);
                alert.show();
                return;
            }                        

            cargarTablaDos(evt);
            cargarTablaUno(evt);
            cargarTablaDos(evt);
            
            Conexion con = new Conexion();
            ResultSet crs = null;
            CachedRowSetImpl rs = null;
            try {
                String query = "SELECT * FROM "+TABLA+" ORDER BY kva ASC";
                System.out.println("Buscando KVA -> "+query);
                crs = con.getCon().createStatement().executeQuery(query);
                rs = new CachedRowSetImpl();
                rs.populate(crs);
                double anterior = 0;
                boolean esta = false;
                while(rs.next()){
                    System.out.println("VOY EN "+rs.getDouble("kva"));
                    if(rs.getDouble("kva")==KVA){
                        esta = true;break;
                    }else if( rs.getDouble("kva") < KVA ){                    
                        anterior = rs.getDouble("kva");
                    }else if( KVA < ((anterior+rs.getDouble("kva"))/2) ){                        
                        rs.previous();esta = true;
                        break;
                    }else{
                        esta = true;break;
                    }
                }
                if(!esta){
                    alert.setContentText("NO SE ENCONTRÓ EL KVA EN LAS TABLAS DE VALORES");
                    alert.show();                
                    lblInfo.setText("NO SE ENCONTRÓ EL KVA EN LAS TABLAS DE VALORES");
                    return;
                }
                lblInfo.setText("Valores de la tabla "+TABLA+" con KVA "+rs.getDouble("kva"));                
                cjiogarantizado.setText(String.valueOf(rs.getDouble("io")));
                cjpogarantizado.setText(String.valueOf(rs.getDouble("po")));
                cjzgarantizado.setText(String.valueOf(rs.getDouble("uz")));
                
                KC = (comboMaterialAlta.getValue().equals("COBRE")&& comboMaterialBaja.getValue().equals("COBRE"))?234.5:(comboMaterialAlta.getValue().equals("ALUMINIO")&& comboMaterialBaja.getValue().equals("ALUMINIO"))?225:229;
                
                switch(comboClaseAislamiento.getSelectionModel().getSelectedIndex()){
                    case 0:
                        K = (KC + 85) / (KC + temperatura);
                        cjpccgarantizado.setText(""+rs.getDouble("pc"));
                        break;
                    case 1:
                        K = (KC + 75) / (KC + temperatura);
                        cjpccgarantizado.setText(""+rs.getInt("pc75"));
                        break;
                    case 2:
                        K = (KC + 85) / (KC + temperatura);
                        cjpccgarantizado.setText(""+rs.getInt("pc85"));
                        break;
                    case 3:
                        K = (KC + 100) / (KC + temperatura);
                        cjpccgarantizado.setText(""+rs.getInt("pc100"));
                        break;
                    case 4:
                        K = (KC + 120) / (KC + temperatura);
                        cjpccgarantizado.setText(""+rs.getInt("pc120"));
                        break;
                    case 5:
                        K = (KC + 145) / (KC + temperatura);
                        cjpccgarantizado.setText(""+rs.getInt("pc145"));
                        break;
                }
            } catch (SQLException ex) {
                alert.setContentText("ERROR AL TRAER LOS VALORES GARANTIZADOS DESDE LAS TABLAS\n"+ex);
                alert.show();
                Logger.getLogger(ProtocoloController.class.getName()).log(Level.SEVERE, null, ex);                
            }finally{
                try {
                    con.getCon().close();
                    crs.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProtocoloController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String tensionserie = (7000 <= vp && vp <= 15000)?"15":(16000 <= vp && vp <= 25000)?"25":(26000 <= vp && vp <= 38000)?"38":(39000 <= vp && vp <= 52000)?"52":(vp <= 1200)?"1.2":"0" ,
                nba = (7000 <= vp && vp <= 15000)?"95":(16000 <= vp && vp <= 25000)?"125":(26000 <= vp && vp <= 38000)?"200":(39000 <= vp && vp <= 52000)?"250":(vp <= 1200)?"30":"0";
            tensionserie += (vs>1200)?"/15":"/1.2";
            nba += (vs<=1200)?"/30":(7000 <= vs && vs <= 15000)?"/95":"0";
            
            cjtensionserie.setText(tensionserie);
            cjnba.setText(nba);
            
            double i1 = ( (KVA*1000) / ( (fase==1)?1:Math.sqrt(3)) ) / vp;
            double i2 = ( (KVA*1000) / ( (fase==1)?1:Math.sqrt(3)) ) / vs;
            
            cji1.setText(""+Math.round(i1*100d)/100d);            
            cji2.setText(""+Math.round(i2*100d)/100d);
            
            double proresalta = (getDouble(cjUV)+getDouble(cjVW)+getDouble(cjWU))/((fase==1)?1:3);
            double proresbaja = (getDouble(cjXY)+getDouble(cjYZ)+getDouble(cjZX))/((fase==1)?1:3);
            
            cjproresalta.setText(""+Math.round(proresalta*10d)/10d);
            cjproresbaja.setText(""+Math.round(proresbaja*10d)/10d);
            
            double promedioi = (((getDouble(cjiu)+getDouble(cjiv)+getDouble(cjiw))/((fase==1)?1:3))/i2)*100;
            
            cjpromedioi.setText(""+Math.round(promedioi*100d)/100d);
            
            double POMEDIDO = getDouble(cjpomedido);
            if(POMEDIDO<=0){
//                alert.setContentText("Ingrese el valor de las Po Medidas(W)");
//                cjpomedido.requestFocus();
//                alert.show();
//                return;
                clases.Metodos.rotarError(cjpomedido);
            }
            
            double I2R = (fase==1)?((Math.pow(i1, 2) * proresalta) + (Math.pow(i2, 2) * (proresbaja / 1000))):
                    1.5 * ((Math.pow(i1, 2) * proresalta) + (Math.pow(i2, 2) * (proresbaja / 1000)));
            cji2r.setText(""+Math.round(I2R*100d)/100d);                        
                        
            double I2R85 = I2R * K;
            cji2ra85.setText(""+Math.round(I2R85*10d)/10d);
            
            double PCUMEDIDO = getDouble(cjpccmedido), VCC = getDouble(cjvcc);
            if(PCUMEDIDO<=0){
//                alert.setContentText("Ingrese el valor de las Pcc Medidas(W)");
//                alert.show();
//                return;
                clases.Metodos.rotarError(cjpccmedido);
            }

//            if(VCC<=0){
//                alert.setContentText("Ingrese el valor del voltaje de corto circuito");
//                alert.show();
//                return;
//            }

            double R = PCUMEDIDO / (10*KVA);
            double R85 = R * K;
            
            double Z = (VCC / vp) * 100;
            cjz.setText(""+Math.round(Z*100d)/100d);
            
            double X = Math.sqrt((Math.pow(Z, 2)) - (Math.pow(R, 2)));
            
            double Z85 = Math.sqrt((Math.pow(R85, 2)) + (Math.pow(X, 2)));
            cjza85.setText(""+Math.round(Z85*100d)/100d);
            
            double REG = Math.sqrt(R85 + Math.pow(X, 2) + (200 * R85 * 0.8) + (200 * X * 0.6) + 10000) - 100;
            REG = Math.pow(R85, 2) + Math.pow(X, 2) + 200 * R85 * 0.8 + 200 * X * 0.6 + 10000;
            REG = Math.sqrt(REG);
            REG = REG - 100;
            cjregulacion.setText(""+Math.round(REG*100d)/100d);                        
            
            double PCU85 = ((PCUMEDIDO - I2R) / K) + I2R85;
            cjpcca85.setText(""+Math.round(PCU85*100d)/100d);
            
            double EF = (0.8 * KVA * Math.pow(10, 5)) / (0.8 * KVA * Math.pow(10, 3) + POMEDIDO + PCU85);
            cjeficiencia.setText(""+Math.round(EF*100d)/100d);
        });
                
    }
    
    private void UpDown(Node n1, Node n2, Node n3){
        n1.setOnKeyPressed((evt)->{
            if(null!=evt.getCode())switch (evt.getCode()) {
                case ENTER:
                    n2.requestFocus();
                    break;
                case DOWN:
                    n2.requestFocus();
                    break;
                case UP:
                    n3.requestFocus();
                    break;
                default:
                    break;
            }
        }); 
    }
    
    public void abrirProtocolo(int idprotocolo){
        try {
            Conexion con = new Conexion();
            String sql = "SELECT * FROM protocolo WHERE idprotocolo="+idprotocolo;
            Statement st = con.getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                setACTUALIZANDO(true);
                M(cjprotocolo, "codigo", rs);
                M(cjserie, "serie", rs);
                M(cjempresa, "empresa", rs);
                M(cjmarca, "marca", rs);
                M(cjkva, "kva", rs);                
                M(comboFases, "fase", rs);
                M(cjano, "ano", rs);
                M(cjvp, "vp", rs);
                M(cjvs, "vs", rs);
                setESTADO_TRAFO(rs.getString("estadoservicio"));
                M(comboServicio, "servicio", rs);
                M(cjfrecuencia, "frecuencia", rs);
                M(comboRefrigeracion, "refrigeracion", rs);
                M(cjtensionserie, "tensionserie", rs);             
                M(cjnba, "nba", rs);
                M(cjcalentamientodevanado, "caldev", rs);
                M(comboClaseAislamiento, "claseaislamiento", rs);
                M(cjalturadiseno, "altdiseno", rs);
                M(cji1, "i1", rs);
                M(cji2, "i2", rs);
                M(comboDerivacion, "dervprim", rs);
                M(cjtemperaturadeprueba, "temperatura", rs);
                M(comboConmutador, "conmutador", rs);
                M(comboAceite, "aceite", rs);
                M(comboReferencia, "referenciaaceite", rs);
                M(cjruptura, "ruptura", rs);
                M(cjmetodo, "metodo", rs);
                M(cjtiemporesistencia, "tiemporesistencia", rs);
                M(comboTensiondeprueba, "tensiondeprueba", rs);
                M(cjATcontraBT, "atcontrabt", rs);
                M(cjATcontraTierra, "atcontratierra", rs);
                M(cjBTcontraTierra, "btcontratierra", rs);
                M(comboConexion, "grupodeconexion", rs);
                M(comboPolaridad, "polaridad", rs);
                M(cjUV, "uv", rs);
                M(cjVW, "vw", rs);
                M(cjWU, "wu", rs);                
                M(cjproresalta, "proresalta", rs);
                M(comboMaterialAlta, "materialalta", rs);
                M(cjXY, "xy", rs);
                M(cjYZ, "yz", rs);
                M(cjZX, "zx", rs);                
                M(cjproresbaja, "proresbaja", rs);
                M(comboMaterialBaja, "materialbaja", rs);
                M(cjBTcontraATyTierra, "btcontraatytierra", rs);
                M(cjATcontraBTyTierra, "atcontrabtytierra", rs);
                M(cjtiempoaplicado, "tiempodeprueba", rs);
                M(cjtensionBT, "tensionbt", rs);
                M(cjFrecuenciaInducida, "frecuencia2", rs);
                M(cjtiempoInducido, "tiempodeprueba2", rs);
                M(cjtensionBT2, "tensionbt2", rs);
                M(cjiu, "iu", rs);
                M(cjiv, "iv", rs);
                M(cjiw, "iw", rs);
                M(cjpromedioi, "promedioi", rs);
                M(cjiogarantizado, "iogarantizado", rs);
                M(cjpogarantizado, "pogarantizado", rs);
                M(cjpomedido, "pomedido", rs);
                M(cji2r, "i2r", rs);
                M(cjvcc, "vcc", rs);
                M(cjpccmedido, "pccmedido", rs);
                M(cjpcca85, "pcca85", rs);
                M(cjpccgarantizado, "pccgarantizado", rs);                
                M(cji2ra85, "i2ra85", rs);
                M(cjz, "z", rs);
                M(cjza85, "za85", rs);
                M(cjzgarantizado, "zgarantizado", rs);
                M(cjregulacion, "reg", rs);
                M(cjeficiencia, "ef", rs);
                M(cjmasa, "masa", rs);
                M(cjaceite, "volumen", rs);
                M(cjlargo, "largo", rs);
                M(cjancho, "ancho", rs);
                M(cjalto, "alto", rs);
                M(cjcolor, "color", rs);
                M(cjespesor, "espesor", rs);
                M(cjelementos, "elementos", rs);
                M(cjlargoelemento, "largoelemento", rs);
                M(cjanchoelemento, "anchoelemento", rs);
                M(cjcliente, "cliente", rs);
                M(cjobservaciones, "observaciones", rs);
                cjfecha.setValue(LocalDate.parse(rs.getString("fechadeprotocolo")));
                
                cargarTablaDos(null);
                cargarTablaUno(null);
                
                listaDatosTablaUno.get(0).setFaseu(rs.getDouble("punou"));
                listaDatosTablaUno.get(0).setFasev(rs.getDouble("punov"));
                listaDatosTablaUno.get(0).setFasew(rs.getDouble("punow"));
                
                listaDatosTablaUno.get(1).setFaseu(rs.getDouble("pdosu"));
                listaDatosTablaUno.get(1).setFasev(rs.getDouble("pdosv"));
                listaDatosTablaUno.get(1).setFasew(rs.getDouble("pdosw"));
                
                listaDatosTablaUno.get(2).setFaseu(rs.getDouble("ptresu"));
                listaDatosTablaUno.get(2).setFasev(rs.getDouble("ptresv"));
                listaDatosTablaUno.get(2).setFasew(rs.getDouble("ptresw"));
                
                listaDatosTablaUno.get(3).setFaseu(rs.getDouble("pcuatrou"));
                listaDatosTablaUno.get(3).setFasev(rs.getDouble("pcuatrov"));
                listaDatosTablaUno.get(3).setFasew(rs.getDouble("pcuatrow"));
                
                listaDatosTablaUno.get(4).setFaseu(rs.getDouble("pcincou"));
                listaDatosTablaUno.get(4).setFasev(rs.getDouble("pcincov"));
                listaDatosTablaUno.get(4).setFasew(rs.getDouble("pcincow"));

                tabPane.getSelectionModel().selectFirst();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProtocoloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void M(Node node, String str, ResultSet rs) throws SQLException{
        if(node instanceof TextField){
            ((TextField)node).setText(rs.getString(str));
        }else if(node instanceof ComboBox){
            ((ComboBox)node).getSelectionModel().select(rs.getObject(str));
        }
    }
    
    public void showTabPane(int index){
        this.tabPane.getSelectionModel().select(index);
    }
    
    public String getESTADO_TRAFO() {
        return ESTADO_TRAFO.get();
    }

    public void setESTADO_TRAFO(String value) {
        ESTADO_TRAFO.set(value);
    }

    public StringProperty ESTADO_TRAFOProperty() {
        return ESTADO_TRAFO;
    }
    
    public boolean isACTUALIZANDO() {
        return ACTUALIZANDO.get();
    }

    public void setACTUALIZANDO(boolean value) {
        ACTUALIZANDO.set(value);
    }

    public BooleanProperty ACTUALIZANDOProperty() {
        return ACTUALIZANDO;
    }
}
