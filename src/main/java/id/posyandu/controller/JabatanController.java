package id.posyandu.controller;

import id.posyandu.domain.Jabatan;
import id.posyandu.service.JabatanService;
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


@Controller
@ComponentScan
public class JabatanController {
    
    @Autowired
    private JabatanService jabatanService;
    
    @RequestMapping(value = {"/jabatan", "/jabatan/savepage"}, method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("allJabatans", (Collection<Jabatan>) jabatanService.getAllJabatans());
        
        return "/jabatan/index";
    }
    
    @RequestMapping(value = "/jabatan/create", method = RequestMethod.GET)
    public String viewForm(Model model){
        
        model.addAttribute("jabatan", new Jabatan());
        
        return "/jabatan/create";
    }
    
    @RequestMapping(value = {"/jabatan/save"}, method = RequestMethod.POST)
    public String saveJabatan(@ModelAttribute("jabatan") Jabatan jabatan,
            final RedirectAttributes redirectAttributes) {

        if (jabatanService.saveJabatan(jabatan) != null) {
            redirectAttributes.addFlashAttribute("save", "success");
        } else {
            redirectAttributes.addFlashAttribute("save", "unsuccess");
        }

        return "redirect:/jabatan/savepage";
    }
    
    @RequestMapping(value = "/jabatan/{operation}/{jabatanId}", method = RequestMethod.GET)
    public String editRemoveJabatan(@PathVariable("operation") String operation,
            @PathVariable("jabatanId") String jabatanId, final RedirectAttributes redirectAttributes,
            Model model) {
        if (operation.equals("delete")) {
            if (jabatanService.deleteJabatan(jabatanId)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            Jabatan jabatan = jabatanService.findJabatan(jabatanId);
            if (jabatan != null) {
                model.addAttribute("jabatan", jabatan);
                return "/jabatan/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        } else if (operation.equals("view")) {
            Jabatan jabatan = jabatanService.findJabatan(jabatanId);
            if (jabatan != null) {
                model.addAttribute("jabatan", jabatan);
                return "/jabatan/view";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }

        return "redirect:/jabatan/savepage";
    }
    
    @RequestMapping(value = "/jabatan/update/{jabatanId}", method = RequestMethod.POST)
    public String update(@PathVariable("jabatanId") String jabatanId, 
    		String namaJabatan, 
    		String deskripsi,
    		final RedirectAttributes redirectAttributes){
    	Jabatan jabatan;
    	jabatan = jabatanService.findJabatan(jabatanId);
    	jabatan.setJabatanId(jabatanId);
    	jabatan.setNamaJabatan(namaJabatan);
    	jabatan.setDeskripsi(deskripsi);
    	
    	if (jabatanService.saveJabatan(jabatan) != null) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
    	
    	return "redirect:/jabatan/savepage";
    }
    
 }