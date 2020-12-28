package jsfproject.img;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ImagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        int i = 0;
        for (;;) {
        	i++;
            images.add("hotel " + i + ".jpg");
            if(i>8) break;
            
            	
            
            
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}