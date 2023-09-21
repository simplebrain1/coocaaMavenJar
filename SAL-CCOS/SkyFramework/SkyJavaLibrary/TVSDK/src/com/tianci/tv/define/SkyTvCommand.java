package com.tianci.tv.define;

public class SkyTvCommand
{
    // SkyTVProvider ==========================================
    public static final String TVP_AUTHORITY = "com.skyworth.tv.provider";

    public static final String TVP_PATH_METHOD = "content://" + TVP_AUTHORITY + "/method";
    // Common
    public static final String TVP_URI_PATH_COMMON_CARD_INFO = "content://" + TVP_AUTHORITY + "/common/card_info";
    public static final String TVP_URI_PATH_COMMON_CAN_SET_WINDOW_SIZE = "content://" + TVP_AUTHORITY + "/common/can_set_window_size";
    public static final String TVP_URI_PATH_COMMON_ANTENNA_VOLTAGE = "content://" + TVP_AUTHORITY + "/common/antenna_voltage";
    public static final String TVP_URI_PATH_SOURCE_TYPE_PURPOSE = "content://" + TVP_AUTHORITY + "/common/source_type_purpose";
    public static final String TVP_URI_PATH_SOURCE_TYPE_DISPLAY_MODE_LIST = "content://" + TVP_AUTHORITY + "/common/display_mode_list";
    public static final String TVP_URI_PATH_COMMON_DISPLAY_MODE = "content://" + TVP_AUTHORITY + "/common/display_mode";
    public static final String TVP_URI_PATH_COMMON_STANDBY_REASON = "content://" + TVP_AUTHORITY + "/common/standby_reason";

//    public static final String TVP_URI_PATH_HDMI_FREESYNC_STATUS = "content://" + TVP_AUTHORITY + "/hdmi/freesync_status";
//
    public static final String TVP_URI_PATH_HDMI_FREESYNC_SUPPORTED = "content://" + TVP_AUTHORITY + "/hdmi/is_freesync_supported";
    public static final String TVP_URI_PATH_HDMI_FREESYNC_STATUS = "content://" + TVP_AUTHORITY + "/hdmi/freesync_status";

    public static final String TVP_URI_PATH_SYSTEM_SOURCE_LIST = "content://" + TVP_AUTHORITY + "/system/source_list";
    public static final String TVP_URI_PATH_SYSTEM_CURRENT_SOURCE = "content://" + TVP_AUTHORITY + "/system/current_source";
    public static final String TVP_URI_PATH_SYSTEM_REAL_SOURCE = "content://" + TVP_AUTHORITY + "/system/real_source";
    public static final String TVP_URI_PATH_SYSTEM_IS_OFFLINE_CHECK_SUPPORTED = "content://" + TVP_AUTHORITY + "/system/is_offline_check_supported";
    public static final String TVP_URI_PATH_SYSTEM_STANDBY_REASON = "content://" + TVP_AUTHORITY + "/system/standby_reason";
    public static final String TVP_URI_PATH_SYSTEM_SIGNAL_STATE = "content://" + TVP_AUTHORITY + "/system/signal_state";
    public static final String TVP_URI_PATH_SYSTEM_IS_RELEASED = "content://" + TVP_AUTHORITY + "/system/is_released";
    public static final String TVP_URI_PATH_SETTING_BOOT_SOURCE = "content://" + TVP_AUTHORITY + "/setting/boot_source";
    public static final String TVP_URI_PATH_SETTING_SOURCE_LIST = "content://" + TVP_AUTHORITY + "/setting/source_list";
    public static final String TVP_URI_SETTING_SOURCE_RENAME = "content://" + TVP_AUTHORITY + "/setting/source_rename";
    public static final String TVP_URI_PATH_SETTING_GET_CEC_DEVICE_INFO = "content://" + TVP_AUTHORITY + "/hdmi/cec_device_info";


    // HDMI
    public static final String TVP_URI_PATH_HDMI_EDID_SUPPORTED = "content://" + TVP_AUTHORITY + "/hdmi/is_edid_supported";
    public static final String TVP_URI_PATH_HDMI_EDID_LIST = "content://" + TVP_AUTHORITY + "/hdmi/edid_list";
    public static final String TVP_URI_PATH_HDMI_EDID = "content://" + TVP_AUTHORITY + "/hdmi/edid";


    // Common
    public static final String[] TVP_ATTRIBUTE_CARD_INFO = {"cardType", "cardStatus", "cardNumber"};
    public static final String[] TVP_ATTRIBUTE_CAN_SET_WINDOW_SIZE = {"canSetWindowSize"};
    public static final String[] TVP_ATTRIBUTE_ANTENNA_VOLTAGE = {"antennaVoltage"};
    public static final String[] TVP_ATTRIBUTE_SOURCE_TYPE_SOURCE_PURPOSE = {"sourceName", "sourceIndex", "displayName","sourceId","sourceTypePurpose"};
    public static final String[] TVP_ATTRIBUTE_SOURCE_TYPE_PURPOSE = {"sourceTypePurpose"};
    public static final String[] TVP_ATTRIBUTE_DISPLAY_MODE = {"displayMode"};

