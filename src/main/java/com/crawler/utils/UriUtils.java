package com.crawler.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * Uri处理工具类
 *
 * @author wencai.xu
 */
public class UriUtils {

    private static final String SLASH_LINE="/";
    private static final String SLASH_DOUBLE_LINE="//";

    private static PropertiesUtils utils = PropertiesUtils.getInstance();

    public static String createUri(String url){
        if(StringUtils.isEmpty(url)){
            return null;
        }
        String uri;
        if(url.startsWith(SLASH_DOUBLE_LINE)){
            uri = composerUri("http.prefix",url);
        }else if (url.startsWith(SLASH_LINE)){
            uri = composerUri("http.refer",url);
        }else{
            return url;
        }
        return uri;
    }

    public static boolean hasVideo(String uri){
        if(StringUtils.isEmpty(uri)){
            return false;
        }
        boolean hasVideo = false;
        String[] parts = uri.split(SLASH_LINE);
        if(isVideo(parts)){
            hasVideo=true;
        }
        return hasVideo;
    }

    private static boolean isVideo(String[] parts){
        if(parts.length < 5){
            return false;
        }
        final String video="video";
        return
                (StringUtils.isNotEmpty(parts[3]) && parts[3].equals(video))
                        && parts[4].length() == 12;
    }

    public static boolean hasChannel(String uri){
        if(StringUtils.isEmpty(uri)){
            return false;
        }
        boolean isChannel = false;
        final String v = "v";
        String[] parts = uri.split(SLASH_LINE);
        if(parts.length >= 4 && parts[3].equals(v)){
            isChannel = true;
        }
        return isChannel;
    }

    private static String composerUri(String prefix, String url){
        return
                new StringBuilder(utils.getValueBy(prefix))
                        .append(url).toString();
    }
}
