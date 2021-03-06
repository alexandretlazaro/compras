package br.com.compras.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compras.model.Cart;
import br.com.compras.model.Item;
import br.com.compras.model.ItemCart;
import br.com.compras.repository.ItemCartRepository;
import br.com.compras.service.ItemCartService;

@Service
public class ItemCartServiceImpl implements ItemCartService {

	@Autowired
	private ItemCartRepository repository;
	
	@Override
	public List<ItemCart> findByCart(Cart cart) {
		return repository.findByCart(cart);
	}
	
	@Override
	public ItemCart findByCartByItem(Cart cart, Item item) {
		
		return repository.findByCartByItem(cart, item);
	}

	@Override
	public void deleteByIdCartByidItem(Long cartId, Long itemId) {
		
		repository.deleteByItemId(cartId, itemId);
	}

	@Transactional
	@Override
	public ItemCart save(ItemCart itemCart) {
		return repository.save(itemCart);
	}

}
