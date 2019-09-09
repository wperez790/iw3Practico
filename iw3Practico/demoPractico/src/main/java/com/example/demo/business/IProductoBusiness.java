package com.example.demo.business;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Producto;

public interface IProductoBusiness {

	public List<Producto> list() throws BusinessException;

	public Producto load(int idProducto) throws BusinessException, NotFoundException;

	public Producto save(Producto producto) throws BusinessException;

	public void remove(int idProducto) throws BusinessException, NotFoundException;
	
	public Producto findByDescripcion(String descripcionProducto) throws BusinessException, NotFoundException;
	
	public Producto findByDescripcionAndPrecio(String descripcionProducto, double precio) throws BusinessException, NotFoundException;
	
	public List<Producto> findByDescripcionOrderByPrecioAsc(String descripcionProducto) throws BusinessException, NotFoundException;
	
	public List<Producto> findFirst2ByDescripcionOrderByPrecioAsc(String descripcionProducto) throws BusinessException, NotFoundException;
	
	public List<Producto> findByPrecioBetween(double precioMenor, double precioMayor) throws BusinessException, NotFoundException;
	
	public List<Producto> findByPrecioGreaterThan(double precio) throws BusinessException, NotFoundException;
	
	public List<Producto> findByPrecioLessThan(double precio) throws BusinessException, NotFoundException;
	
	public List<Producto> findByFechaVencimientoIsNull() throws BusinessException, NotFoundException;
	
	public List<Producto> findByFechaVencimientoIsNotNull() throws BusinessException, NotFoundException;
	
	public List<Producto> findByFechaVencimientoAfter(Date fecha) throws BusinessException, NotFoundException;
	
	public List<Producto> findByFechaVencimientoBefore(Date fecha) throws BusinessException, NotFoundException;



}
