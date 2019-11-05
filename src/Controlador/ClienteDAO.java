package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

import Modelo.Clientes;

/**
 *
 * @author sergio
 * @param <Tipo>
 */
public interface ClienteDAO {    
    
	Clientes findByPK(int id) throws Exception;
     
    List<Clientes> findAll() throws Exception;
    
    // List<Tipo> findByExample(Tipo t) throws Exception;
    
    List<Clientes> findBySQL(String sqlselect) throws Exception;
     
    boolean insert(Clientes t) throws Exception;
     
    boolean update(Clientes t) throws Exception;
    // boolean updateByExample(Tipo t) throws Exception;
     
    boolean delete(int id) throws Exception;    
    //int deleteByExample(Tipo t) throws Exception;
    
    
}
