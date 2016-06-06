package id.posyandu.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.posyandu.domain.Balita;
import id.posyandu.domain.Berat;
import id.posyandu.domain.Tinggi;
import id.posyandu.service.BalitaService;
import id.posyandu.service.BeratService;
import id.posyandu.service.TinggiService;

@Controller
@ComponentScan
public class BeratTinggiController {
	
	@Autowired
	BalitaService balitaService;
	
	@Autowired
	TinggiService tinggiService;
	
	@Autowired
	BeratService beratService;
	
	@RequestMapping(value = {"/bt", "/bt/savepage"}, method = RequestMethod.GET)
    public String index(Model model) {
        
        model.addAttribute("allBalitas", (ArrayList<Balita>) balitaService.getAllBalitas());
        return "/bt/index";
    }
	
	@RequestMapping(value = "/bt/create/{balitaId}", method = RequestMethod.GET)
	public String createBeratTinggi(@PathVariable("balitaId") String balitaId, 
			final RedirectAttributes redirectAttributes,
            Model model){
				
		Balita balita = balitaService.findBalita(balitaId);
			
		model.addAttribute("balita", balita);
		model.addAttribute("berat", new Berat());
        model.addAttribute("tinggi", new Tinggi());
            
        return "/bt/create";
    }
	
	@RequestMapping(value = "/bt/save", method = RequestMethod.POST)
	public String saveBeratTinggi(@ModelAttribute("berat") Berat berat,
				@ModelAttribute("tinggi") Tinggi tinggi,
				//int umurTinggi,
				final RedirectAttributes redirectAttributes){
	    	   	
				//umurTinggi = berat.getUmur();
				//tinggi.setUmur(umurTinggi);
				
	    	if (beratService.saveBerat(berat) != null) {
	            redirectAttributes.addFlashAttribute("edit", "success");
	        } else {
	            redirectAttributes.addFlashAttribute("edit", "unsuccess");
	        }
	    	
	    	tinggiService.saveTinggi(tinggi);
	    	
	    	return "redirect:/bt/savepage";
	    }

}
