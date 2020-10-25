package com.example.narudzba_demo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.narudzba_demo.model.Prodaja;
import com.example.narudzba_demo.model.StavkaNarudzbe;
import com.example.narudzba_demo.model.ZaglavljeNarudzbe;
import com.example.narudzba_demo.model.Zaliha;
import com.example.narudzba_demo.repository.ZaglavljeNarudzbeRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class ZaglavljeNarudzbeService {

	@Autowired
	ZaglavljeNarudzbeRepository zaglavljeNarudzbeRepository;

	public void kreirajNarudzbu(LocalDate datumNarudzbe, Integer brojDana, List<Zaliha> zalihaList, List<Prodaja> prodajaList) throws Exception {

		try {

			//trenutna zaliha na datum
			Map<String, Integer> zaliheNaDatum = zalihaList.stream()
					.collect(Collectors.groupingBy(Zaliha::getArtikl)) // grupiranje zaliha po artiklu
					.entrySet().stream()
					.collect(
							Collectors.toMap(
									Entry::getKey,
									entry -> entry.getValue().stream().filter(zaliha -> zaliha.getDatum().equals(datumNarudzbe)).map(Zaliha::getKolicina).findFirst().get()));  // zalihe po artiklu za datum narudžbe	

			zaliheNaDatum.forEach((k, v) -> log.info(MessageFormat.format("Zaliha na datum {0} : {1}", k, v)));

			//  potrebne količine
			Map<String, Integer> potrebnaKolicina = prodajaList.stream()
					.collect(Collectors.groupingBy(Prodaja::getArtikl)) // grupiranje prodaje po artiklu
					.entrySet().stream()
					.collect(
							Collectors.toMap(
									Entry::getKey,
									entry -> entry.getValue().stream().mapToDouble(Prodaja::getKolicina).average()))  // prosjek prodaje po artiklu 				
					.entrySet().stream()
					.peek(entry -> log.info(MessageFormat.format("Prosjek prodaje: {0} : {1}", entry.getKey(), entry.getValue().getAsDouble())))
					.collect(
							Collectors.toMap(
									Entry::getKey,
									entry -> new BigDecimal(entry.getValue().getAsDouble()).multiply(new BigDecimal(brojDana))))  // potrebna količina = prosjek prodaje * broj dana
					.entrySet().stream()
					.peek(entry -> log.info(MessageFormat.format("Potrebna količina za {0} dana : {1} : {2}", brojDana, entry.getKey(), entry.getValue())))
					.collect(
							Collectors.toMap(
									Entry::getKey,
									entry -> entry.getValue().setScale(0, RoundingMode.UP).intValue()));  // potrebna količina - zaokruženo na prvi veći cijeli broj								

			potrebnaKolicina.forEach((k, v) -> log.info(MessageFormat.format("Potrebna količina (zaokruženo)  za {0} dana : {1} : {2}", brojDana, k, v)));

			// potrebne količine minus zalihe
			Map<String, Integer> stvarnoPotrebnaKolicina = potrebnaKolicina.entrySet().stream()
					.collect(
							Collectors.toMap(
									Entry::getKey,
									entry -> Math.max(0, entry.getValue() - zaliheNaDatum.get(entry.getKey()))));  // potrebna količina minus zaliha - zaokruženo na 0 ako je negativno (zaliha je veća od potrebne količine)

			stvarnoPotrebnaKolicina.forEach((k, v) -> log.info(MessageFormat.format("Potrebna količina minus zaliha za {0} dana : {1} : {2}", brojDana, k, v)));

			//zaglavlje narudžbe
			ZaglavljeNarudzbe zaglavljeNarudzbe = ZaglavljeNarudzbe.builder()
					.brojProdavaonice(1)
					.datumNarudzbe(datumNarudzbe)
					.stavkaNarudzbeList(new ArrayList<>())
					.build();

			//stavke narudžbe
			stvarnoPotrebnaKolicina.forEach((k, v) -> {
				zaglavljeNarudzbe.getStavkaNarudzbeList().add(
						StavkaNarudzbe.builder().artikl(k)
								.kolicina(v)
								.zaglavljeNarudzbe(zaglavljeNarudzbe)
								.build());
			});

			//spremi narudžbu
			save(zaglavljeNarudzbe);

		} catch (Exception e) {
			log.error("Greška kod kreiranja narudžbe: " + e.getMessage());

			throw e;
		}
	}

	public List<ZaglavljeNarudzbe> findAll() {
		return zaglavljeNarudzbeRepository.findAll();
	}

	public ZaglavljeNarudzbe save(ZaglavljeNarudzbe zaglavljeNarudzbe) throws Exception {
		return zaglavljeNarudzbeRepository.save(zaglavljeNarudzbe);
	}

	public List<ZaglavljeNarudzbe> saveAll(List<ZaglavljeNarudzbe> zaglavljeNarudzbeList) throws Exception {
		return zaglavljeNarudzbeRepository.saveAll(zaglavljeNarudzbeList);
	}

}
