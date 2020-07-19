package com.crawler.utils;

import org.apache.commons.lang3.math.NumberUtils;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 日期格式话
 * @author wencai.xu
 */
public class Timer {
    private static final String DD_MM_YYYY = "dd-MMM-yyyy";
    private static ThreadLocal<DateFormatter> formatter = ThreadLocal.withInitial(
            ()->new DateFormatter(new SimpleDateFormat(DD_MM_YYYY))
    );

    public static void main(String[] args) {
        Stream.of(new Integer[]{1, 2}, new Integer[]{3, 4}).flatMap(Arrays::stream).collect(Collectors.toList()).forEach(
                System.out::println
        );
        List<Integer> arrays = Stream.of(1, 5).collect(Collectors.toList());
    }


}