    public static final String[] TVP_ATTRIBUTE_FREESYNC_SUPPORTED = {"isFreeSyncSupported"};
    public static final String[] TVP_ATTRIBUTE_FREESYNC_STATUS = {"freeSyncStatus"};

    public static final String[] TVP_ATTRIBUTE_STANDBY_REASON = {"reason"};

    // HDMI
    public static final String[] TVP_ATTRIBUTE_EDID_SUPPORTED = {"isEdidSupported"};
    public static final String[] TVP_ATTRIBUTE_EDID = {"edid"};

    // SkyTVUI ================================================
    public static final String AUTHORITY = "com.skyworth.tv.sdk.provider";

    public static final String URI_PATH_METHOD = "content://" + AUTHORITY + "/method";
    public static final String URI_PATH_SYSTEM_API_LEVEL = "content://" + AUTHORITY + "/system/api_level";
    public static final String URI_PATH_SYSTEM_CURRENT_SOURCE = "content://" + AUTHORITY + "/system/current_source";
    public static final String URI_PATH_SYSTEM_REAL_SOURCE = "content://" + AUTHORITY + "/system/real_source";
    public static final String URI_PATH_SYSTEM_IS_READY = "content://" + AUTHORITY + "/system/is_ready";
    public static final String URI_PATH_SYSTEM_IS_RELEASED = "content://" + AUTHORITY + "/system/is_released";
    public static final String URI_PATH_SYSTEM_CAN_SCREEN_CAPTURE = "content://" + AUTHORITY + "/system/can_screen_capture";
    public static final String URI_PATH_SYSTEM_SHOW_BOOT_GUIDE = "content://" + AUTHORITY + "/system/show_boot_guide";
    public static final String URI_PATH_SYSTEM_HAS_OSD = "content://" + AUTHORITY + "/system/has_osd";
    public static final String URI_PATH_SYSTEM_SIGNAL_STATE = "content://" + AUTHORITY + "/system/signal_state";
    public static final String URI_PATH_SYSTEM_INTERACTION_ENABLE = "content://" + AUTHORITY + "/system/interaction_enable";
    public static final String URI_PATH_SYSTEM_CAN_INTERACTION = "content://" + AUTHORITY + "/system/can_interaction";
    public static final String URI_PATH_SYSTEM_IS_SEARCHING = "content://" + AUTHORITY + "/system/is_searching";
    public static final String URI_PATH_SYSTEM_STANDBY_REASON = "content://" + AUTHORITY + "/system/standby_reason";
    public static final String URI_PATH_SYSTEM_CHANNEL_SERACH_EDIT = "content://" + AUTHORITY + "/system/channel_search_edit";
    public static final String URI_PATH_SYSTEM_SOURCE_LIST = "content://" + AUTHORITY + "/system/source_list";
    public static final String URI_PATH_SYSTEM_RESOLUTION = "content://" + AUTHORITY + "/system/resolution";
    public static final String URI_PATH_SETTING_BOOT_SOURCE = "content://" + AUTHORITY + "/setting/boot_source";
    public static final String URI_PATH_SETTING_SOURCE_LIST = "content://" + AUTHORITY + "/setting/source_list";
    public static final String URI_PATH_SETTING_BOOT_CHANNEL = "content://" + AUTHORITY + "/setting/boot_channel";
    public static final String URI_PATH_SETTING_CHANNEL_LIST = "content://" + AUTHORITY + "/setting/channel_list";
    public static final String URI_PATH_SETTING_AUTO_SWITCH_SOURCE = "content://" + AUTHORITY + "/setting/auto_switch_source";
    public static final String URI_PATH_SYSTEM_EXTERNAL_SOURCE_LIST = "content://" + AUTHORITY + "/system/external_source_list";
    public static final String URI_PATH_SYSTEM_IS_ENABLE_EXTERNAL_SOURCE = "content://" + AUTHORITY + "/system/is_enable_external_source";
    public static final String URI_PATH_SYSTEM_IS_OFFLINE_CHECK_SUPPORTED = "content://" + AUTHORITY + "/system/is_offline_check_supported";
    public static final String URI_PATH_SYSTEM_CA_OPERATOR_LIST = "content://" + AUTHORITY + "/system/ca_operator_list";
    public static final String URI_PATH_SYSTEM_CA_ENTITLE_LIST = "content://" + AUTHORITY + "/system/ca_entitle_list";
    public static final String URI_PATH_SYSTEM_CHANNEL_LIST_BY_SOURCE = "content://" + AUTHORITY + "/system/channel_list_by_source";
    public static final String URI_PATH_SYSTEM_GET_CURRENT_CHANNEL = "content://" + AUTHORITY + "/system/get_current_channel";
    public static final String URI_PATH_SYSTEM_IS_MEMC_VALID = "content://" + AUTHORITY + "/system/isMEMCValid";
    public static final String URI_PATH_SYSTEM_IS_TV_FOREGROUND = "content://" + AUTHORITY + "/system/tv_foreground";
    public static final String URI_PATH_SYSTEM_SOURCE_RESOLUTION = "content://" + AUTHORITY + "/system/source_resolution";
    public static final String URI_PATH_SYSTEM_SOURCE_FREESYNC = "content://" + AUTHORITY + "/system/freesync";
    public static final String URI_PATH_SETTING_TVMODE_SOURCE = "content://" + AUTHORITY + "/setting/tvmode_source";

