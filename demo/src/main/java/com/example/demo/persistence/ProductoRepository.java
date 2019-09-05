package com.example.demo.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.business.BusinessException;
import com.example.demo.business.NotFoundException;
import com.example.demo.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
	Optional<Producto> findByDescripcion(String descripcionProducto) throws BusinessException, NotFoundException;
	Optional<Producto> findByDescripcionAndPrecio(String descripcionProducto, double precio) throws BusinessException, NotFoundException;
	Optional<List<Producto>> findByDercipcionOrderByPrecioAsc(String descripcionProducto, double precio ) throws BusinessException, NotFoundException;
	Optional<List<Producto>> findFirst2ByDescripcionOrderByPrecioAsc(String descripcionProducto, double precio) throws BusinessException, NotFoundException;
}
