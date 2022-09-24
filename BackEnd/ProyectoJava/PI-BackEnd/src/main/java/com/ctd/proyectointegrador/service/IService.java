package com.ctd.proyectointegrador.service;
import java.util.Map;

public interface IService<T> {

    public Map<String, Object> guardar(T object);

    public Map<String, Object> buscar(Long id);

    public Map<String, Object> actualizar(Long id, T object);

    public Map<String, Object> eliminar(Long id);

    public Map<String, Object> listarTodos();
}
