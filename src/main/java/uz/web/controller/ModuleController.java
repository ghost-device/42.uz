package uz.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.web.domain.DTO.ModuleDTO;
import uz.web.service.ModuleService;

@Controller
@RequestMapping("/module")
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createModule(@ModelAttribute ModuleDTO moduleDTO, Model model){
        try {
            moduleService.saveModule(moduleDTO);
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("modules", moduleService.getModulesOfCourse(moduleDTO.getCourseId()));
        return "admin-modules-control";
    }
}
