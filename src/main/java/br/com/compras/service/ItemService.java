package br.com.compras.service;

import java.util.List;
import java.util.Optional;

import br.com.compras.model.Item;

public interface ItemService {

	public List<Item> getItens(String produto);
	public Optional<Item> getItemById(Long id);
}
