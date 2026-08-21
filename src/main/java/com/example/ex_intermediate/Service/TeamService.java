package com.example.ex_intermediate.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ex_intermediate.Domain.Team;
import com.example.ex_intermediate.Repository.TeamRepository;

/**
 * @author yoshioka
 * TeamService
 */
@Service
@Transactional
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    /**
     * 球団チーム　表示処理
     * @return 球団チーム一覧表示の為のlist
     */
    public List<Team> showList(){
        return teamRepository.findAll();
    }

    /**
     * 球団チームの詳細画面表示機能
     * @return 1球団に関して詳細情報を表示
     */
    public Team showDatail(Integer id){
        return teamRepository.load(id);
    }
}
