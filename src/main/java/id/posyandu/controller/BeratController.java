package id.posyandu.controller;

import java.util.ArrayList;
import java.util.Collection;


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
import id.posyandu.service.BalitaService;
import id.posyandu.service.BeratService;

@Controller
@ComponentScan
public class BeratController {

	@Autowired
	BalitaService balitaService;
	
	@Autowired
	BeratService beratService;
	
	@RequestMapping(value = {"/berat", "/berat/savepage"}, method = RequestMethod.GET)
    public String index(Model model) {
        
        model.addAttribute("allBerats", (ArrayList<Berat>) beratService.getAllBerats());
        model.addAttribute("allBalitas", (Collection<Balita>) balitaService.getAllBalitas());
        return "/berat/index";
    }
	
	@RequestMapping(value = "/berat/create", method = RequestMethod.GET)
    public String viewForm(Model model){
        
       model.addAttribute("berat", new Berat());
       model.addAttribute("allBalitas", (Collection<Balita>) balitaService.getAllBalitas());
        
        return "/berat/create";
    }
	
	@RequestMapping(value = {"/berat/save"}, method = RequestMethod.POST)
    public String saveBerat(@ModelAttribute("berat") Berat berat,
            final RedirectAttributes redirectAttributes) {

        if (beratService.saveBerat(berat) != null) {
            redirectAttributes.addFlashAttribute("save", "success");
        } else {
            redirectAttributes.addFlashAttribute("save", "unsuccess");
        }

        return "redirect:/berat/savepage";
    }
	
	@RequestMapping(value = "/berat/{operation}/{beratId}", method = RequestMethod.GET)
    public String editRemoveBerat(@PathVariable("operation") String operation,
            @PathVariable("beratId") String beratId, final RedirectAttributes redirectAttributes,
            Model model) {
        if (operation.equals("delete")) {
            if (beratService.deleteBerat(beratId)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            Berat berat = beratService.findBerat(beratId);
            if (berat != null) {
                model.addAttribute("berat", berat);
                model.addAttribute("allBalitas", (Collection<Balita>) balitaService.getAllBalitas());
                return "/berat/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        } else if (operation.equals("view")) {
        	Berat berat = beratService.findBerat(beratId);
            if (berat != null) {
            	model.addAttribute("berat", berat);
                return "/berat/view";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }

        return "redirect:/berat/savepage";
    }
	
	@RequestMapping(value = "/berat/update/{beratId}", method = RequestMethod.POST)
	public String updateBerat(@PathVariable("beratId") String beratId, 
	    		Berat berat,
	    		final RedirectAttributes redirectAttributes){
	    	
	    	berat.setBeratId(beratId);
	    	    	
	    	
	    	if (beratService.saveBerat(berat) != null ) {
	            redirectAttributes.addFlashAttribute("edit", "success");
	        } else {
	            redirectAttributes.addFlashAttribute("edit", "unsuccess");
	        }
	    	
	    	return "redirect:/berat/savepage";
	    }
}
