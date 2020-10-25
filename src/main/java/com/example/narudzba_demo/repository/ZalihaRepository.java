package com.example.narudzba_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.narudzba_demo.model.Zaliha;

@Repository
public interface ZalihaRepository extends JpaRepository<Zaliha, Long> {

}
