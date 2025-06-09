package com.zerotohero.khuongmaiapp.validate;

import com.zerotohero.khuongmaiapp.exception.ErrorCode;
import com.zerotohero.khuongmaiapp.exception.KMAppException;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Component
public class ValidateImage {
    private static final long max_file_size=5*1024*1024;
    private static final Set<String> allow_file_extensions=Set.of("jpg","jpeg","png");
    private final Tika tika=new Tika();

    private String getFileExtension(String fileName){
        if(fileName==null) return "";
        int dotIndex=fileName.lastIndexOf('.');
        if(dotIndex > 0 && dotIndex < fileName.length()-1){
            return fileName.substring(dotIndex+1);
        }
        return "";
    }

    public void validateImageFile(MultipartFile file) throws IOException {
        String filename=file.getOriginalFilename();
        String extension=getFileExtension(filename);
        if (file.getSize() > max_file_size) {
            throw new KMAppException(ErrorCode.FILE_TOO_LARGE);
        }
        if(!allow_file_extensions.contains(extension.toLowerCase())){
            throw new KMAppException(ErrorCode.FILE_EXTENSION_INVALID);
        }
        String mimeType= tika.detect(file.getInputStream());
        if(!mimeType.startsWith("image/")){
            throw new KMAppException(ErrorCode.THIS_IS_NOT_IMAGE);
        }
    }
}
