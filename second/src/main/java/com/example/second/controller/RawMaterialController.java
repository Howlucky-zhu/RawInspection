package com.example.second.controller;

import com.example.second.entity.RawMaterial;
import com.example.second.service.MenuService;
import com.example.second.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController    //代表接口中返回都是json格式数据
@RequestMapping("/rawMaterial")
@ResponseBody
@CrossOrigin(allowCredentials = "true")  //运行所有请求、所有域访问，解决跨域问题

public class RawMaterialController {
    @Autowired
    private RawMaterialService rawMaterialService;

    @Autowired
    MenuService menuService;

    /**
     * 查询所有样品信息
     */
    @GetMapping("/findAllRawMaterials")
    public List<RawMaterial> findAllRawMaterials() {
        return this.rawMaterialService.findAllRawMaterials();
    }

    /**
     * 登记样品信息
     */
    @PostMapping("/addRawMaterial")
    public void addRawMaterial(@RequestBody RawMaterial rawMaterial) throws Exception {
        this.rawMaterialService.addRawMaterial(rawMaterial);
    }

    /**
     * 根据样品编号删除样品
     */
    @PostMapping("/deleteRawMaterialByNum")
    public void deleteRawMaterialByNum(@RequestParam Long number) {
        this.rawMaterialService.deleteRawMaterialByNum(number);
    }

    /**
     * 获取选取的数据Id
     */
    @GetMapping("/updateRawMaterialById")
    public List<RawMaterial> updateRawMaterialById(@RequestParam Integer id) {
         return this.rawMaterialService.findRawMaterial_id(id);
//        updateRawMaterial(rawMaterial.get(0));
    }

    /**
     * 更新样品信息
     */
    @PostMapping("/updateRawMaterial")
    public void updateRawMaterial(@RequestBody RawMaterial rawMaterial){
        this.rawMaterialService.updateRawMaterial(rawMaterial);
    }

    /**
     * 根据样品编号进行查询
     */
    @GetMapping("/findRawMaterial")
    public List<RawMaterial> findRawMaterial(@RequestParam String number) {
        return this.rawMaterialService.findRawMaterial(number);
    }

    /**
     * 根据取样日期进行查询
     */
    @GetMapping("/findRawMaterial_date")
    public List<RawMaterial> findRawMaterial_date(@RequestParam  @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return this.rawMaterialService.findRawMaterial_date(date);
    }

    /**
     * 根据样品状态进行查询
     */
    @GetMapping("/findRawMaterial_status")
    public List<RawMaterial> findRawMaterial_status(@RequestParam String status) {
        return this.rawMaterialService.findRawMaterial_status(status);
    }

    /**
     * 批量删除样品信息
     */
    @PostMapping("/deleteRawMaterialsByIds")
    public Integer deleteRawMaterialsByIds(Integer[] ids) {
        return this.rawMaterialService.deleteRawMaterialsByIds(ids);
    }
}
