package newpackage;

import clases.Conexion;
import clases.Metodos;
import static clases.Metodos.getDouble;
import clases.TablaDos;
import clases.TablaUno;
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
import com.sun.rowset.CachedRowSetImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ProtocoloController implements Initializable {

    StringProperty ESTADO_TRAFO = new SimpleStringProperty("Servicio");
    
    @FXML
    private StackPane rootPane;
    @FXML
    private BorderPane contenedor;
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
    private TableView<TablaUno> tablaUno;
    @FXML
    private TableView<TablaDos> tablaDos;
    @FXML
    private TableColumn<TablaUno, Integer> colPosicion;
    @FXML
    private TableColumn<TablaUno, Integer> colTension;
    @FXML
    private TableColumn<TablaUno, Double> colFaseU;
    @FXML
    private TableColumn<TablaUno, Double> colFaseV;
    @FXML
    private TableColumn<TablaUno, Double> colFaseW;
    @FXML
    private TableColumn<TablaDos, Double> colNominal;
    @FXML
    private TableColumn<TablaDos, Double> colMinima;
    @FXML
    private TableColumn<TablaDos, Double> colMaxima;    
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
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {                
        
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
        
        clases.Metodos.configTable(tablaUno);
        clases.Metodos.configTable(tablaDos);
        
        /***CONFIGURAR TABLA UNO DE LAS TENSIONES***/
        /**/colPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
        /**/colTension.setCellValueFactory(new PropertyValueFactory<>("tension"));
        /**/colFaseU.setCellValueFactory(new PropertyValueFactory<>("faseu"));
        /**/colFaseV.setCellValueFactory(new PropertyValueFactory<>("fasev"));
        /**/colFaseW.setCellValueFactory(new PropertyValueFactory<>("fasew"));
        
        /***CONFIGURAR TABLS DOS DE LAS NOMINALES***/
        colNominal.setCellValueFactory(new PropertyValueFactory<>("nominal"));        
        colMinima.setCellValueFactory(new PropertyValueFactory<>("minima"));
        colMaxima.setCellValueFactory(new PropertyValueFactory<>("maxima"));
        colMaxima.setCellFactory(tc -> new TableCell<TablaDos, Double>(){
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty){
                    setText(new DecimalFormat("#.000").format(item));
                }
            }            
        });
        
        UpDown(cjprotocolo, cjserie, cjprotocolo);
        UpDown(cjserie, cjempresa, cjprotocolo);
        UpDown(cjempresa, cjmarca, cjserie);
        UpDown(cjmarca, cjkva, cjempresa);                
        
        Metodos.onlyDouble(cjkva, comboFases, cjmarca);
        comboFases.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cjano.requestFocus();}});
        Metodos.onlyInteger(cjano, cjvp, comboFases);
        Metodos.onlyInteger(cjvp, cjvs, cjano);
        Metodos.onlyInteger(cjvs, comboServicio, cjvp);
        comboServicio.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){comboClaseAislamiento.requestFocus();}});
