package br.com.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compras.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
