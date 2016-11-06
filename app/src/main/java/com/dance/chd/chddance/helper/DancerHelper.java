package com.dance.chd.chddance.helper;

import com.dance.chd.chddance.model.Dancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 11/5/2016.
 */
public class DancerHelper {
    private List<Dancer> dancerList = new ArrayList<>();
    private static DancerHelper ourInstance = new DancerHelper();

    public void initInstance() {
        dancerList.add(new Dancer("Blonde", 0, 0, false));
        dancerList.add(new Dancer("Brunette", 0, 0, false));
        dancerList.add(new Dancer("Black", 0, 0, false));
        dancerList.add(new Dancer("White", 0, 0, false));
        dancerList.add(new Dancer("Green", 0, 0, true));
        dancerList.add(new Dancer("Purple", 0, 0, true));
    }

    public static DancerHelper getInstance() {
        return ourInstance;
    }

    private DancerHelper() {
    }

    public List<Dancer> getDancerList() {
        return dancerList;
    }

    public void setDancerList(List<Dancer> dancerList) {
        this.dancerList = dancerList;
    }

    public void clearDancerList() {
        for (Dancer dancer : dancerList) {
            dancer.setQuantity(0);
        }
    }
}
