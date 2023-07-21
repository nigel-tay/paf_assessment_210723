package vttp2023.batch3.assessment.paf.bookings.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2023.batch3.assessment.paf.bookings.models.Form;

@Controller
@RequestMapping
public class ListingsController {

	//TODO: Task 2
	@GetMapping("/")
	public String getLanding(Model m) {
		m.addAttribute("form", new Form());
		return "landing";
	}
	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
