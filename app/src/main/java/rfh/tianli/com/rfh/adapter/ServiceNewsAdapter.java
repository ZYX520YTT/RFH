package rfh.tianli.com.rfh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import rfh.tianli.com.rfh.R;
import rfh.tianli.com.rfh.domain.NewsInfo;

/**
 * 作者：张宇翔
 * 创建日期： by 2017/2/8 on 17:45.
 * 描述：物业服务里面的新闻的适配器
 */

public class ServiceNewsAdapter extends BaseAdapter {


    private Context context;

    private List<NewsInfo> newsInfos;


    public ServiceNewsAdapter(Context context,List<NewsInfo> newsInfos){
        this.context=context;
        this.newsInfos=newsInfos;
    }


    @Override
    public int getCount() {
        return newsInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return newsInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.item_news,null);
            holder=new ViewHolder();
            holder.iv_news= (ImageView) convertView.findViewById(R.id.iv_news);
            holder.tv_news= (TextView) convertView.findViewById(R.id.tv_news);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        NewsInfo newsInfo=newsInfos.get(position);
        Picasso.with(context)
                .load(newsInfo.getNewsimage())
                .placeholder(R.drawable.background_zhuce)
                .error(R.drawable.background_zhuce)
                .into(holder.iv_news);

        holder.tv_news.setText(newsInfo.getNews());

        return convertView;
    }

    static class ViewHolder{
        public ImageView iv_news;
        public TextView tv_news;
    }
}
