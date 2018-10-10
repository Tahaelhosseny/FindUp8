package khaled.example.com.findup.models;

public class SaveModel {
    private int store_id;
    private String saved_name;
    private String saved_photo;
    private String saved_description;
    private String saved_type;
    private String save_case;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getSaved_name() {
        return saved_name;
    }

    public void setSaved_name(String saved_name) {
        this.saved_name = saved_name;
    }

    public String getSaved_photo() {
        return saved_photo;
    }

    public void setSaved_photo(String saved_photo) {
        this.saved_photo = saved_photo;
    }

    public String getSaved_description() {
        return saved_description;
    }

    public void setSaved_description(String saved_description) {
        this.saved_description = saved_description;
    }

    public String getSaved_type() {
        return saved_type;
    }

    public void setSaved_type(String saved_type) {
        this.saved_type = saved_type;
    }

    public String getSave_case() {
        return save_case;
    }

    public void setSave_case(String save_case) {
        this.save_case = save_case;
    }
}
