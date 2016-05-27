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
    		Antropometri antro,
     		final RedirectAttributes redirectAttributes){
     	     	
     	antro.setAntroId(antroId);
     	     	
     	if (antroService.saveAntro(antro) != null) {
             redirectAttributes.addFlashAttribute("edit", "success");
         } else {
             redirectAttributes.addFlashAttribute("edit", "unsuccess");
         }
     	
     	return "redirect:/antro/savepage";
     }
    
    
   /*     Controller Untuk Index Antropometri
    *     BERAT BADAN BERDASARKAN UMUR
    *     BB/U  
    */

    @RequestMapping(value = {"/antro/bbu", "/antro/bbu/savepage"}, method = RequestMethod.GET)
    public String bbuIndex(Model model) {
        
        model.addAttribute("allAntrosL", (ArrayList<Antropometri>) antroService.getBbuAntrosL());
        model.addAttribute("allAntrosP", (ArrayList<Antropometri>) antroService.getBbuAntrosP());
        model.addAttribute("allKategoris", (Collection<Kategori>) kategoriService.getAllKategoris());
        return "/antro/bbu/index";
    }
    
    @RequestMapping(value = "/antro/bbu/createl", method = RequestMethod.GET)
    public String viewFormBbuL(Model model){
        
       model.addAttribute("antro", new Antropometri());
        
        return "/antro/bbu/createl";
    }
    
    @RequestMapping(value = "/antro/bbu/createp", method = RequestMethod.GET)
    public String viewFormBbuP(Model model){
        
       model.addAttribute("antro", new Antropometri());
        
        return "/antro/bbu/createp";
    }
    
    @RequestMapping(value = {"/antro/bbu/save"}, method = RequestMethod.POST)
    public String saveAntroBbu(@ModelAttribute("antro") Antropometri antro,
    		final RedirectAttributes redirectAttributes) {

        if (antroService.saveAntro(antro) != null) {
            redirectAttributes.addFlashAttribute("save", "success");
        } else {
            redirectAttributes.addFlashAttribute("save", "unsuccess");
        }
        
        return "redirect:/antro/bbu/savepage";
    }
    
    @RequestMapping(value = "/antro/bbu/{operation}/{antroId}", method = RequestMethod.GET)
    public String editRemoveAntroBbu(@PathVariable("operation") String operation,
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
                return "/antro/bbu/edit";
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

        return "redirect:/antro/bbu/savepage";
    }
    
    /*     Controller Untuk Index Antropometri
     *     BERAT BADAN BERDASARKAN TINGGI
     *     BB/T  
     */
    @RequestMapping(value = {"/antro/bbt", "/antro/bbt/savepage"}, method = RequestMethod.GET)
    public String bbtIndex(Model model) {
        
        model.addAttribute("allAntrosL", (ArrayList<Antropometri>) antroService.getBbtAntrosL());
        model.addAttribute("allAntrosP", (ArrayList<Antropometri>) antroService.getBbtAntrosP());
        model.addAttribute("allKategoris", (Collection<Kategori>) kategoriService.getAllKategoris());
        return "/antro/bbt/index";
    }
    
    /*     Controller Untuk Index Antropometri
     *     TINGGI BADAN BERDASARKAN UMUR
     *     BB/T  
     */
    @RequestMapping(value = {"/antro/tbu", "/antro/tbu/savepage"}, method = RequestMethod.GET)
    public String tbuIndex(Model model) {
        
        model.addAttribute("allAntrosL", (ArrayList<Antropometri>) antroService.getTbuAntrosL());
        model.addAttribute("allAntrosP", (ArrayList<Antropometri>) antroService.getTbuAntrosP());
        model.addAttribute("allKategoris", (Collection<Kategori>) kategoriService.getAllKategoris());
        return "/antro/tbu/index";
    }

}