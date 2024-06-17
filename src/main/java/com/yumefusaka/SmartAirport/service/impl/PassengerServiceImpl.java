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
import com.yumefusaka.SmartAirport.pojo.DTO.FindTicketDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.HistoryTicketDTO;
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
import org.springframework.web.bind.annotation.RequestBody;

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
    public List<FindBuyTicketVO> findBuyTicket(@RequestBody FindTicketDTO findTicketDTO) {
        BaseContext.removeCurrentInfo();
        Page<Ticket> page = Page.of(findTicketDTO.getPageNo(), findTicketDTO.getPageSize());
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(false);
        page.addOrder(orderItem);
        QueryWrapper<Ticket> queryWrapper = new QueryWrapper<>();
        if (findTicketDTO.getFlight_number() != null && !findTicketDTO.getFlight_number().isEmpty()) {
            queryWrapper.like("flight_number", findTicketDTO.getFlight_number());
        }

        if (findTicketDTO.getDeparture_city() != null && !findTicketDTO.getDeparture_city().isEmpty()) {
            queryWrapper.like("departure_city", findTicketDTO.getDeparture_city());
        }

        if (findTicketDTO.getArrival_city() != null && !findTicketDTO.getArrival_city().isEmpty()) {
            queryWrapper.like("arrival_city", findTicketDTO.getArrival_city());
        }

        if (findTicketDTO.getDate_of_departure() != null) {
            queryWrapper.like("date_of_departure", findTicketDTO.getDate_of_departure());
        }

        if (findTicketDTO.getEstimated_travel_time() != 0) {
            queryWrapper.eq("estimated_travel_time", findTicketDTO.getEstimated_travel_time());
        }

        if (findTicketDTO.getCapacity() != 0) {
            queryWrapper.eq("capacity", findTicketDTO.getCapacity());
        }

        if (findTicketDTO.getPrice() != 0) {
            queryWrapper.eq("price", findTicketDTO.getPrice());
        }

        if (findTicketDTO.getSeat_class() != null && !findTicketDTO.getSeat_class().isEmpty()) {
            queryWrapper.eq("seat_class", findTicketDTO.getSeat_class());
        }

        if (findTicketDTO.getSeat_number() != 0) {
            queryWrapper.eq("seat_number", findTicketDTO.getSeat_number());
        }
        queryWrapper.eq("passenger_id", 0);
        Page<Ticket> p = ticketService.page(page, queryWrapper);
        List<Ticket> records = p.getRecords();
        List<FindBuyTicketVO> findBuyTicketVOS = new ArrayList<FindBuyTicketVO>();
        for (Ticket ticket : records) {
            FindBuyTicketVO findBuyTicketVO = new FindBuyTicketVO();
            BeanUtils.copyProperties(ticket, findBuyTicketVO);
            findBuyTicketVOS.add(findBuyTicketVO);
        }
        return findBuyTicketVOS;
    }

    @Override
    public void buyTicket(BuyTicketDTO buyTicketDTO) {
        BaseInfo currentInfo = BaseContext.getCurrentInfo();
        BaseContext.removeCurrentInfo();
        List<Long> ticketIds = buyTicketDTO.getTicketIds();
        for (Long ticketId : ticketIds) {
            Ticket ticket = new Ticket();
            ticket.setId(ticketId);
            ticket.setPassenger_id(currentInfo.getId());
            ticketMapper.updateById(ticket);
        }
    }

    @Override
    public List<FindBuyTicketVO> findBuyTicketHistory(HistoryTicketDTO historyTicketDTO) {
        BaseInfo currentInfo = BaseContext.getCurrentInfo();
        BaseContext.removeCurrentInfo();
        Page<Ticket> page = Page.of(historyTicketDTO.getPageNo(), historyTicketDTO.getPageSize());
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(false);
        page.addOrder(orderItem);
        QueryWrapper<Ticket> queryWrapper = new QueryWrapper<>();
        if (historyTicketDTO.getFlight_number() != null && !historyTicketDTO.getFlight_number().isEmpty()) {
            queryWrapper.like("flight_number", historyTicketDTO.getFlight_number());
        }

        if (historyTicketDTO.getDeparture_city() != null && !historyTicketDTO.getDeparture_city().isEmpty()) {
            queryWrapper.like("departure_city", historyTicketDTO.getDeparture_city());
        }

        if (historyTicketDTO.getArrival_city() != null && !historyTicketDTO.getArrival_city().isEmpty()) {
            queryWrapper.like("arrival_city", historyTicketDTO.getArrival_city());
        }

        if (historyTicketDTO.getDate_of_departure() != null) {
            queryWrapper.like("date_of_departure", historyTicketDTO.getDate_of_departure());
        }

        if (historyTicketDTO.getEstimated_travel_time() != 0) {
            queryWrapper.eq("estimated_travel_time", historyTicketDTO.getEstimated_travel_time());
        }

        if (historyTicketDTO.getCapacity() != 0) {
            queryWrapper.eq("capacity", historyTicketDTO.getCapacity());
        }

        if (historyTicketDTO.getPrice() != 0) {
            queryWrapper.eq("price", historyTicketDTO.getPrice());
        }

        if (historyTicketDTO.getSeat_class() != null && !historyTicketDTO.getSeat_class().isEmpty()) {
            queryWrapper.eq("seat_class", historyTicketDTO.getSeat_class());
        }

        if (historyTicketDTO.getSeat_number() != 0) {
            queryWrapper.eq("seat_number", historyTicketDTO.getSeat_number());
        }
        if (historyTicketDTO.getUpdate_time() != null) {
            queryWrapper.like("update_time", historyTicketDTO.getUpdate_time());
        }
        queryWrapper.eq("passenger_id", currentInfo.getId());
        Page<Ticket> p = ticketService.page(page, queryWrapper);
        List<Ticket> records = p.getRecords();
        List<FindBuyTicketVO> findBuyTicketVOS = new ArrayList<FindBuyTicketVO>();
        for (Ticket ticket : records) {
            FindBuyTicketVO findBuyTicketVO = new FindBuyTicketVO();
            BeanUtils.copyProperties(ticket, findBuyTicketVO);
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
        orderItem.setAsc(false);
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
        }
    }

    @Override
    public List<LuggageVO> findLuggageHistory(Long pageNo, Long pageSize) {
        BaseInfo currentInfo = BaseContext.getCurrentInfo();
        BaseContext.removeCurrentInfo();
        Page<Luggage> page = Page.of(pageNo, pageSize);
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(false);
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

    @Override
    public long countTicket(FindTicketDTO findTicketDTO) {
        BaseContext.removeCurrentInfo();
        QueryWrapper<Ticket> queryWrapper = new QueryWrapper<>();
        if (findTicketDTO.getFlight_number() != null && !findTicketDTO.getFlight_number().isEmpty()) {
            queryWrapper.like("flight_number", findTicketDTO.getFlight_number());
        }
        if (findTicketDTO.getDeparture_city() != null && !findTicketDTO.getDeparture_city().isEmpty()) {
            queryWrapper.like("departure_city", findTicketDTO.getDeparture_city());
        }
        if (findTicketDTO.getArrival_city() != null && !findTicketDTO.getArrival_city().isEmpty()) {
            queryWrapper.like("arrival_city", findTicketDTO.getArrival_city());
        }
        if (findTicketDTO.getDate_of_departure() != null) {
            queryWrapper.like("date_of_departure", findTicketDTO.getDate_of_departure());
        }
        if (findTicketDTO.getEstimated_travel_time() != 0) {
            queryWrapper.eq("estimated_travel_time", findTicketDTO.getEstimated_travel_time());
        }
        if (findTicketDTO.getPrice() != 0) {
            queryWrapper.eq("price", findTicketDTO.getPrice());
        }
        if (findTicketDTO.getSeat_class() != null && !findTicketDTO.getSeat_class().isEmpty()) {
            queryWrapper.eq("seat_class", findTicketDTO.getSeat_class());
        }
        if (findTicketDTO.getSeat_number() != 0) {
            queryWrapper.eq("seat_number", findTicketDTO.getSeat_number());
        }
        queryWrapper.eq("passenger_id", 0);
        return ticketMapper.selectCount(queryWrapper);
    }

    @Override
    public long countHistoryTicket(HistoryTicketDTO historyTicketDTO) {
        long current_id = BaseContext.getCurrentInfo().getId();
        BaseContext.removeCurrentInfo();
        QueryWrapper<Ticket> queryWrapper = new QueryWrapper<>();
        if (historyTicketDTO.getFlight_number() != null && !historyTicketDTO.getFlight_number().isEmpty()) {
            queryWrapper.like("flight_number", historyTicketDTO.getFlight_number());
        }
        if (historyTicketDTO.getDeparture_city() != null && !historyTicketDTO.getDeparture_city().isEmpty()) {
            queryWrapper.like("departure_city", historyTicketDTO.getDeparture_city());
        }
        if (historyTicketDTO.getArrival_city() != null && !historyTicketDTO.getArrival_city().isEmpty()) {
            queryWrapper.like("arrival_city", historyTicketDTO.getArrival_city());
        }
        if (historyTicketDTO.getDate_of_departure() != null) {
            queryWrapper.like("date_of_departure", historyTicketDTO.getDate_of_departure());
        }
        if (historyTicketDTO.getEstimated_travel_time() != 0) {
            queryWrapper.eq("estimated_travel_time", historyTicketDTO.getEstimated_travel_time());
        }
        if (historyTicketDTO.getPrice() != 0) {
            queryWrapper.eq("price", historyTicketDTO.getPrice());
        }
        if (historyTicketDTO.getSeat_class() != null && !historyTicketDTO.getSeat_class().isEmpty()) {
            queryWrapper.eq("seat_class", historyTicketDTO.getSeat_class());
        }
        if (historyTicketDTO.getSeat_number() != 0) {
            queryWrapper.eq("seat_number", historyTicketDTO.getSeat_number());
        }
        if (historyTicketDTO.getUpdate_time() != null) {
            queryWrapper.like("update_time", historyTicketDTO.getUpdate_time());
        }
        queryWrapper.eq("passenger_id", current_id);
        return ticketMapper.selectCount(queryWrapper);
    }


}
