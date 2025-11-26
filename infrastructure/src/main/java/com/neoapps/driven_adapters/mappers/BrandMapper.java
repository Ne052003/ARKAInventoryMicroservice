package com.neoapps.driven_adapters.mappers;

import com.neoapps.driven_adapters.entities.BrandEntity;
import com.neoapps.model.brand.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public Brand toBrand(BrandEntity brandEntity) {

        if (brandEntity == null) {
            return null;
        }

        Brand brand = new Brand();
        brand.setId(brandEntity.getId());
        brand.setName(brandEntity.getName());
        brand.setCreationTime(brandEntity.getCreationTime());

        return brand;
    }

    public BrandEntity toBrandEntity(Brand brand) {

        if (brand == null) {
            return null;
        }

        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setId(brand.getId());
        brandEntity.setName(brand.getName());
        brandEntity.setCreationTime(brand.getCreationTime());

        return brandEntity;
    }
}
