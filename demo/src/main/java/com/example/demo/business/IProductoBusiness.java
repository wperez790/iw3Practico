package com.example.demo.business;

import java.util.List;

import com.example.demo.model.Producto;

public interface IProductoBusiness {

	public List<Producto> list() throws BusinessException;

	public Producto load(int idProducto) throws BusinessException, NotFoundException;

	public Producto save(Producto producto) throws BusinessException;

	public void remove(int idProducto) throws BusinessException, NotFoundException;
	
	public Producto findByDescripcion(String descripcionProducto) throws BusinessException, NotFoundException;
	
	public Producto findByDescripcionAndPrecio(String descripcionProducto, double precio) throws BusinessException, NotFoundException;
	
	public List<Producto> findByDercipcionOrderByPrecioAsc(String descripcionProducto, double precio) throws BusinessException, NotFoundException;
	
	public List<Producto> findFirst2ByDercipcionOrderByPrecioAsc(String descripcionProducto, double precio) throws BusinessException, NotFoundException;

}
