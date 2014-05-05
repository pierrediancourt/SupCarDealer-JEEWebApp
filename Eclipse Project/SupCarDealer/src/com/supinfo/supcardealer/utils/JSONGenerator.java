package com.supinfo.supcardealer.utils;

import java.util.ArrayList;

import com.supinfo.supcardealer.entities.Car;

public class JSONGenerator {

	/**
	 * Transforme des données de voitures au format JSON attendu par le slider JS
	 * @param cars			Liste d'informations sur des voitures
	 * @param contextUrl	URL de la racine du site
	 * @return				Données transformées au format JSON
	 */
	public static String getCarsInfo(ArrayList<Car> cars, String contextUrl) {
		
		String carsJSON = "";
		int i = 0;
		for(Car c : cars) {
			carsJSON += "{";
			carsJSON += "\"title\": \"" + c.getName() + "\",";
			carsJSON += "\"description\": \"" + "" + "\",";
			if(c.getImageUrl() != null) {
				carsJSON += "\"image\": \"img/cars/" + c.getImageUrl() + "\",";
			} else {
				carsJSON += "\"image\": \"img/cars/default.jpg" + "\",";
			}
			carsJSON += "\"link\": \"" + contextUrl + "/car?id=" + c.getId() + "\",";
			carsJSON += "\"duration\": \"5\",";
			carsJSON += "}";
			if(++i != cars.size()) carsJSON += ",";
		}
		return "[" + carsJSON + "]";
	}
	
}
