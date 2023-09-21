/**
*@version 1.0.0
*@date  2014-6-16
*@author Zhan Yu
*/

package com.tianci.system.define;

public class SkyUartSerialCmdDefs
{
    /**
     * 进入/退出 工厂232调试模式
     */
    public final static byte UART_SERIAL_CMD_FACTORY_232_DEBUG = 0X0D;
    // 进入
    public final static byte UART_SERIAL_CMD_FACTORY_232_DEBUG_ENTER = 0X01;
    // 退出
    public final static byte UART_SERIAL_CMD_FACTORY_232_DEBUG_EXIT = 0X00;  
    
    /**
     * 响应/不响应 电视遥控器
     */
    public final static byte UART_SERIAL_CMD_TV_REMOTE_CONTROL = 0X0E;
    // 不响应
    public final static byte UART_SERIAL_CMD_TV_REMOTE_CONTROL_NORESPONSE = 0X01;
    // 响应
    public final static byte UART_SERIAL_CMD_TV_REMOTE_CONTROL_RESPONSE = 0X00;
    
    /**
     * 响应/不响应 电视键控板
     */
    public final static byte UART_SERIAL_CMD_TV_KEY_PAD = 0X0F;
    //不响应
    public final static byte UART_SERIAL_CMD_TV_KEY_PAD_NORESPONSE = 0X01;
    //响应
    public final static byte UART_SERIAL_CMD_TV_KEY_PAD_RESPONSE = 0X00;
    
    /**
     * 进入/退出 工厂菜单
     */
    public final static byte UART_SERIAL_CMD_FACTORY = 0X10;
    //进入    
    public final static byte UART_SERIAL_CMD_FACTORY_ENTER = 0X01;
    //退出
    public final static byte UART_SERIAL_CMD_FACTORY_EXIT = 0X00;

    /**
     * 进入/退出 老化
     */
    public final static byte UART_SERIAL_CMD_AGING = 0X11;
    //进入    
    public final static byte UART_SERIAL_CMD_AGING_ENTER = 0X01;
    //退出
    public final static byte UART_SERIAL_CMD_AGING_EXIT = 0X00;

    /**
     * 开/关 单键
     */
    public final static byte UART_SERIAL_CMD_SINGLE_KEY = 0X12;
    //开   
    public final static byte UART_SERIAL_CMD_SINGLE_KEY_ON = 0X01;
    //关
    public final static byte UART_SERIAL_CMD_SINGLE_KEY_OFF = 0X00;
    
    /**
     * 开/关 主EEPROM总线
     */
    public final static byte UART_SERIAL_CMD_EEPROM_BUS = 0X13;
    //开   
    public final static byte UART_SERIAL_CMD_EEPROM_BUS_OFF = 0X01;
    //关
    public final static byte UART_SERIAL_CMD_EEPROM_BUS_0X = 0X00;
    
    /**
     * 主软件版本信息查询 <br />
     * 返回5B 04 0A 69 5B 07 15 11 10 19 B1，其中111019表示版本日期11年10月19日，B1为校验位
     */
    public final static byte UART_SERIAL_CMD_MAIN_SOFTWARE_VERSION = 0X14;
   
    /**
     * 调测项恢复设置
     */
    public final static byte UART_SERIAL_CMD_TESTING_RECOVERY = 0X16;
   
    /**
     * 出厂参数设置
     */
    public final static byte UART_SERIAL_CMD_RECOVERY_PARAMS_SETTING = 0X17;
   
    /**
     * 酷开调测设置
     */
    public final static byte UART_SERIAL_CMD_COOCAA_TESTING_SETTING = 0X18;
   
    /**
     * 网络调测设置
     */
    public final static byte UART_SERIAL_CMD_NET_TESTING_SETTING = 0X19;
   
    /**
     * 进入Uplayer/酷开 下播放酷影/酷乐/酷图
     */
    public final static byte UART_SERIAL_CMD_UPLAYER_PLAY = 0X1A;
    //酷影
    public final static byte UART_SERIAL_CMD_UPLAYER_PLAY_MOVIE = 0X00;
    //酷乐
    public final static byte UART_SERIAL_CMD_UPLAYER_PLAY_MUSIC = 0X01;
    //酷图
    public final static byte UART_SERIAL_CMD_UPLAYER_PLAY_PICTURE = 0X02;
    
    /**
     * 显示/取消显示 电子码（Electronic Code Book : ECB）
     */
    public final static byte UART_SERIAL_CMD_ECB = 0X1B;
    // 显示
    public final static byte UART_SERIAL_CMD_ECB_SHOW = 0X01;
    //取消显示
    public final static byte UART_SERIAL_CMD_ECB_HIDE = 0X00;
    