    //QuickSetting 接口
    public static final String URI_PATH_QUICK_SETTING_SUPPORT = "content://" + AUTHORITY + "/";
    public static final String[] ATTRIBUTE_IS_SUPPORT = {"isSupport"};

    public static final String URI_PATH_QUICK_SETTING_ENABLE = "content://" + AUTHORITY + "/";
    public static final String[] ATTRIBUTE_IS_ENABLE = {"isEnable"};

    public static final String URI_PATH_QUICK_SETTING_NEED_CHECK = "content://" + AUTHORITY + "/";
    public static final String[] ATTRIBUTE_IS_NEED_CHECK = {"isNeedCheck"};

    public static final String URI_PATH_QUICK_SETTING_GET = "content://" + AUTHORITY + "/";
    public static final String URI_PATH_QUICK_SETTING_SET = "content://" + AUTHORITY + "/";
    public static final String[] ATTRIBUTE_CURRENT_INDEX = {"currentIndex"};

    public static final String URI_PATH_QUICK_SETTING_GET_LIST = "content://" + AUTHORITY + "/";
    public static final String[] ATTRIBUTE_OPTIONAL = {"optional"};


    // URI_PATH_SYSTEM
    public static final String[] ATTRIBUTE_API_LEVEL = {"apiLevel"};
    public static final String[] ATTRIBUTE_SOURCE = {"sourceName", "sourceIndex", "displayName", "signalState","sourceId"};
    public static final String[] ATTRIBUTE_SOURCE_INFO = {"name", "index", "displayName", "signalState"};
    public static final String[] ATTRIBUTE_IS_READY = {"isReady"};
    public static final String[] ATTRIBUTE_IS_RELEASED = {"isReleased"};
    public static final String[] ATTRIBUTE_CAN_SCREEN_CAPTURE = {"canScreenCapture"};
    public static final String[] ATTRIBUTE_SHOW_BOOT_GUIDE = {"showBootGuide"};
    public static final String[] ATTRIBUTE_HAS_OSD = {"hasOSD"};
    public static final String[] ATTRIBUTE_SIGNAL_STATE = {"signalState"};
    public static final String[] ATTRIBUTE_INTERACTION_ENABLE = {"interactionEnable"};
    public static final String[] ATTRIBUTE_CAN_INTERACTION = {"canInteraction"};
    public static final String[] ATTRIBUTE_IS_SEARCHING = {"isSearching"};
    public static final String[] ATTRIBUTE_STANDBY_REASON = {"reason"};
    public static final String[] ATTRIBUTE_IS_ENABLE_EXTERNAL_SOURCE = {"isEnableExternalSource"};
    public static final String[] ATTRIBUTE_IS_OFFLINE_CHECK_SUPPORTED = {"isOfflineCheckSupported"};
    public static final String[] ATTRIBUTE_CA_OPERATOR = {"id", "name"};
    public static final String[] ATTRIBUTE_CA_ENTITLE = {"id", "name", "startTime", "endTime", "canTape"};
    public static final String[] ATTRIBUTE_CHANNEL_SERACH_EDIT = {"channelSearchEdit"};
    public static final String[] ATTRIBUTE_RESOLUTION = {"resolution"};
    public static final String[] ATTRIBUTE_IS_MEMC_VALID = {"isMEMCValid"};
    public static final String[] ATTRIBUTE_IS_TV_FOREGROUND = {"isForeground"};
    public static final String[] ATTRIBUTE_SOURCE_RESOLUTION = {"SourceResolution"};
    public static final String[] ATTRIBUTE_SOURCE_FREESYNC = {"SourceFreesync"};

    // URI_PATH_SETTING
    public static final String[] ATTRIBUTE_BOOT_SOURCE = {"sourceName", "sourceIndex", "displayName","sourceId"};
    public static final String[] ATTRIBUTE_CHANNEL = {"channelID", "channelName", "sourceIndex","channelType"};
    public static final String[] ATTRIBUTE_AUTO_SWITCH_SOURCE = {"autoSwitchSource"};
    public static final String[] ATTRIBUTE_SOURCE_RENAME = {"sourceName", "sourceIndex","newName"};
    public static final String[] ATTRIBUTE_CEC_DEVICE_INFO = {"logAddr", "hdmiDevPort","phyAddr","devName", "hdmiListText","vendorId"};

