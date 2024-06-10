package com.yumefusaka.SmartAirport.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumefusaka.SmartAirport.common.BaseContext;
import com.yumefusaka.SmartAirport.mapper.GoodsMapper;
import com.yumefusaka.SmartAirport.mapper.MerchantMapper;
import com.yumefusaka.SmartAirport.pojo.DTO.AddGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.DeleteGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.PutGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.Entity.Goods;
import com.yumefusaka.SmartAirport.pojo.Entity.Merchant;
import com.yumefusaka.SmartAirport.pojo.VO.BuyGoodsVO;
import com.yumefusaka.SmartAirport.service.GoodsService;
import com.yumefusaka.SmartAirport.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void addGoods(AddGoodsDTO addGoodsDTO) {
        BaseContext.removeCurrentInfo();
        Goods goods = new Goods();
        BeanUtils.copyProperties(addGoodsDTO, goods);
        goodsService.save(goods);
    }

    @Override
    public void deleteGoods(DeleteGoodsDTO deleteGoodsDTO) {
        BaseContext.removeCurrentInfo();
        List<Long> goodsIds = deleteGoodsDTO.getGoodsIds();
        for (Long goodsId : goodsIds) {
            goodsService.removeById(goodsId);
        }
    }

    @Override
    public List<BuyGoodsVO> findGoods(Long pageNo, Long pageSize) {
        BaseContext.removeCurrentInfo();
        Page<Goods> page = Page.of(pageNo, pageSize);
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(true);
        page.addOrder(orderItem);
        Page<Goods> p = goodsService.page(page);
        List<Goods> records = p.getRecords();
        List<BuyGoodsVO> buyGoodsVOS = new ArrayList<BuyGoodsVO>();
        for (Goods goods : records) {
            BuyGoodsVO buyGoodsVO = new BuyGoodsVO();
            BeanUtils.copyProperties(goods, buyGoodsVO);
            buyGoodsVOS.add(buyGoodsVO);
        }
        return buyGoodsVOS;
    }

    @Override
    public void updateGoods(PutGoodsDTO putGoodsDTO) {
        BaseContext.removeCurrentInfo();
        Goods goods = new Goods();
        BeanUtils.copyProperties(putGoodsDTO, goods);
        goodsMapper.updateById(goods);
    }
}
