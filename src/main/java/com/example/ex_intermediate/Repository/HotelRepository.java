package com.example.ex_intermediate.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ex_intermediate.Domain.Hotel;

/**
 * @author yoshioka
 * ホテルのDB処理を行うクラス
 */
@Repository
public class HotelRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;


    private static final RowMapper<Hotel> HOTEL_ROW_MAPPER = (rs,i) -> {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("id"));
        hotel.setAreaName(rs.getString("area_name"));
        hotel.setHotelName(rs.getString("hotel_name"));
        hotel.setAddress(rs.getString("address"));
        hotel.setNearestStation(rs.getString("nearest_station"));
        hotel.setPrice(rs.getInt("price"));
        hotel.setPacking(rs.getString("parking"));

        return hotel;
    };

   /**
     * priceの入力に応じて該当件数を返すメソッド
     * @param price 入力されるpriceに応じて検索するメソッドの為
     * @return 複数件返ってくる為
     */
   public List<Hotel> findByPrice(Integer price){
    String sql = "SELECT id, area_name, hotel_name, address, nearest_station, "
    + "price, parking FROM hotels WHERE price <= :price ORDER BY price desc";
    SqlParameterSource param = new MapSqlParameterSource().addValue("price",price);
        List<Hotel> hotelList = template.query(sql, param, HOTEL_ROW_MAPPER);
      return hotelList;
   }
   /**
     * priceフォームに何も入力されなかった時にの処理
     * @return 複数件返ってくる為
     */
   public List<Hotel> findAll(){
    String sql = "SELECT id, area_name, hotel_name, address, nearest_station, "
    + "price, parking FROM hotels ORDER BY price desc";
        List<Hotel> hotelList = template.query(sql, HOTEL_ROW_MAPPER);
      return hotelList;
   }

}
 