    /**
     * 向上/向下 搜台
     */
    public final static byte UART_SERIAL_CMD_SEARCH_CHANNEL = 0X1C;
    //向上
    public final static byte UART_SERIAL_CMD_SEARCH_CHANNEL_UP = 0X01;
    //向下
    public final static byte UART_SERIAL_CMD_SEARCH_CHANNEL_DOWN = 0X00;
    
    /**
     * 打开/关闭 开机LOGO及开机音乐
     */
    public final static byte UART_SERIAL_CMD_BOOT_LOGO_MUSIC = 0X1D;
    //打开
    public final static byte UART_SERIAL_CMD_BOOT_LOGO_MUSIC_ON = 0X01;
    //关闭
    public final static byte UART_SERIAL_CMD_BOOT_LOGO_MUSIC_OFF = 0X00;
    
    /**
     * 开机/待机
     */
    public final static byte UART_SERIAL_CMD_TV_POWER= 0X1E;
    //开机
    public final static byte UART_SERIAL_CMD_TV_POWER_ON = 0X01;
    //待机
    public final static byte UART_SERIAL_CMD_TV_POWER_OFF = 0X00;
 
    /**
     * 模拟电视节目选择
     */
    public final static byte UART_SERIAL_CMD_ATV_CHANNEL_SELECT= 0X30;
    //选择1
    public final static byte UART_SERIAL_CMD_ATV_CHANNEL_SELECT_00 = 0X01;
    //选择5
    public final static byte UART_SERIAL_CMD_ATV_CHANNEL_SELECT_05 = 0X05;
    
    /**
     * 有线电视节目选择
     */
    public final static byte UART_SERIAL_CMD_DTV_CHANNEL_SELECT= 0X31;
    //选择1
    public final static byte UART_SERIAL_CMD_DTV_CHANNEL_SELECT_00 = 0X01;
    //选择5
    public final static byte UART_SERIAL_CMD_DTV_CHANNEL_SELECT_05 = 0X05;
    //选择7
    public final static byte UART_SERIAL_CMD_DTV_CHANNEL_SELECT_07 = 0X07;
    
    /**
     * 无线电视节目选择
     */
    public final static byte UART_SERIAL_CMD_WTV_CHANNEL_SELECT= 0X32;
    //选择1
    public final static byte UART_SERIAL_CMD_WTV_CHANNEL_SELECT_00 = 0X01;
    //选择5
    public final static byte UART_SERIAL_CMD_WTV_CHANNEL_SELECT_05 = 0X05;
    
    /**
     * AV信号源选择
     */
    public final static byte UART_SERIAL_CMD_AV_SOURCE_SELECT= 0X33;
    //AV1
    public final static byte UART_SERIAL_CMD_AV_SOURCE_SELECT_AV1 = 0X01;
    //AV2
    public final static byte UART_SERIAL_CMD_AV_SOURCE_SELECT_AV2 = 0X02;
    //AV3
    public final static byte UART_SERIAL_CMD_AV_SOURCE_SELECT_AV3 = 0X03;
    
    /**
     * S视频信号源选择
     */
    public final static byte UART_SERIAL_CMD_SAV_SOURCE_SELECT = 0X34;
    //视频1
    public final static byte UART_SERIAL_CMD_SAV_SOURCE_SELECT_AV1 = 0X01;
    //视频2
    public final static byte UART_SERIAL_CMD_SAV_SOURCE_SELECT_AV2 = 0X02;
    
    /**
     * 分量信号源选择
     */
    public final static byte UART_SERIAL_CMD_YUV_SOURCE_SELECT = 0X35;
    //分量1
    public final static byte UART_SERIAL_CMD_YUV_SOURCE_SELECT_YUV1 = 0X01;
    //分量2
    public final static byte UART_SERIAL_CMD_YUV_SOURCE_SELECT_YUV2 = 0X02;
    //分量3
    public final static byte UART_SERIAL_CMD_YUV_SOURCE_SELECT_YUV3 = 0X03;
    
    /**
     * 电脑 & VGA信号源选择
     */
    public final static byte UART_SERIAL_CMD_VGA_SOURCE_SELECT = 0X36;
    
