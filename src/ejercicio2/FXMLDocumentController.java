package ejercicio2;

import Modelo.Comunidad;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Comunidad> tvComunidades;
    @FXML
    private Label lbTotal;
    @FXML
    private Label lbEstado;
    @FXML
    private TableColumn<Comunidad, Integer> id;
    @FXML
    private TableColumn<Comunidad, String> nombre;
    
    Connection conexion;
    ResultSet rs;
    PreparedStatement ps;

    ObservableList<Comunidad> comunidades = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/parques", "root", "ROOT");
            lbEstado.setText("CONECTADO");
            lbEstado.setStyle("-fx-text-fill: green;");
            System.out.println(buscarComunidad().toString());
            comunidades.setAll(buscarComunidad());
            tvComunidades.setItems(comunidades);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            
            lbTotal.setText(buscarComunidad().size() + " registros");
              

        } catch (Exception e) {
            System.out.println(e.getMessage());
            lbEstado.setText("DESCONECTADO");
            lbEstado.setStyle("-fx-text-fill: red;");
        }

    }

    public List<Comunidad> buscarComunidad() throws SQLException {
        String consulta;
        Comunidad c;
        List<Comunidad> listaComunidades = new ArrayList<>();

        consulta = "SELECT * FROM parques.comunidad;";

        ps = conexion.prepareStatement(consulta);
        rs = ps.executeQuery();

        while (rs.next()) {
            c = new Comunidad(rs.getInt("id"), rs.getString("nombre"));
            listaComunidades.add(c);
        }

        return listaComunidades;

    }

}
