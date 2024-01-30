package com.nmp.springmvcdemo.controller;

import com.nmp.springmvcdemo.dto.Grade;
import com.nmp.springmvcdemo.dto.Show;
import com.nmp.springmvcdemo.service.IGradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class SpringBootLabController {
    @Autowired
    private IGradeService gradeService;

    @GetMapping("/form")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        model.addAttribute("grade", gradeService.getGradeById(id));
        return "form";
    }
    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeService.getAllGrades());
        return "grades";
    }

    @PostMapping("/submitForm")
    public String handleSubmit(@Valid Grade grade, BindingResult result) {
        if (result.hasErrors()) return "form";
        gradeService.addGrade(grade);
        return "redirect:/grades";
    }
    @GetMapping("/shows")
    public String getShows(Model model) {
        model.addAttribute("show1", new Show("Breaking Bad", "Ozymandias", 10.0));
        model.addAttribute("show2", new Show("Attack on Titan", "Hero", 9.9));
        return "shows";
    }

    // In the olden days, the ModelAndView return type was used instead of String
    /*@GetMapping("/shows")
    public ModelAndView getShows() {
        Map<String, Show> model = new HashMap<>();
        model.put("show1", new Show("Breaking Bad", "Ozymandias", 10.0));
        model.put("show2", new Show("Attack on Titan", "Hero", 9.9));
        return new ModelAndView("shows", model);
    }*/
}
