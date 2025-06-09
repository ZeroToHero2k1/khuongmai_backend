package com.zerotohero.khuongmaiapp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService() {
        cloudinary=new Cloudinary(
                ObjectUtils.asMap("cloud_name", "dhzgwqu4j",
                        "api_key", "265292525796373",
                        "api_secret", "g1Im1tIdAfQqwf-_uUK6tGb_JBo"
                )
        );
    }

    public String uploadFile(MultipartFile file) throws IOException{
        Map uploadResult=cloudinary.uploader().upload(file.getBytes(),ObjectUtils.asMap(
                "folder","khuongmaiimg"
        ));
        return uploadResult.get("secure_url").toString();
    }

    public void deleteFile(String publicId) throws IOException{
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }


}
