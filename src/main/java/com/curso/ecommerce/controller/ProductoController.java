package com.curso.ecommerce.controller;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.ProductoService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	//con esta variable se utiliza para poder imprimir los objetos o las acciones de la clase
	private final Logger LOGGER = (Logger) LoggerFactory.getLogger(ProductoController.class);
	@Autowired
	private ProductoService productoService;

	
	//metodo que obtiene la informacion de productos y los manda a la vista show de producto
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("obteniendoproductos", productoService.findAll());
		return "productos/show";
		
	}
	
	//metodo que redirecciona a la vista create de productos
	@GetMapping("/create")
	public String create() {
		return "productos/create";
		
	}

	//metodo que redirecciona al inicio de productos o sea al metodo show
	@PostMapping("/save")
	public String save(Producto producto) {
		LOGGER.info("Este es el objeto producto {}",producto);
		Usuario u =new Usuario(1,"", "", "", "", "", "", "");
		producto.setUsuario(u);
		
		productoService.save(producto);
		return "redirect:/productos";
		
	}
	
	//controlador que recive un ID y sirve para editar un producto
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		Producto producto=new Producto();
		Optional<Producto>optionalProducto=productoService.get(id);
		producto=optionalProducto.get();
		LOGGER.info("producto buscado {}",producto);
		
		model.addAttribute("editarproducto", producto);
		return "productos/edit";
	}


	@PostMapping("update")
	public String update(Producto producto) {
		productoService.update(producto);
		return "redirect:/productos";
		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		productoService.delete(id);
		return "redirect:/productos";
	}
}
