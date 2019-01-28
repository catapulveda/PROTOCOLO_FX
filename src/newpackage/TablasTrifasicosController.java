package newpackage;

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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class TablasTrifasicosController implements Initializable{

    @FXML
    private TableView<Datos> antesde1996;
    @FXML
    private TableView<Datos> despuesde1996;
    @FXML
    private TableView<Datos> antesde1996serie35;
    @FXML
    private TableView<Datos> despuesde1996serie46;
    @FXML
    private TableView<Datos> nuevos;
    @FXML
    private TableView<Datos> nuevoserie35;
    @FXML
    private TableView<Datos> secoserie1212;
    @FXML
    private TableView<Datos> secoserie1512;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        loadData("trifasicoantesde1996", antesde1996, false);
        loadData("trifasicoantesde1996serie35", antesde1996serie35, false);
        loadData("trifasiconuevo", nuevos, false);
        loadData("trifasicosecoserie1212", secoserie1212, true);
        
        loadData("trifasicodespuesde1996", despuesde1996, false);
        loadData("trifasicodespuesde1996serie46", despuesde1996serie46, false);
        loadData("trifasiconuevoserie35", nuevoserie35, false);
        loadData("trifasicosecoserie1512", secoserie1512, true);                
    }
    
    private void loadData(String tabla, TableView<Datos> table, boolean seco){
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
                datos.setIo(rs.getDouble("io"));
                datos.setUz(rs.getDouble("uz"));
                if(seco){                    
                    datos.setPc75(rs.getDouble("pc75"));
                    datos.setPc(rs.getDouble("pc85"));
                    datos.setPc100(rs.getDouble("pc100"));
                    datos.setPc120(rs.getDouble("pc120"));
                    datos.setPc145(rs.getDouble("pc145"));
                }else{
                    datos.setPc(rs.getDouble("pc"));
                }
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
            
            if(seco){
                table.getColumns().get(5).setCellValueFactory((param)->{
                    return new SimpleObjectProperty(""+param.getValue().getPc75());
                });
                table.getColumns().get(6).setCellValueFactory((param)->{
                    return new SimpleObjectProperty(""+param.getValue().getPc100());
                });
                table.getColumns().get(7).setCellValueFactory((param)->{
                    return new SimpleObjectProperty(""+param.getValue().getPc120());
                });
                table.getColumns().get(8).setCellValueFactory((param)->{
                    return new SimpleObjectProperty(""+param.getValue().getPc145());
                });
            }
            
            table.getColumns().forEach(c->{
                changeSizeOnColumn(c, table);
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(TablasTrifasicosController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrar();
        }
    }
    
    public static void changeSizeOnColumn(TableColumn tc, TableView table) {
        try {
            Text t = new Text( ((tc.getText()==null)?"":tc.getText()) );
            double dPixWidth = t.getLayoutBounds().getWidth() + 10;

            int iCount = table.getItems().size(); // # of rows

            for (int i = 0; i < iCount; i++) {                
                String sTest = (tc.getCellData(i)==null)?"":tc.getCellData(i).toString();   // Get string data entry
                t = new Text();
                t.setText(sTest);
                if (t.getLayoutBounds().getWidth() > dPixWidth) {
                    dPixWidth = t.getLayoutBounds().getWidth()+10; // Get pixel width
                }
            }
            tc.setPrefWidth(dPixWidth + 8); // <= It needs 8-10 more to work.  Not sure why...
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    class Datos{

        private final DoubleProperty kva = new SimpleDoubleProperty();
        private final DoubleProperty po = new SimpleDoubleProperty();
        private final DoubleProperty pc = new SimpleDoubleProperty();
        private final DoubleProperty io = new SimpleDoubleProperty();
        private final DoubleProperty uz = new SimpleDoubleProperty();
        private final DoubleProperty pc75 = new SimpleDoubleProperty();
        private final DoubleProperty pc100 = new SimpleDoubleProperty();
        private final DoubleProperty pc120 = new SimpleDoubleProperty();
        private final DoubleProperty pc145 = new SimpleDoubleProperty();

        public double getPc145() {
            return pc145.get();
        }

        public void setPc145(double value) {
            pc145.set(value);
        }

        public DoubleProperty pc145Property() {
            return pc145;
        }
        

        public double getPc120() {
            return pc120.get();
        }

        public void setPc120(double value) {
            pc120.set(value);
        }

        public DoubleProperty pc120Property() {
            return pc120;
        }
        

        public double getPc100() {
            return pc100.get();
        }

        public void setPc100(double value) {
            pc100.set(value);
        }

        public DoubleProperty pc100Property() {
            return pc100;
        }
        

        public double getPc75() {
            return pc75.get();
        }

        public void setPc75(double value) {
            pc75.set(value);
        }

        public DoubleProperty pc75Property() {
            return pc75;
        }
        

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
