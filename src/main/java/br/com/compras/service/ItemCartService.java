package br.com.compras.service;

import java.util.List;

import br.com.compras.model.Cart;
import br.com.compras.model.Item;
import br.com.compras.model.ItemCart;

public interface ItemCartService {

	public List<ItemCart> findByCart(Cart cart);
	public ItemCart findByCartByItem(Cart cart, Item item);
	public ItemCart save(ItemCart itemCart);
	public void deleteByIdCartByidItem(Long cartId, Long itemId);
}
