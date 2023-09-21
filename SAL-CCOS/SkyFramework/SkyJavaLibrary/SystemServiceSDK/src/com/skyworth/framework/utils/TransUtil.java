package com.skyworth.framework.utils;

import com.skyworth.framework.define.SystemDefines;
import com.tianci.system.define.SkyConfigDefs;

public class TransUtil {

    public static int tranPictureModeToLocal(SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE sdkmode) {
        int mode = SystemDefines.PICTURE_MODE_STANDARD;
        switch (sdkmode) {
            case SKY_CFG_TV_PICTURE_MODE_STANDARD:
                mode = SystemDefines.PICTURE_MODE_STANDARD;
                break;
            case SKY_CFG_TV_PICTURE_MODE_VIVID:
                mode = SystemDefines.PICTURE_MODE_VIVID;
                break;
            case SKY_CFG_TV_PICTURE_MODE_GENTLE:
                mode = SystemDefines.PICTURE_MODE_GENTLE;
                break;
            case SKY_CFG_TV_PICTURE_MODE_USER:
                mode = SystemDefines.PICTURE_MODE_USER;
                break;
            case SKY_CFG_TV_PICTURE_MODE_4K_CINEMA:
                mode = SystemDefines.PICTURE_MODE_4K_CINEMA;
                break;
            case SKY_CFG_TV_PICTURE_MODE_NATURAL:
                mode = SystemDefines.PICTURE_MODE_NATURAL;
                break;
            case SKY_CFG_TV_PICTURE_MODE_GAME:
                mode = SystemDefines.PICTURE_MODE_GAME;
                break;
            case SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_MOVIE_DARK:
                mode = SystemDefines.PICTURE_MODE_DOBLY_VISION_MOVIE_DARK;
                break;
            case SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_MOVIE_BRIGHT:
                mode = SystemDefines.PICTURE_MODE_DOBLY_VISION_MOVIE_BRIGHT;
                break;
            case SKY_CFG_TV_PICTURE_MODE_PROFESSIONAL:
                mode = SystemDefines.PICTURE_MODE_PROFESSIONAL;
                break;
            case SKY_CFG_TV_PICTURE_MODE_SPORT:
                mode = SystemDefines.PICTURE_MODE_SPORT;
                break;
            case SKY_CFG_TV_PICTURE_MODE_MOVIE:
                mode = SystemDefines.PICTURE_MODE_MOVIE;
                break;
            case SKY_CFG_TV_PICTURE_MODE_DYNAMIC:
                mode = SystemDefines.PICTURE_MODE_DYNAMIC;
                break;
            case SKY_CFG_TV_PICTURE_MODE_PHOTO:
                mode = SystemDefines.PICTURE_MODE_PHOTO;
                break;
            case SKY_CFG_TV_PICTURE_MODE_ECO:
                mode = SystemDefines.PICTURE_MODE_ECO;
                break;
            case SKY_CFG_TV_PICTURE_MODE_CARE:
                mode = SystemDefines.PICTURE_MODE_CARE;
                break;
            case SKY_CFG_TV_PICTURE_MODE_AI:
                mode = SystemDefines.PICTURE_MODE_AI;
                break;
            case SKY_CFG_TV_PICTURE_MODE_CAMERA:
                mode = SystemDefines.PICTURE_MODE_CAMERA;
                break;
            case SKY_CFG_TV_PICTURE_MODE_AUTO:
                mode = SystemDefines.PICTURE_MODE_AUTO;
                break;
            case SKY_CFG_TV_PICTURE_MODE_GAME_GENERAL:
                mode = SystemDefines.PICTURE_MODE_GAME_GENERAL;
                break;
            case SKY_CFG_TV_PICTURE_MODE_GAME_RTS_RPG:
                mode = SystemDefines.PICTURE_MODE_GAME_RTS_RPG;
                break;
            case SKY_CFG_TV_PICTURE_MODE_GAME_MOBA:
                mode = SystemDefines.PICTURE_MODE_GAME_MOBA;
                break;
            case SKY_CFG_TV_PICTURE_MODE_GAME_FPS:
                mode = SystemDefines.PICTURE_MODE_GAME_FPS;
                break;
            case SKY_CFG_TV_PICTURE_MODE_GAME_CINEMA:
                mode = SystemDefines.PICTURE_MODE_GAME_CINEMA;
                break;
            case SKY_CFG_TV_PICTURE_MODE_GAME_READ:
                mode = SystemDefines.PICTURE_MODE_GAME_READ;
                break;
            case SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_MOVIE_IQ:
                mode = SystemDefines.PICTURE_MODE_DOBLY_VISION_MOVIE_IQ;
                break;
            case SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_GAME:
                mode = SystemDefines.PICTURE_MODE_DOBLY_VISION_GAME;
                break;
            case SKY_CFG_TV_PICTURE_MODE_CUVA_HDR:
                mode = SystemDefines.PICTURE_MODE_CUVA_HDR;
                break;
            case SKY_CFG_TV_PICTURE_MODE_LOW_BLUE_LIGHT:
                mode = SystemDefines.PICTURE_MODE_LOW_BLUE_LIGHT;
                break;
            case SKY_CFG_TV_PICTURE_MODE_DARKROOM:
                mode = SystemDefines.PICTURE_MODE_DARKROOM;
                break;
            case SKY_CFG_TV_PICTURE_MODE_DICOM:
                mode = SystemDefines.PICTURE_MODE_DICOM;
                break;
            case SKY_CFG_TV_PICTURE_MODE_NOTEBOOK:
                mode = SystemDefines.PICTURE_MODE_NOTEBOOK;
                break;
            case SKY_CFG_TV_PICTURE_MODE_FRAME_ENJOY:
                mode = SystemDefines.PICTURE_MODE_FRAME_ENJOY;
                break;
            case SKY_CFG_TV_PICTURE_MODE_PC:
                mode = SystemDefines.PICTURE_MODE_FRAME_PC;
                break;
            case SKY_CFG_TV_PICTURE_MODE_CUSTOM:
                mode = SystemDefines.PICTURE_MODE_CUSTOM;
                break;

        }
        return mode;
    }

