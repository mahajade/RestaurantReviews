package ca.sheridancollege.mahajade.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.mahajade.beans.Review;
import ca.sheridancollege.mahajade.database.DatabaseAccess;

@Controller
public class ReviewController {

	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String getIndex(Model model, Review review) {
		ArrayList<Review> list =  da.getReviews();
		model.addAttribute("list", list);
		return "index";
	}
	
	@PostMapping("/")
	public String postIndex(Model model, @ModelAttribute Review review) {
		da.addReview(review);
		ArrayList<Review> list =  da.getReviews();
		model.addAttribute("list", list);
		return "index";
	}
	
	@GetMapping("/form")
	public String getForm(Review review) {
		return "form";
	}
	
	@GetMapping("/edit/{name}")
	public String editReview(@PathVariable String name, Review review, Model model) {
		review = da.getReview(name);
		model.addAttribute("review", review);
		return "edit";
	}
	
	@PostMapping("/update")
	public String saveReview(@ModelAttribute Review review, Model model) {
		da.updateReview(review);
		ArrayList<Review> list =  da.getReviews();
		model.addAttribute("list", list);
		return "index";
	}
	
	@GetMapping("/delete/{name}")
	public String deleteReview(@PathVariable String name, Model model) {
		da.deleteReview(name);
		ArrayList<Review> list = da.getReviews();
		model.addAttribute("list", list);
		return "index";
	}
	
}
