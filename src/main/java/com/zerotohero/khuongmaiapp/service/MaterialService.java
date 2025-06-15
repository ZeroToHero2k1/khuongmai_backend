package com.zerotohero.khuongmaiapp.service;

import com.zerotohero.khuongmaiapp.dto.request.MaterialRequest;
import com.zerotohero.khuongmaiapp.entity.Material;
import com.zerotohero.khuongmaiapp.entity.MaterialImage;
import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import com.zerotohero.khuongmaiapp.repository.MaterialImageRepository;
import com.zerotohero.khuongmaiapp.repository.MaterialRepository;
import com.zerotohero.khuongmaiapp.validate.ValidateImage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class MaterialService {
    MaterialRepository materialRepository;
    ValidateImage validateImage;
    CloudinaryService cloudinaryService;
    MaterialImageRepository materialImageRepository;

    public Material createMaterial(MaterialRequest request, MultipartFile[] files) throws IOException {
        if(files!=null&&files.length>0){
            for(MultipartFile file:files) {
                validateImage.validateImageFile(file);
            }
        }
        Material material=Material.builder()
                .name(request.getName())
                .unit(request.getUnit())
                .description(request.getDescription())
                .unitPrice(request.getUnitPrice())
                .supplier(request.getSupplier())
                .build();
        materialRepository.save(material);
        if(files!=null&&files.length>0){
            for(MultipartFile file:files) {
                String imageUrl=cloudinaryService.uploadFile(file);
                MaterialImage materialImage = new MaterialImage();
                materialImage.setMaterial(material);
                materialImage.setImageUrl(imageUrl);
                materialImageRepository.save(materialImage);
            }
        }
        return material;
    }

    public Material updateMaterial(String id,MaterialRequest request, MultipartFile[] files) throws IOException {
        if(files!=null&&files.length>0){
            for(MultipartFile file:files) {
                validateImage.validateImageFile(file);
            }
        }
        Material material=materialRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.MATERIAL_NOT_FOUND));
        material.setName(request.getName());
        material.setUnit(request.getUnit());
        material.setDescription(request.getDescription());
        material.setUnitPrice(request.getUnitPrice());
        material.setSupplier(request.getSupplier());
        materialRepository.save(material);

        List<MaterialImage> materialImageList=materialImageRepository.findAllByMaterial(material);
        for(MaterialImage materialImage:materialImageList){
            String imageUrl = materialImage.getImageUrl();
            int dotIndex = imageUrl.lastIndexOf('.');
            int startIndex = imageUrl.indexOf("khuongmaiimg/");
            String publicId = imageUrl.substring(startIndex, dotIndex);
            cloudinaryService.deleteFile(publicId);
            materialImageRepository.delete(materialImage);
        }

        if(files!=null&&files.length>0){
            for(MultipartFile file:files) {
                String imageUrl=cloudinaryService.uploadFile(file);
                MaterialImage materialImage = new MaterialImage();
                materialImage.setMaterial(material);
                materialImage.setImageUrl(imageUrl);
                materialImageRepository.save(materialImage);
            }
        }
        return material;
    }

    public Page<Material> searchMaterials(String kw, Pageable pageable){
        return materialRepository.searchMaterials(kw,pageable);
    }

    public Material searchMaterialById(String id){
        return materialRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.MATERIAL_NOT_FOUND));
    }

    public void deleteMaterial(String id) throws IOException {
        Material material=materialRepository.findById(id).orElseThrow(()->new KMAppException(ErrorCode.MATERIAL_NOT_FOUND));
        List<MaterialImage> materialImageList=materialImageRepository.findAllByMaterial(material);
        for(MaterialImage materialImage:materialImageList){
            String imageUrl = materialImage.getImageUrl();
            int dotIndex = imageUrl.lastIndexOf('.');
            int startIndex = imageUrl.indexOf("khuongmaiimg/");
            String publicId = imageUrl.substring(startIndex, dotIndex);
            cloudinaryService.deleteFile(publicId);
            materialImageRepository.delete(materialImage);
        }
        materialRepository.delete(material);
    }

}
