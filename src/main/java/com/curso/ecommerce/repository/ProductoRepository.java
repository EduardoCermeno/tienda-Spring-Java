package com.curso.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.ecommerce.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
