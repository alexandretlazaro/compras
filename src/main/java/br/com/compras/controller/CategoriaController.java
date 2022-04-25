package br.com.compras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compras.model.Categoria;
import br.com.compras.service.CategoriaService;

@RequestMapping("/api")
@RestController
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@PostMapping("/categoria")
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {

		try {

			categoriaService.save(categoria);

			return new ResponseEntity<Categoria>(categoria, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/categoria")
	public ResponseEntity<List<Categoria>> listCategorias() {

		try {
			
			List<Categoria> categoriasList = categoriaService.listCategorias();

			return new ResponseEntity<List<Categoria>>(categoriasList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
