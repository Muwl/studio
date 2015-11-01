package com.hycxinfo.yiruiyouneng.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hycxinfo.yiruiyouneng.R;
import com.hycxinfo.yiruiyouneng.adapter.InductorAdapter;
import com.hycxinfo.yiruiyouneng.model.DeviceEntity;
import com.hycxinfo.yiruiyouneng.model.InductorEntity;
import com.hycxinfo.yiruiyouneng.model.RoomEntity;
import com.hycxinfo.yiruiyouneng.utils.LogManager;
import com.hycxinfo.yiruiyouneng.utils.ShareDataTool;
import com.hycxinfo.yiruiyouneng.utils.ToosUtils;

/**
 * @author Mu
 * @date 2015-10-16下午9:19:21
 * @description 体感应器页面
 */
public class InductorActivity extends BaseActivity {

	private ImageView back;

	private TextView title;

	private ListView listView;

	private InductorAdapter adapter;

	private List<InductorEntity> inductorEntities;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inductor);
		initDate();
		initView();
	}

	private void initDate() {
		List<RoomEntity> roomEntities = ShareDataTool.getRooms(this);
		List<DeviceEntity> deviceEntities = ShareDataTool.getDevice(this);
		if (roomEntities == null) {
			roomEntities = new ArrayList<RoomEntity>();
		}
		if (deviceEntities == null) {
			deviceEntities = new ArrayList<DeviceEntity>();
		}

		inductorEntities = new ArrayList<InductorEntity>();

		for (int i = 0; i < roomEntities.size(); i++) {
			InductorEntity entity = new InductorEntity();
			entity.entity = roomEntities.get(i);
			List<DeviceEntity> list = new ArrayList<DeviceEntity>();
			for (int j = 0; j < deviceEntities.size(); j++) {
				if (roomEntities.get(i).roomId
						.equals(deviceEntities.get(j).roomId)
						&& "03".equals(deviceEntities.get(j).type)) {
					list.add(deviceEntities.get(j));
				}
			}
			entity.deviceEntities = list;
			inductorEntities.add(entity);
		}
		InductorEntity entity = new InductorEntity();
		entity.entity = new RoomEntity("其他", String.valueOf(System
				.currentTimeMillis() / 1000));
		List<DeviceEntity> list = new ArrayList<DeviceEntity>();
		for (int i = 0; i < deviceEntities.size(); i++) {
			if (ToosUtils.isStringEmpty(deviceEntities.get(i).roomId) && "03".equals(deviceEntities.get(i).type)) {
				list.add(deviceEntities.get(i));
			}
		}
		entity.deviceEntities = list;
		if (list.size() != 0) {
			inductorEntities.add(entity);
		}
		List<DeviceEntity> list3 = new ArrayList<DeviceEntity>();
		if (roomEntities.size() == 0 && deviceEntities.size() != 0) {
			inductorEntities.clear();
			InductorEntity entity1 = new InductorEntity();
			entity1.entity = new RoomEntity("全部", String.valueOf(System
					.currentTimeMillis() / 1000));
			for (int i = 0; i < deviceEntities.size(); i++) {
				if ("03".equals(deviceEntities.get(i).type)) {
					list3.add(deviceEntities.get(i));
				}
			}
			entity1.deviceEntities = list3;
			inductorEntities.add(entity1);
		}

		List<InductorEntity> removeEntities = new ArrayList<InductorEntity>();
		for (int i=0;i<inductorEntities.size();i++){
			if (inductorEntities.get(i).deviceEntities==null|| inductorEntities.get(i).deviceEntities.size()==0){
				removeEntities.add(inductorEntities.get(i));
			}
		}
		inductorEntities.removeAll(removeEntities);




	}

	public void refush(){
		List<DeviceEntity> reEntities = ShareDataTool.getDevice(this);
		if (reEntities == null) {
			reEntities = new ArrayList<DeviceEntity>();
		}
		for (int i=0;i<inductorEntities.size();i++){
			for (int j=0;j<inductorEntities.get(i).deviceEntities.size();j++){
				for (int m=0;m<reEntities.size();m++){
					if (inductorEntities.get(i).deviceEntities.get(j).longAddress.equals(reEntities.get(m).longAddress)){
						inductorEntities.get(i).deviceEntities.get(j).type=reEntities.get(m).type;
						inductorEntities.get(i).deviceEntities.get(j).running=reEntities.get(m).running;
						inductorEntities.get(i).deviceEntities.get(j).waitting=reEntities.get(m).waitting;
					}
				}
			}
		}
		adapter.notifyDataSetChanged();
		LogManager.LogShow("-----", "InductorActivity=====================", LogManager.ERROR);



	}

	private void initView() {
		back = (ImageView) findViewById(R.id.title_back);
		title = (TextView) findViewById(R.id.title_title);
		listView = (ListView) findViewById(R.id.inductor_listview);

		title.setText("人体感应器");
		back.setVisibility(View.VISIBLE);
		adapter = new InductorAdapter(this, inductorEntities);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		listView.setAdapter(adapter);
	}

}
