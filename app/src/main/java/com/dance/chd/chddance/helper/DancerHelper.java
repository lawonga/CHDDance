package com.dance.chd.chddance.helper;

import com.dance.chd.chddance.R;
import com.dance.chd.chddance.model.Dancer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 11/5/2016.
 */
public class DancerHelper {
    private List<Dancer> femaleDancerList = new ArrayList<>();
    private List<Dancer> maleDancerList = new ArrayList<>();
    private static DancerHelper ourInstance = new DancerHelper();

    public void initInstance() {
        femaleDancerList.add(new Dancer("Blonde", 0, R.drawable.woman_blond, false));
        femaleDancerList.add(new Dancer("Brunette", 0, R.drawable.woman_brown, false));
        femaleDancerList.add(new Dancer("Black", 0, R.drawable.woman_black, false));
        femaleDancerList.add(new Dancer("Red", 0, R.drawable.woman_red, false));
        femaleDancerList.add(new Dancer("Green", 0, R.drawable.woman_green, true));
        femaleDancerList.add(new Dancer("Purple", 0, R.drawable.woman_purple, true));
        maleDancerList.add(new Dancer("Blonde", 0, R.drawable.man_blond, false));
        maleDancerList.add(new Dancer("Brunette", 0, R.drawable.man_brown, false));
        maleDancerList.add(new Dancer("Black", 0, R.drawable.man_black, false));
        maleDancerList.add(new Dancer("Red", 0, R.drawable.man_red, false));
        maleDancerList.add(new Dancer("Green", 0, R.drawable.man_green, true));
        maleDancerList.add(new Dancer("Purple", 0, R.drawable.man_purple, true));
    }

    public static DancerHelper getInstance() {
        return ourInstance;
    }

    private DancerHelper() {
    }

    public List<Dancer> getFemaleDancerList() {
        return femaleDancerList;
    }

    public void setFemaleDancerList(List<Dancer> femaleDancerList) {
        this.femaleDancerList = femaleDancerList;
    }

    /**
     * Female dancer count
     * @return
     */
    public int getFemaleDancerCount() {
        int quantity = 0;
        for (Dancer dancer : femaleDancerList) {
            quantity += dancer.getQuantity();
        }
        return quantity;
    }

    /**
     * Male dancer count
     * @return
     */
    public int getMaleDancerCount() {
        int quantity = 0;
        for (Dancer dancer : maleDancerList) {
            quantity += dancer.getQuantity();
        }
        return quantity;
    }

    /**
     * Clear dancer count
     */
    public void clearDancerList() {
        for (Dancer dancer : femaleDancerList) {
            dancer.setQuantity(0);
        }
        for (Dancer dancer : maleDancerList) {
            dancer.setQuantity(0);
        }
    }

    public List<Dancer> getMaleDancerList() {
        return maleDancerList;
    }
}
