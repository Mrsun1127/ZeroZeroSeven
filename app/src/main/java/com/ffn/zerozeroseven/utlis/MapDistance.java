package com.ffn.zerozeroseven.utlis;

import java.text.DecimalFormat;

public class MapDistance {
    private double DEF_PI = 3.14159265359;

    private double DEF_2PI = 6.28318530712;
    private double DEF_PI180 = 0.01745329252; // PI/180.0
    private double DEF_R = 6370693.5; // radius of earth

    private MapDistance() {
    }

    private static MapDistance instance;

    public static synchronized MapDistance getInstance() {
        if (instance == null) {
            instance = new MapDistance();
        }
        return instance;
    }

    private String trans(double distance) {
        boolean isBig = false;
        if (distance >= 1000) {
            distance /= 1000;
            isBig = true;
        }
        return (new DecimalFormat(".00").format(distance)) + (isBig ? "km" : "m");
    }

    public String getShortDistance(double lon1, double lat1, double lon2, double lat2) {
        double ew1, ns1, ew2, ns2;
        double dx, dy, dew;
        double distance;
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 经度差
        dew = ew1 - ew2;        // 若跨东经和西经180 度，进行调整
        if (dew > DEF_PI)
            dew = DEF_2PI - dew;
        else if (dew < -DEF_PI)
            dew = DEF_2PI + dew;
        dx = DEF_R * Math.cos(ns1) * dew;
        // 东西方向长度(在纬度圈上的投影长度)
        dy = DEF_R * (ns1 - ns2);
        // 南北方向长度(在经度圈上的投影长度)
        // 勾股定理求斜边长
        distance = Math.sqrt(dx * dx + dy * dy);
        return trans(distance);
    }

    public String getLongDistance(double lon1, double lat1, double lon2, double lat2) {
        double ew1, ns1, ew2, ns2;
        double distance;
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
        if (distance > 1.0)
            distance = 1.0;
        else if (distance < -1.0)
            distance = -1.0;
        distance = DEF_R * Math.acos(distance);
        return trans(distance);

    }

}