    // URI_PATH_METHOD
    // For System API
    public static final String METHOD_SWITCH_CHANNEL = "METHOD_SWITCH_CHANNEL";
    public static final String METHOD_GOTO_SOURCE = "METHOD_GOTO_SOURCE";
    public static final String METHOD_SWITCH_SOURCE = "METHOD_SWITCH_SOURCE";
    public static final String METHOD_HOLD_SOURCE = "METHOD_HOLD_SOURCE";
    public static final String METHOD_RELEASE_SOURCE = "METHOD_RELEASE_SOURCE";
    public static final String METHOD_FORCE_RELEASE_SOURCE = "METHOD_FORCE_RELEASE_SOURCE";
    public static final String METHOD_RELEASE_ALL = "METHOD_RELEASE_ALL";
    public static final String METHOD_BACK_TO_TV = "METHOD_BACK_TO_TV";
    public static final String METHOD_BACK_TO_TV_SOURCE = "METHOD_BACK_TO_TV_SOURCE";
    public static final String METHOD_SET_TIME_FLAG = "METHOD_SET_TIME_FLAG";
    public static final String METHOD_SET_WINDOW_SIZE = "METHOD_SET_WINDOW_SIZE";
    public static final String METHOD_SEND_MESSAGE = "METHOD_SEND_MESSAGE";
    public static final String METHOD_ENABLE_EXTERNAL_SOURCE = "METHOD_ENABLE_EXTERNAL_SOURCE";//后续删除
    public static final String METHOD_START_SPLIT_SCREEN_TV = "METHOD_START_SPLIT_SCREEN_TV";
    public static final String METHOD_EXIT_SPLIT_SCREEN_TV = "METHOD_EXIT_SPLIT_SCREEN_TV";
    public static final String METHOD_RESIZE_SPLIT_SCREEN_TV = "METHOD_RESIZE_SPLIT_SCREEN_TV";
    public static final String METHOD_GOTO_SOURCE_WITH_CHANNEL = "METHOD_GOTO_SOURCE_WITH_CHANNEL";
    // For Setting API
    public static final String METHOD_SET_BOOT_SOURCE = "METHOD_SET_BOOT_SOURCE";

    //=====================================================================================================//
    public static final String SERVICE_NAME = "com.tianci.tv/com.tianci.tv.service.SkyTvService";
    public static final String TV_CMD_HEADER = "tianci://" + SERVICE_NAME;
    private static final String CMD = "?cmd=";
    private static final String NEEDACK = "&needack=";
    private static final String OVERRIDE = "&override=";

    public static String getCmd(String cmd)
    {
        return TV_CMD_HEADER + CMD + cmd;
    }

    public static String getCmd(String cmd, boolean needack, boolean override)
    {
        return TV_CMD_HEADER + CMD + cmd + NEEDACK + needack + OVERRIDE + override;
    }

    public static String getCmd(String header, String cmd)
    {
        return header + CMD + cmd;
    }

    public static final String TV_BROADCAST_ON_TVSERVICE_START = "TV_BROADCAST_ON_TVSERVICE_START";

    public enum TV_CMD
    {
        // testApi
        TV_CMD_TEST_SPEED,

        // systemApi
        TV_CMD_SYSTEM_REGISTER_SYSTEM_LISTENER,
        TV_CMD_SYSTEM_UNREGISTER_SYSTEM_LISTENER,
        TV_CMD_SYSTEM_ISREADY,
        TV_CMD_SYSTEM_ISFOCUSED,
        TV_CMD_SYSTEM_SWITCHSOURCE,
        TV_CMD_SYSTEM_ON_SWITCHSOURCE_START,
        TV_CMD_SYSTEM_ON_SWITCHSOURCE_DONE,
        TV_CMD_SYSTEM_GET_CURRENTSOURCE,
        TV_CMD_SYSTEM_GET_SOURCELIST,
        TV_CMD_SYSTEM_GET_EXTERNAL_SOURCELIST,
        TV_CMD_SYSTEM_ENABLE_EXTERNAL_SOURCE,
        TV_CMD_SYSTEM_GET_EXTERNAL_SOURCE_ENABLE,
        TV_CMD_SYSTEM_CHANNELUP,
        TV_CMD_SYSTEM_CHANNELDOWN,
        TV_CMD_SYSTEM_SOURCEUP,
        TV_CMD_SYSTEM_SOURCEDOWN,
        TV_CMD_SYSTEM_SWITCHCHANNEL,
        TV_CMD_SYSTEM_ON_SWITCHCHANNEL_START,
        TV_CMD_SYSTEM_ON_SWITCHCHANNEL_DONE,
        TV_CMD_SYSTEM_GET_CURRENTCHANNEL,
        TV_CMD_SYSTEM_RELEASE,
        TV_CMD_SYSTEM_ISRELEASED,
        TV_CMD_SYSTEM_HASSIGNAL,
        TV_CMD_SYSTEM_HASSIGNAL_ASYNC,
        TV_CMD_SYSTEM_ON_TV_START,
        TV_CMD_SYSTEM_ON_TV_RESTART,
        TV_CMD_SYSTEM_ON_TV_RELEASED,
        TV_CMD_SYSTEM_ON_SIGNAL_CHANGED,
        TV_CMD_SYSTEM_ON_WINDOWFOCUS_CHANGED,
        TV_CMD_SYSTEM_GET_RESOLUTION,
        TV_CMD_SYSTEM_ON_SIGNALFORMAT_CHANGED,
        TV_CMD_SYSTEM_ON_SIGNAL_PLUG_STATE_CHANGED,
        TV_CMD_SYSTEM_RESET_TO_FACTORYMODE,
        TV_CMD_SYSTEM_SHOW_INFOVIEW,
        TV_CMD_DTV_SET_TIMEUPDATEFLAG,
        TV_CMD_SYSTEM_RELEASE_ALL,
        TV_CMD_SYSTEM_BACKTO_TV,
        TV_CMD_SYSTEM_ISSWITCHSOURCE,
        TV_CMD_SYSTEM_ISSWITCHCHANNEL,

