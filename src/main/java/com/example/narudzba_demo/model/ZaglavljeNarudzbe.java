package com.example.narudzba_demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ZaglavljeNarudzbe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@EqualsAndHashCode.Include
	private Long id;

	@Basic(optional = false)
	@Column(name = "BROJ_PRODAVAONICE")
	private Integer brojProdavaonice;

	@Basic(optional = false)
	@Column(name = "DATUM_NARUDZBE")
	private LocalDate datumNarudzbe;

	@OneToMany(mappedBy = "zaglavljeNarudzbe", cascade = CascadeType.ALL)
	private List<StavkaNarudzbe> stavkaNarudzbeList;

}
