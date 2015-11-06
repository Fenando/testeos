/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.PeliculaDAL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sistemas
 */
public class Pelicula {
    private String codigo;
    private String nombre;
    private String categoria;
    private int dPrestamo;
    private String formato;
    private Timestamp fechaIngreso;
   
    
    public Pelicula() {
    }

    public Pelicula(String codigo, String nombre, String categoria, int dPrestamo, String formato, Timestamp fechaIngreso) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.dPrestamo = dPrestamo;
        this.formato = formato;
        this.fechaIngreso = fechaIngreso;
    }
    public Pelicula(String codigo, String nombre, String categoria, int dPrestamo, String formato) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.dPrestamo = dPrestamo;
        this.formato = formato;
        //this.fechaIngreso = fechaIngreso;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    public String getFormato() {
        return formato;
    }

    public String getNombre() {
        return nombre;
    }

    public int getdPrestamo() {
        return dPrestamo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setdPrestamo(int dPrestamo) {
        this.dPrestamo = dPrestamo;
    }
    public String insert(Pelicula movie){
        String c;
        int r = new PeliculaDAL().ingresar(movie);
        switch (r) {
            case -1:
                c = "Codigo ya existente";
                break;
            case 1:
                c = "Ingresado con exito";
                break;
            default:
                //throw new AssertionError();
                c="ERROR, no ingresado";
        }
        return c;
    }
    public String modificar(Pelicula movie){
        int r = new PeliculaDAL().update(movie);
        if (r==1) {
            return "Modificado con exito";
        }else{
            return "ERROR, no modificado";
        }
    
    }
    public String delete(String code){
        int r = new PeliculaDAL().delete(code);
        if (r==1) {
            return "Eleminado con exito";
        }else{
            return "ERROR, dato no eliminado";
        }
    }
    public ArrayList<Pelicula> verNameF(){
        return new PeliculaDAL().verNameFormat();
    }
    public String verNamePorFecha(Timestamp date){
        ResultSet r = new PeliculaDAL().verNamePorFecha(date);
        try {
            return r.getString("nombre");
        } catch (SQLException ex) {
            Logger.getLogger(Pelicula.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR en la busqueda por fecha";
        }
    }
    public ArrayList<Pelicula> listMoviesCC(){
        return new PeliculaDAL().verPeliculasList();
    }
    public ArrayList<Pelicula> listMoviesforDate(Timestamp date){
        return new PeliculaDAL().listPeliculas(date);
    }
}
