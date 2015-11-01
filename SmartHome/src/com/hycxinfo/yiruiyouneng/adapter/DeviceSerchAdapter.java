package com.hycxinfo.yiruiyouneng.adapter;

import java.util.List;

import com.hycxinfo.yiruiyouneng.R;
import com.hycxinfo.yiruiyouneng.model.DeviceEntity;
import com.hycxinfo.yiruiyouneng.model.RoomEntity;
import com.hycxinfo.yiruiyouneng.utils.Constant;
import com.hycxinfo.yiruiyouneng.utils.ToosUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Mu
 * @date 2015-10-17下午4:29:26
 * @description 设备搜索适配器
 */
public class DeviceSerchAdapter extends BaseAdapter {

    private Context context;
    private List<DeviceEntity> entities;
    private List<RoomEntity> roomEntities;

    public DeviceSerchAdapter(Context context, List<DeviceEntity> entities,
                              List<RoomEntity> roomEntities) {
        super();
        this.context = context;
        this.entities = entities;
        this.roomEntities = roomEntities;

    }

    @Override
    public int getCount() {
        if (entities.size() == 0) {
            return entities.size();
        } else {
            return (entities.size() + 1);
        }

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position < entities.size()) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(context,
                        R.layout.activity_diviceserch_item, null);
                holder.image = (ImageView) convertView
                        .findViewById(R.id.diviceserch_item_image);
                holder.name = (TextView) convertView
                        .findViewById(R.id.diciceserch_item_name);
                holder.locate = (TextView) convertView
                        .findViewById(R.id.diciceserch_item_locate);
                holder.address = (TextView) convertView
                        .findViewById(R.id.diciceserch_item_address);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText(ToosUtils.getName(entities.get(position)));
            if (ToosUtils.isStringEmpty(entities.get(position).location)) {
                holder.address.setText("");
            } else {
                holder.address.setText(entities.get(position).location);
            }
            if (ToosUtils.isStringEmpty(entities.get(position).roomId)
                    || roomEntities == null) {
                holder.locate.setText(entities.get(position).longAddress
                        .substring(8) + "");
            } else {
                for (int i = 0; i < roomEntities.size(); i++) {
                    if (entities.get(position).roomId
                            .equals(roomEntities.get(i).roomId)) {
                        holder.locate
                                .setText(entities.get(position).longAddress
                                        .substring(8)
                                        + "\u3000"
                                        + roomEntities.get(i).name);
                    }
                }
            }

            holder.image.setImageResource(ToosUtils.getDrawable(
                    entities.get(position).type, entities.get(position).running));
            return convertView;
        } else {
            convertView = View.inflate(context, R.layout.activity_diviceserch_item_bom, null);
            TextView bomText = (TextView) convertView.findViewById(R.id.diviceserch_item_bom_text);
            int outletNum = 0;
            int airconditioningNum = 0;
            int infraredNum = 0;
            int combinationswitchNum = 0;
            int gangedswitchNum = 0;
            int singleswitchNum = 0;
            int noneNum = 0;

            for (int i = 0; i < entities.size(); i++) {
                if (Constant.TYPE_OUTLET.equals(entities.get(i).type)) {
                    outletNum++;
                } else if (Constant.TYPE_AIRCONDITIONING.equals(entities.get(i).type)) {
                    airconditioningNum++;
                } else if (Constant.TYPE_INFRARED.equals(entities.get(i).type)) {
                    infraredNum++;
                } else if (Constant.TYPE_COMBINATIONSWITCH.equals(entities.get(i).type)) {
                    combinationswitchNum++;
                } else if (Constant.TYPE_GANGEDSWITCH.equals(entities.get(i).type)) {
                    gangedswitchNum++;
                } else if (Constant.TYPE_SINGLESWITCH.equals(entities.get(i).type)) {
                    singleswitchNum++;
                } else if (Constant.TYPE_NONE.equals(entities.get(i).type)) {
                    noneNum++;
                }
            }

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("设备总数:" + entities.size() + "\u2000");
            if (outletNum != 0) {
                stringBuffer.append("插座:" + outletNum + "\u2000");
            }
            if (airconditioningNum != 0) {
                stringBuffer.append("空调:" + airconditioningNum + "\u2000");
            }
            if (infraredNum != 0) {
                stringBuffer.append("红外:" + infraredNum + "\u2000");
            }
            if (combinationswitchNum != 0) {
                stringBuffer.append("复合开关:" + combinationswitchNum + "\u2000");
            }
            if (gangedswitchNum != 0) {
                stringBuffer.append("双联开关:" + gangedswitchNum + "\u2000");
            }
            if (singleswitchNum != 0) {
                stringBuffer.append("单联开关:" + singleswitchNum + "\u2000");
            }
            if (noneNum != 0) {
                stringBuffer.append("未知:" + noneNum + "\u2000");
            }
            bomText.setText(stringBuffer);
            return convertView;


        }
    }

    class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView locate;
        public TextView address;
    }

}
