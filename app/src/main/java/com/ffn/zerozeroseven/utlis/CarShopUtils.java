package com.ffn.zerozeroseven.utlis;

import android.content.Context;

import com.ffn.zerozeroseven.bean.CarShopInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GT on 2018/1/10.
 */

public class CarShopUtils {
    /**
     * @param removeIndex 需要移除购物车某个索引对应的值
     * @return
     */
    public static void CloseCarShops(Context context, int removeIndex) {
        CarShopInfo carShopInfo = (CarShopInfo) SharePrefUtils.readObject(context, "carShopInfo");
        if (carShopInfo != null && carShopInfo.getShopInfos().size() > 0) {
            carShopInfo.getShopInfos().remove(removeIndex);
            SharePrefUtils.saveObject(context, "carShopInfo", carShopInfo);
        }
    }

    /**
     * @param context
     * @param shopInfo 需要被添加进购物车的实例对象
     */
    public static void addCarShops(Context context, CarShopInfo.ShopInfo shopInfo) {
        CarShopInfo carShopInfo = (CarShopInfo) SharePrefUtils.readObject(context, "carShopInfo");
        if(carShopInfo!=null){
            if (carShopInfo.getShopInfos().size() > 0 ) {//购物车里面是有商品的 判断被添加商品是否存在于购物车里 如果有则只增加商品数量
                List<CarShopInfo.ShopInfo> shopInfos = carShopInfo.getShopInfos();
                for (int i = 0; i < shopInfos.size(); i++) {
                    if (shopInfo.getGoodsId() == shopInfos.get(i).getGoodsId()) {
                        shopInfos.get(i).setBuyCount(shopInfos.get(i).getBuyCount() + 1);
                        SharePrefUtils.saveObject(context, "carShopInfo", carShopInfo);
                        return;
                    }
                }
                shopInfos.add(shopInfo);
                carShopInfo.setShopInfos(shopInfos);
                SharePrefUtils.saveObject(context, "carShopInfo", carShopInfo);

            }else{//购物车里面是空的
                List<CarShopInfo.ShopInfo> list = new ArrayList<>();
                list.add(shopInfo);
                carShopInfo.setShopInfos(list);
                SharePrefUtils.saveObject(context, "carShopInfo", carShopInfo);
            }
        }else{
            List<CarShopInfo.ShopInfo> list = new ArrayList<>();
            list.add(shopInfo);
            carShopInfo.setShopInfos(list);
            SharePrefUtils.saveObject(context, "carShopInfo", carShopInfo);
        }


    }
}
