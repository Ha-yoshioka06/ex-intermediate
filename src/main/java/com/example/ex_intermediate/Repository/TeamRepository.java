package com.example.ex_intermediate.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ex_intermediate.Domain.Team;

/**
 * @author yoshioka
 * TeamRepository
 */
@Repository
public class TeamRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 各DB処理メソッドをすべてカバー,使いまわしできるようにあらかじめ全カラムをセットする
     * 
     */

    private static final RowMapper<Team> TEAM_ROW_MAPPER = (rs,i) -> {
        Team team = new Team();
        team.setId(rs.getInt("id"));
        team.setLeagueName(rs.getString("league_name"));
        team.setTeamName(rs.getString("team_name"));
        team.setHeadquarters(rs.getString("headquarters"));
        team.setInauguration(rs.getString("inauguration"));
        team.setHistory(rs.getString("history"));

        return team;
    };
    /**
     * 球団情報を全件検索する処理
     * @return 球団情報が複数件になる為List型で受け取る
     */
    public List<Team> findAll(){
        String sql = "SELECT id, league_name, team_name, headquarters, "+
            "inauguration, history FROM teams ORDER BY inauguration;";
            
            return template.query(sql, TEAM_ROW_MAPPER);
    }
    /**
     * 選択された球団の詳細情報を表示
     * @param id idを参照に球団の詳細情報を表示
     * @return  取得した球団情報をTeam型でまとめて返す
     */
    public Team load(Integer id) {
        String sql = "SELECT id, league_name, team_name, headquarters, "
          +  "inauguration, history FROM teams WHERE id =:id;";

          SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
          Team team = template.queryForObject(sql, param, TEAM_ROW_MAPPER);

          return team;
    }
}
