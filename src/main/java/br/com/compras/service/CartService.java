package br.com.compras.service;

import java.util.Optional;

import br.com.compras.model.Cart;

public interface CartService {

	public Cart save(Cart cart);
	
	public Optional<Cart> findById(Long id);
}
