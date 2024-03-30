package com.TallerApi.demo.service.producto;


import com.TallerApi.demo.dto.producto.ProductDto;
import com.TallerApi.demo.dto.producto.ProductMapper;
import com.TallerApi.demo.dto.producto.ProductToSaveDto;
import com.TallerApi.demo.model.Product;
import com.TallerApi.demo.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto crearProducto(ProductToSaveDto productToSaveDto) {
        Product product = this.productMapper.productToSaveDtoToProduct(productToSaveDto);
        productRepository.save(product);
        return this.productMapper.productToProductDto(product);
    }

    @Override
    public ProductDto actualizarProducto(Long id,ProductToSaveDto productToSaveDto) {
        Product Product = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El Product no existe"));
        Product.setNombre(productToSaveDto.nombre());
        Product.setPrecio(productToSaveDto.precio());
        Product.setStock(productToSaveDto.stock());
        productRepository.save(Product);
        return this.productMapper.productToProductDto(Product);
    }

    @Override
    public ProductDto buscarProductoById(Long id) {
        Product Product = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El Product no existe"));
        return this.productMapper.productToProductDto(Product);
    }

    @Override
    public void removerProducto(Long id) {
        Product Product = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El Product no existe"));
        productRepository.delete(Product);
    }

    @Override
    public List<ProductDto> getAllProductos() {
        List<Product> product = this.productRepository.findAll();

        return product.stream().map(this.productMapper::productToProductDto).toList();
    }

    @Override
    public List<ProductDto> buscarProductoByTermino(String termino) {
        List<Product> product = this.productRepository.findByNombreContaining(termino);
        return product.stream().map(this.productMapper::productToProductDto).toList();
    }

    @Override
    public List<ProductDto> buscarProductosEnStock() {
        List<Product> product = this.productRepository.findByInStock();
        return product.stream().map(this.productMapper::productToProductDto).toList();
    }

    @Override
    public List<ProductDto> buscarProductoMenoresByPrecioAndStock(Double precio, Integer stock) {
        List<Product> product = this.productRepository.findByPrecioLessThanEqualAndStockLessThanEqual(precio, stock);
        return product.stream().map(this.productMapper::productToProductDto).toList();
    }

}
