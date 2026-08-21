package com.example.ex_intermediate.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.ex_intermediate.Domain.Hotel;
import com.example.ex_intermediate.Service.HotelService;

/**
 * @author yoshioka
 * Ex02Controller ホテルを値段順で検索するクラス
 */
@Controller
@RequestMapping("/hotel")
public class Ex02Controller {
    @Autowired
    private HotelService hotelService;

    /**
     * 入力フォームを表示する処理
     */
    @RequestMapping("")
    public String Hotel() {
        return "ex02-form";
    }
     /**
      * 
      * @param price priceを受け取って結果を表示する為
      * @param model 各プロパティを画面表示する為
      * @param hotel ホテル情報の表示をする為
      * @return
      */
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
