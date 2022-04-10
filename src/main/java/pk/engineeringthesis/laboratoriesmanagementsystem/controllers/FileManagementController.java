package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class FileManagementController {

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest req ) {
        String originalFilename = file.getOriginalFilename();
        String fileNameWithOutExt = FilenameUtils.removeExtension(originalFilename);
        String fileExt = FilenameUtils.getExtension(originalFilename);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmm");

        String fileName = fileNameWithOutExt + LocalDateTime.now().format(formatter) +"."+fileExt;
        String root = req.getServletContext().getRealPath("/");
        try {
            file.transferTo( new File(root +"/gfx/"+ fileName));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }
        return new ResponseEntity<>("\\..\\gfx\\"+ fileName, new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping("/deleteimage")
    @ResponseBody
    public ResponseEntity<?> deleteImage(@RequestParam("path") String path, HttpServletRequest req ) {


        String filepath = req.getServletContext().getRealPath("/")+"\\gfx\\"+path;
        File myObj = new File(filepath);
        if(myObj.delete()) {
            return new ResponseEntity<>( HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
