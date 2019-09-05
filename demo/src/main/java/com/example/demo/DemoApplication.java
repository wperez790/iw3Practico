package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Producto;
import com.example.demo.persistence.ProductoRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	
	@Autowired
	private ProductoRepository productoDAO; 
	@Override
	public void run(String... args) throws Exception {
		Producto p=new Producto();
		p.setId(1);
		p.setDescripcion("Arroz");
		p.setPrecio(45.67);
		productoDAO.save(p);
		
		p=new Producto();
		p.setId(2);
		p.setDescripcion("Leche");
		p.setPrecio(56.33);
		productoDAO.save(p);
	 
		
	}

}
