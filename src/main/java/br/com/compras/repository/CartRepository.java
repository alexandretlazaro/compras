package br.com.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compras.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	
}
