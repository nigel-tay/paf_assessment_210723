package vttp2023.batch3.assessment.paf.bookings.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2023.batch3.assessment.paf.bookings.models.Form;
import vttp2023.batch3.assessment.paf.bookings.models.Listing;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;
import vttp2023.batch3.assessment.paf.bookings.services.ListingsService;

@Controller
@RequestMapping
public class ListingsController {

	@Autowired
	ListingsService lService;

	//TODO: Task 2
	@GetMapping("/")
	public String getLanding(Model m) {
		List<String> countryList = lService.getCountriesFromMongo();
		m.addAttribute("countryList", countryList);
		m.addAttribute("form", new Form());
		
		return "landing";
	}
	
	//TODO: Task 3
	// http://localhost:8080/search?country=China&numberOfPerson=2&min=100&max=200
	@GetMapping("/search")
	public String getListings(@RequestParam Map<String, String> params, Model m) {
		String country = params.get("country");
		String numberOfPerson = params.get("numberOfPerson");
		String min = params.get("min");
		String max = params.get("max");

		List<Listing> listingsList = lService.getListingsFromMongo(country, numberOfPerson, min, max);
		m.addAttribute("country", country);
		m.addAttribute("listingsList", listingsList);
		return "listings";
	}

	//TODO: Task 4
	

	//TODO: Task 5


}
