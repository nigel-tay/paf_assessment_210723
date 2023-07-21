package vttp2023.batch3.assessment.paf.bookings.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2023.batch3.assessment.paf.bookings.models.Form;
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


	//TODO: Task 4
	

	//TODO: Task 5


}
