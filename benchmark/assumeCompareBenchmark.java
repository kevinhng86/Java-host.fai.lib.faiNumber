/*
 * Copyright 2019 Khang Hoang Nguyen
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files 
 * (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 **/
import host.fai.lib.faiNumber.Base10Util;
import host.fai.lib.faiNumber.NumberStringUtil;
import java.util.Random;
/* assumeCompare vs normal comparision */
public final class assumeCompareBenchmark{
    public static void main(String[] args){
        long start, end, result;
        int round = 10000000;
        String[] numbers1 = new String[round];
        String[] numbers2 = new String[round];
        Random r = new Random();

        System.out.println("=== Common Parse String Then Compare vs NumberStringUtil.assumeCompare() vs NumberStringUtil.assumeCompareAllBase() vs NumberStringUtil.compare() vs NumberStringUtil.compareAsBase() vs LongUtil.compareAsLong() Benchmark === ");
        System.out.println();
        System.out.println("== Generating random data.");
        System.out.println();
        for ( int i = 0; i < round; i++ ){
            numbers1[i] = ""+r.nextLong();
            numbers2[i] = ""+r.nextLong();
        }
        
        System.out.println("== Random data generating done.");
        System.out.println(String.format("Printing one set of the random data to make sure they are all right. '%s', '%s'.", numbers1[0], numbers2[0]));
        System.out.println();
        System.out.println();
        System.out.println();

        /** 10000000 different string benchmark */
        System.out.println("== Common Parse String Then Compare Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ){
            if ( Base10Util.toLong(numbers1[i]) <= Base10Util.toLong(numbers2[i]) ) continue;
        }
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("Common Parse String Then Compare method compared '%d' sets of number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println("== NumberStringUtil.assumeCompare() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ){
            if( NumberStringUtil.assumeCompare(numbers1[i], numbers2[i]) < 1 ) continue;
        }
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("NumberStringUtil.assumeCompare() method compared '%d' sets of number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("== NumberStringUtil.assumeCompareAllBase() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ){
            if( NumberStringUtil.assumeCompareAllBase(numbers1[i], numbers2[i]) < 1 ) continue;
        }
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("NumberStringUtil.assumeCompareAllBase() method compared '%d' sets of number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println("== NumberStringUtil.compare() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ){
            if( NumberStringUtil.compare(numbers1[i], numbers2[i]) < 1 ) continue;
        }
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("NumberStringUtil.compare() method compared '%d' sets of number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
      
        System.out.println("== NumberStringUtil.compareAsBase() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ){
            if( NumberStringUtil.compareAsBase(numbers1[i], numbers2[i], 10) < 1 ) continue;
        }
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("NumberStringUtil.compareAsBase() method compared '%d' sets of number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
      
        System.out.println("== LongUtil.compareAsLong() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ){
            if( Base10Util.compareAsLong(numbers1[i], numbers2[i]) < 1 ) continue;
        }
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("Base10Util.compareAsLong() method compared '%d' sets of number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println("**************************************************************************");
        System.out.println("************************Worse Case Benchmark******************************");
        System.out.println("**************************************************************************");

        String num1 = "123456789123456789";
        String num2 = "123456789123456789";
        System.out.println("== Common Parse String Then Compare, Same String Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ){
            if ( Base10Util.toLong(num1) <= Base10Util.toLong(num2) ) continue;
        }
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("Common Parse String Then Compare method compared string number '%s' to string number '%s' for %d rounds in %d ms.", num1, num2, round, result) );
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("== NumberStringUtil.assumeCompare(), Equal Length Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ){
            if ( NumberStringUtil.assumeCompare(num1, num2) < 1 ) continue;
        }
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("NumberStringUtil.assumeCompare() method compared string number '%s' to string number '%s' for %d rounds in %d ms.", num1, num2, round, result) );
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("== NumberStringUtil.compare(), Equal Length Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ){
            if ( NumberStringUtil.compare(num1, num2) < 1 ) continue;
        }
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("NumberStringUtil.compare() method compared string number '%s' to string number '%s' for %d rounds in %d ms.", num1, num2, round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
