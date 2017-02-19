package Utils.annotations;

/**
 * Created by elion on 19.02.2017.
 */
public class Review {
    private String title;
    private String message;
    private CheckBox box;

    public Review(String title, String message, CheckBox box) {
        this.title = title;
        this.message = message;
        this.box = box;
    }

    public Review(String title, CheckBox box) {
        this.title = title;
        this.box = box;
    }

    @Override
    public String toString() {
        return "TEST [" + title + "]: " + (message != null ? message : "") + " " + box.getBox();
    }
}