    public static SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE tranPictureModeToSdk(int mode) {
        SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_STANDARD;
        switch (mode) {
            case SystemDefines.PICTURE_MODE_STANDARD:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_STANDARD;
                break;
            case SystemDefines.PICTURE_MODE_VIVID:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_VIVID;
                break;
            case SystemDefines.PICTURE_MODE_GENTLE:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_GENTLE;
                break;
            case SystemDefines.PICTURE_MODE_USER:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_USER;
                break;
            case SystemDefines.PICTURE_MODE_4K_CINEMA:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_4K_CINEMA;
                break;
            case SystemDefines.PICTURE_MODE_NATURAL:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_NATURAL;
                break;
            case SystemDefines.PICTURE_MODE_GAME:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_GAME;
                break;
            case SystemDefines.PICTURE_MODE_DOBLY_VISION_MOVIE_DARK:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_MOVIE_DARK;
                break;
            case SystemDefines.PICTURE_MODE_DOBLY_VISION_MOVIE_BRIGHT:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_MOVIE_BRIGHT;
                break;
            case SystemDefines.PICTURE_MODE_PROFESSIONAL:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_PROFESSIONAL;
                break;
            case SystemDefines.PICTURE_MODE_SPORT:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_SPORT;
                break;
            case SystemDefines.PICTURE_MODE_MOVIE:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_MOVIE;
                break;
            case SystemDefines.PICTURE_MODE_DYNAMIC:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_DYNAMIC;
                break;
            case SystemDefines.PICTURE_MODE_PHOTO:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_PHOTO;
                break;
            case SystemDefines.PICTURE_MODE_ECO:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_ECO;
                break;
            case SystemDefines.PICTURE_MODE_CARE:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_CARE;
                break;
            case SystemDefines.PICTURE_MODE_AI:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_AI;
                break;
            case SystemDefines.PICTURE_MODE_CAMERA:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_CAMERA;
                break;
            case SystemDefines.PICTURE_MODE_AUTO:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_AUTO;
                break;
            case SystemDefines.PICTURE_MODE_GAME_GENERAL:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_GAME_GENERAL;
                break;
            case SystemDefines.PICTURE_MODE_GAME_RTS_RPG:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_GAME_RTS_RPG;
                break;
            case SystemDefines.PICTURE_MODE_GAME_MOBA:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_GAME_MOBA;
                break;
            case SystemDefines.PICTURE_MODE_GAME_FPS:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_GAME_FPS;
                break;
            case SystemDefines.PICTURE_MODE_GAME_CINEMA:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_GAME_CINEMA;
                break;
            case SystemDefines.PICTURE_MODE_GAME_READ:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_GAME_READ;
                break;
            case SystemDefines.PICTURE_MODE_DOBLY_VISION_MOVIE_IQ:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_MOVIE_IQ;
                break;
            case SystemDefines.PICTURE_MODE_DOBLY_VISION_GAME:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_DOLBY_VISION_GAME;
                break;
            case SystemDefines.PICTURE_MODE_CUVA_HDR:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_CUVA_HDR;
                break;
            case SystemDefines.PICTURE_MODE_LOW_BLUE_LIGHT:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_LOW_BLUE_LIGHT;
                break;
            case SystemDefines.PICTURE_MODE_DARKROOM:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_DARKROOM;
                break;
            case SystemDefines.PICTURE_MODE_DICOM:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_DICOM;
                break;
            case SystemDefines.PICTURE_MODE_NOTEBOOK:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_NOTEBOOK;
                break;
            case SystemDefines.PICTURE_MODE_FRAME_ENJOY:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_FRAME_ENJOY;
                break;
            case SystemDefines.PICTURE_MODE_FRAME_PC :
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_PC;
                break;
            case SystemDefines.PICTURE_MODE_CUSTOM :
                sdkmode = SkyConfigDefs.SKY_CFG_TV_PICTURE_MODE_ENUM_TYPE.SKY_CFG_TV_PICTURE_MODE_CUSTOM;
                break;
        }
        return sdkmode;
    }

