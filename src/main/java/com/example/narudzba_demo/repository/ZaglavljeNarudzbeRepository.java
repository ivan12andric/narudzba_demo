package com.example.narudzba_demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.narudzba_demo.model.ZaglavljeNarudzbe;

@Repository
public interface ZaglavljeNarudzbeRepository extends JpaRepository<ZaglavljeNarudzbe, Long> {

	@EntityGraph(attributePaths = { "stavkaNarudzbeList" })
	List<ZaglavljeNarudzbe> findAll();

}
