package mk.ukim.finki.dians.eshop.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

@Controller
@RequestMapping("/images")
public class PhotoController {
    @RequestMapping(value = "/get-image",method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage() throws IOException {
        RandomAccessFile f = new RandomAccessFile("marker-icon-2x.png", "r");
        byte[] b = new byte[(int)f.length()];
        f.readFully(b);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);


        return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);



    }
}