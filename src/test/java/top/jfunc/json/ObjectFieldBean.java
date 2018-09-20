package top.jfunc.json;

import top.jfunc.json.annotation.JsonField;

public class ObjectFieldBean {
        @JsonField(serialize = false)
        private String k1;
        private String k2;
        @JsonField("kk")
        private String k3;
        private String k4;
        private String k5;
        public String getK1() {
            return k1;
        }

        public void setK1(String k1) {
            this.k1 = k1;
        }

        public String getK2() {
            return k2;
        }

        public void setK2(String k2) {
            this.k2 = k2;
        }

        public String getK3() {
            return k3;
        }

        public void setK3(String k3) {
            this.k3 = k3;
        }

        public String getK4() {
            return k4;
        }

        public void setK4(String k4) {
            this.k4 = k4;
        }

        public String getK5() {
            return k5;
        }

        public void setK5(String k5) {
            this.k5 = k5;
        }

}