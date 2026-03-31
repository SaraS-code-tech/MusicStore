package com.sarascattone.MusicStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarascattone.MusicStore.Services.FilterService;

@RestController
public class FilterController {

	@Autowired
	private FilterService filterService;
	
	@GetMapping("/singleCategorie")
	public List<String> getSingleCategorie(String type) {
		return filterService.getSingleCategorie(type);
	}
}
