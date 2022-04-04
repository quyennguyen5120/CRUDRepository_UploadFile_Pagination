package com.example.springdata_01.Service.ServiceImp;

import com.example.springdata_01.Domain.Product;
import com.example.springdata_01.Repository.ProductRepository;
import com.example.springdata_01.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Value("${config.upload_folder}")
    String UPLOAD_FOLDER;

    @Override
    public Page<Product> findAll(Pageable paging) {
        return productRepository.findAll(paging);
    }

    @Override
    public void updateProduct(MultipartFile file, Product p) {
        try{
            if(!file.isEmpty()){
                String realativeFilePath = null;
                Date date = new Date();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int year = localDate.getYear();
                int month = localDate.getMonthValue();
                String subFolder = month +"_"+year;
                String fullUploadDir = UPLOAD_FOLDER + subFolder;
                File checkDir = new File(fullUploadDir);
                if(checkDir.exists() == true || checkDir.isFile() == true){
                    checkDir.mkdir();
                }
                realativeFilePath = subFolder + Instant.now().getEpochSecond() + file.getOriginalFilename();
                Files.write(Paths.get(UPLOAD_FOLDER + realativeFilePath), file.getBytes());
                p.setImage(realativeFilePath);
            }
            productRepository.save(p);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void insertProduct(MultipartFile file, Product p) {
        try{
            String realativeFilePath = null;
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year = localDate.getYear();
            int month = localDate.getMonthValue();
            String subFolder = month +"_"+year;
            String fullUploadDir = UPLOAD_FOLDER + subFolder;
            File checkDir = new File(fullUploadDir);
            if(checkDir.exists() == true || checkDir.isFile() == true){
                checkDir.mkdir();
            }
            realativeFilePath = subFolder + Instant.now().getEpochSecond() + file.getOriginalFilename();
            Files.write(Paths.get(UPLOAD_FOLDER + realativeFilePath), file.getBytes());
            p.setImage(realativeFilePath);
            productRepository.save(p);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