        // epgApi
        TV_CMD_EPG_REGISTER_EPG_ASYNCLOADLISTENER,
        TV_CMD_EPG_UNREGISTER_EPG_ASYNCLOADLISTENER,
        TV_CMD_EPG_REGISTER_EPG_LISTENER,
        TV_CMD_EPG_UNREGISTER_EPG_LISTENER,
        TV_CMD_EPG_HASEPG,
        TV_CMD_EPG_ON_EPG_UPDATE,
        TV_CMD_EPG_GET_BINDCHANNEL,
        TV_CMD_EPG_GET_CHANNELLIST,
        TV_CMD_EPG_GET_CHANNELLIST_BY_PAGE,
        TV_CMD_EPG_GET_CHANNELLIST_BY_CHANNEL,
        TV_CMD_EPG_GET_CHANNELLIST_BY_CATEGORY,
        TV_CMD_EPG_ASYNC_GET_CHANNELLIST,
        TV_CMD_EPG_STOP_ASYNC_GET_CHANNELLIST,
        TV_CMD_EPG_ON_ASYNC_GET_CHANNELLIST,
        TV_CMD_EPG_GET_TIMEEVENT,
        TV_CMD_EPG_ASYNC_GET_TIMEEVENT,
        TV_CMD_EPG_STOP_ASYNC_GET_TIMEEVENT,
        TV_CMD_EPG_ON_ASYNC_GET_TIMEEVENT,
        TV_CMD_EPG_GET_EPGEVENT,
        TV_CMD_EPG_GET_EPGEVENT_BY_PAGE,
        TV_CMD_EPG_ASYNC_GET_EPGEVENT,
        TV_CMD_EPG_STOP_ASYNC_GET_EPGEVENT,
        TV_CMD_EPG_ON_ASYNC_GET_EPGEVENT,
        TV_CMD_EPG_GET_EPGEVENTFROMNOW,
        TV_CMD_EPG_ASYNC_GET_EPGEVENTFROMNOW,
        TV_CMD_EPG_STOP_ASYNC_GET_EPGEVENTFROMNOW,
        TV_CMD_EPG_ON_ASYNC_GET_EPGEVENTFROMNOW,
        TV_CMD_EPG_GET_LIVEEPG,
        TV_CMD_EPG_GET_CHANNEL_CATEGORY,
        TV_CMD_EPG_ATTENTION_PROGRAMME,
        TV_CMD_EPG_ADD_BOOKPROGRAMMER,
        TV_CMD_EPG_DEL_BOOKPROGRAMMER,
        TV_CMD_EPG_REGISTER_BOOK_LISTENER,
        TV_CMD_EPG_UNREGISTER_BOOK_LISTENER,
        TV_CMD_EPG_ON_BOOKPROGRAMMER_CALLBACK,

        // settingApi
        TV_CMD_SETTING_SET_BOOTSOURCE,
        TV_CMD_SETTING_GET_BOOTSOURCE,
        TV_CMD_SETTING_GET_BOOTSOURCELIST,
        TV_CMD_SETTING_SET_DISPLAYMODE,
        TV_CMD_SETTING_GET_DISPLAYMODE,
        TV_CMD_SETTING_GET_DISPLAYMODES,

        // atvApi
        TV_CMD_ATV_REGISTER_AUTOSEARCH_LISTENER,
        TV_CMD_ATV_UNREGISTER_AUTOSEARCH_LISTENER,
        TV_CMD_ATV_REGISTER_MANUALSEARCH_LISTENER,
        TV_CMD_ATV_UNREGISTER_MANUALSEARCH_LISTENER,
        TV_CMD_ATV_REGISTER_FREQTRIM_LISTENER,
        TV_CMD_ATV_UNREGISTER_FREQTRIM_LISTENER,
        TV_CMD_ATV_START_AUTOSEARCH,
        TV_CMD_ATV_STOP_AUTOSEARCH,
        TV_CMD_ATV_ON_AUTOSEARCH_START,
        TV_CMD_ATV_ON_AUTOSEARCH_PROCESS,
        TV_CMD_ATV_ON_AUTOSEARCH_END,
        TV_CMD_ATV_START_MANUALSEARCH,
        TV_CMD_ATV_PROCESS_MANUALSEARCH,
        TV_CMD_ATV_STOP_MANUALSEARCH,
        TV_CMD_ATV_ON_MANUALSEARCH_START,
        TV_CMD_ATV_ON_MANUALSEARCH_PROCESS,
        TV_CMD_ATV_ON_MANUALSEARCH_END,
        TV_CMD_ATV_START_FREQUENCYTRIM,
        TV_CMD_ATV_PROCESS_FREQUENCYTRIM,
        TV_CMD_ATV_STOP_FREQUENCYTRIM,
        TV_CMD_ATV_ON_FREQUENCYTRIM_START,
        TV_CMD_ATV_ON_FREQUENCYTRIM_PROCESS,
        TV_CMD_ATV_ON_FREQUENCYTRIM_END,
        TV_CMD_ATV_SET_SKIPCHANNEL,
        TV_CMD_ATV_GET_SKIPCHANNEL,
        TV_CMD_ATV_SET_VOLUMECOMPENSATION,
        TV_CMD_ATV_GET_VOLUMECOMPENSATION,
        TV_CMD_ATV_SET_FREQUENCYPOINT,
        TV_CMD_ATV_GET_FREQUENCYPOINT,
        TV_CMD_ATV_SET_COLORSYSTEM,
        TV_CMD_ATV_GET_COLORSYSTEM,
        TV_CMD_ATV_GET_REAL_COLORSYSTEM,
        TV_CMD_ATV_GET_COLORSYSTEMVALUES,
        TV_CMD_ATV_GET_AUDIONICAM,
        TV_CMD_ATV_GET_CURAUDIONICAM,
        TV_CMD_ATV_SET_AUDIONICAM,
        TV_CMD_ATV_SET_SOUNDSYSTEM,
        TV_CMD_ATV_GET_SOUNDSYSTEM,
        TV_CMD_ATV_GET_SOUNDSYSTEMVALUES,

