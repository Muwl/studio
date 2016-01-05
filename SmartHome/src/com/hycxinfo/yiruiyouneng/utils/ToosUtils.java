package com.hycxinfo.yiruiyouneng.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.widget.TextView;

import com.hycxinfo.yiruiyouneng.R;
import com.hycxinfo.yiruiyouneng.model.DeviceEntity;
import com.hycxinfo.yiruiyouneng.model.RoomEntity;

/**
 * @author Mu
 * @date 2014-11-4
 * @description 常用工具�??
 */
public class ToosUtils {

	/**
	 * 判断字符串是否为�??
	 * 
	 * @param msg
	 * @return 为空 true,不为�?? false
	 */
	public static boolean isStringEmpty(String msg) {
		if (null == msg || "".equals(msg)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取TextView的内�??
	 * 
	 * @param textView
	 * @return textView 的内�??
	 */
	public static String getTextContent(TextView textView) {
		return textView.getText().toString().trim();
	}

	/**
	 * 判断TextView是否为空
	 * 
	 * @param textView
	 * @return 为空 true,不为�?? false
	 */
	public static boolean isTextEmpty(TextView textView) {
		return isStringEmpty(getTextContent(textView));
	}

	/**
	 * �??查密码是否小�??6�??
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkPwd(String str) {
		if (str.length() < 6) {
			return false;
		} else {
			return true;
		}
	}

	public static int getDrawable(String type, boolean run,boolean disable) {
		switch (type) {
		case "01":
			// 插座
			if (disable){
				return R.drawable.device_socket_normal;
			}
			if (run) {
				return R.drawable.device_socket_press;
			} else {
				return R.drawable.device_socket_normal;
			}

		case "02":
			// 空调
			if (disable){
				return R.drawable.device_airconditioning_normal;
			}
			if (run) {
				return R.drawable.device_airconditioning_press;
			} else {
				return R.drawable.device_airconditioning_normal;
			}
		case "03":
			// 红外
			if (disable){
				return R.drawable.sensor_off;
			}
			if (run) {
				return R.drawable.sensor_on;
			} else {
				return R.drawable.sensor_off;
			}
		case "04":
			// 复合开关
			if (disable){
				return R.drawable.device_lightgroup_normal;
			}
			if (run) {
				return R.drawable.device_lightgroup_press;
			} else {
				return R.drawable.device_lightgroup_normal;
			}
		case "05":
			// 双联开关
			if (disable){
				return R.drawable.device_lightgroup_normal;
			}
			if (run) {
				return R.drawable.device_lightgroup_press;
			} else {
				return R.drawable.device_lightgroup_normal;
			}
		case "06":
			// 单联开关
			if (disable){
				return R.drawable.device_bulb_normal;
			}
			if (run) {
				return R.drawable.device_bulb_press;
			} else {
				return R.drawable.device_bulb_normal;
			}

		default:
			return R.drawable.ic_launcher;
		}
	}

	public static String getRoonName(String roomId, List<RoomEntity> entities) {
		if (entities == null) {
			return "";
		}
		if (ToosUtils.isStringEmpty(roomId)) {
			return "";
		}
		for (int i = 0; i < entities.size(); i++) {
			if (roomId.equals(entities.get(i).roomId)) {
				return entities.get(i).name;
			}
		}

		return "其他";

	}

	public static String  getName(DeviceEntity entity){
		if(!ToosUtils.isStringEmpty(entity.name)){
			return  entity.name;
		}else{
			return  getType(entity.type);
		}
	}

	public static String getType(String type) {
		switch (type) {
		case "01":
			// 插座
			return "插座";
		case "02":
			// 空调
			return "空调";
		case "03":
			// 红外
			return "红外";
		case "04":
			// 复合开关
			return "复合开关";
		case "05":
			// 双联开关
			return "双联开关";
		case "06":
			// 单联开关
			return "单联开关";

		default:
			return "未知";
		}
	}

	/**
	 * 判断字符串是否为手机�??
	 * 
	 * @param msg
	 * @return true为手机号 ,false不为手机�??
	 */
	public static boolean MatchPhone(String name) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(name);
		return m.matches();
	}

	/**
	 * bitmap转换成file
	 * 
	 * @param filePath
	 * @param bitmap
	 * @return
	 */
	public static File saveImage2SD(String filePath, Bitmap bitmap) {
		try {
			File saveFile = null;

			if (bitmap != null) {
				saveFile = createFile(filePath);
			}
			FileOutputStream fos = new FileOutputStream(filePath);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(CompressFormat.JPEG, 100, stream);
			byte[] bytes = stream.toByteArray();
			fos.write(bytes);
			fos.close();
			return saveFile;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 判断是否为有效的链接
	 * 
	 * @param strLink
	 * @return
	 */
	public static boolean isURL(String str) {
		// 转换为小�??
		str = str.toLowerCase();
		String regex = "^((https|http|ftp|rtsp|mms)?://)"
				+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
				+ "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
				+ "|" // 允许IP和DOMAIN（域名）
				+ "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
				+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
				+ "[a-z]{2,6})" // first level domain- .com or .museum
				+ "(:[0-9]{1,4})?" // 端口- :80
				+ "((/?)|" // a slash isn't required if there is no file name
				+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();

	}

	public static boolean hasSdcard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断当前应用程序处于前台还是后台
	 */
	public static boolean isApplicationBroughtToBackground(final Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return false;
			}
		}
		return true;
	}

	public static File createFile(String path) {
		File file = new File(path);
		File dir = new File(file.getParent());
		if (!dir.exists()) {
			try {
				// 按照指定的路径创建文件夹
				dir.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		if (!file.exists()) {
			try {
				// 在指定的文件夹中创建文件
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return file;

	}

}
