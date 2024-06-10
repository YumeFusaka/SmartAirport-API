package com.yumefusaka.SmartAirport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yumefusaka.SmartAirport.pojo.DTO.AddGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.DeleteGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.PutGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.Entity.Merchant;
import com.yumefusaka.SmartAirport.pojo.VO.BuyGoodsVO;

import java.util.List;

public interface MerchantService extends IService<Merchant> {
    void addGoods(AddGoodsDTO addGoodsDTO);

    void deleteGoods(DeleteGoodsDTO deleteGoodsDTO);

    List<BuyGoodsVO> findGoods(Long pageNo, Long pageSize);

    void updateGoods(PutGoodsDTO putGoodsDTO);
}
