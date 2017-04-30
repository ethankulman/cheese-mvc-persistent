package org.launchcode.models.forms;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

/**
 * Created by ethan on 4/28/17.
 */
public class AddMenuItemForm {

    private Menu menu;

    private Iterable<Cheese> cheeses;

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    public int getMenuId(){
        return this.menuId;
    }

    public int getCheeseId(){
        return this.cheeseId;
    }

    public Menu getMenu(){
        return this.menu;
    }

    public Iterable<Cheese> getCheeses(){
        return this.cheeses;
    }

    public void setMenuAndCheeses(Menu menu, Iterable<Cheese> cheeses){
        this.cheeses = cheeses;
        this.menu = menu;
    }



}
