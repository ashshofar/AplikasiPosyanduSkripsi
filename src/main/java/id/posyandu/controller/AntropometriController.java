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
                return "/antro/bbu/view";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }

        return "redirect:/antro/bbu/savepage";
    }
    
    @RequestMapping(value = "/antro/bbu/update/{antroId}", method = RequestMethod.POST)
    public String updateAntroBbu(@PathVariable("antroId") String antroId, 
    		Antropometri antro,
     		final RedirectAttributes redirectAttributes){
     	     	
     	antro.setAntroId(antroId);
     	     	
     	if (antroService.saveAntro(antro) != null) {
             redirectAttributes.addFlashAttribute("edit", "success");
         } else {
             redirectAttributes.addFlashAttribute("edit", "unsuccess");
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
    
    @RequestMapping(value = "/antro/bbt/createl", method = RequestMethod.GET)
    public String viewFormBbtL(Model model){
        
       model.addAttribute("antro", new Antropometri());
        
        return "/antro/bbt/createl";
    }
    
    @RequestMapping(value = "/antro/bbt/createp", method = RequestMethod.GET)
    public String viewFormBbtP(Model model){
        
       model.addAttribute("antro", new Antropometri());
        
        return "/antro/bbt/createp";
    }
    
    @RequestMapping(value = {"/antro/bbt/save"}, method = RequestMethod.POST)
    public String saveAntroBbt(@ModelAttribute("antro") Antropometri antro,
    		final RedirectAttributes redirectAttributes) {

        if (antroService.saveAntro(antro) != null) {
            redirectAttributes.addFlashAttribute("save", "success");
        } else {
            redirectAttributes.addFlashAttribute("save", "unsuccess");
        }
        
        return "redirect:/antro/bbt/savepage";
    }
    
    @RequestMapping(value = "/antro/bbt/{operation}/{antroId}", method = RequestMethod.GET)
    public String editRemoveAntroBbt(@PathVariable("operation") String operation,
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
                return "/antro/bbt/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        } else if (operation.equals("view")) {
        	Antropometri antro = antroService.findAntro(antroId);
            if (antro != null) {
                model.addAttribute("antro", antro);
                return "/antro/bbt/view";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }

        return "redirect:/antro/bbt/savepage";
    }
    
    @RequestMapping(value = "/antro/bbt/update/{antroId}", method = RequestMethod.POST)
    public String updateAntroBbt(@PathVariable("antroId") String antroId, 
    		Antropometri antro,
     		final RedirectAttributes redirectAttributes){
     	     	
     	antro.setAntroId(antroId);
     	     	
     	if (antroService.saveAntro(antro) != null) {
             redirectAttributes.addFlashAttribute("edit", "success");
         } else {
             redirectAttributes.addFlashAttribute("edit", "unsuccess");
         }
     	
     	return "redirect:/antro/bbt/savepage";
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
    
    @RequestMapping(value = "/antro/tbu/createl", method = RequestMethod.GET)
    public String viewFormTbuL(Model model){
        
       model.addAttribute("antro", new Antropometri());
        
        return "/antro/tbu/createl";
    }
    
    @RequestMapping(value = "/antro/tbu/createp", method = RequestMethod.GET)
    public String viewFormTbuP(Model model){
        
       model.addAttribute("antro", new Antropometri());
        
        return "/antro/tbu/createp";
    }
    
    @RequestMapping(value = {"/antro/tbu/save"}, method = RequestMethod.POST)
    public String saveAntroTbu(@ModelAttribute("antro") Antropometri antro,
    		final RedirectAttributes redirectAttributes) {

        if (antroService.saveAntro(antro) != null) {
            redirectAttributes.addFlashAttribute("save", "success");
        } else {
            redirectAttributes.addFlashAttribute("save", "unsuccess");
        }
        
        return "redirect:/antro/tbu/savepage";
    }
    
    @RequestMapping(value = "/antro/tbu/{operation}/{antroId}", method = RequestMethod.GET)
    public String editRemoveAntroTbu(@PathVariable("operation") String operation,
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
                return "/antro/tbu/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        } else if (operation.equals("view")) {
        	Antropometri antro = antroService.findAntro(antroId);
            if (antro != null) {
                model.addAttribute("antro", antro);
                return "/antro/tbu/view";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }

        return "redirect:/antro/tbu/savepage";
    }
    
    @RequestMapping(value = "/antro/tbu/update/{antroId}", method = RequestMethod.POST)
    public String updateAntroTbu(@PathVariable("antroId") String antroId, 
    		Antropometri antro,
     		final RedirectAttributes redirectAttributes){
     	     	
     	antro.setAntroId(antroId);
     	     	
     	if (antroService.saveAntro(antro) != null) {
             redirectAttributes.addFlashAttribute("edit", "success");
         } else {
             redirectAttributes.addFlashAttribute("edit", "unsuccess");
         }
     	
     	return "redirect:/antro/tbu/savepage";
     }

}