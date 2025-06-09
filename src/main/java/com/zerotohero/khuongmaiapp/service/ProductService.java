package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.ProductRequest;
import com.zerotohero.khuongmaiapp.dto.response.ProductResponse;
import com.zerotohero.khuongmaiapp.entity.Product;
import com.zerotohero.khuongmaiapp.entity.ProductImage;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.mapper.ProductMapper;
import com.zerotohero.khuongmaiapp.repository.CategoryRepository;
import com.zerotohero.khuongmaiapp.repository.ProductImageRepository;
import com.zerotohero.khuongmaiapp.repository.ProductRepository;
import com.zerotohero.khuongmaiapp.validate.ValidateImage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    CloudinaryService cloudinaryService;
    ProductMapper productMapper;
    ProductImageRepository productImageRepository;
    ValidateImage validateImage;

    public ProductResponse createProduct(ProductRequest request, MultipartFile[] files) throws IOException {
        log.info("Creating product: {}", request);

        // 1. Validate toàn bộ file trước
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                validateImage.validateImageFile(file); // nếu lỗi sẽ ném exception luôn, không upload gì
            }
        }

        // 2. Chỉ khi VALID thì mới tạo product
        Product product = productMapper.toProduct(request);
        product.setCategory(categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new KMAppException(ErrorCode.CATEGORY_NOT_FOUND)));

        productRepository.save(product);
        // 3. Upload ảnh và lưu ProductImage nếu mọi thứ đã OK
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String imageUrl = cloudinaryService.uploadFile(file);

                ProductImage productImage = new ProductImage();
                productImage.setImageUrl(imageUrl);
                productImage.setProduct(product);

                productImageRepository.save(productImage);
            }
        }
        return productMapper.toProductResponse(product);
    }

    public ProductResponse updateProduct(String id,ProductRequest productRequest,MultipartFile[] files) throws IOException {
        log.info("Updating product: {}", productRequest);

        // 1. Validate toàn bộ file trước
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                validateImage.validateImageFile(file); // nếu lỗi sẽ ném exception luôn, không upload gì
            }
        }
        Product product= productRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.PRODUCT_NOT_FOUND));
        List<ProductImage> productImageList=productImageRepository.findAllByProduct(product);
        for(ProductImage productImage:productImageList){
            String imageUrl = productImage.getImageUrl();
            int dotIndex = imageUrl.lastIndexOf('.');
            int startIndex = imageUrl.indexOf("khuongmaiimg/");
            String publicId = imageUrl.substring(startIndex, dotIndex);
            cloudinaryService.deleteFile(publicId);
            productImageRepository.delete(productImage);
        }
        productMapper.updateProduct(product,productRequest);
        productRepository.save(product);

        // 3. Upload ảnh và lưu ProductImage nếu mọi thứ đã OK
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String imageUrl = cloudinaryService.uploadFile(file);

                ProductImage productImage = new ProductImage();
                productImage.setImageUrl(imageUrl);
                productImage.setProduct(product);

                productImageRepository.save(productImage);
            }
        }
        return productMapper.toProductResponse(product);
    }
}
