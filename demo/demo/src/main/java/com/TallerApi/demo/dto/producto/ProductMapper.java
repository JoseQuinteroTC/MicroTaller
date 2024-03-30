package com.TallerApi.demo.dto.producto;

import com.TallerApi.demo.dto.detalleEnvio.DetalleEnvioDto;
import com.TallerApi.demo.dto.detalleEnvio.DetalleEnvioToSaveDto;
import com.TallerApi.demo.model.DetalleEnvio;
import com.TallerApi.demo.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto productToProductDto (Product product);

    Product productDtoToProduct(ProductDto productDto);

    Product productToSaveDtoToProduct(ProductToSaveDto productToSaveDto);
}
