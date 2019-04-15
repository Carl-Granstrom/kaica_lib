package kaica_lib.controllers;

import kaica_lib.entities.Title;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/title")
public class AddTitleController {


    @GetMapping
    public String showAddTitleForm(Model model) {
        String isbn = "1";
        String title = "The Book";
        String status = "available";
        String author = "The Author";
        Date retDate = new Date();
        model.addAttribute("title", new Title(isbn, title, status, author, retDate));
        return "title";
    }

}
