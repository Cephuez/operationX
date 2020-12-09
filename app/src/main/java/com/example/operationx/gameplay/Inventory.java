package com.example.operationx.gameplay;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

    private int levelID;
    private Activity currActivity;
    private HashMap<InventoryItems, Integer> inventoryList;

    private InventoryItems item1,item2,item3;
    public Inventory(Activity currActivity, int levelID){
        this.levelID = levelID;
        this.currActivity = currActivity;
        inventoryList = new HashMap<InventoryItems, Integer>();
        setUpInventory();
    }

    public int getScore(){
        int score1 = 10;
        int score2 = inventoryList.get(item2) * 20;
        int score3 = inventoryList.get(item3) * 20;
        return score1 + score2 + score3;
    }
    public boolean useItem(int itemID){
        if(itemID == 2){
            return checkIfItemAvailable(item2);
        }else if(itemID == 3){
            return checkIfItemAvailable(item3);
        }
        System.out.println("true");
        return true;
    }

    private boolean checkIfItemAvailable(InventoryItems item){
        int counts = inventoryList.get(item);
        if(counts != 0){
            inventoryList.put(item, counts - 1);
            return true;
        }
        return false;
    }
    private void setUpInventory(){
        if(levelID == 1){
            setUpLevelOne();
        }else if(levelID == 2){
            System.out.println("Level 2");
            setUpLevelTwo();
        }
    }

    private void setUpLevelOne(){
        item1 = new InventoryItems(currActivity, 1,1);
        item2 = new InventoryItems(currActivity,1,2);
        item3 = new InventoryItems(currActivity,1,3);
        inventoryList.put(item1, -1);
        inventoryList.put(item2, 3);
        inventoryList.put(item3, 3);
    }

    private void setUpLevelTwo() {
        item1 = new InventoryItems(currActivity, 2, 1);
        item2 = new InventoryItems(currActivity, 2, 2);
        item3 = new InventoryItems(currActivity, 2, 3);
        inventoryList.put(item1, -1);
        inventoryList.put(item2, 3);
        inventoryList.put(item3, 3);
    }
}