        // ipliveApi
        TV_CMD_IPLIVE_SET_LISTENER,
        TV_CMD_IPLIVE_ON_PREPARED_START,
        TV_CMD_IPLIVE_ON_PREPARED,
        TV_CMD_IPLIVE_ON_RELEASED,
        TV_CMD_IPLIVE_ON_INFO,
        TV_CMD_IPLIVE_ON_COMPLETION,
        TV_CMD_IPLIVE_ON_ERROR,
        TV_CMD_IPLIVE_ON_SEEK_COMPLETE,
        TV_CMD_IPLIVE_ON_BUFFERING_UPDATE,
        TV_CMD_IPLIVE_ON_IPLIVE_INFO,
        TV_CMD_IPLIVE_GET_NETSPEED,
        TV_CMD_IPLIVE_GET_RESOURCE_LIST,
        TV_CMD_IPLIVE_GET_CURRENT_RESOURCE,
        TV_CMD_IPLIVE_PLAY_RESOURCE,
        // dvbc
        TV_CMD_DVBC_REGISTER_AUTOSEARCH_LISTENER,
        TV_CMD_DVBC_UNREGISTER_AUTOSEARCH_LISTENER,
        TV_CMD_DVBC_REGISTER_MANUALSEARCH_LISTENER,
        TV_CMD_DVBC_UNREGISTER_MANUALSEARCH_LISTENER,
        TV_CMD_DVBC_REGISTER_NITSEARCH_LISTENER,
        TV_CMD_DVBC_UNREGISTER_NITSEARCH_LISTENER,
        TV_CMD_DVBC_START_AUTOSEARCH,
        TV_CMD_DVBC_STOP_AUTOSEARCH,
        TV_CMD_DVBC_ON_AUTOSEARCH_START,
        TV_CMD_DVBC_ON_AUTOSEARCH_PROCESS,
        TV_CMD_DVBC_ON_AUTOSEARCH_END,
        TV_CMD_DVBC_START_MANUALSEARCH,
        TV_CMD_DVBC_PROCESS_MANUALSEARCH,
        TV_CMD_DVBC_STOP_MANUALSEARCH,
        TV_CMD_DVBC_ON_MANUALSEARCH_START,
        TV_CMD_DVBC_ON_MANUALSEARCH_PROCESS,
        TV_CMD_DVBC_ON_MANUALSEARCH_END,
        TV_CMD_DVBC_START_NITSEARCH,
        TV_CMD_DVBC_PROCESS_NITSEARCH,
        TV_CMD_DVBC_STOP_NITSEARCH,
        TV_CMD_DVBC_ON_NITSEARCH_START,
        TV_CMD_DVBC_ON_NITSEARCH_PROCESS,
        TV_CMD_DVBC_ON_NITSEARCH_END,
        TV_CMD_DVBC_ON_NITUPDATE_NOTIFY,
        TV_CMD_DVBC_REGISTER_CAMESSAGE_LISTENER,
        TV_CMD_DVBC_UNREGISTER_CAMESSAGE_LISTENER,
        TV_CMD_DVBC_ON_CAOTHER_CALLBACK,
        TV_CMD_DVBC_ON_CASETTING_CALLBACK,
        TV_CMD_DVBC_ON_CAMESSAGE_CALLBACK,
        TV_CMD_DVBC_ON_IRDETOEMM_CALLBACK,
        TV_CMD_DVBC_ON_IRDETOECM_CALLBACK,
        TV_CMD_DVBC_GET_CAOPERATORLIST,
        TV_CMD_DVBC_SET_SWITCH_CHANNELMODE,
        TV_CMD_DVBC_GET_SWITCH_CHANNELMODE,
        TV_CMD_DVBC_GET_CARD_INFO,
        TV_CMD_DVBC_SET_CAOPERATOR,
        TV_CMD_DVBC_GET_INSERTCARD_STATUS,