    public static int tranContentSceneToMode(SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE sdkMode) {
        int mode = SystemDefines.CONTENT_SCENE_OTHER;
        switch (sdkMode) {
            case SKY_CFG_TV_PICTURE_SCENE_MOVIE:
                mode = SystemDefines.CONTENT_SCENE_MOVIE;
                break;
            case SKY_CFG_TV_PICTURE_SCENE_NEWS:
                mode = SystemDefines.CONTENT_SCENE_NEWS;
                break;
            case SKY_CFG_TV_PICTURE_SCENE_SHOW:
                mode = SystemDefines.CONTENT_SCENE_SHOW;
                break;
            case SKY_CFG_TV_PICTURE_SCENE_SPORTS:
                mode = SystemDefines.CONTENT_SCENE_SPORTS;
                break;
            case SKY_CFG_TV_PICTURE_SCENE_GAME:
                mode = SystemDefines.CONTENT_SCENE_GAME;
                break;
            case SKY_CFG_TV_PICTURE_SCENE_GAME_APP:
                mode = SystemDefines.CONTENT_SCENE_GAME_APP;
                break;
            case SKY_CFG_TV_PICTURE_SCENE_MUSIC:
                mode = SystemDefines.CONTENT_SCENE_MUSIC;
                break;
            case SKY_CFG_TV_PICTURE_SCENE_MUSIC_APP:
                mode = SystemDefines.CONTENT_SCENE_MUSIC_APP;
                break;
            case SKY_CFG_TV_PICTURE_SCENE_OTHER:
                mode = SystemDefines.CONTENT_SCENE_OTHER;
                break;
        }
        return mode;
    }

    public static SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE tranContentSceneToSdk(int mode) {
        SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE sdkmode = SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE.SKY_CFG_TV_PICTURE_SCENE_OTHER;
        switch (mode) {
            case SystemDefines.CONTENT_SCENE_MOVIE:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE.SKY_CFG_TV_PICTURE_SCENE_MOVIE;
                break;
            case SystemDefines.CONTENT_SCENE_NEWS:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE.SKY_CFG_TV_PICTURE_SCENE_NEWS;
                break;
            case SystemDefines.CONTENT_SCENE_SHOW:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE.SKY_CFG_TV_PICTURE_SCENE_SHOW;
                break;
            case SystemDefines.CONTENT_SCENE_SPORTS:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE.SKY_CFG_TV_PICTURE_SCENE_SPORTS;
                break;
            case SystemDefines.CONTENT_SCENE_GAME:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE.SKY_CFG_TV_PICTURE_SCENE_GAME;
                break;
            case SystemDefines.CONTENT_SCENE_GAME_APP:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE.SKY_CFG_TV_PICTURE_SCENE_GAME_APP;
                break;
            case SystemDefines.CONTENT_SCENE_MUSIC:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE.SKY_CFG_TV_PICTURE_SCENE_MUSIC;
                break;
            case SystemDefines.CONTENT_SCENE_MUSIC_APP:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE.SKY_CFG_TV_PICTURE_SCENE_MUSIC_APP;
                break;
            case SystemDefines.CONTENT_SCENE_OTHER:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_CONTENT_SCENE_ENUM_TYPE.SKY_CFG_TV_PICTURE_SCENE_OTHER;
                break;

        }
        return sdkmode;
    }

