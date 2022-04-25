package br.com.compras.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compras.model.Categoria;
import br.com.compras.repository.CategoriaRepository;
import br.com.compras.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Override
	public List<Categoria> listCategorias() {
		List<Categoria> categoriasList = repository.findAll();
		
		return categoriasList;
	}

	@Override
	public Optional<Categoria> findById(Long id) {
		
		Optional<Categoria> catOpt = repository.findById(id);
		
		return catOpt;
	}

	@Transactional
	@Override
	public Categoria save(Categoria categoria) {
		return repository.save(categoria);
	}




	
}
