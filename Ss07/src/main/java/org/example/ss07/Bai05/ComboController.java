package org.example.ss07.Bai05;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/combo")
public class ComboController {

    private static final List<Combo> comboList = new ArrayList<>();

    @GetMapping("/form")
    public String showForm() {
        return "form";
    }

    @PostMapping("/add")
    public String addCombo(@RequestParam("name") String name,
                           @RequestParam(value = "items", required = false) List<String> itemList,
                           @RequestParam("banner") MultipartFile file,
                           RedirectAttributes redirectAttributes) throws IOException {

        if (itemList == null || itemList.size() < 2) {
            return "error";
        }

        if (file.isEmpty()) {
            return "error";
        }

        String original = file.getOriginalFilename();
        String newFileName = System.currentTimeMillis() + "_" + original;

        String uploadDir = "C:/RikkeiFood_Temp/";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File dest = new File(uploadDir + newFileName);
        file.transferTo(dest);

        String imageUrl = "/images/" + newFileName;

        Combo combo = new Combo(name, itemList, imageUrl);
        comboList.add(combo);

        // In ket qua combo ra console dang JSON
        System.out.println(
                "{\"name\":\"" + combo.getName() + "\"," +
                        "\"itemList\":" + combo.getItemList() + "," +
                        "\"banner\":\"" + combo.getBanner() + "\"}"
        );

        int index = comboList.size() - 1;
        redirectAttributes.addAttribute("id", index);

        return "redirect:/combo/detail";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") int id, Model model) {
        if (id < 0 || id >= comboList.size()) {
            return "error";
        }
        model.addAttribute("combo", comboList.get(id));
        return "combo-detail";
    }
}