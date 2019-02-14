package view;

import clases.Conexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.kordamp.ikonli.javafx.FontIcon;
import static view.TablasTrifasicosController.changeSizeOnColumn;

public class ListaProtocolosController implements Initializable {

    @FXML
    private TableView<Protocolo> tabla;
    @FXML
    private TableColumn<Protocolo, String> colCliente;
    @FXML
    private TableColumn<Protocolo, String> colSerie;
    @FXML
    private TableColumn colKva;
    @FXML
    private TableColumn<Protocolo, String> coltension;
    @FXML
    private TableColumn<Protocolo, String> colMarca;
    @FXML
    private TableColumn<Protocolo, String> colServicio;
    @FXML
    private TableColumn<Protocolo, LocalDate> colFecha;

    private ProtocoloController pc;
    @FXML
    private TableColumn<Protocolo, String> colProtocolo;
    @FXML
    private Button btnRefrescar;
    
    private MenuItem menuBorrar = new MenuItem("Eliminar", new FontIcon("fa-trash"));
    private ContextMenu menuTabla = new ContextMenu(menuBorrar);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colCliente.setCellValueFactory(tc->tc.getValue().clienteProperty());
        colProtocolo.setCellValueFactory(tc->tc.getValue().noprotocoloProperty());
        colSerie.setCellValueFactory(tc->tc.getValue().serieProperty());
        colKva.setCellValueFactory(new PropertyValueFactory<>("kva"));
        coltension.setCellValueFactory(tc->tc.getValue().tensionProperty());
        colMarca.setCellValueFactory(tc->tc.getValue().marcaProperty());
        colServicio.setCellValueFactory(tc->tc.getValue().servicioProperty());
        colFecha.setCellValueFactory(tc->tc.getValue().fechaProperty());
        
        tabla.setRowFactory(tv -> {
            TableRow<Protocolo> row = new TableRow<>();
            row.contextMenuProperty().bind(Bindings.when(row.emptyProperty()).then((ContextMenu)null).otherwise(menuTabla));
            return row;
        });
        
        menuBorrar.setOnAction(evt->{
            Protocolo p = tabla.getSelectionModel().getSelectedItem();
            if(p==null){
                return;
            }
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea eliminar el protocolo seleccionado?", ButtonType.YES, ButtonType.NO);
            if(a.showAndWait().get()==ButtonType.YES){
                Conexion con = new Conexion();
                try {
                    if(con.getCon().createStatement().executeUpdate("DELETE FROM protocolo WHERE idprotocolo="+p.getIdprotocolo())>0){
                        tabla.getItems().remove(tabla.getSelectionModel().getSelectedIndex());
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ListaProtocolosController.class.getName()).log(Level.SEVERE, null, ex);                    
                }
            }
        });        
        
        cargarDatos();
    }
    
    @FXML
    private void abrirProtocolo(MouseEvent evt){
        if(evt.getClickCount()==2&&evt.getButton()==MouseButton.PRIMARY){            
            pc.abrirProtocolo(tabla.getSelectionModel().getSelectedItem().getIdprotocolo());
        }
    }
    
    @FXML 
    private void recargarTabla(ActionEvent evt){
        cargarDatos();
    }
    
    private void cargarDatos(){
        if(tabla.getItems().size()>0){
            tabla.getItems().clear();
        }
        try {
            Conexion con = new Conexion();
            String sql = "SELECT * FROM protocolo ORDER BY fechaderegistro DESC";
            Statement st = con.getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Protocolo p = new Protocolo();
                p.setIdprotocolo(rs.getInt("idprotocolo"));
                p.setCliente(rs.getString("cliente"));
                p.setNoprotocolo(rs.getString("codigo"));
                p.setSerie(rs.getString("serie"));
                p.setKva(rs.getDouble("kva"));
                p.setTension(rs.getString("vp")+" / "+rs.getString("vs"));
                p.setMarca(rs.getString("marca"));
                p.setServicio(rs.getString("servicio"));            
                p.setFecha(LocalDate.parse(rs.getString("fechaderegistro"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                tabla.getItems().add(p);
            }
            con.cerrar();           

            tabla.getColumns().forEach(c->{
                changeSizeOnColumn(c, tabla);
            });
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }        
    }

    public ProtocoloController getPc() {
        return pc;
    }

    public void setPc(ProtocoloController pc) {
        this.pc = pc;
    }
    
    public class Protocolo{

        private final IntegerProperty idprotocolo = new SimpleIntegerProperty();
        private final StringProperty cliente = new SimpleStringProperty();
        private final StringProperty noprotocolo = new SimpleStringProperty();
        private final StringProperty serie = new SimpleStringProperty();
        private final DoubleProperty kva = new SimpleDoubleProperty();
        private final StringProperty tension = new SimpleStringProperty();
        private final StringProperty marca = new SimpleStringProperty();
        private final StringProperty servicio = new SimpleStringProperty();
        private final ObjectProperty<LocalDate> fecha = new SimpleObjectProperty<>();

        public LocalDate getFecha() {
            return fecha.get();
        }

        public void setFecha(LocalDate value) {
            fecha.set(value);
        }

        public ObjectProperty fechaProperty() {
            return fecha;
        }        

        public String getServicio() {
            return servicio.get();
        }

        public void setServicio(String value) {
            servicio.set(value);
        }

        public StringProperty servicioProperty() {
            return servicio;
        }        

        public String getMarca() {
            return marca.get();
        }

        public void setMarca(String value) {
            marca.set(value);
        }

        public StringProperty marcaProperty() {
            return marca;
        }       

        public String getTension() {
            return tension.get();
        }

        public void setTension(String value) {
            tension.set(value);
        }

        public StringProperty tensionProperty() {
            return tension;
        }
        

        public double getKva() {
            return kva.get();
        }

        public void setKva(double value) {
            kva.set(value);
        }

        public DoubleProperty kvaProperty() {
            return kva;
        }
        

        public String getSerie() {
            return serie.get();
        }

        public void setSerie(String value) {
            serie.set(value);
        }

        public StringProperty serieProperty() {
            return serie;
        }
        

        public String getCliente() {
            return cliente.get();
        }

        public void setCliente(String value) {
            cliente.set(value);
        }

        public StringProperty clienteProperty() {
            return cliente;
        }
        
        public int getIdprotocolo() {
            return idprotocolo.get();
        }

        public void setIdprotocolo(int value) {
            idprotocolo.set(value);
        }

        public IntegerProperty idprotocoloProperty() {
            return idprotocolo;
        }
        
        public String getNoprotocolo() {
            return noprotocolo.get();
        }

        public void setNoprotocolo(String value) {
            noprotocolo.set(value);
        }

        public StringProperty noprotocoloProperty() {
            return noprotocolo;
        }
        
    }
    
}
