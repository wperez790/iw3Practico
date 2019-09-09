package com.example.demo.persistence;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.business.BusinessException;
import com.example.demo.business.NotFoundException;
import com.example.demo.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
	Optional<Producto> findByDescripcion(String descripcionProducto) ;
	Optional<Producto> findByDescripcionAndPrecio(String descripcionProducto, double precio) ;
	Optional<List<Producto>> findByDescripcionOrderByPrecioAsc(String descripcionProducto) ;
	Optional<List<Producto>> findFirst2ByDescripcionOrderByPrecioAsc(String descipcionProducto) ;
	Optional<List<Producto>> findByPrecioBetween(double precioMenor, double precioMayor) ;
	Optional<List<Producto>> findByPrecioGreaterThan(double precio) ;
	Optional<List<Producto>> findByPrecioLessThan(double precio);
	Optional<List<Producto>> findByFechaVencimientoIsNull();
	Optional<List<Producto>> findByFechaVencimientoIsNotNull();
	Optional<List<Producto>> findByFechaVencimientoAfter(Date fecha);
	Optional<List<Producto>> findByFechaVencimientoBefore(Date fecha);

}
