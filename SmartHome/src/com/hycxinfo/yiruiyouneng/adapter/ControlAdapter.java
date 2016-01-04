package com.hycxinfo.yiruiyouneng.adapter;

import java.util.List;

import com.hycxinfo.yiruiyouneng.R;
import com.hycxinfo.yiruiyouneng.model.DeviceEntity;
import com.hycxinfo.yiruiyouneng.utils.DensityUtil;
import com.hycxinfo.yiruiyouneng.utils.ToosUtils;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author Mu
 * @date 2015-10-18下午1:29:49
 * @description 控制适配器
 */
public class ControlAdapter extends BaseAdapter {

    private Context context;
    private List<DeviceEntity> entities;
    private int width;

    public ControlAdapter(Context context, List<DeviceEntity> entities,
                          int width) {
        super();
        this.context = context;
        this.entities = entities;
        this.width = width;
    }

    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public Object getItem(int position) {
        return entities.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.activity_control_item,
                    null);
            holder = new ViewHolder();
            holder.root = convertView.findViewById(R.id.control_item_root);
            holder.checkBox = (CheckBox) convertView
                    .findViewById(R.id.control_item_check);
            holder.imageView = (ImageView) convertView
                    .findViewById(R.id.control_item_image);
            holder.dis= (ImageView) convertView.findViewById(R.id.control_item_dis);
            holder.name = (TextView) convertView
                    .findViewById(R.id.control_item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        LayoutParams params = (LayoutParams) holder.root.getLayoutParams();
        params.width = (width - DensityUtil.dip2px(context, 52)) / 3;
        params.height = (width - DensityUtil.dip2px(context, 52)) / 3;
        holder.root.setLayoutParams(params);

        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) holder.imageView.getLayoutParams();
        params2.width = (int)(((width - DensityUtil.dip2px(context, 52)) / 3)*0.46);
        params2.height =(int)(((width - DensityUtil.dip2px(context, 52)) / 3)*0.46);
        holder.imageView.setLayoutParams(params2);

        RelativeLayout.LayoutParams params3= (RelativeLayout.LayoutParams) holder.dis.getLayoutParams();
        params3.width = (int)(((width - DensityUtil.dip2px(context, 52)) / 3)*0.2);
        params3.height =(int)(((width - DensityUtil.dip2px(context, 52)) / 3)*0.2);
        holder.dis.setLayoutParams(params3);





        if (entities.get(position).disable){
            holder.dis.setVisibility(View.GONE);
            holder.root.setClickable(false);
        }else{
            holder.dis.setVisibility(View.VISIBLE);
            holder.root.setClickable(true);
        }


        holder.checkBox.setChecked(entities.get(position).selected);
        holder.name.setText(ToosUtils.getName(entities.get(position)));
        holder.imageView.setImageResource(ToosUtils.getDrawable(
                entities.get(position).type, entities.get(position).running, entities.get(position).disable));
        return convertView;
    }

    class ViewHolder {
        public View root;
        public CheckBox checkBox;
        public ImageView imageView;
        public ImageView dis;
        public TextView name;
    }

}
