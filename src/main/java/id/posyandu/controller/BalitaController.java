package id.posyandu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.posyandu.domain.Balita;
import id.posyandu.domain.User;
import id.posyandu.service.BalitaService;
import id.posyandu.service.UserService;

@Controller
@ComponentScan
public class BalitaController {
	
	@Autowired
    UserService userService;
	
	@Autowired
	BalitaService balitaService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@RequestMapping(value = {"/balita", "/balita/savepage"}, method = RequestMethod.GET)
    public String index(Model model) {
        
        model.addAttribute("allBalitas", (ArrayList<Balita>) balitaService.getAllBalitas());
        model.addAttribute("allUsers", (Collection<User>) userService.getAllUsers());
        return "/balita/index";
    }
	
	@RequestMapping(value = "/balita/create", method = RequestMethod.GET)
    public String viewForm(Model model){
        
       model.addAttribute("balita", new Balita());
       model.addAttribute("allUsers", (Collection<User>) userService.getAllUsers());
        
        return "/Balita/create";
    }
	
	@RequestMapping(value = {"/balita/save"}, method = RequestMethod.POST)
    public String saveBalita(@ModelAttribute("balita") Balita balita,
            final RedirectAttributes redirectAttributes) {

        if (balitaService.saveBalita(balita) != null) {
            redirectAttributes.addFlashAttribute("save", "success");
        } else {
            redirectAttributes.addFlashAttribute("save", "unsuccess");
        }

        return "redirect:/balita/savepage";
    }
	
	@RequestMapping(value = "/balita/{operation}/{balitaId}", method = RequestMethod.GET)
    public String editRemoveBalita(@PathVariable("operation") String operation,
            @PathVariable("balitaId") String balitaId, final RedirectAttributes redirectAttributes,
            Model model) {
        if (operation.equals("delete")) {
            if (balitaService.deleteBalita(balitaId)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            Balita balita = balitaService.findBalita(balitaId);
            if (balita != null) {
                model.addAttribute("balita", balita);
                model.addAttribute("allUsers", (Collection<User>) userService.getAllUsers());
                return "/balita/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        } else if (operation.equals("view")) {
            Balita balita = balitaService.findBalita(balitaId);
            if (balita != null) {
                model.addAttribute("balita", balita);
                return "/balita/view";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }

        return "redirect:/balita/savepage";
    }
	
	@RequestMapping(value = "/balita/update/{balitaId}", method = RequestMethod.POST)
	public String updateBalita(@PathVariable("balitaId") String balitaId, 
	    		String nama, 
	    		String tempatLahir,
	    		Date tanggalLahir,
	    		String jenisKelamin,
	    		String foto,
	    		float beratLahir,
	    		float tinggiLahir,
	    		User ayah,
	    		//User ibu,
	    		final RedirectAttributes redirectAttributes){
	    	Balita balita;
	    	balita = balitaService.findBalita(balitaId);
	    	balita.setBalitaId(balitaId);
	    	balita.setNama(nama);
	    	balita.setTempatLahir(tempatLahir);
	    	balita.setTanggalLahir(tanggalLahir);
	    	balita.setJenisKelamin(jenisKelamin);
	    	balita.setFoto(foto);
	    	balita.setBeratLahir(beratLahir);
	    	balita.setTinggiLahir(tinggiLahir);
	    	balita.setAyah(ayah);
	    	//balita.setIbu(ibu);
	    	
	    	
	    	if (balitaService.saveBalita(balita) != null) {
	            redirectAttributes.addFlashAttribute("edit", "success");
	        } else {
	            redirectAttributes.addFlashAttribute("edit", "unsuccess");
	        }
	    	
	    	return "redirect:/balita/savepage";
	    }
}
