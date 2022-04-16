package br.com.compras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compras.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByProduto(String produto);
}
