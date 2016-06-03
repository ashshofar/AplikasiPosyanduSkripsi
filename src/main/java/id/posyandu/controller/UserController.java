package id.posyandu.controller;

import id.posyandu.domain.Assigment;
import id.posyandu.domain.Jabatan;
import id.posyandu.domain.User;
import id.posyandu.service.AssigmentService;
import id.posyandu.service.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@Controller
@ComponentScan
public class UserController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    AssigmentService assigmentService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    @RequestMapping(value = {"/user", "/user/savepage"}, method = RequestMethod.GET)
    public String index(Model model) {
        
        model.addAttribute("allUsers", (ArrayList<User>) userService.getAllUsers());
        return "/user/index";
    }
    
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String viewForm(Model model){
        
       model.addAttribute("user", new User());
       return "/user/create";
    }
    
    @RequestMapping(value = {"/user/save"}, method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user,
            final RedirectAttributes redirectAttributes) {

        if (userService.saveUser(user) != null) {
            redirectAttributes.addFlashAttribute("save", "success");
        } else {
            redirectAttributes.addFlashAttribute("save", "unsuccess");
        }

        return "redirect:/user/savepage";
    }
    
    @RequestMapping(value = "/user/{operation}/{userId}", method = RequestMethod.GET)
    public String editRemoveUser(@PathVariable("operation") String operation,
            @PathVariable("userId") String userId, final RedirectAttributes redirectAttributes,
            Model model) {
        if (operation.equals("delete")) {
            if (userService.deleteUser(userId)) {
                redirectAttributes.addFlashAttribute("deletion", "success");
            } else {
                redirectAttributes.addFlashAttribute("deletion", "unsuccess");
            }
        } else if (operation.equals("edit")) {
            User user = userService.findUser(userId);
            if (user != null) {
                model.addAttribute("user", user);
                return "/user/edit";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        } else if (operation.equals("view")) {
            User user = userService.findUser(userId);
            if (user != null) {
                model.addAttribute("user", user);
                return "/user/view";
            } else {
                redirectAttributes.addFlashAttribute("status", "notfound");
            }
        }

        return "redirect:/user/savepage";
    }
    
   @RequestMapping(value = "/user/update/{userId}", method = RequestMethod.POST)
   public String updateUser(@PathVariable("userId") String userId, 
		    User user,    		
    		final RedirectAttributes redirectAttributes){
    	   	
    	user.setUserId(userId);
    	    	
    	if (userService.saveUser(user) != null) {
            redirectAttributes.addFlashAttribute("edit", "success");
        } else {
            redirectAttributes.addFlashAttribute("edit", "unsuccess");
        }
    	
    	return "redirect:/user/savepage";
    }
   
   
   /*  
    * Controller Untuk Jabatan Petugas
    * 
    */
   
   @RequestMapping(value = {"/user/petugas", "/user/petugas/savepage"}, method = RequestMethod.GET)
   public String indexPetugas(Model model) {
       
       model.addAttribute("allPetugas", (ArrayList<User>) userService.getAllPetugas());
       return "/user/petugas/index";
   }
   
   @RequestMapping(value = "/user/petugas/create", method = RequestMethod.GET)
   public String viewFormPetugas(Model model){
       
      model.addAttribute("user", new User());
      return "/user/petugas/create";
   }
   
   @RequestMapping(value = {"/user/petugas/save"}, method = RequestMethod.POST)
   public String savePetugas(@ModelAttribute("user") User user, 
		   Assigment assigment,
		   Jabatan jabatan,
		   final RedirectAttributes redirectAttributes) {
	   
	   if (userService.saveUser(user) != null) {
           redirectAttributes.addFlashAttribute("save", "success");
       } else {
           redirectAttributes.addFlashAttribute("save", "unsuccess");
       }
	   
	   jabatan.setJabatanId("1ac99d1b-cee9-4c60-bb42-05e5399c883c");
	   assigment.setIdUser(user);
	   assigment.setIdJabatan(jabatan);
	   
	   assigmentService.saveAssigment(assigment);

       return "redirect:/user/petugas/savepage";
   }
   
   @RequestMapping(value = "/user/petugas/{operation}/{userId}", method = RequestMethod.GET)
   public String editRemovePetugas(@PathVariable("operation") String operation,
           @PathVariable("userId") String userId, final RedirectAttributes redirectAttributes,
           Model model) {
       if (operation.equals("delete")) {
           if (userService.deleteUser(userId)) {
               redirectAttributes.addFlashAttribute("deletion", "success");
           } else {
               redirectAttributes.addFlashAttribute("deletion", "unsuccess");
           }
       } else if (operation.equals("edit")) {
           User user = userService.findUser(userId);
           if (user != null) {
               model.addAttribute("user", user);
               return "/user/petugas/edit";
           } else {
               redirectAttributes.addFlashAttribute("status", "notfound");
           }
       } else if (operation.equals("view")) {
           User user = userService.findUser(userId);
           if (user != null) {
               model.addAttribute("user", user);
               return "/user/petugas/view";
           } else {
               redirectAttributes.addFlashAttribute("status", "notfound");
           }
       }

       return "redirect:/user/petugas/savepage";
   }
   
  @RequestMapping(value = "/user/petugas/update/{userId}", method = RequestMethod.POST)
  public String updatePetugas(@PathVariable("userId") String userId, 
		    User user,    		
   		final RedirectAttributes redirectAttributes){
   	   	
   	user.setUserId(userId);
   	    	
   	if (userService.saveUser(user) != null) {
           redirectAttributes.addFlashAttribute("edit", "success");
       } else {
           redirectAttributes.addFlashAttribute("edit", "unsuccess");
       }
   	
   	return "redirect:/user/petugas/savepage";
   }
  
  
  /*  
   * Controller Untuk Jabatan OrangTua
   * 
   */
  
  @RequestMapping(value = {"/user/orangtua", "/user/orangtua/savepage"}, method = RequestMethod.GET)
  public String indexOrangtua(Model model) {
      
      model.addAttribute("allOrangtuas", (ArrayList<User>) userService.getAllOrangtuas());
      return "/user/orangtua/index";
  }
  
  @RequestMapping(value = "/user/orangtua/create", method = RequestMethod.GET)
  public String viewFormOrangtua(Model model){
      
     model.addAttribute("user", new User());
     return "/user/orangtua/create";
  }
  
  @RequestMapping(value = {"/user/orangtua/save"}, method = RequestMethod.POST)
  public String saveOrangtua(@ModelAttribute("user") User user, 
		   Assigment assigment,
		   Jabatan jabatan,
		   final RedirectAttributes redirectAttributes) {
	   
	   if (userService.saveUser(user) != null) {
          redirectAttributes.addFlashAttribute("save", "success");
      } else {
          redirectAttributes.addFlashAttribute("save", "unsuccess");
      }
	   
	   jabatan.setJabatanId("c33d618b-a759-4d2f-b5d0-bf5bc5c26d71");
	   assigment.setIdUser(user);
	   assigment.setIdJabatan(jabatan);
	   
	   assigmentService.saveAssigment(assigment);

      return "redirect:/user/orangtua/savepage";
  }
  
  @RequestMapping(value = "/user/orangtua/{operation}/{userId}", method = RequestMethod.GET)
  public String editRemoveOrangtua(@PathVariable("operation") String operation,
          @PathVariable("userId") String userId, final RedirectAttributes redirectAttributes,
          Model model) {
      if (operation.equals("delete")) {
          if (userService.deleteUser(userId)) {
              redirectAttributes.addFlashAttribute("deletion", "success");
          } else {
              redirectAttributes.addFlashAttribute("deletion", "unsuccess");
          }
      } else if (operation.equals("edit")) {
          User user = userService.findUser(userId);
          if (user != null) {
              model.addAttribute("user", user);
              return "/user/orangtua/edit";
          } else {
              redirectAttributes.addFlashAttribute("status", "notfound");
          }
      } else if (operation.equals("view")) {
          User user = userService.findUser(userId);
          if (user != null) {
              model.addAttribute("user", user);
              return "/user/orangtua/view";
          } else {
              redirectAttributes.addFlashAttribute("status", "notfound");
          }
      }

      return "redirect:/user/orangtua/savepage";
  }
  
 @RequestMapping(value = "/user/orangtua/update/{userId}", method = RequestMethod.POST)
 public String updateOrangtua(@PathVariable("userId") String userId, 
		    User user,    		
  		final RedirectAttributes redirectAttributes){
  	   	
  	user.setUserId(userId);
  	    	
  	if (userService.saveUser(user) != null) {
          redirectAttributes.addFlashAttribute("edit", "success");
      } else {
          redirectAttributes.addFlashAttribute("edit", "unsuccess");
      }
  	
  	return "redirect:/user/orangtua/savepage";
  }
 
 
 /*  
  * Controller Untuk Jabatan RW
  * 
  */
 
 @RequestMapping(value = {"/user/rw", "/user/rw/savepage"}, method = RequestMethod.GET)
 public String indexRw(Model model) {
     
     model.addAttribute("allRws", (ArrayList<User>) userService.getAllRws());
     return "/user/rw/index";
 }
 
 @RequestMapping(value = "/user/rw/create", method = RequestMethod.GET)
 public String viewFormRw(Model model){
     
    model.addAttribute("user", new User());
    return "/user/rw/create";
 }
 
 @RequestMapping(value = {"/user/rw/save"}, method = RequestMethod.POST)
 public String saveRw(@ModelAttribute("user") User user, 
		   Assigment assigment,
		   Jabatan jabatan,
		   final RedirectAttributes redirectAttributes) {
	   
	   if (userService.saveUser(user) != null) {
         redirectAttributes.addFlashAttribute("save", "success");
     } else {
         redirectAttributes.addFlashAttribute("save", "unsuccess");
     }
	   
	   jabatan.setJabatanId("6b651276-f046-4ce9-b6d8-14bfeb42591c");
	   assigment.setIdUser(user);
	   assigment.setIdJabatan(jabatan);
	   
	   assigmentService.saveAssigment(assigment);

     return "redirect:/user/rw/savepage";
 }
 
 @RequestMapping(value = "/user/rw/{operation}/{userId}", method = RequestMethod.GET)
 public String editRemoveRw(@PathVariable("operation") String operation,
         @PathVariable("userId") String userId, final RedirectAttributes redirectAttributes,
         Model model) {
     if (operation.equals("delete")) {
         if (userService.deleteUser(userId)) {
             redirectAttributes.addFlashAttribute("deletion", "success");
         } else {
             redirectAttributes.addFlashAttribute("deletion", "unsuccess");
         }
     } else if (operation.equals("edit")) {
         User user = userService.findUser(userId);
         if (user != null) {
             model.addAttribute("user", user);
             return "/user/rw/edit";
         } else {
             redirectAttributes.addFlashAttribute("status", "notfound");
         }
     } else if (operation.equals("view")) {
         User user = userService.findUser(userId);
         if (user != null) {
             model.addAttribute("user", user);
             return "/user/rw/view";
         } else {
             redirectAttributes.addFlashAttribute("status", "notfound");
         }
     }

     return "redirect:/user/rw/savepage";
 }
 
@RequestMapping(value = "/user/rw/update/{userId}", method = RequestMethod.POST)
public String updateRw(@PathVariable("userId") String userId, 
		    User user,    		
 		final RedirectAttributes redirectAttributes){
 	   	
 	user.setUserId(userId);
 	    	
 	if (userService.saveUser(user) != null) {
         redirectAttributes.addFlashAttribute("edit", "success");
     } else {
         redirectAttributes.addFlashAttribute("edit", "unsuccess");
     }
 	
 	return "redirect:/user/rw/savepage";
 }

/*  
 * Controller Untuk Jabatan Bidan
 * 
 */

@RequestMapping(value = {"/user/bidan", "/user/bidan/savepage"}, method = RequestMethod.GET)
public String indexBidan(Model model) {
    
    model.addAttribute("allBidans", (ArrayList<User>) userService.getAllBidans());
    return "/user/bidan/index";
}

@RequestMapping(value = "/user/bidan/create", method = RequestMethod.GET)
public String viewFormBidan(Model model){
    
   model.addAttribute("user", new User());
   return "/user/bidan/create";
}

@RequestMapping(value = {"/user/bidan/save"}, method = RequestMethod.POST)
public String saveBidan(@ModelAttribute("user") User user, 
		   Assigment assigment,
		   Jabatan jabatan,
		   final RedirectAttributes redirectAttributes) {
	   
	   if (userService.saveUser(user) != null) {
        redirectAttributes.addFlashAttribute("save", "success");
    } else {
        redirectAttributes.addFlashAttribute("save", "unsuccess");
    }
	   
	   jabatan.setJabatanId("9ce32cfb-67f7-4410-93a1-3f15282a9811");
	   assigment.setIdUser(user);
	   assigment.setIdJabatan(jabatan);
	   
	   assigmentService.saveAssigment(assigment);

    return "redirect:/user/bidan/savepage";
}

@RequestMapping(value = "/user/bidan/{operation}/{userId}", method = RequestMethod.GET)
public String editRemoveBidan(@PathVariable("operation") String operation,
        @PathVariable("userId") String userId, final RedirectAttributes redirectAttributes,
        Model model) {
    if (operation.equals("delete")) {
        if (userService.deleteUser(userId)) {
            redirectAttributes.addFlashAttribute("deletion", "success");
        } else {
            redirectAttributes.addFlashAttribute("deletion", "unsuccess");
        }
    } else if (operation.equals("edit")) {
        User user = userService.findUser(userId);
        if (user != null) {
            model.addAttribute("user", user);
            return "/user/bidan/edit";
        } else {
            redirectAttributes.addFlashAttribute("status", "notfound");
        }
    } else if (operation.equals("view")) {
        User user = userService.findUser(userId);
        if (user != null) {
            model.addAttribute("user", user);
            return "/user/bidan/view";
        } else {
            redirectAttributes.addFlashAttribute("status", "notfound");
        }
    }

    return "redirect:/user/bidan/savepage";
}

@RequestMapping(value = "/user/bidan/update/{userId}", method = RequestMethod.POST)
public String updateBidan(@PathVariable("userId") String userId, 
		    User user,    		
		final RedirectAttributes redirectAttributes){
	   	
	user.setUserId(userId);
	    	
	if (userService.saveUser(user) != null) {
        redirectAttributes.addFlashAttribute("edit", "success");
    } else {
        redirectAttributes.addFlashAttribute("edit", "unsuccess");
    }
	
	return "redirect:/user/bidan/savepage";
}
   
    
}
