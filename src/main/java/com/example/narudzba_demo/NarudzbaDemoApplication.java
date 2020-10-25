package com.example.narudzba_demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.narudzba_demo.model.Prodaja;
import com.example.narudzba_demo.model.Zaliha;
import com.example.narudzba_demo.service.ProdajaService;
import com.example.narudzba_demo.service.ZalihaService;
import com.example.narudzba_demo.util.ViewScope;
import com.google.common.collect.ImmutableMap;

@SpringBootApplication
public class NarudzbaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NarudzbaDemoApplication.class, args);
	}

	@Bean
	public static CustomScopeConfigurer viewScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.setScopes(new ImmutableMap.Builder<String, Object>().put("view", new ViewScope()).build());
		return configurer;
	}

	@Bean
	CommandLineRunner initProdaja(ProdajaService prodajaService) {

		//init prodaja
		List<Prodaja> prodajaList = List.of(
				Prodaja.builder().datum(LocalDate.of(2020, 9, 1)).prod("1").artikl("A1").kolicina(1).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 2)).prod("1").artikl("A1").kolicina(5).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 3)).prod("1").artikl("A1").kolicina(5).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 4)).prod("1").artikl("A1").kolicina(3).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 5)).prod("1").artikl("A1").kolicina(3).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 6)).prod("1").artikl("A1").kolicina(4).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 7)).prod("1").artikl("A1").kolicina(6).build(),

				Prodaja.builder().datum(LocalDate.of(2020, 9, 1)).prod("1").artikl("A2").kolicina(1).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 2)).prod("1").artikl("A2").kolicina(2).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 3)).prod("1").artikl("A2").kolicina(3).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 4)).prod("1").artikl("A2").kolicina(3).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 5)).prod("1").artikl("A2").kolicina(3).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 6)).prod("1").artikl("A2").kolicina(3).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 7)).prod("1").artikl("A2").kolicina(3).build(),

				Prodaja.builder().datum(LocalDate.of(2020, 9, 1)).prod("1").artikl("A3").kolicina(1).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 2)).prod("1").artikl("A3").kolicina(2).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 3)).prod("1").artikl("A3").kolicina(3).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 4)).prod("1").artikl("A3").kolicina(4).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 5)).prod("1").artikl("A3").kolicina(5).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 6)).prod("1").artikl("A3").kolicina(6).build(),
				Prodaja.builder().datum(LocalDate.of(2020, 9, 7)).prod("1").artikl("A3").kolicina(7).build());

		return args -> {
			prodajaService.saveAll(prodajaList);
		};
	}

	@Bean
	CommandLineRunner initZaliha(ZalihaService zalihaService) {

		//init zaliha
		List<Zaliha> zalihaList = List.of(
				Zaliha.builder().datum(LocalDate.of(2020, 9, 1)).prod("1").artikl("A1").kolicina(30).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 2)).prod("1").artikl("A1").kolicina(29).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 3)).prod("1").artikl("A1").kolicina(24).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 4)).prod("1").artikl("A1").kolicina(19).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 5)).prod("1").artikl("A1").kolicina(16).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 6)).prod("1").artikl("A1").kolicina(13).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 7)).prod("1").artikl("A1").kolicina(9).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 8)).prod("1").artikl("A1").kolicina(3).build(),

				Zaliha.builder().datum(LocalDate.of(2020, 9, 1)).prod("1").artikl("A2").kolicina(100).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 2)).prod("1").artikl("A2").kolicina(99).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 3)).prod("1").artikl("A2").kolicina(97).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 4)).prod("1").artikl("A2").kolicina(94).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 5)).prod("1").artikl("A2").kolicina(91).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 6)).prod("1").artikl("A2").kolicina(88).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 7)).prod("1").artikl("A2").kolicina(85).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 8)).prod("1").artikl("A2").kolicina(82).build(),

				Zaliha.builder().datum(LocalDate.of(2020, 9, 1)).prod("1").artikl("A3").kolicina(48).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 2)).prod("1").artikl("A3").kolicina(47).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 3)).prod("1").artikl("A3").kolicina(45).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 4)).prod("1").artikl("A3").kolicina(42).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 5)).prod("1").artikl("A3").kolicina(38).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 6)).prod("1").artikl("A3").kolicina(33).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 7)).prod("1").artikl("A3").kolicina(27).build(),
				Zaliha.builder().datum(LocalDate.of(2020, 9, 8)).prod("1").artikl("A3").kolicina(20).build());

		return args -> {
			zalihaService.saveAll(zalihaList);
		};

	}
}
