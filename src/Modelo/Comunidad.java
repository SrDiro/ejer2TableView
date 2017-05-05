package Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Comunidad {

    private final IntegerProperty id;
    private final StringProperty nombre;

    public Comunidad(int id, String nombre) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
    }
    

    public String getNombre() {
        return nombre.get();
    }
    
    public int getId() {
        return id.get();
    }

    @Override
    public String toString() {
        return "Comunidad{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    

    
    
    
    
}
