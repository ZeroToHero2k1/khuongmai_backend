package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.CategoryRequest;
import com.zerotohero.khuongmaiapp.entity.Category;
import com.zerotohero.khuongmaiapp.entity.Product;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequest request){
        if(categoryRepository.existsByName(request.getName()))
            throw new KMAppException(ErrorCode.CATEGORY_NAME_EXISTED);
        Category category=new Category();
        category.setName(request.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(String id,CategoryRequest request){
        Category category= categoryRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.CATEGORY_NOT_FOUND));
        category.setName(request.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(String id){
        Category category= categoryRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.CATEGORY_NOT_FOUND));
        for(Product product:category.getProductList()){
            product.setCategory(null);
        }
        categoryRepository.delete(category);
    }

    public Page<Category> searchCategories(String keyword, Pageable pageable){
        return categoryRepository.searchCategories(keyword,pageable);
    }

}
