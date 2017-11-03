/*
 * Copyright (C), 2015-2017
 * FileName: SortTestHelper
 * Author:   Administrator
 * Date:     2017/11/3 0003 9:28
 * Description: help test Sort for generate random numbers
 */

package me.zonglun.Sort;

import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br>
 * 〈help test Sort for generate random numbers〉
 *
 * @author Administrator
 * @created 2017/11/3 0003
 * @since 1.0.0
 */
public class SortTestHelper {
    private SortTestHelper() {
    }

    // 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
    static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert rangeL < rangeR : "something goes wrong here;";
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
            arr[i] = (int) (Math.random() * (rangeL - rangeR + 1) + rangeL);
        return arr;
    }

    // 打印arr数组的所有内容
    public static void printArray(Object arr[]) {

        for (Object i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // 判断arr数组是否有序
    public static boolean isSorted(Comparable[] arr){

        for( int i = 0 ; i < arr.length - 1 ; i ++ )
            if( arr[i].compareTo(arr[i+1]) > 0 )
                return false;
        return true;
    }


    // 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
    public static void testSort(String sortClassName, Comparable[] arr){

        try{
            // 通过sortClassName获得排序函数的Class对象
            Class sortClass = Class.forName(sortClassName);
            // 通过排序函数的Class对象获得排序方法
            Method sortMethod = sortClass.getMethod("sort",new Class[]{Comparable[].class});
            // 排序参数只有一个，是可比较数组arr
            Object[] params = new Object[]{arr};

            long startTime = System.currentTimeMillis();
            // 调用排序函数
            sortMethod.invoke(null,params);
            long endTime = System.currentTimeMillis();

            assert isSorted( arr );

            System.out.println( sortClass.getSimpleName()+ " : " + (endTime-startTime) + "ms" );
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
