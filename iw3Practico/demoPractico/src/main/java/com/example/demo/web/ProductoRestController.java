package com.example.demo.web;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.BusinessException;
import com.example.demo.business.IProductoBusiness;
import com.example.demo.business.NotFoundException;
import com.example.demo.model.Producto;

@RestController
@RequestMapping(Constantes.URL_BASE_PRODUCTOS)
public class ProductoRestController {

	@Autowired
	private IProductoBusiness productoBusiness;

	@GetMapping("")
	public ResponseEntity<List<Producto>> list() {
		
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Producto> load(@PathVariable("id") int idProducto) {
		
		try {
			return new ResponseEntity<Producto>(productoBusiness.load(idProducto), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Producto producto) {
		
		try {
			productoBusiness.save(producto);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_PRODUCTOS + "/" + producto.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Producto producto) {
		
		try {
			productoBusiness.save(producto);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idProducto) {
		
		try {
			productoBusiness.remove(idProducto);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/descripcion")
	public ResponseEntity<Producto> findByDescripcion(@RequestParam("desc") String descripcionProducto) {
		
		try {
			return new ResponseEntity<Producto>(productoBusiness.findByDescripcion(descripcionProducto), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/descripcionAndPrecio")
	public ResponseEntity<Producto> findByDescripcionAndPrecio(@RequestParam("desc") String descripcionProducto, @RequestParam("p") double precio) {
		
		try {
			return new ResponseEntity<Producto>(productoBusiness.findByDescripcionAndPrecio(descripcionProducto, precio), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/descripcionAndPrecioList")
	public ResponseEntity<List<Producto>> findByDercipcionOrderByPrecioAsc(@RequestParam("desc") String descripcionProducto) {
		
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.findByDescripcionOrderByPrecioAsc(descripcionProducto), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/descripcionAndPrecio2List")
	public ResponseEntity<List<Producto>> findFirst2ByDescripcionOrderByPrecioAsc(@RequestParam("desc") String descripcionProducto) {
		
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.findFirst2ByDescripcionOrderByPrecioAsc(descripcionProducto), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(NotFoundException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND); 
		}
		
	}
	
	@GetMapping(value="/descripcionEntrePrecios")
	public ResponseEntity<List<Producto>> findByPrecioBetween(@RequestParam("precioMenor") double precioMenor, @RequestParam("precioMayor") double precioMayor) {
		
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.findByPrecioBetween(precioMenor, precioMayor), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(NotFoundException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND); 
		}
		
	}
	
	@GetMapping(value="/listByType")
	public ResponseEntity<List<Producto>> findByPrecio(@RequestParam("type") String operador, @RequestParam("precio") double precio) {
		
		try {
			if(operador.equals("greater"))
				return new ResponseEntity<List<Producto>>(productoBusiness.findByPrecioGreaterThan(precio), HttpStatus.OK);
			else if(operador.equals("less"))
				return new ResponseEntity<List<Producto>>(productoBusiness.findByPrecioLessThan(precio), HttpStatus.OK);
			else
				return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND); 
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(NotFoundException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND); 
		}
		
	}
	
	@GetMapping(value="/fechaIsNull")
	public ResponseEntity<List<Producto>> findByFechaVencimientoIsNull() {
		
		try {
				return new ResponseEntity<List<Producto>>(productoBusiness.findByFechaVencimientoIsNull(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(NotFoundException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND); 
		}
		
	}
	
	@GetMapping(value="/fechaIsNotNull")
	public ResponseEntity<List<Producto>> findByFechaVencimientoIsNotNull() {
		
		try {
				return new ResponseEntity<List<Producto>>(productoBusiness.findByFechaVencimientoIsNotNull(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(NotFoundException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND); 
		}
		
	}
	
	@GetMapping(value="/fechaAfter")
	public ResponseEntity<List<Producto>> findByFechaVencimientoAfter(@RequestParam("fecha") String fecha) {
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = formatter.parse(fecha);
		} catch (ParseException e1) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
		}
			
		try {
				return new ResponseEntity<List<Producto>>(productoBusiness.findByFechaVencimientoAfter(date), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(NotFoundException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND); 
		}
		
	}
	
	@GetMapping(value="/fechaBefore")
	public ResponseEntity<List<Producto>> findByFechaVencimientoBefore(@RequestParam("fecha") String fecha) {
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = formatter.parse(fecha);
		} catch (ParseException e1) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
		}
		
		try {
				return new ResponseEntity<List<Producto>>(productoBusiness.findByFechaVencimientoAfter(date), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(NotFoundException e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND); 
		}
		
	}
	
	
}
