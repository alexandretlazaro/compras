package br.com.compras.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compras.model.Cart;
import br.com.compras.repository.CartRepository;
import br.com.compras.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository repository;
	
	@Override
	public Cart save(Cart cart) {
		return repository.save(cart);
	}

	@Override
	public Optional<Cart> findById(Long id) {
		return repository.findById(id);
	}

}
