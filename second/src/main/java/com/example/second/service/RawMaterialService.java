package com.example.second.service;

import com.example.second.entity.RawMaterial;

import java.util.Date;
import java.util.List;

public interface RawMaterialService {
    public List<RawMaterial> findAllRawMaterials();

    public void addRawMaterial(RawMaterial rawMaterial);

    public void deleteRawMaterialByNum(Long number);

    public void updateRawMaterial(RawMaterial rawMaterial);

    List<RawMaterial> findRawMaterial(String number);

    List<RawMaterial> findRawMaterial_date(Date date);

    List<RawMaterial> findRawMaterial_status(String status);

    List<RawMaterial> findRawMaterial_id(Integer id);

    public Integer deleteRawMaterialsByIds(Integer[] ids);
}
