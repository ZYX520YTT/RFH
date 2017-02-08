package rfh.tianli.com.rfh.domain;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/8 on 18:05.
 * 描述：物业服务中新闻信息
 */

public class NewsInfo {

    private Integer newsimage;
    private String news;

    public NewsInfo(Integer newsimage, String news) {
        this.newsimage = newsimage;
        this.news = news;
    }

    public Integer getNewsimage() {
        return newsimage;
    }

    public void setNewsimage(Integer newsimage) {
        this.newsimage = newsimage;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}
