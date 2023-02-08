package com.example.second.dao;

import com.example.second.entity.RawMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component(value = "RawMaterialDao")
public interface RawMaterialDao {
    List<RawMaterial> findAllRawMaterials();

    int deleteRawMaterialByNum(Long number);

//    int insert(RawMaterial rawMaterial);

    int addRawMaterial(RawMaterial rawMaterial);

    RawMaterial selectByPrimaryKey(Long uid);

    List<RawMaterial> findRawMaterial_id(Integer id);

    int updateRawMaterial(RawMaterial rawMaterial);

//    int updateByPrimaryKey(RawMaterial rawMaterial);

    List<RawMaterial> findRawMaterial(String number);

    List<RawMaterial> findRawMaterial_date(Date date);

    List<RawMaterial> findRawMaterial_status(String number);

    Integer deleteRawMaterialsByIds(@Param("ids") Integer[] ids);

    int getCountRawMaterials();
}

