package com.example.narudzba_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.narudzba_demo.model.Zaliha;
import com.example.narudzba_demo.repository.ZalihaRepository;

@Service
@Transactional
public class ZalihaService {

	@Autowired
	ZalihaRepository zalihaRepository;

	public List<Zaliha> findAll() {
		return zalihaRepository.findAll();
	}

	public Zaliha save(Zaliha zaliha) throws Exception {
		return zalihaRepository.save(zaliha);
	}

	public List<Zaliha> saveAll(List<Zaliha> zalihaList) throws Exception {
		return zalihaRepository.saveAll(zalihaList);
	}

}
