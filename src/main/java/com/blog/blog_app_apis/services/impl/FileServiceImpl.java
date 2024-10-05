package com.blog.blog_app_apis.services.impl;

import com.blog.blog_app_apis.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // get the file name
        String name = file.getOriginalFilename();

        // to distinguish the file when user upload file with same name
        // add any random id to the file name

        // e.g: 'abc.png' h tb ye convert to 'randomID.png'.

        String randomID = UUID.randomUUID().toString();
        // dot se random wale ke saath add ho jayega
        // uske naam nya filename jis naam se file save karenge
        String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

        // get full path i.e folder and file name
        String filePath = path + File.separator + fileName1;

        // create folder if not created
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }

        // copy file. path_source, path_target
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        InputStream inputStream = new FileInputStream(fullPath);

        // for reading data from database , can write logic here
        return inputStream;
    }
}
