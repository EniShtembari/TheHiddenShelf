package com.boostmytool.TheHiddenShelf.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.boostmytool.TheHiddenShelf.models.Librat;
import com.boostmytool.TheHiddenShelf.models.LibratDto;
import com.boostmytool.TheHiddenShelf.services.LibratRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/librat")
public class LibratController {

	@Autowired
	private LibratRepository repo;
	
	@GetMapping({"", "/"})
	public String showLibratList(Model model) {
		List<Librat> librat = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("librat", librat);
		return "librat/index";
	}
	
	@GetMapping("/krijo")
	public String showCreatePage(Model model) {
		LibratDto libratDto = new LibratDto();
		model.addAttribute("libratDto", libratDto);
		return "librat/CreateLibrat";
	}
	
	@PostMapping("/krijo")
	public String createBook(
			@Valid @ModelAttribute LibratDto libratDto, BindingResult result) 
	{
		if(libratDto.getImageFile().isEmpty()) {
			result.addError(new FieldError("libratDto", "imageFile", "The image file is required"));
		}
		if(result.hasErrors()) {
			return "librat/CreateLibrat";
		}
		
		MultipartFile image=libratDto.getImageFile();
		Date createdAt = new Date();
		String storageFileName= createdAt.getTime()+"_"+image.getOriginalFilename();
		
		try {
			String uploadDir = "public/librat/";
			Path uploadPath = Paths.get(uploadDir);
			
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try(InputStream inputStream=image.getInputStream()){
				Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
						StandardCopyOption.REPLACE_EXISTING);
			}
		}catch (Exception ex) {
			System.out.println("Exception: "+ ex.getMessage());
		}
		
		//ruaj imazhin
		 MultipartFile librat = libratDto.getImageFile();
		 Date createdAt1 = new Date();
		 String storageFileName1 = createdAt1.getTime() + "_"+librat.getOriginalFilename();
		 
		 try {
			 String uploadDir = "public/librat/";
			 Path uploadPath = Paths.get(uploadDir);
			 
			 if(!Files.exists(uploadPath)) {
				 Files.createDirectories(uploadPath);
			 }
			 
			 try (InputStream inputStream = image.getInputStream()){
				 Files.copy(inputStream, Paths.get(uploadDir + storageFileName1), StandardCopyOption.REPLACE_EXISTING);
			 }
		 } catch(Exception ex) {
			 System.out.println("Exception: "+ ex.getMessage());
		 }
		 
		 Librat librat1 = new Librat();
		 librat1.setImageFileName(storageFileName1);
		 librat1.setTitle(libratDto.getTitle());
		 librat1.setAuthor(libratDto.getAuthor());
		 librat1.setPublisher(libratDto.getPublisher());
		 librat1.setCategory(libratDto.getCategory());
		 librat1.setCopiesNo(libratDto.getCopiesNo());
		 librat1.setPrice(libratDto.getPrice());
		 librat1.setCreatedAt(createdAt1);
		 
		 repo.save(librat1);
		 
		return "redirect:/librat";
	}
	
	
	
	
    @GetMapping("/edit")
    public String showEditPage(@RequestParam("id") int id, Model model) {
        try {
            Librat librat = repo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
            model.addAttribute("librat", librat);

            LibratDto libratDto = new LibratDto();
            libratDto.setTitle(librat.getTitle());
            libratDto.setAuthor(librat.getAuthor());
            libratDto.setPublisher(librat.getPublisher());
            libratDto.setCategory(librat.getCategory());
            libratDto.setCopiesNo(librat.getCopiesNo());
            libratDto.setPrice(librat.getPrice());
            model.addAttribute("libratDto", libratDto);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/librat";
        }

        return "librat/EditLibrat";
    }
    
    @PostMapping("/edit")
    public String updateProduct(@RequestParam("id") int id, Model model, @Valid @ModelAttribute LibratDto libratDto, BindingResult result) {
    	 try {
             Librat librat = repo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
             model.addAttribute("librat", librat);
             
             if(result.hasErrors()) {
            	 return "librat/EditLibrat";
             }
             
             if(!libratDto.getImageFile().isEmpty()){
            	 //Fshin Bookcover-in e vjeter
            	 String uploadDir = "public/librat/";
            	 Path oldImagePath = Paths.get(uploadDir+ librat.getImageFileName());
            	 try {
            		 Files.delete(oldImagePath);
            	 }catch(Exception ex) {
            		 System.out.println("Exception: "+ ex.getMessage());
            	 }
            	 
            	 //Ruan Bookcoverin e ri
            	 MultipartFile image = libratDto.getImageFile();
            	 Date createdAt = new Date();
            	 String storageFileName = createdAt.getTime()+"_"+image.getOriginalFilename();
            	 //String storageFileName = image.getOriginalFilename();
            	 
            	 
            	 try(InputStream inputStream = image.getInputStream()){
            		 Files.copy(inputStream, Paths.get(uploadDir + storageFileName),StandardCopyOption.REPLACE_EXISTING);
            	 }
            	 librat.setImageFileName(storageFileName);
             }
             
             librat.setTitle(libratDto.getTitle());
             librat.setAuthor(libratDto.getAuthor());
             librat.setPublisher(libratDto.getPublisher());
             librat.setCategory(libratDto.getCategory());
             librat.setCopiesNo(libratDto.getCopiesNo());
             librat.setPrice(libratDto.getPrice());
             
             repo.save(librat);
             
         } catch (Exception ex) {
             System.out.println("Exception: " + ex.getMessage());
         	}
         
    	 return "redirect:/librat";
         }
    
    	@GetMapping("/delete")
	public String  deleteLibra(@RequestParam("id") int id) {
    		try {
    			Librat librat = repo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    			Path imagePath = Paths.get("public/librat/"+librat.getImageFileName());
    			try {
    				Files.delete(imagePath);
    			}catch(Exception ex) {
    				System.out.println("Exception: "+ ex.getMessage());
    			}
    			
    			repo.delete(librat);
    			
    		}catch(Exception ex) {
    			System.out.println("Exception: "+ ex.getMessage());
    		}
    		
		return"redirect:/librat";
	}
}
