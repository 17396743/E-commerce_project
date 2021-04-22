package com.sprout.mode.data;

/**
 * @创建时间 2021/4/10 16:42
 */
public class DetailBean {

    private int errno;
    private String errmsg;
    private Data data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private int id;
        private String title;
        private String content;
        private String avatar;
        private String item_pic_url;
        private String subtitle;
        private int topic_category_id;
        private int price_info;
        private String read_count;
        private String scene_pic_url;
        private int topic_template_id;
        private int topic_tag_id;
        private int sort_order;
        private int is_show;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getItem_pic_url() {
            return item_pic_url;
        }

        public void setItem_pic_url(String item_pic_url) {
            this.item_pic_url = item_pic_url;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public int getTopic_category_id() {
            return topic_category_id;
        }

        public void setTopic_category_id(int topic_category_id) {
            this.topic_category_id = topic_category_id;
        }

        public int getPrice_info() {
            return price_info;
        }

        public void setPrice_info(int price_info) {
            this.price_info = price_info;
        }

        public String getRead_count() {
            return read_count;
        }

        public void setRead_count(String read_count) {
            this.read_count = read_count;
        }

        public String getScene_pic_url() {
            return scene_pic_url;
        }

        public void setScene_pic_url(String scene_pic_url) {
            this.scene_pic_url = scene_pic_url;
        }

        public int getTopic_template_id() {
            return topic_template_id;
        }

        public void setTopic_template_id(int topic_template_id) {
            this.topic_template_id = topic_template_id;
        }

        public int getTopic_tag_id() {
            return topic_tag_id;
        }

        public void setTopic_tag_id(int topic_tag_id) {
            this.topic_tag_id = topic_tag_id;
        }

        public int getSort_order() {
            return sort_order;
        }

        public void setSort_order(int sort_order) {
            this.sort_order = sort_order;
        }

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }
    }
}
