package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/upload-image")
    public String uploadImageForm() {
        return "upload-image";
    }

    @PostMapping("/upload-image")
    public String handleImageUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select an image file to upload");
            return "redirect:/upload-image";
        }
        // TODO: Save the image file here
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
        return "redirect:/upload-image";
    }

    @GetMapping("/upload-pdf")
    public String uploadPdfForm() {
        return "upload-pdf";
    }

    @PostMapping("/upload-pdf")
    public String handlePdfUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a PDF file to upload");
            return "redirect:/upload-pdf";
        }
        // TODO: Save the PDF file here
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
        return "redirect:/upload-pdf";
    }
}
