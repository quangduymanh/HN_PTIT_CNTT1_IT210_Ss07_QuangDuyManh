package org.example.ss07.Bai03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {

    private static List<Food> foodList = new ArrayList<>();

    @PostMapping("/add")
    public String addFood(@RequestParam("name") String name,
                          @RequestParam("price") double price,
                          @RequestParam("category") String category,
                          @RequestParam("image") MultipartFile file) throws IOException {

        // check file rỗng
        if (file.isEmpty()) {
            System.out.println("Ảnh không được để trống");
            return "error";
        }

        // check định dạng
        String fileName = file.getOriginalFilename();
        if (fileName == null ||
                !(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg"))) {
            System.out.println("Sai định dạng file");
            return "error";
        }

        // check giá
        if (price < 0) {
            System.out.println("Giá phải >= 0");
            return "error";
        }

        // lưu file
        String uploadDir = "C:/RikkeiFood_Temp/";
        File dest = new File(uploadDir + fileName);
        file.transferTo(dest);

        // lưu dữ liệu
        Food food = new Food(name, price, category, fileName);
        foodList.add(food);

        // in ra console
        System.out.println("Đã thêm món: " + name);
        System.out.println("Tổng số món: " + foodList.size());

        return "success";
    }
}