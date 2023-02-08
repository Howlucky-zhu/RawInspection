package com.example.second.service.impl;

import com.example.second.dao.RawMaterialDao;
import com.example.second.entity.RawMaterial;
import com.example.second.entity.User;
import com.example.second.exception.ValidateException;
import com.example.second.service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Transactional
public class RawMaterialServiceBean implements RawMaterialService {
    @Autowired
    private RawMaterialDao rawMaterialDao;

    public List<RawMaterial> findAllRawMaterials() {
        return this.rawMaterialDao.findAllRawMaterials();
    }

    //返回错误数组
    public List<String> errors(RawMaterial rawMaterial) {
        List<String> errors = new ArrayList<>();
        String regex_id = "^[a-z0-9A-Z]+$";
        if (!(rawMaterial.getNumber().matches(regex_id))) {
            errors.add("样品编号不能包括中文");
        }
        Map<String, Object> result = new HashMap<>();
        // 验证样品编号是否存在
        List<RawMaterial> exsitNumber = this.rawMaterialDao.findRawMaterial(rawMaterial.getNumber());
        if (!exsitNumber.isEmpty()) {
            errors.add("该样品编号已存在，请重新输入");
        }
        return errors;
    }

    @Transactional
    public void addRawMaterial(RawMaterial rawMaterial) {
        if(errors(rawMaterial).size() > 0) {
            throw new ValidateException("-1", errors(rawMaterial).toString());
        } else {
            this.rawMaterialDao.addRawMaterial(rawMaterial);
        }
    }

    @Override
    public void deleteRawMaterialByNum(Long number) {
        this.rawMaterialDao.deleteRawMaterialByNum(number);
        Integer count = this.rawMaterialDao.getCountRawMaterials();
    }

    public List<RawMaterial> findRawMaterial_id(Integer id) {
        return this.rawMaterialDao.findRawMaterial_id(id);
    }
    public void updateRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterialDao.updateRawMaterial(rawMaterial);
    }

    public List<RawMaterial> findRawMaterial(String number) {
        return this.rawMaterialDao.findRawMaterial(number);
    }

    public List<RawMaterial> findRawMaterial_date(Date date) {
        return this.rawMaterialDao.findRawMaterial_date(date);
    }

    public List<RawMaterial> findRawMaterial_status(String status) {
        return this.rawMaterialDao.findRawMaterial_status(status);
    }

    public Integer deleteRawMaterialsByIds(Integer[] ids) {
        return this.rawMaterialDao.deleteRawMaterialsByIds(ids);
    }
}
