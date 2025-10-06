package com.rajeev.Blogging_App.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface ImageService {

    String uploadImage(MultipartFile image) throws IOException;


}
