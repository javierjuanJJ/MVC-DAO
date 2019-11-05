package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

import Modelo.Articulos;

/**
 *
 * @author sergio
 * @param <Tipo>
 */
public interface ArticuloDAO {    
    
	Articulos findByPK(int id) throws Exception;
     
    List<Articulos> findAll() throws Exception;
    
    // List<Tipo> findByExample(Tipo t) throws Exception;
    
    List<Articulos> findBySQL(String sqlselect) throws Exception;
     
    boolean insert(Articulos t) throws Exception;
     
    boolean update(Articulos t) throws Exception;
    // boolean updateByExample(Tipo t) throws Exception;
     
    boolean delete(int id) throws Exception;    
    //int deleteByExample(Tipo t) throws Exception;
    
    
}
