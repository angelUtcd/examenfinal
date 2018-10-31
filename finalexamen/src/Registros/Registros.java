package Registros;
public class Registros {
 public String nombre;
    public String apellido;
    public String telefono;
    public String ciudad;
    public Integer ci;
    public Boolean sexo;
    public String fecha;
    public Registros(){}

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Integer getCi() {
        return ci;
    }

    public Boolean getSexo() {
        return sexo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
