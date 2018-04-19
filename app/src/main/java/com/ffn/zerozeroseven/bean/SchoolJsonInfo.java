package com.ffn.zerozeroseven.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GT on 2017/12/10.
 */

public class SchoolJsonInfo implements Serializable {
    private List<PlacesBean> places;

    public List<PlacesBean> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlacesBean> places) {
        this.places = places;
    }

    public static class PlacesBean implements Serializable{
        /**
         * orderNum : 999
         * id : 220000
         * parentId : 000000
         * name : 吉林省
         * selectable : true
         * children : [{"orderNum":999,"id":"220100","parentId":"220000","name":"长春市","selectable":"true","children":[{"orderNum":999,"id":"220101","parentId":"220100","name":"市辖区","selectable":"false"},{"orderNum":999,"id":"220102","parentId":"220100","name":"南关区","selectable":"true"},{"orderNum":999,"id":"220103","parentId":"220100","name":"宽城区","selectable":"true"},{"orderNum":999,"id":"220104","parentId":"220100","name":"朝阳区","selectable":"true"},{"orderNum":999,"id":"220105","parentId":"220100","name":"二道区","selectable":"true"},{"orderNum":999,"id":"220106","parentId":"220100","name":"绿园区","selectable":"true"},{"orderNum":999,"id":"220112","parentId":"220100","name":"双阳区","selectable":"true"},{"orderNum":999,"id":"220113","parentId":"220100","name":"九台区","selectable":"true"},{"orderNum":999,"id":"220122","parentId":"220100","name":"农安县","selectable":"true"},{"orderNum":999,"id":"220182","parentId":"220100","name":"榆树市","selectable":"true"},{"orderNum":999,"id":"220183","parentId":"220100","name":"德惠市","selectable":"true"}]},{"orderNum":999,"id":"220200","parentId":"220000","name":"吉林市","selectable":"true","children":[{"orderNum":999,"id":"220201","parentId":"220200","name":"市辖区","selectable":"false"},{"orderNum":999,"id":"220202","parentId":"220200","name":"昌邑区","selectable":"true"},{"orderNum":999,"id":"220203","parentId":"220200","name":"龙潭区","selectable":"true"},{"orderNum":999,"id":"220204","parentId":"220200","name":"船营区","selectable":"true"},{"orderNum":999,"id":"220211","parentId":"220200","name":"丰满区","selectable":"true"},{"orderNum":999,"id":"220221","parentId":"220200","name":"永吉县","selectable":"true"},{"orderNum":999,"id":"220281","parentId":"220200","name":"蛟河市","selectable":"true"},{"orderNum":999,"id":"220282","parentId":"220200","name":"桦甸市","selectable":"true"},{"orderNum":999,"id":"220283","parentId":"220200","name":"舒兰市","selectable":"true"},{"orderNum":999,"id":"220284","parentId":"220200","name":"磐石市","selectable":"true"}]},{"orderNum":999,"id":"220300","parentId":"220000","name":"四平市","selectable":"true","children":[{"orderNum":999,"id":"220301","parentId":"220300","name":"市辖区","selectable":"false"},{"orderNum":999,"id":"220302","parentId":"220300","name":"铁西区","selectable":"true"},{"orderNum":999,"id":"220303","parentId":"220300","name":"铁东区","selectable":"true"},{"orderNum":999,"id":"220322","parentId":"220300","name":"梨树县","selectable":"true"},{"orderNum":999,"id":"220323","parentId":"220300","name":"伊通满族自治县","selectable":"true"},{"orderNum":999,"id":"220381","parentId":"220300","name":"公主岭市","selectable":"true"},{"orderNum":999,"id":"220382","parentId":"220300","name":"双辽市","selectable":"true"}]},{"orderNum":999,"id":"220400","parentId":"220000","name":"辽源市","selectable":"true","children":[{"orderNum":999,"id":"220401","parentId":"220400","name":"市辖区","selectable":"false"},{"orderNum":999,"id":"220402","parentId":"220400","name":"龙山区","selectable":"true"},{"orderNum":999,"id":"220403","parentId":"220400","name":"西安区","selectable":"true"},{"orderNum":999,"id":"220421","parentId":"220400","name":"东丰县","selectable":"true"},{"orderNum":999,"id":"220422","parentId":"220400","name":"东辽县","selectable":"true"}]},{"orderNum":999,"id":"220500","parentId":"220000","name":"通化市","selectable":"true","children":[{"orderNum":999,"id":"220501","parentId":"220500","name":"市辖区","selectable":"false"},{"orderNum":999,"id":"220502","parentId":"220500","name":"东昌区","selectable":"true"},{"orderNum":999,"id":"220503","parentId":"220500","name":"二道江区","selectable":"true"},{"orderNum":999,"id":"220521","parentId":"220500","name":"通化县","selectable":"true"},{"orderNum":999,"id":"220523","parentId":"220500","name":"辉南县","selectable":"true"},{"orderNum":999,"id":"220524","parentId":"220500","name":"柳河县","selectable":"true"},{"orderNum":999,"id":"220581","parentId":"220500","name":"梅河口市","selectable":"true"},{"orderNum":999,"id":"220582","parentId":"220500","name":"集安市","selectable":"true"}]},{"orderNum":999,"id":"220600","parentId":"220000","name":"白山市","selectable":"true","children":[{"orderNum":999,"id":"220601","parentId":"220600","name":"市辖区","selectable":"false"},{"orderNum":999,"id":"220602","parentId":"220600","name":"浑江区","selectable":"true"},{"orderNum":999,"id":"220605","parentId":"220600","name":"江源区","selectable":"true"},{"orderNum":999,"id":"220621","parentId":"220600","name":"抚松县","selectable":"true"},{"orderNum":999,"id":"220622","parentId":"220600","name":"靖宇县","selectable":"true"},{"orderNum":999,"id":"220623","parentId":"220600","name":"长白朝鲜族自治县","selectable":"true"},{"orderNum":999,"id":"220681","parentId":"220600","name":"临江市","selectable":"true"}]},{"orderNum":999,"id":"220700","parentId":"220000","name":"松原市","selectable":"true","children":[{"orderNum":999,"id":"220701","parentId":"220700","name":"市辖区","selectable":"false"},{"orderNum":999,"id":"220702","parentId":"220700","name":"宁江区","selectable":"true"},{"orderNum":999,"id":"220721","parentId":"220700","name":"前郭尔罗斯蒙古族自治县","selectable":"true"},{"orderNum":999,"id":"220722","parentId":"220700","name":"长岭县","selectable":"true"},{"orderNum":999,"id":"220723","parentId":"220700","name":"乾安县","selectable":"true"},{"orderNum":999,"id":"220781","parentId":"220700","name":"扶余市","selectable":"true"}]},{"orderNum":999,"id":"220800","parentId":"220000","name":"白城市","selectable":"true","children":[{"orderNum":999,"id":"220801","parentId":"220800","name":"市辖区","selectable":"false"},{"orderNum":999,"id":"220802","parentId":"220800","name":"洮北区","selectable":"true"},{"orderNum":999,"id":"220821","parentId":"220800","name":"镇赉县","selectable":"true"},{"orderNum":999,"id":"220822","parentId":"220800","name":"通榆县","selectable":"true"},{"orderNum":999,"id":"220881","parentId":"220800","name":"洮南市","selectable":"true"},{"orderNum":999,"id":"220882","parentId":"220800","name":"大安市","selectable":"true"}]},{"orderNum":999,"id":"222400","parentId":"220000","name":"延边朝鲜族自治州","selectable":"true","children":[{"orderNum":999,"id":"222401","parentId":"222400","name":"延吉市","selectable":"true"},{"orderNum":999,"id":"222402","parentId":"222400","name":"图们市","selectable":"true"},{"orderNum":999,"id":"222403","parentId":"222400","name":"敦化市","selectable":"true"},{"orderNum":999,"id":"222404","parentId":"222400","name":"珲春市","selectable":"true"},{"orderNum":999,"id":"222405","parentId":"222400","name":"龙井市","selectable":"true"},{"orderNum":999,"id":"222406","parentId":"222400","name":"和龙市","selectable":"true"},{"orderNum":999,"id":"222424","parentId":"222400","name":"汪清县","selectable":"true"},{"orderNum":999,"id":"222426","parentId":"222400","name":"安图县","selectable":"true"}]}]
         */

        private int orderNum;
        private String id;
        private String parentId;
        private String name;
        private String selectable;
        private List<ChildrenBeanX> children;

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSelectable() {
            return selectable;
        }

        public void setSelectable(String selectable) {
            this.selectable = selectable;
        }

        public List<ChildrenBeanX> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBeanX> children) {
            this.children = children;
        }

        public static class ChildrenBeanX implements Serializable{
            /**
             * orderNum : 999
             * id : 220100
             * parentId : 220000
             * name : 长春市
             * selectable : true
             * children : [{"orderNum":999,"id":"220101","parentId":"220100","name":"市辖区","selectable":"false"},{"orderNum":999,"id":"220102","parentId":"220100","name":"南关区","selectable":"true"},{"orderNum":999,"id":"220103","parentId":"220100","name":"宽城区","selectable":"true"},{"orderNum":999,"id":"220104","parentId":"220100","name":"朝阳区","selectable":"true"},{"orderNum":999,"id":"220105","parentId":"220100","name":"二道区","selectable":"true"},{"orderNum":999,"id":"220106","parentId":"220100","name":"绿园区","selectable":"true"},{"orderNum":999,"id":"220112","parentId":"220100","name":"双阳区","selectable":"true"},{"orderNum":999,"id":"220113","parentId":"220100","name":"九台区","selectable":"true"},{"orderNum":999,"id":"220122","parentId":"220100","name":"农安县","selectable":"true"},{"orderNum":999,"id":"220182","parentId":"220100","name":"榆树市","selectable":"true"},{"orderNum":999,"id":"220183","parentId":"220100","name":"德惠市","selectable":"true"}]
             */

            private int orderNum;
            private String id;
            private String parentId;
            private String name;
            private String selectable;
            private List<ChildrenBean> children;

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSelectable() {
                return selectable;
            }

            public void setSelectable(String selectable) {
                this.selectable = selectable;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean implements Serializable{
                /**
                 * orderNum : 999
                 * id : 220101
                 * parentId : 220100
                 * name : 市辖区
                 * selectable : false
                 */

                private int orderNum;
                private String id;
                private String parentId;
                private String name;
                private String selectable;

                public int getOrderNum() {
                    return orderNum;
                }

                public void setOrderNum(int orderNum) {
                    this.orderNum = orderNum;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getSelectable() {
                    return selectable;
                }

                public void setSelectable(String selectable) {
                    this.selectable = selectable;
                }
            }
        }
    }
}
