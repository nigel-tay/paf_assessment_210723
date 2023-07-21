package vttp2023.batch3.assessment.paf.bookings.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.assessment.paf.bookings.models.BookingForm;
import vttp2023.batch3.assessment.paf.bookings.models.Details;
import vttp2023.batch3.assessment.paf.bookings.models.Listing;
import vttp2023.batch3.assessment.paf.bookings.models.Reservation;
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

		return details;
	}

	//TODO: Task 5
	public String bookListing(BookingForm bookingForm, String id) {
		// pass bookingForm to repo
		if (lRepo.checkVacancy(bookingForm.getStay(), id)) {
			String resv_id = UUID.randomUUID().toString().substring(0, 8);
			System.out.println(resv_id);
    		String name = bookingForm.getName();
    		String email = bookingForm.getEmail();
    		String acc_id = id;
    		Date arrival_date = bookingForm.getArrival();
    		Integer duration = bookingForm.getStay();

			Reservation newReservation = new Reservation(resv_id, name, email, acc_id, arrival_date, duration);

			if (lRepo.insertReservation(newReservation)) {
				// update vacancy
				if (lRepo.updateVacancy(newReservation)) {
					return resv_id;
				}
			}
		}
		return null;
	}
}
