package br.com.compras.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.compras.model.Cart;
import br.com.compras.model.Item;
import br.com.compras.model.ItemCart;

public interface ItemCartRepository extends JpaRepository<ItemCart, Long> {

	public List<ItemCart> findByCart(Cart cart);
	
	@Query(value = "select * from item_cart where cart_id = ?1 and item_id = ?2", nativeQuery = true)
	public ItemCart findByCartByItem(Cart cart, Item item);
	
	@Modifying
	@Transactional
	@Query(value = "delete from item_cart where cart_id = ?1 and item_id = ?2", nativeQuery = true)
	public void deleteByItemId(Long cartId, Long itemId);
}