        // DTMB
        TV_CMD_DVBC_GET_INFO,

        TV_CMD_DVBC_GET_CURRENT_VOLUME_COMPENSATION,
        TV_CMD_DVBC_SET_VOLUME_COMPENSATION,
        TV_CMD_DVBC_GET_SUPPORT_SOUND_TYPES,
        TV_CMD_DVBC_GET_CURRENT_SOUNDTYPE,
        TV_CMD_DVBC_SET_SOUNDTYPE,
        TV_CMD_DVBC_GET_SUPPORT_SOUNDTRACES,
        TV_CMD_DVBC_GET_CURRENT_SOUNDTRACE,
        TV_CMD_DVBC_SET_SOUNDTRACE,
        TV_CMD_DVBC_GET_MAILLIST,
        TV_CMD_DVBC_GET_MAILCONTEXT,
        TV_CMD_DVBC_DELETE_MAIL,
        TV_CMD_DVBC_DELETE_ALLMAIL,
        TV_CMD_DVBC_GET_FREQUENCY,
        TV_CMD_DVBC_SET_FREQUENCY,
        TV_CMD_DVBC_GET_CHANNELINFO,
        TV_CMD_DVBC_SET_PLAYER_STATE,
        TV_CMD_DVBC_GET_CARDBASICINFO,
        TV_CMD_DVBC_GET_PLAYER_STATE,
        // TV_CMD_DVBC_SET_TIMEUPDATEFLAG,
        TV_CMD_DVBC_GET_CURCATYPE,
        TV_CMD_DVBC_GET_IRDETO_PROGRAMINFO,
        TV_CMD_DVBC_GET_IRDETO_LOADINFO,
        TV_CMD_DVBC_GET_DIGITAL_CAMENU,
        TV_CMD_DVBC_SET_SKIPCHANNEL,
        TV_CMD_DVBC_GET_SKIPCHANNEL,
        TV_CMD_DVBC_SHOW_CASETTING_SUBMENU,

        // DTMB
        TV_CMD_DTMB_REGISTER_AUTOSEARCH_LISTENER,
        TV_CMD_DTMB_UNREGISTER_AUTOSEARCH_LISTENER,
        TV_CMD_DTMB_REGISTER_MANUALSEARCH_LISTENER,
        TV_CMD_DTMB_UNREGISTER_MANUALSEARCH_LISTENER,
        TV_CMD_DTMB_ON_AUTOSEARCH_START,
        TV_CMD_DTMB_ON_AUTOSEARCH_PROCESS,
        TV_CMD_DTMB_ON_AUTOSEARCH_END,
        TV_CMD_DTMB_ON_MANUALSEARCH_START,
        TV_CMD_DTMB_ON_MANUALSEARCH_PROCESS,
        TV_CMD_DTMB_ON_MANUALSEARCH_END,
        TV_CMD_DTMB_REGISTER_NITSEARCH_LISTENER,
        TV_CMD_DTMB_UNREGISTER_NITSEARCH_LISTENER,
        TV_CMD_DTMB_START_AUTOSEARCH,
        TV_CMD_DTMB_STOP_AUTOSEARCH,
        TV_CMD_DTMB_START_MANUALSEARCH,
        TV_CMD_DTMB_PROCESS_MANUALSEARCH,
        TV_CMD_DTMB_STOP_MANUALSEARCH,
        TV_CMD_DTMB_START_NITSEARCH,
        TV_CMD_DTMB_PROCESS_NITSEARCH,
        TV_CMD_DTMB_STOP_NITSEARCH,
        TV_CMD_DTMB_ON_NITSEARCH_START,
        TV_CMD_DTMB_ON_NITSEARCH_PROCESS,
        TV_CMD_DTMB_ON_NITSEARCH_END,
        TV_CMD_DTMB_REGISTER_CAMESSAGE_LISTENER,
        TV_CMD_DTMB_UNREGISTER_CAMESSAGE_LISTENER,
        TV_CMD_DTMB_ON_CAMAIL_CALLBACK,
        TV_CMD_DTMB_ON_CAMESSAGE_CALLBACK,
        TV_CMD_DTMB_GET_CAOPERATORLIST,
        TV_CMD_DTMB_GET_INFO,

        TV_CMD_DTMB_GET_CURRENT_VOLUME_COMPENSATION,
        TV_CMD_DTMB_SET_VOLUME_COMPENSATION,
        TV_CMD_DTMB_GET_SUPPORT_SOUND_TYPES,
        TV_CMD_DTMB_GET_CURRENT_SOUNDTYPE,
        TV_CMD_DTMB_SET_SOUNDTYPE,
        TV_CMD_DTMB_GET_SUPPORT_SOUNDTRACES,
        TV_CMD_DTMB_GET_CURRENT_SOUNDTRACE,
        TV_CMD_DTMB_SET_SOUNDTRACE,
        TV_CMD_DTMB_GET_MAILLIST,
        TV_CMD_DTMB_GET_MAILCONTEXT,
        TV_CMD_DTMB_DELETE_MAIL,
        TV_CMD_DTMB_DELETE_ALLMAIL,
        TV_CMD_DTMB_GET_FREQUENCY,
        TV_CMD_DTMB_SET_FREQUENCY,
        TV_CMD_DTMB_GET_CHANNELINFO,
        TV_CMD_DTMB_GET_CARDBASICINFO,
        // TV_CMD_DTMB_SET_TIMEUPDATEFLAG,
        TV_CMD_DTMB_SET_SWITCH_CHANNELMODE,
        TV_CMD_DTMB_GET_SWITCH_CHANNELMODE,
        TV_CMD_DTMB_GET_SUBTITLE,
        TV_CMD_DTMB_SET_SUBTITLE,
        TV_CMD_DTMB_GET_CURSUBTITLE,
        TV_CMD_DTMB_SET_SKIPCHANNEL,
        TV_CMD_DTMB_GET_SKIPCHANNEL,
        // avApi

