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
import host.fai.lib.faiNumber.BinaryUtil;
import java.util.Random;
public final class BinaryUtilBenchmark{
    public static void main(String[] args){
        long start;
        long end;
        long result;
        int round = 10000000;
        String[] str = new String[round];
        Random r = new Random();

        System.out.println("=== faiNumber.BinaryUtil.toInt(), Integer.parseInt() Benchmark === ");
        System.out.println();
        System.out.println("== Generating random data.");
        System.out.println();
        for ( int i = 0; i < round; i++ ){
            str[i] = Integer.toBinaryString(r.nextInt(Integer.MAX_VALUE));
        }
        System.out.println("== Random data generating done.");
        System.out.println(String.format("Printing one of the random data to make sure they are all right. '%s'.", str[0]));
        System.out.println();
        System.out.println();
        System.out.println();

        /** 10000000 different string benchmark */
        System.out.println("== faiNumber.BinaryUtil.toInt() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) BinaryUtil.toInt(str[i]);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("faiNumber.BinaryUtil.toInt() parsed '%d' number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println("== Integer.parseInt() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) Integer.parseInt(str[i], 2);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("Integer.parseInt() parsed '%d' number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();

        /** Same string parsing benchmark **/
        System.out.println("== Starting Same String Parsing Benchmark Test");
        String number = Integer.toBinaryString(Integer.MAX_VALUE);

        System.out.println("== faiNumber.BinaryUtil.toInt() Same String Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) BinaryUtil.toInt(number);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("faiNumber.BinaryUtil.toInt() parsed string '%s' '%d' times in %d ms.", number, round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println("== Integer.parseInt() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) Integer.parseInt(number, 2);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("Integer.parseInt() parsed string '%s' '%d' times in %d ms.", number, round, result) );
        System.out.println();
        System.out.println();
        System.out.println();

        /* toLong() */
        str = new String[round];
        long randomNumber = 0L;
        System.out.println("=== faiNumber.BinaryUtil.toLong(), Long.parseLong() Benchmark === ");
        System.out.println();
        System.out.println("== Generating random data.");
        System.out.println();
        for ( int i = 0; i < round; i++ ){
            randomNumber = r.nextLong();
            if ( randomNumber < 0 ) randomNumber = randomNumber * -1L;
            str[i] = Long.toBinaryString(randomNumber);
        }
        System.out.println("== Random data generating done.");
        System.out.println(String.format("Printing one of the random data to make sure they are all right. '%s'.", str[0]));
        System.out.println();
        System.out.println();
        System.out.println();

        /** Long - 10000000 different string benchmark */
        System.out.println("== faiNumber.BinaryUtil.toLong() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) BinaryUtil.toLong(str[i]);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("faiNumber.BinaryUtil.toLong() parsed '%d' number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println("== Long.parseLong() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) Long.parseLong(str[i], 2);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("Long.parseLong() parsed '%d' number strings in %d ms.", round, result) );
        System.out.println();
        System.out.println();
        System.out.println();

        /** Long - same string parsing benchmark **/
        System.out.println("== Starting Same String Parsing Benchmark Test");
        number = Long.toBinaryString(Long.MAX_VALUE);

        System.out.println("== faiNumber.BinaryUtil.toLong() Same String Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) BinaryUtil.toLong(number);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("faiNumber.BinaryUtil.toLong() parsed string '%s' '%d' times in %d ms.", number, round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
        
        System.out.println("== Long.parseLong() Bench Mark Start.");
        start = System.currentTimeMillis();
        for( int i = 0; i < round; i++ ) Long.parseLong(number, 2);
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println(String.format("Long.parseLong() parsed string '%s' '%d' times in %d ms.", number, round, result) );
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
