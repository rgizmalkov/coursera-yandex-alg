package Utils.annotations;

/**
 * Created by elion on 19.02.2017.
 */
public enum CheckBox {
    TEST_PASSED("[V]","Test passed"),
    TEST_NOT_PASSED("[X]","Test not passed");


    private String box;
    private String definition;

    CheckBox(String box, String definition) {
        this.box = box;
        this.definition = definition;
    }

    public String getBox() {
        return box;
    }

    public String getDefinition() {
        return definition;
    }
}
