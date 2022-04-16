package br.com.compras.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.compras.model.Cart;
import br.com.compras.model.ItemCart;

public interface ItemCartRepository extends JpaRepository<ItemCart, Long> {

	List<ItemCart> findByCart(Cart cart);
	
	@Modifying
	@Transactional
	@Query(value = "delete from item_cart where cart_id = ?1 and item_id = ?2", nativeQuery = true)
	void deleteByItemId(Long cartId, Long itemId);
}
