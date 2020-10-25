package com.example.narudzba_demo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.narudzba_demo.model.Prodaja;
import com.example.narudzba_demo.model.ZaglavljeNarudzbe;
import com.example.narudzba_demo.model.Zaliha;
import com.example.narudzba_demo.service.ProdajaService;
import com.example.narudzba_demo.service.ZaglavljeNarudzbeService;
import com.example.narudzba_demo.service.ZalihaService;

import lombok.extern.log4j.Log4j2;

@Component
@Scope("view")
@Log4j2
public class NarudzbaController {

	@Autowired
	ProdajaService prodajaService;

	@Autowired
	ZalihaService zalihaService;

	@Autowired
	ZaglavljeNarudzbeService zaglavljeNarudzbeService;

	private List<Prodaja> prodajaList;
	private List<Zaliha> zalihaList;

	private List<ZaglavljeNarudzbe> zaglavljeNarudzbeList;

	private ZaglavljeNarudzbe selectedZaglavljeNarudzbe;

	private Integer brojDana;

	@PostConstruct
	public void init() {

		prodajaList = prodajaService.findAll();
		zalihaList = zalihaService.findAll();
		zaglavljeNarudzbeList = zaglavljeNarudzbeService.findAll();

		//postavi odabrano zaglavlje
		if (zaglavljeNarudzbeList != null && !zaglavljeNarudzbeList.isEmpty()) {
			selectedZaglavljeNarudzbe = zaglavljeNarudzbeList.get(0);
		}

	}

	public void kreirajNarudzbu() {

		try {
			LocalDate datumNarudzbe = LocalDate.of(2020, 9, 8);

			//kreiraj narudžbu
			zaglavljeNarudzbeService.kreirajNarudzbu(datumNarudzbe, brojDana, zalihaList, prodajaList);

			//dohvati podatke
			zaglavljeNarudzbeList = zaglavljeNarudzbeService.findAll();

			//postavi odabrano zaglavlje
			if (zaglavljeNarudzbeList != null && !zaglavljeNarudzbeList.isEmpty()) {
				selectedZaglavljeNarudzbe = zaglavljeNarudzbeList.get(0);
			}

		} catch (Exception e) {
			log.error("Greška kod kreiranja narudžbe: " + e.getMessage());

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška kod kreiranja narudžbe.", null));

			return;
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Narudžba uspješno kreirana"));

	}

	public List<Prodaja> getProdajaList() {
		return prodajaList;
	}

	public void setProdajaList(List<Prodaja> prodajaList) {
		this.prodajaList = prodajaList;
	}

	public List<Zaliha> getZalihaList() {
		return zalihaList;
	}

	public void setZalihaList(List<Zaliha> zalihaList) {
		this.zalihaList = zalihaList;
	}

	public List<ZaglavljeNarudzbe> getZaglavljeNarudzbeList() {
		return zaglavljeNarudzbeList;
	}

	public void setZaglavljeNarudzbeList(List<ZaglavljeNarudzbe> zaglavljeNarudzbeList) {
		this.zaglavljeNarudzbeList = zaglavljeNarudzbeList;
	}

	public ZaglavljeNarudzbe getSelectedZaglavljeNarudzbe() {
		return selectedZaglavljeNarudzbe;
	}

	public void setSelectedZaglavljeNarudzbe(ZaglavljeNarudzbe selectedZaglavljeNarudzbe) {
		this.selectedZaglavljeNarudzbe = selectedZaglavljeNarudzbe;
	}

	public Integer getBrojDana() {
		return brojDana;
	}

	public void setBrojDana(Integer brojDana) {
		this.brojDana = brojDana;
	}

}