    /**
     * HDMI & DV信号源选择
     */
    public final static byte UART_SERIAL_CMD_HDMI_SOURCE_SELECT = 0X37;
    public final static byte UART_SERIAL_CMD_HDMI_SOURCE_SELECT_HDMI1 = 0X01;
    public final static byte UART_SERIAL_CMD_HDMI_SOURCE_SELECT_HDMI2 = 0X02;
    public final static byte UART_SERIAL_CMD_HDMI_SOURCE_SELECT_HDMI3 = 0X03;
    
    /**
     * 多媒体信号源选择
     */
    public final static byte UART_SERIAL_CMD_UPLAYER_SOURCE_SELECT = 0X38;
    //UPLAYER & USB
    public final static byte UART_SERIAL_CMD_UPLAYER_SOURCE_SELECT_USB = 0X01;
    //酷K
    public final static byte UART_SERIAL_CMD_UPLAYER_SOURCE_SELECT_COOLK = 0X02;
    //酷开
    public final static byte UART_SERIAL_CMD_UPLAYER_SOURCE_SELECT_COOCAA = 0X03;
    
    /**
     * 图像模式
     */
    public final static byte UART_SERIAL_CMD_PICTURE_MODE = 0X40;
    //标准
    public final static byte UART_SERIAL_CMD_PICTURE_MODE_STANDARD = 0X00;
    //亮丽
    public final static byte UART_SERIAL_CMD_PICTURE_MODE_VIVID = 0X01;
    //柔和
    public final static byte UART_SERIAL_CMD_PICTURE_MODE_GENTAL = 0X02;
    //自设
    public final static byte UART_SERIAL_CMD_PICTURE_MODE_USER = 0X03;

    /**
     * 亮度
     */
    public final static byte UART_SERIAL_CMD_BRIGHTNESS = 0X41;
    
    /**
     * 对比度
     */
    public final static byte UART_SERIAL_CMD_CONTRAST = 0X42;
    
    /**
     * 彩色
     */
    public final static byte UART_SERIAL_CMD_COLOR = 0X43;
    
    /**
     * 彩色制式
     */
    public final static byte UART_SERIAL_CMD_COLOR_SYSTEM = 0X44;
    //自动
    public final static byte UART_SERIAL_CMD_COLOR_SYSTEM_AUTO = 0X00;
    //PAL
    public final static byte UART_SERIAL_CMD_COLOR_SYSTEM_PAL = 0X01;
    //NTSC
    public final static byte UART_SERIAL_CMD_COLOR_SYSTEM_NTSC = 0X02;
    
    /**
     * 清晰度
     */
    public final static byte UART_SERIAL_CMD_SHARPNESS = 0X45;
    
    /**
     * 色调
     */
    public final static byte UART_SERIAL_CMD_HUE = 0X46;    
    
    /**
     * 图像降噪
     */
    public final static byte UART_SERIAL_CMD_DNR = 0X47;    
    //关
    public final static byte UART_SERIAL_CMD_DNR_OFF = 0X00;    
    //弱
    public final static byte UART_SERIAL_CMD_DNR_LOW = 0X01;    
    //中
    public final static byte UART_SERIAL_CMD_DNR_MID = 0X02;    
    //强
    public final static byte UART_SERIAL_CMD_DNR_HIGH = 0X03;    
    //自动
    public final static byte UART_SERIAL_CMD_DNR_AUTO = 0X04;    
    
    /**
     * 显示模式
     */
    public final static byte UART_SERIAL_CMD_DISPLAY_MODE = 0X48;
    //16:9
    public final static byte UART_SERIAL_CMD_DISPLAY_MODE_16_9 = 0X01;  
    //4:3
    public final static byte UART_SERIAL_CMD_DISPLAY_MODE_4_3 = 0X02;  
    //全屏
    public final static byte UART_SERIAL_CMD_DISPLAY_MODE_FULLSCREEN = 0X03;  
    //全景
    public final static byte UART_SERIAL_CMD_DISPLAY_MODE_PANORAMA = 0X04;
    //电影
    public final static byte UART_SERIAL_CMD_DISPLAY_MODE_MOVIE = 0X05;  
    //字母
    public final static byte UART_SERIAL_CMD_DISPLAY_MODE_CAPTION = 0X06;
    //人物
    public final static byte UART_SERIAL_CMD_DISPLAY_MODE_PERSON = 0X07;
    //21:9
    public final static byte UART_SERIAL_CMD_DISPLAY_MODE_21_9 = 0X08;
    
    /**
     * 屏变
     */
    public final static byte UART_SERIAL_CMD_DREAM_PANEL = 0X49;
    //关
    public final static byte UART_SERIAL_CMD_DREAM_PANEL_OFF = 0X00;
    //光感
    public final static byte UART_SERIAL_CMD_DREAM_PANEL_LIGHT_SENSOR = 0X01;
    //场景
    public final static byte UART_SERIAL_CMD_DREAM_PANEL_IMAGE_DETECT = 0X02;
    //全屏变
    public final static byte UART_SERIAL_CMD_DREAM_PANEL_MULTI_DETECT = 0X03;
    
