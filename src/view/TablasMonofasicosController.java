package view;

import clases.Conexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class TablasMonofasicosController implements Initializable {

    @FXML
    private TableView<Datos> antesde1996;
    @FXML
    private TableView<Datos> despuesde1996;
    @FXML
    private TableView<Datos> antesde1996serie35;
    @FXML
    private TableView<Datos> despuesde1996serie35;
    @FXML
    private TableView<Datos> nuevos;
    @FXML
    private TableView<Datos> nuevosserie35;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData("monofasicoantesde1996", antesde1996);
        loadData("monofasicodespuesde1996", despuesde1996);
        loadData("monofasicoantesde1996serie35", antesde1996serie35);
        loadData("monofasicodespuesde1996serie35", despuesde1996serie35);
        loadData("monofasiconuevo", nuevos);
        loadData("monofasiconuevoserie35", nuevosserie35);
    }
    
    private void loadData(String tabla, TableView<Datos> table){
        final ObservableList<Datos> data = FXCollections.observableArrayList();
        Conexion con = new Conexion();
        try {            
            String sql = "SELECT * FROM "+tabla+" ORDER BY kva ASC;";
            Statement st  = con.getCon().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Datos datos = new Datos();
                datos.setKva(rs.getDouble("kva"));
                datos.setPo(rs.getDouble("po"));
                datos.setPc(rs.getDouble("pc"));
                datos.setIo(rs.getDouble("io"));
                datos.setUz(rs.getDouble("uz"));
                
                data.add(datos);
            }
           
            table.setItems(data);                        
            
            table.getColumns().get(0).setCellValueFactory((param)->{
                return new SimpleObjectProperty(""+param.getValue().getKva());
            });
            table.getColumns().get(1).setCellValueFactory((param)->{
                return new SimpleObjectProperty(""+param.getValue().getPo());
            });
            table.getColumns().get(2).setCellValueFactory((param)->{
                return new SimpleObjectProperty(""+param.getValue().getPc());
            });
            table.getColumns().get(3).setCellValueFactory((param)->{
                return new SimpleObjectProperty(""+param.getValue().getIo());
            });
            table.getColumns().get(4).setCellValueFactory((param)->{
                return new SimpleObjectProperty(""+param.getValue().getUz());
            });
            table.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(TablasMonofasicosController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrar();
        }
    }
    
    class Datos{

        private final DoubleProperty kva = new SimpleDoubleProperty();
        private final DoubleProperty po = new SimpleDoubleProperty();
        private final DoubleProperty pc = new SimpleDoubleProperty();
        private final DoubleProperty io = new SimpleDoubleProperty();
        private final DoubleProperty uz = new SimpleDoubleProperty();

        public double getUz() {
            return uz.get();
        }

        public void setUz(double value) {
            uz.set(value);
        }

        public DoubleProperty uzProperty() {
            return uz;
        }        

        public double getIo() {
            return io.get();
        }

        public void setIo(double value) {
            io.set(value);
        }

        public DoubleProperty ioProperty() {
            return io;
        }
        

        public double getPc() {
            return pc.get();
        }

        public void setPc(double value) {
            pc.set(value);
        }

        public DoubleProperty pcProperty() {
            return pc;
        }
        

        public double getPo() {
            return po.get();
        }

        public void setPo(double value) {
            po.set(value);
        }

        public DoubleProperty poProperty() {
            return po;
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
        
    }
}
