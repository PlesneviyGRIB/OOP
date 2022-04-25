package com.savchenko.testProject.supportive;

import com.savchenko.testProject.models.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class SearchItemParams extends Item {
    private boolean considerOnlineOrder;
    private boolean considerCredit;

    public boolean match(Item item){
        if(!getType().equals("") && !item.getType().toUpperCase().contains(getType().toUpperCase())) return false;
        if(!getCountry().equals("") && !item.getCountry().toUpperCase().contains(getCountry().toUpperCase())) return false;
        if(!getManufacture().equals("") && !item.getManufacture().toUpperCase().contains(getManufacture().toUpperCase())) return false;
        if(considerOnlineOrder && !getOnlineOrder().equals(item.getOnlineOrder())) return false;
        if(considerCredit && !getCredit().equals(item.getCredit())) return false;
        return true;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        if(!getType().equals("")) stringBuilder.append(" Type: ").append(getType());
        if(!getCountry().equals("")) stringBuilder.append(" Country: ").append(getCountry());
        if(!getManufacture().equals("")) stringBuilder.append(" Manufacture: ").append(getManufacture());
        if(considerOnlineOrder) stringBuilder.append(" OnlineOrder: ").append(getOnlineOrder());
        if(considerCredit) stringBuilder.append(" Credit: ").append(getCredit());
        return stringBuilder.toString();
    }
}