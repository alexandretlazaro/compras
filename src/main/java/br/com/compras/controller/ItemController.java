package br.com.compras.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compras.model.Categoria;
import br.com.compras.model.Item;
import br.com.compras.service.impl.CategoriaServiceImpl;
import br.com.compras.service.impl.ItemServiceImpl;

@RequestMapping("/api")
@RestController
public class ItemController {

	private final ItemServiceImpl itemService;

	@Autowired
	public ItemController(ItemServiceImpl itemService) {
		this.itemService = itemService;
	}

	@Autowired
	private CategoriaServiceImpl categoriaService;

	@GetMapping("/item")
	private ResponseEntity<List<Item>> getProducts(@RequestParam(required = false) String produto) {

		List<Item> productsList = new ArrayList<Item>();

		try {

			productsList = itemService.getItens(produto);

			return new ResponseEntity<>(productsList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/item/{id}")
	private ResponseEntity<Item> getProductById(@PathVariable Long id) {

		try {

			Optional<Item> product = itemService.getItemById(id);

			if(!product.isEmpty()) {


				Item _item = product.get();

				return new ResponseEntity<>(_item, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/item")
	private ResponseEntity<Item> saveProduct(@RequestBody Item item) {

		try {

			Optional<Categoria> categoriaOpt = categoriaService.findById(item.getCategoria().getId());

			if(!categoriaOpt.isEmpty()) {

				Categoria _categoria = categoriaOpt.get();

				item.setCategoria(_categoria);

				Item _item = itemService.salvarItem(item);

				return new ResponseEntity<>(_item, HttpStatus.CREATED);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
