package com.example.ex_intermediate.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.ex_intermediate.Domain.Hotel;

import com.example.ex_intermediate.Domain.Team;
import com.example.ex_intermediate.Service.HotelService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/hotel")
public class Ex02Controller {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private HttpSession session;

    @RequestMapping("")
    public String Hotel() {
        return "ex02-form";
    }

    @RequestMapping("/result")
    public String result(Integer price, Model model, Hotel hotel) {
        model.addAttribute("price", price);
        model.addAttribute("hotel", hotel);
        if (price != null) {
            List<Hotel> hotelList = hotelService.searchByLessThanPrice(price);
            model.addAttribute("hotelList", hotelList);
            return "ex02-form";
        }
        List<Hotel> hotelList = hotelService.Fullselect();
        model.addAttribute("hotelList", hotelList);
        return "ex02-form";

    }

}
