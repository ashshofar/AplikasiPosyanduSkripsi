package id.posyandu.controller;

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

import id.posyandu.domain.Kategori;
import id.posyandu.service.KategoriService;

@Controller
@ComponentScan
public class KategoriController {
	
	@Autowired
    private KategoriService kategoriService;
    
    @RequestMapping(value = {"/kategori", "/kategori/savepage"}, method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("allKategoris", (Collection<Kategori>) kategoriService.getAllKategoris());
        
        return "/kategori/index";
    }
    
    @RequestMapping(value = "/kategori/create", method = RequestMethod.GET)
    public String viewForm(Model model){
        
        model.addAttribute("kategori", new Kategori());
        
        return "/kategori/create";
    }
    
    @RequestMapping(value = {"/kategori/save"}, method = RequestMethod.POST)
    public String saveKategori(@ModelAttribute("kategori") Kategori kategori,
            final RedirectAttributes redirectAttributes) {

        if (kategoriService.saveKategori(kategori) != null) {
            redirectAttributes.addFlashAttribute("save", "success");
        } else {
            redirectAttributes.addFlashAttribute("save", "unsuccess");
        }

        return "redirect:/kategori/savepage";
    }
    
    @RequestMapping(value = "/kategori/{operation}/{kategoriId}", method = RequestMethod.GET)
    public String editRemoveKategori(@PathVariable("operation") String operation,
            @PathVariable("kategoriId") String kategoriId, final RedirectAttributes redirectAttributes,
            Model model) {
        if (operation.equals("delete")) {
            if (kategoriService.deleteKategori(kategoriId)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            Kategori kategori = kategoriService.findKategori(kategoriId);
            if (kategori != null) {
                model.addAttribute("kategori", kategori);
                return "/kategori/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        } else if (operation.equals("view")) {
        	Kategori kategori = kategoriService.findKategori(kategoriId);
            if (kategori != null) {
                model.addAttribute("kategori", kategori);
                return "/kategori/view";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }

        return "redirect:/kategori/savepage";
    }
    
    @RequestMapping(value = "/kategori/update/{kategoriId}", method = RequestMethod.POST)
    public String update(@PathVariable("kategoriId") String kategoriId, 
    		String kodeKategori, 
    		String namaKategori,
    		String deskripsiKategori,
    		final RedirectAttributes redirectAttributes){
    	Kategori kategori;
    	kategori = kategoriService.findKategori(kategoriId);
    	kategori.setKategoriId(kategoriId);
    	kategori.setKodeKategori(kodeKategori);
    	kategori.setNamaKategori(namaKategori);
    	kategori.setDeskripsiKategori(deskripsiKategori);
    	
    	if (kategoriService.saveKategori(kategori) != null) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
    	
    	return "redirect:/kategori/savepage";
    }

}
