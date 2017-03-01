package be.howest.pekka.roeselarevrijwilligt;

import java.util.ArrayList;

/**
 * Created by peksu on 11.11.2016.
 */

public class parent_row {

    private String name;
    private ArrayList<child_row> childList;

    public parent_row(String name, ArrayList<child_row> childList) {
        this.name = name;
        this.childList = childList;
    }

    public ArrayList<child_row> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<child_row> childList) {
        this.childList = childList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
