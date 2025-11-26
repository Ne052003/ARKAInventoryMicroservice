package com.neoapps.driven_adapters.mappers;

import com.neoapps.driven_adapters.entities.SupplierEntity;
import com.neoapps.model.supplier.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier toSupplier(SupplierEntity supplierEntity) {

        if (supplierEntity == null) {
            return null;
        }

        Supplier supplier = new Supplier();
        supplier.setId(supplierEntity.getId());
        supplier.setName(supplierEntity.getName());
        supplier.setEmail(supplierEntity.getEmail());
        supplier.setCreationTime(supplierEntity.getCreationTime());

        return supplier;
    }

    public SupplierEntity toSupplierEntity(Supplier supplier) {

        if (supplier == null) {
            return null;
        }

        SupplierEntity supplierEntity = new SupplierEntity();

        supplierEntity.setId(supplier.getId());
        supplierEntity.setName(supplier.getName());
        supplierEntity.setEmail(supplier.getEmail());
        supplierEntity.setCreationTime(supplier.getCreationTime());

        return supplierEntity;
    }
}
