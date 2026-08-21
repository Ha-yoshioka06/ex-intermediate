package com.example.ex_intermediate.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.ex_intermediate.Domain.Team;
import com.example.ex_intermediate.Service.TeamService;
/**
 * @author yoshioka
 * Ex01Controller
 */
@Controller
@RequestMapping("/team")
public class Ex01Controller {

    @Autowired
    private TeamService teamservice;
    /**
     * 球団一覧表示の処理
     * @param model そのページで表示をさせるだけで問題ない為sessionは不要
     * @return  HTMLを返す為
     */
    @RequestMapping("/List")
    public String List(Model model){
        List<Team> team = teamservice.showList();
        model.addAttribute("teamlist", team);
        return "ex01list";
    }
    /**
     * 選択された球団の詳細情報を表示する処理
     * @param id idを参照して球団の詳細画面を表示
     * @param model そのページで表示をさせるだけで問題ない為sessionは不要
     * @return  HTMLを返す為
     */
    @RequestMapping("/Datail")
    public String Datail(@RequestParam("id") Integer id, Model model){
        Team team = teamservice.showDatail(id);
        model.addAttribute("team", team);
        
        return "ex01datail";
    }
}
