package ua.com.learninghub.rest;

/**
 * Created by vasax32 on 17.07.14.
 */
public class UserResponse{
    String description;
    String imagePath;

    UserResponse(){}

    public UserResponse(String descriprion, String imagePath) {
        this.description = descriprion;
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "description='" + description + '\'' +
                ", ImagePath='" + imagePath + '\'' +
                '}';
    }
}
