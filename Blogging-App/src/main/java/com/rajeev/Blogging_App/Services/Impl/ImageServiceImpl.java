package com.rajeev.Blogging_App.Services.Impl;

import com.rajeev.Blogging_App.Services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {


    @Override
    public String uploadImage(String path, MultipartFile image) throws IOException {

        //file name
        String imageName = image.getOriginalFilename();

        //generating random name
        String randomId = UUID.randomUUID().toString();

        if (imageName == null) throw new AssertionError("there is no image of this name");

        String randomName = null;

        if (imageName.substring(imageName.lastIndexOf(".")).equalsIgnoreCase(".png")
                || imageName.substring(imageName.lastIndexOf(".")).equalsIgnoreCase(".jpg")
                || imageName.substring(imageName.lastIndexOf(".")).equalsIgnoreCase(".jpeg")) {
            randomName = randomId.concat(imageName.substring(imageName.lastIndexOf(".")));
        }

        //getting  full path of image
        String fullPath = path + File.separator + randomName;

        //create folder if not created
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //file copy
        Files.copy(image.getInputStream() , Paths.get(fullPath));

        return  randomName;
    }

    @Override
    public InputStream getImage(String path, String imageName) throws FileNotFoundException {
        String fullPath = path + File.separator + imageName;

        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
