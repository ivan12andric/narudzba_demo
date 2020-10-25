package com.example.narudzba_demo.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class StavkaNarudzbe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@EqualsAndHashCode.Include
	private Long id;

	@Basic(optional = false)
	private String artikl;

	@Basic(optional = false)
	private Integer kolicina;

	@ManyToOne
	@JoinColumn(name = "ZAGLAVLJE_ID")
	private ZaglavljeNarudzbe zaglavljeNarudzbe;

}
