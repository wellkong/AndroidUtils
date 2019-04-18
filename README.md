# AndroidUtils
Android常用工具类
功能简介
完全退出
​写在基类，然后每个Activity都去继承

public class BaseActivity extends AppCompatActivity {
    public ActivityManagerUtil activityManagerUtil;
    public Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mActivity = this;
        activityManagerUtil = ActivityManagerUtil.getInstance();
        activityManagerUtil.pushOneActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束Activity&从栈中移除该Activity
        activityManagerUtil.popOneActivity(this);
    }

}
需要完全退出

activityManagerUtil.appExit();
全局异常捕获
只要在Application 初始化即可

public class AndroidUtilsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //崩溃处理
        CrashHandlerUtil crashHandlerUtil = CrashHandlerUtil.getInstance();
        crashHandlerUtil.init(this);
        crashHandlerUtil.setCrashTip("很抱歉，程序出现异常，即将退出！");
    }
}
TimeUtil
时间戳转北京时间
String unixTimestamp2BeijingTime(Object millisecond, String format)
北京时间转时间戳
long beijingTime2UnixTimestamp(String beijingTime, String format)
BitmapCompressUtil
图片压缩类

ArithUtil
算术类，包括加减乘除

AppUtils
工具类集合，不好单独起名都放在AppUtils里

指定小数输出
 String decimalFormat(double s, String format)
关闭键盘
void hideSoftInput(Activity activity)
Bitmap转Byte
byte[] bitmap2Bytes(Bitmap bitmap)
MD5加密
String md5(String plainText)
安装apk
void installAPK(Context context, String path)
直接拨号，需要增加CALL_PHONE权限
void actionCall(Context context, String phone)
跳到拨号盘-拨打电话
void actionDial(Context context, String phone)
DisplayMetricsUtil
获取屏幕分辨率-宽
int getScreenWidth(Context context)
获取屏幕分辨率-高
int getScreenHeight(Context context)
根据手机的分辨率从 dp 的单位 转成为 px(像素)
int dip2px(Context context, float dpValue)
根据手机的分辨率从 px(像素) 的单位 转成为 dp
int px2dip(Context context, float pxValue)
sp转px
int sp2px(Context context, float spValue)
px转sp
int px2sp(Context context, float pxValue)
AbsolutePathUtil
通过Url获取绝对路径

String getAbsolutePath(final Context context, final Uri uri)
DownloadUtil
步骤1：

AndroidManifest注册
<receiver android:name="com.wuxiaolong.androidutils.library.DownloadUtil$DownloadManagerReceiver">
<intent-filter>
<action android:name="android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED"/>
<action android:name="android.intent.action.DOWNLOAD_COMPLETE"/>
</intent-filter>
</receiver>
步骤2： 调用DownloadManager下载

DownloadUtil downloadUtil = new DownloadUtil(content, downloadUrl);
//下载显示名字，不能是中文
downloadUtil.setDownloadFileName("apkName" + System.currentTimeMillis() + ".apk");
downloadUtil.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
downloadUtil.start();
LogUtil
log打印类

LogUtil.d("");
RegexUtil
手机号码，中间4位星号替换
String phoneNoHide(String phone)
银行卡号，保留最后4位，其他星号替换
String cardIdHide(String cardId)
身份证号，中间10位星号替换
String idHide(String id)
是否为车牌号
 boolean checkVehicleNo(String vehicleNo)
验证Email
boolean checkEmail(String email)
验证身份证号码
boolean checkIdCard(String idCard)
验证手机号码
boolean checkMobile(String mobile)
验证固定电话号码
 boolean checkPhone(String phone)
验证整数（正整数和负整数）
boolean checkDecimals(String decimals)
SharedPreferencesUtil
SharedPreferences工具类

void setString(Context context, final String key,final String value)
TimeUtil
时间工具类

VersionUtil
获取版本号
String getVersionName(Context context)
获取版本code
int getVersionCode(Context context)
MediaUtil
多媒体工具类

打开系统拍照
void startActivityForCamera(Activity activity, int requestCode, Uri outputUri)
void startActivityForCamera(Activity activity, int requestCode)
打开系统相册
void startActivityForGallery(Activity activity, int requestCode)
打开系统裁剪
void startActivityForImageCut(Activity activity, int requestCode,
                                          Uri inputUri, Uri outputUri,
                                          int width, int height)
