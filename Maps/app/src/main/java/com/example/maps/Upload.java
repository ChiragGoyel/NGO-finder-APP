package com.example.maps;


public class Upload {
    private String imageName;
    private String imageUrl;
//    private String Description;;
    private String material;

    public Upload(){}   //empty constructor needed for Firebase database

    public Upload(String imageName, String imageUrl, String material) {
        if(imageName.trim().equals("")) {imageName = "No Name";}

        this.imageName = imageName;
        this.imageUrl = imageUrl;
     this.material = material;
    }

    public String getImageName() {
        return imageName;
    }

//    public String getDescription() {
//        return Description;
//    }

    public String getMaterial() {
        return material;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

