package com.sarascattone.MusicStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sarascattone.MusicStore.Services.CartService;
import com.sarascattone.MusicStore.Services.FilterService;
import com.sarascattone.MusicStore.Services.HomeService;
import com.sarascattone.MusicStore.Utilities.Products;

@Controller
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	@Autowired
	private FilterService filterService;
	@Autowired
	private CartService cartService;

	@GetMapping({"/", "/category"})
	public String index(Model model, @RequestParam(required = false) String name) {

		List<Products> products;
		
		if(name == null) {			
			products = homeService.getAllProducts();
			
			model.addAttribute("categoryName", "All Products");
		}
		else {
			products = filterService.getFilteredProducts(name);
			
			model.addAttribute("categoryName", name);
		}
		
		model.addAttribute("products", products);
		
		return "index";
	}
	
	@GetMapping("/search")
	public String indexFromSearch(Model model, @RequestParam String search) {
		
		List<Products> products = filterService.getProductsFromSearch(search);
		
		if(products.isEmpty())
			model.addAttribute("categoryName", "Nessun risultato trovato per la ricerca: " + search);
		else
			model.addAttribute("categoryName", "Risultato ricerca: " + search);
		
		model.addAttribute("products", products);
		
		return "index";
	}
	
	 @GetMapping("/cart")
	 public String cartPage(Model model) {
		 
		 model.addAttribute("products", cartService.getCart(1));
		 
		 return "cart";
	 }
	 
	 @GetMapping("/user")
	 public String userPage(Model model) {
		 
		 return "user";
	 }
}