    /**
     * RF-AGC调整
     */
    public final static byte UART_SERIAL_CMD_RF_AGC_ADJUST = 0X60;
    
    /**
     * 启动当前信源下ADC自动校正 <br />
     * <li>返回5B 04 0A 69+(成功/失败口令)</li>
     * <li>5b 05 62 01 c3(校正成功)</li>
     * <li>5b 05 62 00 c2（校正失败）</li>
     */
    public final static byte UART_SERIAL_CMD_ADC_AUTO_ADJUST= 0X61;
    
    /**
     * 当前信源通道下色温状态选择
     */
    public final static byte UART_SERIAL_CMD_COLOR_TEMPERATURE = 0X63;
    //标准
    public final static byte UART_SERIAL_CMD_COLOR_TEMPERATURE_STANDARD = 0X00;
    //暖色
    public final static byte UART_SERIAL_CMD_COLOR_TEMPERATURE_WARM = 0X01;
    //冷色
    public final static byte UART_SERIAL_CMD_COLOR_TEMPERATURE_COLD = 0X02;
    
    /**
     * RGB：RGain红色增益寄存器值
     */
    public final static byte UART_SERIAL_CMD_TV_RGB_GAIN_REGISTER_RED = 0X64;
    
    /**
     * RGB：GGain绿色增益寄存器值
     */
    public final static byte UART_SERIAL_CMD_TV_RGB_GAIN_REGISTER_GREEN = 0X65;

    /**
     * RGB：BGain蓝色增益寄存器值
     */
    public final static byte UART_SERIAL_CMD_TV_RGB_GAIN_REGISTER_BLUE = 0X66;
    
    /**
     * RGB：RoffSet红截止偏移量寄存器值
     */
    public final static byte UART_SERIAL_CMD_TV_RGB_OFFSET_REGISTER_RED = 0X67;
    
    /**
     * RGB：GoffSet绿截止偏移量寄存器值
     */
    public final static byte UART_SERIAL_CMD_TV_RGB_OFFSET_REGISTER_GEEEN = 0X68;
    
    /**
     * RGB：BoffSet蓝截止偏移量寄存器值
     */
    public final static byte UART_SERIAL_CMD_TV_RGB_OFFSET_REGISTER_BLUE = 0X69;
    
    /**
     * RGB：将上述6类RGB 白平衡参数值一次保存到存储器当前通道、色温所对应的位置
     */
    public final static byte UART_SERIAL_CMD_TV_RGB_SAVE_TO_REGISTER = 0X6A;

    /**
     * 音量
     */
    public final static byte UART_SERIAL_CMD_VOLUME = 0X70;
    //音量设置0
    public final static byte UART_SERIAL_CMD_VOLUME_0 = 0X00;
    //音量设置100
    public final static byte UART_SERIAL_CMD_VOLUME_100 = 0X64;
    //音量设置50
    public final static byte UART_SERIAL_CMD_VOLUME_50 = 0X32;
    
    /**
     * 左右平衡设置
     */
    public final static byte UART_SERIAL_CMD_LEFT_RIGHT_BALANCE = 0X71;
    //设置0
    public final static byte UART_SERIAL_CMD_LEFT_RIGHT_BALANCE_0 = 0X00;
    //设置50
    public final static byte UART_SERIAL_CMD_LEFT_RIGHT_BALANCE_50 = 0X32;
    
    /**
     * 自动音量控制
     */
    public final static byte UART_SERIAL_CMD_AUTO_VOLUME_CONTROL = 0X72;
    //关
    public final static byte UART_SERIAL_CMD_AUTO_VOLUME_CONTROL_OFF = 0X00;
    //开
    public final static byte UART_SERIAL_CMD_AUTO_VOLUME_CONTROL_ON = 0X01;
    
    /**
     * 声音模式
     */
    public final static byte UART_SERIAL_CMD_SOUND_MODE = 0X73; 
    //标准
    public final static byte UART_SERIAL_CMD_SOUND_MODE_STANDARD = 0X00;
    //音乐
    public final static byte UART_SERIAL_CMD_SOUND_MODE_MUSIC = 0X01;
    //影院
    public final static byte UART_SERIAL_CMD_SOUND_MODE_MOVIE = 0X02;
    //新闻
    public final static byte UART_SERIAL_CMD_SOUND_MODE_NEWS = 0X03;
    //自设
    public final static byte UART_SERIAL_CMD_SOUND_MODE_USER = 0X04;   
    
