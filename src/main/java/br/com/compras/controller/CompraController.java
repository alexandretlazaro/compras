package br.com.compras.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compras.model.Cart;
import br.com.compras.model.Item;
import br.com.compras.model.ItemCart;
import br.com.compras.repository.CartRepository;
import br.com.compras.service.impl.ItemCartServiceImpl;
import br.com.compras.service.impl.ItemServiceImpl;

@RequestMapping("/api")
@RestController
public class CompraController {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ItemServiceImpl itemService;

	@Autowired
	private ItemCartServiceImpl itemCartService;

	@GetMapping("/cart/{idCarrinho}")
	private ResponseEntity<List<Item>> getAllItemsFromCart(@PathVariable Long idCarrinho) {

		List<Item> productsList = new ArrayList<Item>();

		try {

			Optional<Cart> cart = cartRepository.findById(idCarrinho);

			Cart _cart = cart.get();

			Iterator<ItemCart> it = _cart.getItemsCartList().iterator();

			while(it.hasNext()) {
				productsList.add(it.next().getItem());
			}

			Collections.sort(productsList, new Comparator<Item>() {

				@Override
				public int compare(Item o1, Item o2) {
					return o1.getId().compareTo(o2.getId());
				}
			});


			return new ResponseEntity<>(productsList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/cart/{itemId}")
	private ResponseEntity<Cart> addItem(@PathVariable("itemId") Long itemId) {

		try {

			Cart myCart = new Cart();

			Optional<Item> itemOpt = itemService.getItemById(itemId);
			ItemCart itemCart = new ItemCart();

			if(itemOpt.isPresent()) {

				Item _item = itemOpt.get();
				itemCart.setItem(_item);

				myCart.getItemsCartList().add(itemCart);

				cartRepository.save(myCart);

				itemCart.setCart(myCart);

				itemCartService.save(itemCart);
			}

			return new ResponseEntity<>(myCart, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/cart/{cartId}/{itemId}")
	private ResponseEntity<Cart> addItemOnExistCart(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId) {

		try {

			Optional<Cart> cartOpt = cartRepository.findById(cartId);

			Optional<Item> itemOpt = itemService.getItemById(itemId);
			ItemCart itemCart = new ItemCart();

			if(cartOpt.isPresent()) {

				if(itemOpt.isPresent()) {

					Item _item = itemOpt.get();
					Cart _cart = cartOpt.get();

					itemCart.setItem(_item);

					_cart.getItemsCartList().add(itemCart);

					cartRepository.save(_cart);

					itemCart.setCart(_cart);

					itemCartService.save(itemCart);
				}
			}

			return new ResponseEntity<>(cartOpt.get(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/cart/{cartId}/{itemId}")
	private ResponseEntity<HttpStatus> deleteItemCart(@PathVariable Long cartId, @PathVariable Long itemId) {

		try {

			itemCartService.deleteByIdCartByidItem(cartId, itemId);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
