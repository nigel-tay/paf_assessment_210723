package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;

@Service
public class ListingsService {
	
	@Autowired
	ListingsRepository lRepo;

	//TODO: Task 2
	public List<String> getCountriesFromMongo() {
		List<Document> countryDocumentList = lRepo.getCountriesFromMongo();
		List<String> countryList = new ArrayList<>();
		
		for (Document country: countryDocumentList) {
			countryList.add(country.getString("_id"));
		}

		return countryList;
	}
	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
