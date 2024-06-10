package com.yumefusaka.SmartAirport.controller;

import com.yumefusaka.SmartAirport.common.Result;
import com.yumefusaka.SmartAirport.pojo.DTO.AddGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.BuyGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.DeleteGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.DTO.PutGoodsDTO;
import com.yumefusaka.SmartAirport.pojo.VO.BuyGoodsVO;
import com.yumefusaka.SmartAirport.service.MerchantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/merchant")
@Tag(name = "商户")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/Goods/add")
    @Operation(summary = "添加商品")
    public Result<String> addGoods(@RequestBody AddGoodsDTO addGoodsDTO) {
        merchantService.addGoods(addGoodsDTO);
        return Result.success("添加商品成功");
    }

    @DeleteMapping("/Goods/delete")
    @Operation(summary = "删除商品")
    public Result<String> deleteGoods(@RequestBody DeleteGoodsDTO deleteGoodsDTO) {
        merchantService.deleteGoods(deleteGoodsDTO);
        return Result.success("删除商品成功");
    }

    @GetMapping("/Goods/find")
    @Operation(summary = "查找商品")
    public Result<List<BuyGoodsVO>> findGoods(@RequestParam Long pageNo, @RequestParam Long pageSize) {
        merchantService.findGoods(pageNo, pageSize);
        return Result.success();
    }

    @PutMapping("/Goods/update")
    @Operation(summary = "更新商品")
    public Result<String> updateGoods(@RequestBody PutGoodsDTO putGoodsDTO) {
        merchantService.updateGoods(putGoodsDTO);
        return Result.success("更新商品成功");
    }
}
