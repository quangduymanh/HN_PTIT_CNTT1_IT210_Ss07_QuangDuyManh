package org.example.ss07.Bai04;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {

    private static List<Food> foodList = new ArrayList<>();

    @PostMapping("/add")
    public String addFood(@RequestParam String name,
                          @RequestParam double price,
                          @RequestParam String category,
                          @RequestParam("image") MultipartFile file,
                          RedirectAttributes redirectAttributes) throws IOException {

        if (file.isEmpty()) return "error";
        if (price < 0) return "error";

        String original = file.getOriginalFilename();
        String newFileName = System.currentTimeMillis() + "_" + original;

        String uploadDir = "C:/RikkeiFood_Temp/";
        File dest = new File(uploadDir + newFileName);
        file.transferTo(dest);

        Food food = new Food(name, price, category, uploadDir + newFileName);
        foodList.add(food);

        int index = foodList.size() - 1;

        redirectAttributes.addAttribute("id", index);

        return "redirect:/food/detail";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int id, Model model) {
        model.addAttribute("food", foodList.get(id));
        return "food-detail";
    }
}