    /**
     * 声音制式
     */
    public final static byte UART_SERIAL_CMD_SOUND_SYSTEM = 0X74;
    //D/K
    public final static byte UART_SERIAL_CMD_SOUND_SYSTEM_DK = 0X01; 
    //I
    public final static byte UART_SERIAL_CMD_SOUND_SYSTEM_I = 0X02; 
    //M
    public final static byte UART_SERIAL_CMD_SOUND_SYSTEM_M = 0X03;  
    //B G
    public final static byte UART_SERIAL_CMD_SOUND_SYSTEM_BG = 0X04; 
    
    /**
     * 低音/重低音设置
     */
    public final static byte UART_SERIAL_CMD_SUBWOOFER = 0X75;
    
    /**
     * 高音设置
     */
    public final static byte UART_SERIAL_CMD_TREBLE = 0X76;
    
    
    /**
     * 静音设置
     */
    public final static byte UART_SERIAL_CMD_MUTE = 0X77;
    //静音
    public final static byte UART_SERIAL_CMD_MUTE_ON = 0X00;
    //非静音
    public final static byte UART_SERIAL_CMD_MUTE_OFF = 0X01;
    
    /**
     * 重低音控制
     */
    public final static byte UART_SERIAL_CMD_SUBWOOFER_SWITCH = 0X78;
    //关
    public final static byte UART_SERIAL_CMD_SUBWOOFER_SWITCH_OFF = 0X00;
    //开
    public final static byte UART_SERIAL_CMD_SUBWOOFER_SWITCH_ON = 0X01;
    
    /**
     * SRS环绕声控制
     */
    public final static byte UART_SERIAL_CMD_SRS = 0X79;
    //关
    public final static byte UART_SERIAL_CMD_SRS_OFF = 0X00;
    //开
    public final static byte UART_SERIAL_CMD_SRS_ON = 0X01;
    
    /**
     * 单独听控制
     */
    public final static byte UART_SERIAL_CMD_AUDIO_ONLY = 0X7A;
    //关
    public final static byte UART_SERIAL_CMD_AUDIO_ONLY_OFF = 0X00;
    //开
    public final static byte UART_SERIAL_CMD_AUDIO_ONLY_ON = 0X01;
    
    /**
     * 方向键控制
     */
    public final static byte UART_SERIAL_CMD_KEY_DPAD_CONTROL_DIRECTION = 0X2A;
    //上
    public final static byte UART_SERIAL_CMD_KEY_DPAD_CONTROL_DIRECTION_UP = 0X00;
    //下
    public final static byte UART_SERIAL_CMD_KEY_DPAD_CONTROL_DIRECTION_DOWN = 0X01;
    //左
    public final static byte UART_SERIAL_CMD_KEY_DPAD_CONTROL_DIRECTION_LEFT = 0X02;
    //右
    public final static byte UART_SERIAL_CMD_KEY_DPAD_CONTROL_DIRECTION_RIGHT = 0X03;
    
    /**
     * 菜单键控制
     */
    public final static byte UART_SERIAL_CMD_KEY_DPAD_CONTROL_MENU = 0X2D;
    
    /**
     * 返回键控制
     */
    public final static byte UART_SERIAL_CMD_KEY_DPAD_CONTROL_BACK = 0X2E;
    
    /**
     * 确定键控制
     */
    public final static byte UART_SERIAL_CMD_KEY_DPAD_CONTROL_CENTER = 0X2B;
    
    /**
     * 主页键控制
     */
    public final static byte UART_SERIAL_CMD_KEY_DPAD_CONTROL_HOME = 0X2C;
    
    /**
     * 3D
     */
    public final static byte UART_SERIAL_CMD_3D = 0X2F;
    //左右
    public final static byte UART_SERIAL_CMD_3D_LEFT_RIGHT = 0X01;
    //上下
    public final static byte UART_SERIAL_CMD_3D_TOP_BOTTOM = 0X02;
    //关
    public final static byte UART_SERIAL_CMD_3D_OFF = 0X00;
    
    /**
     * IP地址查询 <br />
     * IP 地址信息查询 ：根据实际 IP 返回，如 IP地址为:192.168.111.112。 <br />
                返回“5B 10 27 19 21 68 11 11 12 57” 同主版软件信息返回
     */
    public final static byte UART_SERIAL_CMD_IP_INFO = 0X26;
}    



