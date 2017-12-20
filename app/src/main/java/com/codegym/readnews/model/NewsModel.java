package com.codegym.readnews.model;

import java.util.List;

/**
 * Created by Han on 12/16/2017.
 */

public class NewsModel {

    private List<NewsBean> items;


    public List<NewsBean> getItems() {
        return items;
    }

    public static class NewsBean{
        private String title;
        private String link;
        private String description;

        public NewsBean(String title, String link, String description) {
            this.title = title;
            this.link = link;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }


        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}
