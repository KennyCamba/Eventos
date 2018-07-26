/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

/**
 *
 * @author Kenny Camba Torres
 */
public class Persona {
    private String nombre;
    private String cedula;
    private String direccion;
    
    public Persona(String nombre, String cedula, String direccion){
        this.nombre = nombre;
        this.cedula = cedula;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDirccion() {
        return direccion;
    }

    public void setDirccion(String dirccion) {
        this.direccion = dirccion;
    }
    
    @Override
    public boolean equals(Object o){
        if(o != null){
            if(o.getClass() == Persona.class){
                Persona p = (Persona)o;
                if(p.getCedula().equals(this.cedula)){
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        String text = "{Nombre: " + nombre + ", Cedula: " + cedula + ", Dirccion: ";
        if(!direccion.isEmpty()){
            text += direccion;
        }else{
            text += "sin especificar";
        }
        text += "}";
        return text;
    }
}
