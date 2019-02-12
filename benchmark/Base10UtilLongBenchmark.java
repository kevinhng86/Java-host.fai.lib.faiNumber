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
import java.util.Random;
public final class Base10UtilLongBenchmark{
    public static void main(String[] args){
        long start;
        long end;
        long result;
        int round = 10000000;
        String[] str = new String[round];
        Random r = new Random();

        System.out.println("=== faiNumber.Base10Util.toLong() benchmark test === ");
        System.out.println();
        System.out.println("== Generating random data.");
        System.out.println();
        for ( int i = 0; i < round; i++ ){
            str[i] = ""+ r.nextLong();
        }
        System.out.println("== Random data generating done.");
        System.out.println(String.format("Printing one of the random data to make sure they are all right. '%s'.", str[0]));
        System.out.println();
        System.out.println();
        System.out.println();

        /** Integer.parseInt() Benchmark */
        System.out.println("== Long.parseLong() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) Long.parseLong(str[i]);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("Long.parseLong() parsed '%d' number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();

        /** IntUtil Benchmark */
        System.out.println("== faiNumber.Base10Util.toLong() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) Base10Util.toLong(str[i]);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("faiNumber.Base10Util.toLong() parsed '%d' number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();

        /** Same string parsing benchmark **/
        System.out.println("== Starting Same String Parsing Benchmark Test");
        String number = ""+Long.MIN_VALUE;

        System.out.println("== faiNumber.Base10Util.toLong() Same String Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) Base10Util.toLong(number);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("faiNumber.Base10Util.toLong() parsed string '%s' '%d' times in %d ms.", number, round, result) );
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("== Long.parseLong() Same String Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) Long.parseLong(number);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("Long.parseLong parsed string '%s' '%d' times in %d ms.", number, round, result) );

    }
}
