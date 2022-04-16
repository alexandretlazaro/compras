package br.com.compras.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compras.model.Item;
import br.com.compras.repository.ItemRepository;
import br.com.compras.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {


	@Autowired
	private ItemRepository itemRepository;

	public List<Item> getItens(String produto) {

		if(null != produto && !"".equals(produto)) {

			return itemRepository.findByProduto(produto);
		}

		return itemRepository.findAll();

	}
	
	public Optional<Item> getItemById(Long id) {
		
		return itemRepository.findById(id);
	}

	public Item salvarItem(Item item) {

		return itemRepository.save(item);

	}
}
