package br.com.compras.service;

import java.util.List;
import java.util.Optional;

import br.com.compras.model.Categoria;

public interface CategoriaService {

	public List<Categoria> listCategorias();
	public Categoria save(Categoria categoria);
	public Optional<Categoria> findById(Long id);
}
