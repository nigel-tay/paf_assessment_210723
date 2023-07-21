package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.assessment.paf.bookings.models.Details;
import vttp2023.batch3.assessment.paf.bookings.models.Listing;
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
	public List<Listing> getListingsFromMongo(String country, String numberOfPerson, String min, String max) {
		Integer intNumOfPerson = Integer.parseInt(numberOfPerson);
		Double doubleMin = Double.parseDouble(min);
		Double doubleMax = Double.parseDouble(max);

		List<Document> listingResults = lRepo.getListingsFromMongo(country, intNumOfPerson, doubleMin, doubleMax);
		if (listingResults.isEmpty()) {
			return null;
		}
		
		List<Listing> listingsList = new ArrayList<>();
		for (Document listingDoc: listingResults) {
			Listing listing = new Listing();
			listing.setId(listingDoc.getString("_id"));
			listing.setAddress(listingDoc.getString("street"));
			listing.setPrice(listingDoc.getDouble("price"));
			listing.setImage(listingDoc.getString("picture_url"));
			listingsList.add(listing);
		}
		System.out.println(listingsList);
		return listingsList;
	}

	//TODO: Task 4
	public Details getDetails(String id) {
		List<Document> detailsDocumentList = lRepo.getDetails(id);
		if (detailsDocumentList.isEmpty()) {
			return null;
		}

		Document detailsDoc = detailsDocumentList.get(0);
		Details details = new Details();
		details.setId(detailsDoc.getString("_id"));
		details.setDescription(detailsDoc.getString("description"));
		details.setStreetAddress(detailsDoc.getString("street"));
		details.setSuburb(detailsDoc.getString("suburb"));
		details.setCountry(detailsDoc.getString("country"));
		details.setImage(detailsDoc.getString("picture_url"));
		details.setPrice(detailsDoc.getDouble("price"));
		details.setAmenities(detailsDoc.getList("amenities", String.class));

		System.out.println(">>>>>>>>>> service"+details.toString());
		return details;
	}

	//TODO: Task 5


}