//        Metodos.onlyInteger(cjcalentamientodevanado, comboClaseAislamiento);
        comboClaseAislamiento.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cji1.requestFocus();}});
        Metodos.onlyDouble(cji1, cji2, cjalturadiseno);
        Metodos.onlyDouble(cji2, cjtemperaturadeprueba, cji1);
        Metodos.onlyInteger(cjtemperaturadeprueba, comboConmutador, cji2);
        comboConmutador.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){comboAceite.requestFocus();}});
        
        comboAceite.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){comboReferencia.requestFocus();}});
        comboReferencia.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cjruptura.requestFocus();}});                
        Metodos.onlyInteger(cjruptura, cjtiemporesistencia, comboReferencia);
        
        Metodos.onlyInteger(cjtiemporesistencia, cjATcontraBT, cjruptura);
        Metodos.onlyDouble(cjATcontraBT, cjATcontraTierra, comboTensiondeprueba);
        Metodos.onlyDouble(cjATcontraTierra, cjBTcontraTierra, cjATcontraBT);
        Metodos.onlyDouble(cjBTcontraTierra, comboConexion, cjATcontraTierra);
        comboConexion.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){comboPolaridad.requestFocus();}});
        comboPolaridad.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cjUV.requestFocus();}});
        
        Metodos.onlyDouble(cjUV, cjVW, comboPolaridad);
        Metodos.onlyDouble(cjVW, cjWU, cjUV);
        Metodos.onlyDouble(cjWU, comboMaterialAlta, cjVW);
        comboMaterialAlta.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cjXY.requestFocus();}});
        Metodos.onlyDouble(cjproresalta, null, null);
        Metodos.onlyDouble(cjXY, cjYZ, comboMaterialAlta);
        Metodos.onlyDouble(cjYZ, cjZX, cjXY);
        Metodos.onlyDouble(cjZX, comboMaterialBaja, cjYZ);
        comboMaterialBaja.setOnKeyPressed((evt)->{if(evt.getCode()==KeyCode.ENTER){cjBTcontraATyTierra.requestFocus();}});
        Metodos.onlyDouble(cjproresbaja, null, null);
                
        Metodos.onlyDouble(cjBTcontraATyTierra, cjATcontraBTyTierra, cjBTcontraATyTierra);
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
        cjvs.textProperty().bindBidirectional(cjtensionBT.textProperty());
        cjvs.textProperty().bindBidirectional(cjtensionBT2.textProperty());
            
        /*HABILITAR CAMPOS SEGUN LA FASE DEL TRANSFORMADOR*/
        cjVW.disableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        cjVW.disableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        cjYZ.disableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        cjWU.disableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        cjiv.disableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        cjiw.disableProperty().bind(comboFases.valueProperty().isEqualTo(3));
        
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
                ButtonType b1 = new ButtonType("ORIGINAL");
                ButtonType b2 = new ButtonType("REPARADO");
                Alert alert = new Alert(AlertType.CONFIRMATION, "SELECCIONE EL ESTADO DEL TRANSFORMADOR");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(b1,b2, ButtonType.CLOSE);
                alert.setTitle("Seleccione...");
                while(true){
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get()==b1){                        
                        ESTADO_TRAFO.set("Servicio:("+b1.getText()+")");
                        break;
                    }else if(result.get()==b2){
                        ESTADO_TRAFO.set("Servicio:("+b2.getText()+")");
                        break;
                    }else if(result.get()==ButtonType.CLOSE){
                        ESTADO_TRAFO.set("Servicio:");
                        comboServicio.getSelectionModel().selectFirst();
                        break;
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
    }
    
    
    void cargarTablaUno(ActionEvent evt){
        if(getInteger(cjvp)==0)return;
        double factor = 1.0;
        int pos = comboConmutador.getValue();
        if( (pos-1)>0 ){
            factor += (pos-1)*2.5/100;
        }
        tablaUno.getItems().clear();
        for (int i = 1; i <= 5; i++) {
            TablaUno tu = new TablaUno();
            tu.setPosicion((i));
            tu.setTension((int) Math.round(Integer.parseInt(cjvp.getText())*factor));
            factor -= 0.025;
            tablaUno.getItems().add(tu);
        }        
    }
    
    void cargarTablaDos(ActionEvent evt){
        int vs = getInteger(cjvs);
        if(vs==0)return;
        tablaDos.getItems().clear();
        tablaUno.getItems().forEach(i->{
            double multiplicador = (comboConmutador.getValue()==1)?1:Math.sqrt(3);
            TablaDos td = new TablaDos();
            td.setNominal( Math.round( ((i.getTension()*multiplicador)/vs)*1000d )/1000d );
            td.setMinima(Math.round(((((i.getTension()*multiplicador)/vs)*0.995)*1000d))/1000d);
            td.setMaxima( Math.round( (((i.getTension()*multiplicador)/vs)*1.005)*1000d )/1000d );
            tablaDos.getItems().add(td);
        });
    }
    
    @FXML
    void calcular(ActionEvent evt){
        
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(null);

            int vp = getInteger(cjvp), 
                vs = getInteger(cjvs), 
                ano = getInteger(cjano), 
                fase = comboFases.getValue(),
                temperatura = getInteger(cjtemperaturadeprueba);

            double KVA = Metodos.getDouble(cjkva), KC = 0, K = 0;

            String SERVICIO = comboServicio.getValue(), 
                   ACEITE = comboAceite.getValue(),
                   TABLA = null;

            if(vp==0){            
                alert.setContentText("INGRESE EL VOLTAJE DE ALTA TENSIÓN");
            }        
            if(vs == 0){
                alert.setContentText("INGRESE EL VOLTAJE DE BAJA TENSIÓN");
            }
            if(ano == 0){
                alert.setContentText("INGRESE EL AÑO");
            }
            if(temperatura==0){            
                alert.setContentText("INGRESE LA TEMPERATURA DE PRUEBA");
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
            
            if(alert.getContentText()!=null){
                alert.show();
                return;
            }

            Conexion con = new Conexion();
            ResultSet crs = null;
            CachedRowSetImpl rs = null;
            try {
                crs = con.getCon().createStatement().executeQuery("SELECT * FROM "+TABLA+" ORDER BY kva ASC");
                rs = new CachedRowSetImpl();
                rs.populate(crs);
                double anterior = 0;
                while(rs.next()){
                    System.out.println("VOY EN "+rs.getDouble("kva"));
                    if(rs.getDouble("kva")==KVA){
                        break;
                    }else if( rs.getDouble("kva") < KVA ){                    
                        anterior = rs.getDouble("kva");
                    }else if( KVA < ((anterior+rs.getDouble("kva"))/2) ){                        
                        rs.previous();
                        break;
                    }else{
                        break;
                    }
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
                alert.setContentText("ERROR AL TRAER LOS VALORES GARANTIZADOS DESDE LA BASE DE DATOS\n"+ex);
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
            
            cjproresalta.setText(""+proresalta);
            cjproresbaja.setText(""+proresbaja);
            
            double promedioi = (((getDouble(cjiu)+getDouble(cjiv)+getDouble(cjiw))/((fase==1)?1:3))/i2)*100;
            
            cjpromedioi.setText(""+Math.round(promedioi*100d)/100d);
            
            double I2R = (fase==1)?((Math.pow(i1, 2) * proresalta) + (Math.pow(i2, 2) * (proresbaja / 1000))):
                    1.5 * ((Math.pow(i1, 2) * proresalta) + (Math.pow(i2, 2) * (proresbaja / 1000)));
            cji2r.setText(""+Math.round(I2R*100d)/100d);                        
                        
            double I2R85 = I2R * K;
            cji2ra85.setText(""+Math.round(I2R85*10d)/10d);
            
            double PCUMEDIDO = getDouble(cjpccmedido), VCC = getDouble(cjvcc);
            if(PCUMEDIDO<=0){
                alert.setContentText("Ingrese el valor de las Pcc Medidas(W)");
                alert.show();
            }
            if(VCC<=0){
                alert.setContentText("Ingrese el valor del voltaje de corto circuito");
                alert.show();
            }
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
            
            double POMEDIDO = getDouble(cjpomedido);
            if(POMEDIDO<=0){
                alert.setContentText("Ingrese el valor de las Po Medidas(W)");
                alert.show();
            }
            
            double PCU85 = ((PCUMEDIDO - I2R) / K) + I2R85;
            cjpcca85.setText(""+Math.round(PCU85*100d)/100d);
            
            double EF = (0.8 * KVA * Math.pow(10, 5)) / (0.8 * KVA * Math.pow(10, 3) + POMEDIDO + PCU85);
            
            cargarTablaUno(evt);
            cargarTablaDos(evt);
        });
                
    }
    
    public void UpDown(Node n1, Node n2, Node n3){
        n1.setOnKeyPressed((evt)->{            
            if(evt.getCode()==KeyCode.ENTER||evt.getCode()==KeyCode.DOWN){
                n2.requestFocus();
            }else if(evt.getCode()==KeyCode.UP){
                n3.requestFocus();
            }
        }); 
    }
    
}
