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

import id.posyandu.domain.Assigment;
import id.posyandu.domain.Jabatan;
import id.posyandu.domain.User;
import id.posyandu.service.AssigmentService;
import id.posyandu.service.JabatanService;
import id.posyandu.service.UserService;

@Controller
@ComponentScan
public class AssigmentController {
	@Autowired
    UserService userService;
	
	@Autowired
	AssigmentService assigmentService;
	
	@Autowired
	JabatanService jabatanService;
	
	@RequestMapping(value = {"/assigment", "/assigment/savepage"}, method = RequestMethod.GET)
    public String index(Model model) {
        
        model.addAttribute("allAssigments", (ArrayList<Assigment>) assigmentService.getAllAssigments());
        model.addAttribute("allUsers", (Collection<User>) userService.getAllUsers());
        model.addAttribute("allJabatans", (Collection<Jabatan>) jabatanService.getAllJabatans());
        return "/assigment/index";
    }
	
	@RequestMapping(value = "/assigment/create", method = RequestMethod.GET)
    public String viewForm(Model model){
        
       model.addAttribute("assigment", new Assigment());
       model.addAttribute("allUsers", (Collection<User>) userService.getAllUsers());
       model.addAttribute("allJabatans", (Collection<Jabatan>) jabatanService.getAllJabatans());
        
        return "/assigment/create";
    }
	
	@RequestMapping(value = {"/assigment/save"}, method = RequestMethod.POST)
    public String saveAssigment(@ModelAttribute("assigment") Assigment assigment,
            final RedirectAttributes redirectAttributes) {

        if (assigmentService.saveAssigment(assigment) != null) {
            redirectAttributes.addFlashAttribute("save", "success");
        } else {
            redirectAttributes.addFlashAttribute("save", "unsuccess");
        }

        return "redirect:/assigment/savepage";
    }
	
	@RequestMapping(value = "/assigment/{operation}/{assigmentId}", method = RequestMethod.GET)
    public String editRemoveBalita(@PathVariable("operation") String operation,
            @PathVariable("assigmentId") String assigmentId, final RedirectAttributes redirectAttributes,
            Model model) {
        if (operation.equals("delete")) {
            if (assigmentService.deleteAssigment(assigmentId)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            Assigment assigment = assigmentService.findAssigment(assigmentId);
            if (assigment != null) {
                model.addAttribute("assigment", assigment);
                model.addAttribute("allUsers", (Collection<User>) userService.getAllUsers());
                model.addAttribute("allJabatans", (Collection<Jabatan>) jabatanService.getAllJabatans());
                return "/assigment/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        } else if (operation.equals("view")) {
        	Assigment assigment = assigmentService.findAssigment(assigmentId);
            if (assigment != null) {
                model.addAttribute("assigment", assigment);
                return "/assigment/view";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }

        return "redirect:/assigment/savepage";
    }
	
	@RequestMapping(value = "/assigment/update/{assigmentId}", method = RequestMethod.POST)
	public String updateBalita(@PathVariable("assigmentId") String assigmentId, 
	    		Assigment assigment, 
	    		final RedirectAttributes redirectAttributes){
	    	
			assigment.setAssigmentId(assigmentId);
	    		    	
	    	
	    	if (assigmentService.saveAssigment(assigment) != null) {
	            redirectAttributes.addFlashAttribute("edit", "success");
	        } else {
	            redirectAttributes.addFlashAttribute("edit", "unsuccess");
	        }
	    	
	    	return "redirect:/assigment/savepage";
	    }
	
	
}
