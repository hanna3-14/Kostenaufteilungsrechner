package de.dhbw.kostenaufteilungsrechner.l1.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.dhbw.kostenaufteilungsrechner.l3.domain.Gruppe;
import de.dhbw.kostenaufteilungsrechner.l3.domain.GruppeRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GruppeDBAdapter implements GruppeRepository {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	String database = "datenbank/gruppen.json";

	List<Gruppe> gruppenList = new ArrayList<>();

	@Override
	public void erstelleGruppe(Gruppe neueGruppe) {
		try {
			Iterable<Gruppe> gruppen = this.findeAlleGruppen();
			for (Gruppe gruppe : gruppen) {
				gruppenList.add(gruppe);
			}
		} catch (Exception e) {

		}
		gruppenList.add(neueGruppe);
		try (BufferedWriter out = new BufferedWriter(new FileWriter(database, false))) {
			out.write(gson.toJson(gruppenList));
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
	}

	@Override
	public Iterable<Gruppe> findeAlleGruppen() {
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			Iterable<Gruppe> gruppen = gson.fromJson(br, new TypeToken<List<Gruppe>>() {
			}.getType());
			return gruppen;
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		return null;
	}

	@Override
	public Optional<Gruppe> findeGruppeÜberName(String gruppenName) {
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			Iterable<Gruppe> gruppen = gson.fromJson(br, new TypeToken<List<Gruppe>>() {
			}.getType());
			for (Gruppe g : gruppen) {
				if (g.getGruppenName().equals(gruppenName)) {
					return Optional.of(g);
				}
			}
			return Optional.empty();
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		return Optional.empty();
	}

	@Override
	public void aktualisiereGruppe(Gruppe gruppe) {
		List<Gruppe> gruppen = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			gruppen = gson.fromJson(br, new TypeToken<List<Gruppe>>() {
			}.getType());
			for (Gruppe g : gruppen) {
				if (g.getGruppenName().equals(gruppe.getGruppenName())) {
					g.setMitgliederListe(gruppe.getMitgliederListe());
				}
			}
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		try (BufferedWriter out = new BufferedWriter(new FileWriter(database, false))) {
			out.write(gson.toJson(gruppen));
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
	}

	@Override
	public void entferneGruppe(String gruppenName) {
		List<Gruppe> gruppen = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(database))) {
			gruppen = gson.fromJson(br, new TypeToken<List<Gruppe>>() {
			}.getType());
			// Filtern der zu entfernenden Gruppe
			gruppen = gruppen.stream()
					.filter(gruppe -> !gruppe.getGruppenName().equals(gruppenName))
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
		try (BufferedWriter out = new BufferedWriter(new FileWriter(database, false))) {
			out.write(gson.toJson(gruppen));
		} catch (IOException e) {
			System.err.println("I/O Error");
		}
	}
}