    public static SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE tranSoundModeToSdk(int mode) {
        SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_STANDARD;
        switch (mode) {
            case SystemDefines.SOUND_MODE_STANDARD:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_STANDARD;
                break;
            case SystemDefines.SOUND_MODE_MUSIC:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_MUSIC;
                break;
            case SystemDefines.SOUND_MODE_NEWS:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_NEWS;
                break;
            case SystemDefines.SOUND_MODE_MOVIE:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_MOVIE;
                break;
            case SystemDefines.SOUND_MODE_3D_MOVIE_EFFECT:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_3D_MOVIE_EFFECT;
                break;
            case SystemDefines.SOUND_MODE_USER:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_USER;
                break;
            case SystemDefines.SOUND_MODE_SPORT:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_SPORT;
                break;
            case SystemDefines.SOUND_MODE_GAME:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_GAME;
                break;
            case SystemDefines.SOUND_MODE_CARE:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_CARE;
                break;
            case SystemDefines.SOUND_MODE_AI:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_AI;
                break;
            case SystemDefines.SOUND_MODE_AUTO:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_AUTO;
                break;
            case SystemDefines.SOUND_MODE_CUSTOM:
                sdkmode = SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE.SKY_CFG_TV_SOUND_MODE_CUSTOM;
                break;
        }
        return sdkmode;
    }

    public static int tranSoundModeToLocal(SkyConfigDefs.SKY_CFG_TV_SOUND_MODE_ENUM_TYPE sdkmode) {
        int mode = SystemDefines.SOUND_MODE_STANDARD;
        switch (sdkmode) {
            case SKY_CFG_TV_SOUND_MODE_STANDARD:
                mode = SystemDefines.SOUND_MODE_STANDARD;
                break;
            case SKY_CFG_TV_SOUND_MODE_MUSIC:
                mode = SystemDefines.SOUND_MODE_MUSIC;
                break;
            case SKY_CFG_TV_SOUND_MODE_NEWS:
                mode = SystemDefines.SOUND_MODE_NEWS;
                break;
            case SKY_CFG_TV_SOUND_MODE_MOVIE:
                mode = SystemDefines.SOUND_MODE_MOVIE;
                break;
            case SKY_CFG_TV_SOUND_MODE_3D_MOVIE_EFFECT:
                mode = SystemDefines.SOUND_MODE_3D_MOVIE_EFFECT;
                break;
            case SKY_CFG_TV_SOUND_MODE_USER:
                mode = SystemDefines.SOUND_MODE_USER;
                break;
            case SKY_CFG_TV_SOUND_MODE_SPORT:
                mode = SystemDefines.SOUND_MODE_SPORT;
                break;
            case SKY_CFG_TV_SOUND_MODE_GAME:
                mode = SystemDefines.SOUND_MODE_GAME;
                break;
            case SKY_CFG_TV_SOUND_MODE_CARE:
                mode = SystemDefines.SOUND_MODE_CARE;
                break;
            case SKY_CFG_TV_SOUND_MODE_AI:
                mode = SystemDefines.SOUND_MODE_AI;
                break;
            case SKY_CFG_TV_SOUND_MODE_AUTO:
                mode = SystemDefines.SOUND_MODE_AUTO;
                break;
            case SKY_CFG_TV_SOUND_MODE_CUSTOM:
                mode = SystemDefines.SOUND_MODE_CUSTOM;
                break;
        }
        return mode;
    }

    public static int tranStreamTypeToLocal(SkyConfigDefs.SKY_CFG_TV_VIDEO_STREAM_TYPE_ENUM_TYPE sdktype) {
        int type = SystemDefines.STREAM_TYPE_UNKNOWN;
        switch (sdktype) {
            case SKY_CFG_TV_VIDEO_STREAM_TYPE_SDR:
                type = SystemDefines.STREAM_TYPE_SDR;
                break;
            case SKY_CFG_TV_VIDEO_STREAM_TYPE_HDR10:
                type = SystemDefines.STREAM_TYPE_HDR10;
                break;
            case SKY_CFG_TV_VIDEO_STREAM_TYPE_DOLBY_HDR:
                type = SystemDefines.STREAM_TYPE_DOLBY_HDR;
                break;
            case SKY_CFG_TV_VIDEO_STREAM_TYPE_HLG_HDR:
                type = SystemDefines.STREAM_TYPE_HLG_HDR;
                break;
            case SKY_CFG_TV_VIDEO_STREAM_TYPE_CUVA_HDR:
                type = SystemDefines.STREAM_TYPE_CUVA_HDR;
                break;
        }
        return type;
    }
}
