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

import id.posyandu.domain.Antropometri;
import id.posyandu.domain.Kategori;
import id.posyandu.service.AntropometriService;
import id.posyandu.service.KategoriService;

@Controller
@ComponentScan
public class AntropometriController {
	
	@Autowired
    KategoriService kategoriService;
    
    @Autowired
    AntropometriService antroService;
    
    @RequestMapping(value = {"/antro", "/antro/savepage"}, method = RequestMethod.GET)
    public String index(Model model) {
        
        model.addAttribute("allAntros", (ArrayList<Antropometri>) antroService.getAllAntros());
        model.addAttribute("allKategoris", (Collection<Kategori>) kategoriService.getAllKategoris());
        return "/antro/index";
    }
    
    @RequestMapping(value = "/antro/create", method = RequestMethod.GET)
    public String viewForm(Model model){
        
       model.addAttribute("antro", new Antropometri());
       model.addAttribute("allKategoris", (Collection<Kategori>) kategoriService.getAllKategoris());
        
        return "/antro/create";
    }
    
    @RequestMapping(value = {"/antro/save"}, method = RequestMethod.POST)
    public String saveAntro(@ModelAttribute("antro") Antropometri antro,
            final RedirectAttributes redirectAttributes) {

        if (antroService.saveAntro(antro) != null) {
            redirectAttributes.addFlashAttribute("save", "success");
        } else {
            redirectAttributes.addFlashAttribute("save", "unsuccess");
        }

        return "redirect:/antro/savepage";
    }
    
    @RequestMapping(value = "/antro/{operation}/{antroId}", method = RequestMethod.GET)
    public String editRemoveAntro(@PathVariable("operation") String operation,
            @PathVariable("antroId") String antroId, final RedirectAttributes redirectAttributes,
            Model model) {
        if (operation.equals("delete")) {
            if (antroService.deleteAntro(antroId)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            Antropometri antro = antroService.findAntro(antroId);
            if (antro != null) {
                model.addAttribute("antro", antro);
                model.addAttribute("allKategoris", (Collection<Kategori>) kategoriService.getAllKategoris());
                return "/antro/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        } else if (operation.equals("view")) {
        	Antropometri antro = antroService.findAntro(antroId);
            if (antro != null) {
                model.addAttribute("antro", antro);
                return "/antro/view";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }

        return "redirect:/antro/savepage";
    }
    
    @RequestMapping(value = "/antro/update/{antroId}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("antroId") String antroId, 
    		Kategori kategori,
    		String jenisKelamin,
     		int umur,
     		float minus3sd,
    		float minus2sd,
    		float minus1sd,
    		float median,
    		float plus1sd,
    		float plus2sd,
    		float plus3sd,     		
     		final RedirectAttributes redirectAttributes){
     	Antropometri antro;
     	antro = antroService.findAntro(antroId);
     	antro.setAntroId(antroId);
     	antro.setKategori(kategori);
     	antro.setJenisKelamin(jenisKelamin);
     	antro.setUmur(umur);
     	antro.setMinus3sd(minus3sd);
     	antro.setMinus2sd(minus2sd);
     	antro.setMinus1sd(minus1sd);
     	antro.setMedian(median);
     	antro.setPlus1sd(plus1sd);
     	antro.setPlus2sd(plus2sd);
     	antro.setPlus3sd(plus3sd);
     	
     	if (antroService.saveAntro(antro) != null) {
             redirectAttributes.addFlashAttribute("edit", "success");
         } else {
             redirectAttributes.addFlashAttribute("edit", "unsuccess");
         }
     	
     	return "redirect:/antro/savepage";
     }

}