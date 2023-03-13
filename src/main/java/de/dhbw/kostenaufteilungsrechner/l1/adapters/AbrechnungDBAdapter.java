package de.dhbw.kostenaufteilungsrechner.l1.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Abrechnung;
import de.dhbw.kostenaufteilungsrechner.l3.domain.AbrechnungRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class AbrechnungDBAdapter implements AbrechnungRepository {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	String database = "datenbank/abrechnungen.json";

	List<Abrechnung> abrechnungsList = new ArrayList<>();

	@Override
	public void erstelleAbrechnung(Abrechnung neueAbrechnung) {
		try {
			Iterable<Abrechnung> abrechnungen = this.findeAlleAbrechnungen();
			for (Abrechnung abrechnung : abrechnungen) {
				abrechnungsList.add(abrechnung);
			}
		} catch (Exception e) {

		}
		abrechnungsList.add(neueAbrechnung);
		try (BufferedWriter out = new BufferedWriter(new FileWriter(database, false))) {
			out.write(gson.toJson(abrechnungsList));
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
	}

	@Override
	public Iterable<Abrechnung> findeAlleAbrechnungen() {
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			Iterable<Abrechnung> abrechnungen = gson.fromJson(br, new TypeToken<List<Abrechnung>>() {
			}.getType());
			return abrechnungen;
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		return null;
	}

	@Override
	public Optional<Abrechnung> findeAbrechnungÜberID(UUID abrechnungsID) {
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			Iterable<Abrechnung> abrechnungen = gson.fromJson(br, new TypeToken<List<Abrechnung>>() {
			}.getType());
			for (Abrechnung a : abrechnungen) {
				if (a.getAbrechnungsID().equals(abrechnungsID)) {
					return Optional.of(a);
				}
			}
			return Optional.empty();
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		return Optional.empty();
	}

	@Override
	public void aktualisiereAbrechnung(Abrechnung abrechnung) {
		List<Abrechnung> abrechnungen = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			abrechnungen = gson.fromJson(br, new TypeToken<List<Abrechnung>>() {
			}.getType());
			for (Abrechnung a : abrechnungen) {
				if (a.getAbrechnungsID().equals(abrechnung.getAbrechnungsID())) {
					a.setEventID(abrechnung.getEventID());
					a.setGesamtausgaben(abrechnung.getGesamtausgaben());
					a.setBilanzenMap(abrechnung.getBilanzenMap());
				}
			}
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		try (BufferedWriter out = new BufferedWriter(new FileWriter(database, false))) {
			out.write(gson.toJson(abrechnungen));
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
	}

	@Override
	public void entferneAbrechnung(UUID abrechungsID) {
		List<Abrechnung> abrechnungen = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			abrechnungen = gson.fromJson(br, new TypeToken<List<Abrechnung>>() {
			}.getType());
			abrechnungen = abrechnungen.stream()
					.filter(abrechnung -> abrechnung.getAbrechnungsID().equals(abrechungsID))
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		try (BufferedWriter out = new BufferedWriter(new FileWriter(database, false))) {
			out.write(gson.toJson(abrechnungen));
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
	}
}
