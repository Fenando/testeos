/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Pelicula;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sistemas
 */
public class PeliculaDAL {
    private Conector c;
    private Statement sta;
    
    public PeliculaDAL() {
    c = new Conector();
    }
    
    public int ingresar(Pelicula movie){
        sta = c.getStatement();
        try {
            int r = sta.executeUpdate("insert into pelicula (codigo,nombre,categoria,"
                    + "dias_prestamo,formato,fecha_ingreso) values ('"
                    +movie.getCodigo()+"','"
                    +movie.getNombre()+"','"
                    +movie.getCategoria()+"',"
                    +movie.getdPrestamo()+",'"
                    +movie.getFormato()+"','"
                    +movie.getFechaIngreso()+"')");
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAL.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode()==1062) {
                return -1;
            }else{
                return -2;
            }
        }
    }
    public int update(Pelicula movie){
        sta = c.getStatement();
        try {
            int r = sta.executeUpdate("UPDATE pelicula "
                    + "set "
                    + "nombre = '"+movie.getNombre()+"',"
                    + "categoria = '"+movie.getCategoria()+"',"
                    + "dias_prestamo = "+movie.getdPrestamo()+","
                    + "formato = '"+movie.getFormato()+"',"
                    + "fecha_ingreso ='"+movie.getFechaIngreso()+"'"
                    + "where codigo ='"+movie.getCodigo()+"'");
           
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAL.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        }
    
    public int delete(String code){
        sta = c.getStatement();
        try {
            int r = sta.executeUpdate("delete from pelicula "
                    + "where codigo ='"+code+"'");
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAL.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    public ArrayList<Pelicula> verNameFormat() {
        ArrayList<Pelicula> aList= new ArrayList();
        
        try {
            sta = c.getStatement();
            ResultSet rSet = sta.executeQuery("select nombre,formato from pelicula"
                    + "where categoria = estreno");
            while (rSet.next()) {
                Pelicula p = new Pelicula();
                p.setNombre(rSet.getString("nombre"));
            }
            return aList;
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAL.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        
        }
    }
    public ResultSet verNamePorFecha(Timestamp date){
        sta = c.getStatement();
        try {
            ResultSet rSet = sta.executeQuery("select nombre from pelicula "
                    + "where fecha_ingreso ="+date);
            return rSet;
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ArrayList<Pelicula> verPeliculasList(){
        
        ArrayList<Pelicula> movieList = new ArrayList();
        sta = c.getStatement();
        try {
            ResultSet rSet = sta.executeQuery("select categoria,codigo from pelicula");
            while (rSet.next()) {
                Pelicula p = new Pelicula();
                p.setCodigo(rSet.getString("codigo"));
                p.setCategoria(rSet.getString("categoria"));
                movieList.add(p);
            }
        return movieList;    
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAL.class.getName()).log(Level.SEVERE, null, ex);
            return null;   
        }
        
    }
    public ArrayList<Pelicula> listPeliculas(Timestamp date){
        
        ArrayList<Pelicula> movieList = new ArrayList();
        sta = c.getStatement();
        try {
            ResultSet rSet = sta.executeQuery("select nombre,"
                    + "dia_prestamo,"
                    + "fecha_ingreso"
                    + " from pelicula"
                    + "where fecha_ingreso ="+date);
            while (rSet.next()) {
                Pelicula p = new Pelicula();
                p.setNombre(rSet.getString("nombre"));
                p.setdPrestamo(rSet.getInt("dia_prestamo"));
                p.setFechaIngreso(rSet.getTimestamp("fecha_ingreso"));
                movieList.add(p);
            }
        return movieList;    
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAL.class.getName()).log(Level.SEVERE, null, ex);
            return null;   
        }
    }
    public void deleteAll(){
        sta = c.getStatement();
        try {
            sta.executeUpdate("delete from pelicula");
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}   
