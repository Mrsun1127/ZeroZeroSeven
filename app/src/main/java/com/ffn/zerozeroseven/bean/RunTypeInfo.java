package com.ffn.zerozeroseven.bean;


import java.util.List;

public class RunTypeInfo {
    private List<PlacesBean> places;


    public List<PlacesBean> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlacesBean> places) {
        this.places = places;
    }



    public static class PlacesBean{
        private String title;
        private int drawable;
        private int drawablenor;

        public int getDrawablenor() {
            return drawablenor;
        }

        public void setDrawablenor(int drawablenor) {
            this.drawablenor = drawablenor;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDrawable() {
            return drawable;
        }

        public void setDrawable(int drawable) {
            this.drawable = drawable;
        }


    }
}
