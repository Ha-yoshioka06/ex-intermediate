package com.example.ex_intermediate.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ex_intermediate.Domain.Hotel;
import com.example.ex_intermediate.Repository.HotelRepository;

/**
 * @author yoshioka
 * HotelRepositoryからDB処理を受け取りControllerに渡すクラス
 */
@Service
@Transactional
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    

    /**
     * priceの入力に応じて該当件数を返すメソッド
     * @param price 入力されるpriceに応じて検索するメソッドの為
     * @return 複数件返ってくる為
     */
     public List<Hotel> searchByLessThanPrice(Integer price){
        return hotelRepository.findByPrice(price);
    } 
    
    /**
     * priceフォームに何も入力されなかった時にの処理
     * @return 複数件返ってくる為
     */
    public List<Hotel> Fullselect(){
        return hotelRepository.findAll();
    } 
}
