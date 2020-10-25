package com.example.narudzba_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.narudzba_demo.model.Prodaja;
import com.example.narudzba_demo.repository.ProdajaRepository;

@Service
@Transactional
public class ProdajaService {

	@Autowired
	ProdajaRepository prodajaRepository;

	public List<Prodaja> findAll() {
		return prodajaRepository.findAll();
	}

	public Prodaja save(Prodaja prodaja) throws Exception {
		return prodajaRepository.save(prodaja);
	}

	public List<Prodaja> saveAll(List<Prodaja> prodajaList) throws Exception {
		return prodajaRepository.saveAll(prodajaList);
	}

}
