package finalexamen;
import Registros.Registros;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
public class VistaController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TableView<Registros> tblRegistro;
    @FXML
    private TableColumn<Registros, String> columnnombre;
    @FXML
    private TableColumn<Registros, String> columnapellido;
    @FXML
    private TableColumn< Registros, Integer> columnci;
    @FXML
    private TableColumn<Registros, String> columntelefono;
    @FXML
    private TableColumn<Registros, String> columnciudad;
    @FXML
    private TableColumn<Registros, Double> columnsexo;
    @FXML
    private TableColumn<Registros, String> columnfecha;
    @FXML
    private Button btnguardar;
    @FXML
    private Button btnmodificar;
    @FXML
    private Button btneliminar;
    @FXML 
    private Button btnnuevo;
    @FXML
    private TextField txfbuscar;
    @FXML
    private TextField txfnombre;
    @FXML
    private TextField txfapellido;
    @FXML
    private TextField txfci;
    @FXML
    private TextField txftelefono;
    @FXML 
    private TextField txfciudad;
    @FXML
    private RadioButton rddM;
    @FXML
    private RadioButton rdbF;
    private final ObservableList<Registros> registroData = FXCollections.observableArrayList();
    private final List<Registros> ListRegistros = new ArrayList<>();
    private Registros r = new Registros();
    Boolean istadoBtn = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       Platform.runLater(() -> {
            txfnombre.requestFocus();
        });
       Platform.runLater(() -> {
            txfnombre.requestFocus();
        });
        tblRegistro.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue)-> modificarRegistro(newValue));
        inicializarTabla();
    }    
    private void stadobtnguardar(){
        if (tblRegistro.getSelectionModel().getSelectedItem()!=null) {
            istadoBtn=true;
          
        }else{
            istadoBtn = false;
           
        }
        
    }
    
    private void modificarRegistro(Registros newValue) {
        Registros Registro = null;
        if (Registro!=null) {
            r=Registro;
            this.stadobtnguardar();
            System.out.println("Limpiando campos");
            this.limpiarCampos();
            txfnombre.setText(Registro.getNombre());
            txfapellido.setText(Registro.getApellidos());
            txftelefono.setText(Registro.getTelefono());
            txfciudad.setText(Registro.getCiudad());
            if (Registro.getCi() != null) {
                txfci.setText(Registro.getCi().toString());
            }else{
                System.out.println("Ci vacio o null");
            }
            
        }
    }

    private void inicializarTabla() {
        columnnombre.setCellValueFactory(new  PropertyValueFactory<> ("nombre"));
        columnapellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columntelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnciudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        columnci.setCellValueFactory(new PropertyValueFactory<>("ci"));
        
        FilteredList<Registros> filteredData = new FilteredList<>(registroData, r -> true);
        txfbuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (person.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (person.getApellidos().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Registros> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblRegistro.comparatorProperty());
        tblRegistro.setItems(sortedData);
    }
    public void limpiarCampos(){
        txfapellido.clear();
        txfbuscar.clear();
        txfci.clear();
        txfciudad.clear();
        txfnombre.clear();
        txftelefono.clear();
        istadoBtn = false;
        Platform.runLater(() -> {
            txfnombre.requestFocus();
        });
    }
    @FXML
    private void onActionlimpiarCampos(ActionEvent event){
        this.limpiarCampos();
       this.stadobtnguardar();
        istadoBtn= false;
       btnguardar.setText("Registrar datos");
    }
   
    @FXML
    private void onActioEliminarDatos(){
        Integer var1 =0;
        Registros per = tblRegistro.getSelectionModel().getSelectedItem();
        if (per!=null) {
            registroData.remove(per);
        }else{
            System.out.println("Debes seleccionar un elemento de la tabla!!");
        }
    }
    
    @FXML
    private void guardarDatos(ActionEvent event) {
        ListRegistros.clear();
        Registros per = tblRegistro.getSelectionModel().getSelectedItem();
        Integer var = tblRegistro.getSelectionModel().getSelectedIndex();
        if (per == r) {
            r.setNombre(txfnombre.getText());
            String apellido = txfapellido.getText();
            r.setApellido(apellido);
            String telefono = txftelefono.getText();
            r.setTelefono(telefono);
            String ciudad = txfciudad.getText();
            r.setCiudad(ciudad);
            r.setSexo(istadoBtn);
            
            try {
                Integer ci = Integer.parseInt(txfci.getText());
                r.setCi(ci);
            } catch (NumberFormatException e) {
                System.out.println("Error al cargar ci numero");
            }
            
            ListRegistros.add(r);
            registroData.remove(per);
            registroData.addAll(ListRegistros);
        } else {
            this.aniadirRegistros();
        }
        Platform.runLater(() -> {
            this.limpiarCampos();
        });
        
    }
    

    private void aniadirRegistros() {
        ListRegistros.clear();
        Registros persona = new Registros();
        persona.setNombre(txfnombre.getText());
        persona.setApellido(txfapellido.getText());
        String telefono = txftelefono.getText();
        persona.setTelefono(telefono);
        String ciudad = txfciudad.getText();
        persona.setCiudad(ciudad);
        
        try {
            Integer ci = Integer.parseInt(txfci.getText());
            persona.setCi(ci);
        } catch (NumberFormatException e) {
            System.out.println("Error al cargar ci numero");
        }
        
        if (persona != null) {
            ListRegistros.add(persona);
            registroData.addAll(ListRegistros);
        } else {
            
        }
    }
 
}
