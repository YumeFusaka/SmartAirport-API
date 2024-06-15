package com.yumefusaka.SmartAirport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumefusaka.SmartAirport.common.BaseContext;
import com.yumefusaka.SmartAirport.common.BaseInfo;
import com.yumefusaka.SmartAirport.mapper.*;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyTicketDTO;
import com.yumefusaka.SmartAirport.pojo.Entity.*;
import com.yumefusaka.SmartAirport.pojo.VO.BuyGoodsVO;
import com.yumefusaka.SmartAirport.pojo.VO.FindBuyTicketVO;
import com.yumefusaka.SmartAirport.pojo.VO.FlightVO;
import com.yumefusaka.SmartAirport.pojo.VO.LuggageVO;
import com.yumefusaka.SmartAirport.service.GoodsService;
import com.yumefusaka.SmartAirport.service.LuggageService;
import com.yumefusaka.SmartAirport.service.PassengerService;
import com.yumefusaka.SmartAirport.service.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements PassengerService {

    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private FlightMapper flightMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private LuggageMapper luggageMapper;

    @Autowired
    private LuggageService luggageService;

    @Override
    public List<FindBuyTicketVO> findBuyTicket(Long pageNo, Long pageSize) {
        BaseContext.removeCurrentInfo();
        Page<Ticket> page = Page.of(pageNo, pageSize);
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(true);
        page.addOrder(orderItem);
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.eq("passenger_id", 0);
        Page<Ticket> p = ticketService.page(page, wrapper);
        List<Ticket> records = p.getRecords();
        List<FindBuyTicketVO> findBuyTicketVOS = new ArrayList<FindBuyTicketVO>();
        for (Ticket ticket : records) {
            Flight flight = flightMapper.selectById(ticket.getFlight_id());
            FlightVO flightVO = new FlightVO();
            BeanUtils.copyProperties(flight, flightVO);
            FindBuyTicketVO findBuyTicketVO = new FindBuyTicketVO();
            BeanUtils.copyProperties(ticket, findBuyTicketVO);
            findBuyTicketVO.setFlightVO(flightVO);
            findBuyTicketVOS.add(findBuyTicketVO);
        }
        return findBuyTicketVOS;
    }

    @Override
    public void buyTicket(BuyTicketDTO buyTicketDTO) {
        BaseInfo currentInfo = BaseContext.getCurrentInfo();
        BaseContext.removeCurrentInfo();
        List<Long> ticketIds = buyTicketDTO.getTicketIds();
        long price = 0;
        for (Long ticketId : ticketIds) {
            Ticket ticket = new Ticket();
            ticket.setId(ticketId);
            ticket.setPassenger_id(Long.valueOf(currentInfo.getId()));
            ticketMapper.updateById(ticket);
            price = price + ticket.getPrice();
        }
        Passenger passenger = passengerMapper.selectById(currentInfo.getId());
        passenger.setMoney(passenger.getMoney() - price);
        passengerMapper.updateById(passenger);
    }

    @Override
    public List<FindBuyTicketVO> findBuyTicketHistory(Long pageNo, Long pageSize) {
        BaseInfo currentInfo = BaseContext.getCurrentInfo();
        BaseContext.removeCurrentInfo();
        Page<Ticket> page = Page.of(pageNo, pageSize);
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(true);
        page.addOrder(orderItem);
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.eq("passenger_id", currentInfo.getId());
        Page<Ticket> p = ticketService.page(page, wrapper);
        List<Ticket> records = p.getRecords();
        List<FindBuyTicketVO> findBuyTicketVOS = new ArrayList<FindBuyTicketVO>();
        for (Ticket ticket : records) {
            Flight flight = flightMapper.selectById(ticket.getFlight_id());
            FlightVO flightVO = new FlightVO();
            BeanUtils.copyProperties(flight, flightVO);
            FindBuyTicketVO findBuyTicketVO = new FindBuyTicketVO();
            BeanUtils.copyProperties(ticket, findBuyTicketVO);
            findBuyTicketVO.setFlightVO(flightVO);
            findBuyTicketVOS.add(findBuyTicketVO);
        }
        return findBuyTicketVOS;
    }

    @Override
    public List<BuyGoodsVO> findBuyGoods(Long pageNo, Long pageSize) {
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
    public void buyGoods(BuyGoodsDTO buyGoodsDTO) {
        BaseInfo currentInfo = BaseContext.getCurrentInfo();
        BaseContext.removeCurrentInfo();
        List<Long> goodsIds = buyGoodsDTO.getGoodsIds();
        long price = 0;
        for (Long goodsId : goodsIds) {
            Goods goods = goodsMapper.selectById(goodsId);
            Luggage luggage = new Luggage();
            BeanUtils.copyProperties(goods, luggage);
            luggage.setPassenger_id(currentInfo.getId());
            luggageMapper.insert(luggage);
            goods.setStock(goods.getStock() - 1);
            if (goods.getStock() == 0) {
                goodsMapper.deleteById(goodsId);
            } else {
                goodsMapper.updateById(goods);
            }
            price = price + goods.getPrice();
        }
        Passenger passenger = passengerMapper.selectById(currentInfo.getId());
        passenger.setMoney(passenger.getMoney() - price);
        passengerMapper.updateById(passenger);
    }

    @Override
    public List<LuggageVO> findLuggageHistory(Long pageNo, Long pageSize) {
        BaseInfo currentInfo = BaseContext.getCurrentInfo();
        BaseContext.removeCurrentInfo();
        Page<Luggage> page = Page.of(pageNo, pageSize);
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(true);
        page.addOrder(orderItem);
        QueryWrapper<Luggage> wrapper = new QueryWrapper<>();
        wrapper.eq("passenger_id", currentInfo.getId());
        Page<Luggage> p = luggageService.page(page);
        List<Luggage> records = p.getRecords();
        List<LuggageVO> luggageVOS = new ArrayList<LuggageVO>();
        for (Luggage luggage : records) {
            LuggageVO luggageVO = new LuggageVO();
            BeanUtils.copyProperties(luggage, luggageVO);
            luggageVOS.add(luggageVO);
        }
        return luggageVOS;
    }


}
