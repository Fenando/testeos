/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testeos;

import BLL.Pelicula;
import DAL.PeliculaDAL;
import java.sql.Timestamp;

/**
 *
 * @author sistemas
 */
public class test {
    public static void main(String[] args) {
        PeliculaDAL p = new PeliculaDAL();
        
        p.deleteAll();
        Pelicula movie = new Pelicula("p03","hobbit2","estreno",5,"dvd",
                Timestamp.valueOf("2010-05-3 20:00:00"));
        p.ingresar(movie);
        movie.setCodigo("p05");
        movie.insert(movie);
        movie.setCodigo("p07");
        movie.insert(movie);
        movie.delete("p03");
        movie.modificar(new Pelicula("p07","starcrata2","normal",7,"dvd",Timestamp.valueOf("2010-5-15 20:01:22")));
        
        
        
    
    }
    
}