        // hdmiApi

        // yuvApi

        // vgaApi
        TV_CMD_VGA_REGISTER_AUTOADJUST_LISTENER,
        TV_CMD_VGA_UNREGISTER_AUTOADJUST_LISTENER,
        TV_CMD_VGA_START_AUTOADJUST,
        TV_CMD_VGA_ON_AUTOADJUST_START,
        TV_CMD_VGA_ON_AUTOADJUST_COMPLETE,
        TV_CMD_VGA_SET_HPOSITION,
        TV_CMD_VGA_GET_HPOSITION,
        TV_CMD_VGA_SET_VPOSITION,
        TV_CMD_VGA_GET_VPOSITION,
        TV_CMD_VGA_SET_PHASE,
        TV_CMD_VGA_GET_PHASE,
        TV_CMD_VGA_SET_REFRESHRATE,
        TV_CMD_VGA_GET_REFRESHRATE,

        TV_CMD_AV_GET_COLORSYSTEMVALUES,
        TV_CMD_AV_GET_REAL_COLORSYSTEM,
        TV_CMD_AV_GET_COLORSYSTEM,
        TV_CMD_AV_SET_COLORSYSTEM,
        TV_CMD_INTERACTIVE_MSG,
        TV_CMD_INTERACTIVE_MSG_BACK,
        TV_CMD_INTERACTIVE_DATA,
        TV_CMD_INTERACTIVE_DATA_BACK,
        TV_CMD_INTERACTIVE_VOTE,
        TV_CMD_INTERACTIVE_VOTE_BACK,
        TV_CMD_INTERACTIVE_OPTION_IN,
        TV_CMD_INTERACTIVE_OPTION_IN_BACK,
        TV_CMD_INTERACTIVE_INDEX,
        TV_CMD_INTERACTIVE_OPTION,

        TV_CMD_VOD_PLAY_VOD,

        // smartTV
        TV_CMD_SMART_REGISTER_SMART_LISTENER,
        TV_CMD_SMART_UNREGISTER_SMART_LISTENER,
        TV_CMD_SMART_GET_CURRENT_RECOGNIZED_CHANNEL,
        TV_CMD_SMART_ON_CHANNEL_RECOGNIZED,

        // new cmd must be added to tail,in order to remain compliant with the sdk released to third
        // party apk
        TV_CMD_SYSTEM_STOP_SEARCHCHANNEL,
        TV_CMD_DVBC_SHOW_CASETTING_MAINMENU,
        TV_CMD_DVBC_SHOW_DIGITAL_DTVINFO,
        TV_CMD_DVBC_GET_SUBTITLE,
        TV_CMD_DVBC_SET_SUBTITLE,
        TV_CMD_DVBC_GET_CURSUBTITLE,
        TV_CMD_DVBC_GET_CAUIAPK_STATUS,
        TV_CMD_DVBC_SHOW_SERVICE_MENU,
        TV_CMD_SYSTEM_CAN_CAPTURE_SCREEN,
        TV_CMD_SYSTEM_GET_SWITCHSOURE_TYPE,
        TV_CMD_SYSTEM_SET_NEEDSHOW_BOOTGUIDE,
        TV_CMD_SYSTEM_CAN_POPUP_INTERACTION,
        TV_CMD_SYSTEM_TVVIDEO_ISFOCUSED,
        TV_CMD_SYSTEM_INTERACTION_ISENABLED,
        TV_CMD_SYSTEM_BACKTO_TVSOURCE,
        TV_CMD_SYSTEM_SWITCHSOURCE_BACKGROUND,
        TV_CMD_SYSTEM_RESTORE_PRESETCHANNEL,
        TV_CMD_SYSTEM_ISSEARCHCHANNEL,
        TV_CMD_DVBC_ON_CARDTYPESTATUS_CALLBACK,
        TV_CMD_SETTING_SET_BOOTCHANNEL,
        TV_CMD_SETTING_GET_BOOTCHANNEL,
        TV_CMD_SETTING_GET_BOOTCHANNEL_LIST,
        TV_CMD_SYSTEM_RESOURCE_CONFLICT_SOLVE,
        TV_CMD_SYSTEM_SET_STANDBY_REASON,
        TV_CMD_SYSTEM_IS_OFFLINECHECK_SUPPORTED,
    }
}
